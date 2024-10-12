package dev.onyxstudios.foml.obj;

import com.google.gson.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.ModelProviderContext;
import net.fabricmc.fabric.api.client.model.ModelVariantProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemTransform;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/***
 *  ItemOBJLoader
 *  Child class of the OBJ loader that loads basic item OBJ models with JSON model transformations.
 *  Created by jard at 2:27 AM on September 22, 2019.
 ***/
public class ItemOBJLoader implements ModelVariantProvider, Function<ResourceManager, ModelVariantProvider> {
    public static ItemOBJLoader INSTANCE = new ItemOBJLoader();
    public static final Gson GSON = (new GsonBuilder())
            .registerTypeAdapter(ItemTransforms.class, new ItemOBJLoader.ModelTransformDeserializer())
            .registerTypeAdapter(ItemTransform.class, new ItemOBJLoader.TransformDeserializer())
            .create();
    private static final OBJLoader OBJ_LOADER = OBJLoader.INSTANCE;

    @Override
    public UnbakedModel loadModelVariant(ModelResourceLocation modelId, ModelProviderContext context) {
        if (modelId.getVariant().equals("inventory")) {
            ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();

            ResourceLocation modelPath = new ResourceLocation(modelId.getNamespace(),
                    "models/item/" + modelId.getPath() + ".json");
            var resource = resourceManager.getResource(modelPath);
            if (resource.isPresent()) {
                try (Reader reader = new InputStreamReader(resource.get().open())) {
                    JsonObject rawModel = new JsonStreamParser(reader).next().getAsJsonObject();

                    if(rawModel.has("loader")) {
                        JsonPrimitive loader = rawModel.getAsJsonPrimitive("loader");
                        if(loader.isString() && loader.getAsString().equals("forge:obj")) {
                            JsonElement model = rawModel.get("model");
                            if (model instanceof JsonPrimitive && ((JsonPrimitive) model).isString()) {
                                String objPath = model.getAsString();
                                if (!objPath.endsWith(".obj"))
                                    throw new IllegalStateException("Parent of JsonOBJ model must be a .obj file.");

                                ResourceLocation parentPath = new ResourceLocation(objPath);

                                ItemTransforms transformation = null;
                                if (rawModel.has("display")) {
                                    JsonObject rawTransform = rawModel.getAsJsonObject("display");
                                    transformation = GSON.fromJson(rawTransform, ItemTransforms.class);
                                }

                                boolean flipV = false;
                                if(rawModel.has("flip_v")) {
                                    JsonPrimitive value = rawModel.getAsJsonPrimitive("flip_v");
                                    if(value.isBoolean()) {
                                        flipV = value.getAsBoolean();
                                    }
                                }

                                Map<String, ResourceLocation> textureOverrides = new HashMap<>();
                                if(rawModel.has("textures")) {
                                    JsonObject textures = rawModel.getAsJsonObject("textures");
                                    for(var keyValue : textures.entrySet()) {
                                        textureOverrides.put(keyValue.getKey(), new ResourceLocation(keyValue.getValue().getAsString()));
                                    }
                                }

                                return OBJ_LOADER.loadModelResource(parentPath, transformation, flipV, textureOverrides);
                            }
                        }
                    }
                } catch (Exception e) {
                    OBJLoader.LOGGER.error("Unable to load OBJ Model, Source: " + modelId, e);
                }
            }
        }
        return null;
    }

    @Environment(EnvType.CLIENT)
    public static class ModelTransformDeserializer extends ItemTransforms.Deserializer {
        protected ModelTransformDeserializer() {
            super();
        }
    }

    @Environment(EnvType.CLIENT)
    public static class TransformDeserializer extends ItemTransform.Deserializer {
        protected TransformDeserializer() {
            super();
        }
    }

    @Override
    public ModelVariantProvider apply(ResourceManager manager) {
        return this;
    }
}
