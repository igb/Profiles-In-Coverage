package org.hccp.instrument.report;

import org.hccp.instrument.report.graph.ClassFile;
import org.hccp.instrument.report.graph.Method;
import org.hccp.instrument.report.graph.Node;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class UsageReportUtility {

    public static void main(String[] args) throws IOException {

        Map map = new HashMap();

        FileInputStream fstream = new FileInputStream(args[0]);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        while ((strLine = br.readLine()) != null) {
            increment(map, strLine);
        }
        in.close();

        Iterator itr = map.keySet().iterator();
        int count=0;

        while (itr.hasNext()) {
            String method = (String) itr.next();
            System.out.println(method + " is called " + map.get(method) + " times.");
            count+=(Integer)map.get(method);
        }
        System.out.println("count = " + count);
    }


    public Node getNodeFromString(String method) {
        Node node;
        int slashOffset=method.indexOf('/');
        if (slashOffset > 0 && method.indexOf('.') > slashOffset) {
            node = new org.hccp.instrument.report.graph.Package();
            String methodName = method.substring(0, slashOffset);
            node.setName(methodName);
            node.addChild(getNodeFromString(method.substring(slashOffset + 1)));
        } else {
            node = new ClassFile();
            int dotOffset=method.indexOf('.');
            String className = method.substring(0, dotOffset);
            node.setName(className);
            String methodname = method.substring(dotOffset + 1);
            Method m = new Method();
            m.setName(methodname);
            node.addChild(m);


        }
        return node;
    }


    public static void increment(Map map, String method) {
        if (map.containsKey(method)) {
            int count = (Integer) map.get(method);
            count++;
            map.put(method, count);
        } else {
            map.put(method, 1);
        }
    }

    

}
