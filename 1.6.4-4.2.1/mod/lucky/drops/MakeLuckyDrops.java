package mod.lucky.drops;

import none.ce;
import none.bx;
import none.cj;
import none.cf;
import none.ck;
import none.cg;
import none.cl;
import none.by;
import java.util.Random;

public class MakeLuckyDrops
{
    public static int amountOfDrops;
    
    public static LuckyDrop[] getDrops(final String v-1) {
        System.out.println(/*EL:20*/"Chosen drop: " + v-1);
        String[] v0 = /*EL:23*/{ /*EL:24*/v-1 };
        boolean v;
        do {
            /*SL:27*/v = false;
            int v2 = /*EL:28*/0;
            /*SL:29*/while (v2 < v0.length) {
                /*SL:31*/if (v0[v2].startsWith("group")) {
                    /*SL:33*/v = true;
                    final String[] a1 = getGroupContents(/*EL:35*/v0[v2], ';');
                    /*SL:36*/v0 = addToArray(v0, a1, v2);
                    /*SL:37*/v2 += a1.length;
                }
                else {
                    /*SL:41*/++v2;
                }
            }
        } while (/*EL:44*/v);
        /*SL:50*/return makeDropsFromArray(v0);
    }
    
    public static String[] getGroupContents(final String a2, final char v1) {
        final String v2 = /*EL:55*/a2.substring(a2.indexOf("(") + 1, a2.lastIndexOf(")"));
        String[] v3 = splitString(/*EL:56*/v2, v1);
        /*SL:59*/if (a2.startsWith("group:")) {
            final int a3 = getInt(/*EL:61*/a2.substring(a2.indexOf(":") + 1, a2.indexOf("(")));
            /*SL:62*/v3 = decreaseDropAmount(v3, a3);
        }
        /*SL:65*/return v3;
    }
    
    public static String[] splitString(final String v1, final char v2) {
        final char[] v3 = /*EL:71*/v1.toCharArray();
        final int[] v4 = /*EL:72*/new int[1024];
        int v5 = /*EL:73*/1;
        int v6 = /*EL:74*/0;
        /*SL:76*/v4[0] = -1;
        /*SL:78*/for (int a1 = 1; a1 < v3.length; ++a1) {
            /*SL:80*/if (v3[a1] == '(') {
                /*SL:82*/++v6;
            }
            /*SL:84*/if (v3[a1] == ')') {
                /*SL:86*/--v6;
            }
            /*SL:88*/if (v3[a1] == v2 && v6 == 0) {
                /*SL:90*/v4[v5] = a1;
                /*SL:91*/++v5;
            }
        }
        /*SL:94*/v4[v5] = v1.length();
        final String[] v7 = /*EL:97*/new String[v5];
        /*SL:98*/for (int a2 = 0; a2 < v7.length; ++a2) {
            /*SL:100*/v7[a2] = v1.substring(v4[a2] + 1, v4[a2 + 1]);
        }
        /*SL:103*/return v7;
    }
    
    public static String[] decreaseDropAmount(final String[] v1, final int v2) {
        final Random v3 = /*EL:108*/new Random();
        final boolean[] v4 = /*EL:110*/new boolean[v1.length];
        int v5 = /*EL:111*/0;
        do {
            final int a1 = /*EL:114*/v3.nextInt(v1.length);
            /*SL:115*/if (!v4[a1]) {
                /*SL:117*/v4[a1] = true;
                /*SL:118*/++v5;
            }
        } while (/*EL:121*/v5 != v2);
        final String[] v6 = /*EL:124*/new String[v2];
        /*SL:125*/v5 = 0;
        /*SL:126*/for (int a2 = 0; a2 < v1.length; ++a2) {
            /*SL:128*/if (v4[a2]) {
                /*SL:130*/v6[v5] = v1[a2];
                /*SL:131*/++v5;
            }
        }
        /*SL:135*/return v6;
    }
    
