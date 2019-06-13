package mod.lucky.entity;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ResourceLocation;
import java.util.ArrayList;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.BlockAnvil;
import net.minecraft.util.DamageSource;
import com.google.common.collect.Lists;
import net.minecraft.util.MathHelper;
import net.minecraft.nbt.NBTBase;
import java.util.Iterator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.BlockFalling;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.init.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityFallingBlock;

public class EntityLuckyFallingBlock extends EntityFallingBlock
{
    private IBlockState field_175132_d;
    public int field_145812_b;
    public boolean field_145813_c;
    private boolean field_145808_f;
    private boolean hurtEntities;
    private int fallHurtMax;
    private float fallHurtAmount;
    public NBTTagCompound field_145810_d;
    private static final String __OBFID = "CL_00001668";
    
    public EntityLuckyFallingBlock(final World a1) {
        super(a1);
        this.field_145813_c = true;
        this.fallHurtMax = 40;
        this.fallHurtAmount = 2.0f;
    }
    
    public EntityLuckyFallingBlock(final World a1, final double a2, final double a3, final double a4, final IBlockState a5) {
        super(a1);
        this.field_145813_c = true;
        this.fallHurtMax = 40;
        this.fallHurtAmount = 2.0f;
        this.field_175132_d = a5;
        this.field_70156_m = true;
        this.func_70105_a(0.98f, 0.98f);
        this.func_70107_b(a2, a3, a4);
        this.field_70159_w = 0.0;
        this.field_70181_x = 0.0;
        this.field_70179_y = 0.0;
        this.field_70169_q = a2;
        this.field_70167_r = a3;
        this.field_70166_s = a4;
    }
    
    protected boolean func_70041_e_() {
        /*SL:66*/return false;
    }
    
    protected void func_70088_a() {
    }
    
    public boolean func_70067_L() {
        /*SL:77*/return !this.field_70128_L;
    }
    
    public void func_70071_h_() {
        final Block func_177230_c = /*EL:83*/this.field_175132_d.func_177230_c();
        /*SL:85*/if (func_177230_c.func_149688_o() == Material.field_151579_a) {
            /*SL:87*/this.func_70106_y();
        }
        else {
            /*SL:91*/this.field_70169_q = this.field_70165_t;
            /*SL:92*/this.field_70167_r = this.field_70163_u;
            /*SL:93*/this.field_70166_s = this.field_70161_v;
            /*SL:96*/++this.field_145812_b;
            /*SL:97*/this.field_70181_x -= 0.03999999910593033;
            /*SL:98*/this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
            /*SL:99*/this.field_70159_w *= 0.9800000190734863;
            /*SL:100*/this.field_70181_x *= 0.9800000190734863;
            /*SL:101*/this.field_70179_y *= 0.9800000190734863;
            /*SL:103*/if (!this.field_70170_p.field_72995_K) {
                final BlockPos a2 = /*EL:105*/new BlockPos((Entity)this);
                /*SL:107*/if (this.field_70122_E) {
                    /*SL:109*/this.field_70159_w *= 0.699999988079071;
                    /*SL:110*/this.field_70179_y *= 0.699999988079071;
                    /*SL:111*/this.field_70181_x *= -0.5;
                    /*SL:113*/if (this.field_70170_p.func_180495_p(a2).func_177230_c() != Blocks.field_180384_M) {
                        /*SL:115*/this.func_70106_y();
                        /*SL:117*/if (!this.field_145808_f && this.field_70170_p.func_175716_a(func_177230_c, a2, true, EnumFacing.UP, (Entity)null, (ItemStack)null) && !BlockFalling.func_180685_d(this.field_70170_p, a2.func_177977_b()) && this.field_70170_p.func_180501_a(a2, this.field_175132_d, 3)) {
                            /*SL:121*/this.onFinnishedFalling(this.field_70170_p, a2, func_177230_c);
                            /*SL:123*/if (this.field_145810_d != null && func_177230_c instanceof ITileEntityProvider) {
                                final TileEntity func_175625_s = /*EL:125*/this.field_70170_p.func_175625_s(a2);
                                /*SL:127*/if (func_175625_s != null) {
                                    final NBTTagCompound nbtTagCompound = /*EL:129*/new NBTTagCompound();
                                    /*SL:130*/func_175625_s.func_145841_b(nbtTagCompound);
                                    /*SL:131*/for (final String v : this.field_145810_d.func_150296_c()) {
                                        final NBTBase v2 = /*EL:136*/this.field_145810_d.func_74781_a(v);
                                        /*SL:138*/if (!v.equals("x") && !v.equals("y") && !v.equals("z")) {
                                            /*SL:140*/nbtTagCompound.func_74782_a(v, v2.func_74737_b());
                                        }
                                    }
                                    /*SL:144*/func_175625_s.func_145839_a(nbtTagCompound);
                                    /*SL:145*/func_175625_s.func_70296_d();
                                }
                            }
                        }
                        else/*SL:149*/ if (this.field_145813_c && !this.field_145808_f && this.field_70170_p.func_82736_K().func_82766_b("doTileDrops")) {
                            /*SL:151*/this.func_70099_a(new ItemStack(func_177230_c, 1, func_177230_c.func_180651_a(this.field_175132_d)), 0.0f);
                        }
                    }
                }
                else/*SL:155*/ if ((this.field_145812_b > 100 && !this.field_70170_p.field_72995_K && (a2.func_177956_o() < 1 || a2.func_177956_o() > 256)) || this.field_145812_b > 600) {
                    /*SL:157*/if (this.field_145813_c && this.field_70170_p.func_82736_K().func_82766_b("doTileDrops")) {
                        /*SL:159*/this.func_70099_a(new ItemStack(func_177230_c, 1, func_177230_c.func_180651_a(this.field_175132_d)), 0.0f);
                    }
                    /*SL:162*/this.func_70106_y();
                }
            }
        }
    }
    
