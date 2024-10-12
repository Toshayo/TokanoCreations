package net.toshayo.tokanocreations.fabric.client;

import dev.onyxstudios.foml.obj.ItemOBJLoader;
import dev.onyxstudios.foml.obj.OBJLoader;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;

public final class TokanoCreationsModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelLoadingRegistry.INSTANCE.registerResourceProvider(OBJLoader.INSTANCE);
        ModelLoadingRegistry.INSTANCE.registerVariantProvider(ItemOBJLoader.INSTANCE);
    }
}
