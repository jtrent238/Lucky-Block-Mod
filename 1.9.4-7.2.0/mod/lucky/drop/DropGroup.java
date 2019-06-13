package mod.lucky.drop;

import net.minecraft.nbt.NBTTagCompound;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import mod.lucky.drop.value.DropStringUtils;
import java.util.List;
import java.util.Collections;
import mod.lucky.drop.func.DropProcessData;
import mod.lucky.drop.value.DropValue;
import java.util.ArrayList;

public class DropGroup extends DropBase
{
    private ArrayList<DropBase> drops;
    private DropValue amount;
    private boolean shuffle;
    
    public DropGroup() {
        this.drops = new ArrayList<DropBase>();
    }
    
    @Override
    public DropGroup initialize(final DropProcessData a1) {
        final DropGroup v1 = /*EL:30*/this.copy();
        /*SL:31*/v1.amount.initialize(a1);
        /*SL:32*/if (v1.shuffle) {
            Collections.shuffle(v1.drops);
        }
        /*SL:33*/return v1;
    }
    
    @Override
    public void readFromString(final String v-9) {
        final String[] splitBracketString = /*EL:39*/DropStringUtils.splitBracketString(v-9, ':');
        String s = /*EL:40*/splitBracketString[splitBracketString.length - 1];
        String[] splitBracketString2 = /*EL:41*/DropStringUtils.splitBracketString(s, ',');
        /*SL:42*/s = splitBracketString2[0];
        /*SL:44*/splitBracketString2 = (String[])ArrayUtils.remove((Object[])splitBracketString2, 0);
        String s2 = /*EL:45*/StringUtils.join((Object[])splitBracketString2, ',');
        /*SL:47*/if (!s2.equals("")) {
            s2 = "," + s2;
        }
        /*SL:48*/if (s.toLowerCase(Locale.ENGLISH).startsWith("group")) {
            s = s.substring("group".length() + 1, s.length() - 1);
        }
        else {
            /*SL:49*/s = s.substring(1, s.length() - 1);
        }
        final String[] splitBracketString3 = /*EL:50*/DropStringUtils.splitBracketString(s, ';');
        /*SL:52*/if (splitBracketString.length >= 3) {
            /*SL:54*/this.amount = new DropValue(splitBracketString[1], Integer.class);
            /*SL:55*/this.shuffle = true;
        }
        else {
            /*SL:59*/this.amount = new DropValue(splitBracketString3.length);
            /*SL:60*/this.shuffle = false;
        }
        /*SL:63*/for (String v0 : splitBracketString3) {
            /*SL:65*/v0 += s2;
            /*SL:66*/if (v0.toLowerCase(Locale.ENGLISH).startsWith("group")) {
                final DropGroup a1 = /*EL:68*/new DropGroup();
                /*SL:69*/a1.readFromString(v0);
                /*SL:70*/this.drops.add(a1);
            }
            else {
                final DropProperties v = /*EL:74*/new DropProperties();
                /*SL:75*/v.readFromString(v0);
                /*SL:76*/this.drops.add(v);
            }
        }
    }
    
    public ArrayList<DropBase> getDrops() {
        /*SL:83*/return this.drops;
    }
    
    public int getAmount() {
        /*SL:88*/return this.amount.getValueInt();
    }
    
    @Override
    public String writeToString() {
        /*SL:94*/return null;
    }
    
    @Override
    public void readFromNBT(final NBTTagCompound a1) {
    }
    
    @Override
    public NBTTagCompound writeToNBT() {
        /*SL:106*/return null;
    }
    
    public DropGroup copy() {
        final DropGroup v1 = /*EL:111*/new DropGroup();
        /*SL:112*/v1.amount = this.amount.copy();
        /*SL:113*/v1.shuffle = this.shuffle;
        /*SL:115*/v1.drops = this.drops;
        /*SL:116*/return v1;
    }
}
