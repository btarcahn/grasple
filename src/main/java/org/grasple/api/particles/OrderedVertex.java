package org.grasple.api.particles;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Vertex that should be comparable of its value.
 * @param <T> a type that must be Comparable.
 * @author Bach Tran
 */
public class OrderedVertex<T extends Comparable<T>>
                                implements Comparable<OrderedVertex<T>>, Connectable<T> {
    private T value;
    private List<BinaryConnection> connections;

    public OrderedVertex(T value) {
        this.value = value;
        connections = new ArrayList<>();
    }

    @Override
    public int compareTo(OrderedVertex<T> o) {
        return this.value.compareTo(o.value);
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public boolean addConnection(BinaryConnection connection) {
        return connections.add(connection);
    }

    @Override
    public boolean removeConnection(BinaryConnection connection) {
        return connections.remove(connection);
    }

    @Override
    public BinaryConnection connect(Connectable<T> other) {
        BinaryConnection connection = new Edge(this, other);
        this.addConnection(connection);
        if (this != other) { other.addConnection(connection); }
        return connection;
    }

    @Override
    public void disconnect(Connectable<T> other) {
        connections.removeIf(connection -> connection.divert(this) == other);
        // WARNING: the following block is fragile
        if (other instanceof Vertex) {
            ((Vertex<T>) other)
                    .getConnections()
                    .removeIf(connection -> connection.divert(this) == other);
        }
    }

    @Override
    public List<Connectable> getNeighbors() {
        return connections.stream()
                .map(connection -> connection.divert(this))
                .collect(Collectors.toList());
    }
}
