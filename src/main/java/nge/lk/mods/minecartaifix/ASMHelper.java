package nge.lk.mods.minecartaifix;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * Provides useful ASM functionalities.
 */
public final class ASMHelper {

    /**
     * Creates a class node from a class file byte array.
     *
     * @param data The class file byte array.
     *
     * @return The class node.
     */
    public static ClassNode createFromClassFileByteArray(byte[] data) {
        ClassNode cn = new ClassNode();
        ClassReader cr = new ClassReader(data);
        cr.accept(cn, 0);
        return cn;
    }

    /**
     * Fetches a method from a class node.
     *
     * @param cn The class node.
     * @param descriptor The descriptor of the method.
     * @param names The names which the method might have.
     *
     * @return The method node.
     */
    public static MethodNode getMethod(ClassNode cn, String descriptor, String... names) {
        for (MethodNode mn : cn.methods) {
            if (mn.desc.equals(descriptor)) {
                // Need to check that one of the names matches.
                boolean eq = false;
                for (String name : names) {
                    if (mn.name.equals(name)) {
                        eq = true;
                        break;
                    }
                }
                if (eq) {
                    return mn;
                }
            }
        }
        return null;
    }

    /**
     * Converts a modified class node to a class file byte array.
     *
     * @param cn The modified class node.
     *
     * @return The class file byte array.
     */
    public static byte[] convertToClassFileByteArray(ClassNode cn) {
        // No frame computation. For this to work, frames must not be changed.
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        cn.accept(cw);
        return cw.toByteArray();
    }

    /**
     * Private constructor to prevent instance creation.
     */
    private ASMHelper() {
    }
}
