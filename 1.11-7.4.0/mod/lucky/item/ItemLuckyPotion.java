package mod.lucky.item;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.NonNullList;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import java.util.List;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.util.EnumActionResult;
import net.minecraft.stats.StatList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import mod.lucky.entity.EntityLuckyPotion;
import net.minecraft.util.SoundCategory;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import mod.lucky.crafting.LuckyCrafting;
import mod.lucky.drop.func.DropProcessor;
import net.minecraft.item.Item;

public class ItemLuckyPotion extends Item
{
    private DropProcessor dropProcessor;
    private LuckyCrafting crafting;
    
    public ItemLuckyPotion() {
        this.dropProcessor = new DropProcessor();
        this.crafting = new LuckyCrafting(this);
    }
    
    public ActionResult<ItemStack> func_77659_a(final World v2, final EntityPlayer v3, final EnumHand v4) {
        final ItemStack v5 = /*EL:40*/v3.func_184586_b(v4);
        /*SL:41*/if (!v3.field_71075_bZ.field_75098_d) {
            /*SL:44*/v5.func_190920_e(v5.func_190916_E() - 1);
        }
        /*SL:47*/v2.func_184148_a((EntityPlayer)null, v3.field_70165_t, v3.field_70163_u, v3.field_70161_v, SoundEvents.field_187827_fP, SoundCategory.NEUTRAL, 0.5f, 0.4f / (ItemLuckyPotion.field_77697_d.nextFloat() * 0.4f + 0.8f));
        /*SL:49*/if (!v2.field_72995_K) {
            final int a1 = /*EL:51*/ItemLuckyBlock.getLuck(v5);
            final String[] a2 = /*EL:52*/ItemLuckyBlock.getDrops(v5);
            final EntityLuckyPotion a3 = /*EL:53*/new EntityLuckyPotion(v2, (EntityLivingBase)v3, this, this.dropProcessor, a1, a2);
            /*SL:54*/a3.func_184538_a((Entity)v3, v3.field_70125_A, v3.field_70177_z, -20.0f, 0.5f, 1.0f);
            /*SL:55*/v2.func_72838_d((Entity)a3);
        }
        /*SL:58*/v3.func_71029_a(StatList.func_188057_b((Item)this));
        /*SL:59*/return (ActionResult<ItemStack>)new ActionResult(EnumActionResult.SUCCESS, (Object)v5);
    }
    
    public DropProcessor getDropProcessor() {
        /*SL:64*/return this.dropProcessor;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean func_77636_d(final ItemStack a1) {
        /*SL:71*/return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void func_77624_a(final ItemStack a1, final EntityPlayer a2, final List a3, final boolean a4) {
        final int v1 = /*EL:78*/ItemLuckyBlock.getLuck(a1);
        final String[] v2 = /*EL:79*/ItemLuckyBlock.getDrops(a1);
        /*SL:80*/a3.add(I18n.func_74838_a("item.luckyBlock.luck") + ": " + ((v1 == 0) ? TextFormatting.GOLD : ((v1 < 0) ? TextFormatting.RED : (TextFormatting.GREEN + "+"))) + String.valueOf(v1));
        /*SL:81*/if (v2 != null && v2.length != 0) {
            a3.add(TextFormatting.GRAY + "" + TextFormatting.ITALIC + I18n.func_74838_a("item.luckyBlock.customDrop"));
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void func_150895_a(final Item v-6, final CreativeTabs v-5, final NonNullList<ItemStack> v-4) {
        final ItemStack itemStack = /*EL:88*/new ItemStack(v-6, 1, 0);
        /*SL:89*/v-4.add((Object)itemStack);
        /*SL:91*/if (((ResourceLocation)Item.field_150901_e.func_177774_c((Object)this)).toString().equals("lucky:lucky_potion")) {
            final NBTTagCompound a1 = /*EL:93*/new NBTTagCompound();
            /*SL:94*/a1.func_74768_a("Luck", 100);
            final NBTTagCompound a2 = /*EL:96*/new NBTTagCompound();
            /*SL:97*/a2.func_74768_a("Luck", -100);
            final ItemStack a3 = /*EL:99*/new ItemStack(v-6, 1, 0);
            /*SL:100*/a3.func_77982_d(a1);
            /*SL:101*/a3.func_151001_c("Good Lucky Potion");
            /*SL:102*/v-4.add((Object)a3);
            final ItemStack v1 = /*EL:104*/new ItemStack(v-6, 1, 0);
            /*SL:105*/v1.func_77982_d(a2);
            /*SL:106*/v1.func_151001_c("Bad Lucky Potion");
            /*SL:107*/v-4.add((Object)v1);
        }
    }
    
    public LuckyCrafting getCrafting() {
        /*SL:113*/return this.crafting;
    }
}
