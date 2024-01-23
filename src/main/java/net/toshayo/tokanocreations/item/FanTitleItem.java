package net.toshayo.tokanocreations.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.toshayo.tokanocreations.TokanoCreationsMod;
import travellersgear.TravellersGear;
import travellersgear.api.ITravellersGear;

import java.util.List;

public class FanTitleItem extends Item implements ITravellersGear {
    public FanTitleItem() {
        super();
        setUnlocalizedName("TravellersGear.simpleGear.title");
        setCreativeTab(TravellersGear.creativeTab);
        setMaxStackSize(1);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
        ItemStack scr = new ItemStack(this, 1, 0);
        scr.setTagCompound(new NBTTagCompound());
        scr.getTagCompound().setString("title", TokanoCreationsMod.MOD_ID + ".title_fan.title");
        //noinspection unchecked
        list.add(scr);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        String type = this.getSlot(stack) >= 0 ? "tg." + this.getSlot(stack) : "bauble.title";
        //noinspection unchecked
        list.add(StatCollector.translateToLocalFormatted("TG.desc.gearSlot." + type));
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("title")) {
            //noinspection unchecked
            list.add(StatCollector.translateToLocal(stack.getTagCompound().getString("title")));
        }
    }

    @Override
    public int getSlot(ItemStack itemStack) {
        return 3;
    }

    @Override
    public void onTravelGearTick(EntityPlayer entityPlayer, ItemStack itemStack) {

    }

    @Override
    public void onTravelGearEquip(EntityPlayer entityPlayer, ItemStack itemStack) {

    }

    @Override
    public void onTravelGearUnequip(EntityPlayer entityPlayer, ItemStack itemStack) {

    }
}
