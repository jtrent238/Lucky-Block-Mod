package mod.lucky.entity;

import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataSerializers;
import com.google.common.base.Predicates;
import net.minecraft.util.EntitySelectors;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagString;
import mod.lucky.drop.DropContainer;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.nbt.NBTTagCompound;
import java.util.List;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.init.Items;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.util.math.Vec3d;
import mod.lucky.drop.func.DropProcessData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import mod.lucky.drop.func.DropProcessor;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.block.Block;
import net.minecraft.network.datasync.DataParameter;
import com.google.common.base.Predicate;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.Entity;

public class EntityLuckyProjectile extends Entity implements IProjectile
{
    private static final Predicate<Entity> ARROW_TARGETS;
    private static final DataParameter<Byte> CRITICAL;
    private int xTile;
    private int yTile;
    private int zTile;
    private Block inTile;
    private int inData;
    protected boolean inGround;
    protected int field_184552_b;
    public EntityArrow.PickupStatus canBePickedUp;
    public int arrowShake;
    public Entity shootingEntity;
    private int ticksInGround;
    private int ticksInAir;
    private double damage;
    private int knockbackStrength;
    private static final DataParameter<ItemStack> PROJECTILE_ITEM;
    private EntityItem item;
    private boolean hasTrail;
    private float trailFrequency;
    private boolean hasImpact;
    private DropProcessor dropProcessorTrail;
    private DropProcessor dropProcessorImpact;
    
    public EntityLuckyProjectile(final World a1) {
        super(a1);
        this.hasTrail = false;
        this.trailFrequency = 1.0f;
        this.hasImpact = false;
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
        this.canBePickedUp = EntityArrow.PickupStatus.DISALLOWED;
        this.damage = 2.0;
        this.func_70105_a(0.5f, 0.5f);
    }
    
    public EntityLuckyProjectile(final World a1, final double a2, final double a3, final double a4) {
        this(a1);
        this.func_70107_b(a2, a3, a4);
    }
    
    public EntityLuckyProjectile(final World a1, final EntityLivingBase a2) {
        this(a1, a2.field_70165_t, a2.field_70163_u + a2.func_70047_e() - 0.10000000149011612, a2.field_70161_v);
        this.shootingEntity = (Entity)a2;
        if (a2 instanceof EntityPlayer) {
            this.canBePickedUp = EntityArrow.PickupStatus.ALLOWED;
        }
    }
    
    private void luckyImpact(final Entity v2) {
        try {
            /*SL:119*/if (this.hasImpact) {
                /*SL:121*/if (v2 == null) {
                    this.dropProcessorImpact.processRandomDrop(new DropProcessData(this.func_130014_f_(), this.shootingEntity, this.func_174791_d()), 0);
                }
                else {
                    /*SL:122*/this.dropProcessorImpact.processRandomDrop(new DropProcessData(this.func_130014_f_(), this.shootingEntity, v2.func_174791_d()).setHitEntity(v2), 0);
                }
            }
            /*SL:125*/if (!this.func_130014_f_().field_72995_K) {
                this.func_70106_y();
            }
        }
        catch (Exception a1) {
            System.err.println(/*EL:129*/"The Lucky Bow encountered and error while trying to perform an impact function. Error report below:");
            /*SL:130*/a1.printStackTrace();
        }
        /*SL:132*/this.func_70106_y();
    }
    
