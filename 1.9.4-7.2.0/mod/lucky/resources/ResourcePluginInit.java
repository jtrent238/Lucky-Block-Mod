package mod.lucky.resources;

import mod.lucky.Lucky;
import mod.lucky.item.ItemLuckyPotion;
import mod.lucky.item.ItemLuckyBow;
import mod.lucky.item.ItemLuckySword;
import net.minecraft.block.Block;
import mod.lucky.item.ItemLuckyBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.material.Material;
import mod.lucky.block.BlockLuckyBlock;
import java.util.Locale;
import org.apache.commons.lang3.text.WordUtils;
import mod.lucky.resources.loader.PluginLoader;
import mod.lucky.resources.loader.BaseLoader;
import mod.lucky.util.LuckyReader;

public class ResourcePluginInit extends BaseResource
{
    @Override
    public void process(final LuckyReader v-10, final BaseLoader v-9) {
        final PluginLoader pluginLoader = /*EL:25*/(PluginLoader)v-9;
        try {
            String pluginName = /*EL:28*/"random_block";
            String registryName = /*EL:29*/null;
            String registryName2 = /*EL:30*/null;
            String registryName3 = /*EL:31*/null;
            String line;
            /*SL:33*/while ((line = v-10.readLine()) != null) {
                final String a1 = /*EL:35*/line.substring(0, line.indexOf(61));
                final String a2 = /*EL:36*/line.substring(line.indexOf(61) + 1, line.length());
                /*SL:38*/if (a1.equalsIgnoreCase("id") || a1.equalsIgnoreCase("block_id")) {
                    pluginName = a2;
                }
                /*SL:39*/if (a1.equalsIgnoreCase("sword_id")) {
                    registryName = a2;
                }
                /*SL:40*/if (a1.equalsIgnoreCase("bow_id")) {
                    registryName2 = a2;
                }
                /*SL:41*/if (a1.equalsIgnoreCase("potion_id")) {
                    registryName3 = a2;
                }
            }
            String s = /*EL:44*/WordUtils.capitalizeFully(pluginName, new char[] { '_' }).replaceAll("_", "");
            /*SL:45*/s = String.valueOf(s.charAt(0)).toLowerCase(Locale.ENGLISH) + s.substring(1, s.length());
            final BlockLuckyBlock blockLuckyBlock = /*EL:47*/(BlockLuckyBlock)new BlockLuckyBlock(Material.field_151575_d).func_149663_c(s).func_149711_c(0.2f).func_149752_b(6000000.0f).func_149647_a(CreativeTabs.field_78030_b);
            /*SL:48*/blockLuckyBlock.setHarvestLevel("pickaxe", 0);
            /*SL:49*/GameRegistry.register(blockLuckyBlock.setRegistryName(pluginName));
            /*SL:50*/GameRegistry.register(new ItemLuckyBlock((Block)blockLuckyBlock).setRegistryName(pluginName));
            /*SL:52*/pluginLoader.setPluginName(pluginName);
            ItemLuckySword v0 = /*EL:54*/null;
            /*SL:55*/if (registryName != null) {
                String v = /*EL:57*/WordUtils.capitalizeFully(registryName, new char[] { '_' }).replaceAll("_", "");
                /*SL:58*/v = String.valueOf(v.charAt(0)).toLowerCase(Locale.ENGLISH) + v.substring(1, v.length());
                /*SL:59*/v0 = (ItemLuckySword)new ItemLuckySword().func_77655_b(v).func_77637_a(CreativeTabs.field_78037_j);
                /*SL:60*/GameRegistry.register(v0.setRegistryName(registryName));
            }
            ItemLuckyBow v2 = /*EL:63*/null;
            /*SL:64*/if (registryName2 != null) {
                String v3 = /*EL:66*/WordUtils.capitalizeFully(registryName2, new char[] { '_' }).replaceAll("_", "");
                /*SL:67*/v3 = String.valueOf(v3.charAt(0)).toLowerCase(Locale.ENGLISH) + v3.substring(1, v3.length());
                /*SL:68*/v2 = (ItemLuckyBow)new ItemLuckyBow().func_77655_b(v3).func_77637_a(CreativeTabs.field_78037_j);
                /*SL:69*/v2.setBowTextureName("lucky:" + registryName2);
                /*SL:70*/GameRegistry.register(v2.setRegistryName(registryName2));
            }
            ItemLuckyPotion v4 = /*EL:73*/null;
            /*SL:74*/if (registryName3 != null) {
                String v5 = /*EL:76*/WordUtils.capitalizeFully(registryName3, new char[] { '_' }).replaceAll("_", "");
                /*SL:77*/v5 = String.valueOf(v5.charAt(0)).toLowerCase(Locale.ENGLISH) + v5.substring(1, v5.length());
                /*SL:78*/v4 = (ItemLuckyPotion)new ItemLuckyPotion().func_77655_b(v5).func_77637_a(CreativeTabs.field_78026_f);
                /*SL:79*/GameRegistry.register(v4.setRegistryName(registryName3));
            }
            /*SL:82*/v-9.setLuckyBlockItems(blockLuckyBlock, v0, v2, v4);
            /*SL:83*/Lucky.getInstance();
            Lucky.lucky_block_plugins.add(pluginLoader);
        }
        catch (Exception ex) {
            System.err.println(/*EL:87*/"Lucky Block: Error reading 'plugin_init.txt' from plugin '" + pluginLoader.getPluginFile() + "'");
            /*SL:88*/ex.printStackTrace();
        }
    }
    
    @Override
    public String getDirectory() {
        /*SL:95*/return "plugin_init.txt";
    }
}
