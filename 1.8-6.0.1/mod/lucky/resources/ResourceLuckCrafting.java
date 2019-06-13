package mod.lucky.resources;

import net.minecraft.item.ItemStack;
import mod.lucky.crafting.CraftingLuckModifier;
import mod.lucky.drop.func.DropProcessData;
import mod.lucky.drop.value.ValueParser;
import mod.lucky.resources.loader.BaseLoader;
import mod.lucky.util.LuckyReader;

public class ResourceLuckCrafting implements IBaseResource
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
            }
        }
        catch (Exception ex) {
            System.err.println(/*EL:31*/"Lucky Block: Error reading 'luck_crafting.txt'");
            /*SL:32*/ex.printStackTrace();
        }
    }
    
    @Override
    public String getDirectory() {
        /*SL:39*/return "luck_crafting.txt";
    }
}
