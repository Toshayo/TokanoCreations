package net.toshayo.tokanocreations;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

public class ASMHelpers {
    private static final Random rnd = new Random();

    public static boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player) {
        if(!world.isRemote) {
            int meta = world.getBlockMetadata(x, y, z);
            if((meta & 0b1000) == 0) {
                if(rnd.nextInt(100) == 0) {
                    ItemStack stack = new ItemStack(TokanoCreationsMod.IDEAL_HAIR);

                    boolean flag = player.inventory.addItemStackToInventory(stack);
                    if (flag) {
                        player.worldObj.playSound(player.posX, player.posY, player.posZ, "random.pop", 0.2F, ((player.getRNG().nextFloat() - player.getRNG().nextFloat()) * 0.7F + 1.0F) * 2.0F, false);
                        player.inventoryContainer.detectAndSendChanges();
                    } else {
                        EntityItem entityitem = player.dropPlayerItemWithRandomChoice(stack, false);

                        if (entityitem != null) {
                            entityitem.delayBeforeCanPickup = 0;
                            entityitem.func_145797_a(player.getCommandSenderName());
                        }
                    }
                }
                world.setBlockMetadataWithNotify(x, y, z, meta | 0b1000, 2);
            }
        }
        return true;
    }
}
