package mod.lucky.resources;

import net.minecraft.item.ItemStack;
import mod.lucky.crafting.CraftingLuckModifier;
import mod.lucky.drop.func.DropProcessData;
import mod.lucky.drop.value.ValueParser;
import mod.lucky.resources.loader.BaseLoader;
import mod.lucky.util.LuckyReader;

public class ResourceLuckCrafting extends BaseResource
{
    @Override
    public void process(final LuckyReader v-4, final BaseLoader v-3) {
        try {
            String line;
            /*SL:14*/while ((line = v-4.readLine()) != null) {
                final String a1 = /*EL:15*/line.substring(0, line.indexOf(61));
                final String a2 = /*EL:16*/line.substring(line.indexOf(61) + 1, line.length());
                final ItemStack v1 = /*EL:18*/ValueParser.getItemStack(a1, null);
                final int v2 = /*EL:19*/ValueParser.getInteger(a2);
                final CraftingLuckModifier v3 = /*EL:21*/new CraftingLuckModifier(v1.func_77973_b(), /*EL:22*/v1.func_77952_i(), v2);
                /*SL:23*/v-3.getBlock().getCrafting().addLuckModifier(v3);
                /*SL:24*/if (v-3.getSword() != null) {
                    /*SL:25*/v-3.getSword().getCrafting().addLuckModifier(v3);
                }
                /*SL:26*/if (v-3.getBow() != null) {
                    v-3.getBow().getCrafting().addLuckModifier(v3);
                }
                /*SL:27*/if (v-3.getPotion() != null) {
                    /*SL:28*/v-3.getPotion().getCrafting().addLuckModifier(v3);
                }
            }
        }
        catch (Exception ex) {
            System.err.println(/*EL:31*/"Lucky Block: Error reading 'luck_crafting.txt'");
            /*SL:32*/ex.printStackTrace();
        }
    }
    
    @Override
    public String getDirectory() {
        /*SL:38*/return "luck_crafting.txt";
    }
}
