package mod.lucky.item;

import mod.lucky.util.LuckyFunction;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.NonNullList;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.client.util.ITooltipFlag;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.world.World;
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
        final int v1 = getLuck(/*EL:32*/a1);
        final String[] v2 = getDrops(/*EL:33*/a1);
        /*SL:34*/if (v1 == 0 && (v2 == null || v2.length == 0)) {
            return 64;
        }
        /*SL:35*/return 1;
    }
    
    @SideOnly(Side.CLIENT)
    public void func_77624_a(final ItemStack a1, @Nullable final World a2, final List<String> a3, final ITooltipFlag a4) {
        final int v1 = getLuck(/*EL:43*/a1);
        final String[] v2 = getDrops(/*EL:44*/a1);
        /*SL:45*/a3.add(I18n.func_74838_a("item.luckyBlock.luck") + ": " + ((v1 == 0) ? TextFormatting.GOLD : ((v1 < 0) ? TextFormatting.RED : (TextFormatting.GREEN + "+"))) + String.valueOf(v1));
        /*SL:46*/if (v2 != null && v2.length != 0) {
            a3.add(TextFormatting.GRAY + "" + TextFormatting.ITALIC + I18n.func_74838_a("item.luckyBlock.customDrop"));
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void func_150895_a(final CreativeTabs v-4, final NonNullList<ItemStack> v-3) {
        /*SL:53*/if (!this.func_194125_a(v-4)) {
            return;
        }
        final ItemStack itemStack = /*EL:54*/new ItemStack((Item)this, 1, 0);
        /*SL:55*/v-3.add((Object)itemStack);
        /*SL:57*/if (((ResourceLocation)Block.field_149771_c.func_177774_c((Object)this.func_179223_d())).toString().equals("lucky:lucky_block")) {
            final NBTTagCompound a1 = /*EL:59*/new NBTTagCompound();
            /*SL:60*/a1.func_74768_a("Luck", 80);
            final NBTTagCompound a2 = /*EL:62*/new NBTTagCompound();
            /*SL:63*/a2.func_74768_a("Luck", -80);
            final ItemStack v1 = /*EL:65*/new ItemStack((Item)this, 1, 0);
            /*SL:66*/v1.func_77982_d(a1);
            /*SL:67*/v1.func_151001_c("Very Lucky Block");
            /*SL:68*/v-3.add((Object)v1);
            final ItemStack v2 = /*EL:70*/new ItemStack((Item)this, 1, 0);
            /*SL:71*/v2.func_77982_d(a2);
            /*SL:72*/v2.func_151001_c("Unlucky Block");
            /*SL:73*/v-3.add((Object)v2);
        }
    }
    
    public static int getLuck(final ItemStack v1) {
        try {
            /*SL:81*/return v1.func_77978_p().func_74762_e("Luck");
        }
        catch (NullPointerException a1) {
            /*SL:85*/return 0;
        }
    }
    
    public static String[] getDrops(final ItemStack v1) {
        try {
            /*SL:93*/return LuckyFunction.getStringArrayFromNBTTagList((NBTTagList)v1.func_77978_p().func_74781_a("Drops"));
        }
        catch (Exception a1) {
            /*SL:97*/return null;
        }
    }
}
