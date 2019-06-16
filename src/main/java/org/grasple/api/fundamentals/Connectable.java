package org.grasple.api.fundamentals;

import java.util.Set;

/**
 * <p>
 *     An interface specifies how to a Connectable should be specified.
 *     This could more or less is similar to the Subscriber in the Observer Design Pattern.
 *     A connectable can add or remove a connection.
 *     An class of Connectable is Vertex.
 * </p>
 * @see BinaryConnection
 * @author Bach Tran
 */
public interface Connectable {
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
     * Finds all adjacent Connectable objects and returns them
     * as a Set.
     * @return a Set contains all neighbors of this Connectable object.
     */
    Set<Connectable> getNeighbors();
}
