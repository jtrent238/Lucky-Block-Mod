package mod.lucky;

import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import mod.lucky.structure.rotation.Rotations;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.RecipeSorter;
import mod.lucky.crafting.LuckyCrafting;
import mod.lucky.tileentity.TileEntityLuckyBlock;
import net.minecraft.server.MinecraftServer;
import mod.lucky.drop.func.DropFunction;
import net.minecraftforge.fml.common.registry.GameRegistry;
import mod.lucky.item.ItemLuckyBlock;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.relauncher.Side;
import mod.lucky.network.ParticlePacket;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.SidedProxy;
import mod.lucky.world.LuckyTickHandler;
import mod.lucky.resources.loader.ResourceLoader;
import java.util.ArrayList;
import mod.lucky.block.BlockLuckyBlock;
import net.minecraftforge.fml.common.Mod;

@Mod(modid = "lucky", name = "Lucky Block", version = "6.0.1", acceptedMinecraftVersions = "[1.8]")
public class Lucky
{
    @Mod.Instance("lucky")
    private static Lucky instance;
    public static String MODID;
    public static String VERSION;
    public static BlockLuckyBlock lucky_block;
    public static ArrayList<BlockLuckyBlock> lucky_block_plugins;
    private ResourceLoader resourceLoader;
    private LuckyTickHandler tickHandler;
    @SidedProxy(clientSide = "mod.lucky.client.ClientProxy", serverSide = "mod.lucky.CommonProxy")
    public static CommonProxy proxy;
    public static SimpleNetworkWrapper networkHandler;
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent a1) {
        (Lucky.networkHandler = NetworkRegistry.INSTANCE.newSimpleChannel(/*EL:56*/"LuckyBlock")).registerMessage(/*EL:57*/(Class)ParticlePacket.Handler.class, (Class)ParticlePacket.class, 0, Side.CLIENT);
        Lucky.lucky_block_plugins = /*EL:59*/new ArrayList<BlockLuckyBlock>();
        (Lucky.lucky_block = /*EL:61*/(BlockLuckyBlock)new BlockLuckyBlock(Material.field_151575_d).func_149663_c("luckyBlock").func_149711_c(0.2f).func_149752_b(6000000.0f).func_149672_a(Block.field_149769_e).func_149647_a(CreativeTabs.field_78030_b)).setHarvestLevel(/*EL:62*/"pickaxe", 0);
        /*SL:63*/GameRegistry.registerBlock((Block)Lucky.lucky_block, (Class)ItemLuckyBlock.class, "lucky_block");
        /*SL:65*/DropFunction.registerFunctions();
        /*SL:67*/(this.resourceLoader = new ResourceLoader(MinecraftServer.func_71276_C().func_71238_n())).registerPlugins();
        /*SL:69*/this.resourceLoader.extractDefaultResources();
        /*SL:70*/this.resourceLoader.loadAllResources();
        /*SL:72*/GameRegistry.registerTileEntity((Class)TileEntityLuckyBlock.class, "luckyBlockData");
        /*SL:73*/RecipeSorter.register("lucky:lucky_block", (Class)LuckyCrafting.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
        /*SL:74*/GameRegistry.addRecipe((IRecipe)Lucky.lucky_block.getCrafting());
        /*SL:75*/GameRegistry.registerWorldGenerator((IWorldGenerator)Lucky.lucky_block.getWorldGenerator(), 1);
        /*SL:77*/this.tickHandler = new LuckyTickHandler();
        /*SL:78*/FMLCommonHandler.instance().bus().register((Object)this.tickHandler);
        MinecraftForge.EVENT_BUS.register(/*EL:79*/(Object)this.tickHandler);
        /*SL:81*/Rotations.registerRotationHandlers();
        Lucky.proxy.register();
    }
    
    public static Lucky getInstance() {
        /*SL:87*/return Lucky.instance;
    }
    
    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent a1) {
    }
    
    public ResourceLoader getResourceLoader() {
        /*SL:98*/return this.resourceLoader;
    }
    
    public LuckyTickHandler getTickHandler() {
        /*SL:103*/return this.tickHandler;
    }
    
    static {
        Lucky.MODID = "lucky";
        Lucky.VERSION = "6.0.1";
    }
}
