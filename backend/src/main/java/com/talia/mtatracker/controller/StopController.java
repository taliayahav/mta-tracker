package com.talia.mtatracker.controller;

import com.talia.mtatracker.model.Stop;
import com.talia.mtatracker.service.StopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stops")
public class StopController {

    private final StopService stopService;

    public StopController(StopService stopService) {
        this.stopService = stopService;
    }

    @GetMapping
    public ResponseEntity<List<Stop>> getAllStops() {
        return ResponseEntity.ok(stopService.getAllStops());
    }

    @GetMapping("/{stopId}")
    public ResponseEntity<Stop> getStop(@PathVariable String stopId) {
        return ResponseEntity.ok(stopService.getStopByStopId(stopId));
    }

    @PostMapping
    public ResponseEntity<Stop> createStop(@RequestBody Stop stop) {
        return ResponseEntity.ok(stopService.saveStop(stop));
    }
}