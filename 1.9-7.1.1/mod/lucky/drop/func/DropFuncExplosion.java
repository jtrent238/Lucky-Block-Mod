package mod.lucky.drop.func;

import mod.lucky.drop.DropProperties;
import net.minecraft.entity.Entity;

public class DropFuncExplosion extends DropFunction
{
    @Override
    public void process(final DropProcessData a1) {
        final DropProperties v1 = /*EL:10*/a1.getDropProperties();
        /*SL:11*/a1.getWorld().func_72885_a((Entity)null, v1.getPropertyInt("posX") + 0.5, v1.getPropertyInt("posY") + 0.5, v1.getPropertyInt("posZ") + 0.5, (float)v1.getPropertyInt("damage"), (boolean)v1.getPropertyBoolean("fire"), true);
    }
    
    @Override
    public void registerProperties() {
        /*SL:17*/DropProperties.setDefaultProperty(this.getType(), "damage", Integer.class, 3);
        /*SL:18*/DropProperties.setDefaultProperty(this.getType(), "fire", Boolean.class, false);
        /*SL:19*/DropProperties.setReplaceProperty("radius", "damage");
    }
    
    @Override
    public String getType() {
        /*SL:25*/return "explosion";
    }
}
