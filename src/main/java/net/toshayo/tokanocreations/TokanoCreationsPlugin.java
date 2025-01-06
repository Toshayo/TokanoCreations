package net.toshayo.tokanocreations;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.7.10")
@IFMLLoadingPlugin.TransformerExclusions("net.toshayo.tokanocreations.transformers")
public class TokanoCreationsPlugin implements IFMLLoadingPlugin {
    public static final Logger LOGGER = LogManager.getLogger("TokanoCreationsPlugin");

    private static final String ENVIRONMENT_NAME = "@@ENVIRONMENT@@";

    @Override
    public String[] getASMTransformerClass() {
        return new String[] {
                "net.toshayo.tokanocreations.transformers.BlockBookshelfTransformer"
        };
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    public static boolean isObfEnv() {
        //noinspection ConstantValue,MismatchedStringCase
        return ENVIRONMENT_NAME.equals("obf");
    }
}
