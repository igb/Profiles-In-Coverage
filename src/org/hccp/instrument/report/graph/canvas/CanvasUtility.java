package org.hccp.instrument.report.graph.canvas;

import org.hccp.instrument.report.graph.Node;


public class CanvasUtility {

    public static String drawNode(Node node, int totalCount, int x, int y) {

        StringBuffer sb = new StringBuffer();

        float alpha = getAlphaForNode(node, totalCount);
        
        sb.append("ctx.fillStyle = \"rgba(255,0,0," + alpha +")\";\n");
        sb.append("ctx.beginPath();\n");
        sb.append("ctx.rect(" + x +", " + y + ", 20, 20);\n");
        sb.append("ctx.closePath();\n");
        sb.append("ctx.fill();\n");
        return sb.toString();
        
    }

    /**
     * Imprecise, but good enough for canvas alpha values
     *
     * @param node
     * @param totalCount
     * @return
     */
    public static float getAlphaForNode(Node node, int totalCount) {
        return getAlphaForNode(node.getAccessCount(), totalCount);
    }

    /**
     * Imprecise, but good enough for canvas alpha values
     *
     * @param accessCount
     * @param totalCount
     * @return
     */

    public static float getAlphaForNode(int accessCount, int totalCount) {
        return ((float) accessCount) / totalCount;
    }

}
