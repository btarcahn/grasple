package org.grasple.api.structures.trees;

import org.grasple.api.particles.Allocatable;

/**
 * Self-balanced Binary Search Tree, also known as AVL.
 * @author Bach Tran
 * @since 1.0
 * @param <T> must be Comparable
 */
public class BinarySearchTreeAVL<T extends Comparable<T>> extends BinarySearchTree<T> {

    public BinarySearchTreeAVL(T value) {
        super(value);
    }

    @Override
    public void add(T value) {
        // TODO implementation while respecting AVL
        super.add(value);
    }

    public boolean AVLCondition() {
        return heightDiff(getRoot()) <= 1;
    }


    private static int heightDiff(Allocatable root) {
            return height(root.jumpTo(LEFT)) - height(root.jumpTo(RIGHT));
    }
}
