package org.grasple.api.structures.trees;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeLMRTest {

    private static final int TEST_RANGE = 100;
    private static final Random random = new Random();
    private static BinaryTreeLMR<Integer> sampleTree = new BinaryTreeLMR<>(random.nextInt(TEST_RANGE));
    private static final Integer[] sampleInts = new Integer[TEST_RANGE];
    static {
        for (int i = 0; i < TEST_RANGE; i++) {
            sampleInts[i] = random.nextInt(TEST_RANGE);
            sampleTree.add(sampleInts[i]);
        }
    }

    @Test
    void basicContainment() {
        for (int i = 0; i < TEST_RANGE; i++) {
            assertTrue(sampleTree.findAll(sampleInts[i]).size() >= 1);
        }
    }

    @Test
    void inorderTraversal() {
        List<Integer> _list = new ArrayList<>();
        sampleTree.inorderTraversal(_list::add);
        assertTrue(isSorted(_list));
    }

    @Test
    void add() {
    }

    @Test
    void contains() {
    }

    private static <T extends Comparable<T>> boolean isSorted(List<T> list) {
        // TODO implement here
        return true;
    }
}