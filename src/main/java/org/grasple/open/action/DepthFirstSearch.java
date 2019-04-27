package org.grasple.open.action;

import org.grasple.open.entity.Edge;
import org.grasple.open.entity.Vertex;

public class DepthFirstSearch implements Runnable{
    private Vertex start;

    public DepthFirstSearch(Vertex start) {
        this.start = start;
    }
    public void setStart(Vertex start) {
        this.start = start;
    }
    public void traverse(Vertex current) {
        System.out.println(current);
        for (Edge edge : start.getEdges()) {
            traverse(edge.oppositeVertex(current));
        }
    }
    @Override
    public void run() { traverse(start); }
}
