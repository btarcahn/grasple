package org.grasple.api.particles;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * All connections in this vertex is implemented
 * as a HashMap.
 * @author Bach Tran
 * @since 1.0
 * @param <T>
 */
public class IndexedVertex<T> implements IndexedConnectable<T> {

    private T value;
    private Map<Integer, BinaryConnection> connections;

    /**
     * Creates an indexed vertex with an empty map of neighbors
     * and an index 0.
     * @param value
     */
    public IndexedVertex(T value) {
        this.value = value;
        this.connections = new HashMap<>();
    }


    @Override
    public boolean isSaturated() {
        return false;
    }

    @Override
    public boolean addConnection(int index, BinaryConnection connection) {
        if (connections.containsKey(index)) {
            return false;
        }
        connections.put(index, connection);
        return true;
    }

    @Override
    public boolean removeConnection(int index) {
        if (!connections.containsKey(index)) {
            return false;
        }
        connections.remove(index);
        return true;
    }

    @Override
    public boolean connect(int index, Connectable<T> other) {
        return false;
    }

    @Override
    public IndexedConnectable<T> getNeighbor(int index) {
        return (IndexedConnectable<T>)
                connections.get(index).divert(this);
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public boolean adjacent(Connectable<T> other) {
        return getNeighbors().contains(other);
    }

    @Override
    public Collection<BinaryConnection> getConnections() {
        return connections.values();
    }

    @Override
    public boolean addConnection(BinaryConnection connection) {
        return false;
    }

    @Override
    public boolean removeConnection(BinaryConnection connection) {
        return false;
    }

    @Override
    public BinaryConnection connect(Connectable<T> other) {
        return null;
    }

    @Override
    public void disconnect(Connectable<T> other) {

    }

    @Override
    public Collection<Connectable> getNeighbors() {
        return connections.values()
                .stream()
                .map(connection -> connection.divert(this))
                .collect(Collectors.toList());
    }
}
