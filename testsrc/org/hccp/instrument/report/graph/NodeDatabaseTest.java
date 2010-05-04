package org.hccp.instrument.report.graph;

import org.hccp.instrument.report.UsageReportUtility;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class NodeDatabaseTest {


    @Test
    public void testAddWithNoRoots() {
        Node com = new Package();
        com.setName("com");

        NodeDatabase db = new NodeDatabase();
        db.addNode(com);

        List roots = db.getRoots();

        assertEquals(1, roots.size());
        assertEquals(com, roots.get(0));

    }

    @Test
    public void testAddWithCommonRoots() {
        Node com1 = new Package();
        com1.setName("com");

        Node foo = new Package();
        foo.setName("foo");

        com1.addChild(foo);

        Node com2 = new Package();
        com2.setName("com");

        Node bar = new Package();
        bar.setName("bar");

        com2.addChild(bar);


        Node com3 = new Package();
        com3.setName("com");

        NodeDatabase db = new NodeDatabase();
        assertEquals(com1, com2);
        db.addNode(com1);
        db.addNode(com2);
        db.addNode(com3);


        List<Node> roots = db.getRoots();

        assertEquals(1, roots.size());
        assertEquals(3, roots.get(0).getAccessCount());


    }


    @Test
    public void testStatsRollup() {

        NodeDatabase db = new NodeDatabase();
        UsageReportUtility util = new UsageReportUtility();


        String[] data = {"org/hccp/instrument/TestClass001.doHttpViaThirdClass(()V)",
                "org/hccp/instrument/TestClass003.doHttpRequest(()V)",
                "com/slamd/http/HTTPRequest.<init>((ZLjava/net/URL;)V)",
                "com/slamd/http/HTTPClient.<init>(()V)",
                "com/slamd/http/HTTPClient.sendRequest((Lcom/slamd/http/HTTPRequest;)Lcom/slamd/http/HTTPResponse;)",
                "com/slamd/http/HTTPClient.sendRequestInternal((Lcom/slamd/http/HTTPRequest;Z)Lcom/slamd/http/HTTPResponse;)",
                "com/slamd/http/HTTPRequest.generateHTTPRequest((Lcom/slamd/http/HTTPClient;)Ljava/lang/String;)",
                "com/slamd/http/HTTPClient.getCookies((Ljava/net/URL;)[Lcom/slamd/http/HTTPCookie;)",
                "com/slamd/asn1/ASN1Element.getBytes((Ljava/lang/String;)[B)",
                "com/slamd/http/HTTPRequest.getBaseURL(()Ljava/net/URL;)",
                "com/slamd/http/HTTPClient.readResponse((Ljava/net/URL;Ljava/io/InputStream;Z)Lcom/slamd/http/HTTPResponse;)",
                "com/slamd/http/HTTPException.<init>((Ljava/lang/String;Ljava/lang/Throwable;)V)",
                "org/hccp/instrument/TestClass001.doHttpViaThirdClass(()V)",
                "org/hccp/instrument/TestClass003.doHttpRequest(()V)",
                "com/slamd/http/HTTPRequest.<init>((ZLjava/net/URL;)V)",
                "com/slamd/http/HTTPClient.<init>(()V)",
                "com/slamd/http/HTTPClient.sendRequest((Lcom/slamd/http/HTTPRequest;)Lcom/slamd/http/HTTPResponse;)",
                "com/slamd/http/HTTPClient.sendRequestInternal((Lcom/slamd/http/HTTPRequest;Z)Lcom/slamd/http/HTTPResponse;)",
                "com/slamd/http/HTTPRequest.generateHTTPRequest((Lcom/slamd/http/HTTPClient;)Ljava/lang/String;)",
                "com/slamd/http/HTTPClient.getCookies((Ljava/net/URL;)[Lcom/slamd/http/HTTPCookie;)",
                "com/slamd/asn1/ASN1Element.getBytes((Ljava/lang/String;)[B)",
                "com/slamd/http/HTTPRequest.getBaseURL(()Ljava/net/URL;)",
                "com/slamd/http/HTTPClient.readResponse((Ljava/net/URL;Ljava/io/InputStream;Z)Lcom/slamd/http/HTTPResponse;)",
                "com/slamd/http/HTTPException.<init>((Ljava/lang/String;Ljava/lang/Throwable;)V)",
                "org/hccp/instrument/TestClass001.doHttpViaThirdClass(()V)",
                "org/hccp/instrument/TestClass003.doHttpRequest(()V)",
                "com/slamd/http/HTTPRequest.<init>((ZLjava/net/URL;)V)",
                "com/slamd/http/HTTPClient.<init>(()V)",
                "com/slamd/http/HTTPClient.sendRequest((Lcom/slamd/http/HTTPRequest;)Lcom/slamd/http/HTTPResponse;)",
                "com/slamd/http/HTTPClient.sendRequestInternal((Lcom/slamd/http/HTTPRequest;Z)Lcom/slamd/http/HTTPResponse;)",
                "com/slamd/http/HTTPRequest.generateHTTPRequest((Lcom/slamd/http/HTTPClient;)Ljava/lang/String;)",
                "com/slamd/http/HTTPClient.getCookies((Ljava/net/URL;)[Lcom/slamd/http/HTTPCookie;)",
                "com/slamd/asn1/ASN1Element.getBytes((Ljava/lang/String;)[B)",
                "com/slamd/http/HTTPRequest.getBaseURL(()Ljava/net/URL;)",
                "com/slamd/http/HTTPClient.readResponse((Ljava/net/URL;Ljava/io/InputStream;Z)Lcom/slamd/http/HTTPResponse;)",
                "com/slamd/http/HTTPException.<init>((Ljava/lang/String;Ljava/lang/Throwable;)V)",
                "org/hccp/instrument/TestClass001.doHttpViaThirdClass(()V)",
                "org/hccp/instrument/TestClass003.doHttpRequest(()V)",
                "com/slamd/http/HTTPRequest.<init>((ZLjava/net/URL;)V)",
                "com/slamd/http/HTTPClient.<init>(()V)",
                "com/slamd/http/HTTPClient.sendRequest((Lcom/slamd/http/HTTPRequest;)Lcom/slamd/http/HTTPResponse;)",
                "com/slamd/http/HTTPClient.sendRequestInternal((Lcom/slamd/http/HTTPRequest;Z)Lcom/slamd/http/HTTPResponse;)",
                "com/slamd/http/HTTPRequest.generateHTTPRequest((Lcom/slamd/http/HTTPClient;)Ljava/lang/String;)",
                "com/slamd/http/HTTPClient.getCookies((Ljava/net/URL;)[Lcom/slamd/http/HTTPCookie;)",
                "com/slamd/asn1/ASN1Element.getBytes((Ljava/lang/String;)[B)",
                "com/slamd/http/HTTPRequest.getBaseURL(()Ljava/net/URL;)",
                "com/slamd/http/HTTPClient.readResponse((Ljava/net/URL;Ljava/io/InputStream;Z)Lcom/slamd/http/HTTPResponse;)",
                "com/slamd/http/HTTPException.<init>((Ljava/lang/String;Ljava/lang/Throwable;)V)"};

        for (int i = 0; i < data.length; i++) {
            String entry = data[i];
            Node node = util.getNodeFromString(entry);
            db.addNode(node);
        }

        List<Node> roots = db.getRoots();
        assertEquals(2, roots.size());

        for (int i = 0; i < roots.size(); i++) {
            Node root = roots.get(i);
            if (root.getName().equals("org")) {

                assertEquals(8, root.getAccessCount());

                List<Node> orgChildren = root.getChildren();
                assertEquals(1, orgChildren.size());

                Node hccp = orgChildren.get(0);
                assertEquals("hccp", hccp.getName());

                List<Node> hccpChildren = hccp.getChildren();
                assertEquals(1, hccpChildren.size());

                Node instrument = hccpChildren.get(0);
                assertEquals("instrument", instrument.getName());

                List<Node> instrumentChildren = instrument.getChildren();
                assertEquals(2, instrumentChildren.size());


            } else if (root.getName().equals("com")) {
                assertEquals(40, root.getAccessCount());
            }
        }
        

    }

}
