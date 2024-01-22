package net.toshayo.tokanocreations.proxy;

import cpw.mods.fml.common.Loader;
import net.minecraftforge.common.MinecraftForge;
import net.toshayo.tokanocreations.events.AnvilEventHandler;
import net.toshayo.tokanocreations.events.PlayerEventHandler;
import net.toshayo.tokanocreations.integration.thaumcraft.ThaumcraftPlugin;

public class CommonProxy {
    public void onPreInit() {

    }

    public void onPostInit() {
        MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
        if(Loader.isModLoaded("Thaumcraft"))
            ThaumcraftPlugin.postInit();
        else
            MinecraftForge.EVENT_BUS.register(new AnvilEventHandler());
    }
}
