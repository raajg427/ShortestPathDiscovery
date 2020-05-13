package za.co.discovery.assignment.model;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node> {

    private String name;
    private List<Edge> edgesList;
    private boolean visited;
    private Node predecessor;
    private double distance = Double.MAX_VALUE;

    public Node(String name) {
        this.name = name;
        this.edgesList = new ArrayList<>();
    }

    public void addEdge(Edge edge) {
        this.edgesList.add(edge);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Edge> getEdgesList() {
        return edgesList;
    }

    public void setEdgesList(List<Edge> edgesList) {
        this.edgesList = edgesList;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Node getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Node predecessor) {
        this.predecessor = predecessor;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(Node otherNode) {
        return Double.compare(this.distance, otherNode.getDistance());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || (this.getClass() != obj.getClass()))
            return false;
        Node otherNode = (Node) obj;
        return (this.name.equals(otherNode.name));
    }
}