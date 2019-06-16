package org.grasple.api.fundamentals;

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
public class Vertex<T> implements Connectable<T> {
    private T value;
    /** The set of edges that this vertex has. Currently initialized to be an empty HashSet.*/
    private Set<BinaryConnection> connections = new HashSet<>();

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
        return connections.add(connection);
    }

    /**
     * Removes a specified BinaryConnection from this Vertex. <br/>
     * <b>Warning:</b> this method uses the .remove() method of the Set interface, such methods
     * are not thread-safe.
     * @param connection the connection to be removed
     * @return true if the specified connection exists in the Vertex's list of connections.
     * @see Set
     */
    @Override
    public boolean removeConnection(BinaryConnection connection) {
        return connections.remove(connection);
    }
    @Override
    public Set<Connectable> getNeighbors() {
        Set<Connectable> neighbors = new HashSet<>();
        // if there exists a self-connection, then the neighbor of this Vertex can be itself.
        connections.forEach((connection) -> neighbors.add(connection.divert(this)));
        return neighbors;
    }

    /**
     * Gets all BinaryConnection of this Vertex.
     * @return a Set of all BinaryConnections associated with this Vertex
     */
    public Set<BinaryConnection> getConnections() {
        return connections;
    }
    /**
     * Gets the value that this Vertex contains.
     * Subsequent subclasses shall choose to remove a copy, or a clone of
     * the value to preserve encapsulation.
     * @return the value this Vertex contains.
     */
    public T getValue() {
        return value;
    }

    /**
     * Modifies the value of this vertex. The new value must not be null.
     * @param value the new, not-null value.
     */
    public void setValue(T value) {
        if (value == null) {
            throw new IllegalArgumentException("The value of this Vertex cannot be null.");
        }
        this.value = value;
    }

    /**
     * Connects this vertex with other vertex, by creating
     * a new, weightless edge. The other Vertex must be different
     * from this Vertex.
     * <b>Note:</b> the edge created will be added to both
     * this vertex, and the other vertex it connects to.
     * @param other the other vertex to be connected to this vertex.
     * @return an Edge created to connect these two Vertices.
     */
    public Edge connect(Vertex<T> other) {
        Edge connection = new Edge(this, other);
        this.addConnection(connection);
        if (this != other) { other.addConnection(connection); }
        return connection;
    }

    /**
     * Disconnects the specified Vertex with this Vertex.
     * All BinaryConnection between these two Vertices will be lost after the operation.
     * This method uses the removeIf() method from the Collection interface, which is
     * thread-safe.
     * @see java.util.Collection
     */
    public void disconnect(Vertex<T> other) {
        connections.removeIf(connection -> connection.divert(this) == other);
        other.connections.removeIf(connection -> connection.divert(this) == other);
    }
}
