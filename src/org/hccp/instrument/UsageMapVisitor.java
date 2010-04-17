package org.hccp.instrument;

import org.objectweb.asm.*;

/**
 */
public class UsageMapVisitor implements ClassVisitor {
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.println(name + " extends " + superName + " {");
    }

    public void visitSource(String s, String s1) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void visitOuterClass(String s, String s1, String s2) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public AnnotationVisitor visitAnnotation(String s, boolean b) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void visitAttribute(Attribute attribute) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void visitInnerClass(String s, String s1, String s2, int i) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public FieldVisitor visitField(int i, String s, String s1, String s2, Object o) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println("Method: " + name);

        return null;
    }

    public void visitEnd() {
        System.out.println("}");
    }
}
