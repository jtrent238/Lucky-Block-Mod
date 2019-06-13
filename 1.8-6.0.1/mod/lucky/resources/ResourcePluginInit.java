package mod.lucky.resources;

import net.minecraftforge.fml.common.registry.GameRegistry;
import mod.lucky.item.ItemLuckyBlock;
import mod.lucky.Lucky;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import mod.lucky.block.BlockLuckyBlock;
import java.util.Locale;
import org.apache.commons.lang3.text.WordUtils;
import mod.lucky.resources.loader.PluginLoader;
import mod.lucky.resources.loader.BaseLoader;
import mod.lucky.util.LuckyReader;

public class ResourcePluginInit implements IBaseResource
{
    @Override
    public void process(final LuckyReader v-2, final BaseLoader v-1) {
        final PluginLoader v0 = /*EL:23*/(PluginLoader)v-1;
        try {
            String v = /*EL:26*/"random_block";
            String v2;
            /*SL:28*/while ((v2 = v-2.readLine()) != null) {
                final String a1 = /*EL:30*/v2.substring(0, v2.indexOf(61));
                final String a2 = /*EL:31*/v2.substring(v2.indexOf(61) + 1, v2.length());
                /*SL:33*/if (a1.equalsIgnoreCase("id")) {
                    v = a2;
                }
            }
            String v3 = /*EL:36*/WordUtils.capitalizeFully(v, new char[] { '_' }).replaceAll("_", "");
            /*SL:37*/v3 = String.valueOf(v3.charAt(0)).toLowerCase(Locale.ENGLISH) + v3.substring(1, v3.length());
            final BlockLuckyBlock v4 = /*EL:39*/(BlockLuckyBlock)new BlockLuckyBlock(Material.field_151575_d).func_149663_c(v3).func_149711_c(0.2f).func_149752_b(6000000.0f).func_149672_a(Block.field_149769_e).func_149647_a(CreativeTabs.field_78030_b);
            /*SL:40*/v4.setHarvestLevel("pickaxe", 0);
            /*SL:41*/Lucky.getInstance();
            Lucky.lucky_block_plugins.add(v4);
            /*SL:43*/v-1.setLuckyBlock(v4);
            /*SL:44*/GameRegistry.registerBlock((Block)v4, (Class)ItemLuckyBlock.class, v);
        }
        catch (Exception v5) {
            System.err.println(/*EL:48*/"Lucky Block: Error reading 'plugin_init.txt' from plugin '" + v0.getPluginFile() + "'");
            /*SL:49*/v5.printStackTrace();
        }
    }
    
    @Override
    public String getDirectory() {
        /*SL:56*/return "plugin_init.txt";
    }
}
