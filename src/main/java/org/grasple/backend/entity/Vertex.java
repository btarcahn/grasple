package org.grasple.backend.entity;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * <p align = "justify">
 *     Vertex is a basic element in Graph Theory. Here, a Vertex is a generic class,
 *     strongly associates with a generic entity for convenience.
 *     The Vertex must have a value during initialization, and that value should
 *     be Comparable, to make other procedures on the graph become meaningful.
 * </p>
 * @author Bach Tran
 * @since 1.0
 * @param <T> any class that is Comparable
 */
public class Vertex<T extends Comparable<T>> {
    protected T value;
    /** The set of edges that this vertex has. Currently initialized
     * to be an empty HashSet.*/
    protected Set<Edge> edges = new HashSet<>();
    public Vertex(T value) {
        this.value = value;
    }
    public boolean addEdge(Edge edge) {
        return edges.add(edge);
    }
    public boolean removeEdge(Edge edge) {
        return edges.remove(edge);
    }

    public T getValue() {
        return value;
    }

    /**
     * Modifies the value of this vertex.
     * @Contract value != null;
     * @param value the new value to be changed.
     */
    public void setValue(T value) {
        assert value != null;
        this.value = value;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public static Comparator<Vertex> valueComparator = Comparator.comparing(o -> o.value);

    /**
     * Connects this vertex with another vertex, by creating
     * a new, weightless edge.
     * <b>Note:</b> the edge created will be added to both
     * this vertex, and the other vertex it connects to.
     * @param other the other vertex to be connected to this vertex.
     */
    public void connect(Vertex other) {
        Edge edge = new Edge(this, other);
        this.edges.add(edge);
        other.edges.add(edge);
    }

    /**
     * Connects this vertex with another vertex, by creating
     * a new edge having a specific weight.
     * <b>Note:</b> the edge created will be added to both
     * this vertex, and the other vertex it connects to.
     * @param weight weight of the edge.
     * @param other the other vertex to be connected to this vertex.
     */
    public void connect(int weight, Vertex other) {
        Edge edge = new Edge(weight, this, other);
        this.edges.add(edge);
        other.edges.add(edge);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
