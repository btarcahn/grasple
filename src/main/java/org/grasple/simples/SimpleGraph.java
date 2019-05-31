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

    private Set<Vertex> visited = new HashSet<>();

    public SimpleGraph(Vertex vertex) {
        vertices = new HashSet<>();
        vertices.add(vertex);
        edges = new HashSet<>();
    }

    public SimpleGraph(Set<Vertex> vertices, Set<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public boolean addVertex(Vertex vertex) {
        return vertices.add(vertex);
    }

    public boolean removeVertex(Vertex vertex) {
        return vertices.remove(vertex);
    }

    public boolean addEdge(Edge edge) {
        return edges.add(edge);
    }

    public boolean removeEdge(Edge edge) {
        return edges.remove(edge);
    }

    /**
     * Read in V and E data, classify all disconnected components.
     */
    public void classify() {
        // TODO finish this after completed a traversal method.

    }

    private void traverse(Vertex root, Consumer action) {
        if (visited.contains(root)) { return; }
        action.accept(root);
        root.getNeighbors().forEach(neighbor -> {
            assert neighbor instanceof Vertex;
            traverse((Vertex) neighbor, action);
        });
    }
}

/**
 * @deprecated currently under development
 * @author Bach Tran
 */
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
        recursion(stack.pop());
    }
}