    public static String[] addToArray(final String[] a3, final String[] v1, final int v2) {
        final String[] v3 = /*EL:140*/new String[a3.length - 1 + v1.length];
        int v4 = /*EL:141*/0;
        /*SL:143*/for (int a4 = 0; a4 < a3.length; ++a4) {
            /*SL:145*/if (a4 == v2) {
                /*SL:147*/for (int a5 = 0; a5 < v1.length; ++a5) {
                    /*SL:149*/v3[v4] = v1[a5];
                    /*SL:150*/++v4;
                }
            }
            else {
                /*SL:155*/v3[v4] = a3[a4];
                /*SL:156*/++v4;
            }
        }
        /*SL:160*/return v3;
    }
    
    public static LuckyDrop[] makeDropsFromArray(final String[] v-3) {
        final LuckyDrop[] array = /*EL:165*/new LuckyDrop[1024];
        MakeLuckyDrops.amountOfDrops = /*EL:166*/0;
        int n = /*EL:167*/0;
        /*SL:168*/for (int v0 = 0; v0 < v-3.length; ++v0) {
            final LuckyDrop v = setDropProperties(/*EL:170*/v-3[v0]);
            /*SL:172*/for (int v2 = v.getAmount(), a1 = 0; a1 < v2; ++a1) {
                /*SL:175*/if (v.getReinitialize()) {
                    array[n] = setDropProperties(v-3[v0]);
                }
                else {
                    /*SL:176*/array[n] = v;
                }
                /*SL:178*/array[n].setAmount(1);
                /*SL:179*/++n;
            }
        }
        /*SL:182*/return array;
    }
    
    public static LuckyDrop setDropProperties(final String v-9) {
        final LuckyDrop luckyDrop = /*EL:187*/new LuckyDrop();
        final String[] splitString = splitString(/*EL:188*/v-9, ',');
        /*SL:190*/for (int i = 0; i < splitString.length; ++i) {
            final String[] array = /*EL:192*/{ /*EL:193*/splitString[i].substring(0, splitString[i].indexOf("=")), /*EL:194*/splitString[i].substring(splitString[i].indexOf("=") + 1) };
            /*SL:196*/if (array[0].equals("type")) {
                luckyDrop.setType(array[1]);
            }
            /*SL:197*/if (array[0].equals("ID")) {
                luckyDrop.setID(getInt(array[1]));
            }
            /*SL:198*/if (array[0].equals("name")) {
                luckyDrop.setName(array[1]);
            }
            /*SL:199*/if (array[0].equals("damage")) {
                luckyDrop.setDamage(getInt(array[1]));
            }
            /*SL:200*/if (array[0].equals("amount")) {
                luckyDrop.setAmount(getInt(array[1]));
            }
            /*SL:201*/if (array[0].equals("reinitialize")) {
                luckyDrop.setReinitialize(Boolean.valueOf(array[1]));
            }
            /*SL:202*/if (array[0].equals("relativeToPlayer")) {
                luckyDrop.setRelativToPlayer(Boolean.valueOf(array[1]));
            }
            /*SL:203*/if (array[0].equals("posXOffset")) {
                luckyDrop.setXOffset(getInt(array[1]));
            }
            /*SL:204*/if (array[0].equals("posYOffset")) {
                luckyDrop.setYOffset(getInt(array[1]));
            }
            /*SL:205*/if (array[0].equals("posZOffset")) {
                luckyDrop.setZOffset(getInt(array[1]));
            }
            /*SL:206*/if (array[0].equals("duration")) {
                luckyDrop.setEffectDuration(getInt(array[1]));
            }
            /*SL:207*/if (array[0].equals("adjustHeight")) {
                luckyDrop.setAdjustHeight(getInt(array[1]));
            }
            /*SL:209*/if (array[0].equals("scatterOffset")) {
                final Random random = /*EL:211*/new Random();
                final String[] split = /*EL:213*/array[1].split("-");
                final int intValue = /*EL:214*/Integer.valueOf(split[0]);
                final int intValue2 = /*EL:215*/Integer.valueOf(split[1]);
                int a1;
                int v1;
                do {
                    /*SL:219*/a1 = intValue2 - random.nextInt(intValue2 * 2 + 1);
                    /*SL:220*/v1 = intValue2 - random.nextInt(intValue2 * 2 + 1);
                } while (/*EL:222*/a1 < intValue && a1 > intValue * -1 && v1 < intValue && v1 > intValue * -1);
                /*SL:224*/luckyDrop.setXOffset(a1);
                /*SL:225*/luckyDrop.setZOffset(v1);
            }
            /*SL:232*/if (array[0].equals("NBTTag")) {
                /*SL:234*/if (array[1].equals("randomFireworkNBTTag")) {
                    /*SL:236*/luckyDrop.setNBTTag(LuckyFunction.getRandomFireworkNBT());
                }
                else {
                    /*SL:240*/luckyDrop.setNBTTag(getNBTTag(array[1]));
                }
            }
        }
        /*SL:245*/return luckyDrop;
    }
    
