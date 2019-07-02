package org.grasple.api.structures.trees;

import org.grasple.api.particles.NumberedConnectable;
import org.grasple.api.particles.NumberedVertex;

import java.util.*;
import java.util.function.Consumer;

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
public class BinarySearchTree<T extends Comparable<T>>
        implements Iterable<NumberedConnectable<T>> {

    protected static final int LEFT = 0;
    protected static final int MIDDLE = 1;
    protected static final int RIGHT = 2;

    private NumberedConnectable<T> root;

    NumberedConnectable<T> getRoot() {
        return this.root;
    }

    /**
     * Creates a Binary Search Tree with a root.
     *
     * @param root the connectable object used as a root
     *             of the tree.
     */
    public BinarySearchTree(NumberedConnectable<T> root) {
        this.root = root;
    }

    /**
     * Creates a new Binary Search Tree with a starting value.
     * This value will be used to create a new numbered vertex,
     * which then be used as a root of the tree.
     *
     * @param value the value of the root of the tree.
     */
    public BinarySearchTree(T value) {
        this.root = new NumberedVertex<>(value);
    }


    /**
     * Adds a new value to the binary tree. A new numbered vertex will be
     * created, containing this new value.
     *
     * @param value the new value to be added to the tree.
     */
    public void add(T value) {
        recursiveAdd(root, new NumberedVertex<>(value));
    }

    public void add(NumberedConnectable<T> vertexToAdd) {
        recursiveAdd(root, vertexToAdd);
    }

    @SafeVarargs
    public final void add(T... values) {
        for (T val : values) this.add(val);
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
     * Deletes all vertices in this tree that
     * have the specified value. After this operation,
     * the the value and all of its duplicates will
     * not be found in the tree.
     *
     * @param value the value to be deleted.
     */
    public void delete(T value) {

    }


    /**
     * Checks if the tree contain exactly the specified object
     * referring to identical memory address.
     *
     * @param element the element to be checked for containment.
     * @return true if the tree contains the element.
     */
    public boolean contains(NumberedConnectable<T> element) {
        return recursiveContains(root, element);
    }

    /**
     * Checks if the tree contain any value like the one specified.
     * If there exist a vertex having the value specified, this
     * operation returns true.
     *
     * @param value the value to be checked for containment.
     * @return true if there exists a vertex having the specified
     * value in this tree.
     */
    public boolean contains(T value) {
        return recursiveContains(root, value);
    }

    private boolean recursiveContains(NumberedConnectable<T> root,
                                      NumberedConnectable<T> element) {
        // We have found the exact object in this tree.
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

    private boolean recursiveContains(NumberedConnectable<T> root, T value) {
        if (root.get().compareTo(value) == 0) {
            return true;
        }

        if (value.compareTo(root.get()) < 0 && root.occupied(LEFT)) {
            return recursiveContains(root.jumpTo(LEFT), value);
        } else if (value.compareTo(root.get()) > 0 && root.occupied(RIGHT)) {
            return recursiveContains(root.jumpTo(RIGHT), value);
        }

        return false;
    }

    /**
     * Finds the vertex with the exact value specified in this BST.
     * Note that the method returns the top vertex, which directly
     * attaches to this BST, from this vertex. One may use this
     * vertex to traverse to subsequent duplicates.
     *
     * @param value the value to be find.
     * @return an Optional object wrapping the result.
     */
    public Optional<NumberedConnectable<T>> find(T value) {
        return _find(root, value);
    }

    private Optional<NumberedConnectable<T>> _find(NumberedConnectable<T> root,
                                                   T value) {
        if (value.compareTo(root.get()) == 0) {
            return Optional.of(root);
        }

        if (value.compareTo(root.get()) < 0 && root.occupied(LEFT)) {
            return _find(root.jumpTo(LEFT), value);
        } else if (value.compareTo(root.get()) > 0 && root.occupied(RIGHT)) {
            return _find(root.jumpTo(RIGHT), value);
        }
        return Optional.empty();
    }

    /**
     * Searches for all vertices that have the specified value
     * in the binary tree and returns them as a Set. If an
     * empty Set is returned, there is no such value.
     * This method utilizes the binary search algorithm.
     *
     * @param value the value to be searched for.
     * @return a Set of all vertices containing the specified value,
     * or an empty Set if there is no such value.
     */
    public List<NumberedConnectable<T>> findAll(T value) {
        List<NumberedConnectable<T>> results = new ArrayList<>();
        recursiveFindAll(this.root, results, value);
        return results;
    }

    private void recursiveFindAll(NumberedConnectable<T> root,
                                  List<NumberedConnectable<T>> results,
                                  T value) {

        if (value.compareTo(root.get()) == 0) {
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

    /**
     * Performs an in-order traversal on the binary tree.
     * The algorithm reaches the left vertex, the root vertex, then
     * the right vertex. Each visit, it applies the action specified
     * to the vertex.
     *
     * @param action the action to be specified.
     */
    public void traverse(Consumer<T> action) {
        recursiveInorderTraversal(root, action, true);
    }

    /**
     * Performs a traversal on the binary tree with a specified order,
     * and applies the specified action on the inner data of each
     * visited vertex.
     * When finished, the traversal visits all vertices, including
     * every duplicated value in the tree.
     *
     * @param order  the order of the traversal, can be in-order,
     *               pre-order, or post-order.
     * @param action the action applied to the VALUE of each vertex
     *               this traversal visits.
     */
    public void traverse(TreeTraversalOrder order,
                         Consumer<T> action) {
        switch (order) {
            case PREORDER:
                recursivePreorderTraversal(root, action, true);
                break;
            case INORDER:
                recursiveInorderTraversal(root, action, true);
                break;
            case POSTORDER:
                recursivePostorderTraversal(root, action, true);
                break;
        }
    }

    private void traverse(TreeTraversalOrder order,
                          Consumer<T> action,
                          boolean allowDuplicates) {
        switch (order) {
            case PREORDER:
                recursivePreorderTraversal(root, action, allowDuplicates);
                break;
            case INORDER:
                recursiveInorderTraversal(root, action, allowDuplicates);
                break;
            case POSTORDER:
                recursivePostorderTraversal(root, action, allowDuplicates);
                break;
        }
    }

    private void recursiveInorderTraversal(NumberedConnectable<T> root,
                                           Consumer<T> action, boolean allowDuplicates) {

        if (root.occupied(LEFT)) {
            recursiveInorderTraversal(root.jumpTo(LEFT), action, allowDuplicates);
        }

        action.accept(root.get());

        if (allowDuplicates && root.occupied(MIDDLE)) {
            recursiveInorderTraversal(root.jumpTo(MIDDLE), action, allowDuplicates);
        }

        if (root.occupied(RIGHT)) {
            recursiveInorderTraversal(root.jumpTo(RIGHT), action, allowDuplicates);
        }
    }

    private void recursivePreorderTraversal(NumberedConnectable<T> root,
                                            Consumer<T> action, boolean allowDuplicates) {
        action.accept(root.get());

        if (allowDuplicates && root.occupied(MIDDLE)) {
            recursivePreorderTraversal(root.jumpTo(MIDDLE), action, allowDuplicates);
        }

        if (root.occupied(LEFT)) {
            recursivePreorderTraversal(root.jumpTo(LEFT), action, allowDuplicates);
        }
        if (root.occupied(RIGHT)) {
            recursivePreorderTraversal(root.jumpTo(RIGHT), action, allowDuplicates);
        }
    }

    private void recursivePostorderTraversal(NumberedConnectable<T> root,
                                             Consumer<T> action, boolean allowDuplicates) {
        if (root.occupied(LEFT)) {
            recursivePostorderTraversal(root.jumpTo(LEFT), action, allowDuplicates);
        }

        if (root.occupied(RIGHT)) {
            recursivePostorderTraversal(root.jumpTo(RIGHT), action, allowDuplicates);
        }

        action.accept(root.get());

        if (allowDuplicates && root.occupied(MIDDLE)) {
            recursivePostorderTraversal(root.jumpTo(MIDDLE), action, allowDuplicates);
        }
    }

    //----------------------------------- VERTEX TRAVERSAL ----------------------------------//


    private List<NumberedConnectable<T>> _inorderTraversal(NumberedConnectable<T> root) {

        List<NumberedConnectable<T>> list = new ArrayList<>();

        if (root.occupied(LEFT)) {
            list.addAll(_inorderTraversal(root.jumpTo(LEFT)));
        }

        list.add(root);

        if (root.occupied(RIGHT)) {
            list.addAll(_inorderTraversal(root.jumpTo(RIGHT)));
        }

        return list;
    }

    @Override
    public Iterator<NumberedConnectable<T>> iterator() {
        return _inorderTraversal(root).iterator();
    }

    private Optional<NumberedConnectable<T>> inorderSuccessor(NumberedConnectable<T> current) {
        // TODO strengthen this assertion
        assert _inorderTraversal(root).contains(current);

        List<NumberedConnectable<T>> inorderList = _inorderTraversal(root);


        // TODO make it safer
        try {
            return Optional.of(inorderList.get(inorderList.indexOf(current) + 1));
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return Optional.empty();
        }
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
    protected static int height(NumberedConnectable root) {
        int left_height = 1, right_height = 1;

        if (root.occupied(LEFT)) {
            left_height += height(root.jumpTo(LEFT));
        }

        if (root.occupied(RIGHT)) {
            right_height += height(root.jumpTo(RIGHT));
        }

        return Math.max(left_height, right_height);
    }


}
