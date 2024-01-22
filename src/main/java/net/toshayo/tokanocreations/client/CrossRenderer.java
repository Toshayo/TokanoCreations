package net.toshayo.tokanocreations.client;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.toshayo.tokanocreations.TokanoCreationsMod;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class CrossRenderer implements IItemRenderer {
    private static final ResourceLocation texture = new ResourceLocation("textures/blocks/clay.png");
    public static final IModelCustom crossModel = AdvancedModelLoader.loadModel(new ResourceLocation(TokanoCreationsMod.MOD_ID, "models/cross.obj"));

    private static final Random rnd = new Random();
    private int cnt = 0;
    private double color = 0;

    @Override
    public boolean handleRenderType(ItemStack stack, ItemRenderType type) {
        switch(type) {
            case EQUIPPED:
            case EQUIPPED_FIRST_PERSON:
            case ENTITY:
            case INVENTORY:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack stack, ItemRendererHelper helper) {
        return type == ItemRenderType.ENTITY && (helper == ItemRendererHelper.ENTITY_ROTATION || helper == ItemRendererHelper.ENTITY_BOBBING);
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack stack, Object... objects) {
        GL11.glPushMatrix();
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        GL11.glScalef(0.01F, 0.01F, 0.01F);

        if(cnt++ % 15 == 0) {
            color = rnd.nextGaussian() * 0.03 + 0.1;
        }
        GL11.glColor3d(color, color, color);


        if(type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glTranslatef(25, 30, -35);
            GL11.glRotatef(70, 1, 0, 0);
            GL11.glRotatef(-60, 0, 0, 1);
            GL11.glRotatef(50, 0, 1, 0);
        } else if(type == ItemRenderType.INVENTORY) {
            GL11.glScalef(61.5F, 61.5F, 61.5F);
            GL11.glTranslatef(8, 32, 0);
            GL11.glRotatef(90, 1, 0, 0);
            GL11.glRotatef(-135, 0, 1, 0);
        } else if(type == ItemRenderType.EQUIPPED) {
            GL11.glTranslatef(40, 15, -10);
            GL11.glRotatef(90, 1, 0, 0);
        } else if(type == ItemRenderType.ENTITY) {
            GL11.glTranslatef(-10F, 0, 10);
        }
        GL11.glDisable(GL11.GL_CULL_FACE);
        crossModel.renderAll();
        GL11.glPopMatrix();
    }
}
