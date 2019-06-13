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
        final DropGroup v1 = /*EL:25*/this.copy();
        /*SL:26*/v1.amount.initialize(a1);
        /*SL:27*/if (v1.shuffle) {
            Collections.shuffle(v1.drops);
        }
        /*SL:28*/return v1;
    }
    
    @Override
    public void readFromString(final String v-9) {
        final String[] splitBracketString = /*EL:33*/DropStringUtils.splitBracketString(v-9, ':');
        String s = /*EL:34*/splitBracketString[splitBracketString.length - 1];
        String[] splitBracketString2 = /*EL:35*/DropStringUtils.splitBracketString(s, ',');
        /*SL:36*/s = splitBracketString2[0];
        /*SL:38*/splitBracketString2 = (String[])ArrayUtils.remove((Object[])splitBracketString2, 0);
        String s2 = /*EL:39*/StringUtils.join((Object[])splitBracketString2, ',');
        /*SL:41*/if (!s2.equals("")) {
            s2 = "," + s2;
        }
        /*SL:42*/if (s.toLowerCase(Locale.ENGLISH).startsWith("group")) {
            /*SL:43*/s = s.substring("group".length() + 1, s.length() - 1);
        }
        else {
            /*SL:44*/s = s.substring(1, s.length() - 1);
        }
        final String[] splitBracketString3 = /*EL:45*/DropStringUtils.splitBracketString(s, ';');
        /*SL:47*/if (splitBracketString.length >= 3) {
            /*SL:48*/this.amount = new DropValue(splitBracketString[1], Integer.class);
            /*SL:49*/this.shuffle = true;
        }
        else {
            /*SL:51*/this.amount = new DropValue(splitBracketString3.length);
            /*SL:52*/this.shuffle = false;
        }
        /*SL:55*/for (String v0 : splitBracketString3) {
            /*SL:56*/v0 += s2;
            /*SL:57*/if (v0.toLowerCase(Locale.ENGLISH).startsWith("group")) {
                final DropGroup a1 = /*EL:58*/new DropGroup();
                /*SL:59*/a1.readFromString(v0);
                /*SL:60*/this.drops.add(a1);
            }
            else {
                final DropProperties v = /*EL:62*/new DropProperties();
                /*SL:63*/v.readFromString(v0);
                /*SL:64*/this.drops.add(v);
            }
        }
    }
    
    public ArrayList<DropBase> getDrops() {
        /*SL:70*/return this.drops;
    }
    
    public int getAmount() {
        /*SL:74*/return this.amount.getValueInt();
    }
    
    @Override
    public String writeToString() {
        /*SL:79*/return null;
    }
    
    @Override
    public void readFromNBT(final NBTTagCompound a1) {
    }
    
    @Override
    public NBTTagCompound writeToNBT() {
        /*SL:87*/return null;
    }
    
    public DropGroup copy() {
        final DropGroup v1 = /*EL:91*/new DropGroup();
        /*SL:92*/v1.amount = this.amount.copy();
        /*SL:93*/v1.shuffle = this.shuffle;
        /*SL:95*/v1.drops = this.drops;
        /*SL:96*/return v1;
    }
}
