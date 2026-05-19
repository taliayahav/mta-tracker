package com.talia.mtatracker.controller;

import com.talia.mtatracker.dto.ArrivalDTO;
import com.talia.mtatracker.service.ArrivalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/arrivals")
public class ArrivalController {

    private final ArrivalService arrivalService;

    public ArrivalController(ArrivalService arrivalService) {
        this.arrivalService = arrivalService;
    }

    @GetMapping("/{stopId}")
    public ResponseEntity<List<ArrivalDTO>> getArrivalsForStop(@PathVariable String stopId) {
        return ResponseEntity.ok(arrivalService.getArrivalsForStop(stopId));
    }
}