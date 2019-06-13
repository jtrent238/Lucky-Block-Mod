package mod.lucky.entity;

import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagString;
import mod.lucky.drop.DropContainer;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import mod.lucky.drop.func.DropProcessData;
import mod.lucky.util.LuckyFunction;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import mod.lucky.item.ItemLuckyPotion;
import mod.lucky.Lucky;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import mod.lucky.drop.func.DropProcessor;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.entity.projectile.EntityThrowable;

public class EntityLuckyPotion extends EntityThrowable
{
    private static final DataParameter<ItemStack> POTION_ITEM;
    private ItemStack itemLuckyPotion;
    private DropProcessor impactDropProcessor;
    private int luck;
    private String[] customDrops;
    
    public EntityLuckyPotion(final World a1) {
        super(a1);
        this.itemLuckyPotion = null;
        this.luck = 0;
        this.customDrops = null;
    }
    
    public EntityLuckyPotion(final World a1, final EntityLivingBase a2) {
        this(a1, a2, Lucky.lucky_potion, new DropProcessor(), 0, null);
    }
    
    public EntityLuckyPotion(final World a1, final EntityLivingBase a2, final ItemLuckyPotion a3, final DropProcessor a4, final int a5, final String[] a6) {
        super(a1, a2);
        this.itemLuckyPotion = null;
        this.luck = 0;
        this.customDrops = null;
        this.itemLuckyPotion = new ItemStack((Item)a3, 1);
        this.field_70180_af.func_187227_b((DataParameter)EntityLuckyPotion.POTION_ITEM, (Object)this.itemLuckyPotion);
        this.impactDropProcessor = a4;
        this.luck = a5;
        this.customDrops = a6;
    }
    
    public EntityLuckyPotion(final World a1, final double a2, final double a3, final double a4) {
        super(a1, a2, a3, a4);
        this.itemLuckyPotion = null;
        this.luck = 0;
        this.customDrops = null;
    }
    
    protected void func_70088_a() {
        /*SL:61*/super.func_70088_a();
        /*SL:62*/if (this.impactDropProcessor == null) {
            this.impactDropProcessor = new DropProcessor();
        }
        /*SL:63*/this.field_70180_af.func_187214_a((DataParameter)EntityLuckyPotion.POTION_ITEM, (Object)new ItemStack(Items.field_151055_y));
    }
    
    public ItemStack getItemLuckyPotion() {
        /*SL:67*/return (ItemStack)this.field_70180_af.func_187225_a((DataParameter)EntityLuckyPotion.POTION_ITEM);
    }
    
    protected float func_70185_h() {
        /*SL:72*/return 0.05f;
    }
    
    private void luckyImpact(final Entity v0) {
        try {
            /*SL:77*/if (this.impactDropProcessor != null && this.impactDropProcessor.getDrops().size() > 0) {
                final Vec3d a1 = /*EL:78*/(v0 == null) ? this.func_174791_d() : /*EL:79*/v0.func_174791_d();
                /*SL:80*/if (this.customDrops != null && this.customDrops.length != 0) {
                    /*SL:81*/this.impactDropProcessor.processRandomDrop(/*EL:82*/LuckyFunction.getDropsFromStringArray(this.customDrops), new DropProcessData(this.func_130014_f_(), /*EL:83*/(Entity)this.func_85052_h(), a1).setHitEntity(v0), /*EL:84*/this.luck);
                }
                else {
                    /*SL:87*/this.impactDropProcessor.processRandomDrop(new DropProcessData(this.func_130014_f_(), /*EL:88*/(Entity)this.func_85052_h(), a1).setHitEntity(v0), /*EL:89*/this.luck);
                }
            }
        }
        catch (Exception v) {
            System.err.println(/*EL:93*/"The Lucky Potion encountered and error while trying to perform a function. Error report below:");
            /*SL:95*/v.printStackTrace();
        }
    }
    
    public void func_70071_h_() {
        try {
            /*SL:102*/if (this.itemLuckyPotion == null && this.func_130014_f_().field_72995_K) {
                /*SL:103*/this.itemLuckyPotion = (ItemStack)this.field_70180_af.func_187225_a((DataParameter)EntityLuckyPotion.POTION_ITEM);
            }
        }
        catch (Exception ex) {}
        /*SL:106*/this.func_70030_z();
        /*SL:107*/super.func_70071_h_();
    }
    
    protected void func_70184_a(final RayTraceResult a1) {
        /*SL:112*/if (!this.field_70170_p.field_72995_K) {
            /*SL:113*/this.luckyImpact(a1.field_72308_g);
            /*SL:114*/this.func_70106_y();
        }
    }
    
    public void func_70014_b(final NBTTagCompound v2) {
        /*SL:120*/super.func_70014_b(v2);
        final NBTTagList v3 = /*EL:121*/new NBTTagList();
        /*SL:122*/for (int a1 = 0; a1 < this.impactDropProcessor.getDrops().size(); ++a1) {
            /*SL:123*/v3.func_74742_a((NBTBase)new NBTTagString(this.impactDropProcessor.getDrops().get(a1).toString()));
        }
        /*SL:125*/v2.func_74782_a("impact", (NBTBase)v3);
        /*SL:126*/v2.func_74782_a("itemLuckyPotion", (NBTBase)this.getItemLuckyPotion().func_77955_b(/*EL:127*/new NBTTagCompound()));
    }
    
    public void func_70037_a(final NBTTagCompound v-1) {
        /*SL:132*/super.func_70037_a(v-1);
        final NBTTagList v0 = /*EL:133*/v-1.func_150295_c("impact", (int)new NBTTagString().func_74732_a());
        /*SL:134*/for (int v = 0; v < v0.func_74745_c(); ++v) {
            final DropContainer a1 = /*EL:135*/new DropContainer();
            /*SL:136*/a1.readFromString(v0.func_150307_f(v));
            /*SL:137*/this.impactDropProcessor.registerDrop(a1);
        }
        /*SL:139*/if (v-1.func_74764_b("itemLuckyPotion")) {
            /*SL:140*/this.itemLuckyPotion = new ItemStack(v-1.func_74775_l("itemLuckyPotion"));
        }
    }
    
    static {
        POTION_ITEM = EntityDataManager.func_187226_a((Class)EntityLuckyPotion.class, DataSerializers.field_187196_f);
    }
}
