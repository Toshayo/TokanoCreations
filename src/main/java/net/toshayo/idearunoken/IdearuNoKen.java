package net.toshayo.idearunoken;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
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

import java.util.List;

// TODO : add source for original hair strand
// TODO : translate to ja_JP
// TODO : add descriptions
// TODO : if needed, hide some recipes from NEI

@Mod(
	modid = IdearuNoKen.MOD_ID,
	name = IdearuNoKen.NAME,
	version = IdearuNoKen.VERSION
)
public class IdearuNoKen {
	public static final String MOD_ID = "idearunoken";
	public static final String NAME = "IdearuNoKen";
	public static final String VERSION = "1.0.0";

	public static final Item SWORD_SHAPE = new SwordShapeItem();

	public static final Item SWORD_SHAPE_REINFORCED = new Item() {
		@Override
		public EnumRarity getRarity(ItemStack stack) {
			return EnumRarity.rare;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
			list.add(EnumChatFormatting.DARK_PURPLE.toString() + EnumChatFormatting.ITALIC + I18n.format("item.idearunoken.sword_shape_reinforced.tooltip[0]"));
		}
	}.setUnlocalizedName(MOD_ID + ".sword_shape_reinforced").setMaxStackSize(1).setTextureName("iron_sword");

	public static final Item SWORD = new SwordItem().setTextureName("iron_sword");

	public static final Item IDEAL_HAIR = new IdealHairItem();

	public static final Item HOLY_CORE = new Item() {
		@SuppressWarnings("unchecked")
		@Override
		public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
			list.add(EnumChatFormatting.DARK_PURPLE.toString() + EnumChatFormatting.ITALIC + I18n.format("item.idearunoken.holy_core.tooltip[0]"));
		}
	}.setUnlocalizedName(MOD_ID + ".holy_core").setTextureName(MOD_ID + ":holy_core");

	
	public IdearuNoKen() {
	
	}

	@SuppressWarnings("unused")
	@EventHandler
	public void onPreInit(FMLPreInitializationEvent event) {
		GameRegistry.registerItem(SWORD_SHAPE, SWORD_SHAPE.getUnlocalizedName());
		GameRegistry.registerItem(SWORD, SWORD.getUnlocalizedName());
		GameRegistry.registerItem(SWORD_SHAPE_REINFORCED, SWORD_SHAPE_REINFORCED.getUnlocalizedName());
		GameRegistry.registerItem(IDEAL_HAIR, IDEAL_HAIR.getUnlocalizedName());
		GameRegistry.registerItem(HOLY_CORE, HOLY_CORE.getUnlocalizedName());
		MinecraftForgeClient.registerItemRenderer(SWORD_SHAPE, new SwordRenderer());
		MinecraftForgeClient.registerItemRenderer(SWORD_SHAPE_REINFORCED, new SwordRenderer());
		MinecraftForgeClient.registerItemRenderer(SWORD, new SwordRenderer());

		registerCrafts();
	}

	@SuppressWarnings("unused")
	public void onPostInit(FMLPostInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(PlayerEventHandler.class);
	}
	
	private void registerCrafts() {
		GameRegistry.addShapedRecipe(new ItemStack(SWORD_SHAPE),
				"B", "B", "I",
				'B', new ItemStack(Blocks.iron_block),
				'I', new ItemStack(Items.iron_ingot)
		);
		for(int i = 0; i < 8; i++)
			GameRegistry.addSmelting(new ItemStack(SWORD_SHAPE, 1, i), new ItemStack(SWORD_SHAPE, 1, i + 1), 100);
		GameRegistry.addSmelting(new ItemStack(SWORD_SHAPE, 1, 8), new ItemStack(SWORD_SHAPE_REINFORCED), 100);
		GameRegistry.addShapedRecipe(new ItemStack(SWORD),
				" F ", "HSO", "FCF",
				'F', new ItemStack(Items.feather),
				'H', new ItemStack(IDEAL_HAIR, 1, 0),
				'S', new ItemStack(SWORD_SHAPE_REINFORCED),
				'O', new ItemStack(IDEAL_HAIR, 1, 1),
				'C', new ItemStack(HOLY_CORE)
		);
		GameRegistry.addShapedRecipe(new ItemStack(HOLY_CORE),
				"IFI", "FAF", "IFI",
				'I', new ItemStack(Items.gold_ingot),
				'F', new ItemStack(Items.feather),
				'A', new ItemStack(Items.golden_apple)
		);
	}
}
