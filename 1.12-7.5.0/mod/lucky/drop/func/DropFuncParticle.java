package mod.lucky.drop.func;

import java.util.List;
import mod.lucky.drop.DropProperties;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.PotionEffect;
import java.util.Collection;
import net.minecraft.potion.PotionType;
import com.google.common.collect.Lists;
import mod.lucky.drop.value.ValueParser;
import net.minecraft.world.WorldServer;
import net.minecraft.util.EnumParticleTypes;

public class DropFuncParticle extends DropFunction
{
    @Override
    public void process(final DropProcessData v-7) {
        final DropProperties dropProperties = /*EL:19*/v-7.getDropProperties();
        final String propertyString = /*EL:20*/dropProperties.getPropertyString("ID");
        EnumParticleTypes enumParticleTypes = /*EL:21*/null;
        /*SL:22*/for (final EnumParticleTypes a1 : EnumParticleTypes.values()) {
            /*SL:24*/if (propertyString.startsWith(a1.func_179346_b()) && !propertyString.equals("splashpotion")) {
                /*SL:26*/enumParticleTypes = a1;
                /*SL:27*/break;
            }
        }
        /*SL:31*/if (v-7.getWorld() instanceof WorldServer) {
            final WorldServer worldServer = /*EL:33*/(WorldServer)v-7.getWorld();
            /*SL:35*/if (enumParticleTypes != null) {
                final int[] array = /*EL:37*/new int[enumParticleTypes.func_179345_d()];
                final String[] split = /*EL:39*/propertyString.split("_", 3);
                /*SL:41*/for (int v0 = 1; v0 < split.length; ++v0) {
                    try {
                        /*SL:45*/array[v0 - 1] = Integer.parseInt(split[v0]);
                    }
                    catch (NumberFormatException v9) {}
                }
                final float v = /*EL:53*/dropProperties.getPropertyFloat("posX");
                final float v2 = /*EL:54*/dropProperties.getPropertyFloat("posY");
                final float v3 = /*EL:55*/dropProperties.getPropertyFloat("posZ");
                final int v4 = /*EL:56*/dropProperties.getPropertyInt("particleAmount");
                final float v5 = /*EL:57*/dropProperties.getPropertyFloat("length");
                final float v6 = /*EL:58*/dropProperties.getPropertyFloat("height");
                final float v7 = /*EL:59*/dropProperties.getPropertyFloat("width");
                /*SL:61*/worldServer.func_180505_a(enumParticleTypes, true, (double)v, (double)v2, (double)v3, v4, (double)v5, (double)v6, (double)v7, 0.0, array);
            }
            else {
                final int n = /*EL:65*/ValueParser.getString(propertyString, v-7).equals("splashpotion") ? 2002 : ValueParser.getInteger(propertyString, v-7);
                int n2 = /*EL:66*/0;
                /*SL:67*/if (n == 2002) {
                    /*SL:69*/if (dropProperties.hasProperty("potion")) {
                        final List<PotionEffect> a2 = /*EL:71*/(List<PotionEffect>)Lists.<Object>newArrayList();
                        final PotionType v8 = /*EL:72*/PotionType.func_185168_a(dropProperties.getPropertyString("potion"));
                        /*SL:73*/a2.addAll(v8.func_185170_a());
                        /*SL:74*/n2 = PotionUtils.func_185181_a((Collection)a2);
                    }
                    else {
                        /*SL:76*/n2 = dropProperties.getPropertyInt("damage");
                    }
                }
                /*SL:78*/worldServer.func_175718_b(n, dropProperties.getBlockPos(), n2);
            }
        }
    }
    
    @Override
    public void registerProperties() {
        /*SL:86*/DropProperties.setDefaultProperty(this.getType(), "length", Float.class, 0.0f);
        /*SL:87*/DropProperties.setDefaultProperty(this.getType(), "height", Float.class, 0.0f);
        /*SL:88*/DropProperties.setDefaultProperty(this.getType(), "width", Float.class, 0.0f);
        /*SL:89*/DropProperties.setDefaultProperty(this.getType(), "size", String.class, "(0.0,0.0,0.0)");
        /*SL:90*/DropProperties.setDefaultProperty(this.getType(), "particleAmount", Integer.class, 1);
        /*SL:91*/DropProperties.setDefaultProperty(this.getType(), "potion", String.class, "poison");
    }
    
    @Override
    public String getType() {
        /*SL:97*/return "particle";
    }
}
