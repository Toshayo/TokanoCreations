package net.toshayo.idearunoken.integration.thaumcraft;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.toshayo.idearunoken.IdearuNoKen;
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

	public static void postInit() {
		ThaumcraftApi.registerObjectTag(
				new ItemStack(IdearuNoKen.SWORD_SHAPE),
				new AspectList()
						.add(Aspect.WEAPON, 5)
						.add(Aspect.METAL, 40)
		);
		ThaumcraftApi.registerObjectTag(
				new ItemStack(IdearuNoKen.SWORD),
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
				new ItemStack(IdearuNoKen.SWORD_SHAPE_REINFORCED),
				new AspectList()
						.add(Aspect.WEAPON, 10)
						.add(Aspect.ENERGY, 22)
						.add(Aspect.FIRE, 10)
						.add(Aspect.METAL, 33)
		);
		ThaumcraftApi.registerObjectTag(
				new ItemStack(IdearuNoKen.IDEAL_HAIR),
				new AspectList()
						.add(Aspect.LIGHT, 40)
						.add(Aspect.DARKNESS, 40)
						.add(Aspect.LIFE, 40)
						.add(Aspect.DEATH, 40)
		);
		ThaumcraftApi.registerObjectTag(
				new ItemStack(IdearuNoKen.IDEAL_HAIR, 1, 1),
				new AspectList()
						.add(Aspect.CLOTH, 5)
						.add(Aspect.AIR, 5)
						.add(Aspect.AURA, 8)
						.add(Aspect.LIGHT, 3)
						.add(Aspect.DARKNESS, 6)
		);
		ModThaumcraftResearch.init();
	}

	public static ItemStack getAmuletStack() {
		return new ItemStack(ConfigItems.itemResource, 1, 15);
	}

	public static void registerCrafts() {
		SWORD_SHAPE_RECIPE = ThaumcraftApi.addArcaneCraftingRecipe(
				ModThaumcraftResearch.TOKANO_CREATIONS_RESEARCH_KEY,
				new ItemStack(IdearuNoKen.SWORD_SHAPE),
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
				new ItemStack(IdearuNoKen.HOLY_CORE),
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
				new ItemStack(IdearuNoKen.IDEAL_HAIR, 1, 1),
				new ItemStack(IdearuNoKen.IDEAL_HAIR),
				new AspectList()
						.add(Aspect.EXCHANGE, 16)
						.add(Aspect.LIFE, 8)
		);
		SWORD_RECIPE = ThaumcraftApi.addInfusionCraftingRecipe(
				ModThaumcraftResearch.TOKANO_CREATIONS_RESEARCH_KEY,
				new ItemStack(IdearuNoKen.SWORD),
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
				new ItemStack(IdearuNoKen.SWORD_SHAPE_REINFORCED),
				new ItemStack[] {
						new ItemStack(Items.feather),
						new ItemStack(IdearuNoKen.IDEAL_HAIR, 1, 1),
						new ItemStack(Items.feather),
						new ItemStack(IdearuNoKen.HOLY_CORE),
						new ItemStack(Items.feather),
						new ItemStack(IdearuNoKen.IDEAL_HAIR)
				}
		);
	}
}
