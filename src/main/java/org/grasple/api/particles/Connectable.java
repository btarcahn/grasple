package org.grasple.api.particles;

import java.util.Collection;

/**
 * Shared properties of objects that can <b>connect</b> to others
 * of the same class. One connectable object strictly has a
 * distinct binary connection, i.e. each connectable object can
 * only establish <b><u>one</u></b> connection to another connectable.
 * object.
 * @param <T> the data-type that the Connectable contains.
 * @since 1.0
 * @author Bach Tran
 */
public interface Connectable<T> extends Node<T> {

    /**
     * Connects this object to the other of the same type.
     * @param other other connectable
     * @return true if the connection has been established.
     */
    boolean connect(Connectable<T> other);

    /**
     * Disconnects these two objects.
     * @param other the other object to be disconnected.
     */
    boolean disconnect(Connectable<T> other);

    @Override
    Collection<Connectable<T>> getNeighbors();
}
