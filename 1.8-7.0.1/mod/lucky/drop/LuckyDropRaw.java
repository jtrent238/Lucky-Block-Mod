package mod.lucky.drop;

import java.util.ArrayList;
import mod.lucky.drop.value.ExpressionParserOld;
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
        catch (ExpressionParserOld.ExpressionParserException a1) {
            System.out.println("The Lucky Block encountered and error while loading properties for drop: " + v2);
            a1.printStackTrace();
        }
    }
    
    public LuckyDropRaw() {
        this.luck = 2;
        this.chance = 1.0f;
        this.random = new Random();
    }
    
    public void getDropProperties() throws ExpressionParserOld.ExpressionParserException {
        final ExpressionParserOld instance = ExpressionParserOld.instance;
        final ArrayList<Integer> list = /*EL:42*/new ArrayList<Integer>();
        int n = /*EL:43*/0;
        while (true) {
            final int index = /*EL:46*/this.rawDrop.indexOf("@chance=");
            final int v0 = /*EL:47*/this.rawDrop.indexOf("@luck=");
            int v;
            int v2;
            /*SL:51*/if ((index < v0 && index != -1) || (v0 == -1 && index > -1)) {
                /*SL:53*/v = 0;
                /*SL:54*/v2 = "@chance=".length();
                /*SL:55*/list.add(n, index);
            }
            else {
                /*SL:57*/if ((v0 >= index || v0 == -1) && (index != -1 || v0 <= -1)) {
                    break;
                }
                /*SL:59*/v = 1;
                /*SL:60*/v2 = "@luck=".length();
                /*SL:61*/list.add(n, v0);
            }
            final char[] v3 = /*EL:69*/{ '+', '*', '(', ')', ',', ';', '/', '@' };
            final int v4 = /*EL:70*/instance.getEndPoint(this.rawDrop, list.get(n) + v2, v3);
            String v5 = /*EL:71*/this.rawDrop.substring(list.get(n) + v2, v4);
            /*SL:72*/if (v5.startsWith("(") && v5.endsWith(")")) {
                v5 = v5.substring(1, v5.length() - 1);
            }
            /*SL:74*/if (v == 0) {
                this.chance = instance.getFloat(v5);
            }
            /*SL:75*/if (v == 1) {
                this.luck = instance.getInteger(v5);
            }
            final String v6 = /*EL:77*/this.rawDrop.substring(0, list.get(n));
            final String v7 = /*EL:78*/this.rawDrop.substring(v4, this.rawDrop.length());
            /*SL:79*/this.rawDrop = v6 + v7;
            /*SL:81*/++n;
        }
    }
    
    public LuckyDropRaw copy() {
        final LuckyDropRaw v1 = /*EL:87*/new LuckyDropRaw("");
        /*SL:89*/v1.rawDrop = this.rawDrop;
        /*SL:90*/v1.luck = this.luck;
        /*SL:91*/v1.chance = this.chance;
        /*SL:93*/return v1;
    }
    
    public String getDropValue() {
        /*SL:98*/return this.rawDrop;
    }
    
    public void setDropValue(final String a1) {
        /*SL:103*/this.rawDrop = a1;
    }
    
    public int getLuck() {
        /*SL:108*/return this.luck;
    }
    
    public void setLuck(final int a1) {
        /*SL:113*/this.luck = a1;
    }
    
    public float getChance() {
        /*SL:118*/return this.chance;
    }
    
    public void setChance(final float a1) {
        /*SL:123*/this.chance = a1;
    }
    
    @Override
    public String toString() {
        /*SL:129*/return this.rawDrop;
    }
}
