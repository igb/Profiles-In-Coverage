package org.hccp.instrument;

import java.io.*;
import java.util.UUID;

/**
 */
public class UsageCounter {

    private long id;

    /**
     * Singleton used as global db for hit count...
     */
    private final static UsageCounter counter = new UsageCounter();
    public static final String COVERAGE_FILE = "org.hccp.instrument.coverageFile";

    public static UsageCounter getInstance() {
        return counter;

    }


    public UsageCounter() {
         id = System.currentTimeMillis();
    }




    public synchronized void incrementCountForMethod(String method) {

        File file = new File(System.getProperty(COVERAGE_FILE) + id);
        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            PrintStream ps = new PrintStream(fos);
            PrintWriter writer = new PrintWriter(ps);
            writer.println(method);

            writer.flush();
            System.out.println(method);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
