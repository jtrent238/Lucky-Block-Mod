package mod.lucky.client;

import net.minecraftforge.client.model.ITransformation;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import com.google.common.base.Function;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraftforge.client.model.IModelState;
import java.util.Collections;
import java.util.Collection;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;

public class LuckyBlockModel implements IModel
{
    private final ResourceLocation modelLocation;
    private final ModelBlock model;
    
    public LuckyBlockModel(final ResourceLocation a1) {
        this.modelLocation = a1;
        this.model = LuckyBlockModelLoader.instance.loadModelBlock(a1);
    }
    
    public Collection<ResourceLocation> getDependencies() {
        /*SL:31*/return Collections.<ResourceLocation>singletonList(new ResourceLocation("lucky:block/lucky_block"));
    }
    
    public Collection<ResourceLocation> getTextures() {
        /*SL:37*/if (this.model.func_178305_e() != null) {
            /*SL:39*/this.model.field_178315_d = LuckyBlockModelLoader.instance.loadModelBlock(this.model.func_178305_e());
        }
        /*SL:42*/return (Collection<ResourceLocation>)Collections.<Object>emptyList();
    }
    
    public IFlexibleBakedModel bake(final IModelState a1, final VertexFormat a2, final Function<ResourceLocation, TextureAtlasSprite> a3) {
        /*SL:48*/return LuckyBlockModelLoader.instance.bakeModel(this.model, (ITransformation)ModelRotation.X0_Y0, false);
    }
    
    public IModelState getDefaultState() {
        /*SL:54*/return (IModelState)ModelRotation.X0_Y0;
    }
}
