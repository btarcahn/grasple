package org.grasple.api.particles;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * Represents a Vertex in graph theory. Objects of this class are
 * non-comparable; therefore, this object is conventionally named
 * <b>unordered-</b>vertex. An unordered-vertex has a Set of
 * neighbors. Therefore will be randomized during traversals.
 * </p>
 * <p></p>
 * @see OrderedVertex
 * @author Bach Tran
 * @since 1.0
 * @param <T> type that will not be considered of its comparability.
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
        return connections
                .stream()
                .map(connection -> connection.divert(this))
                .collect(Collectors.toSet());
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public boolean adjacent(Connectable<T> other) {
        return getNeighbors().contains(other);
    }

    public T getValue() {
        return value;
    }

    /**
     * Gets all BinaryConnection of this Vertex.
     * @return a Set of all BinaryConnections associated with this Vertex
     */
    @Override
    public Set<BinaryConnection> getConnections() {
        return connections;
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
    @Override
    public BinaryConnection connect(Connectable<T> other) {
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
     * @param other
     */
    public void disconnect(Connectable<T> other) {
        this.getConnections().removeIf(connection -> connection.divert(this) == other);
        other.getConnections().removeIf(connection -> connection.divert(other) == this);
    }
}
