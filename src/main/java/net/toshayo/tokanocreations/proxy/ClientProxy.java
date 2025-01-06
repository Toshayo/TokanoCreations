package net.toshayo.tokanocreations.proxy;

import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.toshayo.tokanocreations.TokanoCreationsMod;
import net.toshayo.tokanocreations.client.CrossRenderer;
import net.toshayo.tokanocreations.client.HolyCoreRenderer;
import net.toshayo.tokanocreations.client.SwordRenderer;
import net.toshayo.tokanocreations.events.ClientEventHandler;

public class ClientProxy extends CommonProxy {
    @Override
    public void onPreInit() {
        super.onPreInit();

        MinecraftForgeClient.registerItemRenderer(TokanoCreationsMod.SWORD_SHAPE, new SwordRenderer());
        MinecraftForgeClient.registerItemRenderer(TokanoCreationsMod.SWORD_SHAPE_REINFORCED, new SwordRenderer());
        MinecraftForgeClient.registerItemRenderer(TokanoCreationsMod.SWORD, new SwordRenderer());
        MinecraftForgeClient.registerItemRenderer(TokanoCreationsMod.HOLY_CORE, new HolyCoreRenderer());
        MinecraftForgeClient.registerItemRenderer(TokanoCreationsMod.CROSS, new CrossRenderer());
    }

    @Override
    public void onPostInit() {
        super.onPostInit();

        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    }
}
