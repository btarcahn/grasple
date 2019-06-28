package org.grasple.api.particles;

/**
 * <p>
     An interface defines the methods that a binary connection should have.
     A binary connection has two points: (start, end),
     it connects two Connectable objects. This is similar to
     a concept of a Relation (Discrete Mathematics). The Connection
     is bidirectional. An Edge is a concrete class implementing this interface.
 * </p>
 * <p>
 *     Note that two endpoints are of type Connectable, which is not generified.
 *     User may need to specifically typecast to the desired datatype during
 *     utilization.
 * </p>
 * <p>
 *     A Connection has <b>TWO</b> endpoints: (start, end). When declaring
 *     a Connection, one must define both endpoints. A Connection must be defined
 *     when it should be a self-connection. When successfully declared, a Connection
 *     can takes one endpoint and direct the user to the other endpoint.
 * </p>
 * @author Bach Tran
 * @since 1.0
 * @see Connectable
 * @see Edge
 */
public interface Connection<S extends Node<S>, E extends Node<E>> {
    /**
     * Retrieves the weight of the connection.
     * @return an integer determines the weight of the connection.
     */
    int weight();

    /**
     * Sets the [start] component of the binary connection.
     * @param start the start component to be set.
     */
    void setStart(S start);
    /**
     * Sets the [end] component of the binary connection.
     * @param end the end component to be set.
     */
    void setEnd(E end);
}
