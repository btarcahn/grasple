package org.grasple.api.structures;

import org.grasple.api.fundamentals.Connectable;
import org.grasple.api.fundamentals.Vertex;

import java.util.function.Consumer;

/**
 * Graph that has one and only one connected component only. Therefore,
 * it has a root Vertex, from which we can traverse to other vertices.
 * A connected graph has a different implementation from a disconnected
 * graph. See the class definition of SimpleGraph for more information.
 * @see SimpleGraph
 * @author Bach Tran
 * @param <T> the datatype that the connected graph contains
 */
public class ConnectedGraph<T> implements GraphStructure {
    private Connectable root;

    /**
     * Creates a connected graph with at least one root vertex.
     * @param root
     */
    public ConnectedGraph(Connectable root) {
        this.root = root;
    }

    /**
     * @return the exact root vertex of this connected graph.
     */
    public Connectable getRoot() {
        return root;
    }

    @Override
    public void traverse() {

    }

    @Override
    public void traverse(Consumer<Connectable> action) {

    }
}
