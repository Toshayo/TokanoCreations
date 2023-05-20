package net.toshayo.tokanocreations;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLFingerprintViolationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.toshayo.tokanocreations.integration.thaumcraft.ThaumcraftPlugin;

import java.util.List;

@Mod(
		modid = TokanoCreationsMod.MOD_ID,
		name = TokanoCreationsMod.NAME,
		version = TokanoCreationsMod.VERSION,
		dependencies = "after:Thaumcraft@[4.2.3.5,)",
		certificateFingerprint = "ee4beef430d574ba7d8c096a4f7f9c6c755bd30f"
)
public class TokanoCreationsMod {
	public static final String MOD_ID = "tokanocreations";
	public static final String NAME = "Tokano Creations Mod";
	public static final String VERSION = "1.2.2";

	public static final Item SWORD_SHAPE = new SwordShapeItem();

	public static final Item SWORD_SHAPE_REINFORCED = new Item() {
		@Override
		public EnumRarity getRarity(ItemStack stack) {
			return EnumRarity.rare;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
			list.add(EnumChatFormatting.DARK_PURPLE.toString() + EnumChatFormatting.ITALIC + I18n.format("item." + MOD_ID + ".sword_shape_reinforced.tooltip[0]"));
		}
	}.setUnlocalizedName(MOD_ID + ".sword_shape_reinforced").setMaxStackSize(1).setTextureName("iron_sword");

	public static final Item SWORD = new SwordItem().setTextureName("iron_sword");

	public static final Item IDEAL_HAIR = new IdealHairItem();

	public static final Item HOLY_CORE = new Item() {
		@SuppressWarnings("unchecked")
		@Override
		public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
			list.add(EnumChatFormatting.DARK_PURPLE.toString() + EnumChatFormatting.ITALIC + I18n.format("item." + MOD_ID + ".holy_core.tooltip[0]"));
		}
	}.setUnlocalizedName(MOD_ID + ".holy_core").setTextureName(MOD_ID + ":holy_core");

	
	public TokanoCreationsMod() {
	
	}

	@SuppressWarnings("unused")
	@Mod.EventHandler
	public void onPreInit(FMLPreInitializationEvent event) {
		GameRegistry.registerItem(SWORD, SWORD.getUnlocalizedName());
		GameRegistry.registerItem(SWORD_SHAPE, SWORD_SHAPE.getUnlocalizedName());
		GameRegistry.registerItem(SWORD_SHAPE_REINFORCED, SWORD_SHAPE_REINFORCED.getUnlocalizedName());
		GameRegistry.registerItem(IDEAL_HAIR, IDEAL_HAIR.getUnlocalizedName());
		GameRegistry.registerItem(HOLY_CORE, HOLY_CORE.getUnlocalizedName());
		MinecraftForgeClient.registerItemRenderer(SWORD_SHAPE, new SwordRenderer());
		MinecraftForgeClient.registerItemRenderer(SWORD_SHAPE_REINFORCED, new SwordRenderer());
		MinecraftForgeClient.registerItemRenderer(SWORD, new SwordRenderer());

		registerCrafts();
	}

	@SuppressWarnings("unused")
	@Mod.EventHandler
	public void onPostInit(FMLPostInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
		if(Loader.isModLoaded("Thaumcraft"))
			ThaumcraftPlugin.postInit();
		else
			MinecraftForge.EVENT_BUS.register(new AnvilEventHandler());
	}
	
	private void registerCrafts() {
		for(int i = 0; i < SwordShapeItem.REINFORCE_COOK_TIMES - 1; i++)
			GameRegistry.addSmelting(new ItemStack(SWORD_SHAPE, 1, i), new ItemStack(SWORD_SHAPE, 1, i + 1), 100);
		GameRegistry.addSmelting(new ItemStack(SWORD_SHAPE, 1, SwordShapeItem.REINFORCE_COOK_TIMES - 1), new ItemStack(SWORD_SHAPE_REINFORCED), 100);
		if(Loader.isModLoaded("Thaumcraft"))
			ThaumcraftPlugin.registerCrafts();
		else {
			GameRegistry.addShapedRecipe(new ItemStack(SWORD),
					" F ", "HSO", "FCF",
					'F', new ItemStack(Items.feather),
					'H', new ItemStack(IDEAL_HAIR, 1, 0),
					'S', new ItemStack(SWORD_SHAPE_REINFORCED),
					'O', new ItemStack(IDEAL_HAIR, 1, 1),
					'C', new ItemStack(HOLY_CORE)
			);
			GameRegistry.addShapedRecipe(new ItemStack(SWORD_SHAPE),
					"B", "B", "I",
					'B', new ItemStack(Blocks.iron_block),
					'I', new ItemStack(Items.iron_ingot)
			);
			GameRegistry.addShapedRecipe(new ItemStack(HOLY_CORE),
					"IFI", "FAF", "IFI",
					'I', new ItemStack(Items.gold_ingot),
					'F', new ItemStack(Items.feather),
					'A', new ItemStack(Items.golden_apple)
			);
		}
	}

	@SuppressWarnings("unused")
	@Mod.EventHandler
	public static void onFingerPrintViolation(FMLFingerprintViolationEvent event) {
		if (!event.isDirectory) {
			System.err.println("A file failed to match with the signing key.");
			System.err.println("If you *know* this is a homebrew/custom build then this is expected, carry on.");
			System.err.println("Otherwise, you might want to redownload this mod from the *official* CurseForge page.");
		}
	}
}
