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
            /*SL:18*/if (propertyString.startsWith(a1.func_179346_b()) && !propertyString.equals("splashpotion")) {
                /*SL:20*/enumParticleTypes = a1;
                /*SL:21*/break;
            }
        }
        /*SL:25*/if (v-7.getWorld() instanceof WorldServer) {
            final WorldServer worldServer = /*EL:27*/(WorldServer)v-7.getWorld();
            /*SL:29*/if (enumParticleTypes != null) {
                final int[] array = /*EL:31*/new int[enumParticleTypes.func_179345_d()];
                final String[] split = /*EL:32*/propertyString.split("_", 3);
                /*SL:34*/for (int v0 = 1; v0 < split.length; ++v0) {
                    try {
                        /*SL:38*/array[v0 - 1] = Integer.parseInt(split[v0]);
                    }
                    catch (NumberFormatException v8) {}
                }
                final float v = /*EL:45*/dropProperties.getPropertyFloat("posX");
                final float v2 = /*EL:46*/dropProperties.getPropertyFloat("posY");
                final float v3 = /*EL:47*/dropProperties.getPropertyFloat("posZ");
                final int v4 = /*EL:48*/dropProperties.getPropertyInt("particleAmount");
                final float v5 = /*EL:49*/dropProperties.getPropertyFloat("length");
                final float v6 = /*EL:50*/dropProperties.getPropertyFloat("height");
                final float v7 = /*EL:51*/dropProperties.getPropertyFloat("width");
                /*SL:53*/worldServer.func_180505_a(enumParticleTypes, true, (double)v, (double)v2, (double)v3, v4, (double)v5, (double)v6, (double)v7, 0.0, array);
            }
            else {
                final int n = /*EL:57*/ValueParser.getString(propertyString, v-7).equals("splashpotion") ? 2002 : ValueParser.getInteger(propertyString, v-7);
                /*SL:58*/worldServer.func_175718_b(n, dropProperties.getBlockPos(), (int)dropProperties.getPropertyInt("damage"));
            }
        }
    }
    
    @Override
    public void registerProperties() {
        /*SL:66*/DropProperties.setDefaultProperty(this.getType(), "length", Float.class, 0.0f);
        /*SL:67*/DropProperties.setDefaultProperty(this.getType(), "height", Float.class, 0.0f);
        /*SL:68*/DropProperties.setDefaultProperty(this.getType(), "width", Float.class, 0.0f);
        /*SL:69*/DropProperties.setDefaultProperty(this.getType(), "size", String.class, "(0.0,0.0,0.0)");
        /*SL:70*/DropProperties.setDefaultProperty(this.getType(), "particleAmount", Integer.class, 1);
    }
    
    @Override
    public String getType() {
        /*SL:76*/return "particle";
    }
}
