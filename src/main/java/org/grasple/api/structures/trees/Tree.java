package org.grasple.api.structures.trees;

import org.grasple.api.particles.Connectable;
import org.grasple.api.structures.graphs.ConnectedGraph;

/**
 * A connected graph having no cycles.
 * Extend this class for more specialized tree structures.
 * @author Bach Tran
 */
public class Tree<T> extends ConnectedGraph<T> {
    /**
     * Creates a connected graph with at least one root vertex.
     * @param root the root vertex of the tree.
     */
    public Tree(Connectable root) {
        super(root);
    }
}
