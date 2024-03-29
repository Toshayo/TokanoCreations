package net.toshayo.tokanocreations.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.toshayo.tokanocreations.TokanoCreationsMod;

public class AnvilEventHandler {
	@SuppressWarnings("unused")
	@SubscribeEvent
	public void onAnvilUpdate(AnvilUpdateEvent event) {
		if(event.left.getItem() == TokanoCreationsMod.IDEAL_HAIR && event.left.getItemDamage() == 0 && event.right.getItem() == Items.clock) {
			event.output = new ItemStack(TokanoCreationsMod.IDEAL_HAIR, 1, 1);
			event.materialCost = 1;
			event.cost = 30;
		}
	}
}
