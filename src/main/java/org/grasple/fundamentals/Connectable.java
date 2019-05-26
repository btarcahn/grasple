package org.grasple.fundamentals;

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
    public boolean addConnection(BinaryConnection connection);
    public boolean removeConnection(BinaryConnection connection);
}
