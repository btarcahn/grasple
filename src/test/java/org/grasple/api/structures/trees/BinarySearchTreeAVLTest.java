package org.grasple.api.structures.trees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeAVLTest {

    private static final Integer[] balancedIntegerSet = {50, 40, 60, 30, 45, 55, 70};
    private BinarySearchTreeAVL<Integer> balancedTree;

    @BeforeEach
    void setUp() {
        balancedTree = new BinarySearchTreeAVL<>(balancedIntegerSet[0]);
        for (int i = 1; i < balancedIntegerSet.length; i++) {
            balancedTree.add(balancedIntegerSet[i]);
        }
    }

    @Test
    void heightDiff() {

    }

    @Test
    void AVLCondition() {
        assertTrue(balancedTree.AVLCondition());
        balancedTree.add(1, 2);
        assertFalse(balancedTree.AVLCondition());
    }
}