package org.grasple.open.entity;

import java.util.HashSet;
import java.util.Set;

public class Vertex {
    protected int value;
    protected Set<Edge> edges = new HashSet<>();
    public Vertex() {
        this.value = 0;
        edges = null;
    }
    public Vertex(int value) {
        this.value = value;
    }
    public boolean addEdge(Edge edge) {
        return edges.add(edge);
    }
    public boolean removeEdge(Edge edge) {
        return edges.remove(edge);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public void connect(Vertex other) {
        edges.add(new Edge(this, other));

    }
    public void connect(int weight, Vertex other) {
        edges.add(new Edge(weight, this, other));
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