    public void func_180430_e(final float v-3, final float v-2) {
        final Block func_177230_c = /*EL:171*/this.field_175132_d.func_177230_c();
        /*SL:173*/if (this.hurtEntities) {
            final int v0 = /*EL:175*/MathHelper.func_76123_f(v-3 - 1.0f);
            /*SL:177*/if (v0 > 0) {
                final ArrayList v = /*EL:179*/Lists.<Object>newArrayList((Iterable<?>)this.field_70170_p.func_72839_b((Entity)this, this.func_174813_aQ()));
                final boolean v2 = /*EL:180*/func_177230_c == Blocks.field_150467_bQ;
                final DamageSource v3 = /*EL:181*/v2 ? DamageSource.field_82728_o : DamageSource.field_82729_p;
                /*SL:182*/for (final Entity a1 : v) {
                    /*SL:187*/a1.func_70097_a(v3, (float)Math.min(MathHelper.func_76141_d(v0 * this.fallHurtAmount), this.fallHurtMax));
                }
                /*SL:190*/if (v2 && this.field_70146_Z.nextFloat() < 0.05000000074505806 + v0 * 0.05) {
                    int a2 = /*EL:192*/(int)this.field_175132_d.func_177229_b((IProperty)BlockAnvil.field_176505_b);
                    /*SL:195*/if (++a2 > 2) {
                        /*SL:197*/this.field_145808_f = true;
                    }
                    else {
                        /*SL:201*/this.field_175132_d = this.field_175132_d.func_177226_a((IProperty)BlockAnvil.field_176505_b, (Comparable)a2);
                    }
                }
            }
        }
    }
    
