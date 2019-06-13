package mod.lucky.crafting;

import net.minecraftforge.common.ForgeHooks;
import net.minecraft.nbt.NBTTagCompound;
import mod.lucky.item.ItemLuckyBlock;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.item.crafting.IRecipe;

public class LuckyCrafting implements IRecipe
{
    private Block block;
    private ArrayList<CraftingLuckModifier> luckModifiers;
    private ItemStack resultItemStack;
    
    public LuckyCrafting(final Block a1) {
        this.block = a1;
        this.luckModifiers = new ArrayList<CraftingLuckModifier>();
    }
    
    public boolean func_77569_a(final InventoryCrafting v-8, final World v-7) {
        int luck = /*EL:30*/0;
        int n = /*EL:31*/0;
        boolean b = /*EL:32*/false;
        ItemStack itemStack = /*EL:33*/null;
        /*SL:35*/for (int i = 0; i < v-8.func_70302_i_(); ++i) {
            final ItemStack func_70301_a = /*EL:37*/v-8.func_70301_a(i);
            /*SL:39*/if (func_70301_a != null) {
                /*SL:43*/if (func_70301_a.func_77973_b() == Item.func_150898_a(this.block)) {
                    /*SL:45*/if (itemStack != null) {
                        return false;
                    }
                    /*SL:46*/luck = ItemLuckyBlock.getLuck(func_70301_a);
                    /*SL:47*/itemStack = func_70301_a;
                }
                else {
                    boolean a2 = /*EL:51*/false;
                    int v1 = /*EL:52*/0;
                    /*SL:53*/for (a2 = 0; a2 < this.luckModifiers.size(); ++a2) {
                        /*SL:55*/if (func_70301_a.func_77973_b() == this.luckModifiers.get(a2).getItem() && (func_70301_a.func_77952_i() == this.luckModifiers.get(a2).getDamage() || this.luckModifiers.get(a2).getDamage() == -1)) {
                            /*SL:57*/a2 = true;
                            /*SL:58*/v1 = this.luckModifiers.get(a2).getLuckValue();
                        }
                    }
                    /*SL:62*/if (!a2) {
                        return false;
                    }
                    /*SL:63*/b = true;
                    /*SL:65*/n += v1;
                }
            }
        }
        /*SL:68*/if (itemStack == null || !b) {
            return false;
        }
        int i = /*EL:70*/luck + n;
        /*SL:71*/if (i > 100) {
            i = 100;
        }
        /*SL:72*/if (i < -100) {
            i = -100;
        }
        /*SL:73*/if (luck == 100 && i == 100) {
            return false;
        }
        /*SL:74*/if (luck == -100 && i == -100) {
            return false;
        }
        /*SL:76*/this.resultItemStack = itemStack.func_77946_l();
        /*SL:77*/this.resultItemStack.field_77994_a = 1;
        /*SL:79*/if (i != 0) {
            final NBTTagCompound func_77978_p = /*EL:81*/this.resultItemStack.func_77978_p();
            /*SL:82*/if (this.resultItemStack.func_77978_p() == null) {
                this.resultItemStack.func_77982_d(new NBTTagCompound());
            }
            /*SL:83*/this.resultItemStack.func_77978_p().func_74768_a("Luck", i);
        }
        else {
            /*SL:85*/this.resultItemStack.func_77982_d((NBTTagCompound)null);
        }
        /*SL:87*/return true;
    }
    
    public ItemStack func_77572_b(final InventoryCrafting a1) {
        /*SL:93*/return this.resultItemStack.func_77946_l();
    }
    
    public int func_77570_a() {
        /*SL:99*/return 10;
    }
    
    public ItemStack func_77571_b() {
        /*SL:105*/return this.resultItemStack;
    }
    
    public ItemStack[] func_179532_b(final InventoryCrafting v-1) {
        final ItemStack[] v0 = /*EL:111*/new ItemStack[v-1.func_70302_i_()];
        /*SL:113*/for (int v = 0; v < v0.length; ++v) {
            final ItemStack a1 = /*EL:115*/v-1.func_70301_a(v);
            /*SL:116*/v0[v] = ForgeHooks.getContainerItem(a1);
        }
        /*SL:119*/return v0;
    }
    
    public void addLuckModifier(final CraftingLuckModifier a1) {
        /*SL:124*/this.luckModifiers.add(a1);
    }
}
