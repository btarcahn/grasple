package org.grasple.api.simples;

import org.grasple.api.fundamentals.Vertex;

import java.util.function.Consumer;

public class ConnectedGraph<T> implements GraphStructure {
    private Vertex<T> root;

    public ConnectedGraph(Vertex<T> root) {
        this.root = root;
    }

    public Vertex<T> getRoot() {
        return root;
    }

    public void setRoot(Vertex<T> root) {
        this.root = root;
    }

    @Override
    public void traverse() {

    }

    @Override
    public void traverse(Consumer action) {

    }
}
