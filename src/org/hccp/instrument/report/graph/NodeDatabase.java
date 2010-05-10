package org.hccp.instrument.report.graph;

import org.hccp.instrument.report.graph.canvas.CanvasUtility;

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

    @Override
    public String toString() {
        return printDatabase(roots, 0, new StringBuffer());
    }

    private String  printDatabase(List<Node> nodes, int offset, StringBuffer buffer) {
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            for (int j=0; j<= offset; j++) {
                buffer.append(" ");
            }
            buffer.append(node.getName() + " (" + node.getAccessCount() + ")\n");
            List<Node> children = node.getChildren();
            printDatabase(children, offset + 1, buffer );
        }
        return buffer.toString();
    }


        public String  printDatabaseAsCanvas(List<Node> nodes, int offset, int totalCount, StringBuffer buffer) {
         int xOffset=(20 * offset) + 5;
           int yOffset=0;
            for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            for (int j=0; j<= offset; j++) {
               yOffset=(20 * j) + 5;
            }

                buffer.append(CanvasUtility.drawNode(node, totalCount, xOffset, yOffset));

            List<Node> children = node.getChildren();
            printDatabaseAsCanvas(children, offset + 1, totalCount, buffer );
        }
        return buffer.toString();
    }


    public String renderDatabaseToCanvas(Node node) {
        StringBuffer sb = new StringBuffer();
       sb.append(sanitize(node.getQualifiedName()) +  "=new node('" + node.getName() + "', " + node.getAccessCount() + ");\n");


        List<Node> children = node.getChildren();
        for (int i = 0; i < children.size(); i++) {
            Node child = children.get(i);
            sb.append(renderDatabaseToCanvas(child));
            sb.append(sanitize(node.getQualifiedName()) + ".add_child(" + sanitize(child.getQualifiedName()) + ");\n");

        }
        return sb.toString();
    }

    public String sanitize(String dirtyString) {
        return dirtyString.replaceAll("[\\[\\.();/<>\\]]", "_");
    }

    
}
