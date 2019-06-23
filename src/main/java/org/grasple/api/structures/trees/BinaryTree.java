package org.grasple.api.structures.trees;

import org.grasple.api.particles.*;


/**
 * A binary tree has a root vertex, also known as the root of
 * the tree. Each vertex in a binary tree has maximum two neighbors.
 * <b>Caution: </b> a binary trees uses ordered vertex as its
 * particles.
 * @see OrderedVertex
 * @param <T> the datatype that the binary tree contains
 * @author Bach Tran
 */
public class BinaryTree<T extends Comparable<T>> extends Tree<T> {
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int BIN_BOUND = 2;

    /**
     * Creates a connected graph with at least one root vertex.
     *
     * @param root the root vertex of the tree.
     */
    public BinaryTree(Connectable<T> root) {
        super(root);
    }
}
