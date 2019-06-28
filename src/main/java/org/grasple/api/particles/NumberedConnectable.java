package org.grasple.api.particles;

import java.util.Collection;

public interface NumberedConnectable<T>
        extends Node<T> {

    boolean connect(Integer index, NumberedConnectable<T> other);

    boolean disconnect(Integer index);

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
     */
    NumberedConnectable<T> jumpTo(Integer index);

    Collection<NumberedConnectable<T>> getNeighbors();
}
