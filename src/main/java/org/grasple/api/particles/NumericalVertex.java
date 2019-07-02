package org.grasple.api.particles;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * RandomAccessVertex that can allocate to others using a unique number.
 * This class is mutable.
 * The underlying neighbor collection is implemented using
 * a HashMap.
 * @param <T> a Comparable type.
 * @author Bach Tran
 */
public class NumericalVertex<T extends Comparable<T>> implements
        Allocatable<T> {

    private T value;
    private Map<Integer, Allocatable<T>> neighbors;

    public NumericalVertex(T value) {
        this.value = value;
        this.neighbors = new HashMap<>();
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public void set(T value) {
        this.value = value;
    }

    @Override
    public boolean adjacent(Node<T> o) {
        if (!(o instanceof Allocatable)) {
            return false;
        }
        Allocatable<T> connectable = (Allocatable<T>) o;
        return neighbors.containsValue(connectable);
    }

    @Override
    public Collection<Allocatable<T>> next() {
        return neighbors.values();
    }

    @Override
    public boolean allocate(Integer index, Allocatable<T> other)
            throws UnsupportedOperationException {

        if (this == other) {
            throw new UnsupportedOperationException("Self-connection not supported.");
        }

        if (neighbors.containsValue(other)) {
            return false;
        }

        neighbors.put(index, other);
        return true;
    }

    @Override
    public boolean deallocate(Integer index) {
        if (!neighbors.containsKey(index)) {
            return false;
        }
        neighbors.remove(index);
        return true;
    }

    @Override
    public boolean disconnect(Allocatable<T> other) {
        if (!neighbors.containsValue(other)) {
            return false;
        }
        return neighbors.keySet().removeIf(key -> neighbors.get(key) == other);
    }

    @Override
    public boolean occupied(Integer index) {
        return neighbors.containsKey(index);
    }

    @Override
    public Allocatable<T> jumpTo(Integer index)
            throws NullPointerException {

        if (!neighbors.containsKey(index))
            throw new NullPointerException("No vertex exists at index " + index);

        return neighbors.get(index);
    }
}
