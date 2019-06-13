package mod.lucky.item;

import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.client.util.ITooltipFlag;
import java.util.List;
import javax.annotation.Nullable;
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
                /*SL:49*/if (v2 == null) {
                    /*SL:50*/return 0.0f;
                }
                final ItemStack a4 = /*EL:52*/v2.func_184607_cu();
                /*SL:53*/return (a4 != null && a4.func_77973_b() instanceof ItemLuckyBow) ? ((a3.func_77988_m() - /*EL:54*/v2.func_184605_cv()) / 20.0f) : 0.0f;
            }
        });
        this.func_185043_a(new ResourceLocation("pulling"), (IItemPropertyGetter)new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float func_185085_a(final ItemStack a1, final World a2, final EntityLivingBase a3) {
                /*SL:65*/return (a3 != null && a3.func_184587_cr() && /*EL:66*/a3.func_184607_cu() == /*EL:67*/a1) ? 1.0f : 0.0f;
            }
        });
    }
    
    public void func_77615_a(final ItemStack v-7, final World v-6, final EntityLivingBase v-5, final int v-4) {
        /*SL:77*/if (v-5 instanceof EntityPlayer) {
            final EntityPlayer a5 = /*EL:78*/(EntityPlayer)v-5;
            final boolean b = /*EL:80*/a5.field_71075_bZ.field_75098_d || /*EL:82*/EnchantmentHelper.func_77506_a(Enchantments.field_185312_x, v-7) > 0;
            final ItemStack inventoryArrows = /*EL:83*/this.getInventoryArrows(a5);
            int v0 = /*EL:85*/this.func_77626_a(v-7) - v-4;
            /*SL:87*/v0 = ForgeEventFactory.onArrowLoose(v-7, v-6, a5, v0, inventoryArrows != null || b);
            /*SL:89*/if (v0 < 0) {
                return;
            }
            /*SL:91*/if (b || inventoryArrows != null) {
                final float v = func_185059_b(/*EL:92*/v0);
                /*SL:93*/if (v < 0.1) {
                    return;
                }
                /*SL:95*/if (!v-6.field_72995_K) {
                    try {
                        final int a1 = /*EL:97*/ItemLuckyBlock.getLuck(v-7);
                        final String[] a2 = /*EL:98*/ItemLuckyBlock.getDrops(v-7);
                        final EntityArrow a3 = /*EL:100*/(EntityArrow)new EntityTippedArrow(v-6, (EntityLivingBase)a5);
                        /*SL:101*/if (a2 != null && a2.length != 0) {
                            /*SL:102*/this.getDropProcessor().processRandomDrop(/*EL:104*/LuckyFunction.getDropsFromStringArray(a2), new DropProcessData(v-6, (Entity)a5, a3.func_174791_d()).setBowPower(/*EL:105*/v * 3.0f), /*EL:106*/a1);
                        }
                        else {
                            /*SL:109*/this.getDropProcessor().processRandomDrop(new DropProcessData(v-6, (Entity)a5, a3.func_174791_d()).setBowPower(/*EL:111*/v * 3.0f), /*EL:112*/a1);
                        }
                    }
                    catch (Exception a4) {
                        System.err.println(/*EL:116*/"The Lucky Bow encountered and error while trying to perform a function. Error report below:");
                        /*SL:118*/a4.printStackTrace();
                    }
                }
                /*SL:122*/v-6.func_184148_a((EntityPlayer)null, a5.field_70165_t, a5.field_70163_u, a5.field_70161_v, SoundEvents.field_187737_v, SoundCategory.NEUTRAL, 1.0f, 1.0f / (ItemLuckyBow.field_77697_d.nextFloat() * /*EL:130*/0.4f + 1.2f) + v * 0.5f);
                /*SL:132*/if (!b) {
                    /*SL:134*/inventoryArrows.func_190920_e(inventoryArrows.func_190916_E() - 1);
                    /*SL:136*/if (inventoryArrows.func_190916_E() == 0) {
                        a5.field_71071_by.func_184437_d(inventoryArrows);
                    }
                }
            }
        }
    }
    
    private ItemStack getInventoryArrows(final EntityPlayer v0) {
        /*SL:143*/if (this.func_185058_h_(v0.func_184586_b(EnumHand.OFF_HAND))) {
            /*SL:144*/return v0.func_184586_b(EnumHand.OFF_HAND);
        }
        /*SL:145*/if (this.func_185058_h_(v0.func_184586_b(EnumHand.MAIN_HAND))) {
            /*SL:146*/return v0.func_184586_b(EnumHand.MAIN_HAND);
        }
        /*SL:148*/for (int v = 0; v < v0.field_71071_by.func_70302_i_(); ++v) {
            final ItemStack a1 = /*EL:149*/v0.field_71071_by.func_70301_a(v);
            /*SL:151*/if (this.func_185058_h_(a1)) {
                /*SL:152*/return a1;
            }
        }
        /*SL:156*/return null;
    }
    
    @SideOnly(Side.CLIENT)
    public ModelResourceLocation getModel(final ItemStack a1, final EntityPlayer a2, final int a3) {
        ModelResourceLocation v1 = /*EL:163*/new ModelResourceLocation(this.bowTextureName, "inventory");
        final int v2 = /*EL:166*/a1.func_77988_m() - a3;
        /*SL:168*/if (a1.func_77973_b() == this && a2.func_184607_cu() != null) {
            /*SL:169*/if (v2 >= 18) {
                /*SL:170*/v1 = new ModelResourceLocation(this.bowTextureName + "_pulling_2", "inventory");
            }
            else/*SL:172*/ if (v2 > 13) {
                /*SL:173*/v1 = new ModelResourceLocation(this.bowTextureName + "_pulling_1", "inventory");
            }
            else/*SL:175*/ if (v2 > 0) {
                /*SL:176*/v1 = new ModelResourceLocation(this.bowTextureName + "_pulling_0", "inventory");
            }
        }
        /*SL:180*/return v1;
    }
    
    public void setBowTextureName(final String a1) {
        /*SL:184*/this.bowTextureName = a1;
    }
    
    public int func_77619_b() {
        /*SL:189*/return 0;
    }
    
    public EnumAction func_77661_b(final ItemStack a1) {
        /*SL:194*/return EnumAction.BOW;
    }
    
    public DropProcessor getDropProcessor() {
        /*SL:198*/return this.dropProcessor;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean func_77636_d(final ItemStack a1) {
        /*SL:204*/return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void func_77624_a(final ItemStack a1, @Nullable final World a2, final List<String> a3, final ITooltipFlag a4) {
        final int v1 = /*EL:211*/ItemLuckyBlock.getLuck(a1);
        final String[] v2 = /*EL:212*/ItemLuckyBlock.getDrops(a1);
        /*SL:213*/a3.add(/*EL:214*/I18n.func_74838_a("item.luckyBlock.luck") + ": " + ((v1 == 0) ? TextFormatting.GOLD : ((v1 < 0) ? TextFormatting.RED : (TextFormatting.GREEN + "+"))) + /*EL:219*/String.valueOf(v1));
        /*SL:220*/if (v2 != null && v2.length != 0) {
            /*SL:221*/a3.add(TextFormatting.GRAY + "" + TextFormatting.ITALIC + /*EL:225*/I18n.func_74838_a("item.luckyBlock.customDrop"));
        }
    }
    
    public LuckyCrafting getCrafting() {
        /*SL:229*/return this.crafting;
    }
}