    public static by getNBTTag(final String v-8) {
        final String substring = /*EL:250*/v-8.substring(0, v-8.indexOf("("));
        final by by = /*EL:252*/new by(substring);
        /*SL:254*/by.p(substring);
        final String[] groupContents = getGroupContents(/*EL:256*/v-8, ',');
        /*SL:257*/for (int i = 0; i < groupContents.length; ++i) {
            final String substring2 = /*EL:259*/groupContents[i].substring(0, groupContents[i].indexOf("="));
            final String formattedString = getFormattedString(/*EL:260*/groupContents[i].substring(groupContents[i].indexOf("=") + 1, groupContents[i].indexOf("(")));
            final String substring3 = /*EL:261*/groupContents[i].substring(groupContents[i].indexOf("(") + 1, groupContents[i].lastIndexOf(")"));
            final String v0 = /*EL:262*/groupContents[i].substring(groupContents[i].indexOf("=") + 1);
            /*SL:264*/if (substring2.equals("text")) {
                /*SL:266*/by.a(formattedString, getFormattedString(substring3));
            }
            /*SL:268*/if (substring2.equals("number")) {
                /*SL:270*/by.a(formattedString, getInt(substring3));
            }
            /*SL:272*/if (substring2.equals("shortNumber")) {
                /*SL:274*/by.a(formattedString, (short)getInt(substring3));
            }
            /*SL:276*/if (substring2.equals("byteNumber")) {
                /*SL:278*/by.a(formattedString, (byte)getInt(substring3));
            }
            /*SL:280*/if (substring2.equals("numberArray")) {
                final String[] v = /*EL:282*/substring3.split("|");
                final int[] v2 = /*EL:283*/new int[v.length];
                int a1 = /*EL:284*/0;
                while (i < v.length) {
                    /*SL:286*/v2[a1] = getInt(v[a1]);
                    ++a1;
                }
                /*SL:288*/by.a(formattedString, v2);
            }
            /*SL:290*/if (substring2.equals("flag")) {
                /*SL:292*/by.a(formattedString, (boolean)Boolean.valueOf(substring3));
            }
            /*SL:294*/if (substring2.equals("NBTTag")) {
                /*SL:296*/by.a(formattedString, (cl)getNBTTag(v0));
            }
            /*SL:298*/if (substring2.equals("NBTTagList")) {
                /*SL:300*/by.a(formattedString, (cl)getNBTTagList(v0));
            }
        }
        /*SL:304*/return by;
    }
    
