package org.grasple.api.particles;

import java.util.Collection;

/**
 * Connectable object which can assign a number to
 * its neighbors. This number is called an <b>index</b>,
 * and is used as an identification of the neighbor.
 * @param <T> data-type that the connectable wraps.
 * @since 1.0
 * @author Bach Tran
 */
public interface Allocatable<T> extends Node<T> {
    /**
     * Adds another connectable object to become a
     * neighbor of this object, and assigns the
     * new neighbor with an index. If there already
     * exists a neighbor at the specified index, the
     * connection is not be made and the method
     * returns false.
     * Immediate neighbor (i.e. a connectable object
     * connecting to itself) is not allowed.
     * @param index the index assigned to the new neighbor.
     * @param other another vertex to become the new neighbor.
     * @return true if the operation is successful.
     * @throws IllegalArgumentException if user attempts to
     * allocate the object to itself.
     */
    boolean allocate(Integer index, Allocatable<T> other);

    boolean deallocate(Integer index);

    boolean disconnect(Allocatable<T> other);

    /**
     * Checks if there exists a neighbor at this index.
     * @param index the index to be checked.
     * @return true if there is a neighbor at this index.
     */
    boolean occupied(Integer index);

    /**
     * Jumps to the neighbor that has the specified index.
     * @param index the index of the neighbor.
     * @return the neighbor object that has the specified index.
     * @throws IllegalArgumentException if no vertex is available at the specified index.
     */
    Allocatable<T> jumpTo(Integer index)
            throws IllegalArgumentException;

    @Override
    Collection<Allocatable<T>> next();
}
