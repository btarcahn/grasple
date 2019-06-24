package org.grasple.api.particles;

public interface IndexedConnectable<T> extends Connectable<T> {
    /**
     * <b>Saturation: </b> a connectable object is saturated
     * if it refused to establish more connections.
     * @return true if the object is saturated.
     */
    boolean isSaturated();
    boolean addConnection(int index, Connection connection);
    boolean removeConnection(int index);
    boolean connect(int index, Connectable<T> other);
    IndexedConnectable<T> getNeighbor(int index);
}
