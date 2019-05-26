package org.grasple.fundamentals;

/**
 * An interface for any BinaryConnection (such as an Edge).
 * A binary connection has two points: (start, end).
 */
public interface BinaryConnection {
    /**
     * Sets the [start] component of the binary connection.
     * @param connectable the start component to be set.
     */
    public void setStart(Connectable connectable);

    /**
     * Sets the [end] component of the binary connection.
     * @param connectable the end component to be set.
     */
    public void setEnd(Connectable connectable);

    /**
     * A self connection links a Connectable object to itself.
     * @return true if the BinaryConnection is a self connection.
     */
    public boolean isSelfConnection();

    /**
     * Return the opposite connectable object of the current connectable
     * @param connectable the current connectable
     * @return the opposite Connectable object in this Connection.
     */
    public Connectable getOpposite(Connectable connectable) throws IllegalArgumentException;
}
