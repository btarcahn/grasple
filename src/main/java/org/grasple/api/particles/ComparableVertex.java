package org.grasple.api.particles;

/**
 * A vertex that can do comparison.
 * @param <T> a comparable type.
 * @author Bach Tran
 */
public class ComparableVertex<T extends Comparable<T>>
        extends Vertex<T>
        implements Comparable<ComparableVertex<T>> {
    /**
     * Creates a comparable vertex.
     * The value given must be comparable.
     * @param value the value of the vertex
     */
    public ComparableVertex(T value) {
        super(value);
    }

    @Override
    public int compareTo(ComparableVertex<T> o) {
        return this.get().compareTo(o.get());
    }
}
