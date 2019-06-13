package mod.lucky.drop.func;

import net.minecraft.world.WorldServer;
import mod.lucky.drop.DropProperties;
import mod.lucky.drop.value.ValueParser;

public class DropFuncTime extends DropFunction
{
    @Override
    public void process(final DropProcessData v2) {
        final DropProperties v3 = /*EL:12*/v2.getDropProperties();
        final String v4 = /*EL:13*/v3.getPropertyString("ID");
        final long v5 = /*EL:14*/v4.equals("day") ? 1000L : (v4.equals("night") ? 13000 : ValueParser.getInteger(v4));
        /*SL:15*/for (final WorldServer a1 : v2.getWorld().func_73046_m().field_71305_c) {
            /*SL:17*/a1.func_72877_b(v5);
        }
    }
    
    @Override
    public String getType() {
        /*SL:24*/return "time";
    }
}
