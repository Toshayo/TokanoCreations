package net.toshayo.tokanocreations.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.toshayo.tokanocreations.TokanoCreationsMod;
import thaumcraft.common.entities.EntitySpecialItem;
import thaumcraft.common.tiles.TileInfusionMatrix;

import java.util.Arrays;

public class CraftEventHandler {
    /**
     * Inspired from
     * <a href="https://github.com/BluSunrize/WitchingGadgets/blob/1b3b5efd4c3623d0faf7cf6690642200e8dda0db/src/main/java/witchinggadgets/common/util/handler/EventHandler.java#L372">...</a>
     * @param event Event data
     */
    @SubscribeEvent
    public void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        double iX = event.player.posX;
        double iY = event.player.posY + 1;
        double iZ = event.player.posZ;
        for(int y =- 16; y <= 16; y++) {
            for (int z = -16; z <= 16; z++) {
                for (int x = -16; x <= 16; x++) {
                    if (event.player.worldObj.getTileEntity((int) event.player.posX + x, (int) event.player.posY + y, (int) event.player.posZ + z) instanceof TileInfusionMatrix) {
                        iX = event.player.posX + x;
                        iY = event.player.posY + y + .5;
                        iZ = event.player.posZ + z;
                    }
                }
            }
        }
        for(Item item : Arrays.asList(TokanoCreationsMod.SWORD, TokanoCreationsMod.CROSS)) {
            EntitySpecialItem entityItem = new EntitySpecialItem(event.player.worldObj, iX, iY, iZ, new ItemStack(item));
            entityItem.motionX = entityItem.motionY = entityItem.motionZ = 0;
            event.player.worldObj.spawnEntityInWorld(entityItem);
        }
    }
}
