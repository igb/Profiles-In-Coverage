package org.hccp.instrument;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;


public class DummyClassFileTransformer implements ClassFileTransformer {

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println("dummy5: " + className);
        System.out.println("dummy5: " + (!"org/hccp/instrument/UsageCounter".equals(className) && !className.startsWith("java/") && !className.startsWith("javax/") && !className.startsWith("sun/") && !className.startsWith("org/apache/juli")));
        byte[] result = classfileBuffer;


        ClassReader reader = new ClassReader(classfileBuffer);
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        reader.accept(new UsageMapClassAdapter(writer, className), ClassReader.EXPAND_FRAMES);


        if (!"org/hccp/instrument/UsageCounter".equals(className) && className.startsWith("org/hccp/")) {
            result = writer.toByteArray();
        }
        return result;

    }


}

