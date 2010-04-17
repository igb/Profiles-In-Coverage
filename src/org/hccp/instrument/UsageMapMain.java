package org.hccp.instrument;

import java.lang.instrument.Instrumentation;
/**
 * Created by IntelliJ IDEA.
 * User: igb
 * Date: Apr 15, 2010
 * Time: 4:01:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class UsageMapMain {

    public static void premain(String agentArguments, Instrumentation instrumentation) {
		instrumentation.addTransformer(new UsageMapTransformer());
	}
    
}
