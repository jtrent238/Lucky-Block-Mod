package mod.lucky.drop.func;

import mod.lucky.drop.DropProperties;
import net.minecraft.entity.Entity;

public class DropFuncSound extends DropFunction
{
    @Override
    public void process(final DropProcessData a1) {
        final DropProperties v1 = /*EL:10*/a1.getDropProperties();
        /*SL:11*/a1.getWorld().func_72956_a((Entity)a1.getPlayer(), v1.getPropertyString("ID"), (float)v1.getPropertyFloat("volume"), (float)v1.getPropertyFloat("pitch"));
    }
    
    @Override
    public void registerProperties() {
        /*SL:17*/DropProperties.setDefaultProperty(this.getType(), "volume", Float.class, 1.0f);
        /*SL:18*/DropProperties.setDefaultProperty(this.getType(), "pitch", Float.class, 1.0f);
    }
    
    @Override
    public String getType() {
        /*SL:24*/return "sound";
    }
}
