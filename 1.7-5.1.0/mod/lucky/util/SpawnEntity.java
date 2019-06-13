package mod.lucky.util;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.util.MathHelper;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.EntityLivingBase;
import mod.lucky.entity.EntityLuckyEgg;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.nbt.NBTTagCompound;
import mod.lucky.drop.LuckyDrop;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import java.util.Random;

public class SpawnEntity
{
    public Random random;
    
    public SpawnEntity() {
        this.random = new Random();
    }
    
    public void setRandom(final Random a1) {
        /*SL:33*/this.random = a1;
    }
    
    public void spawnEntity(final World v-5, final EntityPlayer v-4, final LuckyDrop v-3) {
        final int posX = /*EL:38*/v-3.getPosX();
        final int posY = /*EL:39*/v-3.getPosY();
        final int v0 = /*EL:40*/v-3.getPosZ();
        /*SL:42*/if (posY == -1) {
            return;
        }
        final NBTTagCompound v = /*EL:45*/(v-3.getNBTTag() == null) ? new NBTTagCompound() : v-3.getNBTTag();
        Entity v2 = null;
        /*SL:47*/if (v-3.getId().equals("XP")) {
            final int a1 = /*EL:49*/EntityXPOrb.func_70527_a(1);
            final Entity a2 = /*EL:50*/(Entity)new EntityXPOrb(v-5, posX + 0.5, (double)posY, v0 + 0.5, a1);
        }
        else {
            /*SL:52*/if (v-3.getId().equals("lightning")) {
                /*SL:54*/v-5.func_72942_c((Entity)new EntityLightningBolt(v-5, posX + 0.5, (double)(posY + 1), v0 + 0.5));
                /*SL:55*/return;
            }
            /*SL:57*/if (v-3.getId().equals("fireworks")) {
                final EntityFireworkRocket a3 = /*EL:59*/new EntityFireworkRocket(v-5, posX + 0.5, (double)posY, v0 + 0.5, (ItemStack)null);
                /*SL:60*/a3.func_70037_a(v-3.getNBTTag());
                /*SL:61*/v2 = (Entity)a3;
            }
            else/*SL:63*/ if (v-3.getId().equals("luckyegg")) {
                /*SL:65*/v2 = (Entity)new EntityLuckyEgg(v-5, (EntityLivingBase)v-4, posX + 0.5, posY, v0 + 0.5);
            }
            else/*SL:67*/ if (v-3.getId().equals("tnt")) {
                /*SL:69*/v2 = (Entity)new EntityTNTPrimed(v-5, (double)posX, (double)(posY + 0.5f), (double)v0, (EntityLivingBase)v-4);
                /*SL:70*/v2.func_70012_b((double)posX, (double)(posY + 0.5f), (double)v0, 0.0f, 0.0f);
                /*SL:71*/((EntityTNTPrimed)v2).field_70516_a = 50;
                /*SL:73*/v-5.func_72956_a(v2, "game.tnt.primed", 1.0f, 1.0f);
            }
            else/*SL:75*/ if (v-3.getId().equals("launched_tnt")) {
                /*SL:77*/v2 = (Entity)new EntityTNTPrimed(v-5, (double)posX, (double)(posY + 0.5f), (double)v0, (EntityLivingBase)v-4);
                /*SL:78*/v2.func_70012_b((double)posX, (double)(posY + 0.5f), (double)v0, MathHelper.func_76142_g(this.random.nextFloat() * 360.0f), -90.0f + (this.random.nextInt(60) - 30));
                final float v3 = /*EL:79*/0.4f;
                /*SL:80*/v2.field_70159_w = -MathHelper.func_76126_a(v2.field_70177_z / 180.0f * 3.1415927f) * MathHelper.func_76134_b(v2.field_70125_A / 180.0f * 3.1415927f) * v3;
                /*SL:81*/v2.field_70179_y = MathHelper.func_76134_b(v2.field_70177_z / 180.0f * 3.1415927f) * MathHelper.func_76134_b(v2.field_70125_A / 180.0f * 3.1415927f) * v3;
                /*SL:82*/v2.field_70181_x = -MathHelper.func_76126_a(v2.field_70125_A / 180.0f * 3.1415927f) * v3;
                /*SL:83*/((EntityTNTPrimed)v2).field_70516_a = 50;
                /*SL:84*/this.setThrowableHeading(v2, v2.field_70159_w, v2.field_70181_x, v2.field_70179_y, 0.6f, 1.0f);
                /*SL:86*/v-5.func_72956_a(v2, "game.tnt.primed", 1.0f, 1.0f);
            }
            else/*SL:88*/ if (v-3.getId().equals("expbottle")) {
                /*SL:90*/v2 = (Entity)new EntityExpBottle(v-5, (double)posX, (double)(posY + 0.5f), (double)v0);
                /*SL:91*/v2.func_70012_b((double)posX, (double)(posY + 0.5f), (double)v0, 0.0f, 0.0f);
            }
            else/*SL:93*/ if (v-3.getId().equals("launched_expbottle")) {
                /*SL:95*/v2 = (Entity)new EntityExpBottle(v-5, (double)posX, (double)(posY + 0.5f), (double)v0);
                /*SL:96*/v2.func_70012_b((double)posX, (double)(posY + 0.5f), (double)v0, MathHelper.func_76142_g(this.random.nextFloat() * 360.0f), -90.0f + (this.random.nextInt(20) - 10));
                final float v3 = /*EL:97*/0.4f;
                /*SL:98*/v2.field_70159_w = -MathHelper.func_76126_a(v2.field_70177_z / 180.0f * 3.1415927f) * MathHelper.func_76134_b(v2.field_70125_A / 180.0f * 3.1415927f) * v3;
                /*SL:99*/v2.field_70179_y = MathHelper.func_76134_b(v2.field_70177_z / 180.0f * 3.1415927f) * MathHelper.func_76134_b(v2.field_70125_A / 180.0f * 3.1415927f) * v3;
                /*SL:100*/v2.field_70181_x = -MathHelper.func_76126_a((v2.field_70125_A - 20.0f) / 180.0f * 3.1415927f) * v3;
                /*SL:101*/this.setThrowableHeading(v2, v2.field_70159_w, v2.field_70181_x, v2.field_70179_y, 0.7f, 1.0f);
            }
            else {
                /*SL:105*/v.func_74778_a("id", v-3.getId());
                /*SL:106*/v2 = EntityList.func_75615_a(v, v-5);
                /*SL:107*/if (v2 != null) {
                    /*SL:109*/v2.func_70012_b(v-3.getPosX() + 0.5, (double)v-3.getPosY(), v-3.getPosZ() + 0.5, v2.field_70177_z, v2.field_70125_A);
                    /*SL:110*/if (v2 instanceof EntityLiving) {
                        /*SL:112*/((EntityLiving)v2).func_110161_a((IEntityLivingData)null);
                        /*SL:113*/if (v-3.getNBTTag() != null) {
                            ((EntityLiving)v2).func_70037_a(v);
                        }
                    }
                }
            }
        }
        /*SL:119*/if (v-3.getNBTTag() != null && v2 != null) {
            /*SL:121*/v-5.func_72838_d(v2);
            Entity v4 = /*EL:122*/v2;
            /*SL:124*/for (NBTTagCompound v5 = v; v5.func_150297_b("Riding", 10); v5 = v5.func_74775_l("Riding")) {
                final Entity v6 = /*EL:126*/EntityList.func_75615_a(v5.func_74775_l("Riding"), v-5);
                /*SL:128*/if (v6 != null) {
                    /*SL:130*/v6.func_70012_b(v-3.getPosX() + 0.5, (double)v-3.getPosY(), v-3.getPosZ() + 0.5, v6.field_70177_z, v6.field_70125_A);
                    /*SL:131*/this.spawnEntity(v-5, v6);
                    /*SL:132*/v4.func_70078_a(v6);
                }
                /*SL:135*/v4 = v6;
            }
        }
        else/*SL:138*/ if (v2 != null) {
            /*SL:140*/this.spawnEntity(v-5, v2);
        }
    }
    
