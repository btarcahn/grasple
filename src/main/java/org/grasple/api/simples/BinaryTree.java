package org.grasple.api.simples;

import org.grasple.api.fundamentals.Vertex;

public class BinaryTree<T> extends ConnectedGraph<T> {

    private Vertex<T> root;

    public BinaryTree(Vertex<T> root) {
        super(root);
    }
}
