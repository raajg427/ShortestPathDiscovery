package za.co.discovery.assignment.model;

public class ShortestRoute {

    private String origin;
    private String destination;
    private double distance;
    private String path;

    public ShortestRoute(String origin, String destination, double distance, String path) {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.path = path;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "[origin: " + origin + " Destination:" + destination + " Distance:" + distance + " Path:" + path + " ]";
    }
}
