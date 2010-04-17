package org.hccp.instrument;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 */
public class UsageMapClassAdapter extends ClassAdapter {
    public UsageMapClassAdapter(ClassVisitor classVisitor) {
        super(classVisitor);
    }

    private String className;

    UsageMapClassAdapter(ClassVisitor visitor, String theClass) {
        super(visitor);
        className = theClass;
    }

    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        return new UsageMapMethodAdapter(super.visitMethod(access, name, descriptor, signature, exceptions), className, name, descriptor);
    }


}
