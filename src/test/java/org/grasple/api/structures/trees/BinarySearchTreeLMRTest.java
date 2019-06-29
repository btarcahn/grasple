package org.grasple.api.structures.trees;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeLMRTest {

    private static final int TEST_RANGE = 100;
    private static final Random random = new Random();
    private static BinarySearchTreeLMR<Integer> sampleTree = new BinarySearchTreeLMR<>(random.nextInt(TEST_RANGE));
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
    void traverse() {
        List<Integer> _results = new ArrayList<>();
        // INORDER test
        sampleTree.traverse(TreeTraversalOrder.INORDER, _results::add);
        assertEquals(TEST_RANGE + 1, _results.size());
        assertTrue(isSorted(_results, Comparator.naturalOrder()));
        _results.clear();

        // PREORDER test
        sampleTree.traverse(TreeTraversalOrder.PREORDER, _results::add);
        assertEquals(TEST_RANGE + 1, _results.size());
        _results.clear();

        // POSTORDER test
        sampleTree.traverse(TreeTraversalOrder.POSTORDER, _results::add);
        assertEquals(TEST_RANGE + 1, _results.size());
        _results.clear();

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

    @Test
    void height() {
        BinarySearchTreeLMR<Integer> _tree = new BinarySearchTreeLMR<>(0);
        for (int i = 1; i <= TEST_RANGE; i++) {
            _tree.add(i);
        }
        assertEquals(TEST_RANGE + 1, _tree.height());
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