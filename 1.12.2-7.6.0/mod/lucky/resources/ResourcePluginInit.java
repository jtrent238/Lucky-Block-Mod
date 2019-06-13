package mod.lucky.resources;

import mod.lucky.Lucky;
import mod.lucky.item.ItemLuckyPotion;
import mod.lucky.item.ItemLuckyBow;
import mod.lucky.item.ItemLuckySword;
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
        final PluginLoader pluginLoader = /*EL:19*/(PluginLoader)v-9;
        try {
            String s = /*EL:21*/"random_block";
            String registryName = /*EL:22*/null;
            String registryName2 = /*EL:23*/null;
            String registryName3 = /*EL:24*/null;
            String line;
            /*SL:26*/while ((line = v-10.readLine()) != null) {
                final String a1 = /*EL:27*/line.substring(0, line.indexOf(61));
                final String a2 = /*EL:28*/line.substring(line.indexOf(61) + 1, line.length());
                /*SL:30*/if (a1.equalsIgnoreCase("id") || a1.equalsIgnoreCase("block_id")) {
                    s = a2;
                }
                /*SL:31*/if (a1.equalsIgnoreCase("sword_id")) {
                    registryName = a2;
                }
                /*SL:32*/if (a1.equalsIgnoreCase("bow_id")) {
                    registryName2 = a2;
                }
                /*SL:33*/if (a1.equalsIgnoreCase("potion_id")) {
                    registryName3 = a2;
                }
            }
            String s2 = /*EL:37*/WordUtils.capitalizeFully(s, new char[] { '_' }).replaceAll("_", "");
            /*SL:40*/s2 = String.valueOf(s2.charAt(0)).toLowerCase(Locale.ENGLISH) + s2.substring(1, s2.length());
            final BlockLuckyBlock a3 = /*EL:42*/(BlockLuckyBlock)new BlockLuckyBlock(Material.field_151575_d).func_149663_c(s2).func_149711_c(/*EL:45*/0.2f).func_149752_b(/*EL:46*/6000000.0f).func_149647_a(CreativeTabs.field_78030_b).setRegistryName(/*EL:48*/s);
            /*SL:50*/a3.setHarvestLevel("pickaxe", 0);
            /*SL:52*/pluginLoader.setPluginName(s);
            ItemLuckySword v0 = /*EL:54*/null;
            /*SL:55*/if (registryName != null) {
                String v = /*EL:56*/WordUtils.capitalizeFully(registryName, new char[] { '_' }).replaceAll("_", "");
                /*SL:59*/v = String.valueOf(v.charAt(0)).toLowerCase(Locale.ENGLISH) + v.substring(1, v.length());
                /*SL:65*/v0 = (ItemLuckySword)new ItemLuckySword().func_77655_b(v).func_77637_a(CreativeTabs.field_78037_j).setRegistryName(registryName);
            }
            ItemLuckyBow v2 = /*EL:68*/null;
            /*SL:69*/if (registryName2 != null) {
                String v3 = /*EL:70*/WordUtils.capitalizeFully(registryName2, new char[] { '_' }).replaceAll("_", "");
                /*SL:73*/v3 = String.valueOf(v3.charAt(0)).toLowerCase(Locale.ENGLISH) + v3.substring(1, v3.length());
                /*SL:79*/v2 = (ItemLuckyBow)new ItemLuckyBow().func_77655_b(v3).func_77637_a(CreativeTabs.field_78037_j).setRegistryName(registryName2);
                /*SL:80*/v2.setBowTextureName("lucky:" + registryName2);
            }
            ItemLuckyPotion v4 = /*EL:83*/null;
            /*SL:84*/if (registryName3 != null) {
                String v5 = /*EL:85*/WordUtils.capitalizeFully(registryName3, new char[] { '_' }).replaceAll("_", "");
                /*SL:88*/v5 = String.valueOf(v5.charAt(0)).toLowerCase(Locale.ENGLISH) + v5.substring(1, v5.length());
                /*SL:94*/v4 = (ItemLuckyPotion)new ItemLuckyPotion().func_77655_b(v5).func_77637_a(CreativeTabs.field_78026_f).setRegistryName(registryName3);
            }
            /*SL:97*/v-9.setLuckyBlockItems(a3, v0, v2, v4);
            Lucky.lucky_block_plugins.add(/*EL:98*/pluginLoader);
        }
        catch (Exception ex) {
            System.err.println(/*EL:100*/"Lucky Block: Error reading 'plugin_init.txt' from plugin '" + pluginLoader.getPluginFile() + /*EL:102*/"'");
            /*SL:104*/ex.printStackTrace();
        }
    }
    
    @Override
    public String getDirectory() {
        /*SL:110*/return "plugin_init.txt";
    }
}
