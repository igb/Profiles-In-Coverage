package org.hccp.instrument.report.graph;

import java.util.List;


public interface Node {

    public int getAccessCount();
    public void setAccessCount(int accessCount);
    public float getCoverage();
    public void setCoverage(float coverage);
    public String getName();
    public void setName(String name);
    public String getQualifiedName();
    public void addChild(Node child);
    public List<Node> getChildren();
    public void setParent(Node parent);
    public Node getParent();

}
