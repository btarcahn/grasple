package org.grasple.api.particles;

import java.util.Collection;

/**
 * Shared properties of objects that can <b>connect</b> to others
 * of the same class. One connectable object strictly has a
 * distinct binary connection, i.e. each connectable object can
 * only establish <b><u>one</u></b> connection to another connectable.
 * object.
 * @param <T> the data-type that the Connectable contains.
 * @see Connection
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
     * Modifies the value of this object.
     * @param value the new value of this object.
     */
    void set(T value);

    /**
     * Checks if these to objects are already connected (adjacent).
     * @param other the other connectable object to be checked for adjacency
     * @return true if these two objects are already connected.
     */
    boolean adjacent(Connectable<T> other);

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

    /**
     * Finds all connectable objects that is adjacent to this object.
     * This operation thereby drops the information on the connections
     * held by this object.
     * @return a Set contains all neighbors of this Connectable object.
     */
    Collection<Connectable<T>> getNeighbors();
}
