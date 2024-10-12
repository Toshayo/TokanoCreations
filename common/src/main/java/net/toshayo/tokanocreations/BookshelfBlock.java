package net.toshayo.tokanocreations;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class BookshelfBlock extends Block {
    public static final BooleanProperty GENERATED = BooleanProperty.create("generated");

    public BookshelfBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(GENERATED, true));
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if(!level.isClientSide & interactionHand == InteractionHand.MAIN_HAND) {
            if(blockState.getValue(GENERATED)) {
                if(level.random.nextInt(0, 100) == 0) {
                    ItemStack stack;
                    if(level.random.nextBoolean()) {
                        stack = new ItemStack(Items.WRITTEN_BOOK);
                        CompoundTag nbt = new CompoundTag();
                        nbt.putString("title", "Ideal-chan setting");
                        nbt.putString("author", "tokachan5656");
                        nbt.putInt("generation", 3);

                        CompoundTag display = new CompoundTag();
                        display.putString("Name", "{\"translate\":\"item.tokanocreations.ideal_setting_book\"}");
                        ListTag lore = new ListTag();
                        lore.add(StringTag.valueOf("{\"translate\":\"item.tokanocreations.ideal_setting_book.desc\"}"));
                        display.put("Lore", lore);
                        nbt.put("display", display);

                        ListTag pages = new ListTag();
                        for (int i = 0; i < 7; i++) {
                            pages.add(StringTag.valueOf("[{\"translate\":\"item.tokanocreations.ideal_setting_book.page[" + i + "]\"}]"));
                        }
                        nbt.put("pages", pages);
                        stack.setTag(nbt);
                    } else {
                        stack = new ItemStack(ModItems.IDEAL_HAIR.get());
                    }

                    player.addItem(stack);
                }
                level.setBlock(blockPos, blockState.setValue(GENERATED, false), 2);
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> arg) {
        arg.add(GENERATED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext arg) {
        return this.defaultBlockState().setValue(GENERATED, false);
    }
}
