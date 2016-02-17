package itu.datamining.week3.classification.entities;

import java.util.HashMap;

/**
 * Created by jan on 2/17/2016.
 */
public class Branch<T> extends Node {
    private final Object _attributeClass;

    public Branch(Object attributeClass) {
        this._attributeClass = attributeClass;
    }

    public Object getAttributeClass() {
        return this._attributeClass;
    }

    public HashMap<T, Node> m_attribute;

    public Node getChildNode(T attributeValue){
        return m_attribute.get(attributeValue);
    }
}
