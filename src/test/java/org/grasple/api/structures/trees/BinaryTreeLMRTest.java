package org.grasple.api.structures.trees;
import org.grasple.api.particles.NumberedVertex;
import org.junit.jupiter.api.Test;

import java.util.*;

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
        assertTrue(isSorted(_list, Comparator.naturalOrder()));
    }

    @Test
    void add() {
    }

    @Test
    void contains() {

    }



    @Test
    void findAll() {
    }


    private static <T> boolean isSorted(List<T> list,
                                        Comparator<T> comparator) {
        if (list.isEmpty()) {
            return true;
        }

        Iterator<T> _iterator = list.iterator();
        T first = _iterator.next();
        while (_iterator.hasNext()) {
            T next = _iterator.next();
            if (comparator.compare(next, first) < 0) {
                return false;
            }
            first = next;
        }

        return true;
    }
}