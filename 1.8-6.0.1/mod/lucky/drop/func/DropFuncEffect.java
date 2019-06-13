package mod.lucky.drop.func;

import mod.lucky.drop.DropProperties;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.Potion;
import mod.lucky.drop.value.ValueParser;

public class DropFuncEffect extends DropFunction
{
    @Override
    public void process(final DropProcessData v-2) {
        final DropProperties dropProperties = /*EL:13*/v-2.getDropProperties();
        int v0;
        try {
            /*SL:17*/v0 = ValueParser.getInteger(dropProperties.getPropertyString("ID"));
        }
        catch (Exception v2) {
            final Potion a1 = /*EL:21*/Potion.func_180142_b(dropProperties.getPropertyString("ID"));
            /*SL:22*/v0 = a1.field_76415_H;
        }
        final PotionEffect v = /*EL:25*/new PotionEffect(v0, dropProperties.getPropertyInt("duration") * 20, (int)dropProperties.getPropertyInt("amplifier"));
        /*SL:26*/v-2.getPlayer().func_70690_d(v);
    }
    
    @Override
    public void registerProperties() {
        /*SL:32*/DropProperties.setDefaultProperty(this.getType(), "duration", Integer.class, 30);
        /*SL:33*/DropProperties.setDefaultProperty(this.getType(), "amplifier", Integer.class, 0);
    }
    
    @Override
    public String getType() {
        /*SL:39*/return "effect";
    }
}
