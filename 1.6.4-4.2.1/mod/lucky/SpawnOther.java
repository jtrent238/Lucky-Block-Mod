package mod.lucky;

import none.sp;
import java.util.Random;
import none.asp;
import none.asm;
import none.nn;
import none.aqz;
import none.ls;
import none.uf;
import none.abw;

public class SpawnOther
{
    public static boolean wasHeightAdjusted;
    
    public static void spawnOther(final abw a5, final uf a6, final int a7, final int a8, final int a9, final String a10, final int a11, final String a12, final int a13, final boolean a14, final int v1, final int v2, final int v3, final int v4) {
        int v5 = /*EL:24*/0;
        int v6 = /*EL:25*/0;
        int v7 = /*EL:26*/0;
        /*SL:28*/if (a14) {
            /*SL:30*/v5 = Integer.valueOf(ls.c(a6.u));
            /*SL:31*/v6 = Integer.valueOf(ls.c(a6.v));
            /*SL:32*/v7 = Integer.valueOf(ls.c(a6.w));
        }
        else {
            /*SL:36*/v5 = a7;
            /*SL:37*/v6 = a8;
            /*SL:38*/v7 = a9;
        }
        /*SL:42*/v5 += v1;
        /*SL:43*/v6 += v2;
        /*SL:44*/if (v4 > 0) {
            /*SL:46*/v6 = LuckyFunction.adjustHeight(a5, v4, v5, v6, v7);
            /*SL:47*/if (v6 == -1) {
                return;
            }
        }
        /*SL:49*/v7 += v3;
        /*SL:51*/if (a10.equals("block")) {
            setBlockWithMeta(/*EL:53*/a5, v5, v6, v7, a11, a13);
        }
        /*SL:55*/if (a10.equals("particle")) {
            /*SL:57*/a5.e(a11, v5, v6, v7, a13);
        }
        /*SL:60*/if (a10.equals("structure")) {
            /*SL:62*/if (a12.equals("anviltrap")) {
                makeCage(/*EL:64*/a5, a6, v5, v6, v7, 3);
                final String[] a15 = /*EL:65*/{ "", "Look Up", "", "", "" };
                makeCageSign(/*EL:66*/a5, a6, v5, v6, v7, a15);
                removeColumn(/*EL:67*/a5, v5, v6, v7, 64);
                setBlock(/*EL:68*/a5, v5, v6 + 64, v7, aqz.cm.cF);
                setBlock(/*EL:69*/a5, v5, v6 + 63, v7, aqz.cm.cF);
            }
            /*SL:71*/if (a12.equals("lavatrap")) {
                makeCage(/*EL:73*/a5, a6, v5, v6, v7, 5);
                final String[] a16 = /*EL:74*/{ "", "Look Up", "", "", "" };
                makeCageSign(/*EL:75*/a5, a6, v5, v6, v7, a16);
                setBlock(/*EL:76*/a5, v5, v6 + 4, v7, aqz.H.cF);
            }
            /*SL:78*/if (a12.equals("fort")) {
                makeFort(/*EL:80*/a5, a6, v5, v6, v7);
            }
            /*SL:82*/if (a12.equals("temple")) {
                makeTemple(/*EL:84*/a5, a6, v5, v6, v7);
            }
            /*SL:86*/if (a12.equals("fallingblock")) {
                removeColumn(/*EL:88*/a5, v5, v6, v7, 7);
                final EntityFallingBlock a17 = /*EL:89*/new EntityFallingBlock(a5, a6, v5 + 0.5, v6 + 0.5 + 6.0, v7 + 0.5, a11, a13);
                spawnEntity(/*EL:90*/a5, (nn)a17);
            }
            /*SL:92*/if (a12.equals("bedrock")) {
                setBlock(/*EL:94*/a5, a7, a8, a9, 7);
                final String[] a18 = /*EL:95*/{ "", "Well, there's", "your problem.", "", "" };
                makeSign(/*EL:96*/a5, v5, v6 + 1, v7, 63, LuckyFunction.getPlayerDirection(a6, 16) + 8, a18);
            }
            /*SL:98*/if (a12.equals("explosion")) {
                /*SL:100*/a5.a((nn)null, (double)v5, (double)v6, (double)v7, (float)(a13 * 2), true);
            }
        }
    }
    
