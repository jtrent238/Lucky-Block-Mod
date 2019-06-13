package mod.lucky.resources;

import mod.lucky.Lucky;
import mod.lucky.item.ItemLuckyPotion;
import mod.lucky.item.ItemLuckyBow;
import net.minecraft.item.Item;
import mod.lucky.item.ItemLuckySword;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;
import mod.lucky.item.ItemLuckyBlock;
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
            String s = /*EL:29*/null;
            String s2 = /*EL:30*/null;
            String s3 = /*EL:31*/null;
            String line;
            /*SL:33*/while ((line = v-10.readLine()) != null) {
                final String a1 = /*EL:35*/line.substring(0, line.indexOf(61));
                final String a2 = /*EL:36*/line.substring(line.indexOf(61) + 1, line.length());
                /*SL:38*/if (a1.equalsIgnoreCase("id") || a1.equalsIgnoreCase("block_id")) {
                    pluginName = a2;
                }
                /*SL:39*/if (a1.equalsIgnoreCase("sword_id")) {
                    s = a2;
                }
                /*SL:40*/if (a1.equalsIgnoreCase("bow_id")) {
                    s2 = a2;
                }
                /*SL:41*/if (a1.equalsIgnoreCase("potion_id")) {
                    s3 = a2;
                }
            }
            String s4 = /*EL:44*/WordUtils.capitalizeFully(pluginName, new char[] { '_' }).replaceAll("_", "");
            /*SL:45*/s4 = String.valueOf(s4.charAt(0)).toLowerCase(Locale.ENGLISH) + s4.substring(1, s4.length());
            final BlockLuckyBlock a3 = /*EL:47*/(BlockLuckyBlock)new BlockLuckyBlock(Material.field_151575_d).func_149663_c(s4).func_149711_c(0.2f).func_149752_b(6000000.0f).func_149647_a(CreativeTabs.field_78030_b);
            /*SL:48*/a3.setHarvestLevel("pickaxe", 0);
            /*SL:49*/GameRegistry.registerBlock((Block)a3, (Class)ItemLuckyBlock.class, pluginName);
            /*SL:51*/pluginLoader.setPluginName(pluginName);
            ItemLuckySword v0 = /*EL:53*/null;
            /*SL:54*/if (s != null) {
                String v = /*EL:56*/WordUtils.capitalizeFully(s, new char[] { '_' }).replaceAll("_", "");
                /*SL:57*/v = String.valueOf(v.charAt(0)).toLowerCase(Locale.ENGLISH) + v.substring(1, v.length());
                /*SL:58*/v0 = (ItemLuckySword)new ItemLuckySword().func_77655_b(v).func_77637_a(CreativeTabs.field_78037_j);
                /*SL:59*/GameRegistry.registerItem((Item)v0, s);
            }
            ItemLuckyBow v2 = /*EL:62*/null;
            /*SL:63*/if (s2 != null) {
                String v3 = /*EL:65*/WordUtils.capitalizeFully(s2, new char[] { '_' }).replaceAll("_", "");
                /*SL:66*/v3 = String.valueOf(v3.charAt(0)).toLowerCase(Locale.ENGLISH) + v3.substring(1, v3.length());
                /*SL:67*/v2 = (ItemLuckyBow)new ItemLuckyBow().func_77655_b(v3).func_77637_a(CreativeTabs.field_78037_j);
                /*SL:68*/v2.setBowTextureName("lucky:" + s2);
                /*SL:69*/GameRegistry.registerItem((Item)v2, s2);
            }
            ItemLuckyPotion v4 = /*EL:72*/null;
            /*SL:73*/if (s3 != null) {
                String v5 = /*EL:75*/WordUtils.capitalizeFully(s3, new char[] { '_' }).replaceAll("_", "");
                /*SL:76*/v5 = String.valueOf(v5.charAt(0)).toLowerCase(Locale.ENGLISH) + v5.substring(1, v5.length());
                /*SL:77*/v4 = (ItemLuckyPotion)new ItemLuckyPotion().func_77655_b(v5).func_77637_a(CreativeTabs.field_78026_f);
                /*SL:78*/GameRegistry.registerItem((Item)v4, s3);
            }
            /*SL:81*/v-9.setLuckyBlockItems(a3, v0, v2, v4);
            /*SL:82*/Lucky.getInstance();
            Lucky.lucky_block_plugins.add(pluginLoader);
        }
        catch (Exception ex) {
            System.err.println(/*EL:86*/"Lucky Block: Error reading 'plugin_init.txt' from plugin '" + pluginLoader.getPluginFile() + "'");
            /*SL:87*/ex.printStackTrace();
        }
    }
    
    @Override
    public String getDirectory() {
        /*SL:94*/return "plugin_init.txt";
    }
}
