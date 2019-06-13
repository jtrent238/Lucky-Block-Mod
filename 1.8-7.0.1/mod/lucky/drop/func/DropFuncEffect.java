package mod.lucky.drop.func;

import net.minecraft.util.Vec3;
import net.minecraft.util.MathHelper;
import net.minecraft.potion.PotionEffect;
import java.util.Iterator;
import java.util.List;
import mod.lucky.drop.DropProperties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import mod.lucky.drop.value.ValueParser;
import net.minecraft.util.AxisAlignedBB;

public class DropFuncEffect extends DropFunction
{
    @Override
    public void process(final DropProcessData v-5) {
        final DropProperties dropProperties = /*EL:21*/v-5.getDropProperties();
        Entity player = /*EL:23*/null;
        final AxisAlignedBB func_72314_b = /*EL:24*/new AxisAlignedBB(dropProperties.getBlockPos(), dropProperties.getBlockPos()).func_72314_b((double)(dropProperties.getPropertyInt("range") * 2), (double)(dropProperties.getPropertyInt("range") * 2), (double)(dropProperties.getPropertyInt("range") * 2));
        /*SL:25*/if (dropProperties.hasProperty("target") && !dropProperties.hasProperty("range")) {
            player = (dropProperties.getPropertyString("target").equals("player") ? v-5.getPlayer() : (dropProperties.getPropertyString("target").equals("hitEntity") ? v-5.getHitEntity() : null));
        }
        /*SL:26*/if (!dropProperties.hasProperty("target") && !dropProperties.hasProperty("range")) {
            player = v-5.getPlayer();
        }
        /*SL:27*/if (dropProperties.getPropertyString("target").equals("hitEntity") && v-5.getHitEntity() == null) {
            return;
        }
        int n = /*EL:29*/-1;
        final String v0 = /*EL:30*/dropProperties.getPropertyString("ID");
        /*SL:31*/if (!v0.equals("special_fire") && !v0.equals("special_knockback")) {
            try {
                /*SL:36*/n = ValueParser.getInteger(v0);
            }
            catch (Exception v5) {
                final Potion a1 = /*EL:40*/Potion.func_180142_b(v0);
                /*SL:41*/n = a1.field_76415_H;
            }
        }
        /*SL:45*/if (player != null) {
            /*SL:47*/if (v0.equals("special_fire")) {
                this.specialEffectFire(v-5, player);
            }
            else/*SL:48*/ if (v0.equals("special_knockback")) {
                this.specialEffectKnockback(v-5, player);
            }
            else {
                /*SL:49*/this.potionEffect(v-5, player, n);
            }
        }
        else/*SL:51*/ if (func_72314_b != null) {
            final List v = /*EL:53*/v-5.getWorld().func_72872_a((Class)EntityLivingBase.class, func_72314_b);
            /*SL:54*/if (!v.isEmpty()) {
                /*SL:56*/for (final EntityLivingBase v3 : v) {
                    /*SL:61*/if (v-5.getDropProperties().getPropertyBoolean("excludePlayer") && v3 == v-5.getPlayer()) {
                        continue;
                    }
                    final double v4 = /*EL:62*/v-5.getDropProperties().getVecPos().func_72438_d(v3.func_174791_d());
                    /*SL:64*/if (v4 > dropProperties.getPropertyFloat("range")) {
                        continue;
                    }
                    /*SL:66*/if (v0.equals("special_fire")) {
                        this.specialEffectFire(v-5, (Entity)v3);
                    }
                    else/*SL:67*/ if (v0.equals("special_knockback")) {
                        this.specialEffectKnockback(v-5, (Entity)v3);
                    }
                    else {
                        /*SL:68*/this.potionEffect(v-5, (Entity)v3, n);
                    }
                }
            }
        }
    }
    
    private void potionEffect(final DropProcessData a1, final Entity a2, final int a3) {
        final Potion v1 = /*EL:77*/Potion.field_76425_a[a3];
        int v2 = /*EL:78*/(int)(a1.getDropProperties().getPropertyFloat("duration") * 20.0);
        /*SL:79*/if (v1.func_76403_b()) {
            v2 = 1;
        }
        final PotionEffect v3 = /*EL:81*/new PotionEffect(a3, v2, (int)a1.getDropProperties().getPropertyInt("amplifier"));
        /*SL:82*/if (a2 instanceof EntityLivingBase) {
            ((EntityLivingBase)a2).func_70690_d(v3);
        }
    }
    
    private void specialEffectFire(final DropProcessData a1, final Entity a2) {
        /*SL:87*/a2.func_70015_d((int)a1.getDropProperties().getPropertyInt("duration"));
    }
    
    private void specialEffectKnockback(final DropProcessData a1, final Entity a2) {
        final Vec3 v1 = /*EL:92*/a1.getDropProperties().getVecPos();
        final float v2 = /*EL:93*/a1.getDropProperties().hasProperty("directionYaw") ? a1.getDropProperties().getPropertyFloat("directionYaw") : ((float)Math.toDegrees(Math.atan2((a2.field_70165_t - v1.field_72450_a) * -1.0, a2.field_70161_v - v1.field_72449_c)));
        float v3 = /*EL:94*/a1.getDropProperties().getPropertyFloat("directionPitch");
        float v4 = /*EL:95*/a1.getDropProperties().getPropertyFloat("power");
        /*SL:97*/if (!a1.getDropProperties().hasProperty("target") && v1.func_72438_d(a2.func_174791_d()) < 0.01) {
            /*SL:99*/v3 = -90.0f;
            /*SL:100*/v4 *= 0.5;
        }
        /*SL:103*/a2.field_70159_w = -MathHelper.func_76126_a(v2 / 180.0f * 3.1415927f) * MathHelper.func_76134_b(v3 / 180.0f * 3.1415927f) * v4;
        /*SL:104*/a2.field_70179_y = MathHelper.func_76134_b(v2 / 180.0f * 3.1415927f) * MathHelper.func_76134_b(v3 / 180.0f * 3.1415927f) * v4;
        /*SL:105*/a2.field_70181_x = -MathHelper.func_76126_a(v3 / 180.0f * 3.1415927f) * v4;
        /*SL:106*/a2.field_70133_I = true;
    }
    
    @Override
    public void registerProperties() {
        /*SL:112*/DropProperties.setDefaultProperty(this.getType(), "duration", Integer.class, 30);
        /*SL:113*/DropProperties.setDefaultProperty(this.getType(), "amplifier", Integer.class, 0);
        /*SL:114*/DropProperties.setDefaultProperty(this.getType(), "target", String.class, "player");
        /*SL:115*/DropProperties.setDefaultProperty(this.getType(), "excludePlayer", Boolean.class, false);
        /*SL:116*/DropProperties.setDefaultProperty(this.getType(), "range", Float.class, 4);
        /*SL:117*/DropProperties.setDefaultProperty(this.getType(), "power", Float.class, 1);
        /*SL:118*/DropProperties.setDefaultProperty(this.getType(), "directionYaw", Float.class, 0);
        /*SL:119*/DropProperties.setDefaultProperty(this.getType(), "directionPitch", Float.class, -30);
    }
    
    @Override
    public String getType() {
        /*SL:125*/return "effect";
    }
}