    public static int getEdgeDistance(final int a1, final int a2) {
        int v1 = /*EL:107*/a2 - a1;
        /*SL:108*/if (v1 < 0) {
            v1 *= -1;
        }
        /*SL:110*/return v1;
    }
    
    public static boolean isEdgeSingleSide(final int a1, final int a2, final int a3) {
        int v1 = /*EL:115*/a2 - a1;
        /*SL:116*/if (v1 < 0) {
            v1 *= -1;
        }
        /*SL:118*/return v1 == a3;
    }
    
    public static boolean isEdge(final int a1, final int a2, final int a3, final int a4, final int a5) {
        /*SL:125*/return (getEdgeDistance(a1, a3) == a5 && getEdgeDistance(a2, a4) <= a5) || (getEdgeDistance(/*EL:126*/a2, a4) == a5 && getEdgeDistance(a1, a3) <= a5);
    }
    
    public static boolean isCenterEdge(final int a1, final int a2, final int a3, final int a4, final int a5) {
        /*SL:133*/return (getEdgeDistance(a1, a3) == a5 && getEdgeDistance(a2, a4) == 0) || (getEdgeDistance(/*EL:134*/a2, a4) == a5 && getEdgeDistance(a1, a3) == 0);
    }
    
    public static boolean isCorner(final int a1, final int a2, final int a3, final int a4, final int a5) {
        /*SL:141*/return getEdgeDistance(a1, a3) == a5 && getEdgeDistance(a2, a4) == a5;
    }
    
    public static void makeCage(final abw a4, final uf a5, final int a6, final int v1, final int v2, final int v3) {
        /*SL:147*/for (int a7 = v1 - 1; a7 < v1 + v3; ++a7) {
            /*SL:149*/for (int a8 = a6 - 1; a8 < a6 + 2; ++a8) {
                /*SL:151*/for (int a9 = v2 - 1; a9 < v2 + 2; ++a9) {
                    /*SL:153*/if (a7 == v1 - 1) {
                        setBlock(/*EL:155*/a4, a8, a7, a9, aqz.br.cF);
                    }
                    else/*SL:157*/ if (isEdge(a6, v2, a8, a9, 1)) {
                        setBlock(/*EL:159*/a4, a8, a7, a9, aqz.bu.cF);
                    }
                    else {
                        setBlock(/*EL:163*/a4, a8, a7, a9, 0);
                    }
                }
            }
        }
    }
    
    public static void makeFort(final abw a4, final uf a5, final int v1, final int v2, final int v3) {
        /*SL:172*/for (int a6 = v2 - 1; a6 < v2 + 3; ++a6) {
            /*SL:174*/for (int a7 = v1 - 2; a7 < v1 + 3; ++a7) {
                /*SL:176*/for (int a8 = v3 - 2; a8 < v3 + 3; ++a8) {
                    /*SL:178*/if (a6 == v2 - 1) {
                        setBlock(/*EL:180*/a4, a7, a6, a8, aqz.V.cF);
                    }
                    /*SL:182*/if (a6 == v2) {
                        /*SL:184*/if (isEdge(v1, v3, a7, a8, 2)) {
                            setBlockWithMeta(/*EL:186*/a4, a7, a6, a8, aqz.V.cF, 2);
                        }
                        else/*SL:188*/ if (isCorner(v1, v3, a7, a8, 1)) {
                            setBlock(/*EL:190*/a4, a7, a6, a8, Lucky.blockLucky.cF);
                        }
                        else {
                            setBlock(/*EL:194*/a4, a7, a6, a8, 0);
                        }
                    }
                    /*SL:197*/if (a6 == v2 + 1) {
                        /*SL:199*/if (isEdge(v1, v3, a7, a8, 2)) {
                            setBlockWithMeta(/*EL:201*/a4, a7, a6, a8, aqz.V.cF, 2);
                        }
                        else {
                            setBlock(/*EL:205*/a4, a7, a6, a8, 0);
                        }
                    }
                    /*SL:208*/if (a6 == v2 + 2) {
                        /*SL:210*/if (isCorner(v1, v3, a7, a8, 2)) {
                            setBlock(/*EL:212*/a4, a7, a6, a8, aqz.am.cF);
                        }
                        else {
                            setBlock(/*EL:216*/a4, a7, a6, a8, 0);
                        }
                    }
                }
            }
        }
    }
    