    public boolean spawnEntity(final World v1, final Entity v2) {
        try {
            /*SL:148*/v1.func_72838_d(v2);
            /*SL:149*/return true;
        }
        catch (Exception a1) {
            /*SL:153*/a1.printStackTrace();
            /*SL:154*/return false;
        }
    }
    
    public void setThrowableHeading(final Entity a1, double a2, double a3, double a4, final float a5, final float a6) {
        final Random v1 = /*EL:160*/new Random();
        final float v2 = /*EL:161*/MathHelper.func_76133_a(a2 * a2 + a3 * a3 + a4 * a4);
        /*SL:162*/a2 /= v2;
        /*SL:163*/a3 /= v2;
        /*SL:164*/a4 /= v2;
        /*SL:165*/a2 += v1.nextGaussian() * 0.007499999832361937 * a6;
        /*SL:166*/a3 += v1.nextGaussian() * 0.007499999832361937 * a6;
        /*SL:167*/a4 += v1.nextGaussian() * 0.007499999832361937 * a6;
        /*SL:168*/a2 *= a5;
        /*SL:169*/a3 *= a5;
        /*SL:170*/a4 *= a5;
        /*SL:171*/a1.field_70159_w = a2;
        /*SL:172*/a1.field_70181_x = a3;
        /*SL:173*/a1.field_70179_y = a4;
        final float v3 = /*EL:174*/MathHelper.func_76133_a(a2 * a2 + a4 * a4);
        final float n = /*EL:175*/(float)(Math.atan2(a2, a4) * 180.0 / 3.141592653589793);
        a1.field_70177_z = n;
        a1.field_70126_B = n;
        final float n2 = /*EL:176*/(float)(Math.atan2(a3, v3) * 180.0 / 3.141592653589793);
        a1.field_70125_A = n2;
        a1.field_70127_C = n2;
    }
    
    protected NBTTagList newDoubleNBTList(final double... v-3) {
        final NBTTagList list = /*EL:181*/new NBTTagList();
        /*SL:185*/for (final double a1 : v-3) {
            /*SL:188*/list.func_74742_a((NBTBase)new NBTTagDouble(a1));
        }
        /*SL:191*/return list;
    }
}
