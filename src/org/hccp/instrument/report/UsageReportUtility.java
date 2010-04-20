package org.hccp.instrument.report;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: igb
 * Date: Apr 20, 2010
 * Time: 2:10:06 PM
 * To change this template use File | Settings | File Templates.
 */
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
        while (itr.hasNext()) {
            String method = (String) itr.next();
            System.out.println(method + " is called " + map.get(method) + " times.");
        }
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
