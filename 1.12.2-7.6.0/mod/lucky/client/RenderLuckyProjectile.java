package mod.lucky.client;

import net.minecraft.util.ResourceLocation;
import mod.lucky.entity.EntityLuckyProjectile;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.Render;

public class RenderLuckyProjectile extends Render
{
    protected RenderLuckyProjectile(final RenderManager a1) {
        super(a1);
    }
    
    public void func_76986_a(final Entity a4, final double a5, final double a6, final double v1, final float v3, final float v4) {
        try {
            /*SL:23*/if (a4 instanceof EntityLuckyProjectile) {
                final EntityLuckyProjectile a7 = /*EL:24*/(EntityLuckyProjectile)a4;
                /*SL:25*/if (a7.getItem() != null) {
                    /*SL:26*/this.field_76990_c.func_188391_a((Entity)a7.getItem(), /*EL:27*/a5, a6 - 0.35, v1, 0.0f, v4, true);
                }
            }
        }
        catch (Exception a8) {
            System.err.println(/*EL:30*/"Lucky Block: Error rendering lucky projectile");
            /*SL:31*/a8.printStackTrace();
        }
    }
    
    protected ResourceLocation func_110775_a(final Entity a1) {
        /*SL:37*/return null;
    }
}
