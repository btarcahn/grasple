package org.grasple.api.particles;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Vertex that can connect to others using a unique number.
 * This class is mutable.
 * The underlying neighbor collection is implemented using
 * a HashMap.
 * @param <T> a Comparable type.
 * @author Bach Tran
 */
public class NumberedVertex<T extends Comparable<T>> implements
        NumberedConnectable<T> {

    private T value;
    private Map<Integer, NumberedConnectable<T>> neighbors;

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
        if (!(o instanceof NumberedConnectable)) {
            return false;
        }
        NumberedConnectable<T> connectable = (NumberedConnectable<T>) o;
        return neighbors.containsValue(connectable);
    }

    @Override
    public Collection<NumberedConnectable<T>> getNeighbors() {
        return neighbors.values();
    }

    @Override
    public boolean connect(Integer index, NumberedConnectable<T> other)
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
    public boolean occupied(Integer index) {
        return neighbors.containsKey(index);
    }

    @Override
    public NumberedConnectable<T> jumpTo(Integer index)
            throws IllegalArgumentException {

        if (!neighbors.containsKey(index))
            throw new IllegalArgumentException("No vertex exists at index " + index);

        return neighbors.get(index);
    }
}
