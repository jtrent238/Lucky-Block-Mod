package mod.lucky.item;

import mod.lucky.util.LuckyFunction;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.NonNullList;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
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
        final int v1 = getLuck(/*EL:31*/a1);
        final String[] v2 = getDrops(/*EL:32*/a1);
        /*SL:33*/if (v1 == 0 && (v2 == null || v2.length == 0)) {
            return 64;
        }
        /*SL:34*/return 1;
    }
    
    @SideOnly(Side.CLIENT)
    public void func_77624_a(final ItemStack a1, final EntityPlayer a2, final List a3, final boolean a4) {
        final int v1 = getLuck(/*EL:42*/a1);
        final String[] v2 = getDrops(/*EL:43*/a1);
        /*SL:44*/a3.add(I18n.func_74838_a("item.luckyBlock.luck") + ": " + ((v1 == 0) ? TextFormatting.GOLD : ((v1 < 0) ? TextFormatting.RED : (TextFormatting.GREEN + "+"))) + String.valueOf(v1));
        /*SL:45*/if (v2 != null && v2.length != 0) {
            a3.add(TextFormatting.GRAY + "" + TextFormatting.ITALIC + I18n.func_74838_a("item.luckyBlock.customDrop"));
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void func_150895_a(final Item v-6, final CreativeTabs v-5, final NonNullList<ItemStack> v-4) {
        final ItemStack itemStack = /*EL:52*/new ItemStack(v-6, 1, 0);
        /*SL:53*/v-4.add((Object)itemStack);
        /*SL:55*/if (((ResourceLocation)Block.field_149771_c.func_177774_c((Object)this.func_179223_d())).toString().equals("lucky:lucky_block")) {
            final NBTTagCompound a1 = /*EL:57*/new NBTTagCompound();
            /*SL:58*/a1.func_74768_a("Luck", 80);
            final NBTTagCompound a2 = /*EL:60*/new NBTTagCompound();
            /*SL:61*/a2.func_74768_a("Luck", -80);
            final ItemStack a3 = /*EL:63*/new ItemStack(v-6, 1, 0);
            /*SL:64*/a3.func_77982_d(a1);
            /*SL:65*/a3.func_151001_c("Very Lucky Block");
            /*SL:66*/v-4.add((Object)a3);
            final ItemStack v1 = /*EL:68*/new ItemStack(v-6, 1, 0);
            /*SL:69*/v1.func_77982_d(a2);
            /*SL:70*/v1.func_151001_c("Unlucky Block");
            /*SL:71*/v-4.add((Object)v1);
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
