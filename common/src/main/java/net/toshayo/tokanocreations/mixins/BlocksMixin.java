package net.toshayo.tokanocreations.mixins;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.toshayo.tokanocreations.BookshelfBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Blocks.class)
public class BlocksMixin {
    @SuppressWarnings("DiscouragedShift")
    @Redirect(
            method = "<clinit>",
            at = @At(
                    value = "NEW",
                    target = "(Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)Lnet/minecraft/world/level/block/Block;",
                    ordinal = 0
            ),
            slice = @Slice(
                    from = @At(
                            value = "FIELD",
                            target = "Lnet/minecraft/world/level/block/Blocks;BOOKSHELF:Lnet/minecraft/world/level/block/Block;",
                            shift = At.Shift.BY,
                            by = -10
                    )
            )

    )
    private static Block onNewBlock(BlockBehaviour.Properties properties) {
        return new BookshelfBlock(properties);
    }

    @Inject(
            method = "register",
            at = @At("HEAD")
    )
    private static void onRegister(String string, Block block, CallbackInfoReturnable<Block> cir) {
        if(string.equals("bookshelf") && !(block instanceof BookshelfBlock)) {
            throw new RuntimeException("Failed to redirect bookshelf block registry!");
        }
        if(!string.equals("bookshelf") && block instanceof BookshelfBlock) {
            throw new RuntimeException("Redirected random block to Bookshelf! (" + string + ")");
        }
    }
}
