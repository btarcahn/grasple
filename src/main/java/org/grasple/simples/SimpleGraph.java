package org.grasple.simples;

import java.util.HashSet;
import java.util.Set;

import org.grasple.fundamentals.*;

/**
 * Represents a Simple Graph. Consults the definition of a Simple Graph
 * in Graph Theory (Discrete Mathematics for information.
 * A graph should have <b>AT LEAST</b> one vertex to be created.
 * @param <T> the datatype that the whole graph represents.
 * @author Bach Tran
 */
public class SimpleGraph<T> {
    private Set<Vertex<T>> vertices;
    private Set<Edge> edges;

    public SimpleGraph(Vertex<T> vertex) {
        vertices = new HashSet<>();
        vertices.add(vertex);
        edges = new HashSet<>();
    }

    public SimpleGraph(Set<Vertex<T>> vertices, Set<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public void addVertex(Vertex<T> vertex) {
        vertices.add(vertex);
    }

    public void removeVertex(Vertex<T> vertex) {
        vertices.remove(vertex);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public void removeEdge(Edge edge) {
        edges.remove(edge);
    }

    
}
