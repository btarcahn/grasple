package org.grasple.api.simples;

import java.util.function.Consumer;

public interface GraphStructure {
    void traverse();
    void traverse(Consumer action);
}
