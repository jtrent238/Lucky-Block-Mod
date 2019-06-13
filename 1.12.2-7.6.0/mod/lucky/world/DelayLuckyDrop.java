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
            /*SL:24*/--this.ticksRemaining;
            /*SL:25*/if (this.ticksRemaining <= 0L) {
                /*SL:26*/this.dropProcessor.processDelayDrop(this.processData);
                /*SL:27*/this.finished = true;
            }
        }
        catch (Exception v1) {
            System.err.println(/*EL:30*/"Lucky Block: Error processing delay drop: " + this.processData.getDropProperties().toString());
            /*SL:33*/v1.printStackTrace();
            /*SL:34*/this.finished = true;
        }
    }
    
    public void setDropprocessor(final DropProcessor a1) {
        /*SL:39*/this.dropProcessor = a1;
    }
    
    public DropProcessData getProcessData() {
        /*SL:43*/return this.processData;
    }
    
    public long getTicksRemaining() {
        /*SL:47*/return this.ticksRemaining;
    }
    
    public boolean finished() {
        /*SL:51*/return this.finished;
    }
    
    public NBTTagCompound writeToNBT() {
        final NBTTagCompound v1 = /*EL:55*/new NBTTagCompound();
        /*SL:56*/v1.func_74782_a("processData", (NBTBase)this.processData.writeToNBT());
        /*SL:57*/v1.func_74772_a("ticksRemaining", this.ticksRemaining);
        /*SL:58*/return v1;
    }
    
    public void readFromNBT(final NBTTagCompound a1, final World a2) {
        /*SL:62*/(this.processData = new DropProcessData(a2)).readFromNBT(/*EL:63*/a1.func_74775_l("processData"));
        /*SL:64*/this.ticksRemaining = a1.func_74763_f("ticksRemaining");
    }
}
