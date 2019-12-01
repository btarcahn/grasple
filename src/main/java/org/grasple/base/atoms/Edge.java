package org.grasple.base.atoms;

public class Edge<T extends Vertex> implements Relation<T> {
    private T first;
    private T second;

    @Override
    public T fst() {
        return first;
    }

    @Override
    public T snd() {
        return second;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
