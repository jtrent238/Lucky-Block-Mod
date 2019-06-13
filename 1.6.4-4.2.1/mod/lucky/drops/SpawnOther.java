package mod.lucky.drops;

import none.asm;
import mod.lucky.Lucky;
import none.cg;
import none.ye;
import none.mo;
import none.mk;
import net.minecraftforge.common.ChestGenHooks;
import none.nn;
import none.aqz;
import none.asp;
import none.ary;
import none.ls;
import none.by;
import none.uf;
import none.abw;
import java.util.Random;

public class SpawnOther
{
    public static Random rand;
    
    public static void spawnOther(final abw v-30, final uf v-29, final int v-28, final int v-27, final int v-26, final String v-25, final int v-24, final String v-23, final int v-22, final by v-21, final boolean v-20, final int v-19, final int v-18, final int v-17, final int v-16) {
        int intValue = /*EL:33*/0;
        int n = /*EL:34*/0;
        int intValue2 = /*EL:35*/0;
        /*SL:37*/if (v-20) {
            /*SL:39*/intValue = Integer.valueOf(ls.c(v-29.u));
            /*SL:40*/n = Integer.valueOf(ls.c(v-29.v));
            /*SL:41*/intValue2 = Integer.valueOf(ls.c(v-29.w));
        }
        else {
            /*SL:45*/intValue = v-28;
            /*SL:46*/n = v-27;
            /*SL:47*/intValue2 = v-26;
        }
        /*SL:51*/intValue += v-19;
        /*SL:52*/n += v-18;
        /*SL:53*/if (v-16 > 0) {
            /*SL:55*/n = LuckyFunction.adjustHeight(v-30, v-16, intValue, n, intValue2);
            /*SL:56*/if (n == -1) {
                return;
            }
        }
        /*SL:58*/intValue2 += v-17;
        /*SL:60*/if (v-25.equals("block")) {
            setBlockWithMeta(/*EL:62*/v-30, intValue, n, intValue2, v-24, v-22);
            try {
                /*SL:65*/if (v-21 != null) {
                    final String a1 = /*EL:67*/v-21.e();
                    asp a2 = /*EL:68*/null;
                    /*SL:69*/if (a1.equals("chest")) {
                        /*SL:71*/a2 = (asp)new ary();
                        /*SL:72*/((ary)a2).a(v-21);
                    }
                    else {
                        /*SL:76*/a2 = new asp();
                        /*SL:77*/a2.a(v-21);
                    }
                    /*SL:79*/v-30.a(intValue, n, intValue2, a2);
                }
            }
            catch (Exception ex2) {}
        }
        /*SL:85*/if (v-25.equals("particle")) {
            /*SL:87*/v-30.e(v-24, intValue, n, intValue2, v-22);
        }
        /*SL:90*/if (v-25.equals("structure")) {
            /*SL:92*/if (v-23.equals("anviltrap")) {
                makeCage(/*EL:94*/v-30, v-29, intValue, n, intValue2, 3);
                final String[] a3 = /*EL:95*/{ "", "Look Up", "", "", "" };
                makeCageSign(/*EL:96*/v-30, v-29, intValue, n, intValue2, a3);
                removeColumn(/*EL:97*/v-30, intValue, n, intValue2, 64);
                setBlock(/*EL:98*/v-30, intValue, n + 64, intValue2, aqz.cm.cF);
                setBlock(/*EL:99*/v-30, intValue, n + 63, intValue2, aqz.cm.cF);
            }
            /*SL:101*/if (v-23.equals("lavatrap")) {
                makeCage(/*EL:103*/v-30, v-29, intValue, n, intValue2, 4);
                final String[] a4 = /*EL:104*/{ "", "Look Up", "", "", "" };
                makeCageSign(/*EL:105*/v-30, v-29, intValue, n, intValue2, a4);
                setBlock(/*EL:106*/v-30, intValue, n + 3, intValue2, aqz.H.cF);
            }
            /*SL:108*/if (v-23.equals("watertrap")) {
                makeWaterCage(/*EL:110*/v-30, v-29, intValue, n, intValue2, 3);
                setBlock(/*EL:111*/v-30, intValue, n + 1, intValue2, aqz.F.cF);
            }
            /*SL:113*/if (v-23.equals("pittrap")) {
                makeRect(/*EL:115*/v-30, v-29, intValue - 1, n - 60, intValue2 - 1, 3, 60, 3, 0);
                makeRect(/*EL:116*/v-30, v-29, intValue - 1, n - 59, intValue2 - 1, 3, 1, 3, aqz.H.cF);
                makeRect(/*EL:117*/v-30, v-29, intValue - 1, n - 58, intValue2 - 1, 3, 1, 3, aqz.ab.cF);
            }
            /*SL:119*/if (v-23.equals("fort")) {
                makeFort(/*EL:121*/v-30, v-29, intValue, n, intValue2);
            }
            /*SL:123*/if (v-23.equals("temple")) {
                makeTemple(/*EL:125*/v-30, v-29, intValue, n, intValue2);
            }
            /*SL:127*/if (v-23.equals("fallingblock")) {
                removeColumn(/*EL:129*/v-30, intValue, n, intValue2, 7);
                final EntityFallingBlock a5 = /*EL:130*/new EntityFallingBlock(v-30, v-29, intValue + 0.5, n + 0.5 + 6.0, intValue2 + 0.5, v-24, v-22);
                spawnEntity(/*EL:131*/v-30, (nn)a5);
            }
            /*SL:133*/if (v-23.equals("bedrock")) {
                setBlock(/*EL:135*/v-30, v-28, v-27, v-26, 7);
                final String[] a6 = /*EL:136*/{ "", "Well, there's", "your problem.", "", "" };
                makeSign(/*EL:137*/v-30, intValue, n + 1, intValue2, 63, LuckyFunction.getPlayerDirection(v-29, 16) + 8, a6);
            }
            /*SL:139*/if (v-23.equals("explosion")) {
                /*SL:141*/v-30.a((nn)null, (double)intValue, (double)n, (double)intValue2, (float)(v-22 * 2), true);
            }
            /*SL:143*/if (v-23.equals("chest")) {
                final int playerDirection = /*EL:145*/LuckyFunction.getPlayerDirection(v-29, 4);
                setBlock(/*EL:146*/v-30, intValue, n, intValue2, aqz.az.cF);
                /*SL:147*/switch (playerDirection) {
                    case 0: {
                        setBlockMeta(/*EL:149*/v-30, intValue, n, intValue2, 2);
                        break;
                    }
                    case 1: {
                        setBlockMeta(/*EL:150*/v-30, intValue, n, intValue2, 5);
                        break;
                    }
                    case 2: {
                        setBlockMeta(/*EL:151*/v-30, intValue, n, intValue2, 3);
                        break;
                    }
                    case 3: {
                        setBlockMeta(/*EL:152*/v-30, intValue, n, intValue2, 4);
                        break;
                    }
                }
                final ary ary = /*EL:154*/(ary)v-30.r(intValue, n, intValue2);
                /*SL:156*/if (ary != null && ary != null) {
                    try {
                        /*SL:160*/if (v-21 != null) {
                            /*SL:162*/if (v-21.b("type")) {
                                final String a7 = /*EL:164*/v-21.i("type");
                                /*SL:165*/mk.a(SpawnOther.rand, ChestGenHooks.getItems(a7, SpawnOther.rand), (mo)ary, ChestGenHooks.getCount(a7, SpawnOther.rand));
                            }
                            else {
                                final cg m = /*EL:170*/v-21.m("items");
                                final int e = /*EL:172*/v-21.e("min");
                                final int e2 = /*EL:173*/v-21.e("max");
                                final int n2 = /*EL:174*/(e < e2) ? (e + SpawnOther.rand.nextInt(e2 - e)) : e2;
                                final mk[] array = /*EL:176*/new mk[m.c()];
                                /*SL:177*/for (int i = 0; i < m.c(); ++i) {
                                    final by a8 = /*EL:180*/(by)m.b(i);
                                    final int a9 = /*EL:184*/a8.e("id");
                                    final int a10 = /*EL:185*/a8.e("damage");
                                    int a11 = /*EL:186*/a8.e("minAmount");
                                    int v1 = /*EL:187*/a8.e("maxAmount");
                                    final int v2 = /*EL:188*/a8.e("weight");
                                    /*SL:190*/if (a11 == 0) {
                                        a11 = 1;
                                    }
                                    /*SL:191*/if (v1 == 0) {
                                        v1 = 1;
                                    }
                                    final ye a14;
                                    /*SL:193*/if (a8.b("item")) {
                                        final by a12 = /*EL:195*/a8.l("item");
                                        final ye a13 = /*EL:196*/ye.a(a12);
                                    }
                                    else {
                                        /*SL:200*/a14 = new ye(a9, 1, a10);
                                    }
                                    /*SL:203*/array[i] = new mk(a14, a11, v1, v2);
                                }
                                /*SL:206*/mk.a(SpawnOther.rand, array, (mo)ary, n2);
                            }
                        }
                    }
                    catch (Exception ex) {
                        /*SL:212*/ex.printStackTrace();
                    }
                }
            }
        }
    }
    
