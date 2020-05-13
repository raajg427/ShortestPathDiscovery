package za.co.discovery.assignment.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.discovery.assignment.model.Planet;
import za.co.discovery.assignment.model.Route;
import za.co.discovery.assignment.model.ShortestRoute;
import za.co.discovery.assignment.service.DataService;
import za.co.discovery.assignment.service.FileUtil;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DiscoveryController {

    @Autowired
    private DataService service;
    private Logger logger = LoggerFactory.getLogger(DiscoveryController.class);

    @GetMapping("/routes")
    public ResponseEntity<List<Route>> getRoutes() {
        List<Route> routes = service.getRoutes();
        return new ResponseEntity<>(routes, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/planets")
    public ResponseEntity<List<Planet>> getPlanets() {
        List<Planet> planets = service.getPlanets();
        logger.info("getPlanets : {}", planets.size());
        return new ResponseEntity<>(planets, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/update/route")
    public ResponseEntity<String> updateRoute(@RequestBody Route route) {
        service.addOrUpdateRoute(route);
        logger.info("Route updated : {}", route.getRouteId());
        return new ResponseEntity<>(route.getRouteId() + "", new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/delete/route")
    public ResponseEntity<String> deleteRoute(@RequestBody Route route) {
        service.deleteRoute(route);
        logger.info("Route deleted : {}", route.getRouteId());
        return new ResponseEntity<>(route.getRouteId() + "", new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/delete/route/{routeId}")
    public ResponseEntity<String> deleteRoute(@PathVariable int routeId) {
        Route route = service.getRoute(routeId);
        service.deleteRoute(route);
        logger.info("Route deleted : {} ", route.getRouteId());
        return new ResponseEntity<>(routeId + "", new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/update/planet")
    public ResponseEntity<String> updatePlanet(@RequestBody Planet planet) {
        service.addOrUpdatePlanet(planet);
        logger.info("Planet updated : {}", planet.getPlanetName());
        return new ResponseEntity<>(planet.getPlanetName(), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/delete/planet")
    public ResponseEntity<String> deletePlanet(@RequestBody Planet planet) {
        service.deletePlanet(planet);
        logger.info("Planet deleted : {}", planet.getPlanetName());
        return new ResponseEntity<>(planet.getPlanetName(), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/find/{source}/{destination}")
    public ResponseEntity<ShortestRoute> findDistance(@PathVariable String source, @PathVariable String destination) {
        ShortestRoute shortestRoute = service.getShortestRoute(source, destination);
        logger.info(shortestRoute.toString());
        return new ResponseEntity<>(shortestRoute, new HttpHeaders(), HttpStatus.OK);
    }
}
