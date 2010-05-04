package org.hccp.instrument.report.graph;

import java.util.ArrayList;
import java.util.List;


public class NodeDatabase {

    List<Node> roots = new ArrayList<Node>();

    public void addNode(Node node) {
        if (roots.contains(node)) {
            for (int i = 0; i < roots.size(); i++) {
                Node rootNode = roots.get(i);
                if (rootNode.equals(node)) {
                    incrementNodeAccessCount(rootNode);
                    processChildren(node.getChildren(), rootNode.getChildren(), rootNode);
                }
            }
        } else {

            roots.add(node);
        }
    }


    public List<Node> getRoots() {
        return roots;
    }

    private void incrementNodeAccessCount(Node node) {
        int currentCount = node.getAccessCount();
        node.setAccessCount(++currentCount);
    }

    private void processChildren(List<Node> children, List<Node> databaseChildren, Node databaseParent) {
        for (int i = 0; i < children.size(); i++) {
            boolean found = false;
            Node addedChildNode = children.get(i);
            for (int j = 0; j < databaseChildren.size(); j++) {
                Node databaseChild = databaseChildren.get(j);
                if (addedChildNode.equals(databaseChild)) {
                    incrementNodeAccessCount(databaseChild);
                    processChildren(addedChildNode.getChildren(), databaseChild.getChildren(), databaseChild);
                    found = true;
                    continue;
                }
            }

            if (!found) {
                databaseParent.addChild(addedChildNode);
            }
        }
    }


}
