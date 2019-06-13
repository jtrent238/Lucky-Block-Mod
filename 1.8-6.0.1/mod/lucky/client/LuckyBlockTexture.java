package mod.lucky.client;

import javax.imageio.ImageIO;
import java.io.File;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import java.awt.image.BufferedImage;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public class LuckyBlockTexture extends TextureAtlasSprite
{
    public LuckyBlockTexture() {
        super("lucky:blocks/lucky_block");
    }
    
    public void loadSprite(final BufferedImage a1, final AnimationMetadataSection a2) {
        final BufferedImage[] v1 = /*EL:23*/new BufferedImage[5];
        /*SL:24*/v1[0] = a1;
        /*SL:25*/super.func_180598_a(v1, a2);
        /*SL:26*/this.func_147963_d(Minecraft.func_71410_x().field_71474_y.field_151442_I);
    }
    
    public boolean hasCustomLoader(final IResourceManager a1, final ResourceLocation a2) {
        /*SL:32*/return true;
    }
    
    public boolean load(final IResourceManager v2, final ResourceLocation v3) {
        try {
            final BufferedImage a1 = /*EL:42*/ImageIO.read(new File("./lucky_block.png"));
            /*SL:43*/this.loadSprite(a1, null);
        }
        catch (Exception a2) {
            /*SL:47*/a2.printStackTrace();
        }
        /*SL:50*/return false;
    }
}
