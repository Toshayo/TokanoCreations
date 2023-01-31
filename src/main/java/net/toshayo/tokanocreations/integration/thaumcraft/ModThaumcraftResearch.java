package net.toshayo.tokanocreations.integration.thaumcraft;

import net.minecraft.item.ItemStack;
import net.toshayo.tokanocreations.TokanoCreationsMod;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class ModThaumcraftResearch {
	public static String TOKANO_CREATIONS_RESEARCH_KEY = "TOKANO_CREATIONS";

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
				new ResearchPage(ThaumcraftPlugin.SWORD_SHAPE_RECIPE),
				new ResearchPage(ThaumcraftPlugin.HOLY_CORE_RECIPE),
				new ResearchPage(ThaumcraftPlugin.ORIGINAL_IDEAL_HAIR_RECIPE),
				new ResearchPage(ThaumcraftPlugin.SWORD_RECIPE)
				)
				.registerResearchItem();
	}
}
