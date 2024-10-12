package net.toshayo.tokanocreations;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("unused")
public class ModItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(TokanoCreationsMod.MOD_ID, Registry.ITEM_REGISTRY);

    public static final RegistrySupplier<Item> SWORD_SHAPE = ITEMS.register(new ResourceLocation(TokanoCreationsMod.MOD_ID, "sword_shape"), () -> new SwordShapeItem(0));
    public static final RegistrySupplier<Item> SWORD_SHAPE_R0 = ITEMS.register(new ResourceLocation(TokanoCreationsMod.MOD_ID, "sword_shape_reforged0"), () -> new SwordShapeItem(1));
    public static final RegistrySupplier<Item> SWORD_SHAPE_R1 = ITEMS.register(new ResourceLocation(TokanoCreationsMod.MOD_ID, "sword_shape_reforged1"), () -> new SwordShapeItem(2));
    public static final RegistrySupplier<Item> SWORD_SHAPE_REINFORCED = ITEMS.register(new ResourceLocation(TokanoCreationsMod.MOD_ID, "sword_shape_reinforced"), () -> new Item(new Item.Properties().stacksTo(1)) {
        @Override
        public @NotNull Rarity getRarity(ItemStack itemStack) {
            return Rarity.RARE;
        }

        @Override
        public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
            super.appendHoverText(itemStack, level, list, tooltipFlag);
            list.add(Component.translatable("item." + TokanoCreationsMod.MOD_ID + ".sword_shape_reinforced.tooltip[0]").withStyle(ChatFormatting.DARK_PURPLE).withStyle(ChatFormatting.ITALIC));
        }
    });
    public static final RegistrySupplier<Item> SWORD = ITEMS.register(new ResourceLocation(TokanoCreationsMod.MOD_ID, "ideal_sword"), IdealSwordItem::new);
    public static final RegistrySupplier<Item> HOLY_CORE = ITEMS.register(new ResourceLocation(TokanoCreationsMod.MOD_ID, "holy_core"), () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)) {
        @Override
        public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
            super.appendHoverText(itemStack, level, list, tooltipFlag);
            list.add(
                    Component.translatable("item." + TokanoCreationsMod.MOD_ID + ".holy_core.tooltip[0]")
                            .withStyle(ChatFormatting.DARK_PURPLE)
                            .withStyle(ChatFormatting.ITALIC)
            );
        }
    });
    public static final RegistrySupplier<Item> IDEAL_HAIR = ITEMS.register(new ResourceLocation(TokanoCreationsMod.MOD_ID, "ideal_hair_strand"), () -> new Item(new Item.Properties()));
    public static final RegistrySupplier<Item> IDEAL_ORIGINAL_HAIR = ITEMS.register(new ResourceLocation(TokanoCreationsMod.MOD_ID, "ideal_original_hair_strand"), () -> new Item(new Item.Properties()));

    public static final RegistrySupplier<Item> TAIYAKI_RAW = ITEMS.register(new ResourceLocation(TokanoCreationsMod.MOD_ID, "taiyaki_raw"), () -> new Item(
            new Item.Properties()
                    .tab(CreativeModeTab.TAB_FOOD)
    ));
    public static final RegistrySupplier<Item> TAIYAKI = ITEMS.register(new ResourceLocation(TokanoCreationsMod.MOD_ID, "taiyaki"), () -> new Item(
            new Item.Properties()
                    .tab(CreativeModeTab.TAB_FOOD)
                    .stacksTo(16)
                    .food(new FoodProperties.Builder()
                            .nutrition(6)
                            .saturationMod(0.6F).build()
                    )
    ));

    public static void init() {
        TokanoCreationsMod.LOGGER.info("Registering items");
        ITEMS.register();
    }
}
