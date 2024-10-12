package net.toshayo.tokanocreations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class TokanoCreationsMod {
    public static final String MOD_ID = "tokanocreations";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static void init() {
        ModItems.init();
    }
}
