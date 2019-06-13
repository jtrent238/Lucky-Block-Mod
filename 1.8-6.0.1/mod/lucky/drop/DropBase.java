package mod.lucky.drop;

import net.minecraft.nbt.NBTTagCompound;
import mod.lucky.drop.func.DropProcessData;

public abstract class DropBase
{
    public abstract DropBase initialize(final DropProcessData p0);
    
    public abstract void readFromString(final String p0);
    
    public abstract String writeToString();
    
    public abstract void readFromNBT(final NBTTagCompound p0);
    
    public abstract NBTTagCompound writeToNBT();
}
