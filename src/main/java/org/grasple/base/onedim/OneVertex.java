package org.grasple.base.onedim;


import org.grasple.base.atoms.Vertex;

import java.util.Optional;


public class OneVertex<E> extends Vertex<E>
            implements OneNode<E> {

    private OneNode<E> nextNode;

    public OneVertex(E value) {
        super(value);
        nextNode = null;
    }

    @Override
    public boolean attach(OneNode<E> node) {
        // avoid self-attach, for now
        if (this == node) {
            return false;
        }
        nextNode = node;
        return true;
    }

    @Override
    public Optional<OneNode<E>> next() {
        return Optional.ofNullable(nextNode);
    }
}
