package za.co.discovery.assignment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import za.co.discovery.assignment.model.Planet;
import za.co.discovery.assignment.model.Route;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private FileUtil() {

    }

    public static List<Planet> loadPlanetsFromFile() {
        List<Planet> planets = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("data/Planets.txt")));) {
            String line = reader.readLine(); //Header
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(",");
                Planet p = new Planet();
                p.setNodeName(arr[0]);
                p.setPlanetName(arr[1]);
                planets.add(p);
            }
        } catch (Exception e) {
            logger.error("Error reading planets from file {}", e.getMessage());
        }
        return planets;
    }

    public static List<Route> loadRoutesFromFile() {
        List<Route> routes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("data/Routes.txt")))) {
            String line = reader.readLine(); //Header
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(",");
                Route obj = new Route();
                obj.setRouteId(Integer.parseInt(arr[0]));
                obj.setOrigin(arr[1]);
                obj.setDestination(arr[2]);
                obj.setDistance(Double.parseDouble(arr[3]));
                routes.add(obj);
            }
        } catch (Exception e) {
            logger.error("Error reading routes from file {}", e.getMessage());
        }
        return routes;
    }
}
