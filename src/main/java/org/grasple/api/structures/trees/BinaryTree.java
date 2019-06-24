package org.grasple.api.structures.trees;

import org.grasple.api.particles.NumberedVertex;

public class BinaryTree<T extends Comparable<T>> {

    private static final int LEFT = 0;
    private static final int MIDDLE = 1;
    private static final int RIGHT = 2;

    private NumberedVertex<T> root;

    public BinaryTree(NumberedVertex<T> root) {
        this.root = root;
    }

    public void add(NumberedVertex<T> vertex) {
        addTo(root, vertex);
    }

    private void addTo(NumberedVertex<T> root, NumberedVertex<T> vertexToAdd) {
        if (root.compareTo(vertexToAdd) < 0) {
            if (root.occupied(LEFT)) {
                addTo(root.jumpTo(LEFT), vertexToAdd);
            } else {
                root.connect(LEFT, vertexToAdd);
            }
        } else if (root.compareTo(vertexToAdd) > 0) {
            if (root.occupied(RIGHT)) {
                addTo(root.jumpTo(RIGHT), vertexToAdd);
            } else {
                root.connect(RIGHT, vertexToAdd);
            }
        } else {
            if (root.occupied(MIDDLE)) {
                addTo(root.jumpTo(MIDDLE), vertexToAdd);
            } else {
                root.connect(MIDDLE, vertexToAdd);
            }
        }
    }
}
