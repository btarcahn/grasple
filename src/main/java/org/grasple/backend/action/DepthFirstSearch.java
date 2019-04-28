package org.grasple.backend.action;

import org.grasple.backend.entity.Vertex;

/**
 * <p align="justify">This class encapsulates the basic Depth First Search
 * algorithm on a graph. It needs a concrete starting vertex object to
 * begin the procedure. The class be accessed through the Runnable interface.
 * </p>
 * @author Bach Tran
 * @since 1.0
 */
public class DepthFirstSearch implements Runnable{
    /** The starting vertex of the procedure. */
    private Vertex start;

    /**
     * Construct a depth first search procedural object, given the
     * starting vertex. The procedure will start with the given vertex.
     * @Contract start != null
     * @param start the starting vertex of the procedure.
     */
    public DepthFirstSearch(Vertex start) {
        assert start != null;
        this.start = start;
    }

    /**
     * Modifies the starting vertex with a new starting vertex.
     * @Contract start != null
     * @param start the new starting vertex.
     */
    public void setStart(Vertex start) {
        assert start != null;
        this.start = start;
    }
    public void traverse(Vertex current) {
        System.out.println(current);
        start.getEdges().forEach(System.out::println);
    }
    @Override
    public void run() { traverse(start); }
}
