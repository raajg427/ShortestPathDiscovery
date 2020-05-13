package za.co.discovery.assignment.model;

public class Edge {

    private double weight;
    private Node startNode;
    private Node targetNode;

    public Edge(double weight, Node startNode, Node targetNode) {
        this.weight = weight;
        this.startNode = startNode;
        this.targetNode = targetNode;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public Node getTargetNode() {
        return targetNode;
    }

    public void setTargetNode(Node targetNode) {
        this.targetNode = targetNode;
    }
}

