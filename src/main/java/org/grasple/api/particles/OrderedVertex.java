package org.grasple.api.particles;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>An ordered-vertex can only wraps a <b>comparable</b> datatype.
 * Unlike an unordered-vertex, which uses a Set to contain its
 * neighbors and connections, the ordered-vertex uses a List achieve
 * sequentially iterations. The ordered-vertex is very useful in
 * building structures that utilizes comparison, such as a binary tree.</p>
 * <p>Although the collection of connections is implemented as a List,
 * the uniqueness property in the list is still respected.</p>
 * @see Vertex
 * @param <T> a type that must be Comparable.
 * @author Bach Tran
 */
public class OrderedVertex<T extends Comparable<T>>
                                implements Comparable<OrderedVertex<T>>, Connectable<T> {
    private T value;
    private List<Connection> connections;
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
    public boolean adjacent(Connectable<T> other) {
        return getNeighbors().contains(other);
    }

    @Override
    public List<Connection> getConnections() {
        return connections;
    }

    @Override
    public boolean addConnection(Connection connection) {
        if (!connections.contains(connection)) {
            return connections.add(connection);
        }
        return false;
    }

    @Override
    public boolean removeConnection(Connection connection) {
        if (connections.contains(connection)) {
            return connections.remove(connection);
        }
        return false;
    }

    @Override
    public Connection connect(Connectable<T> other) {
        Connection connection = new Edge(this, other);
        this.addConnection(connection);
        if (this != other) { other.addConnection(connection); }
        return connection;
    }

    @Override
    public void disconnect(Connectable<T> other) {
        this.getConnections().removeIf(connection -> connection.divert(this) == other);
        other.getConnections().removeIf(neighbor -> neighbor.divert(other) == this);
    }

    @Override
    public List<Connectable> getNeighbors() {
        return connections.stream()
                .distinct()
                .map(connection -> connection.divert(this))
                .collect(Collectors.toList());
    }

    /**
     * <p>Only available at this class.</p>
     * Uses the i-th connection of this ordered vertex, diverts it,
     * and gets the corresponding member. The result is <b>not generified</b>,
     * so user should be cautious of type-safety.
     * @param index the index of the connection.
     * @return the neighbor on the other side of the i-th connection.
     * @throws ArrayIndexOutOfBoundsException if the supplied index is larger than
     * the number of neighbors.
     */
    public Connectable getNeighbor(int index)
            throws ArrayIndexOutOfBoundsException {
        return connections.get(index).divert(this);
    }
}