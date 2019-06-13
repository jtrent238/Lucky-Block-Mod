package mod.lucky.item;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.NonNullList;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.client.util.ITooltipFlag;
import java.util.List;
import javax.annotation.Nullable;
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
        final ItemStack v5 = /*EL:38*/v3.func_184586_b(v4);
        /*SL:39*/if (!v3.field_71075_bZ.field_75098_d) {
            /*SL:42*/v5.func_190920_e(v5.func_190916_E() - 1);
        }
        /*SL:45*/v2.func_184148_a((EntityPlayer)null, v3.field_70165_t, v3.field_70163_u, v3.field_70161_v, SoundEvents.field_187827_fP, SoundCategory.NEUTRAL, 0.5f, 0.4f / (ItemLuckyPotion.field_77697_d.nextFloat() * 0.4f + 0.8f));
        /*SL:47*/if (!v2.field_72995_K) {
            final int a1 = /*EL:49*/ItemLuckyBlock.getLuck(v5);
            final String[] a2 = /*EL:50*/ItemLuckyBlock.getDrops(v5);
            final EntityLuckyPotion a3 = /*EL:51*/new EntityLuckyPotion(v2, (EntityLivingBase)v3, this, this.dropProcessor, a1, a2);
            /*SL:52*/a3.func_184538_a((Entity)v3, v3.field_70125_A, v3.field_70177_z, -20.0f, 0.5f, 1.0f);
            /*SL:53*/v2.func_72838_d((Entity)a3);
        }
        /*SL:56*/v3.func_71029_a(StatList.func_188057_b((Item)this));
        /*SL:57*/return (ActionResult<ItemStack>)new ActionResult(EnumActionResult.SUCCESS, (Object)v5);
    }
    
    public DropProcessor getDropProcessor() {
        /*SL:62*/return this.dropProcessor;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean func_77636_d(final ItemStack a1) {
        /*SL:69*/return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void func_77624_a(final ItemStack a1, @Nullable final World a2, final List<String> a3, final ITooltipFlag a4) {
        final int v1 = /*EL:76*/ItemLuckyBlock.getLuck(a1);
        final String[] v2 = /*EL:77*/ItemLuckyBlock.getDrops(a1);
        /*SL:78*/a3.add(I18n.func_74838_a("item.luckyBlock.luck") + ": " + ((v1 == 0) ? TextFormatting.GOLD : ((v1 < 0) ? TextFormatting.RED : (TextFormatting.GREEN + "+"))) + String.valueOf(v1));
        /*SL:79*/if (v2 != null && v2.length != 0) {
            a3.add(TextFormatting.GRAY + "" + TextFormatting.ITALIC + I18n.func_74838_a("item.luckyBlock.customDrop"));
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void func_150895_a(final CreativeTabs v-4, final NonNullList<ItemStack> v-3) {
        /*SL:86*/if (!this.func_194125_a(v-4)) {
            return;
        }
        final ItemStack itemStack = /*EL:87*/new ItemStack((Item)this, 1, 0);
        /*SL:88*/v-3.add((Object)itemStack);
        /*SL:90*/if (((ResourceLocation)Item.field_150901_e.func_177774_c((Object)this)).toString().equals("lucky:lucky_potion")) {
            final NBTTagCompound a1 = /*EL:92*/new NBTTagCompound();
            /*SL:93*/a1.func_74768_a("Luck", 100);
            final NBTTagCompound a2 = /*EL:95*/new NBTTagCompound();
            /*SL:96*/a2.func_74768_a("Luck", -100);
            final ItemStack v1 = /*EL:98*/new ItemStack((Item)this, 1, 0);
            /*SL:99*/v1.func_77982_d(a1);
            /*SL:100*/v1.func_151001_c("Good Lucky Potion");
            /*SL:101*/v-3.add((Object)v1);
            final ItemStack v2 = /*EL:103*/new ItemStack((Item)this, 1, 0);
            /*SL:104*/v2.func_77982_d(a2);
            /*SL:105*/v2.func_151001_c("Bad Lucky Potion");
            /*SL:106*/v-3.add((Object)v2);
        }
    }
    
    public LuckyCrafting getCrafting() {
        /*SL:112*/return this.crafting;
    }
}
