package mod.lucky.item;

import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.client.util.ITooltipFlag;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import mod.lucky.drop.func.DropProcessData;
import mod.lucky.util.LuckyFunction;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import mod.lucky.crafting.LuckyCrafting;
import mod.lucky.drop.func.DropProcessor;
import net.minecraft.item.ItemSword;

public class ItemLuckySword extends ItemSword
{
    private DropProcessor dropProcessor;
    private LuckyCrafting crafting;
    
    public ItemLuckySword() {
        super(Item.ToolMaterial.IRON);
        this.func_77656_e(3124);
        this.dropProcessor = new DropProcessor();
        this.crafting = new LuckyCrafting((Item)this);
    }
    
    public int func_77626_a(final ItemStack a1) {
        /*SL:33*/return 7200;
    }
    
    public boolean func_77644_a(final ItemStack v2, final EntityLivingBase v3, final EntityLivingBase v4) {
        try {
            final int a1 = /*EL:40*/ItemLuckyBlock.getLuck(v2);
            final String[] a2 = /*EL:41*/ItemLuckyBlock.getDrops(v2);
            /*SL:43*/if (a2 != null && a2.length != 0) {
                /*SL:44*/this.getDropProcessor().processRandomDrop(/*EL:46*/LuckyFunction.getDropsFromStringArray(a2), new DropProcessData(v4.func_130014_f_(), /*EL:47*/(Entity)v4, v3.func_174791_d()).setHitEntity((Entity)v3), /*EL:48*/a1);
            }
            else {
                /*SL:51*/this.getDropProcessor().processRandomDrop(new DropProcessData(v4.func_130014_f_(), /*EL:53*/(Entity)v4, v3.func_174791_d()).setHitEntity((Entity)v3), /*EL:54*/a1);
            }
        }
        catch (Exception a3) {
            System.err.println(/*EL:57*/"The Lucky Sword encountered and error while trying to perform a function. Error report below:");
            /*SL:59*/a3.printStackTrace();
        }
        /*SL:62*/return super.func_77644_a(v2, v3, v4);
    }
    
    public DropProcessor getDropProcessor() {
        /*SL:66*/return this.dropProcessor;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean func_77636_d(final ItemStack a1) {
        /*SL:72*/return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void func_77624_a(final ItemStack a1, @Nullable final World a2, final List<String> a3, final ITooltipFlag a4) {
        final int v1 = /*EL:79*/ItemLuckyBlock.getLuck(a1);
        final String[] v2 = /*EL:80*/ItemLuckyBlock.getDrops(a1);
        /*SL:81*/a3.add(/*EL:82*/I18n.func_74838_a("item.luckyBlock.luck") + ": " + ((v1 == 0) ? TextFormatting.GOLD : ((v1 < 0) ? TextFormatting.RED : (TextFormatting.GREEN + "+"))) + /*EL:87*/String.valueOf(v1));
        /*SL:88*/if (v2 != null && v2.length != 0) {
            /*SL:89*/a3.add(TextFormatting.GRAY + "" + TextFormatting.ITALIC + /*EL:93*/I18n.func_74838_a("item.luckyBlock.customDrop"));
        }
    }
    
    public LuckyCrafting getCrafting() {
        /*SL:97*/return this.crafting;
    }
}
