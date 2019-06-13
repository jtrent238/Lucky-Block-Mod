package mod.lucky;

import none.aqz;
import none.uf;
import none.nt;
import none.nn;
import none.ub;
import none.rs;
import none.sd;
import none.rx;
import none.sb;
import none.rw;
import none.sf;
import none.sc;
import none.rq;
import none.rr;
import none.rz;
import none.ry;
import none.ro;
import none.tv;
import none.sm;
import none.sk;
import none.tl;
import none.td;
import none.tq;
import none.te;
import none.tg;
import none.tn;
import none.tj;
import none.ts;
import none.tw;
import none.tk;
import none.tt;
import none.tr;
import none.tf;
import none.og;
import none.abw;
import none.cl;
import none.xl;
import none.cg;
import none.yc;
import java.util.Random;
import none.ye;
import none.by;

public class LuckyFunction
{
    public static int XOffset;
    public static int ZOffset;
    public static int[] potionEffectList;
    public static int[] mobEggList;
    public static int[] mobIDList;
    public static String[] mobNameList;
    
    public static ye getRandomFireworkRocket(final by v-2) {
        final Random random = /*EL:62*/new Random();
        final ye v0 = /*EL:63*/new ye(yc.bW);
        /*SL:65*/if (v-2 == null) {
            final by v = /*EL:67*/new by();
            final by v2 = /*EL:68*/new by("Fireworks");
            final by v3 = /*EL:69*/new by("Explosion");
            final cg v4 = /*EL:70*/new cg("Explosions");
            /*SL:73*/v3.a("Type", (byte)random.nextInt(5));
            /*SL:74*/v3.a("Flicker", random.nextBoolean());
            /*SL:75*/v3.a("Trail", random.nextBoolean());
            final int v5 = /*EL:76*/random.nextInt(4) + 1;
            final int[] v6 = /*EL:77*/new int[v5];
            /*SL:78*/for (int a1 = 0; a1 < v5; ++a1) {
                /*SL:80*/v6[a1] = Integer.valueOf(xl.c[random.nextInt(14)]);
            }
            /*SL:82*/v3.a("Colors", v6);
            /*SL:85*/v4.a((cl)v3);
            /*SL:88*/v2.a("Explosions", (cl)v4);
            /*SL:89*/v2.a("Flight", (byte)(random.nextInt(2) + 1));
            /*SL:92*/v.a("Fireworks", (cl)v2);
            /*SL:95*/v0.d(v);
        }
        else {
            /*SL:99*/v0.b(v-2);
        }
        /*SL:102*/return v0;
    }
    
    public static by getRandomFireworkNBT() {
        final Random random = /*EL:107*/new Random();
        final by by = /*EL:109*/new by();
        final by by2 = /*EL:110*/new by();
        final by by3 = /*EL:111*/new by();
        final by by4 = /*EL:112*/new by("Fireworks");
        final by by5 = /*EL:113*/new by("Explosion");
        final cg cg = /*EL:114*/new cg("Explosions");
        /*SL:117*/by5.a("Type", (byte)random.nextInt(5));
        /*SL:118*/by5.a("Flicker", random.nextBoolean());
        /*SL:119*/by5.a("Trail", random.nextBoolean());
        final int n = /*EL:120*/random.nextInt(4) + 1;
        final int[] v0 = /*EL:121*/new int[n];
        /*SL:122*/for (int v = 0; v < n; ++v) {
            /*SL:124*/v0[v] = Integer.valueOf(xl.c[random.nextInt(14)]);
        }
        /*SL:126*/by5.a("Colors", v0);
        /*SL:129*/cg.a((cl)by5);
        /*SL:132*/by4.a("Explosions", (cl)cg);
        final byte v2 = /*EL:133*/(byte)(random.nextInt(2) + 1);
        /*SL:134*/by4.a("Flight", v2);
        /*SL:137*/by3.a("Fireworks", (cl)by4);
        /*SL:138*/by2.a("tag", (cl)by3);
        /*SL:139*/by2.a("id", (short)401);
        /*SL:140*/by2.a("Count", (byte)0);
        /*SL:141*/by2.a("Damage", (short)0);
        /*SL:142*/by.a("FireworksItem", (cl)by2);
        /*SL:143*/by.a("Life", 0);
        /*SL:144*/by.a("LifeTime", 10 * (v2 + 1) + random.nextInt(6) + random.nextInt(7));
        /*SL:146*/return by;
    }
    
