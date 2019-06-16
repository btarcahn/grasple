package org.grasple.api.particles;

import java.util.Set;

/**
 * An interface specifies how to a Connectable should be specified.
 * This could more or less is similar to the Subscriber in the Observer Design Pattern.
 * A connectable can add or remove a connection. A class of Connectable is Vertex.
 * @see BinaryConnection
 * @author Bach Tran
 */
public interface Connectable<T> {
    /**
     * Adds a new BinaryConnection to this Connectable.
     * @param connection the connection to be added
     * @return true if the method was successful, false otherwise.
     * @see BinaryConnection
     */
    boolean addConnection(BinaryConnection connection);

    /**
     * Removes the given BinaryConnection in this Connectable.
     * The operation will be ignored and still return a true even if
     * there does not exist such BinaryConnection in this Connectable.
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
     * Finds all adjacent Connectable objects and returns them
     * as a Set.
     * @return a Set contains all neighbors of this Connectable object.
     */
    Set<Connectable<T>> getNeighbors();
}
