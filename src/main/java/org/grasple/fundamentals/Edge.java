package org.grasple.fundamentals;

import java.util.Objects;

/**
 * <p align = "justify">In Graph Theory, an edge connects <b>TWO</b> concrete vertices.
 * This class reflects exactly the same notion. An edge holds
 * the address to to vertices (start, end). To create an edge,
 * one must have two concrete vertices ready.
 * </p>
 * <p>
 *     An edge can also have weight (encapsulated as an integer),
 *     or weightless (also means weight 0). It is allowed to create
 *     either a weight or a weightless edge.
 * </p>
 * <p>
 *     Equality: two edges are equal if they connect the two exact same Connectable objects.
 * </p>
 * @author Bach Tran
 * @since 1.0
 */
public class Edge implements BinaryConnection {
    private int weight;
    private Connectable start;
    private Connectable end;

    /**
     * Constructs a weightless edge. The weight of this edge
     * after construction will be 0.
     * @param start the starting vertex that the edge connects to.
     * @param end the ending vertex that the edge connects to.
     */
    public Edge(Connectable start, Connectable end) {
        weight = 0;
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs an edge with weight.
     * @param weight the weight of the edge
     * @param start the starting endpoint of the edge
     * @param end the ending endpoint of the edge
     */
    public Edge(int weight, Connectable start, Connectable end) {
        this.weight = weight;
        this.start = start;
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Connectable getStart() {
        return start;
    }

    public Connectable getEnd() {
        return end;
    }

    @Override
    public void setStart(Connectable start) {
        this.start = start;
    }

    @Override
    public void setEnd(Connectable end) {
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
