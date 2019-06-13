package mod.lucky.client.render;

import net.minecraft.client.renderer.Tessellator;
import java.awt.Color;
import mod.lucky.item.ItemLuckyBlock;
import net.minecraft.block.Block;
import org.lwjgl.opengl.GL11;
import mod.lucky.Lucky;
import mod.lucky.block.BlockLuckyBlock;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class RenderLuckyBlock implements IItemRenderer
{
    public boolean handleRenderType(final ItemStack a1, final IItemRenderer.ItemRenderType a2) {
        /*SL:20*/return a2 == IItemRenderer.ItemRenderType.INVENTORY;
    }
    
    public boolean shouldUseRenderHelper(final IItemRenderer.ItemRenderType a1, final ItemStack a2, final IItemRenderer.ItemRendererHelper a3) {
        /*SL:27*/return false;
    }
    
    public void renderItem(final IItemRenderer.ItemRenderType a1, final ItemStack a2, final Object... a3) {
        final RenderBlocks v1 = /*EL:33*/(RenderBlocks)a3[0];
        /*SL:34*/this.renderInventoryItem(a2, v1);
    }
    
    public void renderInventoryItem(final ItemStack v-5, final RenderBlocks v-4) {
        final BlockLuckyBlock blockLuckyBlock = (BlockLuckyBlock)Lucky.lucky_block;
        /*SL:41*/GL11.glPushMatrix();
        /*SL:43*/GL11.glEnable(2896);
        /*SL:44*/GL11.glTranslatef(-2.0f, 3.0f, 0.0f);
        /*SL:45*/GL11.glScalef(10.0f, 10.0f, 10.0f);
        /*SL:46*/GL11.glTranslatef(1.0f, 0.5f, 1.0f);
        /*SL:47*/GL11.glScalef(1.0f, 1.0f, -1.0f);
        /*SL:48*/GL11.glRotatef(210.0f, 1.0f, 0.0f, 0.0f);
        /*SL:49*/GL11.glRotatef(45.0f, 0.0f, 1.0f, 0.0f);
        /*SL:51*/GL11.glRotatef(-90.0f, 0.0f, 1.0f, 0.0f);
        /*SL:52*/v-4.func_147800_a((Block)blockLuckyBlock, 0, 1.0f);
        /*SL:53*/GL11.glDisable(2896);
        /*SL:55*/GL11.glPopMatrix();
        /*SL:57*/GL11.glPushMatrix();
        int luck = /*EL:59*/ItemLuckyBlock.getLuck(v-5);
        /*SL:60*/if (luck != 0) {
            final Color a1 = /*EL:62*/new Color(7, 147, 27);
            final Color a2 = /*EL:63*/new Color(0, 50, 0);
            final Color v1 = /*EL:64*/new Color(224, 16, 16);
            final Color v2 = /*EL:65*/new Color(50, 0, 0);
            final int v3 = /*EL:67*/(luck < 0) ? v1.getRGB() : a1.getRGB();
            final int v4 = /*EL:68*/(luck < 0) ? v2.getRGB() : a2.getRGB();
            /*SL:70*/if (luck < 0) {
                luck *= -1;
            }
            int v5 = /*EL:72*/(int)Math.round(luck * 13.0 / 100.0);
            /*SL:73*/if (v5 == 0) {
                v5 = 1;
            }
            /*SL:75*/GL11.glDisable(2896);
            /*SL:76*/GL11.glDisable(2929);
            /*SL:77*/GL11.glDisable(3553);
            /*SL:78*/GL11.glDisable(3008);
            /*SL:79*/GL11.glDisable(3042);
            final Tessellator v6 = Tessellator.field_78398_a;
            /*SL:81*/this.renderQuad(v6, 2, 13, 13, 2, 0);
            /*SL:82*/this.renderQuad(v6, 2, 13, 12, 1, v4);
            /*SL:83*/this.renderQuad(v6, 2, 13, v5, 1, v3);
            /*SL:84*/GL11.glEnable(3553);
            /*SL:85*/GL11.glEnable(2896);
            /*SL:86*/GL11.glEnable(2929);
            /*SL:87*/GL11.glEnable(3008);
        }
        /*SL:90*/GL11.glPopMatrix();
    }
    
    private void renderQuad(final Tessellator a1, final int a2, final int a3, final int a4, final int a5, final int a6) {
        /*SL:95*/a1.func_78382_b();
        /*SL:96*/a1.func_78378_d(a6);
        /*SL:97*/a1.func_78377_a((double)(a2 + 0), (double)(a3 + 0), 0.0);
        /*SL:98*/a1.func_78377_a((double)(a2 + 0), (double)(a3 + a5), 0.0);
        /*SL:99*/a1.func_78377_a((double)(a2 + a4), (double)(a3 + a5), 0.0);
        /*SL:100*/a1.func_78377_a((double)(a2 + a4), (double)(a3 + 0), 0.0);
        /*SL:101*/a1.func_78381_a();
    }
}
