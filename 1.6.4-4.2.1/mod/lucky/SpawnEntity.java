package mod.lucky;

import none.og;
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
    public static boolean wasHeightAdjusted;
    
    public static void spawnEntity(final abw a3, final uf a4, final int a5, final int a6, final int a7, final String a8, final int a9, final by a10, final boolean a11, final int a12, final int a13, final int v1, final int v2) {
        int v3 = /*EL:34*/0;
        int v4 = /*EL:35*/0;
        int v5 = /*EL:36*/0;
        /*SL:38*/if (a11) {
            /*SL:40*/v3 = Integer.valueOf(ls.c(a4.u));
            /*SL:41*/v4 = Integer.valueOf(ls.c(a4.v));
            /*SL:42*/v5 = Integer.valueOf(ls.c(a4.w));
        }
        else {
            /*SL:46*/v3 = a5;
            /*SL:47*/v4 = a6;
            /*SL:48*/v5 = a7;
        }
        /*SL:52*/v3 += a12;
        /*SL:53*/v4 += a13;
        /*SL:54*/if (v2 > 0) {
            /*SL:56*/v4 = LuckyFunction.adjustHeight(a3, v2, v3, v4, v5);
            /*SL:57*/if (v4 == -1) {
                return;
            }
        }
        /*SL:59*/v5 += v1;
        nn v6 = /*EL:61*/null;
        /*SL:63*/if (a8.equals("XP")) {
            final int a14 = /*EL:65*/oa.a(1);
            /*SL:66*/v6 = (nn)new oa(a3, v3 + 0.5, (double)v4, v5 + 0.5, a14);
        }
        /*SL:69*/if (a8.equals("lightning")) {
            /*SL:71*/a3.c((nn)new sp(a3, v3 + 0.5, (double)v4, v5 + 0.5));
            /*SL:72*/return;
        }
        /*SL:75*/if (a8.equals("fireworks")) {
            final uk a15 = /*EL:77*/new uk(a3, v3 + 0.5, (double)v4, v5 + 0.5, (ye)null);
            /*SL:78*/a15.a(a10);
            /*SL:79*/v6 = (nn)a15;
        }
        /*SL:81*/if (a8.equals("luckyegg")) {
            /*SL:83*/v6 = (nn)new EntityLuckyEgg(a3, (of)a4, v3 + 0.5, v4, v5 + 0.5);
        }
        /*SL:85*/if (a8.equals("expbottle")) {
            /*SL:87*/v6 = (nn)new EntityLuckyExpBottle(a3, v3 + 0.5, v4, v5 + 0.5);
        }
        final nn v7 = /*EL:90*/LuckyFunction.getEntity(a3, a9, a8);
        final og v8 = /*EL:91*/(og)v7;
        /*SL:92*/if (v8 != null) {
            /*SL:94*/v8.b(v3 + 0.5, (double)v4, v5 + 0.5, 0.0f, 0.0f);
            /*SL:95*/if (a10 != null) {
                v8.a(a10);
            }
            /*SL:96*/v6 = (nn)v8;
        }
        /*SL:99*/if (v2 > 0) {
            /*SL:101*/v6.v = LuckyFunction.adjustHeight(a3, (int)Math.ceil(v6.P), v3, v4, v5);
            /*SL:102*/if (v6.v == -1.0) {
                return;
            }
        }
        /*SL:105*/if (v6 != null) {
            spawnEntity(/*EL:107*/a3, v6);
        }
    }
    
    public static boolean spawnEntity(final abw a2, final nn v1) {
        try {
            /*SL:115*/a2.d(v1);
            /*SL:116*/return true;
        }
        catch (Exception a3) {
            /*SL:119*/return false;
        }
    }
}
