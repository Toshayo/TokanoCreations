package net.toshayo.tokanocreations.forge;

import net.toshayo.tokanocreations.TokanoCreationsMod;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TokanoCreationsMod.MOD_ID)
public final class TokanoCreationsModForge {
    @SuppressWarnings("removal")
    public TokanoCreationsModForge() {
        EventBuses.registerModEventBus(TokanoCreationsMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        TokanoCreationsMod.init();
    }
}
