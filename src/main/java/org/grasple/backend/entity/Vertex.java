package org.grasple.backend.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * <p align = "justify">
 *     Vertex is a basic element in Graph Theory. Here, a Vertex is a generic class,
 *     strongly associates with a generic entity for convenience. It is strongly
 *     recommended that the generic entity should be comparable.
 * </p>
 * @author Bach Tran
 * @since 1.0
 * @param <T> any class comparable
 */
public class Vertex<T extends Comparable<T>> {
    protected T value;
    protected Set<Edge> edges = new HashSet<>();
    public Vertex() { }
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

    public void setValue(T value) {
        this.value = value;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public void connect(Vertex other) {
        Edge edge = new Edge(this, other);
        this.edges.add(edge);
        other.edges.add(edge);
    }
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
