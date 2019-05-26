package org.grasple.fundamentals;

/**
 * <p>
     An interface defines the methods that a binary connection should have.
     A binary connection has two points: (start, end),
     it connects two Connectable objects. This is similar to
     a concept of a Relation (Discrete Mathematics). The BinaryConnection
     is bidirectional. An Edge is a concrete class implementing this interface.
 * </p>
 * <p>
 *     An endpoint should be Connectable, i.e. it should be ready to
 *     be attached onto a Connection.
 * </p>
 * <p>
 *     A BinaryConnection has <b>TWO</b> endpoints: (start, end). When declaring
 *     a BinaryConnection, one must define both endpoints. A BinaryConnection must be defined
 *     when it should be a self-connection. When successfully declared, a BinaryConnection
 *     can takes one endpoint and direct the user to the other endpoint.
 * </p>
 * @author Bach Tran
 * @see Connectable
 * @see Edge
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
     * Checks if this connection is a self-connection.
     * @return true if the BinaryConnection is a self connection.
     */
    public boolean isSelfConnection();

    /**
     * Directs to the other endpoint giving one endpoint.
     * @param connectable the current connectable
     * @return the opposite Connectable object in this Connection.
     */
    public Connectable getOpposite(Connectable connectable) throws IllegalArgumentException;
}
