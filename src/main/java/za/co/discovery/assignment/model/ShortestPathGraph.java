package za.co.discovery.assignment.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ShortestPathGraph {

    private String origin;
    private Map<String, Node> nodesMap = new ConcurrentHashMap<>();

    public ShortestPathGraph(String origin, Map<String, Node> nodesMap) {
        this.origin = origin;
        this.nodesMap = nodesMap;
    }

    public Node getNode(String nodeName) {
        return nodesMap.get(nodeName);
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Map<String, Node> getNodesMap() {
        return nodesMap;
    }

    public void setNodesMap(Map<String, Node> nodesMap) {
        this.nodesMap = nodesMap;
    }
}
