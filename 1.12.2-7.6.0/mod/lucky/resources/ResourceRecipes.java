package mod.lucky.resources;

import net.minecraft.item.Item;
import mod.lucky.drop.func.DropProcessData;
import mod.lucky.drop.value.ValueParser;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.Ingredient;
import java.util.HashMap;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import mod.lucky.resources.loader.BaseLoader;
import mod.lucky.util.LuckyReader;

public class ResourceRecipes extends BaseResource
{
    @Override
    public void process(final LuckyReader v-8, final BaseLoader v-7) {
        try {
            String line;
            /*SL:19*/while ((line = v-8.readLine()) != null) {
                final String[] split = /*EL:20*/line.split(",");
                final NonNullList<Ingredient> func_191196_a = /*EL:21*/(NonNullList<Ingredient>)NonNullList.func_191196_a();
                final ItemStack itemStack = /*EL:22*/new ItemStack((Block)v-7.getBlock());
                /*SL:24*/if (getIngredient(split[0]) != null) {
                    /*SL:26*/for (Ingredient a2 : split) {
                        /*SL:27*/a2 = getIngredient(a2);
                        /*SL:28*/if (a2 != null) {
                            func_191196_a.add((Object)a2);
                        }
                    }
                    /*SL:30*/v-7.getBlock().setBlockRecipe((IForgeRegistryEntry.Impl<IRecipe>)new ShapelessRecipes("lucky", itemStack, (NonNullList)func_191196_a));
                }
                else {
                    final HashMap<Character, Ingredient> hashMap = /*EL:33*/new HashMap<Character, Ingredient>();
                    /*SL:34*/hashMap.put(' ', Ingredient.field_193370_a);
                    int n = /*EL:35*/0;
                    /*SL:36*/for (int v0 = split.length - 1; v0 > 0; v0 -= 2) {
                        final Ingredient v = getIngredient(/*EL:37*/split[v0]);
                        /*SL:38*/if (v == null) {
                            /*SL:39*/n = v0;
                            /*SL:40*/break;
                        }
                        /*SL:42*/hashMap.put(split[v0 - 1].toCharArray()[0], v);
                    }
                    /*SL:44*/for (int v0 = 0; v0 <= n; ++v0) {
                        /*SL:45*/for (final char v2 : split[v0].toCharArray()) {
                            func_191196_a.add((Object)hashMap.get(v2));
                        }
                    }
                    int v0 = /*EL:47*/split[0].length();
                    final int v3 = n + 1;
                    /*SL:48*/v-7.getBlock().setBlockRecipe(/*EL:49*/(IForgeRegistryEntry.Impl<IRecipe>)new ShapedRecipes("lucky", v0, v3, (NonNullList)func_191196_a, itemStack));
                }
            }
        }
        catch (Exception ex) {
            System.err.println(/*EL:54*/"Lucky Block: Error reading 'recipes.txt'");
            /*SL:55*/ex.printStackTrace();
        }
    }
    
    private static Ingredient getIngredient(final String a1) {
        final Item v1 = /*EL:60*/ValueParser.getItem(a1, null);
        final ItemStack v2 = /*EL:61*/ValueParser.getItemStack(a1, null);
        /*SL:62*/if (v1 != null) {
            return Ingredient.func_193367_a(v1);
        }
        /*SL:63*/if (v2 != null) {
            return Ingredient.func_193369_a(new ItemStack[] { v2 });
        }
        /*SL:64*/return null;
    }
    
    @Override
    public String getDirectory() {
        /*SL:69*/return "recipes.txt";
    }
    
    @Override
    public boolean postInit() {
        /*SL:74*/return true;
    }
}
