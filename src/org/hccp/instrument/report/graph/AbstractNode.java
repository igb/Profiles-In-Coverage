package org.hccp.instrument.report.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: igb
 * Date: Apr 30, 2010
 * Time: 4:38:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class AbstractNode implements Node {

    private float coverage;
    private String name;
    private List<Node> children = new ArrayList<Node>();
    private int accessCount=1;
    private Node parent;


    public int getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(int accessCount) {
        this.accessCount=accessCount;
    }

    public float getCoverage() {
        return coverage;
    }

    public void setCoverage(float coverage) {
        this.coverage=coverage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getQualifiedName() {
        if (parent!=null) {
            return parent.getQualifiedName() + "." + name;
        } else {
            return name;
        }
    }


    public void addChild(Node child) {

        children.add(child);
        child.setParent(this);
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setParent(Node parent) {
        this.parent=parent;
    }

    public Node getParent() {
        return parent;
    }


    @Override
    public String toString() {
        return getQualifiedName();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractNode)) {
            return false;
        }
        if (this.getQualifiedName()==null || ((AbstractNode)obj).getQualifiedName()==null) {
            return false;
        }
        return getQualifiedName().equals(((AbstractNode)obj).getQualifiedName());
    }

    @Override
    public int hashCode() {
        if (getQualifiedName() != null) {
            return getQualifiedName().hashCode();
        }
        return -1;
    }
}
