package org.grasple.base.onedim;

import org.grasple.base.atoms.Node;

public interface OneNode<T> extends Node<T> {
    OneNode<T> next();
}
