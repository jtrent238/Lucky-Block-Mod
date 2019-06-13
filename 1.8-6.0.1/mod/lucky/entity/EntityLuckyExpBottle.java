package mod.lucky.entity;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.entity.item.EntityExpBottle;

public class EntityLuckyExpBottle extends EntityExpBottle
{
    public EntityLuckyExpBottle(final World a1, final double a2, final double a3, final double a4) {
        super(a1, a2, a3, a4);
        this.func_70012_b(this.field_70165_t, this.field_70163_u + 0.5, this.field_70161_v, MathHelper.func_76142_g(this.field_70146_Z.nextFloat() * 360.0f), -90.0f + (this.field_70146_Z.nextInt(20) - 10));
        final float v1 = 0.4f;
        this.field_70159_w = -MathHelper.func_76126_a(this.field_70177_z / 180.0f * 3.1415927f) * MathHelper.func_76134_b(this.field_70125_A / 180.0f * 3.1415927f) * v1;
        this.field_70179_y = MathHelper.func_76134_b(this.field_70177_z / 180.0f * 3.1415927f) * MathHelper.func_76134_b(this.field_70125_A / 180.0f * 3.1415927f) * v1;
        this.field_70181_x = -MathHelper.func_76126_a((this.field_70125_A + this.func_70183_g()) / 180.0f * 3.1415927f) * v1;
        this.func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, this.func_70182_d(), 1.0f);
    }
}
