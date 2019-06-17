package org.grasple.api.structures.graphs;

import org.grasple.api.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectedGraphTest {
    ConnectedGraph<String> _common_connected_graph;
    @BeforeEach
    void setCommonConnectedGraph() {
        _common_connected_graph = new ConnectedGraph<>(TestUtils.CENTRAL_VERTEX);
    }

    @Test
    void getRoot() {
        assertEquals(_common_connected_graph.getRoot(), TestUtils.CENTRAL_VERTEX);
    }

    @Test
    void traverse() {
    }

    @Test
    void traverseWithAction() {
        _common_connected_graph.traverse();
    }
}