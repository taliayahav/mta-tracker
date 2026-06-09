package com.talia.mtatracker.config;

import com.talia.mtatracker.model.Stop;
import com.talia.mtatracker.repository.StopRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataSeeder {

    private final StopRepository stopRepository;

    public DataSeeder(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void seedStops() throws Exception {
        if (stopRepository.count() > 0) return;

        List<Stop> stops = new ArrayList<>();
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(new ClassPathResource("stops.txt").getInputStream())
        );

        String line;
        boolean firstLine = true;
        while ((line = reader.readLine()) != null) {
            if (firstLine) { firstLine = false; continue; }

            String[] parts = line.split(",", -1);
            if (parts.length < 4) continue;

            String stopId = parts[0].trim();
            String name = parts[1].trim();
            String locationType = parts[4].trim();

            // only platform-level stops (N/S directional), not parent stations
            if (!locationType.isEmpty()) continue;

            try {
                double lat = Double.parseDouble(parts[2].trim());
                double lon = Double.parseDouble(parts[3].trim());

                Stop stop = new Stop();
                stop.setStopId(stopId);
                stop.setName(name);
                stop.setLatitude(lat);
                stop.setLongitude(lon);
                stops.add(stop);
            } catch (NumberFormatException ignored) {}
        }

        stopRepository.saveAll(stops);
        System.out.println("Seeded " + stops.size() + " stops.");
    }
}
