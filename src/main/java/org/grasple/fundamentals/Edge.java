package org.grasple.fundamentals;

/**
 * <p align = "justify">In Graph Theory, an edge connects two concrete vertices.
 * This class reflects exactly the same notion. An edge holds
 * the address to to vertices (start, end). To create an edge,
 * one must have two concrete vertices ready.
 * </p>
 * <p>
 *     An edge can also have weight (encapsulated as an integer),
 *     or weightless (also means weight 0). It is allowed to create
 *     either a weight or a weightless edge.
 * </p>
 * @author Bach Tran
 * @since 1.0
 */
public class Edge implements BinaryConnection{
    protected int weight;
    protected Connectable start;
    protected Connectable end;

    /**
     * Constructs a weightless edge. The weight of this edge
     * after construction will be 0.
     * @param start the starting vertex that the edge connects to.
     * @param end the ending vertex that the edge connects to.
     */
    public Edge(Connectable start, Connectable end) {
        setStart(start);
        setEnd(end);
    }

    @Override
    public void setStart(Connectable connectable) {
        this.start = start;
    }

    @Override
    public void setEnd(Connectable connectable) {
        this.end = end;
    }


    @Override
    public boolean isSelfConnection() {
        return start == end;
    }

    /**
     * @Contract vertex ==start || vertex ||end
     * @param thisConnectable the given edge.
     * @return the vertex standing on the opposite side of the given edge.
     */
    @Override
    public Connectable getOpposite(Connectable thisConnectable)
            throws IllegalArgumentException {
        if (!(thisConnectable == start || thisConnectable == end)) {
            throw new IllegalArgumentException("The Connectable object does not exist in this edge.");
        }
        return thisConnectable == start ? end : start;
    }
}
