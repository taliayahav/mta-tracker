package com.talia.mtatracker.service;

import com.talia.mtatracker.client.MtaApiClient;
import com.talia.mtatracker.dto.ArrivalDTO;
import com.talia.mtatracker.repository.StopRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

@Service
public class ArrivalService {

    private final MtaApiClient mtaApiClient;
    private final StopRepository stopRepository;

    private static final Map<String, String> ROUTE_TO_FEED = Map.ofEntries(
        entry("A", "gtfs-ace"), entry("C", "gtfs-ace"), entry("E", "gtfs-ace"),
        entry("B", "gtfs-bdfm"), entry("D", "gtfs-bdfm"), entry("F", "gtfs-bdfm"), entry("M", "gtfs-bdfm"),
        entry("G", "gtfs-g"),
        entry("L", "gtfs-l"),
        entry("N", "gtfs-nqrw"), entry("Q", "gtfs-nqrw"), entry("R", "gtfs-nqrw"), entry("W", "gtfs-nqrw"),
        entry("1", "gtfs"), entry("2", "gtfs"), entry("3", "gtfs"),
        entry("4", "gtfs"), entry("5", "gtfs"), entry("6", "gtfs"), entry("7", "gtfs")
    );

    public ArrivalService(MtaApiClient mtaApiClient, StopRepository stopRepository) {
        this.mtaApiClient = mtaApiClient;
        this.stopRepository = stopRepository;
    }

    public List<ArrivalDTO> getArrivalsForStop(String stopId) {
        stopRepository.findByStopId(stopId)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND, "Stop not found: " + stopId));
        return ROUTE_TO_FEED.values().stream()
                .distinct()
                .flatMap(feedId -> mtaApiClient.getArrivalsForStop(stopId, feedId).stream())
                .toList();
    }
}