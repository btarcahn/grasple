package org.grasple.api.particles;

import java.util.Collection;

/**
 * Shared properties of objects that can <b>connect</b> to others
 * of the same class. One connectable object strictly has a
 * distinct binary connection, i.e. each connectable object can
 * only establish <b><u>one</u></b> connection to another connectable.
 * object.
 * @see BinaryConnection
 * @since 1.0
 * @author Bach Tran
 */
public interface Connectable<T> {
    /**
     * Unwraps the value that the object contains.
     * @return the value that the object contains.
     */
    T get();

    /**
     * <b><u>Adjacency:</b></u> two connectable objects are adjacent
     * if there exists at least one connection within them, or one of them
     * exists in the collection of neighbors of the others.
     * @param other the other connectable object to be checked for adjacency
     * @return true if the two connectable objects are adjacent.
     */
    boolean adjacent(Connectable<T> other);

    /**
     * Retrieves all connections associated with this
     * connectable object.
     * @return a Collection of all binary connections.
     */
    Collection<BinaryConnection> getConnections();

    /**
     * Adds a new BinaryConnection to this Connectable.
     * @param connection the connection to be added
     * @return true if the method was successful, false otherwise.
     * @see BinaryConnection
     */
    boolean addConnection(BinaryConnection connection);

    /**
     * Removes the given BinaryConnection in this Connectable.
     * @param connection the connection to be removed
     * @return true if the method was successful, false otherwise.
     */
    boolean removeConnection(BinaryConnection connection);

    /**
     * Connects to another connectable object using a
     * BinaryConnection.
     * @param other other connectable
     * @return the binary connection used to connect these two objects.
     */
    BinaryConnection connect(Connectable<T> other);

    void disconnect(Connectable<T> other);

    /**
     * Finds all connectable objects that is adjacent to this object.
     * This operation thereby drops the information on the connections
     * held by this object.
     * @return a Set contains all neighbors of this Connectable object.
     */
    Collection<Connectable> getNeighbors();
}
