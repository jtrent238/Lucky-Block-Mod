package mod.lucky.drop.func;

import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.potion.PotionEffect;
import java.util.Iterator;
import java.util.List;
import mod.lucky.drop.DropProperties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import mod.lucky.drop.value.ValueParser;
import net.minecraft.util.math.AxisAlignedBB;

public class DropFuncEffect extends DropFunction
{
    @Override
    public void process(final DropProcessData v-5) {
        final DropProperties dropProperties = /*EL:18*/v-5.getDropProperties();
        Entity player = /*EL:20*/null;
        final AxisAlignedBB func_72321_a = /*EL:22*/new AxisAlignedBB(dropProperties.getBlockPos(), dropProperties.getBlockPos()).func_72321_a((double)(dropProperties.getPropertyInt("range") * /*EL:24*/2), (double)(dropProperties.getPropertyInt("range") * /*EL:25*/2), (double)(dropProperties.getPropertyInt("range") * /*EL:26*/2));
        /*SL:27*/if (dropProperties.hasProperty("target") && !dropProperties.hasProperty("range")) {
            /*SL:32*/player = (dropProperties.getPropertyString("target").equals("player") ? v-5.getPlayer() : (dropProperties.getPropertyString("target").equals("hitEntity") ? v-5.getHitEntity() : null));
        }
        /*SL:34*/if (!dropProperties.hasProperty("target") && !dropProperties.hasProperty("range")) {
            player = v-5.getPlayer();
        }
        /*SL:35*/if (dropProperties.getPropertyString("target").equals("hitEntity") && v-5.getHitEntity() == null) {
            /*SL:36*/return;
        }
        int n = /*EL:38*/-1;
        final String v0 = /*EL:39*/dropProperties.getPropertyString("ID");
        /*SL:40*/if (!v0.equals("special_fire") && !v0.equals("special_knockback")) {
            try {
                /*SL:42*/n = ValueParser.getInteger(v0);
            }
            catch (Exception v5) {
                final Potion a1 = /*EL:44*/Potion.func_180142_b(v0);
                /*SL:45*/n = Potion.func_188409_a(a1);
            }
        }
        /*SL:49*/if (player != null) {
            /*SL:50*/if (v0.equals("special_fire")) {
                this.specialEffectFire(v-5, player);
            }
            else/*SL:51*/ if (v0.equals("special_knockback")) {
                /*SL:52*/this.specialEffectKnockback(v-5, player);
            }
            else {
                /*SL:53*/this.potionEffect(v-5, player, n);
            }
        }
        else/*SL:54*/ if (func_72321_a != null) {
            final List v = /*EL:55*/v-5.getWorld().func_72872_a((Class)EntityLivingBase.class, func_72321_a);
            /*SL:56*/if (!v.isEmpty()) {
                /*SL:57*/for (final EntityLivingBase v3 : v) {
                    /*SL:61*/if (v-5.getDropProperties().getPropertyBoolean("excludePlayer") && v3 == v-5.getPlayer()) {
                        /*SL:62*/continue;
                    }
                    final double v4 = /*EL:63*/v-5.getDropProperties().getVecPos().func_72438_d(/*EL:66*/v3.func_174791_d());
                    /*SL:69*/if (v4 > dropProperties.getPropertyFloat("range")) {
                        continue;
                    }
                    /*SL:70*/if (v0.equals("special_fire")) {
                        /*SL:71*/this.specialEffectFire(v-5, (Entity)v3);
                    }
                    else/*SL:72*/ if (v0.equals("special_knockback")) {
                        /*SL:73*/this.specialEffectKnockback(v-5, (Entity)v3);
                    }
                    else {
                        /*SL:74*/this.potionEffect(v-5, (Entity)v3, n);
                    }
                }
            }
        }
    }
    
    private void potionEffect(final DropProcessData a1, final Entity a2, final int a3) {
        final Potion v1 = /*EL:82*/Potion.func_188412_a(a3);
        int v2 = /*EL:83*/(int)(a1.getDropProperties().getPropertyFloat("duration") * 20.0);
        /*SL:84*/if (v1.func_76403_b()) {
            v2 = 1;
        }
        final PotionEffect v3 = /*EL:88*/new PotionEffect(v1, v2, (int)a1.getDropProperties().getPropertyInt("amplifier"));
        /*SL:89*/if (a2 instanceof EntityLivingBase) {
            /*SL:90*/((EntityLivingBase)a2).func_70690_d(v3);
        }
    }
    
    private void specialEffectFire(final DropProcessData a1, final Entity a2) {
        /*SL:94*/a2.func_70015_d((int)a1.getDropProperties().getPropertyInt("duration"));
    }
    
    private void specialEffectKnockback(final DropProcessData a1, final Entity a2) {
        final Vec3d v1 = /*EL:98*/a1.getDropProperties().getVecPos();
        final float v2 = /*EL:99*/a1.getDropProperties().hasProperty(/*EL:100*/"directionYaw") ? a1.getDropProperties().getPropertyFloat(/*EL:101*/"directionYaw") : /*EL:103*/((float)Math.toDegrees(Math.atan2((a2.field_70165_t - v1.field_72450_a) * -1.0, a2.field_70161_v - v1.field_72449_c)));
        float v3 = /*EL:104*/a1.getDropProperties().getPropertyFloat("directionPitch");
        float v4 = /*EL:105*/a1.getDropProperties().getPropertyFloat("power");
        /*SL:107*/if (!a1.getDropProperties().hasProperty("target") && v1.func_72438_d(a2.func_174791_d()) < /*EL:108*/0.01) {
            /*SL:109*/v3 = -90.0f;
            /*SL:110*/v4 *= 0.5;
        }
        /*SL:113*/a2.field_70159_w = /*EL:114*/-MathHelper.func_76126_a(v2 / 180.0f * 3.1415927f) * /*EL:115*/MathHelper.func_76134_b(v3 / 180.0f * 3.1415927f) * v4;
        /*SL:117*/a2.field_70179_y = /*EL:118*/MathHelper.func_76134_b(v2 / 180.0f * 3.1415927f) * /*EL:119*/MathHelper.func_76134_b(v3 / 180.0f * 3.1415927f) * v4;
        /*SL:121*/a2.field_70181_x = -MathHelper.func_76126_a(v3 / 180.0f * 3.1415927f) * v4;
        /*SL:122*/a2.field_70133_I = true;
    }
    
    @Override
    public void registerProperties() {
        /*SL:127*/DropProperties.setDefaultProperty(this.getType(), "duration", Integer.class, 30);
        /*SL:128*/DropProperties.setDefaultProperty(this.getType(), "amplifier", Integer.class, 0);
        /*SL:129*/DropProperties.setDefaultProperty(this.getType(), "target", String.class, "player");
        /*SL:130*/DropProperties.setDefaultProperty(this.getType(), "excludePlayer", Boolean.class, false);
        /*SL:131*/DropProperties.setDefaultProperty(this.getType(), "range", Float.class, 4);
        /*SL:132*/DropProperties.setDefaultProperty(this.getType(), "power", Float.class, 1);
        /*SL:133*/DropProperties.setDefaultProperty(this.getType(), "directionYaw", Float.class, 0);
        /*SL:134*/DropProperties.setDefaultProperty(this.getType(), "directionPitch", Float.class, -30);
    }
    
    @Override
    public String getType() {
        /*SL:139*/return "effect";
    }
}
