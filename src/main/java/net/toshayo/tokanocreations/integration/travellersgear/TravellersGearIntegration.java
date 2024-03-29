package net.toshayo.tokanocreations.integration.travellersgear;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.toshayo.tokanocreations.TokanoCreationsMod;
import net.toshayo.tokanocreations.integration.thaumcraft.ModThaumcraftResearch;
import net.toshayo.tokanocreations.integration.thaumcraft.ThaumcraftIntegration;
import net.toshayo.tokanocreations.item.FanTitleItem;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class TravellersGearIntegration {
    public static void onPreInit() {
        TokanoCreationsMod.TITLE_FAN = new FanTitleItem().setTextureName("travellersgear:simplegear_title");
        GameRegistry.registerItem(TokanoCreationsMod.TITLE_FAN, "item." + TokanoCreationsMod.MOD_ID + ".title_fan");
    }

    public static void registerThaumcraftCrafts() {
        ItemStack stack = new ItemStack(TokanoCreationsMod.TITLE_FAN);
        stack.setTagCompound(new NBTTagCompound());
        stack.getTagCompound().setString("title", TokanoCreationsMod.MOD_ID + ".title_fan.title");

        ThaumcraftIntegration.TITLE_FAN_RECIPE = ThaumcraftApi.addInfusionCraftingRecipe(
                ModThaumcraftResearch.TOKANO_CREATIONS_FAN_RESEARCH_KEY,
                stack,
                2,
                new AspectList()
                        .add(Aspect.MAN, 16)
                        .add(Aspect.SENSES, 24)
                        .add(Aspect.MIND, 20)
                        .add(Aspect.ENERGY, 10),
                new ItemStack(Items.book),
                new ItemStack[] {
                        new ItemStack(Items.paper),
                        new ItemStack(TokanoCreationsMod.SWORD),
                        new ItemStack(Items.paper),
                        new ItemStack(Items.paper),
                        new ItemStack(TokanoCreationsMod.CROSS),
                        new ItemStack(Items.paper),
                }
        );
    }

    public static void registerMundaneCrafts() {
        GameRegistry.addShapedRecipe(
                new ItemStack(TokanoCreationsMod.TITLE_FAN),
                "DRD", "PBP", "LLL",
                'D', new ItemStack(Items.diamond),
                'R', new ItemStack(Items.dye, 1, 1),
                'B', new ItemStack(Items.book),
                'P', new ItemStack(Items.paper),
                'L', new ItemStack(Items.dye, 1, 4)
        );
    }
}
