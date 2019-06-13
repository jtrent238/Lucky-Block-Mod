package mod.lucky.item;

import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import java.util.List;
import net.minecraft.item.EnumAction;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.SoundCategory;
import net.minecraft.init.SoundEvents;
import net.minecraft.entity.Entity;
import mod.lucky.drop.func.DropProcessData;
import mod.lucky.util.LuckyFunction;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Item;
import mod.lucky.crafting.LuckyCrafting;
import mod.lucky.drop.func.DropProcessor;
import net.minecraft.item.ItemBow;

public class ItemLuckyBow extends ItemBow
{
    private DropProcessor dropProcessor;
    private LuckyCrafting crafting;
    private String bowTextureName;
    
    public ItemLuckyBow() {
        this.bowTextureName = "lucky:lucky_bow";
        this.func_77656_e(1000);
        this.dropProcessor = new DropProcessor();
        this.crafting = new LuckyCrafting((Item)this);
        this.func_185043_a(new ResourceLocation("pull"), (IItemPropertyGetter)new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float func_185085_a(final ItemStack a3, final World v1, final EntityLivingBase v2) {
                /*SL:50*/if (v2 == null) {
                    /*SL:52*/return 0.0f;
                }
                final ItemStack a4 = /*EL:56*/v2.func_184607_cu();
                /*SL:57*/return (a4 != null && a4.func_77973_b() instanceof ItemLuckyBow) ? ((a3.func_77988_m() - v2.func_184605_cv()) / 20.0f) : 0.0f;
            }
        });
        this.func_185043_a(new ResourceLocation("pulling"), (IItemPropertyGetter)new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float func_185085_a(final ItemStack a1, final World a2, final EntityLivingBase a3) {
                /*SL:67*/return (a3 != null && a3.func_184587_cr() && a3.func_184607_cu() == a1) ? 1.0f : 0.0f;
            }
        });
    }
    
    public void func_77615_a(final ItemStack v-7, final World v-6, final EntityLivingBase v-5, final int v-4) {
        /*SL:75*/if (v-5 instanceof EntityPlayer) {
            final EntityPlayer a5 = /*EL:77*/(EntityPlayer)v-5;
            final boolean b = /*EL:79*/a5.field_71075_bZ.field_75098_d || EnchantmentHelper.func_77506_a(Enchantments.field_185312_x, v-7) > 0;
            final ItemStack inventoryArrows = /*EL:80*/this.getInventoryArrows(a5);
            int v0 = /*EL:82*/this.func_77626_a(v-7) - v-4;
            /*SL:83*/v0 = ForgeEventFactory.onArrowLoose(v-7, v-6, a5, v0, inventoryArrows != null || b);
            /*SL:84*/if (v0 < 0) {
                return;
            }
            /*SL:86*/if (b || inventoryArrows != null) {
                final float v = func_185059_b(/*EL:88*/v0);
                /*SL:89*/if (v < 0.1) {
                    return;
                }
                /*SL:91*/if (!v-6.field_72995_K) {
                    try {
                        final int a1 = /*EL:95*/ItemLuckyBlock.getLuck(v-7);
                        final String[] a2 = /*EL:96*/ItemLuckyBlock.getDrops(v-7);
                        final EntityArrow a3 = /*EL:98*/(EntityArrow)new EntityTippedArrow(v-6, (EntityLivingBase)a5);
                        /*SL:99*/if (a2 != null && a2.length != 0) {
                            this.getDropProcessor().processRandomDrop(LuckyFunction.getDropsFromStringArray(a2), new DropProcessData(v-6, (Entity)a5, a3.func_174791_d()).setBowPower(v * 3.0f), a1);
                        }
                        else {
                            /*SL:100*/this.getDropProcessor().processRandomDrop(new DropProcessData(v-6, (Entity)a5, a3.func_174791_d()).setBowPower(v * 3.0f), a1);
                        }
                    }
                    catch (Exception a4) {
                        System.err.println(/*EL:105*/"The Lucky Bow encountered and error while trying to perform a function. Error report below:");
                        /*SL:106*/a4.printStackTrace();
                    }
                }
                /*SL:110*/v-6.func_184148_a((EntityPlayer)null, a5.field_70165_t, a5.field_70163_u, a5.field_70161_v, SoundEvents.field_187737_v, SoundCategory.NEUTRAL, 1.0f, 1.0f / (ItemLuckyBow.field_77697_d.nextFloat() * 0.4f + 1.2f) + v * 0.5f);
                /*SL:112*/if (!b) {
                    final ItemStack itemStack = /*EL:114*/inventoryArrows;
                    --itemStack.field_77994_a;
                    /*SL:115*/if (inventoryArrows.field_77994_a == 0) {
                        a5.field_71071_by.func_184437_d(inventoryArrows);
                    }
                }
            }
        }
    }
    
    private ItemStack getInventoryArrows(final EntityPlayer v0) {
        /*SL:123*/if (this.func_185058_h_(v0.func_184586_b(EnumHand.OFF_HAND))) {
            /*SL:125*/return v0.func_184586_b(EnumHand.OFF_HAND);
        }
        /*SL:127*/if (this.func_185058_h_(v0.func_184586_b(EnumHand.MAIN_HAND))) {
            /*SL:129*/return v0.func_184586_b(EnumHand.MAIN_HAND);
        }
        /*SL:133*/for (int v = 0; v < v0.field_71071_by.func_70302_i_(); ++v) {
            final ItemStack a1 = /*EL:135*/v0.field_71071_by.func_70301_a(v);
            /*SL:137*/if (this.func_185058_h_(a1)) {
                /*SL:139*/return a1;
            }
        }
        /*SL:143*/return null;
    }
    
    @SideOnly(Side.CLIENT)
    public ModelResourceLocation getModel(final ItemStack a1, final EntityPlayer a2, final int a3) {
        ModelResourceLocation v1 = /*EL:151*/new ModelResourceLocation(this.bowTextureName, "inventory");
        final int v2 = /*EL:153*/a1.func_77988_m() - a3;
        /*SL:155*/if (a1.func_77973_b() == this && a2.func_184607_cu() != null) {
            /*SL:157*/if (v2 >= 18) {
                /*SL:159*/v1 = new ModelResourceLocation(this.bowTextureName + "_pulling_2", "inventory");
            }
            else/*SL:161*/ if (v2 > 13) {
                /*SL:163*/v1 = new ModelResourceLocation(this.bowTextureName + "_pulling_1", "inventory");
            }
            else/*SL:165*/ if (v2 > 0) {
                /*SL:167*/v1 = new ModelResourceLocation(this.bowTextureName + "_pulling_0", "inventory");
            }
        }
        /*SL:170*/return v1;
    }
    
    public void setBowTextureName(final String a1) {
        /*SL:175*/this.bowTextureName = a1;
    }
    
    public int func_77619_b() {
        /*SL:181*/return 0;
    }
    
    public EnumAction func_77661_b(final ItemStack a1) {
        /*SL:187*/return EnumAction.BOW;
    }
    
    public DropProcessor getDropProcessor() {
        /*SL:192*/return this.dropProcessor;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean func_77636_d(final ItemStack a1) {
        /*SL:199*/return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void func_77624_a(final ItemStack a1, final EntityPlayer a2, final List a3, final boolean a4) {
        final int v1 = /*EL:206*/ItemLuckyBlock.getLuck(a1);
        final String[] v2 = /*EL:207*/ItemLuckyBlock.getDrops(a1);
        /*SL:208*/a3.add(I18n.func_74838_a("item.luckyBlock.luck") + ": " + ((v1 == 0) ? TextFormatting.GOLD : ((v1 < 0) ? TextFormatting.RED : (TextFormatting.GREEN + "+"))) + String.valueOf(v1));
        /*SL:209*/if (v2 != null && v2.length != 0) {
            a3.add(TextFormatting.GRAY + "" + TextFormatting.ITALIC + I18n.func_74838_a("item.luckyBlock.customDrop"));
        }
    }
    
    public LuckyCrafting getCrafting() {
        /*SL:214*/return this.crafting;
    }
}
