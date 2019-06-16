package org.grasple.api.structures;

import org.grasple.api.particles.Edge;
import org.grasple.api.particles.Vertex;

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
public class SimpleGraph implements GraphStructure {
    /** All existing vertices in the graph. The V component in G(V,E). */
    private Set<Vertex> vertices;
    /** All existing edges in the graph. The E component in G(V,E). */
    private Set<Edge> edges;

    /**
     * Creates a SimpleGraph with just a Vertex. This is a minimum
     * requirement for a SimpleGraph to be existed.
     * @param vertex the only Vertex of the graph
     */
    public SimpleGraph(Vertex vertex) {
        vertices = new HashSet<>();
        vertices.add(vertex);
        edges = new HashSet<>();
    }

    /**
     * Creates a SimpleGraph knowing a Set of Vertices and a Set of Edges.
     * @param vertices the Set of Vertices
     * @param edges the Set of Edges
     */
    public SimpleGraph(Set<Vertex> vertices, Set<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    /**
     * Adds a new Vertex to this SimpleGraph.
     * @param vertex the Vertex to be added
     * @return true if the Vertex has not been added to this graph
     */
    public boolean addVertex(Vertex vertex) {
        return vertices.add(vertex);
    }

    /**
     * Removes the specified Vertex from this SimpleGraph.
     * @param vertex the specified Vertex to be removed.
     * @return true if the specified Vertex has existed in this SimpleGraph.
     */
    public boolean removeVertex(Vertex vertex) {
        return vertices.remove(vertex);
    }

    /**
     * Adds a new Edge to this SimpleGraph.
     * @param edge the Edge to be added
     * @return true if the new Edge has not been added to this graph
     */
    public boolean addEdge(Edge edge) {
        return edges.add(edge);
    }

    /**
     * Removes the specified Edge from this SimpleGraph.
     * @param edge the specified Edge to be removed.
     * @return true if the specified Edge has existed in this SimpleGraph.
     */
    public boolean removeEdge(Edge edge) {
        return edges.remove(edge);
    }

    /**
     * Finds all disconnected components in this SimpleGraph. This action
     * helps save time in reading graphs. This method utilizes the
     * depth-first-search (traversal) algorithm provided by the
     * DepthFirstTraverser class.
     * @return a Set of Vertices that are fully disconnected.
     * @see DepthFirstTraverser
     */
    public Set<Vertex> findDisconnectedComponents() {
        if (vertices.isEmpty()) { return vertices; }
        Set<Vertex> disconnects = new HashSet<>();
        Set<Vertex> visited = new HashSet<>();
        vertices.forEach(vertex -> {
            if (!visited.contains(vertex)) {
                DepthFirstTraverser traverser = new DepthFirstTraverser(vertex);
                traverser.run();
                visited.addAll(traverser.getVisited());
                disconnects.add(vertex);
            }

        });
        return disconnects;
    }

    @Override
    public void traverse() {
    }

    @Override
    public void traverse(Consumer action) {
    }
}

