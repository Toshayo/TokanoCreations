package dev.onyxstudios.foml.obj;

import de.javagl.obj.Obj;
import de.javagl.obj.ObjReader;
import de.javagl.obj.ObjUtils;
import dev.onyxstudios.foml.obj.baked.OBJUnbakedModel;
import net.fabricmc.fabric.api.client.model.ModelProviderContext;
import net.fabricmc.fabric.api.client.model.ModelResourceProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class OBJLoader implements ModelResourceProvider, Function<ResourceManager, ModelResourceProvider> {
    public static final OBJLoader INSTANCE = new OBJLoader();
    public static final Logger LOGGER = LogManager.getLogger("FOML");


    private OBJLoader() {
    }

    public OBJUnbakedModel loadModel(Reader reader, String modid, ResourceManager manager, ItemTransforms transform, boolean flipV, Map<String, ResourceLocation> textureOverrides) {
        OBJUnbakedModel model;

        try {
            Obj obj = ObjUtils.convertToRenderable(ObjReader.read(reader));
            model = new OBJUnbakedModel(ObjUtils.triangulate(obj), loadMTL(manager, modid, obj.getMtlFileNames()), transform, flipV, textureOverrides);
        } catch (IOException e) {
            LOGGER.error("Could not read obj model!", e);
            return null;
        }

        return model;
    }

    public Map<String, FOMLMaterial> loadMTL(ResourceManager manager, String modid, List<String> mtlNames) throws IOException {
        Map<String, FOMLMaterial> mtls = new LinkedHashMap<>();

        for (String name : mtlNames) {
            var resource = manager.getResource(new ResourceLocation(modid, "models/" + name));
            // Use 1.0.0 MTL path as a fallback
            if (resource.isEmpty()) {
                resource = manager.getResource(new ResourceLocation(modid, "models/block/" + name));
            }

            // Continue with normal resource loading code
            if (resource.isPresent()) {
                MtlReader.read(resource.get().open()).forEach(mtl -> mtls.put(mtl.getName(), mtl));
            } else {
                LOGGER.warn("Warning, a model specifies an MTL File but it could not be found! Source: " + modid + ":" + name);
            }
        }

        return mtls;
    }

    @Override
    public UnbakedModel loadModelResource(ResourceLocation identifier, ModelProviderContext modelProviderContext) {
        return loadModelResource(identifier, ItemTransforms.NO_TRANSFORMS, false, Map.of());
    }

    protected UnbakedModel loadModelResource(ResourceLocation identifier, ItemTransforms transform, boolean flipV, Map<String, ResourceLocation> textureOverrides) {
        if (identifier.getPath().endsWith(".obj")) {
            ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();

            var resource = resourceManager.getResource(new ResourceLocation(identifier.getNamespace(), identifier.getPath()));
            if (resource.isPresent()) {
                try (Reader reader = new InputStreamReader(resource.get().open())) {
                    return loadModel(reader, identifier.getNamespace(), resourceManager, transform, flipV, textureOverrides);
                } catch (IOException e) {
                    LOGGER.error("Unable to load OBJ Model, Source: " + identifier, e);
                }
            } else {
                LOGGER.error("Unable to find OBJ Model, Source: " + identifier);
            }
        }

        return null;
    }

    @Override
    public ModelResourceProvider apply(ResourceManager manager) {
        return this;
    }
}
