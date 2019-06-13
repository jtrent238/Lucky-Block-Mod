package mod.lucky.drop.func;

import mod.lucky.drop.DropProperties;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.entity.projectile.EntityThrowable;
import mod.lucky.drop.value.DropStringUtils;
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
        final DropProperties dropProperties = /*EL:22*/v-10.getDropProperties();
        final float floatValue = /*EL:24*/dropProperties.getPropertyFloat("posX");
        final float floatValue2 = /*EL:25*/dropProperties.getPropertyFloat("posY");
        final float floatValue3 = /*EL:26*/dropProperties.getPropertyFloat("posZ");
        /*SL:28*/if (floatValue2 <= -1.0f) {
            return;
        }
        final NBTTagCompound nbtTagCompound = /*EL:31*/(dropProperties.getPropertyNBT("NBTTag") == null) ? new NBTTagCompound() : dropProperties.getPropertyNBT("NBTTag");
        final String propertyString = /*EL:33*/dropProperties.getPropertyString("ID");
        /*SL:34*/if (propertyString.equals("LightningBolt")) {
            /*SL:36*/v-10.getWorld().func_72942_c((Entity)new EntityLightningBolt(v-10.getWorld(), (double)floatValue, (double)floatValue2, (double)floatValue3));
            /*SL:37*/return;
        }
        /*SL:41*/if (!nbtTagCompound.func_74764_b("id")) {
            nbtTagCompound.func_74778_a("id", propertyString);
        }
        final boolean func_74764_b = /*EL:43*/nbtTagCompound.func_74764_b("Pos");
        final boolean func_74764_b2 = /*EL:44*/nbtTagCompound.func_74764_b("Motion");
        final boolean v0 = /*EL:45*/nbtTagCompound.func_74764_b("Rotation");
        final Entity func_75615_a;
        /*SL:47*/if (propertyString.equals("ThrownEgg")) {
            final Entity a1 = /*EL:49*/(Entity)new EntityEgg(v-10.getWorld(), floatValue + 0.5, (double)floatValue2, floatValue3 + 0.5);
            /*SL:50*/a1.func_70020_e(nbtTagCompound);
        }
        else {
            /*SL:52*/func_75615_a = EntityList.func_75615_a(nbtTagCompound, v-10.getWorld());
        }
        /*SL:53*/if (func_75615_a instanceof EntityFallingBlock && !nbtTagCompound.func_74764_b("Time")) {
            ((EntityFallingBlock)func_75615_a).field_145812_b = 1;
        }
        /*SL:55*/if (func_75615_a == null) {
            System.err.println(/*EL:57*/"Lucky Block: Error loading entity '" + propertyString + "'. Entity does not exist.");
            /*SL:58*/return;
        }
        /*SL:61*/if (!func_74764_b) {
            /*SL:63*/func_75615_a.field_70165_t = floatValue + (DropStringUtils.isGenericFloat(dropProperties.getRawProperty("posX").getRawValue()) ? 0.0 : 0.5);
            /*SL:64*/func_75615_a.field_70163_u = floatValue2;
            /*SL:65*/func_75615_a.field_70161_v = floatValue3 + (DropStringUtils.isGenericFloat(dropProperties.getRawProperty("posZ").getRawValue()) ? 0.0 : 0.5);
        }
        /*SL:67*/if (func_75615_a instanceof EntityThrowable && !v0 && func_74764_b2) {
            final float v = /*EL:69*/MathHelper.func_76133_a(func_75615_a.field_70159_w * func_75615_a.field_70159_w + func_75615_a.field_70179_y * func_75615_a.field_70179_y);
            /*SL:70*/func_75615_a.field_70177_z = (float)(Math.atan2(func_75615_a.field_70159_w, func_75615_a.field_70179_y) * 180.0 / 3.141592653589793);
            /*SL:71*/func_75615_a.field_70125_A = (float)(Math.atan2(func_75615_a.field_70181_x, v) * 180.0 / 3.141592653589793);
        }
        /*SL:75*/for (int v2 = 0; v2 < 10; ++v2) {
            /*SL:77*/if (v-10.getWorld().func_175623_d(new BlockPos(func_75615_a.field_70165_t, func_75615_a.field_70163_u + v2, func_75615_a.field_70161_v))) {
                final Entity entity = /*EL:79*/func_75615_a;
                entity.field_70163_u += v2;
                /*SL:80*/break;
            }
        }
        /*SL:83*/func_75615_a.func_70012_b(func_75615_a.field_70165_t, func_75615_a.field_70163_u, func_75615_a.field_70161_v, func_75615_a.field_70177_z, func_75615_a.field_70125_A);
        /*SL:85*/if (func_75615_a != null && func_75615_a instanceof EntityLiving) {
            /*SL:88*/if (v-10.getProcessType() != DropProcessData.EnumProcessType.LUCKY_STRUCT) {
                ((EntityLiving)func_75615_a).func_180482_a(v-10.getWorld().func_175649_E(new BlockPos(func_75615_a)), (IEntityLivingData)null);
            }
            /*SL:89*/((EntityLiving)func_75615_a).func_70037_a(nbtTagCompound);
        }
        /*SL:93*/if (dropProperties.hasProperty("NBTTag") && func_75615_a != null) {
            /*SL:95*/v-10.getWorld().func_72838_d(func_75615_a);
            Entity entity2 = /*EL:96*/func_75615_a;
            /*SL:98*/for (NBTTagCompound func_74775_l = nbtTagCompound; func_74775_l.func_150297_b("Riding", 10); func_74775_l = func_74775_l.func_74775_l("Riding")) {
                final Entity v3 = /*EL:100*/EntityList.func_75615_a(func_74775_l.func_74775_l("Riding"), v-10.getWorld());
                /*SL:102*/if (v3 != null) {
                    /*SL:104*/v3.func_70012_b(func_75615_a.field_70165_t, func_75615_a.field_70163_u, func_75615_a.field_70161_v, func_75615_a.field_70177_z, func_75615_a.field_70125_A);
                    /*SL:105*/v-10.getWorld().func_72838_d(v3);
                    /*SL:106*/entity2.func_70078_a(v3);
                }
                /*SL:109*/entity2 = v3;
            }
        }
        else/*SL:112*/ if (func_75615_a != null) {
            /*SL:114*/v-10.getWorld().func_72838_d(func_75615_a);
        }
    }
    
    @Override
    public void registerProperties() {
        /*SL:121*/DropProperties.setDefaultProperty(this.getType(), "NBTTag", NBTTagCompound.class, null);
        /*SL:122*/DropProperties.setDefaultProperty(this.getType(), "posX", Float.class, 0.0f);
        /*SL:123*/DropProperties.setDefaultProperty(this.getType(), "posY", Float.class, 0.0f);
        /*SL:124*/DropProperties.setDefaultProperty(this.getType(), "posZ", Float.class, 0.0f);
        /*SL:125*/DropProperties.setDefaultProperty(this.getType(), "posOffsetX", Float.class, 0.0f);
        /*SL:126*/DropProperties.setDefaultProperty(this.getType(), "posOffsetY", Float.class, 0.0f);
        /*SL:127*/DropProperties.setDefaultProperty(this.getType(), "posOffsetZ", Float.class, 0.0f);
    }
    
    @Override
    public String getType() {
        /*SL:133*/return "entity";
    }
}
