package org.grasple.api.particles;

/**
 * Unified connection.
 * The connection that connects to objects of the same type.
 * @author Bach Tran
 * @param <T> the type of the two objects.
 */
public interface UConnection<T extends Connectable> extends Connection<T , T> {
    T divert(T endpoint);
}
