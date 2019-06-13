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
            /*SL:23*/while ((line = v-8.readLine()) != null) {
                final String[] split = /*EL:25*/line.split(",");
                final NonNullList<Ingredient> func_191196_a = /*EL:26*/(NonNullList<Ingredient>)NonNullList.func_191196_a();
                final ItemStack itemStack = /*EL:27*/new ItemStack((Block)v-7.getBlock());
                /*SL:29*/if (getIngredient(split[0]) != null) {
                    /*SL:31*/for (Ingredient a2 : split) {
                        /*SL:32*/a2 = getIngredient(a2);
                        /*SL:33*/if (a2 != null) {
                            func_191196_a.add((Object)a2);
                        }
                    }
                    /*SL:35*/v-7.getBlock().setBlockRecipe((IForgeRegistryEntry.Impl<IRecipe>)new ShapelessRecipes("lucky", itemStack, (NonNullList)func_191196_a));
                }
                else {
                    final HashMap<Character, Ingredient> hashMap = /*EL:38*/new HashMap<Character, Ingredient>();
                    /*SL:39*/hashMap.put(' ', Ingredient.field_193370_a);
                    int n = /*EL:40*/0;
                    /*SL:41*/for (int v0 = split.length - 1; v0 > 0; v0 -= 2) {
                        final Ingredient v = getIngredient(/*EL:42*/split[v0]);
                        /*SL:43*/if (v == null) {
                            n = v0;
                            break;
                        }
                        /*SL:44*/hashMap.put(split[v0 - 1].toCharArray()[0], v);
                    }
                    /*SL:46*/for (int v0 = 0; v0 <= n; ++v0) {
                        /*SL:47*/for (final char v2 : split[v0].toCharArray()) {
                            /*SL:48*/func_191196_a.add((Object)hashMap.get(v2));
                        }
                    }
                    int v0 = /*EL:50*/split[0].length();
                    final int v3 = n + 1;
                    /*SL:51*/v-7.getBlock().setBlockRecipe((IForgeRegistryEntry.Impl<IRecipe>)new ShapedRecipes("lucky", v0, v3, (NonNullList)func_191196_a, itemStack));
                }
            }
        }
        catch (Exception ex) {
            System.err.println(/*EL:57*/"Lucky Block: Error reading 'recipes.txt'");
            /*SL:58*/ex.printStackTrace();
        }
    }
    
    private static Ingredient getIngredient(final String a1) {
        final Item v1 = /*EL:63*/ValueParser.getItem(a1, null);
        final ItemStack v2 = /*EL:64*/ValueParser.getItemStack(a1, null);
        /*SL:65*/if (v1 != null) {
            return Ingredient.func_193367_a(v1);
        }
        /*SL:66*/if (v2 != null) {
            return Ingredient.func_193369_a(new ItemStack[] { v2 });
        }
        /*SL:67*/return null;
    }
    
    @Override
    public String getDirectory() {
        /*SL:73*/return "recipes.txt";
    }
    
    @Override
    public boolean postInit() {
        /*SL:77*/return true;
    }
}
