package org.grasple.base.onedim;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OneTreeTest {

    @Test
    void size() {
        // simpleTestCase
        OneTree<Double> doubleTree =
                new OneTree<>();
        assertEquals(0, doubleTree.size());
        doubleTree.add(1.0);
        assertEquals(1, doubleTree.size());
        doubleTree.add(2.1);
        assertEquals(2, doubleTree.size());
        Double[] doubleArray = {2.0,3.0,4.0,-5.5};
        Collections.addAll(doubleTree, doubleArray);
        assertEquals(2 + doubleArray.length,
                doubleTree.size());
    }

    @Test
    void isEmpty() {
        OneTree<Float> testCandidate =
                new OneTree<>();
        assertTrue(testCandidate.isEmpty());
        testCandidate.add(1.0f);
        assertFalse(testCandidate.isEmpty());

    }

    @Test
    void contains() {
        List<String> fishes =
                new OneTree<>();
        for (int i = 1; i <= 100; i++) {
            fishes.add("fish #" + i);
        }
        assertEquals(fishes.size(), 100);
        assertTrue(fishes.contains("fish #1"));
        assertTrue(fishes.contains("fish #50"));
        assertTrue(fishes.contains("fish #100"));
        assertFalse(fishes.contains("fish #562"));
        assertFalse(fishes.contains("argh"));
        assertFalse(fishes.contains("hahaha"));
    }

    @Test
    void toArray() {
    }

    @Test
    void add() {
    }

    @Test
    void remove() {
        List<Double> ints =
                new OneTree<>();
        for (int i = 0; i < 50; i++) {
            ints.add((double) i);
        }
        assertEquals(50, ints.size());
        assertFalse(ints.remove(50.0));
        assertTrue(ints.remove(1.0));
        assertEquals(49, ints.size());
        assertTrue(ints.remove(0.0));
        assertEquals(48, ints.size());
        assertTrue(ints.remove(49.0));
        assertEquals(47, ints.size());
        assertFalse(ints.remove(49.0));
        assertEquals(47, ints.size());
    }

    @Test
    void get() {
    }

    @Test
    void set() {
    }
}

final class OneTreeTestUtils {
    static OneNode<Integer> zeroHead =
            new OneVertex<>(0);
    static OneTree<Integer> zeroTree =
            new OneTree<>();
}