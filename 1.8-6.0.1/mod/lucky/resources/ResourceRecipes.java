package mod.lucky.resources;

import java.util.List;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import mod.lucky.drop.func.DropProcessData;
import mod.lucky.drop.value.ValueParser;
import java.util.ArrayList;
import mod.lucky.resources.loader.BaseLoader;
import mod.lucky.util.LuckyReader;

public class ResourceRecipes implements IBaseResource
{
    @Override
    public void process(final LuckyReader v-7, final BaseLoader v-6) {
        try {
            String line;
            /*SL:21*/while ((line = v-7.readLine()) != null) {
                final String[] split = /*EL:23*/line.split(",");
                final List<Object> list = /*EL:24*/new ArrayList<Object>();
                /*SL:26*/for (final String v1 : split) {
                    final Item a1 = /*EL:28*/ValueParser.getItem(v1, null);
                    final ItemStack a2 = /*EL:29*/ValueParser.getItemStack(v1, null);
                    /*SL:31*/if (a1 != null) {
                        /*SL:33*/list.add(a1);
                    }
                    else/*SL:36*/ if (a2 != null) {
                        /*SL:38*/list.add(a2);
                    }
                    else/*SL:42*/ if (v1.length() == 1 && !v1.equals(split[0])) {
                        /*SL:44*/list.add(v1.charAt(0));
                    }
                    else {
                        /*SL:48*/list.add(v1);
                    }
                }
                final ItemStack itemStack = /*EL:51*/new ItemStack((Block)v-6.getBlock());
                /*SL:52*/if (list.get(0) instanceof Item) {
                    GameRegistry.addShapelessRecipe(itemStack, list.toArray());
                }
                else {
                    /*SL:53*/GameRegistry.addRecipe(itemStack, list.toArray());
                }
            }
        }
        catch (Exception ex) {
            System.err.println(/*EL:58*/"Lucky Block: Error reading 'recipes.txt'");
            /*SL:59*/ex.printStackTrace();
        }
    }
    
    @Override
    public String getDirectory() {
        /*SL:66*/return "recipes.txt";
    }
}
