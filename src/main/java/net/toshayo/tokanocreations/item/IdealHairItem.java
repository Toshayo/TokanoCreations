package net.toshayo.tokanocreations.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.toshayo.tokanocreations.TokanoCreationsMod;

import java.util.List;

public class IdealHairItem extends Item {
	private IIcon originalHairIcon;

	public IdealHairItem() {
		setUnlocalizedName(TokanoCreationsMod.MOD_ID + ".ideal_hair_strand");
		setTextureName(TokanoCreationsMod.MOD_ID + ":ideal_hair_strand");
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		super.registerIcons(iconRegister);
		originalHairIcon = iconRegister.registerIcon(TokanoCreationsMod.MOD_ID + ":ideal_original_hair_strand");
	}

	@Override
	public IIcon getIconFromDamage(int damage) {
		return damage == 0 ? itemIcon : originalHairIcon;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return stack.getItemDamage() == 0 ? super.getUnlocalizedName(stack) : "item." + TokanoCreationsMod.MOD_ID + ".ideal_original_hair_strand";
	}

	@Override
	public boolean getHasSubtypes() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void getSubItems(Item item, CreativeTabs tabs, List list) {
		super.getSubItems(item, tabs, list);
		list.add(new ItemStack(item, 1, 1));
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.epic;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
		list.add(EnumChatFormatting.DARK_AQUA.toString() + EnumChatFormatting.ITALIC + I18n.format(getUnlocalizedName(stack) + ".tooltip[0]"));
	}
}
