package org.hccp.instrument;

/**
*
*/
public class TestClass001 {

    private TestClass002 secondTestClass = new TestClass002();

    public TestClass001() {
        secondTestClass = new  TestClass002();
    }


    public static void main(String[] args) {
        TestClass001 firstTestClass = new TestClass001();
        firstTestClass.doSomething();
        firstTestClass.doSomething();

        for (int i=0; i < 100; i++) {
            firstTestClass.doSomething();       
        }
       // UsageCounter counter = UsageCounter.getInstance();
       // counter.incrementCountForMethod("x");
    }


    public void doSomething() {
        int y = secondTestClass.doAnotherThing();
        System.out.println("y = " + y);
    }
}
