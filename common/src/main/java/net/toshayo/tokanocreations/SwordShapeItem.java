package net.toshayo.tokanocreations;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SwordShapeItem extends Item {
    public final int reforgedTimes;

    public static final int REINFORCE_COOK_TIMES = 3;

    public SwordShapeItem(int reforgedTimes) {
        super(new Properties().tab(reforgedTimes == 0 ? CreativeModeTab.TAB_MISC : null).stacksTo(1));
        this.reforgedTimes = reforgedTimes;
    }

    @Override
    public @NotNull String getOrCreateDescriptionId() {
        return "item." + TokanoCreationsMod.MOD_ID + ".sword_shape";
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, list, tooltipFlag);

        list.add(Component.translatable("item." + TokanoCreationsMod.MOD_ID + ".sword_shape.tooltip[0]").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC));
        list.add(Component.translatable("item." + TokanoCreationsMod.MOD_ID + ".sword_shape.tooltip[1]", REINFORCE_COOK_TIMES - ((SwordShapeItem) itemStack.getItem()).reforgedTimes).withStyle(ChatFormatting.DARK_GREEN));
    }
}
