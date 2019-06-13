package mod.lucky.entity;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.world.WorldServer;
import java.util.Random;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.DamageSource;
import java.util.Collection;
import java.util.ArrayList;
import net.minecraft.nbt.NBTBase;
import java.util.Iterator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.BlockFalling;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import mod.lucky.util.LuckyFunction;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityFallingBlock;

public class EntityLuckyFallingBlock extends EntityFallingBlock
{
    private Block field_145811_e;
    public boolean field_145813_c;
    private boolean field_145808_f;
    private boolean field_145809_g;
    private final int field_145815_h;
    private final float field_145816_i;
    public NBTTagCompound field_145810_d;
    private LuckyFunction luckyFunction;
    
    public EntityLuckyFallingBlock(final World a1) {
        super(a1);
        this.field_145813_c = true;
        this.field_145815_h = 40;
        this.field_145816_i = 2.0f;
    }
    
    public EntityLuckyFallingBlock(final World a1, final double a2, final double a3, final double a4, final Block a5) {
        this(a1, a2, a3, a4, a5, 0);
    }
    
    public EntityLuckyFallingBlock(final World a1, final double a2, final double a3, final double a4, final Block a5, final int a6) {
        super(a1, a4, a4, a4, a5);
        this.field_145813_c = true;
        this.field_145815_h = 40;
        this.field_145816_i = 2.0f;
        this.field_145811_e = a5;
        this.field_145814_a = a6;
        this.field_70156_m = true;
        this.func_70105_a(0.98f, 0.98f);
        this.field_70129_M = this.field_70131_O / 2.0f;
        this.func_70107_b(a2, a3, a4);
        this.field_70159_w = 0.0;
        this.field_70181_x = 0.0;
        this.field_70179_y = 0.0;
        this.field_70169_q = a2;
        this.field_70167_r = a3;
        this.field_70166_s = a4;
        this.luckyFunction = new LuckyFunction();
    }
    
    public void setNBTTagCompound(final NBTTagCompound a1) {
        /*SL:75*/this.field_145810_d = a1;
    }
    
    protected boolean func_70041_e_() {
        /*SL:85*/return false;
    }
    
    protected void func_70088_a() {
    }
    
    public boolean func_70067_L() {
        /*SL:100*/return !this.field_70128_L;
    }
    
    public void func_70071_h_() {
        /*SL:109*/if (this.field_145811_e.func_149688_o() == Material.field_151579_a) {
            /*SL:111*/this.func_70106_y();
        }
        else {
            /*SL:115*/this.field_70169_q = this.field_70165_t;
            /*SL:116*/this.field_70167_r = this.field_70163_u;
            /*SL:117*/this.field_70166_s = this.field_70161_v;
            /*SL:118*/++this.field_145812_b;
            /*SL:119*/this.field_70181_x -= 0.03999999910593033;
            /*SL:120*/this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
            /*SL:121*/this.field_70159_w *= 0.9800000190734863;
            /*SL:122*/this.field_70181_x *= 0.9800000190734863;
            /*SL:123*/this.field_70179_y *= 0.9800000190734863;
            /*SL:125*/if (!this.field_70170_p.field_72995_K) {
                final int func_76128_c = /*EL:127*/MathHelper.func_76128_c(this.field_70165_t);
                final int func_76128_c2 = /*EL:128*/MathHelper.func_76128_c(this.field_70163_u);
                final int func_76128_c3 = /*EL:129*/MathHelper.func_76128_c(this.field_70161_v);
                /*SL:131*/if (this.field_70122_E) {
                    /*SL:133*/this.field_70159_w *= 0.699999988079071;
                    /*SL:134*/this.field_70179_y *= 0.699999988079071;
                    /*SL:135*/this.field_70181_x *= -0.5;
                    /*SL:137*/if (this.field_70170_p.func_147439_a(func_76128_c, func_76128_c2, func_76128_c3) != Blocks.field_150326_M) {
                        /*SL:139*/this.func_70106_y();
                        /*SL:141*/if (!this.field_145808_f && this.field_70170_p.func_147472_a(this.field_145811_e, func_76128_c, func_76128_c2, func_76128_c3, true, 1, (Entity)null, (ItemStack)null) && !BlockFalling.func_149831_e(this.field_70170_p, func_76128_c, func_76128_c2 - 1, func_76128_c3) && this.field_70170_p.func_147465_d(func_76128_c, func_76128_c2, func_76128_c3, this.field_145811_e, this.field_145814_a, 3)) {
                            /*SL:143*/this.onFinnishedFalling(this.field_70170_p, func_76128_c, func_76128_c2, func_76128_c3, this.field_145811_e);
                            /*SL:145*/if (this.field_145810_d != null && this.field_145811_e instanceof ITileEntityProvider) {
                                final TileEntity func_147438_o = /*EL:147*/this.field_70170_p.func_147438_o(func_76128_c, func_76128_c2, func_76128_c3);
                                /*SL:149*/if (func_147438_o != null) {
                                    final NBTTagCompound nbtTagCompound = /*EL:151*/new NBTTagCompound();
                                    /*SL:152*/func_147438_o.func_145841_b(nbtTagCompound);
                                    /*SL:153*/for (final String v : this.field_145810_d.func_150296_c()) {
                                        final NBTBase v2 = /*EL:158*/this.field_145810_d.func_74781_a(v);
                                        /*SL:160*/if (!v.equals("x") && !v.equals("y") && !v.equals("z")) {
                                            /*SL:162*/nbtTagCompound.func_74782_a(v, v2.func_74737_b());
                                        }
                                    }
                                    /*SL:166*/func_147438_o.func_145839_a(nbtTagCompound);
                                    /*SL:167*/func_147438_o.func_70296_d();
                                }
                            }
                        }
                        else/*SL:171*/ if (this.field_145813_c && !this.field_145808_f) {
                            /*SL:173*/this.func_70099_a(new ItemStack(this.field_145811_e, 1, this.field_145811_e.func_149692_a(this.field_145814_a)), 0.0f);
                        }
                    }
                }
                else/*SL:177*/ if ((this.field_145812_b > 100 && !this.field_70170_p.field_72995_K && (func_76128_c2 < 1 || func_76128_c2 > 256)) || this.field_145812_b > 600) {
                    /*SL:179*/if (this.field_145813_c) {
                        /*SL:181*/this.func_70099_a(new ItemStack(this.field_145811_e, 1, this.field_145811_e.func_149692_a(this.field_145814_a)), 0.0f);
                    }
                    /*SL:184*/this.func_70106_y();
                }
            }
        }
    }
    
