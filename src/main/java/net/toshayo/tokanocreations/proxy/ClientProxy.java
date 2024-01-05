package net.toshayo.tokanocreations.proxy;

import net.minecraftforge.client.MinecraftForgeClient;
import net.toshayo.tokanocreations.client.SwordRenderer;
import net.toshayo.tokanocreations.TokanoCreationsMod;

public class ClientProxy extends CommonProxy {
    @Override
    public void onPreInit() {
        super.onPreInit();

        MinecraftForgeClient.registerItemRenderer(TokanoCreationsMod.SWORD_SHAPE, new SwordRenderer());
        MinecraftForgeClient.registerItemRenderer(TokanoCreationsMod.SWORD_SHAPE_REINFORCED, new SwordRenderer());
        MinecraftForgeClient.registerItemRenderer(TokanoCreationsMod.SWORD, new SwordRenderer());
    }
}
