package org.grasple.api.structures.trees;

import org.grasple.api.particles.BinaryVertex;

import java.util.*;

/**
 * An improved version of the 2-branch Binary
 * Search Tree. Vertices of this tree have 3
 * branches: left, middle, right. The left
 * and right branches still contain bigger
 * and smaller values, respectively, of the root,
 * just like a 2-branch BST, while the middle branch
 * contains values that equals to the root.
 * In many cases, this data structure reduces time
 * complexity, for its improvement in handling
 * duplicates.
 * @since 1.0
 * @param <T> Comparable data type.
 * @author Bach Tran
 */
public class BinarySearchTree<T extends Comparable<T>> {

    private BinaryVertex<T> root;

    public BinarySearchTree(T initialValue) {
        this.root = new BinaryVertex<>(initialValue);
    }

    public void add(T value) {
        this.root.connect(new BinaryVertex<>(value));
    }

    public void delete(T value) {
        // TODO implement delete logic

        List<BinaryVertex<T>> candidates = objSearch(value);
        
    }

    /**
     * Searches for all objects in this tree having
     * the specified value. The algorithm utilizes
     * binary search and starts from the root vertex.
     * @param value the value to be searched for
     * @return a set of all objects having the specified
     * value.
     */
    public List<BinaryVertex<T>> objSearch(T value) {
        return objSearch(root, value);
    }

    private List<BinaryVertex<T>> objSearch(BinaryVertex<T> start,
                                            T value) {
        List<BinaryVertex<T>> searchResults = new ArrayList<>();

        if (value.compareTo(start.get()) < 0 && start.leftOccupied()) {
            searchResults.addAll(objSearch(start.left(), value));
        } else if (value.compareTo(start.get()) > 0 && start.rightOccupied()) {
            searchResults.addAll(objSearch(start.right(), value));
        } else {
            // object found, add the head first then add subsequent objects
            searchResults.add(start);
            if (start.middleOccupied())
                searchResults.addAll(objSearch(start.middle(), value));
        }
        return searchResults;
    }

    /**
     * Finds the max-depth (or commonly known as the
     * tree's height). This operation ignores the
     * depth of the middle branch, namely the branch
     * containing duplicates.
     * @return the height of the tree.
     */
    public int height() {
        return height(root);
    }

    /**
     * Calculates the depth of the sub-tree.
     * @param root the root of the sub-tree.
     * @return the height respective to the root of the
     * sub-tree.
     */
    public static int height(BinaryVertex root) {
        int left_height = 1, right_height = 1;

        if (root.leftOccupied()) {
            left_height += height(root.left());
        }

        if (root.rightOccupied()) {
            right_height += height(root.right());
        }

        return Math.max(left_height, right_height);
    }


}
