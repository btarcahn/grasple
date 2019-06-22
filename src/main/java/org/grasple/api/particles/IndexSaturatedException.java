package org.grasple.api.particles;

public class IndexSaturatedException extends RuntimeException {
    private static final String DEFAULT_MSG =
            "This memory index has been occupied by a previous value.";

    IndexSaturatedException() {
        super(DEFAULT_MSG);
    }
}