    private void luckyUpdate() {
        try {
            /*SL:139*/if (this.item == null && this.func_130014_f_().field_72995_K) {
                this.item = new EntityItem(this.func_130014_f_(), this.field_70165_t, this.field_70163_u, this.field_70161_v, (ItemStack)this.field_70180_af.func_187225_a((DataParameter)EntityLuckyProjectile.PROJECTILE_ITEM));
            }
        }
        catch (Exception ex) {}
        /*SL:145*/if (this.item != null) {
            this.item.func_70071_h_();
        }
        /*SL:147*/if (!this.func_130014_f_().field_72995_K && this.hasTrail && this.ticksInAir >= 2) {
            try {
                /*SL:151*/if (this.trailFrequency < 1.0 && this.trailFrequency > 0.0f) {
                    /*SL:154*/for (int v0 = (int)(1.0 / this.trailFrequency), v = 0; v < v0; ++v) {
                        /*SL:156*/this.dropProcessorTrail.processRandomDrop(new DropProcessData(this.func_130014_f_(), this.shootingEntity, new Vec3d(this.field_70165_t + this.field_70159_w * v / v0, this.field_70163_u + this.field_70181_x * v / v0, this.field_70161_v + this.field_70179_y * v / v0)), 0, false);
                    }
                }
                else/*SL:159*/ if ((this.ticksInAir - 2) % (int)this.trailFrequency == 0) {
                    this.dropProcessorTrail.processRandomDrop(new DropProcessData(this.func_130014_f_(), this.shootingEntity, this.func_174791_d()), 0, false);
                }
            }
            catch (Exception v2) {
                System.err.println(/*EL:163*/"The Lucky Bow encountered and error while trying to perform a trail function. Error report below:");
                /*SL:164*/v2.printStackTrace();
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public boolean func_70112_a(final double a1) {
        double v1 = /*EL:178*/this.func_174813_aQ().func_72320_b() * 10.0;
        /*SL:180*/if (Double.isNaN(v1)) {
            /*SL:182*/v1 = 1.0;
        }
        /*SL:185*/v1 = v1 * 64.0 * func_184183_bd();
        /*SL:186*/return a1 < v1 * v1;
    }
    
    protected void func_70088_a() {
        /*SL:192*/this.field_70180_af.func_187214_a((DataParameter)EntityLuckyProjectile.PROJECTILE_ITEM, (Object)new ItemStack(Items.field_151055_y));
        /*SL:193*/this.field_70180_af.func_187214_a((DataParameter)EntityLuckyProjectile.CRITICAL, (Object)(byte)0);
        /*SL:194*/this.dropProcessorTrail = new DropProcessor();
        /*SL:195*/this.dropProcessorImpact = new DropProcessor();
    }
    
    public void func_184547_a(final Entity a1, final float a2, final float a3, final float a4, final float a5, final float a6) {
        final float v1 = /*EL:200*/-MathHelper.func_76126_a(a3 * 0.017453292f) * MathHelper.func_76134_b(a2 * 0.017453292f);
        final float v2 = /*EL:201*/-MathHelper.func_76126_a(a2 * 0.017453292f);
        final float v3 = /*EL:202*/MathHelper.func_76134_b(a3 * 0.017453292f) * MathHelper.func_76134_b(a2 * 0.017453292f);
        /*SL:203*/this.func_70186_c(v1, v2, v3, a5, a6);
        /*SL:204*/this.field_70159_w += a1.field_70159_w;
        /*SL:205*/this.field_70179_y += a1.field_70179_y;
        /*SL:207*/if (!a1.field_70122_E) {
            /*SL:209*/this.field_70181_x += a1.field_70181_x;
        }
    }
    
    public void func_70186_c(double a1, double a2, double a3, final float a4, final float a5) {
        final float v1 = /*EL:220*/MathHelper.func_76133_a(a1 * a1 + a2 * a2 + a3 * a3);
        /*SL:221*/a1 /= v1;
        /*SL:222*/a2 /= v1;
        /*SL:223*/a3 /= v1;
        /*SL:224*/a1 += this.field_70146_Z.nextGaussian() * 0.007499999832361937 * a5;
        /*SL:225*/a2 += this.field_70146_Z.nextGaussian() * 0.007499999832361937 * a5;
        /*SL:226*/a3 += this.field_70146_Z.nextGaussian() * 0.007499999832361937 * a5;
        /*SL:227*/a1 *= a4;
        /*SL:228*/a2 *= a4;
        /*SL:229*/a3 *= a4;
        /*SL:230*/this.field_70159_w = a1;
        /*SL:231*/this.field_70181_x = a2;
        /*SL:232*/this.field_70179_y = a3;
        final float v2 = /*EL:233*/MathHelper.func_76133_a(a1 * a1 + a3 * a3);
        final float n = /*EL:234*/(float)(MathHelper.func_181159_b(a1, a3) * 57.29577951308232);
        this.field_70177_z = n;
        this.field_70126_B = n;
        final float n2 = /*EL:235*/(float)(MathHelper.func_181159_b(a2, (double)v2) * 57.29577951308232);
        this.field_70125_A = n2;
        this.field_70127_C = n2;
        /*SL:236*/this.ticksInGround = 0;
    }
    
    @SideOnly(Side.CLIENT)
    public void func_180426_a(final double a1, final double a2, final double a3, final float a4, final float a5, final int a6, final boolean a7) {
        /*SL:243*/this.func_70107_b(a1, a2, a3);
        /*SL:244*/this.func_70101_b(a4, a5);
    }
    
    @SideOnly(Side.CLIENT)
    public void func_70016_h(final double a3, final double v1, final double v3) {
        /*SL:254*/this.field_70159_w = a3;
        /*SL:255*/this.field_70181_x = v1;
        /*SL:256*/this.field_70179_y = v3;
        /*SL:258*/if (this.field_70127_C == 0.0f && this.field_70126_B == 0.0f) {
            final float a4 = /*EL:260*/MathHelper.func_76133_a(a3 * a3 + v3 * v3);
            final float n = /*EL:261*/(float)(MathHelper.func_181159_b(a3, v3) * 57.29577951308232);
            this.field_70177_z = n;
            this.field_70126_B = n;
            final float n2 = /*EL:262*/(float)(MathHelper.func_181159_b(v1, (double)a4) * 57.29577951308232);
            this.field_70125_A = n2;
            this.field_70127_C = n2;
            /*SL:263*/this.field_70127_C = this.field_70125_A;
            /*SL:264*/this.field_70126_B = this.field_70177_z;
            /*SL:265*/this.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, this.field_70125_A);
            /*SL:266*/this.ticksInGround = 0;
        }
    }
    
    public void func_70071_h_() {
        /*SL:276*/super.func_70071_h_();
        /*SL:278*/if (this.field_70127_C == 0.0f && this.field_70126_B == 0.0f) {
            final float v1 = /*EL:280*/MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
            final float n = /*EL:281*/(float)(MathHelper.func_181159_b(this.field_70159_w, this.field_70179_y) * 57.29577951308232);
            this.field_70177_z = n;
            this.field_70126_B = n;
            final float n2 = /*EL:282*/(float)(MathHelper.func_181159_b(this.field_70181_x, (double)v1) * 57.29577951308232);
            this.field_70125_A = n2;
            this.field_70127_C = n2;
        }
        final BlockPos v2 = /*EL:285*/new BlockPos(this.xTile, this.yTile, this.zTile);
        final IBlockState v3 = /*EL:286*/this.field_70170_p.func_180495_p(v2);
        final Block v4 = /*EL:287*/v3.func_177230_c();
        /*SL:289*/if (v3.func_185904_a() != Material.field_151579_a) {
            final AxisAlignedBB v5 = /*EL:291*/v3.func_185890_d((IBlockAccess)this.field_70170_p, v2);
            /*SL:293*/if (v5 != Block.field_185506_k && v5.func_186670_a(v2).func_72318_a(new Vec3d(this.field_70165_t, this.field_70163_u, this.field_70161_v))) {
                /*SL:295*/this.inGround = true;
            }
        }
        /*SL:299*/if (this.arrowShake > 0) {
            /*SL:301*/--this.arrowShake;
        }
        /*SL:304*/if (this.inGround) {
            final int v6 = /*EL:306*/v4.func_176201_c(v3);
            /*SL:308*/if (v4 == this.inTile && v6 == this.inData) {
                /*SL:310*/++this.ticksInGround;
                /*SL:312*/if (this.ticksInGround >= 1200) {
                    /*SL:314*/this.func_70106_y();
                }
            }
            else {
                /*SL:319*/this.inGround = false;
                /*SL:320*/this.field_70159_w *= this.field_70146_Z.nextFloat() * 0.2f;
                /*SL:321*/this.field_70181_x *= this.field_70146_Z.nextFloat() * 0.2f;
                /*SL:322*/this.field_70179_y *= this.field_70146_Z.nextFloat() * 0.2f;
                /*SL:323*/this.ticksInGround = 0;
                /*SL:324*/this.ticksInAir = 0;
            }
            /*SL:327*/++this.field_184552_b;
            /*SL:329*/this.luckyImpact(null);
            /*SL:330*/this.luckyUpdate();
        }
        else {
            /*SL:334*/this.field_184552_b = 0;
            /*SL:335*/++this.ticksInAir;
            Vec3d v7 = /*EL:336*/new Vec3d(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            Vec3d v8 = /*EL:337*/new Vec3d(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
            RayTraceResult v9 = /*EL:338*/this.field_70170_p.func_147447_a(v7, v8, false, true, false);
            /*SL:339*/v7 = new Vec3d(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            /*SL:340*/v8 = new Vec3d(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
            /*SL:342*/if (v9 != null) {
                /*SL:344*/v8 = new Vec3d(v9.field_72307_f.field_72450_a, v9.field_72307_f.field_72448_b, v9.field_72307_f.field_72449_c);
            }
            final Entity v10 = /*EL:347*/this.func_184551_a(v7, v8);
            /*SL:349*/if (v10 != null) {
                /*SL:351*/v9 = new RayTraceResult(v10);
            }
            /*SL:354*/if (v9 != null && v9.field_72308_g != null && v9.field_72308_g instanceof EntityPlayer) {
                final EntityPlayer v11 = /*EL:356*/(EntityPlayer)v9.field_72308_g;
                /*SL:358*/if (this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).func_96122_a(v11)) {
                    /*SL:360*/v9 = null;
                }
            }
            /*SL:364*/if (v9 != null && (this.ticksInAir > 5 || (v9.field_72308_g != this.shootingEntity && this.shootingEntity != null))) {
                /*SL:366*/this.onHit(v9);
                /*SL:367*/this.luckyImpact(v9.field_72308_g);
            }
            /*SL:370*/if (this.getIsCritical()) {
                /*SL:372*/for (int v12 = 0; v12 < 4; ++v12) {
                    /*SL:374*/this.field_70170_p.func_175688_a(EnumParticleTypes.CRIT, this.field_70165_t + this.field_70159_w * v12 / 4.0, this.field_70163_u + this.field_70181_x * v12 / 4.0, this.field_70161_v + this.field_70179_y * v12 / 4.0, -this.field_70159_w, -this.field_70181_x + 0.2, -this.field_70179_y, new int[0]);
                }
            }
            /*SL:378*/this.luckyUpdate();
            /*SL:380*/this.field_70165_t += this.field_70159_w;
            /*SL:381*/this.field_70163_u += this.field_70181_x;
            /*SL:382*/this.field_70161_v += this.field_70179_y;
            final float v13 = /*EL:383*/MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
            /*SL:384*/this.field_70177_z = (float)(MathHelper.func_181159_b(this.field_70159_w, this.field_70179_y) * 57.29577951308232);
            /*SL:386*/this.field_70125_A = (float)(MathHelper.func_181159_b(this.field_70181_x, (double)v13) * 57.29577951308232);
            while (this.field_70125_A - this.field_70127_C < -180.0f) {
                this.field_70127_C -= 360.0f;
            }
            /*SL:391*/while (this.field_70125_A - this.field_70127_C >= 180.0f) {
                /*SL:393*/this.field_70127_C += 360.0f;
            }
            /*SL:396*/while (this.field_70177_z - this.field_70126_B < -180.0f) {
                /*SL:398*/this.field_70126_B -= 360.0f;
            }
            /*SL:401*/while (this.field_70177_z - this.field_70126_B >= 180.0f) {
                /*SL:403*/this.field_70126_B += 360.0f;
            }
            /*SL:406*/this.field_70125_A = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2f;
            /*SL:407*/this.field_70177_z = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2f;
            float v14 = /*EL:408*/0.99f;
            final float v15 = /*EL:409*/0.05f;
            /*SL:411*/if (this.func_70090_H()) {
                /*SL:413*/for (int v16 = 0; v16 < 4; ++v16) {
                    final float v17 = /*EL:415*/0.25f;
                    /*SL:416*/this.field_70170_p.func_175688_a(EnumParticleTypes.WATER_BUBBLE, this.field_70165_t - this.field_70159_w * v17, this.field_70163_u - this.field_70181_x * v17, this.field_70161_v - this.field_70179_y * v17, this.field_70159_w, this.field_70181_x, this.field_70179_y, new int[0]);
                }
                /*SL:419*/v14 = 0.6f;
            }
            /*SL:422*/if (this.func_70026_G()) {
                /*SL:424*/this.func_70066_B();
            }
            /*SL:427*/this.field_70159_w *= v14;
            /*SL:428*/this.field_70181_x *= v14;
            /*SL:429*/this.field_70179_y *= v14;
            /*SL:430*/this.field_70181_x -= v15;
            /*SL:431*/this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            /*SL:432*/this.func_145775_I();
        }
    }
    
    protected void onHit(final RayTraceResult v-4) {
        final Entity field_72308_g = /*EL:438*/v-4.field_72308_g;
        /*SL:440*/if (field_72308_g != null) {
            final float func_76133_a = /*EL:442*/MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
            int func_76143_f = /*EL:443*/MathHelper.func_76143_f(func_76133_a * this.damage);
            /*SL:445*/if (this.getIsCritical()) {
                /*SL:447*/func_76143_f += this.field_70146_Z.nextInt(func_76143_f / 2 + 2);
            }
            DamageSource v0 = /*EL:450*/null;
            /*SL:452*/if (this.shootingEntity == null) {
                /*SL:454*/v0 = DamageSource.func_188403_a((Entity)this, (EntityLivingBase)null);
            }
            else/*SL:458*/ if (this.shootingEntity instanceof EntityLivingBase) {
                v0 = DamageSource.func_188403_a((Entity)this, (EntityLivingBase)this.shootingEntity);
            }
            /*SL:461*/if (this.func_70027_ad() && !(field_72308_g instanceof EntityEnderman)) {
                /*SL:463*/field_72308_g.func_70015_d(5);
            }
            /*SL:466*/if (field_72308_g.func_70097_a(v0, (float)func_76143_f)) {
                /*SL:468*/if (field_72308_g instanceof EntityLivingBase) {
                    final EntityLivingBase v = /*EL:470*/(EntityLivingBase)field_72308_g;
                    /*SL:472*/if (!this.field_70170_p.field_72995_K) {
                        /*SL:474*/v.func_85034_r(v.func_85035_bI() + 1);
                    }
                    /*SL:477*/if (this.knockbackStrength > 0) {
                        final float a1 = /*EL:479*/MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
                        /*SL:481*/if (a1 > 0.0f) {
                            /*SL:483*/v.func_70024_g(this.field_70159_w * this.knockbackStrength * 0.6000000238418579 / a1, 0.1, this.field_70179_y * this.knockbackStrength * 0.6000000238418579 / a1);
                        }
                    }
                    /*SL:487*/if (this.shootingEntity instanceof EntityLivingBase) {
                        /*SL:489*/EnchantmentHelper.func_151384_a(v, this.shootingEntity);
                        /*SL:490*/EnchantmentHelper.func_151385_b((EntityLivingBase)this.shootingEntity, (Entity)v);
                    }
                    /*SL:493*/this.arrowHit(v);
                    /*SL:495*/if (this.shootingEntity != null && v != this.shootingEntity && v instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP) {
                        /*SL:497*/((EntityPlayerMP)this.shootingEntity).field_71135_a.func_147359_a((Packet)new SPacketChangeGameState(6, 0.0f));
                    }
                }
                /*SL:501*/this.func_184185_a(SoundEvents.field_187731_t, 1.0f, 1.2f / (this.field_70146_Z.nextFloat() * 0.2f + 0.9f));
                /*SL:503*/if (!(field_72308_g instanceof EntityEnderman)) {
                    /*SL:505*/this.func_70106_y();
                }
            }
            else {
                /*SL:510*/this.field_70159_w *= -0.10000000149011612;
                /*SL:511*/this.field_70181_x *= -0.10000000149011612;
                /*SL:512*/this.field_70179_y *= -0.10000000149011612;
                /*SL:513*/this.field_70177_z += 180.0f;
                /*SL:514*/this.field_70126_B += 180.0f;
                /*SL:515*/this.ticksInAir = 0;
                /*SL:517*/if (!this.field_70170_p.field_72995_K && this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y < 0.0010000000474974513) {
                    /*SL:519*/if (this.canBePickedUp == EntityArrow.PickupStatus.ALLOWED) {
                        /*SL:521*/this.func_70099_a(this.getArrowStack(), 0.1f);
                    }
                    /*SL:524*/this.func_70106_y();
                }
            }
        }
        else {
            final BlockPos func_178782_a = /*EL:530*/v-4.func_178782_a();
            /*SL:531*/this.xTile = func_178782_a.func_177958_n();
            /*SL:532*/this.yTile = func_178782_a.func_177956_o();
            /*SL:533*/this.zTile = func_178782_a.func_177952_p();
            final IBlockState func_180495_p = /*EL:534*/this.field_70170_p.func_180495_p(func_178782_a);
            /*SL:535*/this.inTile = func_180495_p.func_177230_c();
            /*SL:536*/this.inData = this.inTile.func_176201_c(func_180495_p);
            /*SL:537*/this.field_70159_w = (float)(v-4.field_72307_f.field_72450_a - this.field_70165_t);
            /*SL:538*/this.field_70181_x = (float)(v-4.field_72307_f.field_72448_b - this.field_70163_u);
            /*SL:539*/this.field_70179_y = (float)(v-4.field_72307_f.field_72449_c - this.field_70161_v);
            final float v2 = /*EL:540*/MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
            /*SL:541*/this.field_70165_t -= this.field_70159_w / v2 * 0.05000000074505806;
            /*SL:542*/this.field_70163_u -= this.field_70181_x / v2 * 0.05000000074505806;
            /*SL:543*/this.field_70161_v -= this.field_70179_y / v2 * 0.05000000074505806;
            /*SL:544*/this.func_184185_a(SoundEvents.field_187731_t, 1.0f, 1.2f / (this.field_70146_Z.nextFloat() * 0.2f + 0.9f));
            /*SL:545*/this.inGround = true;
            /*SL:546*/this.arrowShake = 7;
            /*SL:547*/this.setIsCritical(false);
            /*SL:549*/if (func_180495_p.func_185904_a() != Material.field_151579_a) {
                /*SL:551*/this.inTile.func_180634_a(this.field_70170_p, func_178782_a, func_180495_p, (Entity)this);
            }
        }
    }
    
    protected void arrowHit(final EntityLivingBase a1) {
        /*SL:558*/this.func_70106_y();
    }
    
    protected Entity func_184551_a(final Vec3d v-8, final Vec3d v-7) {
        Entity entity = /*EL:563*/null;
        final List<Entity> func_175674_a = /*EL:564*/(List<Entity>)this.field_70170_p.func_175674_a((Entity)this, this.func_174813_aQ().func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_186662_g(1.0), (Predicate)EntityLuckyProjectile.ARROW_TARGETS);
        double n = /*EL:565*/0.0;
        /*SL:567*/for (int i = 0; i < func_175674_a.size(); ++i) {
            final Entity entity2 = /*EL:569*/func_175674_a.get(i);
            /*SL:571*/if (entity2 != this.shootingEntity || this.ticksInAir >= 5) {
                AxisAlignedBB a2 = /*EL:573*/entity2.func_174813_aQ().func_186662_g(0.30000001192092896);
                final RayTraceResult v1 = /*EL:574*/a2.func_72327_a(v-8, v-7);
                /*SL:576*/if (v1 != null) {
                    /*SL:578*/a2 = v-8.func_72436_e(v1.field_72307_f);
                    /*SL:580*/if (a2 < n || n == 0.0) {
                        /*SL:582*/entity = entity2;
                        /*SL:583*/n = a2;
                    }
                }
            }
        }
        /*SL:589*/return entity;
    }
    
    public void func_70014_b(final NBTTagCompound a1) {
        /*SL:598*/a1.func_74768_a("xTile", this.xTile);
        /*SL:599*/a1.func_74768_a("yTile", this.yTile);
        /*SL:600*/a1.func_74768_a("zTile", this.zTile);
        /*SL:601*/a1.func_74777_a("life", (short)this.ticksInGround);
        final ResourceLocation v1 = (ResourceLocation)Block.field_149771_c.func_177774_c(/*EL:602*/(Object)this.inTile);
        /*SL:603*/a1.func_74778_a("inTile", (v1 == null) ? "" : v1.toString());
        /*SL:604*/a1.func_74774_a("inData", (byte)this.inData);
        /*SL:605*/a1.func_74774_a("shake", (byte)this.arrowShake);
        /*SL:606*/a1.func_74774_a("inGround", (byte)(byte)(this.inGround ? 1 : 0));
        /*SL:607*/a1.func_74774_a("pickup", (byte)this.canBePickedUp.ordinal());
        /*SL:608*/a1.func_74780_a("damage", this.damage);
        /*SL:610*/this.customWriteEntityToNBT(a1);
    }
    
    private void customWriteEntityToNBT(final NBTTagCompound v0) {
        /*SL:615*/if (this.item != null) {
            v0.func_74782_a("item", (NBTBase)this.item.func_92059_d().func_77955_b(new NBTTagCompound()));
        }
        /*SL:617*/if (this.hasTrail) {
            final NBTTagCompound v = /*EL:619*/new NBTTagCompound();
            /*SL:620*/v.func_74776_a("frequency", this.trailFrequency);
            final NBTTagList v2 = /*EL:622*/new NBTTagList();
            /*SL:623*/for (int a1 = 0; a1 < this.dropProcessorTrail.getDrops().size(); ++a1) {
                /*SL:625*/v2.func_74742_a((NBTBase)new NBTTagString(this.dropProcessorTrail.getDrops().get(a1).toString()));
            }
            /*SL:627*/v.func_74782_a("drops", (NBTBase)v2);
            /*SL:628*/v0.func_74782_a("trail", (NBTBase)v);
        }
        /*SL:632*/if (this.hasImpact) {
            final NBTTagList v3 = /*EL:634*/new NBTTagList();
            /*SL:635*/for (int v4 = 0; v4 < this.dropProcessorImpact.getDrops().size(); ++v4) {
                /*SL:637*/v3.func_74742_a((NBTBase)new NBTTagString(this.dropProcessorImpact.getDrops().get(v4).toString()));
            }
            /*SL:639*/v0.func_74782_a("impact", (NBTBase)v3);
        }
    }
    
    public void func_70037_a(final NBTTagCompound a1) {
        /*SL:649*/this.xTile = a1.func_74762_e("xTile");
        /*SL:650*/this.yTile = a1.func_74762_e("yTile");
        /*SL:651*/this.zTile = a1.func_74762_e("zTile");
        /*SL:652*/this.ticksInGround = a1.func_74765_d("life");
        /*SL:654*/if (a1.func_150297_b("inTile", 8)) {
            /*SL:656*/this.inTile = Block.func_149684_b(a1.func_74779_i("inTile"));
        }
        else {
            /*SL:660*/this.inTile = Block.func_149729_e(a1.func_74771_c("inTile") & 0xFF);
        }
        /*SL:663*/this.inData = (a1.func_74771_c("inData") & 0xFF);
        /*SL:664*/this.arrowShake = (a1.func_74771_c("shake") & 0xFF);
        /*SL:665*/this.inGround = (a1.func_74771_c("inGround") == 1);
        /*SL:667*/if (a1.func_150297_b("damage", 99)) {
            /*SL:669*/this.damage = a1.func_74769_h("damage");
        }
        /*SL:672*/if (a1.func_150297_b("pickup", 99)) {
            /*SL:674*/this.canBePickedUp = EntityArrow.PickupStatus.func_188795_a((int)a1.func_74771_c("pickup"));
        }
        else/*SL:676*/ if (a1.func_150297_b("player", 99)) {
            /*SL:678*/this.canBePickedUp = (a1.func_74767_n("player") ? EntityArrow.PickupStatus.ALLOWED : EntityArrow.PickupStatus.DISALLOWED);
        }
        /*SL:681*/this.customReadEntityFromNBT(a1);
    }
    
    private void customReadEntityFromNBT(final NBTTagCompound v-2) {
        /*SL:686*/if (v-2.func_74764_b("item")) {
            /*SL:688*/v-2.func_74775_l("item").func_74782_a("Count", (NBTBase)new NBTTagInt(1));
            /*SL:689*/this.item = new EntityItem(this.func_130014_f_(), this.field_70165_t, this.field_70163_u, this.field_70161_v, new ItemStack(v-2.func_74775_l("item")));
        }
        else {
            /*SL:691*/this.item = new EntityItem(this.func_130014_f_(), this.field_70165_t, this.field_70163_u, this.field_70161_v, new ItemStack(Items.field_151055_y));
        }
        /*SL:693*/this.field_70180_af.func_187227_b((DataParameter)EntityLuckyProjectile.PROJECTILE_ITEM, (Object)this.item.func_92059_d());
        /*SL:695*/if (v-2.func_74764_b("trail")) {
            final NBTTagCompound func_74775_l = /*EL:697*/v-2.func_74775_l("trail");
            /*SL:698*/if (func_74775_l.func_74764_b("frequency")) {
                this.trailFrequency = func_74775_l.func_74760_g("frequency");
            }
            /*SL:699*/if (func_74775_l.func_74764_b("drops")) {
                final NBTTagList v0 = /*EL:701*/func_74775_l.func_150295_c("drops", (int)new NBTTagString().func_74732_a());
                /*SL:702*/for (int v = 0; v < v0.func_74745_c(); ++v) {
                    final DropContainer a1 = /*EL:704*/new DropContainer();
                    /*SL:705*/a1.readFromString(v0.func_150307_f(v));
                    /*SL:706*/this.dropProcessorTrail.registerDrop(a1);
                }
            }
            /*SL:709*/this.hasTrail = true;
        }
        /*SL:712*/if (v-2.func_74764_b("impact")) {
            final NBTTagList func_150295_c = /*EL:714*/v-2.func_150295_c("impact", (int)new NBTTagString().func_74732_a());
            /*SL:715*/for (int v2 = 0; v2 < func_150295_c.func_74745_c(); ++v2) {
                final DropContainer v3 = /*EL:717*/new DropContainer();
                /*SL:718*/v3.readFromString(func_150295_c.func_150307_f(v2));
                /*SL:719*/this.dropProcessorImpact.registerDrop(v3);
            }
            /*SL:721*/this.hasImpact = true;
        }
    }
    
    public void func_70100_b_(final EntityPlayer v2) {
        /*SL:731*/if (!this.field_70170_p.field_72995_K && this.inGround && this.arrowShake <= 0) {
            boolean a1 = /*EL:733*/this.canBePickedUp == EntityArrow.PickupStatus.ALLOWED || (this.canBePickedUp == EntityArrow.PickupStatus.CREATIVE_ONLY && v2.field_71075_bZ.field_75098_d);
            /*SL:735*/if (this.canBePickedUp == EntityArrow.PickupStatus.ALLOWED && !v2.field_71071_by.func_70441_a(this.getArrowStack())) {
                /*SL:737*/a1 = false;
            }
            /*SL:740*/if (a1) {
                /*SL:742*/this.func_184185_a(SoundEvents.field_187638_cR, 0.2f, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.7f + 1.0f) * 2.0f);
                /*SL:743*/v2.func_71001_a((Entity)this, 1);
                /*SL:744*/this.func_70106_y();
            }
        }
    }
    
    protected ItemStack getArrowStack() {
        /*SL:751*/return new ItemStack(Items.field_151032_g);
    }
    
    protected boolean func_70041_e_() {
        /*SL:761*/return false;
    }
    
    @SideOnly(Side.CLIENT)
    public int func_70070_b(final float a1) {
        /*SL:768*/return 15728880;
    }
    
    public void setDamage(final double a1) {
        /*SL:773*/this.damage = a1;
    }
    
    public double getDamage() {
        /*SL:778*/return this.damage;
    }
    
    public void setKnockbackStrength(final int a1) {
        /*SL:786*/this.knockbackStrength = a1;
    }
    
    public boolean func_70075_an() {
        /*SL:795*/return false;
    }
    
    public float func_70047_e() {
        /*SL:801*/return 0.0f;
    }
    
    public void setIsCritical(final boolean a1) {
        final byte v1 = /*EL:810*/(byte)this.field_70180_af.func_187225_a((DataParameter)EntityLuckyProjectile.CRITICAL);
        /*SL:812*/if (a1) {
            /*SL:814*/this.field_70180_af.func_187227_b((DataParameter)EntityLuckyProjectile.CRITICAL, (Object)(byte)(v1 | 0x1));
        }
        else {
            /*SL:818*/this.field_70180_af.func_187227_b((DataParameter)EntityLuckyProjectile.CRITICAL, (Object)(byte)(v1 & 0xFFFFFFFE));
        }
    }
    
    public boolean getIsCritical() {
        final byte v1 = /*EL:828*/(byte)this.field_70180_af.func_187225_a((DataParameter)EntityLuckyProjectile.CRITICAL);
        /*SL:829*/return (v1 & 0x1) != 0x0;
    }
    
    public EntityItem getItem() {
        /*SL:834*/return this.item;
    }
    
    static {
        ARROW_TARGETS = Predicates.<Entity>and(EntitySelectors.field_180132_d, EntitySelectors.field_94557_a, new Predicate<Entity>() {
            @Override
            public boolean apply(final Entity a1) {
                /*SL:54*/return a1.func_70067_L();
            }
        });
        CRITICAL = EntityDataManager.func_187226_a((Class)EntityLuckyProjectile.class, DataSerializers.field_187191_a);
        PROJECTILE_ITEM = EntityDataManager.func_187226_a((Class)EntityLuckyProjectile.class, DataSerializers.field_187196_f);
    }
    
    public enum PickupStatus
    {
        DISALLOWED, 
        ALLOWED, 
        CREATIVE_ONLY;
        
        public static PickupStatus func_188795_a(int a1) {
            /*SL:843*/if (a1 < 0 || a1 > values().length) {
                /*SL:845*/a1 = 0;
            }
            /*SL:848*/return values()[a1];
        }
    }
}
