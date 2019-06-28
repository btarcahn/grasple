package org.grasple.api.structures.trees;

import org.grasple.api.particles.NumberedConnectable;
import org.grasple.api.particles.NumberedVertex;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Binary tree containing three branches: (left) "smaller than",
 * (right) "greater than", and (middle) "equals to". This type
 * of binary tree is an improved version of the classic binary
 * tree. Performing algorithms on this type of tree, in many cases,
 * are significantly faster.
 * @since 1.0
 * @param <T> Comparable data type.
 * @author Bach Tran
 */
public class BinarySearchTreeLMR<T extends Comparable<T>> {

    private static final int LEFT = 0;
    private static final int MIDDLE = 1;
    private static final int RIGHT = 2;

    private NumberedConnectable<T> root;

    public BinarySearchTreeLMR(NumberedConnectable<T> root) {
        this.root = root;
    }

    public BinarySearchTreeLMR(T value) {
        this.root = new NumberedVertex<>(value);
    }

    /**
     * Adds a new vertex to the tree using basic binary
     * tree adding procedure.
     * @param vertex the new vertex to be added.
     */
    public void add(NumberedConnectable<T> vertex) {
        recursiveAdd(root, vertex);
    }

    /**
     * Adds a new value to the binary tree. A new numbered vertex will be
     * created, containing this new value.
     * @param value the new value to be added to the tree.
     */
    public void add(T value) {
        recursiveAdd(root, new NumberedVertex<>(value));
    }

    private void recursiveAdd(NumberedConnectable<T> root, NumberedConnectable<T> vertexToAdd) {
        if (root.get().compareTo(vertexToAdd.get()) > 0) {
            if (root.occupied(LEFT)) {
                recursiveAdd(root.jumpTo(LEFT), vertexToAdd);
            } else {
                root.connect(LEFT, vertexToAdd);
            }
        } else if (root.get().compareTo(vertexToAdd.get()) < 0) {
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
    public boolean contains(NumberedConnectable<T> element) {
        recursiveContains(this.root, element);
        return false;
    }

    private boolean recursiveContains(NumberedConnectable<T> root,
                                      NumberedConnectable<T> element) {
        if (root == element) {
            return true;
        }

        if (element.get().compareTo(root.get()) < 0 && root.occupied(LEFT)) {
            return recursiveContains(root.jumpTo(LEFT), element);
        } else if (element.get().compareTo(root.get()) > 0 && root.occupied(RIGHT)) {
            return recursiveContains(root.jumpTo(RIGHT), element);
        } else if (root.occupied(MIDDLE)) {
            return recursiveContains(root.jumpTo(MIDDLE), element);
        }

        return false;
    }

    /**
     * Searches for all vertices that have the specified value
     * in the binary tree and returns them as a Set. If an
     * empty Set is returned, there is no such value.
     * This method utilizes the binary search algorithm.
     * @param value the value to be searched for.
     * @return a Set of all vertices containing the specified value,
     * or an empty Set if there is no such value.
     */
    public Set<NumberedConnectable<T>> findAll(T value) {
        Set<NumberedConnectable<T>> results = new HashSet<>();
        recursiveFindAll(this.root, results, value);
        return results;
    }

    private void recursiveFindAll(NumberedConnectable<T> root,
                                  Set<NumberedConnectable<T>> results,
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

    // TODO merge with graph traversals

    /**
     * Performing an in-order traversal on the binary tree.
     * The algorithm reaches the left vertex, the root vertex, then
     * the right vertex. Each visit, it applies the action specified
     * to the vertex.
     * @param action the action to be specified.
     */
    public void traverse(Consumer<T> action) {
        recursiveInorderTraversal(root, action);
    }

    public void traverse(TraversalOrder order, Consumer<T> action) {
        switch(order) {
            case PREORDER:
                recursivePreorderTraversal(root, action);
                break;
            case INORDER:
                recursiveInorderTraversal(root, action);
                break;
            case POSTORDER:
                recursivePostorderTraversal(root, action);
                break;
        }
    }

    private void recursiveInorderTraversal(NumberedConnectable<T> root, Consumer<T> action) {

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

    private void recursivePreorderTraversal(NumberedConnectable<T> root,
                                            Consumer<T> action) {
        action.accept(root.get());

        if (root.occupied(MIDDLE)) {
            recursivePreorderTraversal(root.jumpTo(MIDDLE), action);
        }

        if (root.occupied(LEFT)) {
            recursivePreorderTraversal(root.jumpTo(LEFT), action);
        }
        if (root.occupied(RIGHT)) {
            recursivePreorderTraversal(root.jumpTo(RIGHT), action);
        }
    }

    private void recursivePostorderTraversal(NumberedConnectable<T> root,
                                             Consumer<T> action) {
        if (root.occupied(LEFT)) {
            recursivePostorderTraversal(root.jumpTo(LEFT), action);
        }

        if (root.occupied(RIGHT)) {
            recursivePostorderTraversal(root.jumpTo(RIGHT), action);
        }

        action.accept(root.get());

        if (root.occupied(MIDDLE)) {
            recursivePostorderTraversal(root.jumpTo(MIDDLE), action);
        }
    }

    public void reconstruct() {
        // TODO implement binary tree maintenance
    }
}
