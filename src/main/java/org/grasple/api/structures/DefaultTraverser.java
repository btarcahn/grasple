package org.grasple.api.structures;

import org.grasple.api.particles.Connectable;
import org.grasple.api.particles.Vertex;

import java.util.HashSet;
import java.util.Set;

/**
 * Contains the Depth First Traversal algorithm. The class
 * implements the Runnable interface.
 * @see Runnable
 * @author Bach Tran
 */
public final class DefaultTraverser implements Runnable {
    /** The vertex where the algorithm commences. */
    private Connectable start;
    /** A list of visited vertices */
    private Set<Connectable> visited = new HashSet<>();
    /**
     * Creates a new DefaultTraverser contains the
     * depth-first traversal algorithm.
     * @param start the vertex where the algorithm commences.
     * */
    public DefaultTraverser(Vertex start) { this.start = start; }

    /**
     * Returns all visited vertices by this algorithm.
     * @return a Set of all visited vertices.
     */
    public Set<Connectable> getVisited() {
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
    private void recursion(Connectable vertex) {
        visited.add(vertex);
        vertex.getNeighbors().forEach(neighbor -> {
            if (!visited.contains(neighbor)) {
                assert neighbor instanceof Connectable;
                recursion((Connectable) neighbor);
            }
        });
    }
}
