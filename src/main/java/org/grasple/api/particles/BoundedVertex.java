package org.grasple.api.particles;

import java.util.*;

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
public class BoundedVertex<T extends Comparable<T>>
        implements IndexedConnectable<T>, Comparable<BoundedVertex<T>> {
    private static final short DEFAULT_LEN = 256;
    private T value;
    private Connection[] connections;

    /**
     * Constructs an index-able vertex with a value.
     * The maximum number of neighbors that this vertex
     * can hold is 256.
     * @param value the value of the vertex.
     */
    public BoundedVertex(T value) {
        this.value = value;
        this.connections = new Connection[DEFAULT_LEN];
    }

    /**
     * Creates an index-able vertex with a value, and
     * an array of binary connections it holds.
     * @param value the value of the vertex.
     * @param connections the array of binary connections.
     */
    public BoundedVertex(T value, Connection[] connections) {
        this.value = value;
        this.connections = connections;
    }

    /**
     * Creates an index-able vertex with a value, a maximum
     * number of neighbors this vertex can hold. This number
     * cannot be larger than the biggest integer in Java. If
     * a negative integer is supplied, the method will create
     * an array of the default value (256 elements).
     * @param value the value of the vertex.
     * @param neighbors the maximum number of neighbors that
     *                  this vertex can hold.
     */
    public BoundedVertex(T value, int neighbors) {
        this.value = value;
        this.connections =
                new Connection[neighbors > 0 ? neighbors : DEFAULT_LEN];
    }

    @Override
    public boolean isSaturated() {
        for (Connection connection : connections) {
            if (connection == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int compareTo(BoundedVertex<T> o) {
        return value.compareTo(o.value);
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public boolean adjacent(Connectable<T> other) {
        for (Connection connection : connections) {
            if (connection.divert(this) == other) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Collection<Connection> getConnections() {
        List<Connection> _connections = new ArrayList<>();
        Collections.addAll(_connections, connections);
        return _connections;
    }

    /**
     * <p>Exclusive to this class.</p>
     * <p>This method adds the specified binary connection to the first available
     * space found in the primitive array. If no space is available, an extra
     * memory space of 256 elements will be automatically allocated to contain
     * the new connection.</p>
     * @param connection the connection to be added
     * @return true if the connection has been added to this index-able vertex.
     */
    @Override
    public boolean addConnection(Connection connection) {
        allocateExtraMemory();
        // add to the first (leftmost) available space
        for (int i = 0; i < connections.length; i++) {
            if (connections[i] == null) {
                assert i + 1 < connections.length;
                connections[i + 1] = connection;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addConnection(int index, Connection connection)
            throws ArrayIndexOutOfBoundsException, IndexSaturatedException {
        if (connections[index] != null) {
            throw new IndexSaturatedException();
        }
        connections[index] = connection;
        return true;
    }

    @Override
    public boolean removeConnection(int index) {
        if (connections[index] == null) {
            return false;
        }
        connections[index] = null;
        return true;
    }

    @Override
    public boolean removeConnection(Connection connection) {
        for (int i = 0; i < connections.length; i++) {
            if (connections[i] == connection) {
                connections[i] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public Connection connect(Connectable<T> other) {
        Connection _connection = new Edge(this, other);
        addConnection(_connection);
        return _connection;
    }

    @Override
    public boolean connect(int index, Connectable<T> other)
            throws ArrayIndexOutOfBoundsException, IndexSaturatedException {
        if (connections[index] != null) {
            throw new IndexSaturatedException();
        }
        Connection _connection = new Edge(this, other);
        addConnection(index, _connection);
        return true;
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
        for (Connection connection : connections) {
            _neighbors.add(connection.divert(this));
        }
        return _neighbors;
    }
    @Override
    public IndexedConnectable<T> getNeighbor(int index)
            throws ArrayIndexOutOfBoundsException, NullPointerException {
        if (connections[index] == null) {
            throw new NullPointerException("Item not available at index " +  index + ".");
        }
        return (IndexedConnectable<T>)
                connections[index].divert(this);
    }

    private void allocateExtraMemory() {
        // create extra memory to avoid saturation
        if (this.isSaturated()) {
            Connection[] _extended
                    = new Connection[connections.length + DEFAULT_LEN];
            System.arraycopy(connections, 0,
                    _extended, 0, connections.length);
            connections = _extended;
        }
    }
}

