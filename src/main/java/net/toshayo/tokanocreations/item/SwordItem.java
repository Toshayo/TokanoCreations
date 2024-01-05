package net.toshayo.tokanocreations.item;

import com.google.common.collect.Multimap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.toshayo.tokanocreations.TokanoCreationsMod;

public class SwordItem extends ItemSword {
	public SwordItem() {
		super(ToolMaterial.EMERALD);
		setUnlocalizedName(TokanoCreationsMod.MOD_ID + ".sword");
		setMaxDamage(-1);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.epic;
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase attacker, EntityLivingBase target) {
		target.addPotionEffect(new PotionEffect(Potion.wither.id, 100, 2, false));
		target.addPotionEffect(new PotionEffect(Potion.weakness.id, 100, 2, false));
		return super.hitEntity(stack, attacker, target);
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
