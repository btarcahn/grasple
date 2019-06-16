package org.grasple.api;

import org.grasple.api.particles.Vertex;

import java.util.ArrayList;
import java.util.List;

/**
 * Constants for unit testing
 * @author Bach Tran
 */
public class TestUtils {
    // Integer constants
    private static final int MAXIMUM_NUMBER_OF_NEIGHBORS = 10;

    /** A Vertex of type String having the value: STRING_VERTEX */
    public static final Vertex<String> STRING_VERTEX = new Vertex<>("STRING_VERTEX");
    /** A Vertex of type Integer having the value: 0*/
    public static final Vertex<Integer> INTEGER_VERTEX = new Vertex<>(0);
    /** A Vertex of type String that has 10 neighbors */
    public static final Vertex<String> CENTRAL_VERTEX = new Vertex<>("CENTRAL_VERTEX");
    /** All neighbor vertices that <b>DIRECTLY</b> connects with the CENTRAL_VERTEX */
    public static final List<Vertex<String>> PRIMARY_NEIGHBORS = new ArrayList<>();
    /** All neighbor vertices that <b>DOES NOT DIRECTLY</b> connect with the CENTRAL_VERTEX */
    public static final List<Vertex<String>> SECONDARY_NEIGHBORS = new ArrayList<>();

    static {
        // Establish a CENTRAL_VERTEX with 10 PRIMARY_NEIGHBORS
        for (int i = 0; i < MAXIMUM_NUMBER_OF_NEIGHBORS; i++) {
            PRIMARY_NEIGHBORS.add(new Vertex<>("PRIMARY_NEIGHBOR_" + i));
        }
        PRIMARY_NEIGHBORS.forEach(CENTRAL_VERTEX::connect);
        // Each PRIMARY_NEIGHBOR_i has i SECONDARY_NEIGHBOR(S)
        for (int i = 0; i < MAXIMUM_NUMBER_OF_NEIGHBORS; i++) {
            for (int j = 0; j < i; j++) {
                Vertex<String> secondary_neighbor =
                        new Vertex<>("SECONDARY_NEIGHBOR_" + i + "->" + j);
                SECONDARY_NEIGHBORS.add(secondary_neighbor);
                PRIMARY_NEIGHBORS.get(i).connect(secondary_neighbor);
            }
        }
    }
}
