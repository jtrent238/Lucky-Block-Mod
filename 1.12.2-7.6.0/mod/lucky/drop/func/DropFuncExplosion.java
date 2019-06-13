package mod.lucky.drop.func;

import mod.lucky.drop.DropProperties;
import net.minecraft.entity.Entity;

public class DropFuncExplosion extends DropFunction
{
    @Override
    public void process(final DropProcessData a1) {
        final DropProperties v1 = /*EL:8*/a1.getDropProperties();
        /*SL:9*/a1.getWorld().func_72885_a(/*EL:10*/(Entity)null, v1.getPropertyInt("posX") + /*EL:13*/0.5, v1.getPropertyInt("posY") + /*EL:14*/0.5, v1.getPropertyInt("posZ") + /*EL:15*/0.5, (float)v1.getPropertyInt("damage"), /*EL:16*/(boolean)v1.getPropertyBoolean("fire"), /*EL:17*/true);
    }
    
    @Override
    public void registerProperties() {
        /*SL:23*/DropProperties.setDefaultProperty(this.getType(), "damage", Integer.class, 3);
        /*SL:24*/DropProperties.setDefaultProperty(this.getType(), "fire", Boolean.class, false);
        /*SL:25*/DropProperties.setReplaceProperty("radius", "damage");
    }
    
    @Override
    public String getType() {
        /*SL:30*/return "explosion";
    }
}
