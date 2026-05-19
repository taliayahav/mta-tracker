package com.talia.mtatracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArrivalDTO {

    private String routeId;       // e.g. "A"
    private String stopId;        // e.g. "127N"
    private long arrivalTime;     // Unix timestamp from MTA feed
}