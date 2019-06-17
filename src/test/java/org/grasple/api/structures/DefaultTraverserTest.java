package org.grasple.api.structures;

import org.grasple.api.TestUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DefaultTraverserTest {

    @Test
    void run() {
        DefaultTraverser _tested_candidate = new DefaultTraverser(TestUtils.CENTRAL_VERTEX);
        assertEquals(0, _tested_candidate.getVisited().size());
        _tested_candidate.run();
        // ensures all vertices have been visited by the algorithm
        assertTrue(_tested_candidate.getVisited().contains(TestUtils.CENTRAL_VERTEX));
        assertTrue(_tested_candidate.getVisited().containsAll(TestUtils.PRIMARY_NEIGHBORS));
        assertTrue(_tested_candidate.getVisited().containsAll(TestUtils.SECONDARY_NEIGHBORS));
    }
}