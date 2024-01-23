package net.toshayo.tokanocreations.item;

import net.minecraft.item.ItemFood;
import net.toshayo.tokanocreations.TokanoCreationsMod;

public class TaiyakiItem extends ItemFood {
    public TaiyakiItem() {
        super(6, false);
        setUnlocalizedName(TokanoCreationsMod.MOD_ID + ".taiyaki");
        setMaxStackSize(16);
    }
}
