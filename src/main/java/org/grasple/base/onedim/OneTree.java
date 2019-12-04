package org.grasple.base.onedim;

import java.util.*;

/**
 * Similar to ArrayList provided by Java.
 * @see java.util.ArrayList
 * @param <E>
 */
public class OneTree<E> implements List<E> {

    private OneNode<E> head;
    @Override
    public int size() {
        if (isEmpty()) {
            return 0;
        }
        int count = 0;
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
        for (OneNode<E> current = head;
             current.next().isPresent();
             current = current.next().get()) {
            if (current == o) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
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
        return tail.next().get().attach(new OneVertex<>(e));
    }

    @Override
    public boolean remove(Object o) {
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
        if (isEmpty() || size() <= index) {
            return null;
        }
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

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
}
