package org.grasple.api.particles;

import java.util.Collection;

/**
 * The highest interface in the hierarchy.
 * @param <T> any type parameter.
 */
public interface Node<T> {
    /**
     * @return the value of the node.
     */
    T get();

    /**
     * Modifies the value of the node.
     * @param value the new value to be modified.
     */
    void set(T value);

    /**
     * Two objects are adjacent if there is a relationship
     * between them.
     * @param o the other object to be checked for adjacency.
     * @return true if the two objects are adjacent.
     */
    boolean adjacent(Node<T> o);
    /**
     * @return all neighbors of this node.
     */
    Collection<? extends Node<T>> getNeighbors();
}
