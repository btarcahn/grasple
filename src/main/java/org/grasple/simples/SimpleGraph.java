package org.grasple.simples;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.function.Consumer;

import org.grasple.fundamentals.*;

/**
 * Represents a Simple Graph. Consults the definition of a Simple Graph
 * in Graph Theory (Discrete Mathematics for information.
 * A graph should have <b>AT LEAST</b> one vertex to be created.
 * @author Bach Tran
 */
public class SimpleGraph {
    /** All existing vertices in the graph. The V component in G(V,E). */
    private Set<Vertex> vertices;
    /** All existing edges in the graph. The E component in G(V,E). */
    private Set<Edge> edges;
    /** All disconnected components, represented as a Vertex */
    private Set<Vertex> disconnectedComponents = new HashSet<>();

    public SimpleGraph(Vertex vertex) {
        vertices = new HashSet<>();
        vertices.add(vertex);
        edges = new HashSet<>();
    }

    public SimpleGraph(Set<Vertex> vertices, Set<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    public void removeVertex(Vertex vertex) {
        vertices.remove(vertex);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public void removeEdge(Edge edge) {
        edges.remove(edge);
    }

    /**
     * Read in V and E data, classify all disconnected components.
     */
    private void classify() {
        // TODO finish this after completed a traversal method.
    }
}

class Traverser implements Runnable {

    private Vertex start;
    private Stack<Vertex> stack = new Stack<>();
    private Set<Vertex> visited = new HashSet<>();
    public Traverser(Vertex start) { this.start = start; }

    @Override
    public void run() {
        recursion(start);
    }

    private void recursion(Vertex vertex) {
        if (visited.contains(vertex)) { return; }
        visited.add(vertex);
        stack.add(vertex);
        vertex.getNeighbors().forEach(neighbor -> {
            assert neighbor instanceof Vertex;
            recursion((Vertex) neighbor);
        });
        stack.pop();
    }
}
