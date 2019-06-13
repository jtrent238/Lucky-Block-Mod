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
            /*SL:17*/while ((line = v-4.readLine()) != null) {
                final String a1 = /*EL:19*/line.substring(0, line.indexOf(61));
                final String a2 = /*EL:20*/line.substring(line.indexOf(61) + 1, line.length());
                final ItemStack v1 = /*EL:22*/ValueParser.getItemStack(a1, null);
                final int v2 = /*EL:23*/ValueParser.getInteger(a2);
                final CraftingLuckModifier v3 = /*EL:25*/new CraftingLuckModifier(v1.func_77973_b(), v1.func_77952_i(), v2);
                /*SL:26*/v-3.getBlock().getCrafting().addLuckModifier(v3);
                /*SL:27*/if (v-3.getSword() != null) {
                    v-3.getSword().getCrafting().addLuckModifier(v3);
                }
                /*SL:28*/if (v-3.getBow() != null) {
                    v-3.getBow().getCrafting().addLuckModifier(v3);
                }
                /*SL:29*/if (v-3.getPotion() != null) {
                    v-3.getPotion().getCrafting().addLuckModifier(v3);
                }
            }
        }
        catch (Exception ex) {
            System.err.println(/*EL:34*/"Lucky Block: Error reading 'luck_crafting.txt'");
            /*SL:35*/ex.printStackTrace();
        }
    }
    
    @Override
    public String getDirectory() {
        /*SL:42*/return "luck_crafting.txt";
    }
}
