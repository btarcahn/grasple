package org.grasple.base.onedim;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class OneTree<T> implements List<T> {

    private OneVertex<T> start = null;

    // TODO complete these methods to fulfill List<T>
    @Override
    public int size() {
        if (start == null) {
            return 0;
        }

        int count = 1;
        OneNode<T> current = start;
        while (current.next() != null) {
            count++;
            current = current.next();
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return (start == null);
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
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
    public boolean add(T t) {
        // if initially the list is empty
        if (start == null) {
            start = new OneVertex<>(t);
            return true;
        }
        // if the list is not empty, traverse
        // to the end of list, with O(n) cost.
        OneNode<T> current = start;
        while (current.next() != null) {
            current = current.next();
        }
        current = current.next();
        current = new OneVertex<>(t);
        return true;
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
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
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
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
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
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
