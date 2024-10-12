package net.toshayo.tokanocreations.transformers;

import net.minecraft.launchwrapper.IClassTransformer;
import net.toshayo.tokanocreations.TokanoCreationsPlugin;
import org.objectweb.asm.*;


public class BlockBookshelfTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if(transformedName.equals("net.minecraft.block.BlockBookshelf")) {
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
            new ClassReader(basicClass).accept(new ClassVisitor(Opcodes.ASM5, classWriter) {
                @Override
                public void visitEnd() {
                    MethodVisitor mv = cv.visitMethod(Opcodes.ACC_PUBLIC, Mappings.get("onBlockPlaced"), "(L" + Mappings.get("net/minecraft/world/World") + ";IIIIFFFI)I", null, null);
                    if (mv != null) {
                        TokanoCreationsPlugin.LOGGER.info("Overriding method onBlockPlaced in BlockBookshelf");
                        mv.visitCode();

                        mv.visitVarInsn(Opcodes.ILOAD, 9);
                        mv.visitVarInsn(Opcodes.BIPUSH, 8);
                        mv.visitInsn(Opcodes.IOR);
                        mv.visitInsn(Opcodes.IRETURN);

                        mv.visitEnd();
                    }

                    mv = cv.visitMethod(Opcodes.ACC_PUBLIC, Mappings.get("onBlockActivated"), "(L" + Mappings.get("net/minecraft/world/World") + ";IIIL" + Mappings.get("net/minecraft/entity/player/EntityPlayer") + ";IFFF)Z", null, null);
                    if (mv != null) {
                        TokanoCreationsPlugin.LOGGER.info("Overriding method onBlockActivated in BlockBookshelf");
                        mv.visitCode();

                        mv.visitVarInsn(Opcodes.ALOAD, 1);
                        mv.visitVarInsn(Opcodes.ILOAD, 2);
                        mv.visitVarInsn(Opcodes.ILOAD, 3);
                        mv.visitVarInsn(Opcodes.ILOAD, 4);
                        mv.visitVarInsn(Opcodes.ALOAD, 5);
                        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "net/toshayo/tokanocreations/ASMHelpers", "onBlockActivated", "(L" + Mappings.get("net/minecraft/world/World") + ";IIIL" + Mappings.get("net/minecraft/entity/player/EntityPlayer") + ";)Z", false);
                        mv.visitInsn(Opcodes.IRETURN);

                        mv.visitEnd();
                    }
                    super.visitEnd();
                }
            }, ClassReader.EXPAND_FRAMES);
            return classWriter.toByteArray();
        }
        return basicClass;
    }
}
