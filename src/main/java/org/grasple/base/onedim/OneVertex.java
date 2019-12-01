package org.grasple.base.onedim;


import org.grasple.base.atoms.Vertex;

public class OneVertex<T> extends Vertex<T>
            implements OneNode<T> {

    private OneVertex<T> nextVertex;

    public void setNextVertex(OneVertex<T> nextVertex) {
        this.nextVertex = nextVertex;
    }

    public OneVertex(T value) {
        super(value);
        nextVertex = null;
    }

    @Override
    public OneNode<T> next() {
        return nextVertex;
    }

    @Override
    public T extract() {
        return super.extract();
    }
}
