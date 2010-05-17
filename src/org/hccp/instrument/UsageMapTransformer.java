package org.hccp.instrument;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.PrintWriter;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: igb
 * Date: Apr 15, 2010
 * Time: 3:55:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class UsageMapTransformer implements ClassFileTransformer {
    private String[] packagesToInstrument = null;


    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {


        byte[] result = classfileBuffer;
        ClassReader reader = new ClassReader(classfileBuffer);
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        reader.accept(new UsageMapClassAdapter(writer, className), ClassReader.EXPAND_FRAMES);

        if (!"org/hccp/instrument/UsageCounter".equals(className) && classPassesFilter(className)) {
            result = writer.toByteArray();
        }

        return result;
    }

    private boolean classPassesFilter(String className) {


        if (packagesToInstrument == null) {
            synchronized (this) {



                String packageFilterString = System.getProperty("org.hccp.instrument.packageFilter");

                String[] dotSeparatedPackagesToInstrument = packageFilterString.split(",");

                packagesToInstrument=new String[dotSeparatedPackagesToInstrument.length];

                for (int i = 0; i < dotSeparatedPackagesToInstrument.length; i++) {
                    String dotSeparatedPackage = dotSeparatedPackagesToInstrument[i];
                    packagesToInstrument[i]=dotSeparatedPackage.replace('.', '/');

                }

            }
        }

        for (int i = 0; i < packagesToInstrument.length; i++) {
            String packageToInstrument = packagesToInstrument[i];
            if (className.startsWith(packageToInstrument)) {
                return true;
            }
        }

        return false;

    }
}
