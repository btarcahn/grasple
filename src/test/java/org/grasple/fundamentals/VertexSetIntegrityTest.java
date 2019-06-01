package org.grasple.fundamentals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VertexSetIntegrityTest {
    static final short NEIGHBORS = 6;
    final Vertex<String> center = new Vertex<>("center");
    final String[] strings = {"n1", "n2", "n3", "n4", "n5", "n6"};
    List<Vertex<String>> neighbors = new ArrayList<>();

    VertexSetIntegrityTest() {
        for (String string : strings) { neighbors.add(new Vertex<>(string));}
    }
    @BeforeEach
    void getReadyToTest() {
        assertNotNull(center);
        assertTrue(center.getNeighbors().isEmpty());
        assertEquals(NEIGHBORS, strings.length);
    }

    @Test
    void connectToSameVertex() {
        final short times = 100;
        final short neighbor_id = 0;
        for (short i = 0; i < times; i++) {
            center.connect(neighbors.get(neighbor_id));
        }
        assertEquals(1, center.getNeighbors().size());
        assertTrue(center.getNeighbors().contains(neighbors.get(neighbor_id)));
    }
    @Test
    void addSameConnection() {
        final short times = 10;
        neighbors.forEach(center::connect);
        assertEquals(neighbors.size(), center.getNeighbors().size());
        for (short i = 0; i < times; i++) {
            center.addConnection(new Edge(neighbors.get(1), center));
            neighbors.forEach(center::connect);
        }
        assertEquals(neighbors.size(), center.getNeighbors().size());
    }
    @AfterEach
    void clearNeighbors() {
        neighbors.forEach(center::disconnect);
    }

}
