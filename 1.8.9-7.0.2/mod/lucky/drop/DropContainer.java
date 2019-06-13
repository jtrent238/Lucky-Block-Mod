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
        final ArrayList<Integer> list = /*EL:30*/new ArrayList<Integer>();
        int n = /*EL:31*/0;
        boolean b = /*EL:32*/true;
        while (true) {
            final int index = /*EL:35*/v-6.indexOf("@chance=", (n > 0) ? (b ? list.get(n - 1) : (list.get(n - 1) + 1)) : 0);
            final int index2 = /*EL:36*/v-6.indexOf("@luck=", (n > 0) ? (b ? list.get(n - 1) : (list.get(n - 1) + 1)) : 0);
            int v1;
            final int v2;
            /*SL:40*/if ((index < index2 && index != -1) || (index2 == -1 && index > -1)) {
                final int a1 = /*EL:42*/0;
                /*SL:43*/v1 = "@chance=".length();
                /*SL:44*/list.add(n, index);
            }
            else {
                /*SL:46*/if ((index2 >= index || index2 == -1) && (index != -1 || index2 <= -1)) {
                    break;
                }
                /*SL:48*/v2 = 1;
                /*SL:49*/v1 = "@luck=".length();
                /*SL:50*/list.add(n, index2);
            }
            /*SL:58*/b = true;
            final char[] v3 = /*EL:59*/{ '+', '*', '(', ')', '[', ']', '\"', ',', ';', '/' };
            /*SL:60*/if (DropStringUtils.getEndPoint(v-6, list.get(n) + v1, v3) != v-6.toCharArray().length) {
                b = false;
            }
            final int v4 = /*EL:61*/DropStringUtils.getEndPoint(v-6, list.get(n) + v1, '@');
            String v5 = /*EL:62*/v-6.substring(list.get(n) + v1, v4);
            /*SL:63*/if (v5.startsWith("(") && v5.endsWith(")")) {
                v5 = v5.substring(1, v5.length() - 1);
            }
            /*SL:65*/if (b) {
                try {
                    /*SL:69*/if (v2 == 0) {
                        /*SL:71*/this.chance = ValueParser.getFloat(v5);
                        /*SL:72*/this.setChance = true;
                    }
                    /*SL:74*/if (v2 == 1) {
                        this.luck = ValueParser.getInteger(v5);
                    }
                }
                catch (Exception v6) {
                    System.err.println(/*EL:78*/"Lucky Block: Error reading luck/chance for drop: " + v-6);
                    /*SL:79*/v6.printStackTrace();
                }
                final String v7 = /*EL:82*/v-6.substring(0, list.get(n));
                final String v8 = /*EL:83*/v-6.substring(v4, v-6.length());
                /*SL:84*/v-6 = v7 + v8;
            }
            /*SL:86*/++n;
        }
        /*SL:88*/return v-6;
    }
    
    public DropBase getDrop() {
        /*SL:93*/return this.drop;
    }
    
    public int getLuck() {
        /*SL:98*/return this.luck;
    }
    
    public float getChance() {
        /*SL:103*/return this.chance;
    }
    
    public void setDrop(final DropBase a1) {
        /*SL:108*/this.drop = a1;
    }
    
    public void setLuck(final int a1) {
        /*SL:113*/this.luck = a1;
    }
    
    public void setChance(final float a1) {
        /*SL:118*/this.chance = a1;
    }
    
    public boolean wasChanceSet() {
        /*SL:123*/return this.setChance;
    }
    
    public DropContainer copy() {
        final DropContainer v1 = /*EL:128*/new DropContainer();
        /*SL:129*/v1.setLuck(this.luck);
        /*SL:130*/v1.setChance(this.chance);
        /*SL:131*/v1.setDrop(this.drop);
        /*SL:132*/return v1;
    }
    
    @Override
    public DropContainer initialize(final DropProcessData a1) {
        /*SL:138*/return this;
    }
    
    @Override
    public void readFromString(String v2) {
        try {
            /*SL:146*/this.rawDrop = v2;
            /*SL:147*/v2 = this.readLuckChance(v2);
            /*SL:148*/this.readDropFromString(v2);
        }
        catch (Exception a1) {
            System.err.println(/*EL:152*/"Lucky Block: Error reading drop: " + v2);
            /*SL:153*/a1.printStackTrace();
        }
    }
    
    private void readDropFromString(final String v0) {
        /*SL:159*/if (v0.toLowerCase(Locale.ENGLISH).startsWith("group")) {
            final DropGroup a1 = /*EL:161*/new DropGroup();
            /*SL:162*/a1.readFromString(v0);
            /*SL:163*/this.drop = a1;
        }
        else {
            final DropProperties v = /*EL:167*/new DropProperties();
            /*SL:168*/v.readFromString(v0);
            /*SL:169*/this.drop = v;
        }
    }
    
    @Override
    public String writeToString() {
        /*SL:176*/return null;
    }
    
    @Override
    public void readFromNBT(final NBTTagCompound a1) {
    }
    
    @Override
    public NBTTagCompound writeToNBT() {
        /*SL:188*/return null;
    }
    
    @Override
    public String toString() {
        /*SL:194*/return this.rawDrop;
    }
}
