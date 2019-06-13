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
        int luck = /*EL:41*/0;
        int n = /*EL:42*/0;
        boolean b = /*EL:43*/false;
        ItemStack itemStack = /*EL:44*/null;
        /*SL:46*/for (int i = 0; i < v-8.func_70302_i_(); ++i) {
            final ItemStack func_70301_a = /*EL:48*/v-8.func_70301_a(i);
            /*SL:50*/if (func_70301_a != ItemStack.field_190927_a) {
                Label_0106: {
                    /*SL:54*/if (this.luckCraftingItem != null) {
                        if (func_70301_a.func_77973_b() != this.luckCraftingItem) {
                            break Label_0106;
                        }
                    }
                    else if (this.luckCraftingBlock == null || func_70301_a.func_77973_b() != Item.func_150898_a(this.luckCraftingBlock)) {
                        break Label_0106;
                    }
                    /*SL:56*/if (itemStack != null) {
                        return false;
                    }
                    /*SL:57*/luck = ItemLuckyBlock.getLuck(func_70301_a);
                    /*SL:58*/itemStack = func_70301_a;
                    continue;
                }
                boolean a2 = /*EL:62*/false;
                int v1 = /*EL:63*/0;
                /*SL:64*/for (a2 = 0; a2 < this.luckModifiers.size(); ++a2) {
                    /*SL:66*/if (func_70301_a.func_77973_b() == this.luckModifiers.get(a2).getItem() && (func_70301_a.func_77952_i() == this.luckModifiers.get(a2).getDamage() || this.luckModifiers.get(a2).getDamage() == -1)) {
                        /*SL:68*/a2 = true;
                        /*SL:69*/v1 = this.luckModifiers.get(a2).getLuckValue();
                        /*SL:70*/if (this.luckCraftingItem instanceof ItemLuckyPotion) {
                            v1 *= 4;
                        }
                    }
                }
                /*SL:74*/if (!a2) {
                    return false;
                }
                /*SL:75*/b = true;
                /*SL:77*/n += v1;
            }
        }
        /*SL:80*/if (itemStack == null || !b) {
            return false;
        }
        int i = /*EL:82*/luck + n;
        /*SL:83*/if (i > 100) {
            i = 100;
        }
        /*SL:84*/if (i < -100) {
            i = -100;
        }
        /*SL:85*/if (luck == 100 && i == 100) {
            return false;
        }
        /*SL:86*/if (luck == -100 && i == -100) {
            return false;
        }
        /*SL:88*/(this.resultItemStack = itemStack.func_77946_l()).func_190920_e(/*EL:90*/1);
        /*SL:92*/if (i != 0) {
            final NBTTagCompound func_77978_p = /*EL:94*/this.resultItemStack.func_77978_p();
            /*SL:95*/if (this.resultItemStack.func_77978_p() == null) {
                this.resultItemStack.func_77982_d(new NBTTagCompound());
            }
            /*SL:96*/this.resultItemStack.func_77978_p().func_74768_a("Luck", i);
        }
        else {
            /*SL:98*/this.resultItemStack.func_77982_d((NBTTagCompound)null);
        }
        /*SL:100*/return true;
    }
    
    public ItemStack func_77572_b(final InventoryCrafting a1) {
        /*SL:106*/return this.resultItemStack.func_77946_l();
    }
    
    public ItemStack func_77571_b() {
        /*SL:112*/return ItemStack.field_190927_a;
    }
    
    public NonNullList<ItemStack> func_179532_b(final InventoryCrafting v-1) {
        final NonNullList<ItemStack> v0 = /*EL:118*/(NonNullList<ItemStack>)NonNullList.func_191197_a(v-1.func_70302_i_(), (Object)new ItemStack(Items.field_151055_y));
        /*SL:120*/for (int v = 0; v < v0.size(); ++v) {
            final ItemStack a1 = /*EL:122*/v-1.func_70301_a(v);
            /*SL:123*/v0.set(v, (Object)ForgeHooks.getContainerItem(a1));
        }
        /*SL:126*/return v0;
    }
    
    public boolean func_194133_a(final int a1, final int a2) {
        /*SL:132*/return true;
    }
    
    public String func_193358_e() {
        /*SL:136*/return "lucky";
    }
    
    public boolean func_192399_d() {
        /*SL:141*/return true;
    }
    
    public void addLuckModifier(final CraftingLuckModifier a1) {
        /*SL:146*/this.luckModifiers.add(a1);
    }
}
