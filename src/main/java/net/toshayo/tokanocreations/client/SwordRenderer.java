package net.toshayo.tokanocreations.client;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.toshayo.tokanocreations.item.SwordShapeItem;
import net.toshayo.tokanocreations.TokanoCreationsMod;
import org.lwjgl.opengl.GL11;

public class SwordRenderer implements IItemRenderer {
	public static final ResourceLocation swordTexture = new ResourceLocation(TokanoCreationsMod.MOD_ID, "textures/models/sword.png");
	public static final IModelCustom swordModel = AdvancedModelLoader.loadModel(new ResourceLocation(TokanoCreationsMod.MOD_ID, "models/sword.obj"));

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
		Minecraft.getMinecraft().renderEngine.bindTexture(swordTexture);
		GL11.glScalef(0.015F, 0.015F, 0.015F);

		GL11.glRotatef(90, 0, 1, 0);
		GL11.glRotatef(45, 1, 0, 0);

		if(type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
			GL11.glScalef(1.5F, 1.5F, 1.5F);
			GL11.glRotatef(25, 1, 0, 0);
			GL11.glTranslatef(0, -10, 10);
			if(Minecraft.getMinecraft().thePlayer.getItemInUseCount() > 0) {
				GL11.glTranslatef(-10, -30, 20);
			}
		} else if(type == ItemRenderType.INVENTORY) {
			GL11.glScalef(15F, 15F, 15F);
			GL11.glTranslatef(0F, 95, -30F);
			GL11.glRotatef(180, 1, 0, 0);
			GL11.glScalef(1, 1, 0.4F);
		} else if(type == ItemRenderType.ENTITY) {
			EntityItem entity = (EntityItem) objects[1];
			if(entity.posX == 0 && entity.posY == 0 && entity.posZ == 0 && entity.hoverStart == 0) {
				GL11.glTranslatef(0, -15, 30);
				GL11.glScalef(0.5F, 0.5F, 0.5F);
			} else {
				GL11.glTranslatef(0, 100, -100);
				GL11.glRotatef(180, 1, 0, 0);
			}
		} else {
			if(Minecraft.getMinecraft().thePlayer.getItemInUseCount() > 0) {
				GL11.glTranslatef(0, 0, 20);
			}
		}

		if(stack.getItem() != TokanoCreationsMod.SWORD) {
			if(stack.getItem() == TokanoCreationsMod.SWORD_SHAPE) {
				float color = 0.3F + 0.5F * ((SwordShapeItem.REINFORCE_COOK_TIMES - 1F - stack.getItemDamage()) / SwordShapeItem.REINFORCE_COOK_TIMES);
				GL11.glColor3f(color, color, color + 0.1F);
			} else
				GL11.glColor3f(0.1F, 0.1F, 0.1F);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
			if(type == ItemRenderType.EQUIPPED) {
				GL11.glRotatef(30, 1, 0, 0);
				GL11.glRotatef(10, 0, 1, 0);
				GL11.glTranslatef(10, -15, -10);
			}
		} else if(type == ItemRenderType.EQUIPPED){
			GL11.glScalef(0.8F, 0.8F, 0.8F);
			if(Minecraft.getMinecraft().thePlayer.getItemInUseCount() > 0) {
				GL11.glTranslatef(5, 10, -5);
			} else
				GL11.glTranslatef(0, 10, 0);
		}

		GL11.glTranslatef(0, 35, -80);

		GL11.glDisable(GL11.GL_CULL_FACE);
		swordModel.renderAll();
		GL11.glPopMatrix();
	}
}
