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
                /*SL:35*/n = ValueParser.getInteger(v0);
            }
            catch (Exception v5) {
                final Potion a1 = /*EL:39*/Potion.func_180142_b(v0);
                /*SL:40*/n = Potion.func_188409_a(a1);
            }
        }
        /*SL:44*/if (player != null) {
            /*SL:46*/if (v0.equals("special_fire")) {
                this.specialEffectFire(v-5, player);
            }
            else/*SL:47*/ if (v0.equals("special_knockback")) {
                this.specialEffectKnockback(v-5, player);
            }
            else {
                /*SL:48*/this.potionEffect(v-5, player, n);
            }
        }
        else/*SL:50*/ if (func_72314_b != null) {
            final List v = /*EL:52*/v-5.getWorld().func_72872_a((Class)EntityLivingBase.class, func_72314_b);
            /*SL:53*/if (!v.isEmpty()) {
                /*SL:55*/for (final EntityLivingBase v3 : v) {
                    /*SL:60*/if (v-5.getDropProperties().getPropertyBoolean("excludePlayer") && v3 == v-5.getPlayer()) {
                        continue;
                    }
                    final double v4 = /*EL:61*/v-5.getDropProperties().getVecPos().func_72438_d(v3.func_174791_d());
                    /*SL:63*/if (v4 > dropProperties.getPropertyFloat("range")) {
                        continue;
                    }
                    /*SL:65*/if (v0.equals("special_fire")) {
                        this.specialEffectFire(v-5, (Entity)v3);
                    }
                    else/*SL:66*/ if (v0.equals("special_knockback")) {
                        this.specialEffectKnockback(v-5, (Entity)v3);
                    }
                    else {
                        /*SL:67*/this.potionEffect(v-5, (Entity)v3, n);
                    }
                }
            }
        }
    }
    
    private void potionEffect(final DropProcessData a1, final Entity a2, final int a3) {
        final Potion v1 = /*EL:76*/Potion.func_188412_a(a3);
        int v2 = /*EL:77*/(int)(a1.getDropProperties().getPropertyFloat("duration") * 20.0);
        /*SL:78*/if (v1.func_76403_b()) {
            v2 = 1;
        }
        final PotionEffect v3 = /*EL:80*/new PotionEffect(v1, v2, (int)a1.getDropProperties().getPropertyInt("amplifier"));
        /*SL:81*/if (a2 instanceof EntityLivingBase) {
            ((EntityLivingBase)a2).func_70690_d(v3);
        }
    }
    
    private void specialEffectFire(final DropProcessData a1, final Entity a2) {
        /*SL:86*/a2.func_70015_d((int)a1.getDropProperties().getPropertyInt("duration"));
    }
    
    private void specialEffectKnockback(final DropProcessData a1, final Entity a2) {
        final Vec3d v1 = /*EL:91*/a1.getDropProperties().getVecPos();
        final float v2 = /*EL:92*/a1.getDropProperties().hasProperty("directionYaw") ? a1.getDropProperties().getPropertyFloat("directionYaw") : ((float)Math.toDegrees(Math.atan2((a2.field_70165_t - v1.field_72450_a) * -1.0, a2.field_70161_v - v1.field_72449_c)));
        float v3 = /*EL:93*/a1.getDropProperties().getPropertyFloat("directionPitch");
        float v4 = /*EL:94*/a1.getDropProperties().getPropertyFloat("power");
        /*SL:96*/if (!a1.getDropProperties().hasProperty("target") && v1.func_72438_d(a2.func_174791_d()) < 0.01) {
            /*SL:98*/v3 = -90.0f;
            /*SL:99*/v4 *= 0.5;
        }
        /*SL:102*/a2.field_70159_w = -MathHelper.func_76126_a(v2 / 180.0f * 3.1415927f) * MathHelper.func_76134_b(v3 / 180.0f * 3.1415927f) * v4;
        /*SL:103*/a2.field_70179_y = MathHelper.func_76134_b(v2 / 180.0f * 3.1415927f) * MathHelper.func_76134_b(v3 / 180.0f * 3.1415927f) * v4;
        /*SL:104*/a2.field_70181_x = -MathHelper.func_76126_a(v3 / 180.0f * 3.1415927f) * v4;
        /*SL:105*/a2.field_70133_I = true;
    }
    
    @Override
    public void registerProperties() {
        /*SL:111*/DropProperties.setDefaultProperty(this.getType(), "duration", Integer.class, 30);
        /*SL:112*/DropProperties.setDefaultProperty(this.getType(), "amplifier", Integer.class, 0);
        /*SL:113*/DropProperties.setDefaultProperty(this.getType(), "target", String.class, "player");
        /*SL:114*/DropProperties.setDefaultProperty(this.getType(), "excludePlayer", Boolean.class, false);
        /*SL:115*/DropProperties.setDefaultProperty(this.getType(), "range", Float.class, 4);
        /*SL:116*/DropProperties.setDefaultProperty(this.getType(), "power", Float.class, 1);
        /*SL:117*/DropProperties.setDefaultProperty(this.getType(), "directionYaw", Float.class, 0);
        /*SL:118*/DropProperties.setDefaultProperty(this.getType(), "directionPitch", Float.class, -30);
    }
    
    @Override
    public String getType() {
        /*SL:124*/return "effect";
    }
}
