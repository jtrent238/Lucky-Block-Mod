package mod.lucky.item;

import mod.lucky.util.LuckyFunction;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemLuckyBlock extends ItemBlock
{
    public ItemLuckyBlock(final Block a1) {
        super(a1);
        this.func_77627_a(true);
    }
    
    public int getItemStackLimit(final ItemStack a1) {
        final int v1 = getLuck(/*EL:30*/a1);
        final String[] v2 = getDrops(/*EL:31*/a1);
        /*SL:32*/if (v1 == 0 && (v2 == null || v2.length == 0)) {
            return 64;
        }
        /*SL:33*/return 1;
    }
    
    @SideOnly(Side.CLIENT)
    public void func_77624_a(final ItemStack a1, final EntityPlayer a2, final List a3, final boolean a4) {
        final int v1 = getLuck(/*EL:41*/a1);
        final String[] v2 = getDrops(/*EL:42*/a1);
        /*SL:43*/a3.add(StatCollector.func_74838_a("item.luckyBlock.luck") + ": " + ((v1 == 0) ? EnumChatFormatting.GOLD : ((v1 < 0) ? EnumChatFormatting.RED : (EnumChatFormatting.GREEN + "+"))) + String.valueOf(v1));
        /*SL:44*/if (v2 != null && v2.length != 0) {
            a3.add(EnumChatFormatting.GRAY + "" + EnumChatFormatting.ITALIC + StatCollector.func_74838_a("item.luckyBlock.customDrop"));
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void func_150895_a(final Item a1, final CreativeTabs a2, final List a3) {
        final NBTTagCompound v1 = /*EL:51*/new NBTTagCompound();
        /*SL:52*/v1.func_74768_a("Luck", 80);
        final NBTTagCompound v2 = /*EL:54*/new NBTTagCompound();
        /*SL:55*/v2.func_74768_a("Luck", -80);
        final ItemStack v3 = /*EL:57*/new ItemStack(a1, 1, 0);
        /*SL:58*/a3.add(v3);
        final ItemStack v4 = /*EL:60*/new ItemStack(a1, 1, 0);
        /*SL:61*/v4.func_77982_d(v1);
        /*SL:62*/v4.func_151001_c("Very Lucky Block");
        /*SL:63*/a3.add(v4);
        final ItemStack v5 = /*EL:65*/new ItemStack(a1, 1, 0);
        /*SL:66*/v5.func_77982_d(v2);
        /*SL:67*/v5.func_151001_c("Unlucky Block");
        /*SL:68*/a3.add(v5);
    }
    
    public static int getLuck(final ItemStack v1) {
        try {
            /*SL:75*/return v1.func_77978_p().func_74762_e("Luck");
        }
        catch (NullPointerException a1) {
            /*SL:79*/return 0;
        }
    }
    
    public static String[] getDrops(final ItemStack v1) {
        try {
            /*SL:87*/return LuckyFunction.getArrayFromNBTTagList((NBTTagList)v1.func_77978_p().func_74781_a("Drops"));
        }
        catch (NullPointerException a1) {
            /*SL:91*/return null;
        }
    }
}
