package mod.lucky.item;

import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import java.util.List;
import net.minecraft.item.EnumAction;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import mod.lucky.drop.func.DropProcessData;
import mod.lucky.util.LuckyFunction;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
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
    }
    
    public void func_77615_a(final ItemStack v-6, final World v-5, final EntityPlayer v-4, final int v-3) {
        int charge = /*EL:42*/this.func_77626_a(v-6) - v-3;
        final ArrowLooseEvent arrowLooseEvent = /*EL:43*/new ArrowLooseEvent(v-4, v-6, charge);
        /*SL:44*/if (MinecraftForge.EVENT_BUS.post((Event)arrowLooseEvent)) {
            return;
        }
        /*SL:45*/charge = arrowLooseEvent.charge;
        final boolean v0 = /*EL:47*/v-4.field_71075_bZ.field_75098_d || EnchantmentHelper.func_77506_a(Enchantment.field_77342_w.field_77352_x, v-6) > 0;
        /*SL:49*/if (v0 || v-4.field_71071_by.func_146028_b(Items.field_151032_g)) {
            float v = /*EL:51*/charge / 20.0f;
            /*SL:52*/v = (v * v + v * 2.0f) / 3.0f;
            /*SL:54*/if (v < 0.1) {
                return;
            }
            /*SL:55*/if (v > 1.0f) {
                v = 1.0f;
            }
            /*SL:57*/if (!v-5.field_72995_K) {
                try {
                    final int a1 = /*EL:61*/ItemLuckyBlock.getLuck(v-6);
                    final String[] a2 = /*EL:62*/ItemLuckyBlock.getDrops(v-6);
                    final EntityArrow a3 = /*EL:64*/new EntityArrow(v-5, (EntityLivingBase)v-4, 1.0f);
                    /*SL:65*/if (a2 != null && a2.length != 0) {
                        this.getDropProcessor().processRandomDrop(LuckyFunction.getDropsFromStringArray(a2), new DropProcessData(v-5, (Entity)v-4, a3.func_174791_d()).setBowPower(v * 2.0f), a1);
                    }
                    else {
                        /*SL:66*/this.getDropProcessor().processRandomDrop(new DropProcessData(v-5, (Entity)v-4, a3.func_174791_d()).setBowPower(v * 2.0f), a1);
                    }
                }
                catch (Exception a4) {
                    System.err.println(/*EL:70*/"The Lucky Bow encountered and error while trying to perform a function. Error report below:");
                    /*SL:71*/a4.printStackTrace();
                }
            }
            /*SL:75*/v-4.field_71071_by.func_146026_a(Items.field_151032_g);
            /*SL:76*/v-6.func_77972_a(1, (EntityLivingBase)v-4);
            /*SL:77*/v-5.func_72956_a((Entity)v-4, "random.bow", 1.0f, 1.0f / (ItemLuckyBow.field_77697_d.nextFloat() * 0.4f + 1.2f) + v * 0.5f);
        }
    }
    
    @SideOnly(Side.CLIENT)
    public ModelResourceLocation getModel(final ItemStack a1, final EntityPlayer a2, final int a3) {
        ModelResourceLocation v1 = /*EL:85*/new ModelResourceLocation(this.bowTextureName, "inventory");
        final int v2 = /*EL:87*/a1.func_77988_m() - a3;
        /*SL:89*/if (a1.func_77973_b() == this && a2.func_71011_bu() != null) {
            /*SL:91*/if (v2 >= 18) {
                /*SL:93*/v1 = new ModelResourceLocation(this.bowTextureName + "_pulling_2", "inventory");
            }
            else/*SL:95*/ if (v2 > 13) {
                /*SL:97*/v1 = new ModelResourceLocation(this.bowTextureName + "_pulling_1", "inventory");
            }
            else/*SL:99*/ if (v2 > 0) {
                /*SL:101*/v1 = new ModelResourceLocation(this.bowTextureName + "_pulling_0", "inventory");
            }
        }
        /*SL:104*/return v1;
    }
    
    public void setBowTextureName(final String a1) {
        /*SL:109*/this.bowTextureName = a1;
    }
    
    public int func_77619_b() {
        /*SL:115*/return 0;
    }
    
    public EnumAction func_77661_b(final ItemStack a1) {
        /*SL:121*/return EnumAction.BOW;
    }
    
    public DropProcessor getDropProcessor() {
        /*SL:126*/return this.dropProcessor;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean func_77636_d(final ItemStack a1) {
        /*SL:133*/return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void func_77624_a(final ItemStack a1, final EntityPlayer a2, final List a3, final boolean a4) {
        final int v1 = /*EL:140*/ItemLuckyBlock.getLuck(a1);
        final String[] v2 = /*EL:141*/ItemLuckyBlock.getDrops(a1);
        /*SL:142*/a3.add(StatCollector.func_74838_a("item.luckyBlock.luck") + ": " + ((v1 == 0) ? EnumChatFormatting.GOLD : ((v1 < 0) ? EnumChatFormatting.RED : (EnumChatFormatting.GREEN + "+"))) + String.valueOf(v1));
        /*SL:143*/if (v2 != null && v2.length != 0) {
            a3.add(EnumChatFormatting.GRAY + "" + EnumChatFormatting.ITALIC + StatCollector.func_74838_a("item.luckyBlock.customDrop"));
        }
    }
    
    public LuckyCrafting getCrafting() {
        /*SL:148*/return this.crafting;
    }
}
