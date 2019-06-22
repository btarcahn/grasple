package org.grasple.api.particles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * This class uses a <b>primitive array</b> to contain all
 * connections to the connectable object. Therefore, it is
 * prone to out-of-bound errors, though there are built-in
 * mechanisms to stop that from happening. With the use of
 * a primitive array, objects of this class have better
 * performance during internal iterations.
 * @author Bach Tran
 * @since 1.0
 * @param <T> a datatype that must be comparable.
 */
public class IndexableVertex<T extends Comparable<T>>
        implements Connectable<T>, Comparable<IndexableVertex<T>> {
    private static final short DEFAULT_LEN = 256;
    private T value;
    private BinaryConnection[] connections;

    /**
     * Constructs an index-able vertex with a value.
     * The maximum number of neighbors that this vertex
     * can hold is 256.
     * @param value the value of the vertex.
     */
    public IndexableVertex(T value) {
        this.value = value;
        this.connections = new BinaryConnection[DEFAULT_LEN];
    }

    /**
     * Creates an index-able vertex with a value, and
     * an array of binary connections it holds.
     * @param value the value of the vertex.
     * @param connections the array of binary connections.
     */
    public IndexableVertex(T value, BinaryConnection[] connections) {
        this.value = value;
        this.connections = connections;
    }

    /**
     * Creates an index-able vertex with a value, a maximum
     * number of neighbors this vertex can hold. This number
     * cannot be larger than the biggest integer in Java.
     * @param value the value of the vertex.
     * @param neighbors the maximum number of neighbors that
     *                  this vertex can hold.
     */
    public IndexableVertex(T value, int neighbors) {
        this.value = value;
        this.connections = new BinaryConnection[neighbors];
    }

    /**
     * <p>An index-able vertex is saturated if the array of neighbors has
     * no null spaces. This means all available space has been used up.</p>
     * <p>This method checks whether this index-able vertex is saturated.</p>
     * @return true if this vertex is saturated.
     */
    public boolean isSaturated() {
        for (BinaryConnection connection : connections) {
            if (connection == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int compareTo(IndexableVertex<T> o) {
        return value.compareTo(o.value);
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public boolean adjacent(Connectable<T> other) {
        for (BinaryConnection connection : connections) {
            if (connection.divert(this) == other) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Collection<BinaryConnection> getConnections() {
        List<BinaryConnection> _connections = new ArrayList<>();
        Collections.addAll(_connections, connections);
        return _connections;
    }

    @Override
    public boolean addConnection(BinaryConnection connection) {
        // create extra memory to avoid saturation
        if (this.isSaturated()) {
            BinaryConnection[] _extended
                    = new BinaryConnection[connections.length + DEFAULT_LEN];
            System.arraycopy(connections, 0,
                    _extended, 0, connections.length);
            connections = _extended;
        }
        // add to the right-most available space
        for (int i = connections.length - 1; i >=0; i--) {
            if (connections[i] != null) {
                assert i + 1 < connections.length;
                connections[i + 1] = connection;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeConnection(BinaryConnection connection) {
        for (int i = 0; i < connections.length; i++) {
            if (connections[i] == connection) {
                connections[i] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public BinaryConnection connect(Connectable<T> other) {
        BinaryConnection _connection = new Edge(this, other);
        addConnection(_connection);
        return _connection;
    }

    @Override
    public void disconnect(Connectable<T> other) {
        for (int i = 0; i < connections.length; i++) {
            if (connections[i].divert(this) == other) {
                connections[i] = null;
                return;
            }
        }
    }

    @Override
    public Collection<Connectable> getNeighbors() {
        List<Connectable> _neighbors = new ArrayList<>();
        for (BinaryConnection connection : connections) {
            _neighbors.add(connection.divert(this));
        }
        return _neighbors;
    }
}
