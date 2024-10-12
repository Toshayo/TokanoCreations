package net.toshayo.tokanocreations;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;

public class IdealSwordItem extends SwordItem {
    public IdealSwordItem() {
        super(Tiers.NETHERITE, 45, -2.5F, new Properties().tab(CreativeModeTab.TAB_COMBAT).rarity(Rarity.EPIC).durability(-1));
    }

    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
        target.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 2, false, false));
        target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 3, false, false));
        return super.hurtEnemy(itemStack, target, attacker);
    }
}
