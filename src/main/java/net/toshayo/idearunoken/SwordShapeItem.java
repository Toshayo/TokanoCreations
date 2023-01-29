package net.toshayo.idearunoken;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

public class SwordShapeItem extends Item {
	public SwordShapeItem() {
		setUnlocalizedName(IdearuNoKen.MOD_ID + ".sword_shape");
		setMaxStackSize(1);
		setTextureName("iron_sword");
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.uncommon;
	}

	@Override
	public boolean getHasSubtypes() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
		list.add(EnumChatFormatting.DARK_PURPLE.toString() + EnumChatFormatting.ITALIC + I18n.format("item.idearunoken.sword_shape.tooltip[0]"));
		list.add(EnumChatFormatting.DARK_GREEN + I18n.format("item.idearunoken.sword_shape.tooltip[1]", 9 - getDamage(stack)));
	}
}
