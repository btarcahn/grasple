package org.grasple.base.atoms;

public class Vertex<T> implements Node<T> {

    private T value;

    public Vertex(T value) {
        this.value = value;
    }

    @Override
    public T extract() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
