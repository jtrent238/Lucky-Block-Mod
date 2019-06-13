package mod.lucky.client;

import net.minecraft.client.renderer.block.model.BakedQuad;
import java.util.Iterator;
import java.awt.image.BufferedImage;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.client.model.Attributes;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.util.EnumFacing;
import net.minecraft.client.renderer.block.model.BlockPart;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import javax.imageio.ImageIO;
import java.io.File;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.ITransformation;
import java.io.Reader;
import net.minecraft.client.resources.IResource;
import java.io.InputStreamReader;
import com.google.common.base.Charsets;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraftforge.client.model.IModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraftforge.client.model.ICustomModelLoader;

public class LuckyBlockModelLoader implements ICustomModelLoader
{
    public static LuckyBlockModelLoader instance;
    private FaceBakery faceBakery;
    
    public LuckyBlockModelLoader() {
        LuckyBlockModelLoader.instance = this;
        this.faceBakery = new FaceBakery();
    }
    
    public void func_110549_a(final IResourceManager a1) {
    }
    
    public boolean accepts(final ResourceLocation a1) {
        /*SL:51*/return a1.func_110624_b().equals("lucky");
    }
    
    public IModel loadModel(final ResourceLocation a1) {
        /*SL:57*/return (IModel)new LuckyBlockModel(a1);
    }
    
    public ModelBlock loadModelBlock(final ResourceLocation v0) {
        try {
            IResource v = /*EL:68*/null;
            try {
                /*SL:71*/v = Minecraft.func_71410_x().func_110442_L().func_110536_a(this.getModelLocation(v0));
            }
            catch (IOException a1) {
                System.err.println(/*EL:75*/"Lucky Block: Failed to load block model from location: " + v0.toString());
                /*SL:76*/a1.printStackTrace();
            }
            final Reader v2 = /*EL:78*/new InputStreamReader(v.func_110527_b(), Charsets.UTF_8);
            final ModelBlock v3 = /*EL:81*/ModelBlock.func_178307_a(v2);
            /*SL:82*/v3.field_178317_b = v0.toString();
            /*SL:83*/v2.close();
            /*SL:85*/if (v3.func_178305_e() != null) {
                v3.field_178315_d = this.loadModelBlock(v3.func_178305_e());
            }
            /*SL:87*/return v3;
        }
        catch (Exception v4) {
            System.err.println(/*EL:91*/"Lucky Block: Failed to load block model from location: " + v0.toString());
            /*SL:92*/v4.printStackTrace();
            /*SL:94*/return null;
        }
    }
    
    private ResourceLocation getModelLocation(final ResourceLocation a1) {
        /*SL:99*/return new ResourceLocation(a1.func_110624_b(), (a1.func_110623_a().startsWith("models/") ? "" : "models/") + a1.func_110623_a() + ".json");
    }
    
    public IFlexibleBakedModel bakeModel(final ModelBlock v-9, final ITransformation v-8, final boolean v-7) {
        TextureAtlasSprite func_110572_b = /*EL:104*/new LuckyBlockTexture();
        try {
            final BufferedImage a1 = /*EL:109*/ImageIO.read(new File("./lucky_block.png"));
            /*SL:110*/((LuckyBlockTexture)func_110572_b).loadSprite(a1, null);
            /*SL:111*/func_110572_b.func_147963_d(Minecraft.func_71410_x().field_71474_y.field_151442_I);
        }
        catch (Exception a2) {
            /*SL:115*/a2.printStackTrace();
        }
        /*SL:117*/func_110572_b = Minecraft.func_71410_x().func_147117_R().func_110572_b("lucky:blocks/lucky_block_custom");
        final TextureAtlasSprite func_110572_b2 = /*EL:118*/Minecraft.func_71410_x().func_147117_R().func_110572_b("minecraft:blocks/sponge");
        final SimpleBakedModel.Builder func_177646_a = /*EL:122*/new SimpleBakedModel.Builder(v-9).func_177646_a(func_110572_b);
        /*SL:123*/for (final BlockPart blockPart : v-9.func_178298_a()) {
            /*SL:128*/for (final EnumFacing a3 : blockPart.field_178240_c.keySet()) {
                final BlockPartFace v1 = /*EL:133*/blockPart.field_178240_c.get(a3);
                /*SL:135*/if (v1.field_178244_b == null) {
                    /*SL:137*/func_177646_a.func_177648_a(this.makeBakedQuad(blockPart, v1, func_110572_b, a3, v-8, v-7));
                }
                else {
                    /*SL:141*/func_177646_a.func_177650_a(v-8.rotate(v1.field_178244_b), this.makeBakedQuad(blockPart, v1, func_110572_b, a3, v-8, v-7));
                }
            }
        }
        /*SL:146*/return (IFlexibleBakedModel)new IFlexibleBakedModel.Wrapper(func_177646_a.func_177645_b(), Attributes.DEFAULT_BAKED_FORMAT);
    }
    
    private BakedQuad makeBakedQuad(final BlockPart a1, final BlockPartFace a2, final TextureAtlasSprite a3, final EnumFacing a4, final ITransformation a5, final boolean a6) {
        /*SL:151*/return this.faceBakery.makeBakedQuad(a1.field_178241_a, a1.field_178239_b, a2, a3, a4, a5, a1.field_178237_d, a6, a1.field_178238_e);
    }
}