    public static void makeTemple(final abw a4, final uf a5, final int v1, final int v2, final int v3) {
        /*SL:227*/for (int a6 = v2 - 1; a6 < v2 + 5; ++a6) {
            /*SL:229*/for (int a7 = v1 - 3; a7 < v1 + 4; ++a7) {
                /*SL:231*/for (int a8 = v3 - 3; a8 < v3 + 4; ++a8) {
                    /*SL:233*/if (a6 == v2 - 1) {
                        setBlock(/*EL:235*/a4, a7, a6, a8, aqz.V.cF);
                    }
                    /*SL:237*/if (a6 == v2) {
                        /*SL:239*/if (isEdge(v1, v3, a7, a8, 3)) {
                            setBlockWithMeta(/*EL:241*/a4, a7, a6, a8, aqz.V.cF, 2);
                        }
                        else/*SL:243*/ if (isCorner(v1, v3, a7, a8, 2)) {
                            setBlock(/*EL:245*/a4, a7, a6, a8, Lucky.blockLucky.cF);
                        }
                        else {
                            setBlock(/*EL:249*/a4, a7, a6, a8, 0);
                        }
                    }
                    /*SL:252*/if (a6 == v2 + 1) {
                        /*SL:254*/if (isEdge(v1, v3, a7, a8, 2) || isCorner(v1, v3, a7, a8, 3)) {
                            setBlockWithMeta(/*EL:256*/a4, a7, a6, a8, aqz.V.cF, 2);
                        }
                        else {
                            setBlock(/*EL:260*/a4, a7, a6, a8, 0);
                        }
                    }
                    /*SL:264*/if (a6 == v2 + 2) {
                        /*SL:266*/if (isEdge(v1, v3, a7, a8, 1) || isCorner(v1, v3, a7, a8, 3)) {
                            setBlockWithMeta(/*EL:268*/a4, a7, a6, a8, aqz.V.cF, 2);
                        }
                        else {
                            setBlock(/*EL:272*/a4, a7, a6, a8, 0);
                        }
                    }
                    /*SL:275*/if (a6 == v2 + 3) {
                        /*SL:277*/if (isEdge(v1, v3, a7, a8, 0) || isCorner(v1, v3, a7, a8, 3)) {
                            setBlock(/*EL:279*/a4, a7, a6, a8, aqz.am.cF);
                        }
                        else {
                            setBlock(/*EL:283*/a4, a7, a6, a8, 0);
                        }
                    }
                }
            }
        }
        final int v4 = /*EL:291*/LuckyFunction.getPlayerDirection(a5, 4);
        /*SL:292*/switch (v4) {
            case 0: {
                setBlockWithMeta(/*EL:294*/a4, v1, v2 + 1, v3 - 1, 50, 3);
                break;
            }
            case 1: {
                setBlockWithMeta(/*EL:295*/a4, v1 + 1, v2 + 1, v3, 50, 2);
                break;
            }
            case 2: {
                setBlockWithMeta(/*EL:296*/a4, v1, v2 + 1, v3 + 1, 50, 4);
                break;
            }
            case 3: {
                setBlockWithMeta(/*EL:297*/a4, v1 - 1, v2 + 1, v3, 50, 1);
                break;
            }
        }
    }
    
    public static void makeCageSign(final abw a1, final uf a2, final int a3, final int a4, final int a5, final String[] a6) {
        final int v1 = /*EL:303*/LuckyFunction.getPlayerDirection(a2, 4);
        /*SL:304*/switch (v1) {
            case 0: {
                setBlock(/*EL:306*/a1, a3, a4 - 1, a5 + 2, aqz.br.cF);
                makeSign(a1, a3, a4, a5 + 2, 63, 8, a6);
                setBlock(a1, a3, a4 + 1, a5 + 2, 0);
                break;
            }
            case 1: {
                setBlock(/*EL:307*/a1, a3 - 2, a4 - 1, a5, aqz.br.cF);
                makeSign(a1, a3 - 2, a4, a5, 63, 12, a6);
                setBlock(a1, a3 - 2, a4 + 1, a5, 0);
                break;
            }
            case 2: {
                setBlock(/*EL:308*/a1, a3, a4 - 1, a5 - 2, aqz.br.cF);
                makeSign(a1, a3, a4, a5 - 2, 63, 0, a6);
                setBlock(a1, a3, a4 + 1, a5 - 2, 0);
                break;
            }
            case 3: {
                setBlock(/*EL:309*/a1, a3 + 2, a4 - 1, a5, aqz.br.cF);
                makeSign(a1, a3 + 2, a4, a5, 63, 4, a6);
                setBlock(a1, a3 + 2, a4 + 1, a5, 0);
                break;
            }
        }
    }
    
