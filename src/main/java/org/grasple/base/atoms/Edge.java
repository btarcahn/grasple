package org.grasple.base.atoms;

public class Edge<E extends Vertex> implements Relation<E> {
    private E first;
    private E second;

    @Override
    public E fst() {
        return first;
    }

    @Override
    public E snd() {
        return second;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
