package org.grasple.open.entity;

public class Edge {
    protected int weight = 0;
    protected Vertex start;
    protected Vertex end;
    public Edge(Vertex start, Vertex end) {
        this.start = start;
        this.end = end;
        this.weight = 0;
    }
    public Edge(int weight, Vertex start, Vertex end) {
        this.weight = weight;
        this.start = start;
        this.end = end;
    }
    public boolean isCycle() { return start == end; }
    public Vertex oppositeVertex(Vertex vertex) {
        assert vertex == start || vertex == end;
        return vertex == start? end : start;
    }
}
