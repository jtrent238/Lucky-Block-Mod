package mod.lucky.init;

import net.minecraft.item.crafting.IRecipe;
import mod.lucky.item.ItemLuckyBlock;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import mod.lucky.resources.loader.PluginLoader;
import net.minecraftforge.registries.IForgeRegistryEntry;
import mod.lucky.Lucky;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Register
{
    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> v1) {
        /*SL:17*/v1.getRegistry().register((IForgeRegistryEntry)Lucky.lucky_block);
        /*SL:18*/for (final PluginLoader a1 : Lucky.lucky_block_plugins) {
            /*SL:19*/v1.getRegistry().register((IForgeRegistryEntry)a1.getBlock());
        }
    }
    
    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> v1) {
        /*SL:24*/v1.getRegistry().register(/*EL:25*/new ItemLuckyBlock((Block)Lucky.lucky_block).setRegistryName(Lucky.lucky_block.getRegistryName()));
        /*SL:29*/v1.getRegistry().register((IForgeRegistryEntry)Lucky.lucky_sword);
        /*SL:30*/v1.getRegistry().register((IForgeRegistryEntry)Lucky.lucky_bow);
        /*SL:31*/v1.getRegistry().register((IForgeRegistryEntry)Lucky.lucky_potion);
        /*SL:33*/for (final PluginLoader a1 : Lucky.lucky_block_plugins) {
            /*SL:34*/v1.getRegistry().register(/*EL:37*/new ItemLuckyBlock((Block)a1.getBlock()).setRegistryName(a1.getBlock().getRegistryName()));
            /*SL:39*/if (a1.getSword() != null) {
                v1.getRegistry().register((IForgeRegistryEntry)a1.getSword());
            }
            /*SL:40*/if (a1.getBow() != null) {
                v1.getRegistry().register((IForgeRegistryEntry)a1.getBow());
            }
            /*SL:41*/if (a1.getPotion() != null) {
                v1.getRegistry().register((IForgeRegistryEntry)a1.getPotion());
            }
        }
        Lucky.resourceLoader.loadAllResources(/*EL:44*/true);
    }
    
    @SubscribeEvent
    public static void registerRecipes(final RegistryEvent.Register<IRecipe> v1) {
        /*SL:49*/v1.getRegistry().register(Lucky.lucky_block.getBlockRecipe().setRegistryName(Lucky.lucky_block.getRegistryName().toString()));
        /*SL:55*/v1.getRegistry().register(Lucky.lucky_block.getCrafting().setRegistryName(Lucky.lucky_block.getRegistryName().toString() + /*EL:60*/"_luck"));
        /*SL:61*/v1.getRegistry().register(Lucky.lucky_sword.getCrafting().setRegistryName(Lucky.lucky_sword.getRegistryName().toString() + /*EL:66*/"_luck"));
        /*SL:67*/v1.getRegistry().register(Lucky.lucky_bow.getCrafting().setRegistryName(Lucky.lucky_bow.getRegistryName().toString() + /*EL:72*/"_luck"));
        /*SL:73*/v1.getRegistry().register(Lucky.lucky_potion.getCrafting().setRegistryName(Lucky.lucky_potion.getRegistryName().toString() + /*EL:78*/"_luck"));
        /*SL:80*/for (final PluginLoader a1 : Lucky.lucky_block_plugins) {
            /*SL:81*/if (a1.getBlock().getBlockRecipe() != null) {
                /*SL:82*/v1.getRegistry().register(/*EL:83*/a1.getBlock().getBlockRecipe().setRegistryName(/*EL:87*/a1.getBlock().getRegistryName().toString()));
            }
            /*SL:89*/v1.getRegistry().register(/*EL:90*/a1.getBlock().getCrafting().setRegistryName(/*EL:94*/a1.getBlock().getRegistryName().toString() + /*EL:95*/"_luck"));
            /*SL:96*/if (a1.getSword() != null) {
                /*SL:97*/v1.getRegistry().register(/*EL:98*/a1.getSword().getCrafting().setRegistryName(/*EL:102*/a1.getSword().getRegistryName().toString() + /*EL:103*/"_luck"));
            }
            /*SL:104*/if (a1.getBow() != null) {
                /*SL:105*/v1.getRegistry().register(/*EL:106*/a1.getBow().getCrafting().setRegistryName(/*EL:110*/a1.getBow().getRegistryName().toString() + /*EL:111*/"_luck"));
            }
            /*SL:112*/if (a1.getPotion() != null) {
                /*SL:113*/v1.getRegistry().register(/*EL:114*/a1.getPotion().getCrafting().setRegistryName(/*EL:118*/a1.getPotion().getRegistryName().toString() + /*EL:119*/"_luck"));
            }
        }
    }
}
