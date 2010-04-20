package org.hccp.instrument;

/**
 * java -javaagent:dist/hccp-usage-map.jar -cp dist/hccp-usage-map.jar:lib/asm-3.2.jar:lib/asm-util-3.2.jar  org.hccp.instrument.TestClass001
 */
public class TestClass001 {

    private TestClass002 secondTestClass = new TestClass002();
    private TestClass003 thirdTestClass = new TestClass003();


    public TestClass001() {
        secondTestClass = new TestClass002();

    }


    public static void main(String[] args) {
        System.out.println(System.getProperty("org.hccp.instrument.coverageFile"));
        TestClass001 firstTestClass = new TestClass001();
        firstTestClass.doSomething();
        firstTestClass.doSomething();

        for (int i = 0; i < 100; i++) {
            firstTestClass.doSomething();
        }

        for (int i = 0; i < 5; i++) {
            firstTestClass.doHttpViaThirdClass();
        }


        // UsageCounter counter = UsageCounter.getInstance();
        // counter.incrementCountForMethod("x");
    }


    public void doSomething() {
        int y = secondTestClass.doAnotherThing();
        System.out.println("y = " + y);
    }

    public void doHttpViaThirdClass() {
        thirdTestClass.doHttpRequest();
    }

}
