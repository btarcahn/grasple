package org.grasple.api.particles;

import java.util.HashMap;
import java.util.Map;

/**
 * Vertex that can connect to others using a unique number.
 * @param <T> a Comparable type.
 * @author Bach Tran
 */
public class NumberedVertex<T extends Comparable<T>>
        implements Comparable<NumberedVertex<T>> {

    private T value;
    private Map<Integer, NumberedVertex<T>> neighbors;

    public NumberedVertex(T value) {
        this.value = value;
        this.neighbors = new HashMap<>();
    }

    public boolean connect(Integer index, NumberedVertex<T> other) {

        if (neighbors.containsValue(other)) {
            return false;
        }

        neighbors.put(index, other);
        return true;
    }

    public boolean disconnect(Integer index) {

        if (neighbors.containsKey(index)) {
            neighbors.remove(index);
            return true;
        }

        return false;
    }

    /**
     * Checks if there exists a neighbor at this index.
     * @param index the index to be checked.
     * @return true if there is a neighbor at this index.
     */
    public boolean occupied(Integer index) {
        return neighbors.containsKey(index);
    }

    public NumberedVertex<T> jumpTo(Integer index) {
        return neighbors.get(index);
    }

    @Override
    public int compareTo(NumberedVertex<T> o) {
        return this.value.compareTo(o.value);
    }
}
