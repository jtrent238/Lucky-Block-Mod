package mod.lucky.entity;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MathHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraft.entity.projectile.EntityEgg;

public class EntityLuckyEgg extends EntityEgg
{
    public EntityLuckyEgg(final World a1, final EntityLivingBase a2, final double a3, final double a4, final double a5) {
        super(a1, a3, a4, a5);
        this.func_70012_b(a3, a4 + 0.5, a5, MathHelper.func_76142_g(this.field_70146_Z.nextFloat() * 360.0f), -90.0f + (this.field_70146_Z.nextInt(20) - 10));
        final float v1 = 0.4f;
        this.field_70159_w = -MathHelper.func_76126_a(this.field_70177_z / 180.0f * 3.1415927f) * MathHelper.func_76134_b(this.field_70125_A / 180.0f * 3.1415927f) * v1;
        this.field_70179_y = MathHelper.func_76134_b(this.field_70177_z / 180.0f * 3.1415927f) * MathHelper.func_76134_b(this.field_70125_A / 180.0f * 3.1415927f) * v1;
        this.field_70181_x = -MathHelper.func_76126_a((this.field_70125_A + this.func_70183_g()) / 180.0f * 3.1415927f) * v1;
        this.func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, this.func_70182_d(), 1.0f);
    }
    
    protected void func_70184_a(final MovingObjectPosition v0) {
        /*SL:32*/if (v0.field_72308_g != null) {
            /*SL:34*/v0.field_72308_g.func_70097_a(DamageSource.func_76356_a((Entity)this, (Entity)this.func_85052_h()), 0.0f);
        }
        /*SL:37*/if (!this.field_70170_p.field_72995_K) {
            /*SL:40*/if (this.field_70146_Z.nextInt(3) == 0) {
                final EntityItem a1 = /*EL:42*/new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, new ItemStack(Items.field_151045_i));
                /*SL:43*/this.field_70170_p.func_72838_d((Entity)a1);
            }
            else {
                final EntityChicken v = /*EL:47*/new EntityChicken(this.field_70170_p);
                /*SL:48*/v.func_70873_a(-24000);
                /*SL:49*/v.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, 0.0f);
                /*SL:50*/this.field_70170_p.func_72838_d((Entity)v);
            }
        }
        /*SL:54*/for (int v2 = 0; v2 < 8; ++v2) {
            /*SL:56*/this.field_70170_p.func_175688_a(EnumParticleTypes.SNOWBALL, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0, 0.0, 0.0, new int[0]);
        }
        /*SL:59*/if (!this.field_70170_p.field_72995_K) {
            /*SL:61*/this.func_70106_y();
        }
    }
    
    protected float func_70185_h() {
        /*SL:68*/return 0.03f;
    }
    
    protected float func_70182_d() {
        /*SL:74*/return 0.6f;
    }
    
    protected float func_70183_g() {
        /*SL:80*/return 0.0f;
    }
}
