package org.grasple.fundamentals;

public interface Connectable {
    public boolean addConnection(BinaryConnection connection);
    public boolean removeConnection(BinaryConnection connection);
}
