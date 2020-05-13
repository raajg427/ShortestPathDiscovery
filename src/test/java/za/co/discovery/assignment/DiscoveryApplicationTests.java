package za.co.discovery.assignment;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import za.co.discovery.assignment.algo.DijkstraPathFinder;
import za.co.discovery.assignment.model.Edge;
import za.co.discovery.assignment.model.Node;

@RunWith(MockitoJUnitRunner.class)
public class DiscoveryApplicationTests {

    @Test
    public void testAlgo() {
        DijkstraPathFinder finder = new DijkstraPathFinder();
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        nodeA.addEdge(new Edge(10, nodeA, nodeB));
        nodeA.addEdge(new Edge(20, nodeA, nodeC));
        nodeB.addEdge(new Edge(5, nodeB, nodeC));
        finder.calculatePathAndUpdateNodes(nodeA);
        Assert.assertEquals(15.0, nodeC.getDistance(), 0);
    }


}
