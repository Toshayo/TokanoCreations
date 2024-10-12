package net.toshayo.tokanocreations.fabric;

import net.fabricmc.api.ModInitializer;
import net.toshayo.tokanocreations.TokanoCreationsMod;

public final class TokanoCreationsModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        TokanoCreationsMod.init();
    }
}
