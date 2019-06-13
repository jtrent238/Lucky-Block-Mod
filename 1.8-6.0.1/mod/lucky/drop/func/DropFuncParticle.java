package mod.lucky.drop.func;

import mod.lucky.drop.DropProperties;
import mod.lucky.drop.value.ValueParser;
import net.minecraft.world.WorldServer;
import net.minecraft.util.EnumParticleTypes;

public class DropFuncParticle extends DropFunction
{
    @Override
    public void process(final DropProcessData v-7) {
        final DropProperties dropProperties = /*EL:13*/v-7.getDropProperties();
        final String propertyString = /*EL:14*/dropProperties.getPropertyString("ID");
        EnumParticleTypes enumParticleTypes = /*EL:15*/null;
        /*SL:16*/for (final EnumParticleTypes a1 : EnumParticleTypes.values()) {
            /*SL:18*/if (a1.func_179346_b().equals(propertyString)) {
                /*SL:20*/enumParticleTypes = a1;
                /*SL:21*/break;
            }
        }
        /*SL:25*/if (v-7.getWorld() instanceof WorldServer) {
            final WorldServer worldServer = /*EL:27*/(WorldServer)v-7.getWorld();
            /*SL:29*/if (enumParticleTypes != null) {
                final int[] array = /*EL:31*/new int[enumParticleTypes.func_179345_d()];
                /*SL:33*/if (enumParticleTypes.func_179343_f()) {
                    final String[] split = /*EL:35*/propertyString.split("_", 3);
                    /*SL:37*/for (int v0 = 1; v0 < split.length; ++v0) {
                        try {
                            /*SL:41*/array[v0 - 1] = Integer.parseInt(split[v0]);
                        }
                        catch (NumberFormatException v6) {}
                    }
                }
                final int intValue = /*EL:49*/dropProperties.getPropertyInt("posX");
                int v0 = /*EL:50*/dropProperties.getPropertyInt("posY");
                final int v = /*EL:51*/dropProperties.getPropertyInt("posZ");
                final int v2 = /*EL:52*/dropProperties.getPropertyInt("particleAmount");
                final float v3 = /*EL:53*/dropProperties.getPropertyFloat("length");
                final float v4 = /*EL:54*/dropProperties.getPropertyFloat("height");
                final float v5 = /*EL:55*/dropProperties.getPropertyFloat("width");
                /*SL:57*/worldServer.func_180505_a(enumParticleTypes, false, intValue + 0.5, (double)v0, v + 0.5, v2, (double)v3, (double)v4, (double)v5, 0.0, array);
            }
            else {
                final int intValue2 = /*EL:61*/ValueParser.getInteger(propertyString, v-7);
                /*SL:62*/worldServer.func_175718_b(intValue2, dropProperties.getBlockPos(), (int)dropProperties.getPropertyInt("damage"));
            }
        }
    }
    
    @Override
    public void registerProperties() {
        /*SL:70*/DropProperties.setDefaultProperty(this.getType(), "length", Float.class, 0.0f);
        /*SL:71*/DropProperties.setDefaultProperty(this.getType(), "height", Float.class, 0.0f);
        /*SL:72*/DropProperties.setDefaultProperty(this.getType(), "width", Float.class, 0.0f);
        /*SL:73*/DropProperties.setDefaultProperty(this.getType(), "size", String.class, "(0.0,0.0,0.0)");
        /*SL:74*/DropProperties.setDefaultProperty(this.getType(), "particleAmount", Integer.class, 1);
    }
    
    @Override
    public String getType() {
        /*SL:80*/return "particle";
    }
}
