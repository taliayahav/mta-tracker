package com.talia.mtatracker.controller;

import com.talia.mtatracker.model.Route;
import com.talia.mtatracker.service.RouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public ResponseEntity<List<Route>> getAllRoutes() {
        return ResponseEntity.ok(routeService.getAllRoutes());
    }

    @GetMapping("/{routeId}")
    public ResponseEntity<Route> getRoute(@PathVariable String routeId) {
        return ResponseEntity.ok(routeService.getRouteByRouteId(routeId));
    }

    @PostMapping
    public ResponseEntity<Route> createRoute(@RequestBody Route route) {
        return ResponseEntity.ok(routeService.saveRoute(route));
    }
}