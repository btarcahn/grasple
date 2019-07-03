package org.grasple.api.particles;

/**
 * Specific type of vertex to build binary tree.
 * @param <T>
 */
final class BinaryVertex<T extends Comparable<T>> extends NumericalVertex<T> {

    private static final int LEFT = 0;
    private static final int MIDDLE = 1;
    private static final int RIGHT = 2;

    private BinaryVertex<T> parent;

    public BinaryVertex(T value) {
        super(value);
    }

    @Override
    public boolean adjacent(Node<T> o) {
        if (!(o instanceof BinaryVertex)) return false;

        return this.left() == o || this.right() == o ||
                this.middle() == o;
    }

    /**
     * This vertex is connected to only one parent
     * of the same type.
     * @return the parent node of this binary vertex.
     */
    BinaryVertex<T> parent() {
        assert parent != this;
        return parent;
    }

    /**
     * Modifies the parent node of this vertex
     * @param parent the new parent of the vertex.
     */
    void setParent(BinaryVertex<T> parent)
            throws UnsupportedOperationException {
        if (parent == this) {
            throw new UnsupportedOperationException("Self connection is not supported.");
        }
        this.parent = parent;
    }

    @Override
    public BinaryVertex<T> jumpTo(Integer index)
            throws NullPointerException {
        assert super.jumpTo(index) instanceof BinaryVertex;
        return (BinaryVertex<T>) super.jumpTo(index);
    }

    BinaryVertex<T> left() throws NullPointerException {
        if (!this.occupied(LEFT)) {
            throw new NullPointerException("No object found on the left node");
        }
        return this.jumpTo(LEFT);
    }

    BinaryVertex<T> right() throws NullPointerException {
        if (!this.occupied(RIGHT)) {
            throw new NullPointerException("No object found on the right node");
        }
        return this.jumpTo(RIGHT);
    }

    BinaryVertex<T> middle() throws NullPointerException {
        if (!this.occupied(MIDDLE)) {
            throw new NullPointerException("No object found on the middle node.");
        }
        return this.jumpTo(MIDDLE);
    }

    /**
     * Contains binary logical operations that does
     * the comparison of the this vertex with the added
     * vertex so that it shall put the added vertex to the
     * right direction.
     * @param o the new vertex to be conneted.
     */
    void connect(BinaryVertex<T> o) {
        if (o.get().compareTo(this.get()) < 0) {
            if (this.occupied(LEFT)) {
                this.left().connect(o);
            } else {
                this.allocate(LEFT, o);
            }
        } else if (o.get().compareTo(this.get()) > 0) {
            if (this.occupied(RIGHT)) {
                this.right().connect(o);
            } else {
                this.allocate(RIGHT, o);
            }
        } else {
            if (this.occupied(MIDDLE)) {
                this.middle().connect(o);
            } else {
                this.allocate(MIDDLE, o);
            }
        }
    }
}
