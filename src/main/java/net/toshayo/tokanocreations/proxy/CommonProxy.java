package net.toshayo.tokanocreations.proxy;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.toshayo.tokanocreations.TokanoCreationsMod;
import net.toshayo.tokanocreations.events.AnvilEventHandler;
import net.toshayo.tokanocreations.events.PlayerEventHandler;
import net.toshayo.tokanocreations.integration.thaumcraft.ThaumcraftIntegration;
import net.toshayo.tokanocreations.integration.travellersgear.TravellersGearIntegration;
import net.toshayo.tokanocreations.item.SwordShapeItem;

public class CommonProxy {
    public void onPreInit() {

    }

    public void onPostInit() {
        MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());

        boolean thaumcraftInstalled = Loader.isModLoaded("Thaumcraft");

        if(Loader.isModLoaded("TravellersGear")) {
            if(thaumcraftInstalled) {
                TravellersGearIntegration.registerThaumcraftCrafts();
            } else {
                TravellersGearIntegration.registerMundaneCrafts();
            }
        }

        registerCrafts(thaumcraftInstalled);

        if(thaumcraftInstalled) {
            ThaumcraftIntegration.postInit();
        } else {
            MinecraftForge.EVENT_BUS.register(new AnvilEventHandler());
        }
    }

    private void registerCrafts(boolean thaumcraftInstalled) {
        for(int i = 0; i < SwordShapeItem.REINFORCE_COOK_TIMES - 1; i++) {
            GameRegistry.addSmelting(new ItemStack(TokanoCreationsMod.SWORD_SHAPE, 1, i), new ItemStack(TokanoCreationsMod.SWORD_SHAPE, 1, i + 1), 100);
        }
        GameRegistry.addSmelting(new ItemStack(TokanoCreationsMod.SWORD_SHAPE, 1, SwordShapeItem.REINFORCE_COOK_TIMES - 1), new ItemStack(TokanoCreationsMod.SWORD_SHAPE_REINFORCED), 100);
        if(thaumcraftInstalled) {
            ThaumcraftIntegration.registerCrafts();
        } else {
            GameRegistry.addShapedRecipe(new ItemStack(TokanoCreationsMod.SWORD),
                    " F ", "HSO", "FCF",
                    'F', new ItemStack(Items.feather),
                    'H', new ItemStack(TokanoCreationsMod.IDEAL_HAIR, 1, 0),
                    'S', new ItemStack(TokanoCreationsMod.SWORD_SHAPE_REINFORCED),
                    'O', new ItemStack(TokanoCreationsMod.IDEAL_HAIR, 1, 1),
                    'C', new ItemStack(TokanoCreationsMod.HOLY_CORE)
            );
            GameRegistry.addShapedRecipe(new ItemStack(TokanoCreationsMod.SWORD_SHAPE),
                    "B", "B", "I",
                    'B', new ItemStack(Blocks.iron_block),
                    'I', new ItemStack(Items.iron_ingot)
            );
            GameRegistry.addShapedRecipe(new ItemStack(TokanoCreationsMod.HOLY_CORE),
                    "IFI", "FAF", "IFI",
                    'I', new ItemStack(Items.gold_ingot),
                    'F', new ItemStack(Items.feather),
                    'A', new ItemStack(Items.golden_apple)
            );
            GameRegistry.addShapedRecipe(
                    new ItemStack(TokanoCreationsMod.TAIYAKI_RAW),
                    " WW", "WCW", " WW",
                    'W', OreDictionary.doesOreNameExist("dustWheat") ? OreDictionary.getOres("dustWheat").get(0) : new ItemStack(Items.wheat),
                    'C', new ItemStack(Items.dye, 1, 3)
            );
            GameRegistry.addShapedRecipe(
                    new ItemStack(TokanoCreationsMod.CROSS),
                    " O ", "OCO", " O ",
                    'O', new ItemStack(Blocks.obsidian),
                    'C', new ItemStack(TokanoCreationsMod.HOLY_CORE)
            );
        }
        GameRegistry.addSmelting(TokanoCreationsMod.TAIYAKI_RAW, new ItemStack(TokanoCreationsMod.TAIYAKI), 2);
    }
}
