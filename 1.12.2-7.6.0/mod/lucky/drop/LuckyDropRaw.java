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
        final ArrayList<Integer> list = /*EL:36*/new ArrayList<Integer>();
        int n = /*EL:37*/0;
        while (true) {
            final int index = /*EL:39*/this.rawDrop.indexOf("@chance=");
            final int v0 = /*EL:40*/this.rawDrop.indexOf("@luck=");
            int v;
            int v2;
            /*SL:44*/if ((index < v0 && index != -1) || (v0 == -1 && index > -1)) {
                /*SL:45*/v = 0;
                /*SL:46*/v2 = "@chance=".length();
                /*SL:47*/list.add(n, index);
            }
            else {
                /*SL:48*/if ((v0 >= index || v0 == -1) && (index != -1 || v0 <= -1)) {
                    break;
                }
                /*SL:50*/v = 1;
                /*SL:51*/v2 = "@luck=".length();
                /*SL:52*/list.add(n, v0);
            }
            final char[] v3 = /*EL:58*/{ '+', '*', '(', ')', ',', ';', '/', '@' };
            final int v4 = getEndPoint(/*EL:59*/this.rawDrop, list.get(n) + /*EL:60*/v2, v3);
            String v5 = /*EL:61*/this.rawDrop.substring(list.get(n) + /*EL:62*/v2, v4);
            /*SL:63*/if (v5.startsWith("(") && v5.endsWith(")")) {
                /*SL:64*/v5 = v5.substring(1, v5.length() - 1);
            }
            /*SL:66*/if (v == 0) {
                this.chance = ValueParser.getFloat(v5);
            }
            /*SL:67*/if (v == 1) {
                this.luck = ValueParser.getInteger(v5);
            }
            final String v6 = /*EL:69*/this.rawDrop.substring(0, list.get(n));
            final String v7 = /*EL:70*/this.rawDrop.substring(v4, this.rawDrop.length());
            /*SL:71*/this.rawDrop = v6 + v7;
            /*SL:73*/++n;
        }
    }
    
    public LuckyDropRaw copy() {
        final LuckyDropRaw v1 = /*EL:78*/new LuckyDropRaw("");
        /*SL:80*/v1.rawDrop = this.rawDrop;
        /*SL:81*/v1.luck = this.luck;
        /*SL:82*/v1.chance = this.chance;
        /*SL:84*/return v1;
    }
    
    public String getDropValue() {
        /*SL:88*/return this.rawDrop;
    }
    
    public void setDropValue(final String a1) {
        /*SL:92*/this.rawDrop = a1;
    }
    
    public int getLuck() {
        /*SL:96*/return this.luck;
    }
    
    public void setLuck(final int a1) {
        /*SL:100*/this.luck = a1;
    }
    
    public float getChance() {
        /*SL:104*/return this.chance;
    }
    
    public void setChance(final float a1) {
        /*SL:108*/this.chance = a1;
    }
    
    @Override
    public String toString() {
        /*SL:113*/return this.rawDrop;
    }
    
    private static int getEndPoint(final String v1, final int v2, final char... v3) {
        final char[] v4 = /*EL:117*/v1.toCharArray();
        int v5 = /*EL:118*/v4.length;
        /*SL:119*/for (char a3 = (char)v2; a3 < v4.length; ++a3) {
            boolean a2 = /*EL:120*/false;
            /*SL:121*/for (final char a3 : v3) {
                /*SL:122*/if (v4[a3] == a3) {
                    /*SL:123*/v5 = a3;
                    /*SL:124*/a2 = true;
                    /*SL:125*/break;
                }
            }
            /*SL:128*/if (a2) {
                break;
            }
        }
        /*SL:130*/return v5;
    }
}
