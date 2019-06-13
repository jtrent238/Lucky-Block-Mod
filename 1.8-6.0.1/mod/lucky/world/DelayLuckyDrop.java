package mod.lucky.world;

import net.minecraft.world.World;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import mod.lucky.drop.func.DropProcessData;

public class DelayLuckyDrop
{
    private long ticksRemaining;
    private DropProcessData processData;
    private boolean finished;
    
    public DelayLuckyDrop(final DropProcessData a1, final long a2) {
        this.processData = a1;
        this.ticksRemaining = a2;
        this.finished = false;
    }
    
    public void update() {
        try {
            /*SL:24*/--this.ticksRemaining;
            /*SL:25*/if (this.ticksRemaining <= 0L) {
                /*SL:27*/this.processData.getBlock().getDropProcessor().processDelayDrop(this.processData);
                /*SL:28*/this.finished = true;
            }
        }
        catch (Exception v1) {
            System.err.println(/*EL:33*/"Lucky Block: Error processing delay drop: " + this.processData.getDropProperties().toString());
            /*SL:34*/v1.printStackTrace();
        }
    }
    
    public DropProcessData getProcessData() {
        /*SL:40*/return this.processData;
    }
    
    public long getTicksRemaining() {
        /*SL:45*/return this.ticksRemaining;
    }
    
    public boolean finished() {
        /*SL:50*/return this.finished;
    }
    
    public NBTTagCompound writeToNBT() {
        final NBTTagCompound v1 = /*EL:55*/new NBTTagCompound();
        /*SL:56*/v1.func_74782_a("processData", (NBTBase)this.processData.writeToNBT());
        /*SL:57*/v1.func_74772_a("ticksRemaining", this.ticksRemaining);
        /*SL:58*/return v1;
    }
    
    public void readFromNBT(final NBTTagCompound a1, final World a2) {
        /*SL:63*/(this.processData = new DropProcessData(a2)).readFromNBT(/*EL:64*/a1.func_74775_l("processData"));
        /*SL:65*/this.ticksRemaining = a1.func_74763_f("ticksRemaining");
    }
}
