package com.talia.mtatracker.client;

import com.google.transit.realtime.GtfsRealtime.FeedMessage;
import com.google.transit.realtime.GtfsRealtime.TripUpdate.StopTimeUpdate;
import com.talia.mtatracker.dto.ArrivalDTO;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class MtaApiClient {

    private static final String BASE_URL =
        "https://api-endpoint.mta.info/Dataservice/mtagtfsfeeds/nyct%2F";

    public List<ArrivalDTO> getArrivalsForStop(String stopId, String feedId) {
        List<ArrivalDTO> arrivals = new ArrayList<>();

        try {
            URL url = new URL(BASE_URL + feedId);
            InputStream stream = url.openStream();
            FeedMessage feed = FeedMessage.parseFrom(stream);

            for (var entity : feed.getEntityList()) {
                if (!entity.hasTripUpdate()) continue;

                var tripUpdate = entity.getTripUpdate();
                String routeId = tripUpdate.getTrip().getRouteId();

                for (StopTimeUpdate stopTime : tripUpdate.getStopTimeUpdateList()) {
                    if (!stopTime.getStopId().equals(stopId)) continue;
                    if (!stopTime.hasArrival()) continue;

                    long arrivalTime = stopTime.getArrival().getTime();
                    arrivals.add(new ArrivalDTO(routeId, stopId, arrivalTime));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch MTA feed: " + feedId, e);
        }

        return arrivals;
    }
}