    public static int getEdgeDistance(final int a1, final int a2) {
        int v1 = /*EL:222*/a2 - a1;
        /*SL:223*/if (v1 < 0) {
            v1 *= -1;
        }
        /*SL:225*/return v1;
    }
    
    public static boolean isEdgeSingleSide(final int a1, final int a2, final int a3) {
        int v1 = /*EL:230*/a2 - a1;
        /*SL:231*/if (v1 < 0) {
            v1 *= -1;
        }
        /*SL:233*/return v1 == a3;
    }
    
    public static boolean isEdge(final int a1, final int a2, final int a3, final int a4, final int a5) {
        /*SL:240*/return (getEdgeDistance(a1, a3) == a5 && getEdgeDistance(a2, a4) <= a5) || (getEdgeDistance(/*EL:241*/a2, a4) == a5 && getEdgeDistance(a1, a3) <= a5);
    }
    
    public static boolean isCenterEdge(final int a1, final int a2, final int a3, final int a4, final int a5) {
        /*SL:248*/return (getEdgeDistance(a1, a3) == a5 && getEdgeDistance(a2, a4) == 0) || (getEdgeDistance(/*EL:249*/a2, a4) == a5 && getEdgeDistance(a1, a3) == 0);
    }
    
    public static boolean isCorner(final int a1, final int a2, final int a3, final int a4, final int a5) {
        /*SL:256*/return getEdgeDistance(a1, a3) == a5 && getEdgeDistance(a2, a4) == a5;
    }
    
