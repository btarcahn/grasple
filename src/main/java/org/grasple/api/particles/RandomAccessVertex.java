package org.grasple.api.particles;

import java.util.*;

/**
 * The neighbors of this class are accessed not in a particular
 * order. A HashSet is implemented as a collection of neighbors.
 * @author Bach Tran
 * @since 1.0
 * @param <T> any data type.
 */
public class RandomAccessVertex<T> implements Connectable<T> {

    private T value;
    private Set<Connectable<T>> neighbors;

    /**
     * Creates a RandomAccessVertex given only a not-null value.
     * @param value the value of the vertex
     */
    public RandomAccessVertex(T value) {
        this.value = value;
        this.neighbors = new HashSet<>();
    }

    @Override
    public Collection<Connectable<T>> next() {
        return neighbors;
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
        if (!(o instanceof Connectable)) {
            return false;
        }
        Connectable<T> connectable = (Connectable<T>) o;
        return neighbors.contains(connectable);
    }

    @Override
    public boolean connect(Connectable<T> other) {
        if (this == other) { return false; }
        return this.neighbors.add(other);
    }

    public boolean disconnect(Connectable<T> other) {
        return this.neighbors.remove(other);
    }
}
