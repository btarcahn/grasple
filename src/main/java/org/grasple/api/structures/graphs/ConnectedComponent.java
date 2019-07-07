package org.grasple.api.structures.graphs;

import org.grasple.api.particles.Node;

public interface ConnectedComponent<T> {
    Node<T> set();
    Node<T> get();
}
