package org.grasple.simples;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import org.grasple.fundamentals.*;

/**
 * Represents a Simple Graph. Consults the definition of a Simple Graph
 * in Graph Theory (Discrete Mathematics for information.
 * A graph should have <b>AT LEAST</b> one vertex to be created.
 * @param <T> the datatype that the whole graph represents.
 * @author Bach Tran
 */
public class SimpleGraph<T> {
    protected Set<Vertex<T>> vertices;
    protected Set<Edge> edges;

    public SimpleGraph(Vertex<T> vertex) {
        vertices = new HashSet<>();
        vertices.add(vertex);
    }

    public SimpleGraph(Set<Vertex<T>> vertices, Set<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public void dfs(Vertex<T> startingVertex, Consumer<T> action) {
        //TODO finish this
    }

}
