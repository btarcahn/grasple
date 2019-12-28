package org.grasple.base.atoms;

public interface Node<T> {
    T extract();
    void set(T value);
}
