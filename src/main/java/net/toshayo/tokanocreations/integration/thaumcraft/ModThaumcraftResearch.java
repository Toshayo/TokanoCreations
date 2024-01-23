package net.toshayo.tokanocreations.integration.thaumcraft;

import net.minecraft.item.ItemStack;
import net.toshayo.tokanocreations.TokanoCreationsMod;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class ModThaumcraftResearch {
	public static final String TOKANO_CREATIONS_RESEARCH_KEY = "TOKANO_CREATIONS";
	public static final String TOKANO_CREATIONS_SWORD_RESEARCH_KEY = "TOKANO_CREATIONS_SWORD";
	public static final String TOKANO_CREATIONS_BAUBLES_RESEARCH_KEY = "TOKANO_CREATIONS_BAUBLES";
	public static final String TOKANO_CREATIONS_SWEETS_RESEARCH_KEY = "TOKANO_CREATIONS_SWEETS";

	public static void init() {
		registerResearch();
	}

	public static void registerResearch() {
		new ResearchItem(
				TOKANO_CREATIONS_RESEARCH_KEY, "BASICS",
				new AspectList()
						.add(Aspect.LIGHT, 1)
						.add(Aspect.DARKNESS, 1)
						.add(Aspect.LIFE, 1)
						.add(Aspect.DEATH, 1)
						.add(Aspect.EXCHANGE, 1),
				6, -4, 5,
				new ItemStack(TokanoCreationsMod.IDEAL_HAIR)
		).setPages(
				new ResearchPage("tc.research_page." + TokanoCreationsMod.MOD_ID + "." + TOKANO_CREATIONS_RESEARCH_KEY + ".1"),
				new ResearchPage("tc.research_page." + TokanoCreationsMod.MOD_ID + "." + TOKANO_CREATIONS_RESEARCH_KEY + ".2"),
				new ResearchPage("tc.research_page." + TokanoCreationsMod.MOD_ID + "." + TOKANO_CREATIONS_RESEARCH_KEY + ".3"),
				new ResearchPage(ThaumcraftPlugin.ORIGINAL_IDEAL_HAIR_RECIPE),
				new ResearchPage(ThaumcraftPlugin.HOLY_CORE_RECIPE)
				)
				.registerResearchItem();

		new ResearchItem(
				TOKANO_CREATIONS_SWORD_RESEARCH_KEY, "BASICS",
				new AspectList()
						.add(Aspect.WEAPON, 1)
						.add(Aspect.LIGHT, 1)
						.add(Aspect.DARKNESS, 1)
						.add(Aspect.LIFE, 1)
						.add(Aspect.DEATH, 1),
				7, -6, 6,
				new ItemStack(TokanoCreationsMod.SWORD)
		).setPages(
						new ResearchPage("tc.research_page." + TokanoCreationsMod.MOD_ID + "." + TOKANO_CREATIONS_RESEARCH_KEY + ".sword.1"),
						new ResearchPage(ThaumcraftPlugin.SWORD_SHAPE_RECIPE),
						new ResearchPage(ThaumcraftPlugin.SWORD_RECIPE)
				)
				.setParents(TOKANO_CREATIONS_RESEARCH_KEY)
				.registerResearchItem();

		new ResearchItem(
				TOKANO_CREATIONS_BAUBLES_RESEARCH_KEY, "BASICS",
				new AspectList()
						.add(Aspect.LIGHT, 1)
						.add(Aspect.DARKNESS, 1)
						.add(Aspect.LIFE, 1)
						.add(Aspect.DEATH, 1),
				8, -3, 4,
				new ItemStack(TokanoCreationsMod.CROSS)
		).setPages(
						new ResearchPage("tc.research_page." + TokanoCreationsMod.MOD_ID + "." + TOKANO_CREATIONS_RESEARCH_KEY + ".baubles.1"),
						new ResearchPage(ThaumcraftPlugin.CROSS_RECIPE)
				)
				.setParents(TOKANO_CREATIONS_RESEARCH_KEY)
				.registerResearchItem();

		new ResearchItem(
				TOKANO_CREATIONS_SWEETS_RESEARCH_KEY, "BASICS",
				new AspectList()
						.add(Aspect.HUNGER, 1)
						.add(Aspect.SENSES, 1)
						.add(Aspect.MOTION, 1),
				7, -1, 3,
				new ItemStack(TokanoCreationsMod.TAIYAKI)
		).setPages(
						new ResearchPage("tc.research_page." + TokanoCreationsMod.MOD_ID + "." + TOKANO_CREATIONS_RESEARCH_KEY + ".sweets.1"),
						new ResearchPage(ThaumcraftPlugin.TAIYAKI_RECIPE)
				)
				.setParents(TOKANO_CREATIONS_RESEARCH_KEY)
				.registerResearchItem();
	}
}
