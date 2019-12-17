package org.grasple.base.onedim;

import org.grasple.base.atoms.Node;

import java.util.Optional;

public interface OneNode<E> extends Node<E> {
    Optional<OneNode<E>> next();
    boolean attach(OneNode<E> node);
    boolean detach();
}
