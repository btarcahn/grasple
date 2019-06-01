package org.grasple.simples;

import org.grasple.TestUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepthFirstTraverserTest {

    @Test
    void run() {
        DepthFirstTraverser _tested_candidate = new DepthFirstTraverser(TestUtils.CENTRAL_VERTEX);
        assertEquals(0, _tested_candidate.getVisited().size());
        _tested_candidate.run();
        assertTrue(_tested_candidate.getVisited().contains(TestUtils.CENTRAL_VERTEX));
        assertTrue(_tested_candidate.getVisited().containsAll(TestUtils.PRIMARY_NEIGHBORS));
        assertTrue(_tested_candidate.getVisited().containsAll(TestUtils.SECONDARY_NEIGHBORS));
    }
}