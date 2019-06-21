package org.grasple.api.structures.trees;

import org.grasple.api.particles.BinaryConnection;
import org.grasple.api.particles.Connectable;
import org.grasple.api.particles.OrderedVertex;

import java.util.Set;

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

    /**
     * Creates a connected graph with at least one root vertex.
     * Creating a binary tree needs an ordered vertex.
     * @param root the root vertex of the tree.
     */
    public BinaryTree(OrderedVertex<T> root) {
        super(root);
    }

    /**
     * A connected object is saturated if they already own
     * two connections. This method checks whether the given
     * connectable object is saturated or not.
     * @param connectable the connectable object to be checked for saturation
     * @return true if the connectable is saturated.
     */
    private static boolean saturated(Connectable connectable) {
        return connectable.getNeighbors().size() >= 2;
    }
}