    public static void makeRect(final abw a4, final uf a5, final int a6, final int a7, final int a8, final int a9, final int v1, final int v2, final int v3) {
        /*SL:262*/for (int a10 = a7; a10 < a7 + v1; ++a10) {
            /*SL:264*/for (int a11 = a6; a11 < a6 + a9; ++a11) {
                /*SL:266*/for (int a12 = a8; a12 < a8 + v2; ++a12) {
                    setBlock(/*EL:268*/a4, a11, a10, a12, v3);
                }
            }
        }
    }
    
    public static void makeCage(final abw a4, final uf a5, final int a6, final int v1, final int v2, final int v3) {
        /*SL:276*/for (int a7 = v1 - 1; a7 < v1 + v3; ++a7) {
            /*SL:278*/for (int a8 = a6 - 1; a8 < a6 + 2; ++a8) {
                /*SL:280*/for (int a9 = v2 - 1; a9 < v2 + 2; ++a9) {
                    /*SL:282*/if (a7 == v1 - 1) {
                        setBlock(/*EL:284*/a4, a8, a7, a9, aqz.br.cF);
                    }
                    else/*SL:286*/ if (isEdge(a6, v2, a8, a9, 1)) {
                        setBlock(/*EL:288*/a4, a8, a7, a9, aqz.bu.cF);
                    }
                    else {
                        setBlock(/*EL:292*/a4, a8, a7, a9, 0);
                    }
                }
            }
        }
    }
    
    public static void makeWaterCage(final abw a4, final uf a5, final int a6, final int v1, final int v2, final int v3) {
        /*SL:301*/for (int a7 = v1 - 1; a7 < v1 + v3; ++a7) {
            /*SL:303*/for (int a8 = a6 - 1; a8 < a6 + 2; ++a8) {
                /*SL:305*/for (int a9 = v2 - 1; a9 < v2 + 2; ++a9) {
                    /*SL:307*/if (a7 == v1 - 1) {
                        setBlock(/*EL:309*/a4, a8, a7, a9, aqz.br.cF);
                    }
                    else/*SL:311*/ if (isEdge(a6, v2, a8, a9, 1) || a7 == v1 + v3 - 1) {
                        /*SL:313*/if (a7 == v1 + 1) {
                            setBlock(/*EL:315*/a4, a8, a7, a9, aqz.R.cF);
                        }
                        else {
                            setBlock(/*EL:319*/a4, a8, a7, a9, aqz.au.cF);
                        }
                    }
                    else {
                        setBlock(/*EL:324*/a4, a8, a7, a9, 0);
                    }
                }
            }
        }
    }
    
