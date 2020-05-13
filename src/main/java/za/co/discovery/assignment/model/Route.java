package za.co.discovery.assignment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROUTES")
public class Route {
    @Id
    @Column(name = "route_id")
    private int routeId;
    @Column(name = "origin")
    private String origin;
    @Column(name = "destination")
    private String destination;
    @Column(name = "distance")
    private double distance;

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null || (this.getClass() != obj.getClass()))
            return false;
        Route otherObj = (Route) obj;
        return (this.routeId == otherObj.routeId);
    }

    @Override
    public String toString() {
        return "[RouteId: " + routeId + " Origin: " + this.origin + " Destination: " + destination + " Distance: " + distance + "]";
    }
}
