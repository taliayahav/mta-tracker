package com.talia.mtatracker.service;

import com.talia.mtatracker.model.Route;
import com.talia.mtatracker.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Route getRouteByRouteId(String routeId) {
        return routeRepository.findByRouteId(routeId)
                .orElseThrow(() -> new RuntimeException("Route not found: " + routeId));
    }

    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }
}