package net.toshayo.idearunoken.integration;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import net.minecraft.item.ItemStack;
import net.toshayo.idearunoken.IdearuNoKen;

public class NEIConfig implements IConfigureNEI {
	@Override
	public void loadConfig() {
		API.hideItem(new ItemStack(IdearuNoKen.IDEAL_HAIR, 1, 0));
		API.hideItem(new ItemStack(IdearuNoKen.IDEAL_HAIR, 1, 1));
		API.hideItem(new ItemStack(IdearuNoKen.SWORD_SHAPE_REINFORCED));
	}

	@Override
	public String getName() {
		return IdearuNoKen.NAME;
	}

	@Override
	public String getVersion() {
		return IdearuNoKen.VERSION;
	}
}
