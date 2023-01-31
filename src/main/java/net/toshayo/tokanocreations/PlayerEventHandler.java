package net.toshayo.tokanocreations;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;

public class PlayerEventHandler {
	@SuppressWarnings("unused")
	@SubscribeEvent
	public void onWakeUp(PlayerWakeUpEvent event) {
		if(!event.wakeImmediatly && !event.entity.worldObj.isRemote && event.entity.worldObj.rand.nextFloat() <= 0.01) {
			int slot = event.entityPlayer.inventory.getFirstEmptyStack();
			if(slot >= 0)
				event.entityPlayer.inventory.setInventorySlotContents(slot, new ItemStack(TokanoCreationsMod.IDEAL_HAIR));
		}
	}
}
