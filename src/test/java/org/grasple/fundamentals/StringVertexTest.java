package org.grasple.fundamentals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
        stark = new Vertex(STARK);
        cap = new Vertex(CAP);
        nat = new Vertex(NAT);
        fury = new Vertex(FURY);
        hulk = new Vertex(HULK);
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
        assertEquals(STARK, stark.getValue());
        assertEquals(HULK, hulk.getValue());
        assertEquals(NAT, nat.getValue());
        assertEquals(CAP, cap.getValue());
        assertEquals(FURY, fury.getValue());
    }

    @Test
    void setValue() {
        stark.setValue("Anthony Stark");
        assertNotEquals("Tony Stark", stark.getValue());
        assertEquals("Anthony Stark", stark.getValue());
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

    @Disabled("method deprecated")
    void connect() {

    }

    @Disabled("method deprecated")
    void disconnect() {
    }
}