    public static void makeFort(final abw a4, final uf a5, final int v1, final int v2, final int v3) {
        /*SL:333*/for (int a6 = v2 - 1; a6 < v2 + 3; ++a6) {
            /*SL:335*/for (int a7 = v1 - 2; a7 < v1 + 3; ++a7) {
                /*SL:337*/for (int a8 = v3 - 2; a8 < v3 + 3; ++a8) {
                    /*SL:339*/if (a6 == v2 - 1) {
                        setBlock(/*EL:341*/a4, a7, a6, a8, aqz.V.cF);
                    }
                    /*SL:343*/if (a6 == v2) {
                        /*SL:345*/if (isEdge(v1, v3, a7, a8, 2)) {
                            setBlockWithMeta(/*EL:347*/a4, a7, a6, a8, aqz.V.cF, 2);
                        }
                        else/*SL:349*/ if (isCorner(v1, v3, a7, a8, 1)) {
                            setBlock(/*EL:351*/a4, a7, a6, a8, Lucky.blockLucky.cF);
                        }
                        else {
                            setBlock(/*EL:355*/a4, a7, a6, a8, 0);
                        }
                    }
                    /*SL:358*/if (a6 == v2 + 1) {
                        /*SL:360*/if (isEdge(v1, v3, a7, a8, 2)) {
                            setBlockWithMeta(/*EL:362*/a4, a7, a6, a8, aqz.V.cF, 2);
                        }
                        else {
                            setBlock(/*EL:366*/a4, a7, a6, a8, 0);
                        }
                    }
                    /*SL:369*/if (a6 == v2 + 2) {
                        /*SL:371*/if (isCorner(v1, v3, a7, a8, 2)) {
                            setBlock(/*EL:373*/a4, a7, a6, a8, aqz.am.cF);
                        }
                        else {
                            setBlock(/*EL:377*/a4, a7, a6, a8, 0);
                        }
                    }
                }
            }
        }
    }
    
    public static void makeTemple(final abw a4, final uf a5, final int v1, final int v2, final int v3) {
        /*SL:388*/for (int a6 = v2 - 1; a6 < v2 + 5; ++a6) {
            /*SL:390*/for (int a7 = v1 - 3; a7 < v1 + 4; ++a7) {
                /*SL:392*/for (int a8 = v3 - 3; a8 < v3 + 4; ++a8) {
                    /*SL:394*/if (a6 == v2 - 1) {
                        setBlock(/*EL:396*/a4, a7, a6, a8, aqz.V.cF);
                    }
                    /*SL:398*/if (a6 == v2) {
                        /*SL:400*/if (isEdge(v1, v3, a7, a8, 3)) {
                            setBlockWithMeta(/*EL:402*/a4, a7, a6, a8, aqz.V.cF, 2);
                        }
                        else/*SL:404*/ if (isCorner(v1, v3, a7, a8, 2)) {
                            setBlock(/*EL:406*/a4, a7, a6, a8, Lucky.blockLucky.cF);
                        }
                        else {
                            setBlock(/*EL:410*/a4, a7, a6, a8, 0);
                        }
                    }
                    /*SL:413*/if (a6 == v2 + 1) {
                        /*SL:415*/if (isEdge(v1, v3, a7, a8, 2) || isCorner(v1, v3, a7, a8, 3)) {
                            setBlockWithMeta(/*EL:417*/a4, a7, a6, a8, aqz.V.cF, 2);
                        }
                        else {
                            setBlock(/*EL:421*/a4, a7, a6, a8, 0);
                        }
                    }
                    /*SL:425*/if (a6 == v2 + 2) {
                        /*SL:427*/if (isEdge(v1, v3, a7, a8, 1) || isCorner(v1, v3, a7, a8, 3)) {
                            setBlockWithMeta(/*EL:429*/a4, a7, a6, a8, aqz.V.cF, 2);
                        }
                        else {
                            setBlock(/*EL:433*/a4, a7, a6, a8, 0);
                        }
                    }
                    /*SL:436*/if (a6 == v2 + 3) {
                        /*SL:438*/if (isEdge(v1, v3, a7, a8, 0) || isCorner(v1, v3, a7, a8, 3)) {
                            setBlock(/*EL:440*/a4, a7, a6, a8, aqz.am.cF);
                        }
                        else {
                            setBlock(/*EL:444*/a4, a7, a6, a8, 0);
                        }
                    }
                }
            }
        }
        final int v4 = /*EL:452*/LuckyFunction.getPlayerDirection(a5, 4);
        /*SL:453*/switch (v4) {
            case 0: {
                setBlockWithMeta(/*EL:455*/a4, v1, v2 + 1, v3 - 1, 50, 3);
                break;
            }
            case 1: {
                setBlockWithMeta(/*EL:456*/a4, v1 + 1, v2 + 1, v3, 50, 2);
                break;
            }
            case 2: {
                setBlockWithMeta(/*EL:457*/a4, v1, v2 + 1, v3 + 1, 50, 4);
                break;
            }
            case 3: {
                setBlockWithMeta(/*EL:458*/a4, v1 - 1, v2 + 1, v3, 50, 1);
                break;
            }
        }
    }
    
