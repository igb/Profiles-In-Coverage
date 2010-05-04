package org.hccp.instrument.report;

import org.hccp.instrument.report.graph.*;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.*;
import static junit.framework.Assert.assertEquals;


public class UsageReportUtilityTest {
    private static final String PACKAGE = "PACKAGE";
    private static final String CLASS_FILE = "CLASS_FILE";
    private static final String METHOD = "METHOD";

    @Test
    public void testParseMethod() {

        UsageReportUtility util = new UsageReportUtility();
        Node node = util.getNodeFromString("com/slamd/http/HTTPRequest.getBaseURL(()Ljava/net/URL;)");

        assertNotNull(node);
        assertTrue(node instanceof  org.hccp.instrument.report.graph.Package);
        assertEquals("com", node.getName());

        Node slamd=assertChildCorrectness(node, "slamd", PACKAGE);
        Node http = assertChildCorrectness(slamd, "http", PACKAGE);
        Node reqClass = assertChildCorrectness(http, "HTTPRequest", CLASS_FILE);
        Node getBaseUrlMethod = assertChildCorrectness(reqClass, "getBaseURL(()Ljava/net/URL;)", METHOD);


        //walk back up the tree, testing that parents were created correctly...
        assertEquals(reqClass, getBaseUrlMethod.getParent());
        assertEquals(http, reqClass.getParent());
        assertEquals(slamd, http.getParent());
        assertEquals(node, slamd.getParent());

    }

    private Node assertChildCorrectness(Node node, String name, String type) {
        List<Node> nodes = node.getChildren();
        assertEquals(nodes.size(), 1);
        Node child = nodes.get(0);
        if (PACKAGE.equals(type)) {
            assertTrue(child instanceof  org.hccp.instrument.report.graph.Package);
        } else if (CLASS_FILE.equals(type)) {
            assertTrue(child instanceof  ClassFile);
        }  else if (METHOD.equals(type)) {
            assertTrue(child instanceof  Method);
        }
            assertEquals(name, child.getName());
        return child;
    }
}
