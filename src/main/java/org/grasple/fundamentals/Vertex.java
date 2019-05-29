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
    private T value;
    /** The set of edges that this vertex has. Currently initialized to be an empty HashSet.*/
    private Set<BinaryConnection> connections = new HashSet<>();
    /** All vertices having connections with this vertex, excluding itself. */
    private Set<Vertex<T>> neighbors = new HashSet<>();

    /**
     * Creates a Vertex given only a not-null value.
     * @param value the value of the vertex
     */
    public Vertex(T value) {
        if (value == null) {
            throw new IllegalArgumentException("The value of this Vertex cannot be null.");
        }
        this.value = value;
    }
    @Override
    public boolean addConnection(BinaryConnection connection) {
        addNeighbor(connection);
        return connections.add(connection);
    }
    @Override
    public boolean removeConnection(BinaryConnection connection) {
        removeNeighbor(connection);
        return connections.remove(connection);
    }

    /**
     * Adds a new neighbor to this vertex given the connection. The connection will be
     * used to diverting to the other endpoint. A self-connection (which connects
     * a Vertex to itself) will be ignored.
     * The result of the .divert() method will be typecast to a Vertex.
     * @param connection the connection to be diverted.
     */
    private void addNeighbor(BinaryConnection connection) {
        if (connection.divert(this) == this) {
            return;
        }
        if (connection.divert(this) instanceof Vertex) {
            Vertex<T> toBeAdded = (Vertex) connection.divert(this);
            this.neighbors.add(toBeAdded);
        }
    }

    private void removeNeighbor(BinaryConnection connection) {
        this.neighbors.remove(connection.divert(this));
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
     * @deprecated unsafe, establish a connection first.
     */
    public void connect(Vertex other) {
        BinaryConnection connection = new Edge(this, other);
        this.addConnection(connection);
        other.addConnection(connection);
    }

    /**
     * Disconnects the specified vertex with this vertex. All connections
     * with the specified Vertex will be lost after this operation.
     * @param other The specified vertex to be disconnected from this vertex.
     * @deprecated unsafe, establish a connection first.
     */
    public void disconnect(Vertex other) {
        connections.forEach(connection -> {
           if (connection.divert(this) == other) { removeConnection(connection); }
        });
    }
}
