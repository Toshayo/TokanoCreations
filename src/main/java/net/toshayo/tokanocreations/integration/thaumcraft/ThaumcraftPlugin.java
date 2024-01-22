package net.toshayo.tokanocreations.integration.thaumcraft;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.toshayo.tokanocreations.TokanoCreationsMod;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.common.config.ConfigItems;

public class ThaumcraftPlugin {
	public static ShapedArcaneRecipe SWORD_SHAPE_RECIPE;
	public static ShapedArcaneRecipe HOLY_CORE_RECIPE;
	public static CrucibleRecipe ORIGINAL_IDEAL_HAIR_RECIPE;
	public static InfusionRecipe SWORD_RECIPE;
	public static InfusionRecipe CROSS_RECIPE;

	public static void postInit() {
		ThaumcraftApi.registerObjectTag(
				new ItemStack(TokanoCreationsMod.SWORD_SHAPE),
				new AspectList()
						.add(Aspect.WEAPON, 5)
						.add(Aspect.METAL, 40)
		);
		ThaumcraftApi.registerObjectTag(
				new ItemStack(TokanoCreationsMod.SWORD),
				new AspectList()
						.add(Aspect.WEAPON, 38)
						.add(Aspect.FLIGHT, 36)
						.add(Aspect.ENERGY, 36)
						.add(Aspect.LIGHT, 18)
						.add(Aspect.DARKNESS, 18)
						.add(Aspect.ELDRITCH, 18)
						.add(Aspect.LIFE, 18)
						.add(Aspect.DEATH, 18)
						.add(Aspect.METAL, 30)
		);
		ThaumcraftApi.registerObjectTag(
				new ItemStack(TokanoCreationsMod.SWORD_SHAPE_REINFORCED),
				new AspectList()
						.add(Aspect.WEAPON, 10)
						.add(Aspect.ENERGY, 22)
						.add(Aspect.FIRE, 10)
						.add(Aspect.METAL, 33)
		);
		ThaumcraftApi.registerObjectTag(
				new ItemStack(TokanoCreationsMod.IDEAL_HAIR),
				new AspectList()
						.add(Aspect.LIGHT, 40)
						.add(Aspect.DARKNESS, 40)
						.add(Aspect.LIFE, 40)
						.add(Aspect.DEATH, 40)
		);
		ThaumcraftApi.registerObjectTag(
				new ItemStack(TokanoCreationsMod.IDEAL_HAIR, 1, 1),
				new AspectList()
						.add(Aspect.CLOTH, 5)
						.add(Aspect.AIR, 5)
						.add(Aspect.AURA, 8)
						.add(Aspect.LIGHT, 3)
						.add(Aspect.DARKNESS, 6)
		);
		ThaumcraftApi.registerObjectTag(
				new ItemStack(TokanoCreationsMod.CROSS),
				new AspectList()
						.add(Aspect.ARMOR, 21)
						.add(Aspect.LIGHT, 14)
						.add(Aspect.DARKNESS, 10)
						.add(Aspect.LIFE, 12)
						.add(Aspect.DEATH, 9)
		);
		ModThaumcraftResearch.init();
	}

	public static void registerCrafts() {
		SWORD_SHAPE_RECIPE = ThaumcraftApi.addArcaneCraftingRecipe(
				ModThaumcraftResearch.TOKANO_CREATIONS_RESEARCH_KEY,
				new ItemStack(TokanoCreationsMod.SWORD_SHAPE),
				new AspectList()
						.add(Aspect.AIR, 3)
						.add(Aspect.EARTH, 10)
						.add(Aspect.FIRE, 5),
				"B", "B", "I",
				'B', new ItemStack(Blocks.iron_block),
				'I', new ItemStack(Items.iron_ingot)
		);
		HOLY_CORE_RECIPE = ThaumcraftApi.addArcaneCraftingRecipe(
				ModThaumcraftResearch.TOKANO_CREATIONS_RESEARCH_KEY,
				new ItemStack(TokanoCreationsMod.HOLY_CORE),
				new AspectList()
						.add(Aspect.AIR, 20)
						.add(Aspect.FIRE, 20)
						.add(Aspect.ORDER, 20),
				"IFI", "FAF", "IFI",
				'I', new ItemStack(Items.gold_ingot),
				'F', new ItemStack(Items.feather),
				'A', ThaumcraftPlugin.getAmuletStack()
		);
		ORIGINAL_IDEAL_HAIR_RECIPE = ThaumcraftApi.addCrucibleRecipe(
				ModThaumcraftResearch.TOKANO_CREATIONS_RESEARCH_KEY,
				new ItemStack(TokanoCreationsMod.IDEAL_HAIR, 1, 1),
				new ItemStack(TokanoCreationsMod.IDEAL_HAIR),
				new AspectList()
						.add(Aspect.EXCHANGE, 16)
						.add(Aspect.LIFE, 8)
		);
		SWORD_RECIPE = ThaumcraftApi.addInfusionCraftingRecipe(
				ModThaumcraftResearch.TOKANO_CREATIONS_RESEARCH_KEY,
				new ItemStack(TokanoCreationsMod.SWORD),
				10,
				new AspectList()
						.add(Aspect.MAGIC, 256)
						.add(Aspect.LIGHT, 128)
						.add(Aspect.DARKNESS, 128)
						.add(Aspect.LIFE, 128)
						.add(Aspect.DEATH, 128)
						.add(Aspect.ENERGY, 64)
						.add(Aspect.METAL, 64)
						.add(Aspect.WEAPON, 32),
				new ItemStack(TokanoCreationsMod.SWORD_SHAPE_REINFORCED),
				new ItemStack[] {
						new ItemStack(Items.feather),
						new ItemStack(TokanoCreationsMod.IDEAL_HAIR, 1, 1),
						new ItemStack(Items.feather),
						new ItemStack(TokanoCreationsMod.HOLY_CORE),
						new ItemStack(Items.feather),
						new ItemStack(TokanoCreationsMod.IDEAL_HAIR)
				}
		);
		CROSS_RECIPE = ThaumcraftApi.addInfusionCraftingRecipe(
				ModThaumcraftResearch.TOKANO_CREATIONS_RESEARCH_KEY,
				new ItemStack(TokanoCreationsMod.CROSS),
				3,
				new AspectList()
						.add(Aspect.MAGIC, 32)
						.add(Aspect.LIGHT, 64)
						.add(Aspect.DARKNESS, 48)
						.add(Aspect.LIFE, 16)
						.add(Aspect.DEATH, 24)
						.add(Aspect.ENERGY, 64)
						.add(Aspect.ARMOR, 32),
				new ItemStack(ConfigItems.itemAmuletRunic, 1, 0),
				new ItemStack[] {
						new ItemStack(TokanoCreationsMod.HOLY_CORE),
						ConfigItems.WAND_ROD_OBSIDIAN.getItem(),
						ConfigItems.STAFF_ROD_OBSIDIAN.getItem(),
						ConfigItems.WAND_ROD_OBSIDIAN.getItem(),
				}
		);
	}

	private static ItemStack getAmuletStack() {
		return new ItemStack(ConfigItems.itemResource, 1, 15);
	}
}
