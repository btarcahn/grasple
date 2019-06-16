package org.grasple.api.structures.trees;

import org.grasple.api.particles.Connectable;
/**
 * A binary tree has a root vertex, also known as the root of
 * the tree. Each vertex in a binary tree has maximum two neighbors.
 * @param <T> the datatype that the binary tree contains
 * @author Bach Tran
 */
public class BinaryTree<T> extends Tree<T> {

    /**
     * Creates a connected graph with at least one root vertex.
     * @param root the root vertex of the tree.
     */
    public BinaryTree(Connectable root) {
        super(root);
    }
}
