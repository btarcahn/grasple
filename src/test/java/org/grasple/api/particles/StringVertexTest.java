package org.grasple.api.particles;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StringVertexTest {
    static String STARK = "Tony Stark";
    static String CAP = "Steve Rogers";
    static String NAT = "Natasha Romanov";
    static String FURY = "Nick Fury";
    static String HULK = "Bruce Banner";
    Set<Vertex<String>> avengers = new HashSet<>();
    Vertex<String> stark, cap, nat, fury, hulk;
    @BeforeEach
    void setUp() {
        stark = new Vertex<>(STARK);
        cap = new Vertex<>(CAP);
        nat = new Vertex<>(NAT);
        fury = new Vertex<>(FURY);
        hulk = new Vertex<>(HULK);
        avengers.add(stark);
        avengers.add(cap);
        avengers.add(nat);
        avengers.add(fury);
        avengers.add(hulk);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addConnection() {
        // stark is the central Avenger, every other Avengers surround him
        stark.addConnection(new Edge(stark, nat));
        stark.addConnection(new Edge(stark, hulk));
        stark.addConnection(new Edge(stark, fury));
        stark.addConnection(new Edge(stark, cap));
    }

    @Test
    void removeConnection() {
    }

    @Test
    void getValue() {
        assertEquals(STARK, stark.get());
        assertEquals(HULK, hulk.get());
        assertEquals(NAT, nat.get());
        assertEquals(CAP, cap.get());
        assertEquals(FURY, fury.get());
    }

    @Test
    void setValue() {
        stark.setValue("Anthony Stark");
        assertNotEquals("Tony Stark", stark.get());
        assertEquals("Anthony Stark", stark.get());
    }

    @Test
    void setNullValue() {
        assertThrows(IllegalArgumentException.class, () -> stark.setValue(null));
    }
    @Test
    void getConnections() {
        addConnection();
        assertEquals(4, stark.getConnections().size());
    }

    @Test
    void getNeighbors() {
        addConnection();
        stark.addConnection(new Edge(stark, stark));
        assertEquals(5, stark.getNeighbors().size());
        assertTrue(stark.getNeighbors().containsAll(avengers));
    }

    @Test
    void connect() {
        Set<Vertex<String>> friends = new HashSet<>(avengers);
        friends.remove(stark);
        friends.forEach(friend -> stark.connect(friend));
        assertTrue(stark.getNeighbors().containsAll(friends));
        friends.forEach(friend -> assertTrue(friend.getNeighbors().contains(stark)));
    }


    @Test
    void disconnect() {
        connect();
        stark.disconnect(hulk);
        stark.disconnect(nat);
        stark.disconnect(cap);
        assertFalse(stark.getNeighbors().contains(hulk));
        assertFalse(stark.getNeighbors().contains(cap));
        assertFalse(hulk.getNeighbors().contains(stark));
        assertFalse(cap.getNeighbors().contains(stark));
    }
}