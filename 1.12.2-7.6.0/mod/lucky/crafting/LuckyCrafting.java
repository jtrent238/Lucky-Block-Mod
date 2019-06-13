package mod.lucky.crafting;

import net.minecraftforge.common.ForgeHooks;
import net.minecraft.util.NonNullList;
import net.minecraft.nbt.NBTTagCompound;
import mod.lucky.item.ItemLuckyPotion;
import mod.lucky.item.ItemLuckyBlock;
import net.minecraft.world.World;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import java.util.ArrayList;
import net.minecraft.item.Item;
import net.minecraft.block.Block;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class LuckyCrafting extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe
{
    private Block luckCraftingBlock;
    private Item luckCraftingItem;
    private ArrayList<CraftingLuckModifier> luckModifiers;
    private ItemStack resultItemStack;
    
    public LuckyCrafting(final Block a1) {
        this.luckCraftingBlock = a1;
        (this.luckModifiers = new ArrayList<CraftingLuckModifier>()).add(new CraftingLuckModifier(Items.field_151034_e, 0, 20));
    }
    
    public LuckyCrafting(final Item a1) {
        this.luckCraftingItem = a1;
        this.luckModifiers = new ArrayList<CraftingLuckModifier>();
    }
    
    public boolean func_77569_a(final InventoryCrafting v-8, final World v-7) {
        int luck = /*EL:37*/0;
        int n = /*EL:38*/0;
        boolean b = /*EL:39*/false;
        ItemStack itemStack = /*EL:40*/null;
        /*SL:42*/for (int i = 0; i < v-8.func_70302_i_(); ++i) {
            final ItemStack func_70301_a = /*EL:43*/v-8.func_70301_a(i);
            /*SL:45*/if (func_70301_a != ItemStack.field_190927_a) {
                Label_0106: {
                    /*SL:47*/if (this.luckCraftingItem != null) {
                        /*SL:48*/if (func_70301_a.func_77973_b() != this.luckCraftingItem) {
                            break Label_0106;
                        }
                    }
                    else if (this.luckCraftingBlock == null || func_70301_a.func_77973_b() != /*EL:50*/Item.func_150898_a(this.luckCraftingBlock)) {
                        break Label_0106;
                    }
                    /*SL:52*/if (itemStack != null) {
                        return false;
                    }
                    /*SL:53*/luck = ItemLuckyBlock.getLuck(func_70301_a);
                    /*SL:54*/itemStack = func_70301_a;
                    continue;
                }
                boolean a2 = /*EL:56*/false;
                int v1 = /*EL:57*/0;
                /*SL:58*/for (a2 = 0; a2 < this.luckModifiers.size(); ++a2) {
                    /*SL:59*/if (func_70301_a.func_77973_b() == this.luckModifiers.get(a2).getItem() && (func_70301_a.func_77952_i() == /*EL:60*/this.luckModifiers.get(a2).getDamage() || this.luckModifiers.get(a2).getDamage() == /*EL:61*/-1)) {
                        /*SL:62*/a2 = true;
                        /*SL:63*/v1 = this.luckModifiers.get(a2).getLuckValue();
                        /*SL:64*/if (this.luckCraftingItem instanceof ItemLuckyPotion) {
                            v1 *= 4;
                        }
                    }
                }
                /*SL:68*/if (!a2) {
                    return false;
                }
                /*SL:69*/b = true;
                /*SL:71*/n += v1;
            }
        }
        /*SL:74*/if (itemStack == null || !b) {
            return false;
        }
        int i = /*EL:76*/luck + n;
        /*SL:77*/if (i > 100) {
            i = 100;
        }
        /*SL:78*/if (i < -100) {
            i = -100;
        }
        /*SL:79*/if (luck == 100 && i == 100) {
            return false;
        }
        /*SL:80*/if (luck == -100 && i == -100) {
            return false;
        }
        /*SL:82*/(this.resultItemStack = itemStack.func_77946_l()).func_190920_e(/*EL:84*/1);
        /*SL:86*/if (i != 0) {
            final NBTTagCompound func_77978_p = /*EL:87*/this.resultItemStack.func_77978_p();
            /*SL:88*/if (this.resultItemStack.func_77978_p() == null) {
                /*SL:89*/this.resultItemStack.func_77982_d(new NBTTagCompound());
            }
            /*SL:90*/this.resultItemStack.func_77978_p().func_74768_a("Luck", i);
        }
        else {
            /*SL:91*/this.resultItemStack.func_77982_d((NBTTagCompound)null);
        }
        /*SL:93*/return true;
    }
    
    public ItemStack func_77572_b(final InventoryCrafting a1) {
        /*SL:98*/return this.resultItemStack.func_77946_l();
    }
    
    public ItemStack func_77571_b() {
        /*SL:103*/return ItemStack.field_190927_a;
    }
    
    public NonNullList<ItemStack> func_179532_b(final InventoryCrafting v-1) {
        final NonNullList<ItemStack> v0 = /*EL:109*/(NonNullList<ItemStack>)NonNullList.func_191197_a(v-1.func_70302_i_(), /*EL:110*/(Object)new ItemStack(Items.field_151055_y));
        /*SL:112*/for (int v = 0; v < v0.size(); ++v) {
            final ItemStack a1 = /*EL:113*/v-1.func_70301_a(v);
            /*SL:114*/v0.set(v, (Object)ForgeHooks.getContainerItem(a1));
        }
        /*SL:117*/return v0;
    }
    
    public boolean func_194133_a(final int a1, final int a2) {
        /*SL:122*/return true;
    }
    
    public String func_193358_e() {
        /*SL:127*/return "lucky";
    }
    
    public boolean func_192399_d() {
        /*SL:132*/return true;
    }
    
    public void addLuckModifier(final CraftingLuckModifier a1) {
        /*SL:136*/this.luckModifiers.add(a1);
    }
}
