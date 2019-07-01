package org.grasple.api.structures.trees;
import org.grasple.api.particles.NumberedConnectable;
import org.grasple.api.particles.NumberedVertex;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.grasple.api.structures.trees.BinarySearchTreeLMR.MIDDLE;
import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeLMRTest {

    private static final int TEST_RANGE = 100;
    private static final Random random = new Random();
    private static BinarySearchTreeLMR<Integer> randomTree = new BinarySearchTreeLMR<>(random.nextInt(TEST_RANGE));
    private static final Integer[] randomInts = new Integer[TEST_RANGE];

    private static final Integer[] balancedIntegerSet = {50, 40, 60, 30, 45, 55, 70};

    static {
        for (int i = 0; i < TEST_RANGE; i++) {
            randomInts[i] = random.nextInt(TEST_RANGE);
            randomTree.add(randomInts[i]);
        }
    }

    @Test
    void basicContainment() {
        for (int i = 0; i < TEST_RANGE; i++) {
            assertTrue(randomTree.findAll(randomInts[i]).size() >= 1);
        }
    }

    @Test
    void traverse() {
        List<Integer> _results = new ArrayList<>();
        // INORDER test
        randomTree.traverse(TreeTraversalOrder.INORDER, _results::add);
        assertEquals(TEST_RANGE + 1, _results.size());
        assertTrue(isSorted(_results, Comparator.naturalOrder()));
        _results.clear();

        // PREORDER test
        randomTree.traverse(TreeTraversalOrder.PREORDER, _results::add);
        assertEquals(TEST_RANGE + 1, _results.size());
        _results.clear();

        // POSTORDER test
        randomTree.traverse(TreeTraversalOrder.POSTORDER, _results::add);
        assertEquals(TEST_RANGE + 1, _results.size());
        _results.clear();

    }

    @Test
    void add() {
        BinarySearchTreeLMR<Integer> _balancedTree = new BinarySearchTreeLMR<>(balancedIntegerSet[0]);
        for (int i = 1; i < balancedIntegerSet.length; i++) {
            _balancedTree.add(balancedIntegerSet[i]);
            assertTrue(_balancedTree.contains(balancedIntegerSet[i]));
        }
        assertEquals(3, _balancedTree.height());
    }

    @Test
    void handingDuplicates() {
        final String _SAMPLE_ = "SAMPLE";
        final NumberedConnectable<String> _SAMPLE_VERTEX_ = new NumberedVertex<>(_SAMPLE_);
        final BinarySearchTreeLMR<String> _SAMPLE_TREE_ = new BinarySearchTreeLMR<>(_SAMPLE_);
        // add 100 duplicated vertices
        for (int i = 0; i < TEST_RANGE; i++) {
            _SAMPLE_TREE_.add(_SAMPLE_);
            assertEquals(1, _SAMPLE_TREE_.height());
        }
        assertFalse(_SAMPLE_TREE_.contains(_SAMPLE_VERTEX_));
        _SAMPLE_TREE_.add(_SAMPLE_VERTEX_);
        // add the specfied vertex
        assertEquals(1, _SAMPLE_TREE_.height());
        assertTrue(_SAMPLE_TREE_.contains(_SAMPLE_VERTEX_));
    }

    @Test
    void findAll() {
        final int REPEATED_VAL = 50;
        BinarySearchTreeLMR<Integer> _tree = new BinarySearchTreeLMR<>(REPEATED_VAL);
        _tree.add(REPEATED_VAL - 1, REPEATED_VAL, REPEATED_VAL + 1);

        assertTrue(_tree.contains(REPEATED_VAL));
        assertEquals(2, _tree.findAll(REPEATED_VAL).size());
        for (int i = 0; i < TEST_RANGE; i++) {
            _tree.add(50);
        }
        assertEquals(TEST_RANGE + 2, _tree.findAll(REPEATED_VAL).size());

        // check sequence
        List<NumberedConnectable<Integer>> _duplicates = _tree.findAll(REPEATED_VAL);
        assertFalse(_duplicates.get(_duplicates.size() - 1).occupied(MIDDLE));
        for (int i = 0; i < _duplicates.size() -1; i++) {
            assertTrue(_duplicates.get(i).occupied(MIDDLE));
        }
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