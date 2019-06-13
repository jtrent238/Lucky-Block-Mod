package mod.lucky.item;

import mod.lucky.util.LuckyFunction;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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
        this.func_77627_a(false);
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
    public void func_150895_a(final Item v-7, final CreativeTabs v-6, final List v-5) {
        final ItemStack itemStack = /*EL:51*/new ItemStack(v-7, 1, 0);
        /*SL:52*/v-5.add(itemStack);
        final String string = Block.field_149771_c.func_177774_c(/*EL:54*/(Object)this.func_179223_d()).toString();
        /*SL:55*/if (Block.field_149771_c.func_177774_c((Object)this.func_179223_d()).toString().equals("lucky:lucky_block")) {
            final NBTTagCompound a1 = /*EL:57*/new NBTTagCompound();
            /*SL:58*/a1.func_74768_a("Luck", 80);
            final NBTTagCompound a2 = /*EL:60*/new NBTTagCompound();
            /*SL:61*/a2.func_74768_a("Luck", -80);
            final ItemStack a3 = /*EL:63*/new ItemStack(v-7, 1, 0);
            /*SL:64*/a3.func_77982_d(a1);
            /*SL:65*/a3.func_151001_c("Very Lucky Block");
            /*SL:66*/v-5.add(a3);
            final ItemStack v1 = /*EL:68*/new ItemStack(v-7, 1, 0);
            /*SL:69*/v1.func_77982_d(a2);
            /*SL:70*/v1.func_151001_c("Unlucky Block");
            /*SL:71*/v-5.add(v1);
        }
    }
    
    public static int getLuck(final ItemStack v1) {
        try {
            /*SL:79*/return v1.func_77978_p().func_74762_e("Luck");
        }
        catch (NullPointerException a1) {
            /*SL:83*/return 0;
        }
    }
    
    public static String[] getDrops(final ItemStack v1) {
        try {
            /*SL:91*/return LuckyFunction.getStringArrayFromNBTTagList((NBTTagList)v1.func_77978_p().func_74781_a("Drops"));
        }
        catch (NullPointerException a1) {
            /*SL:95*/return null;
        }
    }
}
