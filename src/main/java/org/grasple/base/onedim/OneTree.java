package org.grasple.base.onedim;

import java.util.*;

/**
 * A tree with one branch only, therefore,
 * it may be treated as a list.
 * Similar to ArrayList provided by Java.
 * Most of the operations in this class
 * cost O(n) time and space complexity.
 * @see java.util.ArrayList
 * @param <E> any generic type.
 * @author Bach Tran
 * @since 1.0
 */
public class OneTree<E> implements List<E> {

    /*
    NOTE: THE USE OF FOR LOOP
    The following for-loop will potentially IGNORE
    the last element of the OneTree:
    for (OneNode<E> current = head;
        current = current.next().isPresent();
        current.next().get()) {}
    Do consider workarounds with your own understanding.
     */

    // TODO an iterator may be helpful to get rid of for loops.

    private OneNode<E> head;

    /**
     * Creates an empty tree.
     * <b>Warning:</b> the head of the initialized
     * tree is null.
     */
    public OneTree() {
        head = null;
    }

    /**
     * Creates a pre-initialized tree with
     * a specific, instantiated head.
     * This is a prefer alternative to OneTree()
     * @param head the instantiated head.
     */
    public OneTree(OneNode<E> head) {
        this.head = head;
    }


    @Override
    public int size() {
        if (isEmpty()) {
            return 0;
        }
        int count = 1;
        for (OneNode<E> current = head;
             current.next().isPresent();
             current = current.next().get()) {
            count++;
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    @Override
    public boolean contains(Object o) {
        if (isEmpty()) {
            return false;
        }
        OneNode<E> current;
        for (current = head;
             current.next().isPresent();
             current = current.next().get()) {
            if (current.extract().equals(o)) {
                return true;
            }
        }
        return current.extract().equals(o);
    }

    @Override
    public Iterator<E> iterator() {
        // TODO investigate this operation
        throw new UnsupportedOperationException("Currently in development.");
    }

    @Override
    public Object[] toArray() {
        // calculate the size of the Array
        Object[] finalArray = new Object[size()];
        int i = 0;
        for (OneNode<E> current = head;
             current.next().isPresent();
             current = current.next().get()) {
            finalArray[i++] = current;
        }
        return finalArray;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        // TODO investigate this operation.
        throw new UnsupportedOperationException("Currently not supported");
    }

    @Override
    public boolean add(E e) {
        if (isEmpty()) {
            head = new OneVertex<>(e);
            return true;
        }
        OneNode<E> tail = head;
        while (tail.next().isPresent()) {
            tail = tail.next().get();
        }
        return tail.attach(new OneVertex<>(e));
    }

    @Override
    public boolean remove(Object o) {
        // Base case: for size smaller than 3.
        if (size() < 3) {
            if (head.extract().equals(o)) {
                head = head.next().orElse(null);
                return true;
            }
            assert head.next().isPresent();
            if (head.next().get().extract().equals(o)) {
                head.attach(null);
                return true;
            }
        }
        // For size greater than 3, iteratively.
        // Carefully initialize values
        assert head.next().isPresent();
        OneNode<E> previous = head,
                current = head.next().get();
        assert current.next().isPresent();
        OneNode<E> next = current.next().get();

        if (head.extract().equals(o)) {
            head = current;
            return true;
        }
        if (current.extract().equals(o)) {
            previous.attach(next);
            return true;
        }
        while (next.next().isPresent()) {
            // move forward
            previous = current;
            current = next;
            next = next.next().get();
            // If found, removing current by skipping it
            if (current.extract().equals(o)) {
                previous.attach(next);
                return true;
            }
        }
        if (next.extract().equals(o)) {
            current.attach(null);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        // drive towards the ith index
        OneNode<E> current = head;
        for (int i = 0; i < index; i++) {
            if (current.next().isPresent()) {
                current = current.next().get();
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        return current.extract();
    }

    @Override
    public E set(int index, E element) {
        OneNode<E> current = head;
        // find the appropriate element based on the
        // indicated index.
        for (int i = 0; i < index; i++) {
            if (current.next().isPresent()) {
                current = current.next().get();
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        // temporarily store the previous value
        E tempStorage = current.extract();
        current.set(element);
        return tempStorage;
    }

    @Override
    public void add(int index, E element) {
        // search for the appropriate location
        OneNode<E> prev = null,
                current = head;
        for (int i = 0; i < index; i++) {
            if (current.next().isPresent()) {
                prev = current;
                current = current.next().get();
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        }

        // perform addition
        OneNode<E> newNode = new OneVertex<>(element);
        if (prev == null) {
            newNode.attach(head);
            head = newNode;
        } else {
            prev.attach(newNode);
            newNode.attach(current);
        }
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString() {
        String result = "{";
        OneNode<E> current;
        for (current = head;
             current.next().isPresent() ;
             current = current.next().get()) {
            result += current.toString() + ", ";
        }
        result += current.toString() +  "}";
        return result;
    }
}
