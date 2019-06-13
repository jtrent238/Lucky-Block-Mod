package mod.lucky.entity;

import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagString;
import mod.lucky.drop.DropContainer;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.nbt.NBTTagCompound;
import java.util.List;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import mod.lucky.drop.func.DropProcessData;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import mod.lucky.drop.func.DropProcessor;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.block.Block;
import net.minecraft.entity.projectile.EntityArrow;

public class EntityLuckyProjectile extends EntityArrow
{
    private int xTile;
    private int yTile;
    private int zTile;
    private Block inTile;
    private int inData;
    private boolean inGround;
    private int ticksInGround;
    private int ticksInAir;
    private EntityItem item;
    private float trailFrequency;
    private boolean hasTrail;
    private boolean hasImpact;
    private DropProcessor dropProcessorTrail;
    private DropProcessor dropProcessorImpact;
    
    public EntityLuckyProjectile(final World a1) {
        super(a1);
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
        this.trailFrequency = 1.0f;
        this.hasTrail = false;
        this.hasImpact = false;
        this.field_70155_l = 10.0;
        this.func_70105_a(0.5f, 0.5f);
    }
    
    public EntityLuckyProjectile(final World a1, final double a2, final double a3, final double a4) {
        super(a1, a2, a3, a4);
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
        this.trailFrequency = 1.0f;
        this.hasTrail = false;
        this.hasImpact = false;
        this.field_70155_l = 10.0;
        this.func_70105_a(0.5f, 0.5f);
        this.func_70107_b(a2, a3, a4);
    }
    
    public EntityLuckyProjectile(final World a1, final EntityLivingBase a2, final EntityLivingBase a3, final float a4, final float a5) {
        super(a1, a2, a3, a4, a5);
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
        this.trailFrequency = 1.0f;
        this.hasTrail = false;
        this.hasImpact = false;
    }
    
    public EntityLuckyProjectile(final World a1, final EntityLivingBase a2, final float a3) {
        super(a1, a2, a3);
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
        this.trailFrequency = 1.0f;
        this.hasTrail = false;
        this.hasImpact = false;
        this.field_70155_l = 10.0;
        this.field_70250_c = (Entity)a2;
    }
    
    protected void func_70088_a() {
        /*SL:86*/super.func_70088_a();
        /*SL:87*/this.field_70180_af.func_75682_a(20, (Object)new ItemStack(Items.field_151055_y));
        /*SL:88*/this.dropProcessorTrail = new DropProcessor();
        /*SL:89*/this.dropProcessorImpact = new DropProcessor();
    }
    
    private void luckyImpact(final MovingObjectPosition v2) {
        try {
            /*SL:96*/if (this.hasImpact) {
                /*SL:98*/if (v2.field_72308_g == null) {
                    this.dropProcessorImpact.processRandomDrop(new DropProcessData(this.func_130014_f_(), this.field_70250_c, v2.field_72307_f), 0);
                }
                else {
                    /*SL:99*/this.dropProcessorImpact.processRandomDrop(new DropProcessData(this.func_130014_f_(), this.field_70250_c, v2.field_72308_g.func_174791_d()).setHitEntity(v2.field_72308_g), 0);
                }
            }
        }
        catch (Exception a1) {
            System.err.println(/*EL:104*/"The Lucky Bow encountered and error while trying to perform an impact function. Error report below:");
            /*SL:105*/a1.printStackTrace();
        }
    }
    
