package mod.lucky.drops;

import none.rq;
import none.ss;
import none.ye;
import none.yc;
import none.nn;
import none.nb;
import none.ata;
import none.ls;
import none.of;
import none.abw;
import none.ur;

public class EntityLuckyEgg extends ur
{
    public EntityLuckyEgg(final abw a1, final of a2, final double a3, final double a4, final double a5) {
        super(a1, a3, a4, a5);
        this.b(a3, a4 + 0.5, a5, ls.g(this.ab.nextFloat() * 360.0f), -90.0f + (this.ab.nextInt(20) - 10));
        final float v1 = 0.4f;
        this.x = -ls.a(this.A / 180.0f * 3.1415927f) * ls.b(this.B / 180.0f * 3.1415927f) * v1;
        this.z = ls.b(this.A / 180.0f * 3.1415927f) * ls.b(this.B / 180.0f * 3.1415927f) * v1;
        this.y = -ls.a((this.B + this.d()) / 180.0f * 3.1415927f) * v1;
        this.c(this.x, this.y, this.z, this.c(), 1.0f);
    }
    
    protected void a(final ata v0) {
        /*SL:31*/if (v0.g != null) {
            /*SL:33*/v0.g.a(nb.a((nn)this, (nn)this.h()), 0.0f);
        }
        /*SL:36*/if (!this.q.I) {
            /*SL:39*/if (this.ab.nextInt(3) == 0) {
                final ss a1 = /*EL:41*/new ss(this.q, this.u, this.v, this.w, new ye(yc.p));
                /*SL:42*/this.q.d((nn)a1);
            }
            else {
                final rq v = /*EL:46*/new rq(this.q);
                /*SL:47*/v.c(-24000);
                /*SL:48*/v.b(this.u, this.v, this.w, this.A, 0.0f);
                /*SL:49*/this.q.d((nn)v);
            }
        }
        /*SL:53*/for (int v2 = 0; v2 < 8; ++v2) {
            /*SL:55*/this.q.a("snowballpoof", this.u, this.v, this.w, 0.0, 0.0, 0.0);
        }
        /*SL:58*/if (!this.q.I) {
            /*SL:60*/this.x();
        }
    }
    
    protected float e() {
        /*SL:66*/return 0.03f;
    }
    
    protected float c() {
        /*SL:71*/return 0.6f;
    }
    
    protected float d() {
        /*SL:76*/return 0.0f;
    }
}
