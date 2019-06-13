package mod.lucky.world;

import net.minecraft.world.World;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import mod.lucky.drop.func.DropProcessor;
import mod.lucky.drop.func.DropProcessData;

public class DelayLuckyDrop
{
    private long ticksRemaining;
    private DropProcessData processData;
    private DropProcessor dropProcessor;
    private boolean finished;
    
    public DelayLuckyDrop(final DropProcessor a1, final DropProcessData a2, final long a3) {
        this.dropProcessor = a1;
        this.processData = a2;
        this.ticksRemaining = a3;
        this.finished = false;
    }
    
    public void update() {
        try {
            /*SL:27*/--this.ticksRemaining;
            /*SL:28*/if (this.ticksRemaining <= 0L) {
                /*SL:30*/this.dropProcessor.processDelayDrop(this.processData);
                /*SL:31*/this.finished = true;
            }
        }
        catch (Exception v1) {
            System.err.println(/*EL:36*/"Lucky Block: Error processing delay drop: " + this.processData.getDropProperties().toString());
            /*SL:37*/v1.printStackTrace();
            /*SL:38*/this.finished = true;
        }
    }
    
    public void setDropprocessor(final DropProcessor a1) {
        /*SL:44*/this.dropProcessor = a1;
    }
    
    public DropProcessData getProcessData() {
        /*SL:49*/return this.processData;
    }
    
    public long getTicksRemaining() {
        /*SL:54*/return this.ticksRemaining;
    }
    
    public boolean finished() {
        /*SL:59*/return this.finished;
    }
    
    public NBTTagCompound writeToNBT() {
        final NBTTagCompound v1 = /*EL:64*/new NBTTagCompound();
        /*SL:65*/v1.func_74782_a("processData", (NBTBase)this.processData.writeToNBT());
        /*SL:66*/v1.func_74772_a("ticksRemaining", this.ticksRemaining);
        /*SL:67*/return v1;
    }
    
    public void readFromNBT(final NBTTagCompound a1, final World a2) {
        /*SL:72*/(this.processData = new DropProcessData(a2)).readFromNBT(/*EL:73*/a1.func_74775_l("processData"));
        /*SL:74*/this.ticksRemaining = a1.func_74763_f("ticksRemaining");
    }
}
