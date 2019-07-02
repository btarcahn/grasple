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

        return super.adjacent(o);
    }

    BinaryVertex<T> getParent() {
        return parent;
    }

    void setParent(BinaryVertex<T> parent) {
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
}
