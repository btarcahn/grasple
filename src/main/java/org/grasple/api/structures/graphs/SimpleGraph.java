package org.grasple.api.structures.graphs;

import org.grasple.api.particles.*;
import org.grasple.api.utils.DefaultTraverser;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * A wrapper class of all vertices and edges to fasten traversals and ease
 * the issues of disconnected components. All edges in a SimpleGraph are
 * undirected. Its vertices can be either connected or disconnected.
 * @see Vertex
 * @see Edge
 * @since 1.0
 * @author Bach Tran
 */
public class SimpleGraph<T> implements GraphStructure<T> {
    private Set<Connectable<T>> vertices;
    private Set<Connection> edges;

    /**
     * Creates a simple graph with at least one vertex.
     * The graph created will have an empty HashSet of edges.
     * @param vertex the only vertex to be created with this simple graph.
     */
    public SimpleGraph(Vertex<T> vertex) {
        vertices = new HashSet<>();
        vertices.add(vertex);
        edges = new HashSet<>();
    }

    /**
     * Creates a simple graph with a Set of vertices.
     * The graph created will have an empty HashSet of edges.
     * @param vertices the Set of vertices for this simple graph.
     */
    public SimpleGraph(Set<Connectable<T>> vertices) {
        this.vertices = vertices;
        edges = new HashSet<>();
    }

    /**
     * Creates a simple graph with a Set of vertices and edges.
     * This complies with a graph condition: G(V, E).
     * @param vertices the Set of vertices.
     * @param edges the Set of edges.
     */
    public SimpleGraph(Set<Connectable<T>> vertices, Set<Connection> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    /**
     * Adds a new connectable object to this simple graph.
     * For convention, we call the connectable object a "vertex".
     * @param vertex the new vertex to be added.
     * @return true if the vertex hasn't been added to this graph.
     */
    public boolean addVertex(Connectable<T> vertex) { return vertices.add(vertex); }

    /**
     * Removes the connectable object from this simple graph.
     * For convention, we call the connectable object a "vertex".
     * @param vertex the vertex to be removed.
     * @return true if the specified vertex exists in the collection of vertices in this graph.
     */
    public boolean removeVertex(Connectable<T> vertex) { return vertices.remove(vertex); }

    /**
     * Adds a new binary connection to this simple graph.
     * @param connection the connection to be added.
     * @return true if the connection hasn't been added to this graph.
     */
    public boolean addEdge(Connection connection) { return edges.add(connection); }

    /**
     * Removes the binary connection from this simple graph.
     * @param connection the connection to be removed.
     * @return true if the specified connection exists in this simple graph.
     */
    public boolean removeEdge(Connection connection) { return edges.remove(connection); }

    /**
     * @return the Set of all Vertices in this simple graph.
     */
    public Set<Connectable<T>> getVertices() {
        return vertices;
    }

    /**
     * @return the Set of all edges in this simple graph.
     */
    public Set<Connection> getEdges() {
        return edges;
    }

    /**
     * <p>Exclusive to this class only.</p>
     * Finds connected components in the graph using a depth-first
     * traversal algorithm. This operation returns a set that contains
     * vertices belong to different connected components.
     * @return a collection of vertices represent its connected components.
     */
    public Set<Connectable<T>> separateComponents() {
        Set<Connectable<T>> _visited = new HashSet<>();
        Set<Connectable<T>> _separations = new HashSet<>();
        vertices.forEach(candidate -> {
            if (!_visited.contains(candidate)) {
                DefaultTraverser<T> traverser = new DefaultTraverser<>(candidate);
                traverser.run();
                _visited.addAll(traverser.getVisited());
                _separations.add(candidate);
            }
        });
        return _separations;
    }

    @Override
    public boolean addConnection(Connectable<T> connectable, UConnection<Connectable<T>> connection) {
        return false;
    }


    @Override
    public boolean removeConnection(Connectable<T> connectable, UConnection<Connectable<T>> connection) {
        return false;
    }

    @Override
    public void traverse() {
    }

    @Override
    public void traverse(Consumer<T> action) {

    }
}

