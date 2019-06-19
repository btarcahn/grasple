package org.grasple.api.particles;

/**
 * <p>
     An interface defines the methods that a binary connection should have.
     A binary connection has two points: (start, end),
     it connects two Connectable objects. This is similar to
     a concept of a Relation (Discrete Mathematics). The BinaryConnection
     is bidirectional. An Edge is a concrete class implementing this interface.
 * </p>
 * <p>
 *     Note that two endpoints are of type Connectable, which is not generified.
 *     User may need to specifically typecast to the desired datatype during
 *     utilization.
 * </p>
 * <p>
 *     A BinaryConnection has <b>TWO</b> endpoints: (start, end). When declaring
 *     a BinaryConnection, one must define both endpoints. A BinaryConnection must be defined
 *     when it should be a self-connection. When successfully declared, a BinaryConnection
 *     can takes one endpoint and direct the user to the other endpoint.
 * </p>
 * @author Bach Tran
 * @since 1.0
 * @see Connectable
 * @see Edge
 */
public interface BinaryConnection {
    /**
     * Sets the [start] component of the binary connection.
     * @param connectable the start component to be set.
     */
    void setStart(Connectable connectable);

    /**
     * Sets the [end] component of the binary connection.
     * @param connectable the end component to be set.
     */
    void setEnd(Connectable connectable);

    /**
     * Directs to the other endpoint giving one endpoint.
     * @param connectable the current connectable
     * @return the opposite Connectable object in this Connection.
     */
    Connectable divert(Connectable connectable);
}
