package com.talia.mtatracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ArrivalDTO {

    private String routeId;       // e.g. "A"
    private String stopId;        // e.g. "127N"
    private long arrivalTime;     // Unix timestamp from MTA feed
    private final long minutesUntilArrival;

    public ArrivalDTO(String routeId, String stopId, long arrivalTime) {
        this.routeId = routeId;
        this.stopId = stopId;
        this.arrivalTime = arrivalTime;
        this.minutesUntilArrival = Math.max(0,
            (arrivalTime - System.currentTimeMillis() / 1000) / 60);
    }    
}