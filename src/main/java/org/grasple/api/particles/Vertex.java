package org.grasple.api.particles;

import java.util.*;

/**
 * <p>
 * Represents a Vertex in graph theory. Objects of this class are
 * non-comparable; therefore, this object is conventionally named
 * <b>unordered-</b>vertex. An unordered-vertex has a Set of
 * neighbors. Therefore will be randomized during traversals.
 * </p>
 * @author Bach Tran
 * @since 1.0
 * @param <T> type that will not be considered of its comparability.
 */
public class Vertex<T> implements Connectable<T> {

    private T value;
    private Set<Connectable<T>> neighbors;


    /**
     * Creates a Vertex given only a not-null value.
     * @param value the value of the vertex
     */
    public Vertex(T value) {
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
