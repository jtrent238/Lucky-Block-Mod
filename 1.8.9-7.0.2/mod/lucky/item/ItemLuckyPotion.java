package mod.lucky.item;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import java.util.List;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import mod.lucky.entity.EntityLuckyPotion;
import net.minecraft.entity.Entity;
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
    
    public ItemStack func_77659_a(final ItemStack v1, final World v2, final EntityPlayer v3) {
        /*SL:33*/if (!v3.field_71075_bZ.field_75098_d) {
            /*SL:35*/--v1.field_77994_a;
        }
        /*SL:38*/v2.func_72956_a((Entity)v3, "random.bow", 0.5f, 0.4f / (ItemLuckyPotion.field_77697_d.nextFloat() * 0.4f + 0.8f));
        /*SL:40*/if (!v2.field_72995_K) {
            final int a1 = /*EL:42*/ItemLuckyBlock.getLuck(v1);
            final String[] a2 = /*EL:43*/ItemLuckyBlock.getDrops(v1);
            /*SL:44*/v2.func_72838_d((Entity)new EntityLuckyPotion(v2, (EntityLivingBase)v3, this, this.dropProcessor, a1, a2));
        }
        /*SL:47*/return v1;
    }
    
    public DropProcessor getDropProcessor() {
        /*SL:52*/return this.dropProcessor;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean func_77636_d(final ItemStack a1) {
        /*SL:59*/return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void func_77624_a(final ItemStack a1, final EntityPlayer a2, final List a3, final boolean a4) {
        final int v1 = /*EL:66*/ItemLuckyBlock.getLuck(a1);
        final String[] v2 = /*EL:67*/ItemLuckyBlock.getDrops(a1);
        /*SL:68*/a3.add(StatCollector.func_74838_a("item.luckyBlock.luck") + ": " + ((v1 == 0) ? EnumChatFormatting.GOLD : ((v1 < 0) ? EnumChatFormatting.RED : (EnumChatFormatting.GREEN + "+"))) + String.valueOf(v1));
        /*SL:69*/if (v2 != null && v2.length != 0) {
            a3.add(EnumChatFormatting.GRAY + "" + EnumChatFormatting.ITALIC + StatCollector.func_74838_a("item.luckyBlock.customDrop"));
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void func_150895_a(final Item v-6, final CreativeTabs v-5, final List v-4) {
        final ItemStack itemStack = /*EL:76*/new ItemStack(v-6, 1, 0);
        /*SL:77*/v-4.add(itemStack);
        /*SL:79*/if (((ResourceLocation)Item.field_150901_e.func_177774_c((Object)this)).toString().equals("lucky:lucky_potion")) {
            final NBTTagCompound a1 = /*EL:81*/new NBTTagCompound();
            /*SL:82*/a1.func_74768_a("Luck", 100);
            final NBTTagCompound a2 = /*EL:84*/new NBTTagCompound();
            /*SL:85*/a2.func_74768_a("Luck", -100);
            final ItemStack a3 = /*EL:87*/new ItemStack(v-6, 1, 0);
            /*SL:88*/a3.func_77982_d(a1);
            /*SL:89*/a3.func_151001_c("Good Lucky Potion");
            /*SL:90*/v-4.add(a3);
            final ItemStack v1 = /*EL:92*/new ItemStack(v-6, 1, 0);
            /*SL:93*/v1.func_77982_d(a2);
            /*SL:94*/v1.func_151001_c("Bad Lucky Potion");
            /*SL:95*/v-4.add(v1);
        }
    }
    
    public LuckyCrafting getCrafting() {
        /*SL:101*/return this.crafting;
    }
}
