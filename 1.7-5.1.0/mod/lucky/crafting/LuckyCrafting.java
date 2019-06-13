package mod.lucky.crafting;

import java.util.List;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import net.minecraft.nbt.NBTTagCompound;
import mod.lucky.item.ItemLuckyBlock;
import mod.lucky.Lucky;
import net.minecraft.world.World;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;

public class LuckyCrafting implements IRecipe
{
    public String luckyBlockRecipe;
    public Item[] luckyCraftingItems;
    public int[] luckyCraftingItemDamage;
    public int[] luckyCraftingLevels;
    private int resultLuckLevel;
    private ItemStack resultItemStack;
    
    public LuckyCrafting() {
        this.luckyBlockRecipe = "";
        this.luckyCraftingItems = new Item[1];
        this.luckyCraftingItemDamage = new int[1];
        this.luckyCraftingLevels = new int[1];
    }
    
    public boolean func_77569_a(final InventoryCrafting v-9, final World v-8) {
        int luck = /*EL:30*/0;
        int n = /*EL:31*/0;
        boolean b = /*EL:32*/false;
        boolean b2 = /*EL:33*/false;
        ItemStack func_77946_l = /*EL:34*/null;
        /*SL:36*/for (int i = 0; i < v-9.func_70302_i_(); ++i) {
            final ItemStack func_70301_a = /*EL:38*/v-9.func_70301_a(i);
            /*SL:40*/if (func_70301_a != null) {
                /*SL:44*/if (func_70301_a.func_77973_b() == Item.func_150898_a(Lucky.lucky_block)) {
                    /*SL:46*/if (b) {
                        return false;
                    }
                    /*SL:47*/luck = ItemLuckyBlock.getLuck(func_70301_a);
                    /*SL:48*/func_77946_l = func_70301_a.func_77946_l();
                    /*SL:49*/b = true;
                }
                else {
                    boolean a2 = /*EL:53*/false;
                    int v1 = /*EL:54*/0;
                    /*SL:55*/for (a2 = 0; a2 < this.luckyCraftingItems.length; ++a2) {
                        /*SL:57*/if (func_70301_a.func_77973_b() == this.luckyCraftingItems[a2] && (func_70301_a.func_77960_j() == this.luckyCraftingItemDamage[a2] || this.luckyCraftingItemDamage[a2] == -1)) {
                            /*SL:59*/a2 = true;
                            /*SL:60*/v1 = this.luckyCraftingLevels[a2];
                        }
                    }
                    /*SL:64*/if (!a2) {
                        return false;
                    }
                    /*SL:65*/b2 = true;
                    /*SL:67*/n += v1;
                }
            }
        }
        /*SL:70*/if (!b || !b2 || func_77946_l == null) {
            return false;
        }
        /*SL:72*/func_77946_l.field_77994_a = 1;
        /*SL:74*/this.resultLuckLevel = luck + n;
        /*SL:75*/if (this.resultLuckLevel > 100) {
            this.resultLuckLevel = 100;
        }
        /*SL:76*/if (this.resultLuckLevel < -100) {
            this.resultLuckLevel = -100;
        }
        /*SL:78*/this.resultItemStack = func_77946_l;
        /*SL:80*/if (this.resultLuckLevel != 0) {
            NBTTagCompound func_77978_p = /*EL:82*/func_77946_l.func_77978_p();
            /*SL:83*/if (func_77978_p == null) {
                func_77978_p = new NBTTagCompound();
            }
            /*SL:84*/func_77978_p.func_74768_a("Luck", this.resultLuckLevel);
            /*SL:85*/this.resultItemStack.func_77982_d(func_77978_p);
        }
        /*SL:88*/return true;
    }
    
    public ItemStack func_77572_b(final InventoryCrafting a1) {
        /*SL:94*/return this.resultItemStack.func_77946_l();
    }
    
    public int func_77570_a() {
        /*SL:100*/return 10;
    }
    
    public ItemStack func_77571_b() {
        /*SL:106*/return this.resultItemStack;
    }
    
    public void setLuckyBlockRecipe(final String a1) {
        /*SL:111*/this.luckyBlockRecipe = a1;
    }
    
    public void makeLuckyBlockRecipe() {
        try {
            final String[] split = /*EL:118*/this.luckyBlockRecipe.split(",");
            final List<Object> list = /*EL:119*/new ArrayList<Object>();
            /*SL:121*/for (final String v0 : split) {
                Label_0112: {
                    try {
                        final Item v = (Item)Item.field_150901_e.func_82594_a(/*EL:125*/v0);
                        /*SL:126*/if (v != null) {
                            /*SL:128*/list.add(v);
                            break Label_0112;
                        }
                    }
                    catch (Exception ex) {}
                    /*SL:136*/if (v0.length() == 1) {
                        /*SL:138*/list.add(v0.charAt(0));
                    }
                    else {
                        /*SL:142*/list.add(v0);
                    }
                }
            }
            final ItemStack itemStack = /*EL:145*/new ItemStack(Lucky.lucky_block);
            /*SL:146*/GameRegistry.addRecipe(itemStack, list.toArray());
        }
        catch (Exception ex2) {}
    }
}
