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
public class Edge implements Connection {
    private int weight;
    private Connectable start;
    private Connectable end;

    /**
     * Creates a zero-weight, undirected edge.
     * @param start the starting vertex that the edge connects to.
     * @param end the ending vertex that the edge connects to.
     */
    public Edge(Connectable start, Connectable end) {
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
    public Edge(int weight, Connectable start, Connectable end) {
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
    public Connectable getStart() {
        return start;
    }

    /**
     * @return one of the endpoints of the edge.
     */
    public Connectable getEnd() {
        return end;
    }

    @Override
    public void setStart(Object start) {
        this.start = start;
    }

    @Override
    public void setEnd(Object end) {
        this.end = end;
    }

    @Override
    public Connectable divert(Connectable connectable) {
        if (start != connectable && end != connectable) {
            throw new IllegalArgumentException(("The Connectable object does not belong to this Edge."));
        }
        return connectable == start ? end : start;
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
}
