package net.toshayo.tokanocreations.transformers;

import net.toshayo.tokanocreations.TokanoCreationsMod;

import java.util.HashMap;

public class Mappings {
    private static final HashMap<String, String> MAPPINGS = new HashMap<String, String>() {{
        put("onBlockPlaced", "a");
        put("onBlockActivated", "a");
        put("net/minecraft/world/World", "ahb");
        put("net/minecraft/entity/player/EntityPlayer", "yz");
    }};

    public static String get(String key) {
        return TokanoCreationsMod.isObfEnv() ? MAPPINGS.get(key) : key;
    }
}
