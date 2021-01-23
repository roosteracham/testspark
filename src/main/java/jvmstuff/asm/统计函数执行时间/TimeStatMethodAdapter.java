package jvmstuff.asm.统计函数执行时间;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class TimeStatMethodAdapter extends MethodVisitor implements Opcodes {

    public TimeStatMethodAdapter(MethodVisitor methodVisitor) {
        super(Opcodes.ASM5, methodVisitor);
    }

    @Override
    public void visitCode() {
        visitMethodInsn(Opcodes.INVOKESTATIC, "jvmstuff/asm/统计函数执行时间/TimeStat", "start", "()V");
        super.visitCode();
    }

    @Override
    public void visitInsn(int i) {
        if (i >= IRETURN && i <= RETURN) {
            visitMethodInsn(Opcodes.INVOKESTATIC, "jvmstuff/asm/统计函数执行时间/TimeStat", "end", "()V");
        }
        mv.visitInsn(i);
    }
}
