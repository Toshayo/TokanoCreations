package net.toshayo.idearunoken;

import com.google.common.collect.Multimap;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class SwordItem extends ItemSword {
	public SwordItem() {
		super(ToolMaterial.EMERALD);
		setUnlocalizedName(IdearuNoKen.MOD_ID + ".sword");
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.epic;
	}

	@Override
	public float func_150931_i() {
		return 40F;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public Multimap<?, ?> getItemAttributeModifiers() {
		Multimap multimap = super.getItemAttributeModifiers();
		multimap.removeAll(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName());
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 4 + func_150931_i(), 0));
		return multimap;
	}
}
