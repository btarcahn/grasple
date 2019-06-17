package org.grasple.api.structures.graphs;

import org.grasple.api.particles.Connectable;

import java.util.function.Consumer;

/**
 * All tasks that a graph can do.
 * @author Bach Tran
 */
public interface GraphStructure<T> {
    /**
     * Traverses to all vertices in the graph.
     */
    void traverse();

    /**
     * Traverses to all vertices in the graph, and apply
     * an action to each vertex visited.
     * @param action the action to be visited.
     */
    void traverse(Consumer<T> action);
}
