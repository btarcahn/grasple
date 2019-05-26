package org.grasple.fundamentals;

import java.util.HashSet;
import java.util.Set;

/**
 * <p align = "justify">
 *     Vertex is a basic element in Graph Theory. Here, a Vertex is a generic class,
 *     strongly associates with a generic entity for convenience.
 *     The Vertex must have a value during initialization, the value could be comparable
 *     or non-comparable. Currently in this version, the Vertex is non-comparable to itself.
 * </p>
 * @author Bach Tran
 * @since 1.0
 * @param <T> any type, could be comparable or non-comparable.
 */
public class Vertex<T> implements Connectable {
    protected T value;
    /** The set of edges that this vertex has. Currently initialized
     * to be an empty HashSet.*/
    protected Set<BinaryConnection> connections = new HashSet<>();
    public Vertex(T value) {
        this.value = value;
    }
    @Override
    public boolean addConnection(BinaryConnection connection) {
        return connections.add(connection);
    }
    @Override
    public boolean removeConnection(BinaryConnection connection) { return connections.remove(connection);
    }

    public T getValue() {
        return value;
    }

    /**
     * Modifies the value of this vertex.
     * @param value the new value to be changed.
     */
    public void setValue(T value) {
        assert value != null;
        this.value = value;
    }

    public Set<BinaryConnection> getConnections() {
        return connections;
    }

    /**
     * Connects this vertex with another vertex, by creating
     * a new, weightless edge.
     * <b>Note:</b> the edge created will be added to both
     * this vertex, and the other vertex it connects to.
     * @param other the other vertex to be connected to this vertex.
     */
    public void connect(Vertex other) {
        BinaryConnection connection = new Edge(this, other);
        this.addConnection(connection);
        other.addConnection(connection);
    }

    /**
     * Disconnects the specified vertex with this vertex.
     * @param other The specified vertex to be disconnected from this vertex.
     */
    public void disconnect(Vertex other) {
        connections.forEach(connection -> {
           if (connection.getOpposite(this) == other) { removeConnection(connection); }
        });
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
