package org.grasple.api.particles;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * RandomAccessVertex that can connect to others using a unique number.
 * This class is mutable.
 * The underlying neighbor collection is implemented using
 * a HashMap.
 * @param <T> a Comparable type.
 * @author Bach Tran
 */
public class NumberedVertex<T extends Comparable<T>> implements
        Allocatable<T> {

    private T value;
    private Map<Integer, Allocatable<T>> neighbors;

    public NumberedVertex(T value) {
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
    public boolean connect(Integer index, Allocatable<T> other)
            throws IllegalArgumentException {

        if (this == other) {
            throw new IllegalArgumentException("Immediate connection is not allowed.");
        }

        if (neighbors.containsValue(other)) {
            return false;
        }

        neighbors.put(index, other);
        return true;
    }

    @Override
    public boolean disconnect(Integer index) {
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
            throws IllegalArgumentException {

        if (!neighbors.containsKey(index))
            throw new IllegalArgumentException("No vertex exists at index " + index);

        return neighbors.get(index);
    }
}
