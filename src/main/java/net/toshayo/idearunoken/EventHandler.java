package net.toshayo.idearunoken;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;

public class EventHandler {
	@SuppressWarnings("unused")
	@SubscribeEvent
	public void onWakeUp(PlayerWakeUpEvent event) {
		if(!event.wakeImmediatly && !event.entity.worldObj.isRemote && event.entity.worldObj.rand.nextFloat() <= 0.01) {
			int slot = event.entityPlayer.inventory.getFirstEmptyStack();
			if(slot >= 0)
				event.entityPlayer.inventory.setInventorySlotContents(slot, new ItemStack(IdearuNoKen.IDEAL_HAIR));
		}
	}

	@SuppressWarnings("unused")
	@SubscribeEvent
	public void onAnvilUpdate(AnvilUpdateEvent event) {
		if(event.left.getItem() == IdearuNoKen.IDEAL_HAIR && event.left.getItemDamage() == 0 && event.right.getItem() == Items.clock) {
			event.output = new ItemStack(IdearuNoKen.IDEAL_HAIR, 1, 1);
			event.materialCost = 1;
			event.cost = 30;
		}
	}
}
