package org.grasple.api.structures;

import org.grasple.api.particles.Connectable;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Contains the Depth First Traversal algorithm. The class
 * implements the Runnable interface.
 * @see Runnable
 * @author Bach Tran
 */
public final class DefaultTraverser<T> implements Runnable {
    /** The vertex where the algorithm commences. */
    private Connectable<T> start;
    private Consumer<T> action;
    /** A list of visited vertices */
    private Set<Connectable<T>> visited = new HashSet<>();
    /**
     * Creates a new DefaultTraverser contains the
     * depth-first traversal algorithm.
     * @param start the vertex where the algorithm commences.
     * */
    public DefaultTraverser(Connectable start) { this.start = start; }

    /**
     * Creates a traverser object with a starting vertex and the action.
     * @param start the starting vertex to run the algorithm.
     * @param action the action applied to each vertex visited.
     */
    public DefaultTraverser(Connectable<T> start, Consumer<T> action) {
        this.start = start;
        this.action = action;
    }

    /**
     * Returns all visited vertices by this algorithm.
     * @return a Set of all visited vertices.
     */
    public Set<Connectable<T>> getVisited() {
        return visited;
    }

    @Override
    public void run() {
        recursion(start);
    }

    /**
     * The underlying recursive implementation of
     * the depth-first traversal algorithm.
     * @param vertex the starting vertex of the algorithm
     */
    private void recursion(Connectable<T> vertex) {
        if (action != null) { action.accept(vertex.get()); }
        visited.add(vertex);
        vertex.getNeighbors()
                .stream()
                .filter(neighbor -> !visited.contains(neighbor))
                .forEach(this::recursion);
    }
}
