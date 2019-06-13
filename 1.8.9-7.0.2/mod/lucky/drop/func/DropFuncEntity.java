package mod.lucky.drop.func;

import mod.lucky.drop.DropProperties;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.BlockPos;
import mod.lucky.entity.EntityLuckyProjectile;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.MathHelper;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.nbt.NBTTagCompound;

public class DropFuncEntity extends DropFunction
{
    @Override
    public void process(final DropProcessData v-10) {
        final DropProperties dropProperties = /*EL:23*/v-10.getDropProperties();
        final float floatValue = /*EL:25*/dropProperties.getPropertyFloat("posX");
        final float floatValue2 = /*EL:26*/dropProperties.getPropertyFloat("posY");
        final float floatValue3 = /*EL:27*/dropProperties.getPropertyFloat("posZ");
        /*SL:29*/if (floatValue2 <= -1.0f) {
            return;
        }
        final NBTTagCompound nbtTagCompound = /*EL:32*/(dropProperties.getPropertyNBT("NBTTag") == null) ? new NBTTagCompound() : dropProperties.getPropertyNBT("NBTTag");
        final String propertyString = /*EL:34*/dropProperties.getPropertyString("ID");
        /*SL:35*/if (propertyString.equals("LightningBolt")) {
            /*SL:37*/v-10.getWorld().func_72942_c((Entity)new EntityLightningBolt(v-10.getWorld(), (double)floatValue, (double)floatValue2, (double)floatValue3));
            /*SL:38*/return;
        }
        /*SL:42*/if (!nbtTagCompound.func_74764_b("id")) {
            nbtTagCompound.func_74778_a("id", propertyString);
        }
        /*SL:43*/if (!nbtTagCompound.func_74764_b("ownerName")) {
            nbtTagCompound.func_74778_a("ownerName", v-10.getPlayer().func_70005_c_());
        }
        final boolean func_74764_b = /*EL:45*/nbtTagCompound.func_74764_b("Pos");
        final boolean func_74764_b2 = /*EL:46*/nbtTagCompound.func_74764_b("Motion");
        final boolean v0 = /*EL:47*/nbtTagCompound.func_74764_b("Rotation");
        final Entity func_75615_a;
        /*SL:49*/if (propertyString.equals("ThrownEgg")) {
            final Entity a1 = /*EL:51*/(Entity)new EntityEgg(v-10.getWorld(), (double)floatValue, (double)floatValue2, (double)floatValue3);
            /*SL:52*/a1.func_70020_e(nbtTagCompound);
        }
        else {
            /*SL:54*/func_75615_a = EntityList.func_75615_a(nbtTagCompound, v-10.getWorld());
        }
        /*SL:55*/if (func_75615_a instanceof EntityFallingBlock && !nbtTagCompound.func_74764_b("Time")) {
            ((EntityFallingBlock)func_75615_a).field_145812_b = 1;
        }
        /*SL:57*/if (func_75615_a == null) {
            System.err.println(/*EL:59*/"Lucky Block: Error loading entity '" + propertyString + "'.");
            /*SL:60*/return;
        }
        /*SL:63*/if (!func_74764_b) {
            /*SL:65*/func_75615_a.field_70165_t = floatValue;
            /*SL:66*/func_75615_a.field_70163_u = floatValue2;
            /*SL:67*/func_75615_a.field_70161_v = floatValue3;
        }
        /*SL:69*/if (func_75615_a instanceof EntityThrowable && !v0 && func_74764_b2) {
            final float v = /*EL:71*/MathHelper.func_76133_a(func_75615_a.field_70159_w * func_75615_a.field_70159_w + func_75615_a.field_70179_y * func_75615_a.field_70179_y);
            /*SL:72*/func_75615_a.field_70177_z = (float)(Math.atan2(func_75615_a.field_70159_w, func_75615_a.field_70179_y) * 180.0 / 3.141592653589793);
            /*SL:73*/func_75615_a.field_70125_A = (float)(Math.atan2(func_75615_a.field_70181_x, v) * 180.0 / 3.141592653589793);
            /*SL:74*/func_75615_a.field_70133_I = true;
        }
        /*SL:76*/if (func_75615_a instanceof EntityArrow) {
            ((EntityArrow)func_75615_a).field_70250_c = v-10.getPlayer();
        }
        /*SL:77*/if (func_75615_a instanceof EntityLuckyProjectile) {
            ((EntityLuckyProjectile)func_75615_a).field_70250_c = v-10.getPlayer();
        }
        /*SL:80*/for (int v2 = 0; v2 < 10; ++v2) {
            /*SL:82*/if (v-10.getWorld().func_175623_d(new BlockPos(func_75615_a.field_70165_t, func_75615_a.field_70163_u + v2, func_75615_a.field_70161_v))) {
                final Entity entity = /*EL:84*/func_75615_a;
                entity.field_70163_u += v2;
                /*SL:85*/break;
            }
        }
        /*SL:88*/func_75615_a.func_70012_b(func_75615_a.field_70165_t, func_75615_a.field_70163_u, func_75615_a.field_70161_v, func_75615_a.field_70177_z, func_75615_a.field_70125_A);
        /*SL:90*/if (func_75615_a != null && func_75615_a instanceof EntityLiving) {
            /*SL:93*/if (v-10.getProcessType() != DropProcessData.EnumProcessType.LUCKY_STRUCT) {
                ((EntityLiving)func_75615_a).func_180482_a(v-10.getWorld().func_175649_E(new BlockPos(func_75615_a)), (IEntityLivingData)null);
            }
            /*SL:94*/((EntityLiving)func_75615_a).func_70037_a(nbtTagCompound);
        }
        /*SL:98*/if (dropProperties.hasProperty("NBTTag") && func_75615_a != null) {
            /*SL:100*/v-10.getWorld().func_72838_d(func_75615_a);
            Entity entity2 = /*EL:101*/func_75615_a;
            /*SL:103*/for (NBTTagCompound func_74775_l = nbtTagCompound; func_74775_l.func_150297_b("Riding", 10); func_74775_l = func_74775_l.func_74775_l("Riding")) {
                final Entity v3 = /*EL:105*/EntityList.func_75615_a(func_74775_l.func_74775_l("Riding"), v-10.getWorld());
                /*SL:107*/if (v3 != null) {
                    /*SL:109*/v3.func_70012_b(func_75615_a.field_70165_t, func_75615_a.field_70163_u, func_75615_a.field_70161_v, func_75615_a.field_70177_z, func_75615_a.field_70125_A);
                    /*SL:110*/v-10.getWorld().func_72838_d(v3);
                    /*SL:111*/entity2.func_70078_a(v3);
                }
                /*SL:114*/entity2 = v3;
            }
        }
        else/*SL:117*/ if (func_75615_a != null) {
            /*SL:119*/v-10.getWorld().func_72838_d(func_75615_a);
        }
    }
    
    @Override
    public void registerProperties() {
        /*SL:126*/DropProperties.setDefaultProperty(this.getType(), "NBTTag", NBTTagCompound.class, null);
        /*SL:127*/DropProperties.setDefaultProperty(this.getType(), "posX", Float.class, 0.0f);
        /*SL:128*/DropProperties.setDefaultProperty(this.getType(), "posY", Float.class, 0.0f);
        /*SL:129*/DropProperties.setDefaultProperty(this.getType(), "posZ", Float.class, 0.0f);
        /*SL:130*/DropProperties.setDefaultProperty(this.getType(), "posOffsetX", Float.class, 0.0f);
        /*SL:131*/DropProperties.setDefaultProperty(this.getType(), "posOffsetY", Float.class, 0.0f);
        /*SL:132*/DropProperties.setDefaultProperty(this.getType(), "posOffsetZ", Float.class, 0.0f);
    }
    
    @Override
    public String getType() {
        /*SL:138*/return "entity";
    }
}
