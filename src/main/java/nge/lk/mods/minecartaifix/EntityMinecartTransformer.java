package nge.lk.mods.minecartaifix;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;

import java.util.Iterator;

/**
 * Transforms the minecart class to fix the AI bug.
 */
public class EntityMinecartTransformer implements IClassTransformer {

    /**
     * Transforms the {@code instanceof} that causes the bug behavior.
     *
     * @param dataIn The class file byte array.
     *
     * @return The modified class file byte array.
     */
    private static byte[] transformMinecartClass(byte[] dataIn) {
        ClassNode cn = ASMHelper.createFromClassFileByteArray(dataIn);
        MethodNode mn = ASMHelper.getMethod(cn,
                "(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/state/IBlockState;)V",
                "moveAlongTrack", "func_180460_a");

        Iterator<AbstractInsnNode> it = mn.instructions.iterator();
        while (it.hasNext()) {
            AbstractInsnNode ain = it.next();
            // The first instanceof in the method should be changed from
            //   entity instanceof EntityLivingBase
            // to
            //   entity instanceof EntityPlayer
            if (ain.getOpcode() == Opcodes.INSTANCEOF) {
                TypeInsnNode tin = (TypeInsnNode) ain;
                tin.desc = "net/minecraft/entity/player/EntityPlayer";
                break;
            }
        }

        return ASMHelper.convertToClassFileByteArray(cn);
    }

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if ("net.minecraft.entity.item.EntityMinecart".equals(transformedName)) {
            return transformMinecartClass(basicClass);
        }
        return basicClass;
    }
}
