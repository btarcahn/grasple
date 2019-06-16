package org.grasple.api.structures;

import org.grasple.api.fundamentals.Vertex;

import java.util.HashSet;
import java.util.Set;

/**
 * Contains the Depth First Traversal algorithm. The class
 * implements the Runnable interface.
 * @see Runnable
 * @author Bach Tran
 */
public final class DepthFirstTraverser implements Runnable {
    /** The vertex where the algorithm commences. */
    private Vertex start;
    /** A list of visited vertices */
    private Set<Vertex> visited = new HashSet<>();
    /**
     * Creates a new DepthFirstTraverser contains the
     * depth-first traversal algorithm.
     * @param start the vertex where the algorithm commences.
     * */
    public DepthFirstTraverser(Vertex start) { this.start = start; }

    /**
     * Returns all visited vertices by this algorithm.
     * @return a Set of all visited vertices.
     */
    public Set<Vertex> getVisited() {
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
    private void recursion(Vertex vertex) {
        visited.add(vertex);
        vertex.getNeighbors().forEach(neighbor -> {
            if (!visited.contains(neighbor)) {
                assert neighbor instanceof Vertex;
                recursion((Vertex) neighbor);
            }
        });
    }
}
