package net.toshayo.tokanocreations.events;

import baubles.api.BaublesApi;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.toshayo.tokanocreations.TokanoCreationsMod;
import org.lwjgl.opengl.GL11;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED;

public class ClientEventHandler {
    @SubscribeEvent
    public void onPlayerRender(RenderPlayerEvent.Specials.Post event) {
        if(event.entityLiving.getActivePotionEffect(Potion.invisibility) != null) {
            return;
        }

        EntityPlayer player = event.entityPlayer;
        IInventory inv = BaublesApi.getBaubles(player);
        ItemStack stack = inv.getStackInSlot(0);
        if(stack == null || stack.getItem() != TokanoCreationsMod.CROSS) {
            return;
        }

        GL11.glPushMatrix();
        GL11.glColor4f(1F, 1F, 1F, 1F);
        GL11.glRotatef(180, 1, 0, 0);
        if (player.isSneaking()) {
            GL11.glRotatef(90F / (float) Math.PI, 1, 0, 0);
        }
        GL11.glTranslatef(-0.2F, player.height * -0.3F, 0.19F);
        GL11.glScalef(0.7F, 0.7F, 0.7F);
        MinecraftForgeClient.getItemRenderer(stack, EQUIPPED).renderItem(EQUIPPED, stack);
        GL11.glPopMatrix();
    }
}
