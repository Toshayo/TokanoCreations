package net.toshayo.tokanocreations.integration;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import net.minecraft.item.ItemStack;
import net.toshayo.tokanocreations.TokanoCreationsMod;

public class NEIConfig implements IConfigureNEI {
	@Override
	public void loadConfig() {
		API.hideItem(new ItemStack(TokanoCreationsMod.IDEAL_HAIR, 1, 0));
		API.hideItem(new ItemStack(TokanoCreationsMod.IDEAL_HAIR, 1, 1));
		API.hideItem(new ItemStack(TokanoCreationsMod.SWORD_SHAPE_REINFORCED));
	}

	@Override
	public String getName() {
		return TokanoCreationsMod.NAME;
	}

	@Override
	public String getVersion() {
		return TokanoCreationsMod.VERSION;
	}
}
