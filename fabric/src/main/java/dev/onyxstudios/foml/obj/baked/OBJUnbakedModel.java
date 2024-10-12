package dev.onyxstudios.foml.obj.baked;

import com.mojang.datafixers.util.Pair;
import com.mojang.math.Transformation;
import com.mojang.math.Vector3f;
import de.javagl.obj.FloatTuple;
import de.javagl.obj.Mtl;
import de.javagl.obj.Obj;
import de.javagl.obj.ObjSplitting;
import dev.onyxstudios.foml.obj.FOMLMaterial;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.RendererAccess;
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh;
import net.fabricmc.fabric.api.renderer.v1.mesh.MeshBuilder;
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;

public class OBJUnbakedModel implements UnbakedModel {

    public static final Material DEFAULT_SPRITE = new Material(InventoryMenu.BLOCK_ATLAS, null);

    private final Obj obj;
    private final Map<String, FOMLMaterial> mtls;
    private final ItemTransforms transform;
    private final Material sprite;
    private final boolean flipV;

    public OBJUnbakedModel(Obj obj, Map<String, FOMLMaterial> mtls, ItemTransforms transform, boolean flipV) {
        this.obj = obj;
        this.mtls = mtls;
        this.transform = transform == null ? ItemTransforms.NO_TRANSFORMS : transform;
        this.flipV = flipV;

        Mtl mtl = this.findMtlForName("sprite");
        this.sprite = !mtls.isEmpty()
                ? new Material(InventoryMenu.BLOCK_ATLAS, new ResourceLocation((mtl == null ? mtls.values().iterator().next() : mtl).getMapKd()))
                : DEFAULT_SPRITE;
    }

    private FOMLMaterial findMtlForName(String name) {
        return mtls.get(name);
    }

    @Override
    public @NotNull Collection<ResourceLocation> getDependencies() {
        return Collections.emptySet();
    }

    @Override
    public @NotNull Collection<Material> getMaterials(Function<ResourceLocation, UnbakedModel> unbakedModelGetter, Set<Pair<String, String>> unresolvedTextureReferences) {
        List<Material> sprites = new ArrayList<>();
        mtls.values().forEach(mtl -> sprites.add(new Material(InventoryMenu.BLOCK_ATLAS, new ResourceLocation(mtl.getMapKd()))));

        return sprites;
    }

    @Nullable
    @Override
    public BakedModel bake(ModelBakery modelBakery, Function<Material, TextureAtlasSprite> textureGetter, ModelState modelState, ResourceLocation modelId) {
        Renderer renderer = RendererAccess.INSTANCE.getRenderer();
        Mesh mesh = null;

        if (renderer != null) {
            Map<String, Obj> materialGroups = ObjSplitting.splitByMaterialGroups(obj);
            MeshBuilder builder = renderer.meshBuilder();
            QuadEmitter emitter = builder.getEmitter();

            for (Map.Entry<String, Obj> entry : materialGroups.entrySet()) {
                String matName = entry.getKey();
                Obj matGroupObj = entry.getValue();

                FOMLMaterial mtl = findMtlForName(matName);
                int color = -1;

                TextureAtlasSprite mtlSprite = textureGetter.apply(DEFAULT_SPRITE);

                if (mtl != null) {
                    FloatTuple diffuseColor = mtl.getKd();

                    if (mtl.useDiffuseColor()) {
                        color = 0xFF000000;

                        for (int i = 0; i < 3; ++i) {
                            color |= (int) (255 * diffuseColor.get(i)) << (16 - 8 * i);
                        }
                    }

                    mtlSprite = getMtlSprite(textureGetter, new ResourceLocation(mtl.getMapKd()));
                }

                for (int i = 0; i < matGroupObj.getNumFaces(); i++) {
                    FloatTuple floatTuple;
                    Vector3f vertex;
                    FloatTuple normal;
                    int v;
                    for (v = 0; v < matGroupObj.getFace(i).getNumVertices(); v++) {
                        floatTuple = matGroupObj.getVertex(matGroupObj.getFace(i).getVertexIndex(v));
                        vertex = new Vector3f(floatTuple.getX(), floatTuple.getY(), floatTuple.getZ());
                        normal = matGroupObj.getNormal(matGroupObj.getFace(i).getNormalIndex(v));

                        addVertex(i, v, vertex, normal, emitter, matGroupObj, false, modelState);

                        // Special conversion of triangles to quads: re-add third vertex as the fourth vertex
                        // Moved into the loop so that `vertex` and `normal` are guaranteed to exist
                        if (v == 2 && matGroupObj.getFace(i).getNumVertices() == 3) {
                            addVertex(i, 3, vertex, normal, emitter, matGroupObj, true, modelState);
                        }
                    }

                    emitter.spriteColor(0, color, color, color, color);
                    emitter.material(RendererAccess.INSTANCE.getRenderer().materialFinder().find());
                    emitter.colorIndex(mtl != null ? mtl.getTintIndex() : -1);
                    emitter.nominalFace(emitter.lightFace());
                    int flag = MutableQuadView.BAKE_NORMALIZED;
                    if(flipV) {
                        flag |= MutableQuadView.BAKE_FLIP_V;
                    }
                    if(modelState.isUvLocked()) {
                        flag |= MutableQuadView.BAKE_LOCK_UV;
                    }
                    emitter.spriteBake(0, mtlSprite, flag);

                    emitter.emit();
                }
            }

            mesh = builder.build();
        }

        return new OBJBakedModel(mesh, transform, textureGetter.apply(this.sprite));
    }

    private void addVertex(int faceIndex, int vertIndex, Vector3f vertex, FloatTuple normal, QuadEmitter emitter,
                           Obj matGroup, boolean degenerate, ModelState bakeSettings) {
        int textureCoordIndex = vertIndex;
        if (degenerate)
            textureCoordIndex--;

        if (bakeSettings.getRotation() != Transformation.identity() && !degenerate) {
            vertex.add(-0.5F, -0.5F, -0.5F);
            vertex.transform(bakeSettings.getRotation().getLeftRotation());
            vertex.add(0.5f, 0.5f, 0.5f);
        }

        emitter.pos(vertIndex, vertex.x(), vertex.y(), vertex.z());
        emitter.normal(vertIndex, normal.getX(), normal.getY(), normal.getZ());

        if (obj.getNumTexCoords() > 0) {
            FloatTuple text = matGroup.getTexCoord(matGroup.getFace(faceIndex).getTexCoordIndex(textureCoordIndex));

            emitter.sprite(vertIndex, 0, text.getX(), text.getY());
        } else {
            emitter.nominalFace(Direction.getNearest(normal.getX(), normal.getY(), normal.getZ()));
        }
    }

    private static TextureAtlasSprite getMtlSprite(Function<Material, TextureAtlasSprite> textureGetter, ResourceLocation name) {
        return textureGetter.apply(new Material(InventoryMenu.BLOCK_ATLAS, name));
    }
}
