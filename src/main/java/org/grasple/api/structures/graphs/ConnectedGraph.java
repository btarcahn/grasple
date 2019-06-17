package org.grasple.api.structures.graphs;

import org.grasple.api.particles.Connectable;

import java.util.HashSet;
import java.util.Set;
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
public class ConnectedGraph<T> implements GraphStructure<T> {
    /**
     * The only vertex that a connected graph should have, since
     * it has one and ONLY one connected component. Other vertices
     * can be reached by doing traversals starting from this root
     * vertex.
     */
    private Connectable<T> root;
    /**
     * Creates a connected graph with at least one root vertex.
     * @param root
     */
    public ConnectedGraph(Connectable<T> root) {
        this.root = root;
    }

    /**
     * @return the exact root vertex of this connected graph.
     */
    public Connectable<T> getRoot() {
        return root;
    }

    @Override
    public void traverse() {
        recursiveDepthFirstTraversal(root, new HashSet<>());
    }

    @Override
    public void traverse(Consumer<T> action) {
        recursiveDepthFirstTraversal(action, root, new HashSet<>());
    }

    private void recursiveDepthFirstTraversal(Connectable<T> startVertex,
                                              Set<Connectable<T>> visited) {
        visited.add(startVertex);
        startVertex.getNeighbors()
                .stream()
                .filter(neighbor -> !visited.contains(neighbor))
                .forEach(neighbor -> recursiveDepthFirstTraversal(neighbor, visited));
    }

    private void recursiveDepthFirstTraversal(Consumer<T> action, Connectable<T> startVertex,
                                              Set<Connectable<T>> visited) {
        action.accept(startVertex.get());
        visited.add(startVertex);
        startVertex.getNeighbors()
                .stream()
                .filter(neighbor -> !visited.contains(neighbor))
                .forEach(neighbor -> recursiveDepthFirstTraversal(neighbor, visited));
    }
}
