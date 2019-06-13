package mod.lucky.drop;

import mod.lucky.drop.value.ValueParser;
import java.util.ArrayList;
import java.util.Random;

public class LuckyDropRaw
{
    private String rawDrop;
    private int luck;
    private float chance;
    private final Random random;
    
    public LuckyDropRaw(String v2) {
        this.luck = 2;
        this.chance = 1.0f;
        if ((v2.startsWith("(") && v2.endsWith(")")) || (v2.startsWith("[") && v2.endsWith("]")) || (v2.startsWith("{") && v2.endsWith("}"))) {
            v2 = v2.substring(1, v2.length() - 1);
        }
        this.rawDrop = v2;
        this.random = new Random();
        try {
            this.getDropProperties();
        }
        catch (Exception a1) {
            System.out.println("The Lucky Block encountered and error while loading properties for drop: " + v2);
            a1.printStackTrace();
        }
    }
    
    public LuckyDropRaw() {
        this.luck = 2;
        this.chance = 1.0f;
        this.random = new Random();
    }
    
    public void getDropProperties() {
        final ArrayList<Integer> list = /*EL:40*/new ArrayList<Integer>();
        int n = /*EL:41*/0;
        while (true) {
            final int index = /*EL:44*/this.rawDrop.indexOf("@chance=");
            final int v0 = /*EL:45*/this.rawDrop.indexOf("@luck=");
            int v;
            int v2;
            /*SL:49*/if ((index < v0 && index != -1) || (v0 == -1 && index > -1)) {
                /*SL:51*/v = 0;
                /*SL:52*/v2 = "@chance=".length();
                /*SL:53*/list.add(n, index);
            }
            else {
                /*SL:55*/if ((v0 >= index || v0 == -1) && (index != -1 || v0 <= -1)) {
                    break;
                }
                /*SL:57*/v = 1;
                /*SL:58*/v2 = "@luck=".length();
                /*SL:59*/list.add(n, v0);
            }
            final char[] v3 = /*EL:67*/{ '+', '*', '(', ')', ',', ';', '/', '@' };
            final int v4 = getEndPoint(/*EL:68*/this.rawDrop, list.get(n) + v2, v3);
            String v5 = /*EL:69*/this.rawDrop.substring(list.get(n) + v2, v4);
            /*SL:70*/if (v5.startsWith("(") && v5.endsWith(")")) {
                v5 = v5.substring(1, v5.length() - 1);
            }
            /*SL:72*/if (v == 0) {
                this.chance = ValueParser.getFloat(v5);
            }
            /*SL:73*/if (v == 1) {
                this.luck = ValueParser.getInteger(v5);
            }
            final String v6 = /*EL:75*/this.rawDrop.substring(0, list.get(n));
            final String v7 = /*EL:76*/this.rawDrop.substring(v4, this.rawDrop.length());
            /*SL:77*/this.rawDrop = v6 + v7;
            /*SL:79*/++n;
        }
    }
    
    public LuckyDropRaw copy() {
        final LuckyDropRaw v1 = /*EL:85*/new LuckyDropRaw("");
        /*SL:87*/v1.rawDrop = this.rawDrop;
        /*SL:88*/v1.luck = this.luck;
        /*SL:89*/v1.chance = this.chance;
        /*SL:91*/return v1;
    }
    
    public String getDropValue() {
        /*SL:96*/return this.rawDrop;
    }
    
    public void setDropValue(final String a1) {
        /*SL:101*/this.rawDrop = a1;
    }
    
    public int getLuck() {
        /*SL:106*/return this.luck;
    }
    
    public void setLuck(final int a1) {
        /*SL:111*/this.luck = a1;
    }
    
    public float getChance() {
        /*SL:116*/return this.chance;
    }
    
    public void setChance(final float a1) {
        /*SL:121*/this.chance = a1;
    }
    
    @Override
    public String toString() {
        /*SL:127*/return this.rawDrop;
    }
    
    private static int getEndPoint(final String v1, final int v2, final char... v3) {
        final char[] v4 = /*EL:132*/v1.toCharArray();
        int v5 = /*EL:133*/v4.length;
        /*SL:134*/for (char a3 = (char)v2; a3 < v4.length; ++a3) {
            boolean a2 = /*EL:136*/false;
            /*SL:137*/for (final char a3 : v3) {
                /*SL:139*/if (v4[a3] == a3) {
                    /*SL:141*/v5 = a3;
                    /*SL:142*/a2 = true;
                    /*SL:143*/break;
                }
            }
            /*SL:146*/if (a2) {
                break;
            }
        }
        /*SL:148*/return v5;
    }
}
