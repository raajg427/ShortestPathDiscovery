package za.co.discovery.assignment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import za.co.discovery.assignment.algo.DijkstraPathFinder;
import za.co.discovery.assignment.model.*;
import za.co.discovery.assignment.repository.PlanetRepository;
import za.co.discovery.assignment.repository.RouteRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DataService {

    @Autowired
    private PlanetRepository planetRepository;
    @Autowired
    private RouteRepository routeRepository;
    private Logger logger = LoggerFactory.getLogger(DataService.class);

    private Map<String, Planet> planetsMap = new ConcurrentHashMap<>();
    private Map<Integer, Route> routesMap = new ConcurrentHashMap<>();

    private Map<String, ShortestPathGraph> shortestPathGraphMap = new ConcurrentHashMap<>();
    private DijkstraPathFinder dijkstraFinder = new DijkstraPathFinder();

    @Value("${init.data.from.file:true}")
    private boolean initDataFromFile;


    /**
     * Inserts data into db if needed and loads the updated
     * data from db to memory.
     */
    @PostConstruct
    private void initializeDB() {
        logger.debug("Initializing DB");
        logger.info("Init db from file flag set to : " + initDataFromFile);
        if (initDataFromFile || (planetRepository.findAll().isEmpty())) {
            logger.info("Loading planets from file");
            planetRepository.deleteAll();
            planetRepository.saveAll(FileUtil.loadPlanetsFromFile());
        }
        if (initDataFromFile || (routeRepository.findAll().isEmpty())) {
            logger.info("Loading routes from file");
            routeRepository.deleteAll();
            routeRepository.saveAll(FileUtil.loadRoutesFromFile());
        }
        List<Planet> planets = getPlanets();
        planets.stream().forEach(p -> planetsMap.put(p.getNodeName(), p));
        List<Route> routes = getRoutes();
        routes.stream().forEach(r -> routesMap.put(r.getRouteId(), r));
    }

    /**
     * Given an origin name, return the graph with updated shortest path
     *
     * @param origin
     * @return
     */
    private ShortestPathGraph getShortestGraph(String origin) {
        logger.debug("Getting shortest graph {} ", origin);
        if (shortestPathGraphMap.containsKey(origin)) {
            logger.debug("Graph already found in memory for : {}", origin);
            return shortestPathGraphMap.get(origin);
        }
        Map<String, Node> nodesMap = new ConcurrentHashMap<>();
        planetsMap.values().stream().forEach(p -> nodesMap.put(p.getNodeName(), new Node(p.getNodeName())));
        for (Route route : routesMap.values()) {
            Node originNode = nodesMap.get(route.getOrigin());
            Node destinationNode = nodesMap.get(route.getDestination());
            Edge edge = new Edge(route.getDistance(), originNode, destinationNode);
            if (originNode == null || destinationNode == null) {
                logger.warn("Ambiguous route found : {} ", route);
            } else {
                originNode.addEdge(edge);
            }
        }
        Node originNode = nodesMap.get(origin);
        dijkstraFinder.calculatePathAndUpdateNodes(originNode);
        ShortestPathGraph sp = new ShortestPathGraph(origin, nodesMap);
        if (!shortestPathGraphMap.containsKey(origin)) {
            shortestPathGraphMap.put(origin, sp);
        }
        logger.info("Shortest graph updated for :{}", origin);
        return sp;
    }

    /**
     * Calculate shortest distance between source and destination
     *
     * @param source
     * @param destination
     * @return
     */
    public ShortestRoute getShortestRoute(String source, String destination) {
        logger.debug("Shortest route started for {}, {}", source, destination);
        ShortestPathGraph graph = getShortestGraph(source);
        Node destinationNode = graph.getNode(destination);
        List<String> pathList = getShortestPathTo(destinationNode);
        double distance = destinationNode.getDistance();
        String path = getPathString(pathList);
        if (distance == Double.MAX_VALUE) {
            distance = -1;
            path = "Not Reachable";
        }
        ShortestRoute shortestRoute = new ShortestRoute(getPlanetName(source), getPlanetName(destination), distance, path);
        logger.info("Shortest route found for {}, {} ", source, destination);
        return shortestRoute;
    }

    public String getPlanetName(String nodeName) {
        return planetsMap.get(nodeName).getPlanetName();
    }

    /**
     * @param pathList
     * @return
     */
    public String getPathString(List<String> pathList) {
        StringBuilder slb = new StringBuilder();
        for (int i = 0; i < pathList.size(); i++) {
            if (i == pathList.size() - 1) {
                slb.append(getPlanetName(pathList.get(i)));
            } else {
                slb.append(getPlanetName(pathList.get(i)) + ",");
            }
        }
        return slb.toString();
    }

    /**
     * Returns path to destination in a serial order from source
     * If there is no path, then the default max value is returned
     *
     * @param targetNode
     * @return
     */
    private List<String> getShortestPathTo(Node targetNode) {
        List<String> path = new ArrayList<>();
        for (Node node = targetNode; node != null; node = node.getPredecessor()) {
            path.add(node.getName());
        }
        Collections.reverse(path);
        return path;
    }

    public List<Planet> getPlanets() {
        return planetRepository.findAll();
    }

    public Route getRoute(int id) {
        return routesMap.get(id);
    }

    public List<Route> getRoutes() {
        return routeRepository.findAll();
    }

    public void addOrUpdateRoute(Route route) {
        logger.info("Add or update route {}", route.getRouteId());
        routeRepository.save(route);
        routesMap.put(route.getRouteId(), route);
        clearCache();
    }

    public void deleteRoute(Route route) {
        routeRepository.delete(route);
        routesMap.remove(route.getRouteId());
        clearCache();
    }

    public void deleteRoute(int routeId) {
        Route route = routesMap.get(routeId);
        routeRepository.delete(route);
        routesMap.remove(routeId);
        clearCache();
    }

    public void deleteAllRoutes() {
        routeRepository.deleteAll();
        routesMap.clear();
        clearCache();
    }

    public void addOrUpdatePlanet(Planet planet) {
        planetRepository.save(planet);
        planetsMap.put(planet.getNodeName(), planet);
        clearCache();
    }

    public void deletePlanet(Planet planet) {
        planetRepository.delete(planet);
        planetsMap.remove(planet.getNodeName());
        clearCache();
    }

    public void deleteAllPlanets() {
        planetRepository.deleteAll();
        planetsMap.clear();
        clearCache();
    }

    public void clearCache() {
        shortestPathGraphMap.clear();
    }
}
