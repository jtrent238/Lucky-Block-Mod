package mod.lucky.crafting;

import net.minecraftforge.common.ForgeHooks;
import net.minecraft.nbt.NBTTagCompound;
import mod.lucky.item.ItemLuckyPotion;
import mod.lucky.item.ItemLuckyBlock;
import net.minecraft.world.World;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import java.util.ArrayList;
import net.minecraft.item.Item;
import net.minecraft.block.Block;
import net.minecraft.item.crafting.IRecipe;

public class LuckyCrafting implements IRecipe
{
    private Block luckCraftingBlock;
    private Item luckCraftingItem;
    private ArrayList<CraftingLuckModifier> luckModifiers;
    private ItemStack resultItemStack;
    
    public LuckyCrafting(final Block a1) {
        this.luckCraftingBlock = a1;
        this.luckModifiers = new ArrayList<CraftingLuckModifier>();
    }
    
    public LuckyCrafting(final Item a1) {
        this.luckCraftingItem = a1;
        this.luckModifiers = new ArrayList<CraftingLuckModifier>();
    }
    
    public boolean func_77569_a(final InventoryCrafting v-8, final World v-7) {
        int luck = /*EL:38*/0;
        int n = /*EL:39*/0;
        boolean b = /*EL:40*/false;
        ItemStack itemStack = /*EL:41*/null;
        /*SL:43*/for (int i = 0; i < v-8.func_70302_i_(); ++i) {
            final ItemStack func_70301_a = /*EL:45*/v-8.func_70301_a(i);
            /*SL:47*/if (func_70301_a != null) {
                Label_0103: {
                    /*SL:51*/if (this.luckCraftingItem != null) {
                        if (func_70301_a.func_77973_b() != this.luckCraftingItem) {
                            break Label_0103;
                        }
                    }
                    else if (this.luckCraftingBlock == null || func_70301_a.func_77973_b() != Item.func_150898_a(this.luckCraftingBlock)) {
                        break Label_0103;
                    }
                    /*SL:53*/if (itemStack != null) {
                        return false;
                    }
                    /*SL:54*/luck = ItemLuckyBlock.getLuck(func_70301_a);
                    /*SL:55*/itemStack = func_70301_a;
                    continue;
                }
                boolean a2 = /*EL:59*/false;
                int v1 = /*EL:60*/0;
                /*SL:61*/for (a2 = 0; a2 < this.luckModifiers.size(); ++a2) {
                    /*SL:63*/if (func_70301_a.func_77973_b() == this.luckModifiers.get(a2).getItem() && (func_70301_a.func_77952_i() == this.luckModifiers.get(a2).getDamage() || this.luckModifiers.get(a2).getDamage() == -1)) {
                        /*SL:65*/a2 = true;
                        /*SL:66*/v1 = this.luckModifiers.get(a2).getLuckValue();
                        /*SL:67*/if (this.luckCraftingItem instanceof ItemLuckyPotion) {
                            v1 *= 4;
                        }
                    }
                }
                /*SL:71*/if (!a2) {
                    return false;
                }
                /*SL:72*/b = true;
                /*SL:74*/n += v1;
            }
        }
        /*SL:77*/if (itemStack == null || !b) {
            return false;
        }
        int i = /*EL:79*/luck + n;
        /*SL:80*/if (i > 100) {
            i = 100;
        }
        /*SL:81*/if (i < -100) {
            i = -100;
        }
        /*SL:82*/if (luck == 100 && i == 100) {
            return false;
        }
        /*SL:83*/if (luck == -100 && i == -100) {
            return false;
        }
        /*SL:85*/this.resultItemStack = itemStack.func_77946_l();
        /*SL:86*/this.resultItemStack.field_77994_a = 1;
        /*SL:88*/if (i != 0) {
            final NBTTagCompound func_77978_p = /*EL:90*/this.resultItemStack.func_77978_p();
            /*SL:91*/if (this.resultItemStack.func_77978_p() == null) {
                this.resultItemStack.func_77982_d(new NBTTagCompound());
            }
            /*SL:92*/this.resultItemStack.func_77978_p().func_74768_a("Luck", i);
        }
        else {
            /*SL:94*/this.resultItemStack.func_77982_d((NBTTagCompound)null);
        }
        /*SL:96*/return true;
    }
    
    public ItemStack func_77572_b(final InventoryCrafting a1) {
        /*SL:102*/return this.resultItemStack.func_77946_l();
    }
    
    public int func_77570_a() {
        /*SL:108*/return 10;
    }
    
    public ItemStack func_77571_b() {
        /*SL:114*/return this.resultItemStack;
    }
    
    public ItemStack[] func_179532_b(final InventoryCrafting v-1) {
        final ItemStack[] v0 = /*EL:120*/new ItemStack[v-1.func_70302_i_()];
        /*SL:122*/for (int v = 0; v < v0.length; ++v) {
            final ItemStack a1 = /*EL:124*/v-1.func_70301_a(v);
            /*SL:125*/v0[v] = ForgeHooks.getContainerItem(a1);
        }
        /*SL:128*/return v0;
    }
    
    public void addLuckModifier(final CraftingLuckModifier a1) {
        /*SL:133*/this.luckModifiers.add(a1);
    }
}
