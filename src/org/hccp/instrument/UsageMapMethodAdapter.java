package org.hccp.instrument;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 */
public class UsageMapMethodAdapter extends MethodAdapter {
    private String className;
    private String methodName;
    private String methodDescriptor;

    public UsageMapMethodAdapter(MethodVisitor methodVisitor) {
        super(methodVisitor);
    }

    public UsageMapMethodAdapter(MethodVisitor methodVisitor, String className, String methodName, String methodDescriptor) {
        super(methodVisitor);
        this.className = className;
        this.methodName = methodName;
        this.methodDescriptor = methodDescriptor;
        System.out.println("visited method " + getFullyQualifiedMethodName(className, methodName, methodDescriptor));


    }


    public void visitCode() {
        super.visitCode();


        if (!"org/hccp/instrument/UsageCounter".equals(className) && !className.startsWith("java/") && !className.startsWith("sun/")) {
            super.visitMethodInsn(Opcodes.INVOKESTATIC, "org/hccp/instrument/UsageCounter", "getInstance", "()Lorg/hccp/instrument/UsageCounter;");
            super.visitLdcInsn(getFullyQualifiedMethodName(className, methodName, methodDescriptor));
            super.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "org/hccp/instrument/UsageCounter", "incrementCountForMethod", "(Ljava/lang/String;)V");
        }
    }

    private String getFullyQualifiedMethodName(String className, String methodName, String methodDescriptor) {
        return className + "." + methodName + "(" + methodDescriptor + ")";
    }
}
