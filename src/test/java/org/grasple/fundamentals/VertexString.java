package org.grasple.fundamentals;

import org.junit.Test;
import static org.junit.Assert.*;

public class VertexString {
    public static final String CONSTANT_SAMPLE = "hello";
    @Test
    public void TestVertexCreation() {
        Vertex sample = new Vertex(CONSTANT_SAMPLE);
        assertEquals(sample.getValue(), CONSTANT_SAMPLE);
    }
}
