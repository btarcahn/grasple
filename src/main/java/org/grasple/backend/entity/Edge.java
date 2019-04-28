package org.grasple.backend.entity;

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
public class Edge {
    protected int weight;
    protected Vertex start;
    protected Vertex end;

    /**
     * Constructs a weightless edge. The weight of this edge
     * after construction will be 0.
     * @param start the starting vertex that the edge connects to.
     * @param end the ending vertex that the edge connects to.
     */
    public Edge(Vertex start, Vertex end) {
        this.start = start;
        this.end = end;
        this.weight = 0;
    }

    /**
     * Constructs an edge with a specific weight (encapsulated as
     * a primitive integer).
     * @param weight the weight of the edge.
     * @param start the starting vertex that the edge connects to.
     * @param end the ending vertex that the edge connects to.
     */
    public Edge(int weight, Vertex start, Vertex end) {
        this.weight = weight;
        this.start = start;
        this.end = end;
    }

    /**
     * An edge is determined to be cyclic if it directly has
     * both ends pointing to the same vertex.
     * Building such type of edge is discouraged in a circumstance
     * of a simple graph. However, this method might be useful
     * in some very specific situation.
     * @return true if this edge is cyclic.
     */
    public boolean isCycle() { return start == end; }

    /**
     * @Contract vertex ==start || vertex ||end
     * @param vertex the given edge.
     * @return the vertex standing on the opposite side of the given edge.
     */
    public Vertex oppositeVertex(Vertex vertex) {
        assert vertex == start || vertex == end;
        return vertex == start? end : start;
    }
}
