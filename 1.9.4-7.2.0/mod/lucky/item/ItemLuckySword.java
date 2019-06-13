package mod.lucky.item;

import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
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
        /*SL:35*/return 7200;
    }
    
    public boolean func_77644_a(final ItemStack v2, final EntityLivingBase v3, final EntityLivingBase v4) {
        try {
            final int a1 = /*EL:43*/ItemLuckyBlock.getLuck(v2);
            final String[] a2 = /*EL:44*/ItemLuckyBlock.getDrops(v2);
            /*SL:46*/if (a2 != null && a2.length != 0) {
                this.getDropProcessor().processRandomDrop(LuckyFunction.getDropsFromStringArray(a2), new DropProcessData(v4.func_130014_f_(), (Entity)v4, v3.func_174791_d()).setHitEntity((Entity)v3), a1);
            }
            else {
                /*SL:47*/this.getDropProcessor().processRandomDrop(new DropProcessData(v4.func_130014_f_(), (Entity)v4, v3.func_174791_d()).setHitEntity((Entity)v3), a1);
            }
        }
        catch (Exception a3) {
            System.err.println(/*EL:51*/"The Lucky Sword encountered and error while trying to perform a function. Error report below:");
            /*SL:52*/a3.printStackTrace();
        }
        /*SL:55*/return super.func_77644_a(v2, v3, v4);
    }
    
    public DropProcessor getDropProcessor() {
        /*SL:60*/return this.dropProcessor;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean func_77636_d(final ItemStack a1) {
        /*SL:67*/return true;
    }
    
    @SideOnly(Side.CLIENT)
    public void func_77624_a(final ItemStack a1, final EntityPlayer a2, final List a3, final boolean a4) {
        final int v1 = /*EL:74*/ItemLuckyBlock.getLuck(a1);
        final String[] v2 = /*EL:75*/ItemLuckyBlock.getDrops(a1);
        /*SL:76*/a3.add(I18n.func_74838_a("item.luckyBlock.luck") + ": " + ((v1 == 0) ? TextFormatting.GOLD : ((v1 < 0) ? TextFormatting.RED : (TextFormatting.GREEN + "+"))) + String.valueOf(v1));
        /*SL:77*/if (v2 != null && v2.length != 0) {
            a3.add(TextFormatting.GRAY + "" + TextFormatting.ITALIC + I18n.func_74838_a("item.luckyBlock.customDrop"));
        }
    }
    
    public LuckyCrafting getCrafting() {
        /*SL:82*/return this.crafting;
    }
}