    public void func_70071_h_() {
        try {
            /*SL:114*/if (this.item == null && this.func_130014_f_().field_72995_K) {
                this.item = new EntityItem(this.func_130014_f_(), this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70180_af.func_82710_f(20));
            }
        }
        catch (Exception ex) {}
        /*SL:120*/this.func_70030_z();
        /*SL:122*/if (this.item != null) {
            this.item.func_70071_h_();
        }
        /*SL:124*/if (this.field_70127_C == 0.0f && this.field_70126_B == 0.0f) {
            final float v1 = /*EL:126*/MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
            final float n = /*EL:127*/(float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0 / 3.141592653589793);
            this.field_70177_z = n;
            this.field_70126_B = n;
            final float n2 = /*EL:128*/(float)(Math.atan2(this.field_70181_x, v1) * 180.0 / 3.141592653589793);
            this.field_70125_A = n2;
            this.field_70127_C = n2;
        }
        final BlockPos v2 = /*EL:131*/new BlockPos(this.xTile, this.yTile, this.zTile);
        IBlockState v3 = /*EL:132*/this.field_70170_p.func_180495_p(v2);
        final Block v4 = /*EL:133*/v3.func_177230_c();
        /*SL:135*/if (v4.func_149688_o() != Material.field_151579_a) {
            /*SL:137*/v4.func_180654_a((IBlockAccess)this.field_70170_p, v2);
            final AxisAlignedBB v5 = /*EL:138*/v4.func_180640_a(this.field_70170_p, v2, v3);
            /*SL:140*/if (v5 != null && v5.func_72318_a(new Vec3(this.field_70165_t, this.field_70163_u, this.field_70161_v))) {
                /*SL:142*/this.inGround = true;
            }
        }
        /*SL:146*/if (this.inGround) {
            /*SL:148*/this.func_70106_y();
        }
        else {
            /*SL:152*/++this.ticksInAir;
            Vec3 v6 = /*EL:153*/new Vec3(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            Vec3 v7 = /*EL:154*/new Vec3(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
            MovingObjectPosition v8 = /*EL:155*/this.field_70170_p.func_147447_a(v6, v7, false, true, false);
            /*SL:156*/v6 = new Vec3(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            /*SL:157*/v7 = new Vec3(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
            /*SL:159*/if (v8 != null) {
                /*SL:161*/v7 = new Vec3(v8.field_72307_f.field_72450_a, v8.field_72307_f.field_72448_b, v8.field_72307_f.field_72449_c);
            }
            Entity v9 = /*EL:164*/null;
            final List v10 = /*EL:165*/this.field_70170_p.func_72839_b((Entity)this, this.func_174813_aQ().func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0, 1.0, 1.0));
            double v11 = /*EL:166*/0.0;
            /*SL:170*/for (int v12 = 0; v12 < v10.size(); ++v12) {
                final Entity v13 = /*EL:172*/v10.get(v12);
                /*SL:174*/if (v13.func_70067_L() && (v13 != this.field_70250_c || this.ticksInAir >= 5)) {
                    final float v14 = /*EL:176*/0.3f;
                    final AxisAlignedBB v15 = /*EL:177*/v13.func_174813_aQ().func_72314_b((double)v14, (double)v14, (double)v14);
                    final MovingObjectPosition v16 = /*EL:178*/v15.func_72327_a(v6, v7);
                    /*SL:180*/if (v16 != null) {
                        final double v17 = /*EL:182*/v6.func_72438_d(v16.field_72307_f);
                        /*SL:184*/if (v17 < v11 || v11 == 0.0) {
                            /*SL:186*/v9 = v13;
                            /*SL:187*/v11 = v17;
                        }
                    }
                }
            }
            /*SL:193*/if (v9 != null) {
                /*SL:195*/v8 = new MovingObjectPosition(v9);
            }
            /*SL:198*/if (v8 != null && v8.field_72308_g != null && v8.field_72308_g instanceof EntityPlayer) {
                final EntityPlayer v18 = /*EL:200*/(EntityPlayer)v8.field_72308_g;
                /*SL:202*/if (this.field_70250_c instanceof EntityPlayer && !((EntityPlayer)this.field_70250_c).func_96122_a(v18)) {
                    /*SL:204*/v8 = null;
                }
            }
            /*SL:212*/if (v8 != null && !this.func_130014_f_().field_72995_K) {
                /*SL:214*/if (v8.field_72308_g != null) {
                    final float v19 = /*EL:216*/MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
                    int v20 = /*EL:217*/MathHelper.func_76143_f(v19 * this.func_70242_d());
                    /*SL:219*/if (this.func_70241_g()) {
                        /*SL:221*/v20 += this.field_70146_Z.nextInt(v20 / 2 + 2);
                    }
                    /*SL:225*/this.luckyImpact(v8);
                    /*SL:227*/if (this.func_70027_ad()) {
                        v8.field_72308_g.func_70015_d(5);
                    }
                    DamageSource v21 = /*EL:229*/null;
                    /*SL:230*/if (this.func_70242_d() != 0.0) {
                        /*SL:232*/if (this.field_70250_c == null) {
                            v21 = DamageSource.func_76353_a((EntityArrow)this, (Entity)this);
                        }
                        else {
                            /*SL:233*/v21 = DamageSource.func_76353_a((EntityArrow)this, this.field_70250_c);
                        }
                    }
                    /*SL:235*/if (this.func_70242_d() != 0.0) {
                        /*SL:237*/v8.field_72308_g.func_70097_a(v21, (float)this.func_70242_d());
                        /*SL:238*/if (v8.field_72308_g instanceof EntityLivingBase) {
                            final EntityLivingBase v22 = /*EL:240*/(EntityLivingBase)v8.field_72308_g;
                            /*SL:242*/if (this.field_70250_c instanceof EntityLivingBase) {
                                /*SL:244*/EnchantmentHelper.func_151384_a(v22, this.field_70250_c);
                                /*SL:245*/EnchantmentHelper.func_151385_b((EntityLivingBase)this.field_70250_c, (Entity)v22);
                            }
                            /*SL:248*/if (this.field_70250_c != null && v8.field_72308_g != this.field_70250_c && v8.field_72308_g instanceof EntityPlayer && this.field_70250_c instanceof EntityPlayerMP) {
                                /*SL:250*/((EntityPlayerMP)this.field_70250_c).field_71135_a.func_147359_a((Packet)new S2BPacketChangeGameState(6, 0.0f));
                            }
                        }
                    }
                    else {
                        /*SL:256*/this.field_70159_w *= -0.10000000149011612;
                        /*SL:257*/this.field_70181_x *= -0.10000000149011612;
                        /*SL:258*/this.field_70179_y *= -0.10000000149011612;
                        /*SL:259*/this.field_70177_z += 180.0f;
                        /*SL:260*/this.field_70126_B += 180.0f;
                        /*SL:261*/this.ticksInAir = 0;
                    }
                    /*SL:263*/this.func_70106_y();
                }
                else {
                    final BlockPos v23 = /*EL:267*/v8.func_178782_a();
                    /*SL:268*/this.xTile = v23.func_177958_n();
                    /*SL:269*/this.yTile = v23.func_177956_o();
                    /*SL:270*/this.zTile = v23.func_177952_p();
                    /*SL:271*/v3 = this.field_70170_p.func_180495_p(v23);
                    /*SL:272*/this.inTile = v3.func_177230_c();
                    /*SL:273*/this.inData = this.inTile.func_176201_c(v3);
                    /*SL:274*/this.field_70159_w = (float)(v8.field_72307_f.field_72450_a - this.field_70165_t);
                    /*SL:275*/this.field_70181_x = (float)(v8.field_72307_f.field_72448_b - this.field_70163_u);
                    /*SL:276*/this.field_70179_y = (float)(v8.field_72307_f.field_72449_c - this.field_70161_v);
                    final float v24 = /*EL:277*/MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
                    /*SL:278*/this.field_70165_t -= this.field_70159_w / v24 * 0.05000000074505806;
                    /*SL:279*/this.field_70163_u -= this.field_70181_x / v24 * 0.05000000074505806;
                    /*SL:280*/this.field_70161_v -= this.field_70179_y / v24 * 0.05000000074505806;
                    /*SL:281*/this.inGround = true;
                    /*SL:282*/this.func_70243_d(false);
                    /*SL:284*/if (this.inTile.func_149688_o() != Material.field_151579_a) {
                        /*SL:287*/this.luckyImpact(v8);
                        /*SL:289*/this.inTile.func_180634_a(this.field_70170_p, v23, v3, (Entity)this);
                        /*SL:290*/this.func_70106_y();
                    }
                }
            }
            /*SL:296*/if (!this.func_130014_f_().field_72995_K && this.hasTrail && this.ticksInAir >= 2) {
                try {
                    /*SL:301*/if (this.trailFrequency < 1.0 && this.trailFrequency > 0.0f) {
                        /*SL:304*/for (int v20 = (int)(1.0 / this.trailFrequency), v12 = 0; v12 < v20; ++v12) {
                            /*SL:306*/this.dropProcessorTrail.processRandomDrop(new DropProcessData(this.func_130014_f_(), this.field_70250_c, new Vec3(this.field_70165_t + this.field_70159_w * v12 / v20, this.field_70163_u + this.field_70181_x * v12 / v20, this.field_70161_v + this.field_70179_y * v12 / v20)), 0, false);
                        }
                    }
                    else/*SL:309*/ if ((this.ticksInAir - 2) % (int)this.trailFrequency == 0) {
                        this.dropProcessorTrail.processRandomDrop(new DropProcessData(this.func_130014_f_(), this.field_70250_c, this.func_174791_d()), 0, false);
                    }
                }
                catch (Exception v25) {
                    System.err.println(/*EL:313*/"The Lucky Bow encountered and error while trying to perform a trail function. Error report below:");
                    /*SL:314*/v25.printStackTrace();
                }
            }
            /*SL:318*/this.field_70165_t += this.field_70159_w;
            /*SL:319*/this.field_70163_u += this.field_70181_x;
            /*SL:320*/this.field_70161_v += this.field_70179_y;
            final float v19 = /*EL:321*/MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
            /*SL:322*/this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0 / 3.141592653589793);
            /*SL:324*/this.field_70125_A = (float)(Math.atan2(this.field_70181_x, v19) * 180.0 / 3.141592653589793);
            while (this.field_70125_A - this.field_70127_C < -180.0f) {
                this.field_70127_C -= 360.0f;
            }
            /*SL:329*/while (this.field_70125_A - this.field_70127_C >= 180.0f) {
                /*SL:332*/this.field_70127_C += 360.0f;
            }
            /*SL:334*/while (this.field_70177_z - this.field_70126_B < -180.0f) {
                /*SL:337*/this.field_70126_B -= 360.0f;
            }
            /*SL:339*/while (this.field_70177_z - this.field_70126_B >= 180.0f) {
                /*SL:342*/this.field_70126_B += 360.0f;
            }
            /*SL:345*/this.field_70125_A = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2f;
            /*SL:346*/this.field_70177_z = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2f;
            float v24 = /*EL:347*/0.99f;
            final float v14 = /*EL:348*/0.05f;
            /*SL:350*/if (this.func_70090_H()) {
                /*SL:353*/for (int v20 = 0; v20 < 4; ++v20) {
                    final float v26 = /*EL:355*/0.25f;
                    /*SL:356*/this.field_70170_p.func_175688_a(EnumParticleTypes.WATER_BUBBLE, this.field_70165_t - this.field_70159_w * v26, this.field_70163_u - this.field_70181_x * v26, this.field_70161_v - this.field_70179_y * v26, this.field_70159_w, this.field_70181_x, this.field_70179_y, new int[0]);
                }
                /*SL:359*/v24 = 0.6f;
            }
            /*SL:362*/if (this.func_70026_G()) {
                this.func_70066_B();
            }
            /*SL:364*/this.field_70159_w *= v24;
            /*SL:365*/this.field_70181_x *= v24;
            /*SL:366*/this.field_70179_y *= v24;
            /*SL:367*/this.field_70181_x -= v14;
            /*SL:368*/this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            /*SL:369*/this.func_145775_I();
        }
    }
    
    public void func_70014_b(final NBTTagCompound v-1) {
        /*SL:378*/v-1.func_74777_a("xTile", (short)this.xTile);
        /*SL:379*/v-1.func_74777_a("yTile", (short)this.yTile);
        /*SL:380*/v-1.func_74777_a("zTile", (short)this.zTile);
        /*SL:381*/v-1.func_74777_a("life", (short)this.ticksInGround);
        final ResourceLocation v0 = (ResourceLocation)Block.field_149771_c.func_177774_c(/*EL:382*/(Object)this.inTile);
        /*SL:383*/v-1.func_74778_a("inTile", (v0 == null) ? "" : v0.toString());
        /*SL:384*/v-1.func_74774_a("inData", (byte)this.inData);
        /*SL:385*/v-1.func_74774_a("shake", (byte)this.field_70249_b);
        /*SL:386*/v-1.func_74774_a("inGround", (byte)(byte)(this.inGround ? 1 : 0));
        /*SL:387*/v-1.func_74774_a("pickup", (byte)this.field_70251_a);
        /*SL:388*/v-1.func_74780_a("damage", this.func_70242_d());
        /*SL:390*/if (this.item != null) {
            v-1.func_74782_a("item", (NBTBase)this.item.func_92059_d().func_77955_b(new NBTTagCompound()));
        }
        /*SL:392*/if (this.hasTrail) {
            final NBTTagCompound v = /*EL:394*/new NBTTagCompound();
            /*SL:395*/v.func_74776_a("frequency", this.trailFrequency);
            final NBTTagList v2 = /*EL:397*/new NBTTagList();
            /*SL:398*/for (int a1 = 0; a1 < this.dropProcessorTrail.getDrops().size(); ++a1) {
                /*SL:400*/v2.func_74742_a((NBTBase)new NBTTagString(this.dropProcessorTrail.getDrops().get(a1).toString()));
            }
            /*SL:402*/v.func_74782_a("drops", (NBTBase)v2);
            /*SL:403*/v-1.func_74782_a("trail", (NBTBase)v);
        }
        /*SL:407*/if (this.hasImpact) {
            final NBTTagList v3 = /*EL:409*/new NBTTagList();
            /*SL:410*/for (int v4 = 0; v4 < this.dropProcessorImpact.getDrops().size(); ++v4) {
                /*SL:412*/v3.func_74742_a((NBTBase)new NBTTagString(this.dropProcessorImpact.getDrops().get(v4).toString()));
            }
            /*SL:414*/v-1.func_74782_a("impact", (NBTBase)v3);
        }
    }
    
    public void func_70037_a(final NBTTagCompound v-2) {
        /*SL:421*/this.xTile = v-2.func_74765_d("xTile");
        /*SL:422*/this.yTile = v-2.func_74765_d("yTile");
        /*SL:423*/this.zTile = v-2.func_74765_d("zTile");
        /*SL:424*/this.ticksInGround = v-2.func_74765_d("life");
        /*SL:426*/if (v-2.func_150297_b("inTile", 8)) {
            /*SL:428*/this.inTile = Block.func_149684_b(v-2.func_74779_i("inTile"));
        }
        else {
            /*SL:432*/this.inTile = Block.func_149729_e(v-2.func_74771_c("inTile") & 0xFF);
        }
        /*SL:435*/this.inData = (v-2.func_74771_c("inData") & 0xFF);
        /*SL:436*/this.field_70249_b = (v-2.func_74771_c("shake") & 0xFF);
        /*SL:437*/this.inGround = (v-2.func_74771_c("inGround") == 1);
        /*SL:439*/if (v-2.func_150297_b("damage", 99)) {
            /*SL:441*/this.func_70239_b(v-2.func_74769_h("damage"));
        }
        /*SL:444*/if (v-2.func_150297_b("pickup", 99)) {
            /*SL:446*/this.field_70251_a = v-2.func_74771_c("pickup");
        }
        else/*SL:448*/ if (v-2.func_150297_b("player", 99)) {
            /*SL:450*/this.field_70251_a = (v-2.func_74767_n("player") ? 1 : 0);
        }
        /*SL:453*/if (v-2.func_74764_b("item")) {
            /*SL:455*/v-2.func_74782_a("Count", (NBTBase)new NBTTagInt(1));
            /*SL:456*/this.item = new EntityItem(this.func_130014_f_(), this.field_70165_t, this.field_70163_u, this.field_70161_v, ItemStack.func_77949_a(v-2.func_74775_l("item")));
        }
        else {
            /*SL:458*/this.item = new EntityItem(this.func_130014_f_(), this.field_70165_t, this.field_70163_u, this.field_70161_v, new ItemStack(Items.field_151055_y));
        }
        /*SL:460*/this.field_70180_af.func_75692_b(20, (Object)this.item.func_92059_d());
        /*SL:462*/if (v-2.func_74764_b("trail")) {
            final NBTTagCompound func_74775_l = /*EL:464*/v-2.func_74775_l("trail");
            /*SL:465*/if (func_74775_l.func_74764_b("frequency")) {
                this.trailFrequency = func_74775_l.func_74760_g("frequency");
            }
            /*SL:466*/if (func_74775_l.func_74764_b("drops")) {
                final NBTTagList v0 = /*EL:468*/func_74775_l.func_150295_c("drops", (int)new NBTTagString().func_74732_a());
                /*SL:469*/for (int v = 0; v < v0.func_74745_c(); ++v) {
                    final DropContainer a1 = /*EL:471*/new DropContainer();
                    /*SL:472*/a1.readFromString(v0.func_150307_f(v));
                    /*SL:473*/this.dropProcessorTrail.registerDrop(a1);
                }
            }
            /*SL:476*/this.hasTrail = true;
        }
        /*SL:479*/if (v-2.func_74764_b("impact")) {
            final NBTTagList func_150295_c = /*EL:481*/v-2.func_150295_c("impact", (int)new NBTTagString().func_74732_a());
            /*SL:482*/for (int v2 = 0; v2 < func_150295_c.func_74745_c(); ++v2) {
                final DropContainer v3 = /*EL:484*/new DropContainer();
                /*SL:485*/v3.readFromString(func_150295_c.func_150307_f(v2));
                /*SL:486*/this.dropProcessorImpact.registerDrop(v3);
            }
            /*SL:488*/this.hasImpact = true;
        }
    }
    
    public void func_70100_b_(final EntityPlayer a1) {
    }
    
    public EntityItem getItem() {
        /*SL:500*/return this.item;
    }
    
    public enum EnumRenderType
    {
        ITEM, 
        BLOCK, 
        PARTICLE;
    }
}
