package mod.lucky;

import java.util.Iterator;
import none.asp;
import none.cl;
import none.by;
import none.aoe;
import none.aos;
import none.ye;
import none.nn;
import none.aqz;
import none.ls;
import none.abw;
import none.uf;
import none.sr;

public class EntityFallingBlock extends sr
{
    private boolean f;
    private int h;
    private float i;
    public uf player;
    
    public EntityFallingBlock(final abw a1, final uf a2, final double a3, final double a4, final double a5, final int a6) {
        this(a1, a2, a3, a4, a5, a6, 0);
    }
    
    public EntityFallingBlock(final abw a1, final uf a2, final double a3, final double a4, final double a5, final int a6, final int a7) {
        super(a1);
        this.d = true;
        this.h = 40;
        this.i = 2.0f;
        this.a = a6;
        this.b = a7;
        this.m = true;
        this.a(0.98f, 0.98f);
        this.N = this.P / 2.0f;
        this.b(a3, a4, a5);
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
        this.r = a3;
        this.s = a4;
        this.t = a5;
        this.player = a2;
    }
    
    public void l_() {
        /*SL:58*/if (this.a == 0) {
            /*SL:60*/this.x();
        }
        else {
            /*SL:64*/this.r = this.u;
            /*SL:65*/this.s = this.v;
            /*SL:66*/this.t = this.w;
            /*SL:67*/++this.c;
            /*SL:68*/this.y -= 0.03999999910593033;
            /*SL:69*/this.d(this.x, this.y, this.z);
            /*SL:70*/this.x *= 0.9800000190734863;
            /*SL:71*/this.y *= 0.9800000190734863;
            /*SL:72*/this.z *= 0.9800000190734863;
            /*SL:74*/if (!this.q.I) {
                final int c = /*EL:76*/ls.c(this.u);
                final int c2 = /*EL:77*/ls.c(this.v);
                final int c3 = /*EL:78*/ls.c(this.w);
                /*SL:80*/if (this.c == 1) {
                    /*SL:82*/this.q.i(c, c2, c3);
                }
                /*SL:85*/if (this.F) {
                    /*SL:87*/this.x *= 0.699999988079071;
                    /*SL:88*/this.z *= 0.699999988079071;
                    /*SL:89*/this.y *= -0.5;
                    /*SL:91*/if (this.q.a(c, c2, c3) != aqz.ah.cF) {
                        /*SL:93*/this.x();
                        /*SL:95*/if (!this.f && this.q.a(this.a, c, c2, c3, true, 1, (nn)null, (ye)null) && !aos.a_(this.q, c, c2 - 1, c3) && this.q.f(c, c2, c3, this.a, this.b, 3)) {
                            /*SL:98*/SpawnOther.onFinnishedFalling(this.q, this.player, c, c2, c3, this.b, this.a);
                            /*SL:100*/if (this.e != null && aqz.s[this.a] instanceof aoe) {
                                final asp r = /*EL:102*/this.q.r(c, c2, c3);
                                /*SL:104*/if (r != null) {
                                    final by by = /*EL:106*/new by();
                                    /*SL:107*/r.b(by);
                                    /*SL:108*/for (final cl v : this.e.c()) {
                                        /*SL:114*/if (!v.e().equals("x") && !v.e().equals("y") && !v.e().equals("z")) {
                                            /*SL:116*/by.a(v.e(), v.b());
                                        }
                                    }
                                    /*SL:120*/r.a(by);
                                    /*SL:121*/r.e();
                                }
                            }
                        }
                        else/*SL:125*/ if (this.d && !this.f) {
                            /*SL:127*/this.a(new ye(this.a, 1, aqz.s[this.a].a(this.b)), 0.0f);
                        }
                    }
                }
                else/*SL:131*/ if ((this.c > 100 && !this.q.I && (c2 < 1 || c2 > 256)) || this.c > 600) {
                    /*SL:133*/if (this.d) {
                        /*SL:135*/this.a(new ye(this.a, 1, aqz.s[this.a].a(this.b)), 0.0f);
                    }
                    /*SL:138*/this.x();
                }
            }
        }
    }
}
