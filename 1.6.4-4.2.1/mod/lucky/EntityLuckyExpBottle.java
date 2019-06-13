package mod.lucky;

import none.ls;
import none.abw;
import none.ut;

public class EntityLuckyExpBottle extends ut
{
    public EntityLuckyExpBottle(final abw a1, final double a2, final double a3, final double a4) {
        super(a1, a2, a3, a4);
        this.b(this.u, this.v + 0.5, this.w, ls.g(this.ab.nextFloat() * 360.0f), -90.0f + (this.ab.nextInt(20) - 10));
        final float v1 = 0.4f;
        this.x = -ls.a(this.A / 180.0f * 3.1415927f) * ls.b(this.B / 180.0f * 3.1415927f) * v1;
        this.z = ls.b(this.A / 180.0f * 3.1415927f) * ls.b(this.B / 180.0f * 3.1415927f) * v1;
        this.y = -ls.a((this.B + this.d()) / 180.0f * 3.1415927f) * v1;
        this.c(this.x, this.y, this.z, this.c(), 1.0f);
    }
}