    public static int getPotionDamage() {
        final Random random = /*EL:151*/new Random();
        final int potionEffect = getPotionEffect();
        int n = /*EL:157*/0;
        int n2 = /*EL:158*/0;
        final int v0 = /*EL:159*/random.nextInt(3);
        /*SL:160*/if (v0 == 1) {
            n = 32;
        }
        /*SL:161*/if (v0 == 2) {
            n2 = 64;
        }
        int v;
        /*SL:165*/if (random.nextInt(2) == 0) {
            v = 0;
        }
        else {
            /*SL:166*/v = 16384;
        }
        System.out.println(/*EL:168*/potionEffect + " " + n + " " + n2 + " " + v);
        /*SL:169*/return potionEffect + n + n2 + v;
    }
    
    public static int getPotionEffect() {
        final Random v1 = /*EL:174*/new Random();
        /*SL:175*/return LuckyFunction.potionEffectList[v1.nextInt(LuckyFunction.potionEffectList.length)];
    }
    
    @Deprecated
    public static og getMob(final abw a1, final String a2, final int a3) {
        /*SL:182*/if (a2.equals(LuckyFunction.mobNameList[0]) || a3 == LuckyFunction.mobIDList[0]) {
            return (og)new tf(a1);
        }
        /*SL:183*/if (a2.equals(LuckyFunction.mobNameList[1]) || a3 == LuckyFunction.mobIDList[1]) {
            return (og)new tr(a1);
        }
        /*SL:184*/if (a2.equals(LuckyFunction.mobNameList[2]) || a3 == LuckyFunction.mobIDList[2]) {
            return (og)new tt(a1);
        }
        /*SL:185*/if (a2.equals(LuckyFunction.mobNameList[3]) || a3 == LuckyFunction.mobIDList[3]) {
            return (og)new tk(a1);
        }
        /*SL:186*/if (a2.equals(LuckyFunction.mobNameList[4]) || a3 == LuckyFunction.mobIDList[4]) {
            return (og)new tw(a1);
        }
        /*SL:187*/if (a2.equals(LuckyFunction.mobNameList[5]) || a3 == LuckyFunction.mobIDList[5]) {
            return (og)new ts(a1);
        }
        /*SL:188*/if (a2.equals(LuckyFunction.mobNameList[6]) || a3 == LuckyFunction.mobIDList[6]) {
            return (og)new tj(a1);
        }
        /*SL:189*/if (a2.equals(LuckyFunction.mobNameList[7]) || a3 == LuckyFunction.mobIDList[7]) {
            return (og)new tn(a1);
        }
        /*SL:190*/if (a2.equals(LuckyFunction.mobNameList[8]) || a3 == LuckyFunction.mobIDList[8]) {
            return (og)new tg(a1);
        }
        /*SL:191*/if (a2.equals(LuckyFunction.mobNameList[9]) || a3 == LuckyFunction.mobIDList[9]) {
            return (og)new te(a1);
        }
        /*SL:192*/if (a2.equals(LuckyFunction.mobNameList[10]) || a3 == LuckyFunction.mobIDList[10]) {
            return (og)new tq(a1);
        }
        /*SL:193*/if (a2.equals(LuckyFunction.mobNameList[11]) || a3 == LuckyFunction.mobIDList[11]) {
            return (og)new td(a1);
        }
        /*SL:194*/if (a2.equals(LuckyFunction.mobNameList[12]) || a3 == LuckyFunction.mobIDList[12]) {
            return (og)new tl(a1);
        }
        /*SL:195*/if (a2.equals(LuckyFunction.mobNameList[13]) || a3 == LuckyFunction.mobIDList[13]) {
            return (og)new sk(a1);
        }
        /*SL:196*/if (a2.equals(LuckyFunction.mobNameList[14]) || a3 == LuckyFunction.mobIDList[14]) {
            return (og)new sm(a1);
        }
        /*SL:197*/if (a2.equals(LuckyFunction.mobNameList[15]) || a3 == LuckyFunction.mobIDList[15]) {
            return (og)new tv(a1);
        }
        /*SL:200*/if (a2.equals(LuckyFunction.mobNameList[16]) || a3 == LuckyFunction.mobIDList[16]) {
            return (og)new ro(a1);
        }
        /*SL:201*/if (a2.equals(LuckyFunction.mobNameList[17]) || a3 == LuckyFunction.mobIDList[17]) {
            return (og)new ry(a1);
        }
        /*SL:202*/if (a2.equals(LuckyFunction.mobNameList[18]) || a3 == LuckyFunction.mobIDList[18]) {
            return (og)new rz(a1);
        }
        /*SL:203*/if (a2.equals(LuckyFunction.mobNameList[19]) || a3 == LuckyFunction.mobIDList[19]) {
            return (og)new rr(a1);
        }
        /*SL:204*/if (a2.equals(LuckyFunction.mobNameList[20]) || a3 == LuckyFunction.mobIDList[20]) {
            return (og)new rq(a1);
        }
        /*SL:205*/if (a2.equals(LuckyFunction.mobNameList[21]) || a3 == LuckyFunction.mobIDList[21]) {
            return (og)new sc(a1);
        }
        /*SL:206*/if (a2.equals(LuckyFunction.mobNameList[22]) || a3 == LuckyFunction.mobIDList[22]) {
            return (og)new sf(a1);
        }
        /*SL:207*/if (a2.equals(LuckyFunction.mobNameList[23]) || a3 == LuckyFunction.mobIDList[23]) {
            return (og)new rw(a1);
        }
        /*SL:208*/if (a2.equals(LuckyFunction.mobNameList[24]) || a3 == LuckyFunction.mobIDList[24]) {
            return (og)new sb(a1);
        }
        /*SL:209*/if (a2.equals(LuckyFunction.mobNameList[25]) || a3 == LuckyFunction.mobIDList[25]) {
            return (og)new rx(a1);
        }
        /*SL:210*/if (a2.equals(LuckyFunction.mobNameList[26]) || a3 == LuckyFunction.mobIDList[26]) {
            return (og)new sd(a1);
        }
        /*SL:211*/if (a2.equals(LuckyFunction.mobNameList[27]) || a3 == LuckyFunction.mobIDList[27]) {
            return (og)new rs(a1);
        }
        /*SL:212*/if (a2.equals(LuckyFunction.mobNameList[28]) || a3 == LuckyFunction.mobIDList[28]) {
            return (og)new ub(a1);
        }
        /*SL:214*/return null;
    }
    
