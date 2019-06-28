package org.grasple.api.particles;

import java.util.Objects;

/**
 * Represents an <b>undirected</b> edge, which is a pair
 * (u, v) of vertices. Since this Edge is undirected,
 * (u, v) is equal to (v, u).
 * An edge has two endpoints (start, end), which in this
 * case are interchangeable.
 * @author Bach Tran
 * @since 1.0
 */
public class Edge<T extends Node<T>> implements UConnection<T> {
    private int weight;
    private T start;
    private T end;

    /**
     * Creates a zero-weight, undirected edge.
     * @param start the starting vertex that the edge connects to.
     * @param end the ending vertex that the edge connects to.
     */
    public Edge(T start, T end) {
        weight = 0;
        this.start = start;
        this.end = end;
    }

    /**
     * Creates an undirected edge with weight.
     * @param weight the weight of the edge
     * @param start the starting endpoint of the edge
     * @param end the ending endpoint of the edge
     */
    public Edge(int weight, T start, T end) {
        this.weight = weight;
        this.start = start;
        this.end = end;
    }

    /**
     * @return the weight of the edge.
     */
    @Override
    public int weight() {
        return weight;
    }

    /**
     * Modifies the weight of the edge with a new value.
     * @param weight the new weight to be modified
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * @return one of the endpoints of the edge.
     */
    public T getStart() {
        return start;
    }

    /**
     * @return one of the endpoints of the edge.
     */
    public T getEnd() {
        return end;
    }

    @Override
    public void setStart(T start) {
        this.start = start;
    }

    @Override
    public void setEnd(T end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return (start.equals(edge.start) && end.equals(edge.end)) ||
                (start.equals(edge.end) && end.equals(edge.start));
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public T divert(T endpoint) {
        return endpoint == start ? end : start;
    }
}
