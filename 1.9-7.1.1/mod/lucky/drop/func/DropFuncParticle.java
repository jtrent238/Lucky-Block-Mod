package mod.lucky.drop.func;

import mod.lucky.drop.DropProperties;
import net.minecraft.potion.PotionType;
import mod.lucky.drop.value.ValueParser;
import net.minecraft.world.WorldServer;
import net.minecraft.util.EnumParticleTypes;

public class DropFuncParticle extends DropFunction
{
    @Override
    public void process(final DropProcessData v-7) {
        final DropProperties dropProperties = /*EL:14*/v-7.getDropProperties();
        final String propertyString = /*EL:15*/dropProperties.getPropertyString("ID");
        EnumParticleTypes enumParticleTypes = /*EL:16*/null;
        /*SL:17*/for (final EnumParticleTypes a1 : EnumParticleTypes.values()) {
            /*SL:19*/if (propertyString.startsWith(a1.func_179346_b()) && !propertyString.equals("splashpotion")) {
                /*SL:21*/enumParticleTypes = a1;
                /*SL:22*/break;
            }
        }
        /*SL:26*/if (v-7.getWorld() instanceof WorldServer) {
            final WorldServer worldServer = /*EL:28*/(WorldServer)v-7.getWorld();
            /*SL:30*/if (enumParticleTypes != null) {
                final int[] array = /*EL:32*/new int[enumParticleTypes.func_179345_d()];
                final String[] split = /*EL:34*/propertyString.split("_", 3);
                /*SL:36*/for (int v0 = 1; v0 < split.length; ++v0) {
                    try {
                        /*SL:40*/array[v0 - 1] = Integer.parseInt(split[v0]);
                    }
                    catch (NumberFormatException v8) {}
                }
                final float v = /*EL:48*/dropProperties.getPropertyFloat("posX");
                final float v2 = /*EL:49*/dropProperties.getPropertyFloat("posY");
                final float v3 = /*EL:50*/dropProperties.getPropertyFloat("posZ");
                final int v4 = /*EL:51*/dropProperties.getPropertyInt("particleAmount");
                final float v5 = /*EL:52*/dropProperties.getPropertyFloat("length");
                final float v6 = /*EL:53*/dropProperties.getPropertyFloat("height");
                final float v7 = /*EL:54*/dropProperties.getPropertyFloat("width");
                /*SL:56*/worldServer.func_180505_a(enumParticleTypes, true, (double)v, (double)v2, (double)v3, v4, (double)v5, (double)v6, (double)v7, 0.0, array);
            }
            else {
                final int n = /*EL:60*/ValueParser.getString(propertyString, v-7).equals("splashpotion") ? 2002 : ValueParser.getInteger(propertyString, v-7);
                int n2 = /*EL:61*/0;
                /*SL:62*/if (n == 2002) {
                    /*SL:64*/if (dropProperties.hasProperty("potion")) {
                        n2 = PotionType.func_185171_a(PotionType.func_185168_a(dropProperties.getPropertyString("potion")));
                    }
                    else {
                        /*SL:65*/n2 = dropProperties.getPropertyInt("damage");
                    }
                }
                /*SL:68*/worldServer.func_175718_b(n, dropProperties.getBlockPos(), n2);
            }
        }
    }
    
    @Override
    public void registerProperties() {
        /*SL:76*/DropProperties.setDefaultProperty(this.getType(), "length", Float.class, 0.0f);
        /*SL:77*/DropProperties.setDefaultProperty(this.getType(), "height", Float.class, 0.0f);
        /*SL:78*/DropProperties.setDefaultProperty(this.getType(), "width", Float.class, 0.0f);
        /*SL:79*/DropProperties.setDefaultProperty(this.getType(), "size", String.class, "(0.0,0.0,0.0)");
        /*SL:80*/DropProperties.setDefaultProperty(this.getType(), "particleAmount", Integer.class, 1);
        /*SL:81*/DropProperties.setDefaultProperty(this.getType(), "potion", String.class, "poison");
    }
    
    @Override
    public String getType() {
        /*SL:87*/return "particle";
    }
}
