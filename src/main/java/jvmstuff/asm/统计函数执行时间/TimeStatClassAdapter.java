package jvmstuff.asm.统计函数执行时间;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class TimeStatClassAdapter extends ClassVisitor {

    public TimeStatClassAdapter(ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {
        MethodVisitor methodVisitor = cv.visitMethod(i, s, s1, s2, strings);
        MethodVisitor wmv = methodVisitor;
        if (methodVisitor != null) {
            if (s.equals("operation")) {
                wmv = new TimeStatMethodAdapter(methodVisitor);
            }
        }

        return wmv;
    }
}
