package mod.lucky.drops;

import none.sp;
import java.util.Random;
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
        /*SL:55*/if (this.a == 0) {
            /*SL:57*/this.x();
        }
        else {
            /*SL:61*/this.r = this.u;
            /*SL:62*/this.s = this.v;
            /*SL:63*/this.t = this.w;
            /*SL:64*/++this.c;
            /*SL:65*/this.y -= 0.03999999910593033;
            /*SL:66*/this.d(this.x, this.y, this.z);
            /*SL:67*/this.x *= 0.9800000190734863;
            /*SL:68*/this.y *= 0.9800000190734863;
            /*SL:69*/this.z *= 0.9800000190734863;
            /*SL:71*/if (!this.q.I) {
                final int c = /*EL:73*/ls.c(this.u);
                final int c2 = /*EL:74*/ls.c(this.v);
                final int c3 = /*EL:75*/ls.c(this.w);
                /*SL:77*/if (this.c == 1) {
                    /*SL:79*/this.q.i(c, c2, c3);
                }
                /*SL:82*/if (this.F) {
                    /*SL:84*/this.x *= 0.699999988079071;
                    /*SL:85*/this.z *= 0.699999988079071;
                    /*SL:86*/this.y *= -0.5;
                    /*SL:88*/if (this.q.a(c, c2, c3) != aqz.ah.cF) {
                        /*SL:90*/this.x();
                        /*SL:92*/if (!this.f && this.q.a(this.a, c, c2, c3, true, 1, (nn)null, (ye)null) && !aos.a_(this.q, c, c2 - 1, c3) && this.q.f(c, c2, c3, this.a, this.b, 3)) {
                            /*SL:94*/this.onFinnishedFalling(this.q, this.player, c, c2, c3, this.b, this.a);
                            /*SL:96*/if (this.e != null && aqz.s[this.a] instanceof aoe) {
                                final asp r = /*EL:98*/this.q.r(c, c2, c3);
                                /*SL:100*/if (r != null) {
                                    final by by = /*EL:102*/new by();
                                    /*SL:103*/r.b(by);
                                    /*SL:104*/for (final cl v : this.e.c()) {
                                        /*SL:110*/if (!v.e().equals("x") && !v.e().equals("y") && !v.e().equals("z")) {
                                            /*SL:112*/by.a(v.e(), v.b());
                                        }
                                    }
                                    /*SL:116*/r.a(by);
                                    /*SL:117*/r.e();
                                }
                            }
                        }
                        else/*SL:121*/ if (this.d && !this.f) {
                            /*SL:123*/this.a(new ye(this.a, 1, aqz.s[this.a].a(this.b)), 0.0f);
                        }
                    }
                }
                else/*SL:127*/ if ((this.c > 100 && !this.q.I && (c2 < 1 || c2 > 256)) || this.c > 600) {
                    /*SL:129*/if (this.d) {
                        /*SL:131*/this.a(new ye(this.a, 1, aqz.s[this.a].a(this.b)), 0.0f);
                    }
                    /*SL:134*/this.x();
                }
            }
        }
    }
    
    public void onFinnishedFalling(final abw a6, final uf a7, final int v1, final int v2, final int v3, final int v4, final int v5) {
        /*SL:143*/if (v5 == 42) {
            /*SL:145*/for (int a8 = 0; a8 < 30; ++a8) {
                final Random a9 = /*EL:147*/new Random();
                /*SL:148*/LuckyFunction.setOffset(1, 2);
                /*SL:149*/a6.e(2000, v1 + LuckyFunction.getXOffset(), v2, v3 + LuckyFunction.getZOffset(), a9.nextInt(9));
                /*SL:150*/a6.a((nn)a7, "random.fixx", 3.0f, 1.0f);
            }
        }
        /*SL:155*/if (v5 == 41) {
            /*SL:157*/for (int a10 = 0; a10 < 5; ++a10) {
                /*SL:159*/a6.e(2002, v1, v2, v3, 3);
            }
        }
        /*SL:163*/if (v5 == 57) {
            /*SL:165*/a6.c((nn)new sp(a6, (double)v1, (double)v2, (double)v3));
        }
        /*SL:168*/if (v5 == 133) {
            /*SL:170*/for (int a11 = 0; a11 < 30; ++a11) {
                /*SL:172*/LuckyFunction.setOffset(1, 2);
                /*SL:173*/a6.e(2005, v1 + LuckyFunction.getXOffset(), v2, v3 + LuckyFunction.getZOffset(), 0);
                /*SL:174*/a6.a((nn)a7, "dig.grass", 3.0f, 1.0f);
            }
        }
    }
}
