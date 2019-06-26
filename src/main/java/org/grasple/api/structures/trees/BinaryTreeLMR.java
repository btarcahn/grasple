package org.grasple.api.structures.trees;

import org.grasple.api.particles.NumberedVertex;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Binary tree containing three branches: (left) "smaller than",
 * (right) "greater than", and (middle) "equals to".
 * @param <T> Comparable data type.
 * @author Bach Tran
 */
public class BinaryTreeLMR<T extends Comparable<T>> {

    private static final int LEFT = 0;
    private static final int MIDDLE = 1;
    private static final int RIGHT = 2;

    private NumberedVertex<T> root;

    public BinaryTreeLMR(NumberedVertex<T> root) {
        this.root = root;
    }

    public BinaryTreeLMR(T value) {
        this.root = new NumberedVertex<>(value);
    }

    /**
     * Adds a new vertex to the tree using basic binary
     * tree adding procedure.
     * @param vertex the new vertex to be added.
     */
    public void add(NumberedVertex<T> vertex) {
        recursiveAdd(root, vertex);
    }

    public void add(T value) {
        recursiveAdd(root, new NumberedVertex<>(value));
    }

    private void recursiveAdd(NumberedVertex<T> root, NumberedVertex<T> vertexToAdd) {
        if (root.compareTo(vertexToAdd) > 0) {
            if (root.occupied(LEFT)) {
                recursiveAdd(root.jumpTo(LEFT), vertexToAdd);
            } else {
                root.connect(LEFT, vertexToAdd);
            }
        } else if (root.compareTo(vertexToAdd) < 0) {
            if (root.occupied(RIGHT)) {
                recursiveAdd(root.jumpTo(RIGHT), vertexToAdd);
            } else {
                root.connect(RIGHT, vertexToAdd);
            }
        } else {
            if (root.occupied(MIDDLE)) {
                recursiveAdd(root.jumpTo(MIDDLE), vertexToAdd);
            } else {
                root.connect(MIDDLE, vertexToAdd);
            }
        }
    }

    /**
     * Checks if the tree contain exactly the specified element,
     * referring to identical memory address.
     * @param element the element to be checked for containment.
     * @return true if the tree contains the element.
     */
    public boolean contains(NumberedVertex<T> element) {
        recursiveContains(this.root, element);
        return false;
    }

    private boolean recursiveContains(NumberedVertex<T> root,
                                      NumberedVertex<T> element) {
        if (root == element) {
            return true;
        }

        if (element.compareTo(root) < 0 && root.occupied(LEFT)) {
            return recursiveContains(root.jumpTo(LEFT), element);
        } else if (element.compareTo(root) > 0 && root.occupied(RIGHT)) {
            return recursiveContains(root.jumpTo(RIGHT), element);
        } else if (root.occupied(MIDDLE)) {
            return recursiveContains(root.jumpTo(MIDDLE), element);
        }

        return false;
    }

    public Set<NumberedVertex<T>> findAll(T value) {
        Set<NumberedVertex<T>> results = new HashSet<>();
        recursiveFindAll(this.root, results, value);
        return results;
    }

    private void recursiveFindAll(NumberedVertex<T> root,
                                  Set<NumberedVertex<T>> results,
                                  T value) {

        if (value.compareTo(root.get()) == 0 ) {
            results.add(root);
            if (root.occupied(MIDDLE)) {
                recursiveFindAll(root.jumpTo(MIDDLE), results, value);
            } else {
                return;
            }
        }

        if (value.compareTo(root.get()) < 0 && root.occupied(LEFT)) {
            recursiveFindAll(root.jumpTo(LEFT), results, value);
        } else if (value.compareTo(root.get()) > 0 && root.occupied(RIGHT)) {
            recursiveFindAll(root.jumpTo(RIGHT), results, value);
        }
    }

    public void inorderTraversal(Consumer<T> action) {
        recursiveInorderTraversal(root, action);
    }

    private void recursiveInorderTraversal(NumberedVertex<T> root, Consumer<T> action) {

        if (root.occupied(LEFT)) {
            recursiveInorderTraversal(root.jumpTo(LEFT), action);
        }

        action.accept(root.get());

        if (root.occupied(MIDDLE)) {
            recursiveInorderTraversal(root.jumpTo(MIDDLE), action);
        }

        if (root.occupied(RIGHT)) {
            recursiveInorderTraversal(root.jumpTo(RIGHT), action);
        }
    }

    // TODO implement binary tree maintenance.
}
