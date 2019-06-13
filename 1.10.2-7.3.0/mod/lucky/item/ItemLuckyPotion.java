package mod.lucky.item;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
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
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
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
    
    public ActionResult<ItemStack> func_77659_a(final ItemStack v1, final World v2, final EntityPlayer v3, final EnumHand v4) {
        /*SL:40*/if (!v3.field_71075_bZ.field_75098_d) {
            /*SL:42*/--v1.field_77994_a;
        }
        /*SL:45*/v2.func_184148_a((EntityPlayer)null, v3.field_70165_t, v3.field_70163_u, v3.field_70161_v, SoundEvents.field_187827_fP, SoundCategory.NEUTRAL, 0.5f, 0.4f / (ItemLuckyPotion.field_77697_d.nextFloat() * 0.4f + 0.8f));
        /*SL:47*/if (!v2.field_72995_K) {
            final int a1 = /*EL:49*/ItemLuckyBlock.getLuck(v1);
            final String[] a2 = /*EL:50*/ItemLuckyBlock.getDrops(v1);
            final EntityLuckyPotion a3 = /*EL:51*/new EntityLuckyPotion(v2, (EntityLivingBase)v3, this, this.dropProcessor, a1, a2);
            /*SL:52*/a3.func_184538_a((Entity)v3, v3.field_70125_A, v3.field_70177_z, -20.0f, 0.5f, 1.0f);
            /*SL:53*/v2.func_72838_d((Entity)a3);
        }
        /*SL:56*/v3.func_71029_a(StatList.func_188057_b((Item)this));
        /*SL:57*/return (ActionResult<ItemStack>)new ActionResult(EnumActionResult.SUCCESS, (Object)v1);
    }
    
    public DropProcessor getDropProcessor() {
        /*SL:62*/return this.dropProcessor;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean func_77636_d(final ItemStack a1) {
        /*SL:69*/return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void func_77624_a(final ItemStack a1, final EntityPlayer a2, final List a3, final boolean a4) {
        final int v1 = /*EL:76*/ItemLuckyBlock.getLuck(a1);
        final String[] v2 = /*EL:77*/ItemLuckyBlock.getDrops(a1);
        /*SL:78*/a3.add(I18n.func_74838_a("item.luckyBlock.luck") + ": " + ((v1 == 0) ? TextFormatting.GOLD : ((v1 < 0) ? TextFormatting.RED : (TextFormatting.GREEN + "+"))) + String.valueOf(v1));
        /*SL:79*/if (v2 != null && v2.length != 0) {
            a3.add(TextFormatting.GRAY + "" + TextFormatting.ITALIC + I18n.func_74838_a("item.luckyBlock.customDrop"));
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void func_150895_a(final Item v-6, final CreativeTabs v-5, final List v-4) {
        final ItemStack itemStack = /*EL:86*/new ItemStack(v-6, 1, 0);
        /*SL:87*/v-4.add(itemStack);
        /*SL:89*/if (((ResourceLocation)Item.field_150901_e.func_177774_c((Object)this)).toString().equals("lucky:lucky_potion")) {
            final NBTTagCompound a1 = /*EL:91*/new NBTTagCompound();
            /*SL:92*/a1.func_74768_a("Luck", 100);
            final NBTTagCompound a2 = /*EL:94*/new NBTTagCompound();
            /*SL:95*/a2.func_74768_a("Luck", -100);
            final ItemStack a3 = /*EL:97*/new ItemStack(v-6, 1, 0);
            /*SL:98*/a3.func_77982_d(a1);
            /*SL:99*/a3.func_151001_c("Good Lucky Potion");
            /*SL:100*/v-4.add(a3);
            final ItemStack v1 = /*EL:102*/new ItemStack(v-6, 1, 0);
            /*SL:103*/v1.func_77982_d(a2);
            /*SL:104*/v1.func_151001_c("Bad Lucky Potion");
            /*SL:105*/v-4.add(v1);
        }
    }
    
    public LuckyCrafting getCrafting() {
        /*SL:111*/return this.crafting;
    }
}
