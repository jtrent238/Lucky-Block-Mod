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
        final DropGroup v1 = /*EL:29*/this.copy();
        /*SL:30*/v1.amount.initialize(a1);
        /*SL:31*/if (v1.shuffle) {
            Collections.shuffle(v1.drops);
        }
        /*SL:32*/return v1;
    }
    
    @Override
    public void readFromString(final String v-9) {
        final String[] splitBracketString = /*EL:38*/DropStringUtils.splitBracketString(v-9, ':');
        String s = /*EL:39*/splitBracketString[splitBracketString.length - 1];
        String[] splitBracketString2 = /*EL:40*/DropStringUtils.splitBracketString(s, ',');
        /*SL:41*/s = splitBracketString2[0];
        /*SL:43*/splitBracketString2 = (String[])ArrayUtils.remove((Object[])splitBracketString2, 0);
        String s2 = /*EL:44*/StringUtils.join((Object[])splitBracketString2, ',');
        /*SL:46*/if (!s2.equals("")) {
            s2 = "," + s2;
        }
        /*SL:47*/if (s.toLowerCase(Locale.ENGLISH).startsWith("group")) {
            s = s.substring("group".length() + 1, s.length() - 1);
        }
        else {
            /*SL:48*/s = s.substring(1, s.length() - 1);
        }
        final String[] splitBracketString3 = /*EL:49*/DropStringUtils.splitBracketString(s, ';');
        /*SL:51*/if (splitBracketString.length >= 3) {
            /*SL:53*/this.amount = new DropValue(splitBracketString[1], Integer.class);
            /*SL:54*/this.shuffle = true;
        }
        else {
            /*SL:58*/this.amount = new DropValue(splitBracketString3.length);
            /*SL:59*/this.shuffle = false;
        }
        /*SL:62*/for (String v0 : splitBracketString3) {
            /*SL:64*/v0 += s2;
            /*SL:65*/if (v0.toLowerCase(Locale.ENGLISH).startsWith("group")) {
                final DropGroup a1 = /*EL:67*/new DropGroup();
                /*SL:68*/a1.readFromString(v0);
                /*SL:69*/this.drops.add(a1);
            }
            else {
                final DropProperties v = /*EL:73*/new DropProperties();
                /*SL:74*/v.readFromString(v0);
                /*SL:75*/this.drops.add(v);
            }
        }
    }
    
    public ArrayList<DropBase> getDrops() {
        /*SL:82*/return this.drops;
    }
    
    public int getAmount() {
        /*SL:87*/return this.amount.getValueInt();
    }
    
    @Override
    public String writeToString() {
        /*SL:93*/return null;
    }
    
    @Override
    public void readFromNBT(final NBTTagCompound a1) {
    }
    
    @Override
    public NBTTagCompound writeToNBT() {
        /*SL:105*/return null;
    }
    
    public DropGroup copy() {
        final DropGroup v1 = /*EL:110*/new DropGroup();
        /*SL:111*/v1.amount = this.amount.copy();
        /*SL:112*/v1.shuffle = this.shuffle;
        /*SL:114*/v1.drops = this.drops;
        /*SL:115*/return v1;
    }
}
