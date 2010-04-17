package org.hccp.instrument;

/**
 * Created by IntelliJ IDEA.
 * User: igb
 * Date: Apr 16, 2010
 * Time: 10:08:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class UsageCounterTask extends Thread {

    private long interval;

    public UsageCounterTask(long interval) {
        this.interval = interval;
    }

    @Override
    public void run() {
        try {

            

            Thread.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
