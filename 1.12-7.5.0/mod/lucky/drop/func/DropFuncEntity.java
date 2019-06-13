package mod.lucky.drop.func;

import net.minecraft.nbt.NBTTagList;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.projectile.EntityArrow;
import mod.lucky.entity.EntityLuckyProjectile;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.entity.EntityList;
import net.minecraft.world.World;
import mod.lucky.drop.DropProperties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.nbt.NBTTagCompound;

public class DropFuncEntity extends DropFunction
{
    @Override
    public void process(final DropProcessData a1) {
        final DropProperties v1 = /*EL:27*/a1.getDropProperties();
        final float v2 = /*EL:29*/v1.getPropertyFloat("posX");
        final float v3 = /*EL:30*/v1.getPropertyFloat("posY");
        final float v4 = /*EL:31*/v1.getPropertyFloat("posZ");
        /*SL:33*/if (v3 <= -1.0f) {
            return;
        }
        final NBTTagCompound v5 = /*EL:36*/(v1.getPropertyNBT("NBTTag") == null) ? new NBTTagCompound() : v1.getPropertyNBT("NBTTag");
        final String v6 = /*EL:38*/v1.getPropertyString("ID");
        /*SL:39*/if (v6.equals("LightningBolt")) {
            /*SL:41*/a1.getWorld().func_72942_c((Entity)new EntityLightningBolt(a1.getWorld(), (double)v2, (double)v3, (double)v4, false));
            /*SL:42*/return;
        }
        /*SL:46*/v5.func_74778_a("id", v6);
        spawnEntity(/*EL:47*/a1, v5, a1.getWorld(), v2, v3, v4);
    }
    
    private static Entity spawnEntity(final DropProcessData a6, final NBTTagCompound v1, final World v2, final double v3, final double v5, final double v7) {
        final Entity v8 = /*EL:53*/EntityList.func_75615_a(v1, v2);
        /*SL:54*/if (v8 == null) {
            return null;
        }
        /*SL:56*/if (v8 instanceof EntityTippedArrow && !v1.func_74764_b("Potion") && !v1.func_74764_b("CustomPotionEffects")) {
            ((EntityTippedArrow)v8).func_184555_a(new ItemStack(Items.field_151032_g));
        }
        final boolean v9 = /*EL:58*/v1.func_74764_b("Pos");
        final boolean v10 = /*EL:59*/v1.func_74764_b("Motion");
        final boolean v11 = /*EL:60*/v1.func_74764_b("Rotation");
        /*SL:61*/if (!v9) {
            /*SL:63*/v8.field_70165_t = v3;
            /*SL:64*/v8.field_70163_u = v5;
            /*SL:65*/v8.field_70161_v = v7;
        }
        /*SL:67*/if (v8 instanceof EntityThrowable && !v11 && v10) {
            final float a7 = /*EL:69*/MathHelper.func_76133_a(v8.field_70159_w * v8.field_70159_w + v8.field_70179_y * v8.field_70179_y);
            /*SL:70*/v8.field_70177_z = (float)(Math.atan2(v8.field_70159_w, v8.field_70179_y) * 180.0 / 3.141592653589793);
            /*SL:71*/v8.field_70125_A = (float)(Math.atan2(v8.field_70181_x, a7) * 180.0 / 3.141592653589793);
            /*SL:72*/v8.field_70133_I = true;
        }
        /*SL:75*/if (v8 instanceof EntityFallingBlock && !v1.func_74764_b("Time")) {
            ((EntityFallingBlock)v8).field_145812_b = 1;
        }
        else/*SL:76*/ if (v8 instanceof EntityLuckyProjectile) {
            ((EntityLuckyProjectile)v8).shootingEntity = a6.getPlayer();
        }
        else/*SL:77*/ if (v8 instanceof EntityArrow) {
            ((EntityArrow)v8).field_70250_c = a6.getPlayer();
        }
        /*SL:80*/for (int a8 = 0; a8 < 10; ++a8) {
            /*SL:82*/if (a6.getWorld().func_175623_d(new BlockPos(v8.field_70165_t, v8.field_70163_u + a8, v8.field_70161_v))) {
                final Entity entity = /*EL:84*/v8;
                entity.field_70163_u += a8;
                /*SL:85*/break;
            }
        }
        /*SL:88*/v8.func_70012_b(v8.field_70165_t, v8.field_70163_u, v8.field_70161_v, v8.field_70177_z, v8.field_70125_A);
        /*SL:90*/if (v8 instanceof EntityLiving) {
            /*SL:92*/if (a6.getProcessType() != DropProcessData.EnumProcessType.LUCKY_STRUCT) {
                ((EntityLiving)v8).func_180482_a(v2.func_175649_E(new BlockPos(v8)), (IEntityLivingData)null);
            }
            /*SL:93*/((EntityLiving)v8).func_70037_a(v1);
        }
        /*SL:96*/if (!v2.func_72838_d(v8)) {
            /*SL:98*/return null;
        }
        /*SL:102*/if (v1.func_150297_b("Passengers", 9)) {
            final NBTTagList a9 = /*EL:104*/v1.func_150295_c("Passengers", 10);
            /*SL:106*/for (int a10 = 0; a10 < a9.func_74745_c(); ++a10) {
                final Entity a11 = spawnEntity(/*EL:108*/a6, a9.func_150305_b(a10), v2, v3, v5, v7);
                /*SL:109*/if (a11 != null) {
                    a11.func_184205_a(v8, true);
                }
            }
        }
        /*SL:113*/return v8;
    }
    
    @Override
    public void registerProperties() {
        /*SL:120*/DropProperties.setDefaultProperty(this.getType(), "NBTTag", NBTTagCompound.class, null);
        /*SL:121*/DropProperties.setDefaultProperty(this.getType(), "posX", Float.class, 0.0f);
        /*SL:122*/DropProperties.setDefaultProperty(this.getType(), "posY", Float.class, 0.0f);
        /*SL:123*/DropProperties.setDefaultProperty(this.getType(), "posZ", Float.class, 0.0f);
        /*SL:124*/DropProperties.setDefaultProperty(this.getType(), "posOffsetX", Float.class, 0.0f);
        /*SL:125*/DropProperties.setDefaultProperty(this.getType(), "posOffsetY", Float.class, 0.0f);
        /*SL:126*/DropProperties.setDefaultProperty(this.getType(), "posOffsetZ", Float.class, 0.0f);
    }
    
    @Override
    public String getType() {
        /*SL:132*/return "entity";
    }
}