    protected void func_70014_b(final NBTTagCompound a1) {
        final Block v1 = /*EL:211*/(this.field_175132_d != null) ? this.field_175132_d.func_177230_c() : Blocks.field_150350_a;
        final ResourceLocation v2 = (ResourceLocation)Block.field_149771_c.func_177774_c(/*EL:212*/(Object)v1);
        /*SL:213*/a1.func_74778_a("Block", (v2 == null) ? "" : v2.toString());
        /*SL:214*/a1.func_74774_a("Data", (byte)v1.func_176201_c(this.field_175132_d));
        /*SL:215*/a1.func_74774_a("Time", (byte)this.field_145812_b);
        /*SL:216*/a1.func_74757_a("DropItem", this.field_145813_c);
        /*SL:217*/a1.func_74757_a("HurtEntities", this.hurtEntities);
        /*SL:218*/a1.func_74776_a("FallHurtAmount", this.fallHurtAmount);
        /*SL:219*/a1.func_74768_a("FallHurtMax", this.fallHurtMax);
        /*SL:221*/if (this.field_145810_d != null) {
            /*SL:223*/a1.func_74782_a("TileEntityData", (NBTBase)this.field_145810_d);
        }
    }
    
    protected void func_70037_a(final NBTTagCompound a1) {
        final int v1 = /*EL:230*/a1.func_74771_c("Data") & 0xFF;
        /*SL:232*/if (a1.func_150297_b("Block", 8)) {
            /*SL:234*/this.field_175132_d = Block.func_149684_b(a1.func_74779_i("Block")).func_176203_a(v1);
        }
        else/*SL:236*/ if (a1.func_150297_b("TileID", 99)) {
            /*SL:238*/this.field_175132_d = Block.func_149729_e(a1.func_74762_e("TileID")).func_176203_a(v1);
        }
        else {
            /*SL:242*/this.field_175132_d = Block.func_149729_e(a1.func_74771_c("Tile") & 0xFF).func_176203_a(v1);
        }
        /*SL:245*/this.field_145812_b = (a1.func_74771_c("Time") & 0xFF);
        final Block v2 = /*EL:246*/this.field_175132_d.func_177230_c();
        /*SL:248*/if (a1.func_150297_b("HurtEntities", 99)) {
            /*SL:250*/this.hurtEntities = a1.func_74767_n("HurtEntities");
            /*SL:251*/this.fallHurtAmount = a1.func_74760_g("FallHurtAmount");
            /*SL:252*/this.fallHurtMax = a1.func_74762_e("FallHurtMax");
        }
        else/*SL:254*/ if (v2 == Blocks.field_150467_bQ) {
            /*SL:256*/this.hurtEntities = true;
        }
        /*SL:259*/if (a1.func_150297_b("DropItem", 99)) {
            /*SL:261*/this.field_145813_c = a1.func_74767_n("DropItem");
        }
        /*SL:264*/if (a1.func_150297_b("TileEntityData", 10)) {
            /*SL:266*/this.field_145810_d = a1.func_74775_l("TileEntityData");
        }
        /*SL:269*/if (v2 == null || v2.func_149688_o() == Material.field_151579_a) {
            /*SL:271*/this.field_175132_d = Blocks.field_150354_m.func_176223_P();
        }
    }
    
    public void func_145806_a(final boolean a1) {
        /*SL:278*/this.hurtEntities = a1;
    }
    
    public void func_85029_a(final CrashReportCategory v2) {
        /*SL:284*/super.func_85029_a(v2);
        /*SL:286*/if (this.field_175132_d != null) {
            final Block a1 = /*EL:288*/this.field_175132_d.func_177230_c();
            /*SL:289*/v2.func_71507_a("Immitating block ID", (Object)Block.func_149682_b(a1));
            /*SL:290*/v2.func_71507_a("Immitating block data", (Object)a1.func_176201_c(this.field_175132_d));
        }
    }
    
    @SideOnly(Side.CLIENT)
    public World func_145807_e() {
        /*SL:298*/return this.field_70170_p;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean func_90999_ad() {
        /*SL:305*/return false;
    }
    
    public IBlockState func_175131_l() {
        /*SL:311*/return this.field_175132_d;
    }
    
    public void onFinnishedFalling(final World a1, final BlockPos a2, final Block a3) {
    }
}
