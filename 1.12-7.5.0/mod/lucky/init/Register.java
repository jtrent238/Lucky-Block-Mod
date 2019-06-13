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
        /*SL:23*/v1.getRegistry().register((IForgeRegistryEntry)Lucky.lucky_block);
        /*SL:24*/for (final PluginLoader a1 : Lucky.lucky_block_plugins) {
            /*SL:25*/v1.getRegistry().register((IForgeRegistryEntry)a1.getBlock());
        }
    }
    
    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> v1) {
        /*SL:32*/v1.getRegistry().register(new ItemLuckyBlock((Block)Lucky.lucky_block).setRegistryName(Lucky.lucky_block.getRegistryName()));
        /*SL:33*/v1.getRegistry().register((IForgeRegistryEntry)Lucky.lucky_sword);
        /*SL:34*/v1.getRegistry().register((IForgeRegistryEntry)Lucky.lucky_bow);
        /*SL:35*/v1.getRegistry().register((IForgeRegistryEntry)Lucky.lucky_potion);
        /*SL:37*/for (final PluginLoader a1 : Lucky.lucky_block_plugins) {
            /*SL:38*/v1.getRegistry().register(new ItemLuckyBlock((Block)a1.getBlock()).setRegistryName(a1.getBlock().getRegistryName()));
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
        /*SL:50*/v1.getRegistry().register(Lucky.lucky_block.getBlockRecipe().setRegistryName(Lucky.lucky_block.getRegistryName().toString()));
        /*SL:51*/v1.getRegistry().register(Lucky.lucky_block.getCrafting().setRegistryName(Lucky.lucky_block.getRegistryName().toString() + "_luck"));
        /*SL:52*/v1.getRegistry().register(Lucky.lucky_sword.getCrafting().setRegistryName(Lucky.lucky_sword.getRegistryName().toString() + "_luck"));
        /*SL:53*/v1.getRegistry().register(Lucky.lucky_bow.getCrafting().setRegistryName(Lucky.lucky_bow.getRegistryName().toString() + "_luck"));
        /*SL:54*/v1.getRegistry().register(Lucky.lucky_potion.getCrafting().setRegistryName(Lucky.lucky_potion.getRegistryName().toString() + "_luck"));
        /*SL:56*/for (final PluginLoader a1 : Lucky.lucky_block_plugins) {
            /*SL:57*/if (a1.getBlock().getBlockRecipe() != null) {
                v1.getRegistry().register(a1.getBlock().getBlockRecipe().setRegistryName(a1.getBlock().getRegistryName().toString()));
            }
            /*SL:58*/v1.getRegistry().register(a1.getBlock().getCrafting().setRegistryName(a1.getBlock().getRegistryName().toString() + "_luck"));
            /*SL:59*/if (a1.getSword() != null) {
                v1.getRegistry().register(a1.getSword().getCrafting().setRegistryName(a1.getSword().getRegistryName().toString() + "_luck"));
            }
            /*SL:60*/if (a1.getBow() != null) {
                v1.getRegistry().register(a1.getBow().getCrafting().setRegistryName(a1.getBow().getRegistryName().toString() + "_luck"));
            }
            /*SL:61*/if (a1.getPotion() != null) {
                v1.getRegistry().register(a1.getPotion().getCrafting().setRegistryName(a1.getPotion().getRegistryName().toString() + "_luck"));
            }
        }
    }
}