    public static void makeCageSign(final abw a1, final uf a2, final int a3, final int a4, final int a5, final String[] a6) {
        final int v1 = /*EL:464*/LuckyFunction.getPlayerDirection(a2, 4);
        /*SL:465*/switch (v1) {
            case 0: {
                setBlock(/*EL:467*/a1, a3, a4 - 1, a5 + 2, aqz.br.cF);
                makeSign(a1, a3, a4, a5 + 2, 63, 8, a6);
                setBlock(a1, a3, a4 + 1, a5 + 2, 0);
                break;
            }
            case 1: {
                setBlock(/*EL:468*/a1, a3 - 2, a4 - 1, a5, aqz.br.cF);
                makeSign(a1, a3 - 2, a4, a5, 63, 12, a6);
                setBlock(a1, a3 - 2, a4 + 1, a5, 0);
                break;
            }
            case 2: {
                setBlock(/*EL:469*/a1, a3, a4 - 1, a5 - 2, aqz.br.cF);
                makeSign(a1, a3, a4, a5 - 2, 63, 0, a6);
                setBlock(a1, a3, a4 + 1, a5 - 2, 0);
                break;
            }
            case 3: {
                setBlock(/*EL:470*/a1, a3 + 2, a4 - 1, a5, aqz.br.cF);
                makeSign(a1, a3 + 2, a4, a5, 63, 4, a6);
                setBlock(a1, a3 + 2, a4 + 1, a5, 0);
                break;
            }
        }
    }
    
    public static void makeSign(final abw a2, final int a3, final int a4, final int a5, final int a6, final int a7, final String[] v1) {
        setBlockWithMeta(/*EL:476*/a2, a3, a4, a5, a6, a7);
        final asm v2 = /*EL:478*/new asm();
        /*SL:479*/for (int a8 = 0; a8 < 4; ++a8) {
            /*SL:481*/v2.a[a8] = v1[a8];
        }
        /*SL:484*/a2.a(a3, a4, a5, (asp)v2);
    }
    
    public static void removeColumn(final abw a2, final int a3, final int a4, final int a5, final int v1) {
        /*SL:489*/for (int a6 = a4; a6 < a4 + v1; ++a6) {
            setBlock(/*EL:491*/a2, a3, a6, a5, 0);
        }
    }
    
    public static boolean setBlock(final abw a2, final int a3, final int a4, final int a5, final int v1) {
        try {
            /*SL:501*/a2.f(a3, a4, a5, v1, 0, 3);
            /*SL:502*/return true;
        }
        catch (Exception a6) {
            /*SL:506*/a6.printStackTrace();
            /*SL:509*/return false;
        }
    }
    
    public static boolean setBlockWithMeta(final abw a2, final int a3, final int a4, final int a5, final int a6, final int v1) {
        try {
            /*SL:516*/a2.f(a3, a4, a5, a6, v1, 3);
            /*SL:517*/return true;
        }
        catch (Exception a7) {
            /*SL:521*/a7.printStackTrace();
            /*SL:523*/return false;
        }
    }
    
    public static boolean setBlockMeta(final abw a2, final int a3, final int a4, final int a5, final int v1) {
        try {
            /*SL:530*/a2.b(a3, a4, a5, v1, 3);
            /*SL:531*/return true;
        }
        catch (Exception a6) {
            /*SL:535*/a6.printStackTrace();
            /*SL:537*/return false;
        }
    }
    
    public static boolean spawnEntity(final abw a2, final nn v1) {
        try {
            /*SL:544*/a2.d(v1);
            /*SL:545*/return true;
        }
        catch (Exception a3) {
            /*SL:549*/a3.printStackTrace();
            /*SL:551*/return false;
        }
    }
    
    private static int adjustHeight(final abw a2, final int a3, final int a4, final int a5, final int v1) {
        int v2 = /*EL:556*/a5;
        int v3 = /*EL:557*/0;
        /*SL:558*/for (int a6 = a5; a6 < a5 + 16; ++a6) {
            /*SL:560*/if (!aqz.t[a2.a(a4, a6, v1)]) {
                /*SL:562*/++v3;
            }
            else {
                /*SL:567*/v3 = 0;
                /*SL:568*/v2 = a6 + 1;
            }
            /*SL:571*/if (v3 == a3) {
                /*SL:573*/break;
            }
        }
        /*SL:576*/return v2;
    }
    
    static {
        SpawnOther.rand = new Random();
    }
}
