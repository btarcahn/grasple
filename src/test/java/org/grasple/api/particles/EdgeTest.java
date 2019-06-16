package org.grasple.api.particles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Performs basic black-box unit tests on Edge
 */
class EdgeTest {
    static final String SAMPLE_STRING = "sample";
    Vertex<String> vertex1;
    Vertex<String> vertex2;
    Edge sampleEdge;
    @BeforeEach
    void buildUp() {
        vertex1 = new Vertex(SAMPLE_STRING);
        vertex2 = new Vertex(SAMPLE_STRING + "1");
        sampleEdge = new Edge(vertex1, vertex2);
    }
    @Test
    void getWeight() {
        assertEquals(0, sampleEdge.getWeight());
    }

    @Test
    void setWeight() {
        sampleEdge.setWeight(500);
        assertEquals(500, sampleEdge.getWeight());
    }

    @Test
    void setStart() {
        sampleEdge.setStart(vertex2);
        assertEquals(vertex2, sampleEdge.getStart());
    }

    @Test
    void setEnd() {
        sampleEdge.setEnd(vertex1);
        assertEquals(vertex1, sampleEdge.getEnd());
    }

    @Test
    void divert() {
        sampleEdge.setStart(vertex1);
        sampleEdge.setEnd(vertex2);
        assertEquals(vertex1, sampleEdge.divert(vertex2));
        assertEquals(vertex2, sampleEdge.divert(vertex1));
    }
}