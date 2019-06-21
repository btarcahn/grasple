package org.grasple.api.structures.graphs;

import org.grasple.api.particles.BinaryConnection;
import org.grasple.api.particles.Connectable;

import java.util.function.Consumer;

/**
 * All tasks that a graph can do.
 * @author Bach Tran
 */
public interface GraphStructure<T> {
    /**
     * Adds a new connection to the graph, attaches it
     * to the specified connectable object.
     * @param connectable the connectable object that the connection will attach to.
     * @param connection the connection to be attached to the connectable object.
     * @return true if the connection has not yet attached to the connectable.
     */
    boolean addConnection(Connectable<T> connectable, BinaryConnection connection);

    /**
     * Removes the connection from the connectable object.
     * @param connectable the connectable object chosen.
     * @param connection the connection to be removed from the chosen connectable object.
     * @return true if the connection has existed in the connectable object before.
     */
    boolean removeConnection(Connectable<T> connectable, BinaryConnection connection);

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
