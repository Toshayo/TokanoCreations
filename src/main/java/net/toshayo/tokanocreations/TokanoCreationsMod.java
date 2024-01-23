package net.toshayo.tokanocreations;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLFingerprintViolationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.toshayo.tokanocreations.integration.travellersgear.TravellersGearIntegration;
import net.toshayo.tokanocreations.item.*;
import net.toshayo.tokanocreations.proxy.CommonProxy;

import java.util.List;

@Mod(
		modid = TokanoCreationsMod.MOD_ID,
		name = TokanoCreationsMod.NAME,
		version = TokanoCreationsMod.VERSION,
		dependencies = "after:Thaumcraft@[4.2.3.5,);after:TravellersGear@[1.16.6,);after:baubles@[1.0.1.10,)",
		acceptedMinecraftVersions = "1.7.10",
		certificateFingerprint = "ee4beef430d574ba7d8c096a4f7f9c6c755bd30f"
)
public class TokanoCreationsMod {
	public static final String MOD_ID = "tokanocreations";
	public static final String NAME = "Tokano Creations Mod";
	public static final String VERSION = "@@VERSION@@";

	@SidedProxy(serverSide = "net.toshayo.tokanocreations.proxy.CommonProxy", clientSide = "net.toshayo.tokanocreations.proxy.ClientProxy")
	public static CommonProxy proxy;

	public static final Item SWORD_SHAPE = new SwordShapeItem();

	public static final Item SWORD_SHAPE_REINFORCED = new Item() {
		@Override
		public EnumRarity getRarity(ItemStack stack) {
			return EnumRarity.rare;
		}

		@Override
		public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
			//noinspection unchecked
			list.add(EnumChatFormatting.DARK_PURPLE.toString() + EnumChatFormatting.ITALIC + I18n.format("item." + MOD_ID + ".sword_shape_reinforced.tooltip[0]"));
		}
	}.setUnlocalizedName(MOD_ID + ".sword_shape_reinforced").setMaxStackSize(1).setTextureName("iron_sword");

	public static final Item SWORD = new SwordItem().setTextureName("iron_sword");

	public static final Item IDEAL_HAIR = new IdealHairItem();
	public static final Item CROSS = new CrossItem().setTextureName("iron_ingot");

	public static final Item HOLY_CORE = new Item() {
		@SuppressWarnings("unchecked")
		@Override
		public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
			list.add(EnumChatFormatting.DARK_PURPLE.toString() + EnumChatFormatting.ITALIC + I18n.format("item." + MOD_ID + ".holy_core.tooltip[0]"));
		}
	}.setUnlocalizedName(MOD_ID + ".holy_core").setTextureName(MOD_ID + ":holy_core");

	public static final Item TAIYAKI_RAW = new Item().setCreativeTab(CreativeTabs.tabFood).setUnlocalizedName(MOD_ID + ".taiyaki_raw").setTextureName(MOD_ID + ":taiyaki_raw");
	public static final Item TAIYAKI = new TaiyakiItem().setTextureName(MOD_ID + ":taiyaki");
	public static Item TITLE_FAN;

	@Mod.EventHandler
	public void onPreInit(FMLPreInitializationEvent event) {
		GameRegistry.registerItem(SWORD, SWORD.getUnlocalizedName());
		GameRegistry.registerItem(SWORD_SHAPE, SWORD_SHAPE.getUnlocalizedName());
		GameRegistry.registerItem(SWORD_SHAPE_REINFORCED, SWORD_SHAPE_REINFORCED.getUnlocalizedName());
		GameRegistry.registerItem(IDEAL_HAIR, IDEAL_HAIR.getUnlocalizedName());
		GameRegistry.registerItem(HOLY_CORE, HOLY_CORE.getUnlocalizedName());
		GameRegistry.registerItem(CROSS, CROSS.getUnlocalizedName());
		GameRegistry.registerItem(TAIYAKI, TAIYAKI.getUnlocalizedName());
		GameRegistry.registerItem(TAIYAKI_RAW, TAIYAKI_RAW.getUnlocalizedName());

		if(Loader.isModLoaded("TravellersGear")) {
			TravellersGearIntegration.onPreInit();
		}
		proxy.onPreInit();
	}

	@Mod.EventHandler
	public void onPostInit(FMLPostInitializationEvent event) {
		proxy.onPostInit();
	}

	@Mod.EventHandler
	public static void onFingerPrintViolation(FMLFingerprintViolationEvent event) {
		if (!event.isDirectory) {
			System.err.println("A file failed to match with the signing key.");
			System.err.println("If you *know* this is a homebrew/custom build then this is expected, carry on.");
			System.err.println("Otherwise, you might want to redownload this mod from the *official* CurseForge page.");
		}
	}
}