    public static cg getNBTTagList(final String v-8) {
        final String substring = /*EL:309*/v-8.substring(0, v-8.indexOf("("));
        final cg cg = /*EL:311*/new cg(substring);
        /*SL:313*/cg.p(substring);
        final String[] groupContents = getGroupContents(/*EL:315*/v-8, ',');
        /*SL:316*/for (int i = 0; i < groupContents.length; ++i) {
            final String substring2 = /*EL:318*/groupContents[i].substring(0, groupContents[i].indexOf("="));
            final String formattedString = getFormattedString(/*EL:319*/groupContents[i].substring(groupContents[i].indexOf("=") + 1, groupContents[i].indexOf("(")));
            final String substring3 = /*EL:320*/groupContents[i].substring(groupContents[i].indexOf("(") + 1, groupContents[i].lastIndexOf(")"));
            final String v0 = /*EL:321*/groupContents[i].substring(groupContents[i].indexOf("=") + 1);
            /*SL:323*/if (substring2.equals("text")) {
                /*SL:325*/cg.a((cl)new ck(formattedString, getFormattedString(substring3)));
            }
            /*SL:327*/if (substring2.equals("number")) {
                /*SL:329*/cg.a((cl)new cf(formattedString, getInt(substring3)));
            }
            /*SL:331*/if (substring2.equals("shortNumber")) {
                /*SL:333*/cg.a((cl)new cj(formattedString, (short)getInt(substring3)));
            }
            /*SL:335*/if (substring2.equals("byteNumber")) {
                /*SL:337*/cg.a((cl)new bx(formattedString, (byte)getInt(substring3)));
            }
            /*SL:339*/if (substring2.equals("numberArray")) {
                final String[] v = /*EL:341*/substring3.split(":");
                final int[] v2 = /*EL:342*/new int[v.length];
                int a1 = /*EL:343*/0;
                while (i < v.length) {
                    /*SL:345*/v2[a1] = getInt(v[a1]);
                    ++a1;
                }
                /*SL:347*/cg.a((cl)new ce(formattedString, v2));
            }
            /*SL:349*/if (substring2.equals("NBTTag")) {
                /*SL:351*/cg.a((cl)getNBTTag(v0));
            }
            /*SL:353*/if (substring2.equals("NBTTagList")) {
                /*SL:355*/cg.a((cl)getNBTTagList(v0));
            }
        }
        /*SL:359*/return cg;
    }
    
    public static String getFormattedString(String a1) {
        /*SL:364*/a1 = a1.replaceAll("\\\\t", "\t");
        /*SL:365*/a1 = a1.replaceAll("\\\\b", "\b");
        /*SL:366*/a1 = a1.replaceAll("\\\\n", "\n");
        /*SL:367*/a1 = a1.replaceAll("\\\\r", "\r");
        /*SL:368*/a1 = a1.replaceAll("\\\\f", "\f");
        /*SL:370*/return a1;
    }
    
    public static int getInt(final String v-1) {
        /*SL:374*/if (v-1.startsWith("random-")) {
            final Random a1 = /*EL:376*/new Random();
            final String[] v1 = /*EL:378*/v-1.split("-");
            final int v2 = /*EL:380*/Integer.valueOf(v1[1]);
            final int v3 = /*EL:381*/Integer.valueOf(v1[2]);
            /*SL:383*/return a1.nextInt(v3 - v2 + 1) + v2;
        }
        /*SL:385*/if (v-1.startsWith("randomPositiveOrNegative-")) {
            final Random v4 = /*EL:387*/new Random();
            final String[] v1 = /*EL:389*/v-1.split("-");
            final int v2 = /*EL:391*/Integer.valueOf(v1[1]);
            final int v3 = /*EL:392*/Integer.valueOf(v1[2]);
            int v5 = /*EL:394*/v4.nextInt(v3 - v2 + 1) + v2;
            /*SL:395*/if (v4.nextInt(2) == 0) {
                v5 *= -1;
            }
            System.out.println(/*EL:397*/v5);
            /*SL:398*/return v5;
        }
        /*SL:400*/if (v-1.startsWith("randomPotionDamage")) {
            /*SL:402*/return LuckyFunction.getPotionDamage();
        }
        /*SL:404*/if (v-1.startsWith("randomPotionEffect")) {
            /*SL:406*/return LuckyFunction.getPotionEffect();
        }
        /*SL:408*/if (v-1.startsWith("randomSpawnEggDamage")) {
            /*SL:410*/return LuckyFunction.getMobEgg();
        }
        /*SL:413*/return Integer.valueOf(v-1);
    }
    
    static {
        MakeLuckyDrops.amountOfDrops = 0;
    }
}
