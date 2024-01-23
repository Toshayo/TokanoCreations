package net.toshayo.tokanocreations.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.toshayo.tokanocreations.TokanoCreationsMod;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.events.EventHandlerRunic;

import java.util.List;

@Optional.InterfaceList(
        value = {
                @Optional.Interface(
                        modid = "Baubles",
                        iface = "baubles.api.IBauble"
                ),
                @Optional.Interface(
                        modid = "Thaumcraft",
                        iface = "thaumcraft.api.IRunicArmor"
                ),
                @Optional.Interface(
                        modid = "Thaumcraft",
                        iface = "thaumcraft.api.IVisDiscountGear"
                )
        }
)
public class CrossItem extends Item implements IBauble, IRunicArmor, IVisDiscountGear {
    public CrossItem() {
        super();
        setUnlocalizedName(TokanoCreationsMod.MOD_ID + ".cross");
        setCreativeTab(CreativeTabs.tabCombat);
        setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        super.addInformation(stack, player, list, p_77624_4_);
        if(Loader.isModLoaded("Thaumcraft")) {
            if (this.getVisDiscount(stack, player, null) != 0) {
                //noinspection unchecked
                list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") + ": " + this.getVisDiscount(stack, player, null) + "%");
            }
        }
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.AMULET;
    }

    @Override
    public void onWornTick(ItemStack itemStack, EntityLivingBase entityLivingBase) {

    }

    @Override
    public void onEquipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        Thaumcraft.instance.runicEventHandler.isDirty = true;
    }

    @Override
    public void onUnequipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        Thaumcraft.instance.runicEventHandler.isDirty = true;
    }

    @Override
    public boolean canEquip(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return true;
    }

    @Override
    public boolean canUnequip(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return true;
    }

    @Override
    public int getRunicCharge(ItemStack itemStack) {
        return 8;
    }

    @Override
    public int getVisDiscount(ItemStack itemStack, EntityPlayer entityPlayer, Aspect aspect) {
        return EventHandlerRunic.getFinalCharge(itemStack) * 2;
    }
}
