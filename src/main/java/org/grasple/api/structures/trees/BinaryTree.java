package org.grasple.api.structures.trees;

import org.grasple.api.particles.Connectable;
import org.grasple.api.particles.IndexSaturatedException;
import org.grasple.api.particles.IndexableVertex;
import org.grasple.api.particles.OrderedVertex;


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
    private static final short LEFT = 0;
    private static final short RIGHT = 1;
    private static final short BIN_BOUND = 2;

    public BinaryTree(T value) {
        super(new IndexableVertex<>(value, BIN_BOUND));
    }

    public BinaryTree(IndexableVertex<T> root) {
        super(root);
    }

    public boolean add(T value) {
        assert this.getRoot() instanceof IndexableVertex;
        _add((IndexableVertex<T>) getRoot(), value);
        return false;
    }

    private boolean _add(IndexableVertex<T> vertex, T value) {
        // TODO check the preconditions of vertex
        // add to this vertex
        if (value.compareTo(vertex.get()) < 0) {
            try {
                vertex.connect(createBinVertex(value), LEFT);
            } catch (IndexSaturatedException e) {
                assert vertex.getNeighbor(LEFT) != null;
                return _add(vertex.getNeighbor(LEFT), value);
            }
            return true;
        } else {
            try {
                vertex.connect(createBinVertex(value), RIGHT);
            } catch (IndexSaturatedException e) {
                assert vertex.getNeighbor(RIGHT) != null;
                return _add(vertex.getNeighbor(RIGHT), value);
            }
            return true;
        }
    }

    private IndexableVertex<T> createBinVertex(T value) {
        return new IndexableVertex<>(value, BIN_BOUND);
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
