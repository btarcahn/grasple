package org.grasple.api.particles;

import java.util.HashMap;
import java.util.Map;

public class NumberedVertex<T extends Comparable<T>>
        implements Comparable<NumberedVertex<T>> {

    private T value;
    private Map<Integer, NumberedVertex<T>> neighbors;

    public NumberedVertex(T value) {
        this.value = value;
        this.neighbors = new HashMap<>();
    }

    public boolean connect(Integer number, NumberedVertex<T> other) {

        if (neighbors.containsValue(other)) {
            return false;
        }

        neighbors.put(number, other);
        return true;
    }

    public boolean disconnect(Integer number) {

        if (neighbors.containsKey(number)) {
            neighbors.remove(number);
            return true;
        }

        return false;
    }

    public boolean occupied(Integer number) {
        return neighbors.containsKey(number);
    }

    public NumberedVertex<T> jumpTo(Integer number) {
        return neighbors.get(number);
    }

    @Override
    public int compareTo(NumberedVertex<T> o) {
        return this.value.compareTo(o.value);
    }
}