    protected void func_70069_a(final float v-5) {
        /*SL:196*/if (this.field_145809_g) {
            final int func_76123_f = /*EL:198*/MathHelper.func_76123_f(v-5 - 1.0f);
            /*SL:200*/if (func_76123_f > 0) {
                final ArrayList list = /*EL:202*/new ArrayList(this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D));
                final boolean b = /*EL:203*/this.field_145811_e == Blocks.field_150467_bQ;
                final DamageSource damageSource = /*EL:204*/b ? DamageSource.field_82728_o : DamageSource.field_82729_p;
                /*SL:205*/for (final Entity a1 : list) {
                    /*SL:210*/a1.func_70097_a(damageSource, (float)Math.min(MathHelper.func_76141_d(func_76123_f * this.field_145816_i), this.field_145815_h));
                }
                /*SL:213*/if (b && this.field_70146_Z.nextFloat() < 0.05000000074505806 + func_76123_f * 0.05) {
                    int v = /*EL:215*/this.field_145814_a >> 2;
                    final int v2 = /*EL:216*/this.field_145814_a & 0x3;
                    /*SL:219*/if (++v > 2) {
                        /*SL:221*/this.field_145808_f = true;
                    }
                    else {
                        /*SL:225*/this.field_145814_a = (v2 | v << 2);
                    }
                }
            }
        }
    }
    
    protected void func_70014_b(final NBTTagCompound a1) {
        /*SL:238*/super.func_70014_b(a1);
    }
    
    protected void func_70037_a(final NBTTagCompound a1) {
        /*SL:247*/super.func_70037_a(a1);
    }
    
    public void func_145806_a(final boolean a1) {
        /*SL:253*/this.field_145809_g = a1;
    }
    
    public void func_85029_a(final CrashReportCategory a1) {
        /*SL:259*/super.func_85029_a(a1);
        /*SL:260*/a1.func_71507_a("Immitating block ID", (Object)Block.func_149682_b(this.field_145811_e));
        /*SL:261*/a1.func_71507_a("Immitating block data", (Object)this.field_145814_a);
    }
    
    @SideOnly(Side.CLIENT)
    public float func_70053_R() {
        /*SL:268*/return 0.0f;
    }
    
    @SideOnly(Side.CLIENT)
    public World func_145807_e() {
        /*SL:275*/return this.field_70170_p;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean func_90999_ad() {
        /*SL:285*/return false;
    }
    
    public Block func_145805_f() {
        /*SL:291*/return this.field_145811_e;
    }
    
    public void onFinnishedFalling(final World v1, final int v2, final int v3, final int v4, final Block v5) {
        /*SL:297*/if (v5 == Blocks.field_150339_S) {
            /*SL:299*/for (Random a2 = (Random)0; a2 < 30; ++a2) {
                /*SL:301*/a2 = new Random();
                /*SL:302*/this.luckyFunction.setOffset(1, 2);
                /*SL:303*/v1.func_72926_e(2000, v2 + this.luckyFunction.getXOffset(), v3, v4 + this.luckyFunction.getZOffset(), a2.nextInt(9));
                /*SL:304*/v1.func_72908_a((double)v2, (double)v3, (double)v4, "random.fizz", 3.0f, 1.0f);
            }
        }
        /*SL:308*/if (v5 == Blocks.field_150340_R) {
            /*SL:310*/for (int a3 = 0; a3 < 5; ++a3) {
                /*SL:312*/v1.func_72926_e(2002, v2, v3, v4, 3);
            }
        }
        /*SL:316*/if (v5 == Blocks.field_150484_ah) {
            /*SL:318*/((WorldServer)v1).func_72942_c((Entity)new EntityLightningBolt(v1, v2 + 0.5, (double)(v3 + 1), v4 + 0.5));
        }
        /*SL:321*/if (v5 == Blocks.field_150475_bE) {
            /*SL:323*/for (int a4 = 0; a4 < 30; ++a4) {
                /*SL:325*/this.luckyFunction.setOffset(1, 2);
                /*SL:326*/v1.func_72926_e(2005, v2 + this.luckyFunction.getXOffset(), v3, v4 + this.luckyFunction.getZOffset(), 0);
                /*SL:327*/v1.func_72908_a((double)v2, (double)v3, (double)v4, "dig.grass", 3.0f, 1.0f);
            }
        }
    }
}
