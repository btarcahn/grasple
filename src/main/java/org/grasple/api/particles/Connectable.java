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
     * <b><u>Adjacency:</b></u> two connectable objects are adjacent
     * if there exists at least one connection within them, or one of them
     * exists in the collection of neighbors of the others.
     * @param other the other connectable object to be checked for adjacency
     * @return true if the two connectable objects are adjacent.
     */
    boolean adjacent(Connectable<T> other);

    /**
     * Connects to another connectable object using a
     * Connection.
     * @param other other connectable
     * @return the binary connection used to connect these two objects.
     */
    Connection connect(Connectable<T> other);

    void disconnect(Connectable<T> other);

    /**
     * Finds all connectable objects that is adjacent to this object.
     * This operation thereby drops the information on the connections
     * held by this object.
     * @return a Set contains all neighbors of this Connectable object.
     */
    Collection<Connectable<T>> getNeighbors();
}
