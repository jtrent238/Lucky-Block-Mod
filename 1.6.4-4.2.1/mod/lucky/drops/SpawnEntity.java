package mod.lucky.drops;

import none.og;
import none.tc;
import java.util.Random;
import none.of;
import none.ye;
import none.uk;
import none.nn;
import none.sp;
import none.oa;
import none.ls;
import none.by;
import none.uf;
import none.abw;

public class SpawnEntity
{
    public static void spawnEntity(final abw a6, final uf a7, final int a8, final int a9, final int a10, final String a11, final int a12, final by a13, final boolean v1, final int v2, final int v3, final int v4, final int v5) {
        int v6 = /*EL:33*/0;
        int v7 = /*EL:34*/0;
        int v8 = /*EL:35*/0;
        /*SL:37*/if (v1) {
            /*SL:39*/v6 = Integer.valueOf(ls.c(a7.u));
            /*SL:40*/v7 = Integer.valueOf(ls.c(a7.v));
            /*SL:41*/v8 = Integer.valueOf(ls.c(a7.w));
        }
        else {
            /*SL:45*/v6 = a8;
            /*SL:46*/v7 = a9;
            /*SL:47*/v8 = a10;
        }
        /*SL:51*/v6 += v2;
        /*SL:52*/v7 += v3;
        /*SL:53*/if (v5 > 0) {
            /*SL:55*/v7 = LuckyFunction.adjustHeight(a6, v5, v6, v7, v8);
            /*SL:56*/if (v7 == -1) {
                return;
            }
        }
        /*SL:58*/v8 += v4;
        nn v9 = /*EL:60*/null;
        /*SL:62*/if (a11.equals("XP")) {
            final int a14 = /*EL:64*/oa.a(1);
            /*SL:65*/v9 = (nn)new oa(a6, v6 + 0.5, (double)v7, v8 + 0.5, a14);
        }
        /*SL:68*/if (a11.equals("lightning")) {
            /*SL:70*/a6.c((nn)new sp(a6, v6 + 0.5, (double)v7, v8 + 0.5));
            /*SL:71*/return;
        }
        /*SL:74*/if (a11.equals("fireworks")) {
            final uk a15 = /*EL:76*/new uk(a6, v6 + 0.5, (double)v7, v8 + 0.5, (ye)null);
            /*SL:77*/a15.a(a13);
            /*SL:78*/v9 = (nn)a15;
        }
        /*SL:80*/if (a11.equals("luckyegg")) {
            /*SL:82*/v9 = (nn)new EntityLuckyEgg(a6, (of)a7, v6 + 0.5, v7, v8 + 0.5);
        }
        /*SL:84*/if (a11.equals("tnt")) {
            final Random a16 = /*EL:86*/new Random();
            /*SL:87*/v9 = (nn)new tc(a6, v6 + 0.5, (double)v7, v8 + 0.5, (of)a7);
            /*SL:88*/v9.b((double)v6, (double)(v7 + 0.5f), (double)v8, ls.g(a16.nextFloat() * 360.0f), -90.0f + (a16.nextInt(60) - 30));
            final float a17 = /*EL:89*/0.4f;
            /*SL:90*/v9.x = -ls.a(v9.A / 180.0f * 3.1415927f) * ls.b(v9.B / 180.0f * 3.1415927f) * a17;
            /*SL:91*/v9.z = ls.b(v9.A / 180.0f * 3.1415927f) * ls.b(v9.B / 180.0f * 3.1415927f) * a17;
            /*SL:92*/v9.y = -ls.a(v9.B / 180.0f * 3.1415927f) * a17;
            /*SL:93*/((tc)v9).a = 50;
            setThrowableHeading(/*EL:94*/v9, v9.x, v9.y, v9.z, 0.6f, 1.0f);
            /*SL:96*/a6.a(v9, "random.fuse", 1.0f, 1.0f);
        }
        /*SL:98*/if (a11.equals("expbottle")) {
            /*SL:100*/v9 = (nn)new EntityLuckyExpBottle(a6, v6 + 0.5, v7, v8 + 0.5);
        }
        final nn v10 = /*EL:103*/LuckyFunction.getEntity(a6, a12, a11);
        final og v11 = /*EL:104*/(og)v10;
        /*SL:105*/if (v11 != null) {
            /*SL:107*/v11.b(v6 + 0.5, (double)v7, v8 + 0.5, 0.0f, 0.0f);
            /*SL:108*/if (a13 != null) {
                v11.a(a13);
            }
            /*SL:109*/v9 = (nn)v11;
        }
        /*SL:112*/if (v5 > 0) {
            /*SL:114*/v9.v = LuckyFunction.adjustHeight(a6, (int)Math.ceil(v9.P), v6, v7, v8);
            /*SL:115*/if (v9.v == -1.0) {
                return;
            }
        }
        try {
            /*SL:120*/if (a13 != null) {
                v9.f(a13);
            }
        }
        catch (Exception ex) {}
        /*SL:124*/if (v9 != null) {
            spawnEntity(/*EL:126*/a6, v9);
        }
    }
    
    public static boolean spawnEntity(final abw a2, final nn v1) {
        try {
            /*SL:134*/a2.d(v1);
            /*SL:135*/return true;
        }
        catch (Exception a3) {
            /*SL:138*/return false;
        }
    }
    
    public static void setThrowableHeading(final nn a1, double a2, double a3, double a4, final float a5, final float a6) {
        final Random v1 = /*EL:143*/new Random();
        final float v2 = /*EL:144*/ls.a(a2 * a2 + a3 * a3 + a4 * a4);
        /*SL:145*/a2 /= v2;
        /*SL:146*/a3 /= v2;
        /*SL:147*/a4 /= v2;
        /*SL:148*/a2 += v1.nextGaussian() * 0.007499999832361937 * a6;
        /*SL:149*/a3 += v1.nextGaussian() * 0.007499999832361937 * a6;
        /*SL:150*/a4 += v1.nextGaussian() * 0.007499999832361937 * a6;
        /*SL:151*/a2 *= a5;
        /*SL:152*/a3 *= a5;
        /*SL:153*/a4 *= a5;
        /*SL:154*/a1.x = a2;
        /*SL:155*/a1.y = a3;
        /*SL:156*/a1.z = a4;
        final float v3 = /*EL:157*/ls.a(a2 * a2 + a4 * a4);
        final float n = /*EL:158*/(float)(Math.atan2(a2, a4) * 180.0 / 3.141592653589793);
        a1.A = n;
        a1.C = n;
        final float n2 = /*EL:159*/(float)(Math.atan2(a3, v3) * 180.0 / 3.141592653589793);
        a1.B = n2;
        a1.D = n2;
    }
}
