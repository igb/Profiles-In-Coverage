package org.hccp.instrument;

import java.lang.instrument.Instrumentation;

/**
 * Created by IntelliJ IDEA.
 * User: igb
 * Date: May 16, 2010
 * Time: 12:04:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class DummyPremain {

      public static void premain(String agentArguments, Instrumentation instrumentation) {
          System.out.println("agentArguments = " + agentArguments);
          instrumentation.addTransformer(new DummyClassFileTransformer());
          
	}
}
