package com.talia.mtatracker.service;

import com.talia.mtatracker.model.Stop;
import com.talia.mtatracker.repository.StopRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StopService {

    private final StopRepository stopRepository;

    public StopService(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }

    public List<Stop> getAllStops() {
        return stopRepository.findAll();
    }

    public Stop getStopByStopId(String stopId) {
        return stopRepository.findByStopId(stopId)
                .orElseThrow(() -> new RuntimeException("Stop not found: " + stopId));
    }

    public Stop saveStop(Stop stop) {
        return stopRepository.save(stop);
    }
}