    public static nn getEntity(final abw a1, final int a2, final String a3) {
        /*SL:219*/if (!nt.a.containsKey(a2)) {
            /*SL:221*/return nt.a(a3, a1);
        }
        /*SL:225*/return nt.a(a2, a1);
    }
    
    public static int getMobEgg() {
        final Random v1 = /*EL:231*/new Random();
        /*SL:232*/return LuckyFunction.mobEggList[v1.nextInt(LuckyFunction.mobEggList.length)];
    }
    
    public static void setOffset(final int a1, final int a2) {
        final Random v1 = /*EL:237*/new Random();
        int v2;
        int v3;
        /*SL:247*/do {
            v2 = a2 - v1.nextInt(a2 * 2 + 1);
            v3 = a2 - v1.nextInt(a2 * 2 + 1);
        } while (v2 < a1 && v2 > a1 * -1 && v3 < a1 && v3 > a1 * -1);
        LuckyFunction.XOffset = /*EL:249*/v2;
        LuckyFunction.ZOffset = /*EL:250*/v3;
    }
    
    public static int getXOffset() {
        /*SL:259*/return LuckyFunction.XOffset;
    }
    
    public static int getZOffset() {
        /*SL:264*/return LuckyFunction.ZOffset;
    }
    
    public static int getPlayerDirection(final uf a1, int a2) {
        int v1 = /*EL:269*/(int)a1.A;
        /*SL:271*/a2 = 360 / a2;
        /*SL:273*/if (v1 < 0) {
            v1 += 360;
        }
        /*SL:274*/v1 += a2 / 2;
        /*SL:275*/v1 %= 360;
        final int v2 = /*EL:277*/v1 / a2;
        /*SL:279*/return v2;
    }
    
    public static int adjustHeight(final abw a2, final int a3, final int a4, final int a5, final int v1) {
        boolean v2 = /*EL:284*/false;
        int v3 = /*EL:285*/a5;
        int v4 = /*EL:286*/0;
        /*SL:287*/for (int a6 = a5; a6 < a5 + 16; ++a6) {
            /*SL:289*/if (aqz.t[a2.a(a4, a6, v1)]) {
                /*SL:291*/v4 = 0;
                /*SL:292*/v3 = a6 + 1;
            }
            else {
                /*SL:296*/++v4;
            }
            /*SL:299*/if (v4 == a3) {
                /*SL:301*/v2 = true;
                /*SL:302*/break;
            }
        }
        /*SL:306*/if (v2) {
            /*SL:308*/return v3;
        }
        /*SL:312*/return -1;
    }
    
    static {
        LuckyFunction.potionEffectList = new int[] { 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 14 };
        LuckyFunction.mobEggList = new int[] { 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 65, 66, 90, 91, 92, 93, 94, 95, 96, 98, 100, 120 };
        LuckyFunction.mobIDList = new int[] { 50, 51, 52, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 120 };
        LuckyFunction.mobNameList = new String[] { "creeper", "skeleton", "spider", "giant zombie", "zombie", "slime", "ghast", "zombie pigman", "enderman", "cave spider", "silverfish", "blaze", "magma cube", "ender dragon", "wither", "witch", "bat", "pig", "sheep", "cow", "chicken", "squid", "wolf", "mooshroom", "snow golem", "ocelot", "iron golem", "horse", "villager" };
    }
}
