package mod.lucky.crafting;

import net.minecraftforge.common.ForgeHooks;
import net.minecraft.init.Items;
import net.minecraft.util.NonNullList;
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
        int luck = /*EL:40*/0;
        int n = /*EL:41*/0;
        boolean b = /*EL:42*/false;
        ItemStack itemStack = /*EL:43*/null;
        /*SL:45*/for (int i = 0; i < v-8.func_70302_i_(); ++i) {
            final ItemStack func_70301_a = /*EL:47*/v-8.func_70301_a(i);
            /*SL:49*/if (func_70301_a != null) {
                Label_0103: {
                    /*SL:53*/if (this.luckCraftingItem != null) {
                        if (func_70301_a.func_77973_b() != this.luckCraftingItem) {
                            break Label_0103;
                        }
                    }
                    else if (this.luckCraftingBlock == null || func_70301_a.func_77973_b() != Item.func_150898_a(this.luckCraftingBlock)) {
                        break Label_0103;
                    }
                    /*SL:55*/if (itemStack != null) {
                        return false;
                    }
                    /*SL:56*/luck = ItemLuckyBlock.getLuck(func_70301_a);
                    /*SL:57*/itemStack = func_70301_a;
                    continue;
                }
                boolean a2 = /*EL:61*/false;
                int v1 = /*EL:62*/0;
                /*SL:63*/for (a2 = 0; a2 < this.luckModifiers.size(); ++a2) {
                    /*SL:65*/if (func_70301_a.func_77973_b() == this.luckModifiers.get(a2).getItem() && (func_70301_a.func_77952_i() == this.luckModifiers.get(a2).getDamage() || this.luckModifiers.get(a2).getDamage() == -1)) {
                        /*SL:67*/a2 = true;
                        /*SL:68*/v1 = this.luckModifiers.get(a2).getLuckValue();
                        /*SL:69*/if (this.luckCraftingItem instanceof ItemLuckyPotion) {
                            v1 *= 4;
                        }
                    }
                }
                /*SL:73*/if (!a2) {
                    return false;
                }
                /*SL:74*/b = true;
                /*SL:76*/n += v1;
            }
        }
        /*SL:79*/if (itemStack == null || !b) {
            return false;
        }
        int i = /*EL:81*/luck + n;
        /*SL:82*/if (i > 100) {
            i = 100;
        }
        /*SL:83*/if (i < -100) {
            i = -100;
        }
        /*SL:84*/if (luck == 100 && i == 100) {
            return false;
        }
        /*SL:85*/if (luck == -100 && i == -100) {
            return false;
        }
        /*SL:87*/(this.resultItemStack = itemStack.func_77946_l()).func_190920_e(/*EL:89*/1);
        /*SL:91*/if (i != 0) {
            final NBTTagCompound func_77978_p = /*EL:93*/this.resultItemStack.func_77978_p();
            /*SL:94*/if (this.resultItemStack.func_77978_p() == null) {
                this.resultItemStack.func_77982_d(new NBTTagCompound());
            }
            /*SL:95*/this.resultItemStack.func_77978_p().func_74768_a("Luck", i);
        }
        else {
            /*SL:97*/this.resultItemStack.func_77982_d((NBTTagCompound)null);
        }
        /*SL:99*/return true;
    }
    
    public ItemStack func_77572_b(final InventoryCrafting a1) {
        /*SL:105*/return this.resultItemStack.func_77946_l();
    }
    
    public int func_77570_a() {
        /*SL:111*/return 10;
    }
    
    public ItemStack func_77571_b() {
        /*SL:117*/if (this.resultItemStack == null) {
            return ItemStack.field_190927_a;
        }
        /*SL:118*/return this.resultItemStack;
    }
    
    public NonNullList<ItemStack> func_179532_b(final InventoryCrafting v-1) {
        final NonNullList<ItemStack> v0 = /*EL:124*/(NonNullList<ItemStack>)NonNullList.func_191197_a(v-1.func_70302_i_(), (Object)new ItemStack(Items.field_151055_y));
        /*SL:126*/for (int v = 0; v < v0.size(); ++v) {
            final ItemStack a1 = /*EL:128*/v-1.func_70301_a(v);
            /*SL:129*/v0.set(v, (Object)ForgeHooks.getContainerItem(a1));
        }
        /*SL:132*/return v0;
    }
    
    public void addLuckModifier(final CraftingLuckModifier a1) {
        /*SL:137*/this.luckModifiers.add(a1);
    }
}
