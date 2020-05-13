package za.co.discovery.assignment.algo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.discovery.assignment.model.Edge;
import za.co.discovery.assignment.model.Node;

import java.util.PriorityQueue;

public class DijkstraPathFinder {
    private Logger logger = LoggerFactory.getLogger(DijkstraPathFinder.class);

    public void calculatePathAndUpdateNodes(Node originNode) {
        logger.info("Dijkstra Algo Calculation started for origin : {}", originNode.getName());
        originNode.setDistance(0);
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(originNode);
        originNode.setVisited(true);
        while (!priorityQueue.isEmpty()) {
            Node actualNode = priorityQueue.poll();
            for (Edge edge : actualNode.getEdgesList()) {
                Node targetV = edge.getTargetNode();
                if (!targetV.isVisited()) {
                    double newDistance = actualNode.getDistance() + edge.getWeight();
                    if (newDistance < targetV.getDistance()) {
                        priorityQueue.remove(targetV);
                        targetV.setDistance(newDistance);
                        targetV.setPredecessor(actualNode);
                        priorityQueue.add(targetV);
                    }
                }
            }
            actualNode.setVisited(true);
        }
        logger.info("Dijkstra Path Finder ended for origin : {}", originNode.getName());
    }
}