    public static void makeSign(final abw a2, final int a3, final int a4, final int a5, final int a6, final int a7, final String[] v1) {
        setBlockWithMeta(/*EL:315*/a2, a3, a4, a5, a6, a7);
        final asm v2 = /*EL:317*/new asm();
        /*SL:318*/for (int a8 = 0; a8 < 4; ++a8) {
            /*SL:320*/v2.a[a8] = v1[a8];
        }
        /*SL:323*/a2.a(a3, a4, a5, (asp)v2);
    }
    
    public static void removeColumn(final abw a2, final int a3, final int a4, final int a5, final int v1) {
        /*SL:328*/for (int a6 = a4; a6 < a4 + v1; ++a6) {
            setBlock(/*EL:330*/a2, a3, a6, a5, 0);
        }
    }
    
    public static void onFinnishedFalling(final abw a5, final uf a6, final int a7, final int v1, final int v2, final int v3, final int v4) {
        /*SL:337*/if (v4 == 42) {
            /*SL:339*/for (int a8 = 0; a8 < 30; ++a8) {
                final Random a9 = /*EL:341*/new Random();
                /*SL:342*/LuckyFunction.setOffset(1, 2);
                /*SL:343*/a5.e(2000, a7 + LuckyFunction.getXOffset(), v1, v2 + LuckyFunction.getZOffset(), a9.nextInt(9));
                /*SL:344*/a5.a((nn)a6, "fireworks.launch", 3.0f, 1.0f);
            }
        }
        /*SL:349*/if (v4 == 41) {
            /*SL:351*/for (int a10 = 0; a10 < 5; ++a10) {
                /*SL:353*/a5.e(2002, a7, v1, v2, 3);
            }
        }
        /*SL:357*/if (v4 == 57) {
            /*SL:359*/a5.c((nn)new sp(a5, (double)a7, (double)v1, (double)v2));
        }
        /*SL:362*/if (v4 == 133) {
            /*SL:364*/for (int a11 = 0; a11 < 30; ++a11) {
                /*SL:366*/LuckyFunction.setOffset(1, 2);
                /*SL:367*/a5.e(2005, a7 + LuckyFunction.getXOffset(), v1, v2 + LuckyFunction.getZOffset(), 0);
                /*SL:368*/a5.a((nn)a6, "dig.grass", 3.0f, 1.0f);
            }
        }
    }
    
    public static boolean setBlock(final abw a2, final int a3, final int a4, final int a5, final int v1) {
        try {
            /*SL:377*/a2.f(a3, a4, a5, v1, 0, 3);
            /*SL:378*/return true;
        }
        catch (Exception a6) {
            /*SL:382*/a6.printStackTrace();
            /*SL:385*/return false;
        }
    }
    
    public static boolean setBlockWithMeta(final abw a2, final int a3, final int a4, final int a5, final int a6, final int v1) {
        try {
            /*SL:392*/a2.f(a3, a4, a5, a6, v1, 3);
            /*SL:393*/return true;
        }
        catch (Exception a7) {
            /*SL:397*/a7.printStackTrace();
            /*SL:399*/return false;
        }
    }
    
    public static boolean spawnEntity(final abw a2, final nn v1) {
        try {
            /*SL:406*/a2.d(v1);
            /*SL:407*/return true;
        }
        catch (Exception a3) {
            /*SL:411*/a3.printStackTrace();
            /*SL:413*/return false;
        }
    }
    
    private static int adjustHeight(final abw a2, final int a3, final int a4, final int a5, final int v1) {
        SpawnOther.wasHeightAdjusted = /*EL:418*/false;
        int v2 = /*EL:419*/a5;
        int v3 = /*EL:420*/0;
        /*SL:421*/for (int a6 = a5; a6 < a5 + 16; ++a6) {
            /*SL:423*/if (!aqz.t[a2.a(a4, a6, v1)]) {
                /*SL:425*/++v3;
            }
            else {
                /*SL:430*/v3 = 0;
                /*SL:431*/v2 = a6 + 1;
            }
            /*SL:434*/if (v3 == a3) {
                SpawnOther.wasHeightAdjusted = /*EL:436*/true;
                /*SL:437*/break;
            }
        }
        /*SL:440*/return v2;
    }
}
