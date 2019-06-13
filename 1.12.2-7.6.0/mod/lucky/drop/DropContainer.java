package mod.lucky.drop;

import net.minecraft.nbt.NBTTagCompound;
import java.util.Locale;
import mod.lucky.drop.func.DropProcessData;
import mod.lucky.drop.value.ValueParser;
import mod.lucky.drop.value.DropStringUtils;
import java.util.ArrayList;

public class DropContainer extends DropBase
{
    private String rawDrop;
    private int luck;
    private float chance;
    private boolean setChance;
    private DropBase drop;
    
    public DropContainer() {
        this.luck = 0;
        this.chance = 1.0f;
    }
    
    public String readLuckChance(String v-6) {
        final ArrayList<Integer> list = /*EL:26*/new ArrayList<Integer>();
        int n = /*EL:27*/0;
        boolean b = /*EL:28*/true;
        while (true) {
            final int index = /*EL:30*/v-6.indexOf("@chance=", (n > 0) ? (b ? list.get(n - 1) : /*EL:33*/(list.get(n - 1) + 1)) : 0);
            final int index2 = /*EL:34*/v-6.indexOf("@luck=", (n > 0) ? (b ? list.get(n - 1) : /*EL:37*/(list.get(n - 1) + 1)) : 0);
            int v1;
            final int v2;
            /*SL:41*/if ((index < index2 && index != -1) || (index2 == -1 && index > -1)) {
                final int a1 = /*EL:42*/0;
                /*SL:43*/v1 = "@chance=".length();
                /*SL:44*/list.add(n, index);
            }
            else {
                /*SL:45*/if ((index2 >= index || index2 == -1) && (index != -1 || index2 <= -1)) {
                    break;
                }
                /*SL:47*/v2 = 1;
                /*SL:48*/v1 = "@luck=".length();
                /*SL:49*/list.add(n, index2);
            }
            /*SL:55*/b = true;
            final char[] v3 = /*EL:56*/{ '+', '*', '(', ')', '[', ']', '\"', ',', ';', '/' };
            /*SL:59*/if (DropStringUtils.getEndPoint(v-6, list.get(n) + v1, v3) != v-6.toCharArray().length) {
                b = false;
            }
            final int v4 = /*EL:61*/DropStringUtils.getEndPoint(v-6, list.get(n) + v1, '@');
            String v5 = /*EL:62*/v-6.substring(list.get(n) + v1, v4);
            /*SL:63*/if (v5.startsWith("(") && v5.endsWith(")")) {
                /*SL:64*/v5 = v5.substring(1, v5.length() - 1);
            }
            /*SL:66*/if (b) {
                try {
                    /*SL:68*/if (v2 == 0) {
                        /*SL:69*/this.chance = ValueParser.getFloat(v5);
                        /*SL:70*/this.setChance = true;
                    }
                    /*SL:72*/if (v2 == 1) {
                        this.luck = ValueParser.getInteger(v5);
                    }
                }
                catch (Exception v6) {
                    System.err.println(/*EL:74*/"Lucky Block: Error reading luck/chance for drop: " + v-6);
                    /*SL:75*/v6.printStackTrace();
                }
                final String v7 = /*EL:78*/v-6.substring(0, list.get(n));
                final String v8 = /*EL:79*/v-6.substring(v4, v-6.length());
                /*SL:80*/v-6 = v7 + v8;
            }
            /*SL:82*/++n;
        }
        /*SL:84*/return v-6;
    }
    
    public DropBase getDrop() {
        /*SL:88*/return this.drop;
    }
    
    public int getLuck() {
        /*SL:92*/return this.luck;
    }
    
    public float getChance() {
        /*SL:96*/return this.chance;
    }
    
    public void setDrop(final DropBase a1) {
        /*SL:100*/this.drop = a1;
    }
    
    public void setLuck(final int a1) {
        /*SL:104*/this.luck = a1;
    }
    
    public void setChance(final float a1) {
        /*SL:108*/this.chance = a1;
    }
    
    public boolean wasChanceSet() {
        /*SL:112*/return this.setChance;
    }
    
    public DropContainer copy() {
        final DropContainer v1 = /*EL:116*/new DropContainer();
        /*SL:117*/v1.setLuck(this.luck);
        /*SL:118*/v1.setChance(this.chance);
        /*SL:119*/v1.setDrop(this.drop);
        /*SL:120*/return v1;
    }
    
    @Override
    public DropContainer initialize(final DropProcessData a1) {
        /*SL:125*/return this;
    }
    
    @Override
    public void readFromString(String v2) {
        try {
            /*SL:131*/this.rawDrop = v2;
            /*SL:132*/v2 = this.readLuckChance(v2);
            /*SL:133*/this.readDropFromString(v2);
        }
        catch (Exception a1) {
            System.err.println(/*EL:135*/"Lucky Block: Error reading drop: " + v2);
            /*SL:136*/a1.printStackTrace();
        }
    }
    
    private void readDropFromString(final String v0) {
        /*SL:141*/if (v0.toLowerCase(Locale.ENGLISH).startsWith("group")) {
            final DropGroup a1 = /*EL:142*/new DropGroup();
            /*SL:143*/a1.readFromString(v0);
            /*SL:144*/this.drop = a1;
        }
        else {
            final DropProperties v = /*EL:146*/new DropProperties();
            /*SL:147*/v.readFromString(v0);
            /*SL:148*/this.drop = v;
        }
    }
    
    @Override
    public String writeToString() {
        /*SL:154*/return null;
    }
    
    @Override
    public void readFromNBT(final NBTTagCompound a1) {
    }
    
    @Override
    public NBTTagCompound writeToNBT() {
        /*SL:162*/return null;
    }
    
    @Override
    public String toString() {
        /*SL:167*/return this.rawDrop;
    }
}
