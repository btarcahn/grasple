package org.grasple.fundamentals;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p align = "justify">
 *     Vertex is a basic element in Graph Theory. Here, a Vertex is a generic class,
 *     strongly associates with a generic entity for convenience.
 *     The Vertex must have a value during initialization, and that value should
 *     be Comparable, to make other procedures on the graph become meaningful.
 * </p>
 * @author Bach Tran
 * @since 1.0
 * @param <T> any class that is Comparable
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
     * @Contract value != null;
     * @param value the new value to be changed.
     */
    public void setValue(T value) {
        assert value != null;
        this.value = value;
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

    public void disconnect(Vertex other) {
        connections =
                connections.stream()
                        .filter((connection) -> connection.getOpposite(this) != other)
                        .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
