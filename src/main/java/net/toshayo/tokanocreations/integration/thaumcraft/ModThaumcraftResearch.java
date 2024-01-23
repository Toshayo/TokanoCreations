package net.toshayo.tokanocreations.integration.thaumcraft;

import cpw.mods.fml.common.Loader;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.toshayo.tokanocreations.TokanoCreationsMod;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class ModThaumcraftResearch {
	public static final String TOKANO_CREATIONS_IDEAL_RESEARCH_KEY = "TOKANO_CREATIONS_IDEAL";
	public static final String TOKANO_CREATIONS_HAIR_SETTING_RESEARCH_KEY = "TOKANO_CREATIONS_HAIR_SETTING";
	public static final String TOKANO_CREATIONS_SWORD_RESEARCH_KEY = "TOKANO_CREATIONS_SWORD";
	public static final String TOKANO_CREATIONS_BAUBLES_RESEARCH_KEY = "TOKANO_CREATIONS_BAUBLES";
	public static final String TOKANO_CREATIONS_SWEETS_RESEARCH_KEY = "TOKANO_CREATIONS_SWEETS";
	public static final String TOKANO_CREATIONS_FAN_RESEARCH_KEY = "TOKANO_CREATIONS_FAN";

	public static void init() {
		registerResearch();
	}

	public static void registerResearch() {
		new ResearchItem(
				TOKANO_CREATIONS_IDEAL_RESEARCH_KEY, "BASICS",
				new AspectList()
						.add(Aspect.LIGHT, 8)
						.add(Aspect.DARKNESS, 8)
						.add(Aspect.LIFE, 12)
						.add(Aspect.FLIGHT, 16)
						.add(Aspect.SENSES, 9)
						.add(Aspect.ORDER, 7),
				7, -6, 7,
				Aspect.FLIGHT.getImage()
		)
				.setPages(
						new ResearchPage("tc.research_page." + TokanoCreationsMod.MOD_ID + "." + TOKANO_CREATIONS_IDEAL_RESEARCH_KEY + ".1"),
						new ResearchPage("tc.research_page." + TokanoCreationsMod.MOD_ID + "." + TOKANO_CREATIONS_IDEAL_RESEARCH_KEY + ".2"),
						new ResearchPage(ThaumcraftIntegration.HOLY_CORE_RECIPE)
				)
				.setSpecial()
				.registerResearchItem();

		new ResearchItem(
				TOKANO_CREATIONS_HAIR_SETTING_RESEARCH_KEY, "BASICS",
				new AspectList()
						.add(Aspect.LIGHT, 6)
						.add(Aspect.DARKNESS, 6)
						.add(Aspect.LIFE, 7)
						.add(Aspect.DEATH, 5)
						.add(Aspect.EXCHANGE, 5),
				9, -7, 5,
				new ItemStack(TokanoCreationsMod.IDEAL_HAIR)
		)
				.setPages(
						new ResearchPage("tc.research_page." + TokanoCreationsMod.MOD_ID + "." + TOKANO_CREATIONS_HAIR_SETTING_RESEARCH_KEY + ".1"),
						new ResearchPage("tc.research_page." + TokanoCreationsMod.MOD_ID + "." + TOKANO_CREATIONS_HAIR_SETTING_RESEARCH_KEY + ".2"),
						new ResearchPage("tc.research_page." + TokanoCreationsMod.MOD_ID + "." + TOKANO_CREATIONS_HAIR_SETTING_RESEARCH_KEY + ".3"),
						new ResearchPage(ThaumcraftIntegration.ORIGINAL_IDEAL_HAIR_RECIPE)
				)
				.setParents(TOKANO_CREATIONS_IDEAL_RESEARCH_KEY)
				.registerResearchItem();

		new ResearchItem(
				TOKANO_CREATIONS_SWORD_RESEARCH_KEY, "BASICS",
				new AspectList()
						.add(Aspect.WEAPON, 16)
						.add(Aspect.LIGHT, 8)
						.add(Aspect.DARKNESS, 8)
						.add(Aspect.LIFE, 4)
						.add(Aspect.DEATH, 10),
				11, -7, 6,
				new ItemStack(TokanoCreationsMod.SWORD)
		)
				.setParents(TOKANO_CREATIONS_HAIR_SETTING_RESEARCH_KEY)
				.setPages(
						new ResearchPage("tc.research_page." + TokanoCreationsMod.MOD_ID + "." + TOKANO_CREATIONS_SWORD_RESEARCH_KEY + ".1"),
						new ResearchPage(ThaumcraftIntegration.SWORD_SHAPE_RECIPE),
						new ResearchPage(ThaumcraftIntegration.SWORD_RECIPE)
				)
				.registerResearchItem();

		new ResearchItem(
				TOKANO_CREATIONS_BAUBLES_RESEARCH_KEY, "BASICS",
				new AspectList()
						.add(Aspect.LIGHT, 6)
						.add(Aspect.DARKNESS, 7)
						.add(Aspect.LIFE, 10)
						.add(Aspect.DEATH, 10),
				10, -6, 4,
				new ItemStack(TokanoCreationsMod.CROSS)
		)
				.setPages(
						new ResearchPage("tc.research_page." + TokanoCreationsMod.MOD_ID + "." + TOKANO_CREATIONS_BAUBLES_RESEARCH_KEY + ".1"),
						new ResearchPage(ThaumcraftIntegration.CROSS_RECIPE)
				)
				.setParents(TOKANO_CREATIONS_IDEAL_RESEARCH_KEY)
				.registerResearchItem();

		new ResearchItem(
				TOKANO_CREATIONS_SWEETS_RESEARCH_KEY, "BASICS",
				new AspectList()
						.add(Aspect.HUNGER, 10)
						.add(Aspect.SENSES, 16)
						.add(Aspect.MOTION, 5),
				9, -5, 3,
				new ItemStack(TokanoCreationsMod.TAIYAKI)
		)
				.setPages(
						new ResearchPage("tc.research_page." + TokanoCreationsMod.MOD_ID + "." + TOKANO_CREATIONS_SWEETS_RESEARCH_KEY + ".1"),
						new ResearchPage(ThaumcraftIntegration.TAIYAKI_RAW_RECIPE),
						new ResearchPage(new ItemStack(TokanoCreationsMod.TAIYAKI_RAW))
				)
				.setParents(TOKANO_CREATIONS_IDEAL_RESEARCH_KEY)
				.registerResearchItem();

		if(Loader.isModLoaded("TravellersGear")) {
			new ResearchItem(
					TOKANO_CREATIONS_FAN_RESEARCH_KEY, "BASICS",
					new AspectList()
							.add(Aspect.MAN, 8)
							.add(Aspect.SENSES, 32)
							.add(Aspect.SOUL, 4),
					9, -3, 0,
					new ResourceLocation("travellersgear", "textures/items/simplegear_title.png")
			)
					.setPages(
							new ResearchPage(ThaumcraftIntegration.TITLE_FAN_RECIPE)
					)
					.setConcealed()
					.setSecondary()
					.setParents(TOKANO_CREATIONS_SWORD_RESEARCH_KEY, TOKANO_CREATIONS_BAUBLES_RESEARCH_KEY)
					.registerResearchItem();
		}
	}
}
