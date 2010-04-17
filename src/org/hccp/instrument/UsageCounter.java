package org.hccp.instrument;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 */
public class UsageCounter {

    /**
     * Singleton used as global db for hit count...
     */
    private final static UsageCounter counter = new UsageCounter();

    public static UsageCounter getInstance() {
        return counter;
    }

    private static Map<String, Integer> map = new HashMap<String, Integer>();




    public synchronized void incrementCountForMethod(String method) {

        Integer count = map.get(method);
        if (count == null) {
            count = 1;

        } else {
            count += 1;
        }

        map.put(method, count);
    }

}
