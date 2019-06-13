package mod.lucky;

import java.util.Iterator;
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
import mod.lucky.entity.EntityLuckyPotion;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import mod.lucky.entity.EntityLuckyProjectile;
import net.minecraft.item.Item;
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
import mod.lucky.structure.Structure;
import mod.lucky.resources.loader.PluginLoader;
import java.util.ArrayList;
import mod.lucky.item.ItemLuckyPotion;
import mod.lucky.item.ItemLuckyBow;
import mod.lucky.item.ItemLuckySword;
import mod.lucky.block.BlockLuckyBlock;
import net.minecraftforge.fml.common.Mod;

@Mod(modid = "lucky", name = "Lucky Block", version = "7.0.1", acceptedMinecraftVersions = "[1.8]")
public class Lucky
{
    @Mod.Instance("lucky")
    private static Lucky instance;
    public static String MODID;
    public static String VERSION;
    public static String MC_VERSION;
    public static BlockLuckyBlock lucky_block;
    public static ItemLuckySword lucky_sword;
    public static ItemLuckyBow lucky_bow;
    public static ItemLuckyPotion lucky_potion;
    public static ArrayList<PluginLoader> lucky_block_plugins;
    public static ArrayList<Structure> structures;
    private ResourceLoader resourceLoader;
    private LuckyTickHandler tickHandler;
    @SidedProxy(clientSide = "mod.lucky.client.ClientProxy", serverSide = "mod.lucky.CommonProxy")
    public static CommonProxy proxy;
    public static SimpleNetworkWrapper networkHandler;
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent a1) {
        (Lucky.networkHandler = NetworkRegistry.INSTANCE.newSimpleChannel(/*EL:69*/"LuckyBlock")).registerMessage(/*EL:70*/(Class)ParticlePacket.Handler.class, (Class)ParticlePacket.class, 0, Side.CLIENT);
        Lucky.lucky_block_plugins = /*EL:72*/new ArrayList<PluginLoader>();
        Lucky.structures = /*EL:73*/new ArrayList<Structure>();
        (Lucky.lucky_block = /*EL:76*/(BlockLuckyBlock)new BlockLuckyBlock(Material.field_151575_d).func_149663_c("luckyBlock").func_149711_c(0.2f).func_149752_b(6000000.0f).func_149672_a(Block.field_149769_e).func_149647_a(CreativeTabs.field_78030_b)).setHarvestLevel(/*EL:77*/"pickaxe", 0);
        /*SL:78*/GameRegistry.registerBlock((Block)Lucky.lucky_block, (Class)ItemLuckyBlock.class, "lucky_block");
        /*SL:82*/GameRegistry.registerItem((Item)(Lucky.lucky_sword = (ItemLuckySword)new ItemLuckySword().func_77655_b("luckySword").func_77637_a(CreativeTabs.field_78037_j)), "lucky_sword");
        /*SL:86*/GameRegistry.registerItem((Item)(Lucky.lucky_bow = (ItemLuckyBow)new ItemLuckyBow().func_77655_b("luckyBow").func_77637_a(CreativeTabs.field_78037_j)), "lucky_bow");
        /*SL:90*/GameRegistry.registerItem((Item)(Lucky.lucky_potion = (ItemLuckyPotion)new ItemLuckyPotion().func_77655_b("luckyPotion").func_77637_a(CreativeTabs.field_78026_f)), "lucky_potion");
        /*SL:93*/EntityRegistry.registerGlobalEntityID((Class)EntityLuckyProjectile.class, "LuckyProjectile", EntityRegistry.findGlobalUniqueEntityId());
        /*SL:94*/EntityRegistry.registerModEntity((Class)EntityLuckyProjectile.class, "LuckyProjectile", EntityRegistry.findGlobalUniqueEntityId(), (Object)this, 128, 20, true);
        /*SL:96*/EntityRegistry.registerGlobalEntityID((Class)EntityLuckyPotion.class, "LuckyPotion", EntityRegistry.findGlobalUniqueEntityId());
        /*SL:97*/EntityRegistry.registerModEntity((Class)EntityLuckyPotion.class, "LuckyPotion", EntityRegistry.findGlobalUniqueEntityId(), (Object)this, 128, 20, true);
        /*SL:99*/DropFunction.registerFunctions();
        /*SL:100*/(this.resourceLoader = new ResourceLoader(MinecraftServer.func_71276_C().func_71238_n())).registerPlugins();
        /*SL:102*/this.resourceLoader.extractDefaultResources();
        /*SL:103*/this.resourceLoader.loadAllResources();
        /*SL:105*/GameRegistry.registerTileEntity((Class)TileEntityLuckyBlock.class, "luckyBlockData");
        /*SL:106*/RecipeSorter.register("lucky:lucky_block", (Class)LuckyCrafting.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
        /*SL:107*/GameRegistry.addRecipe((IRecipe)Lucky.lucky_block.getCrafting());
        /*SL:108*/GameRegistry.addRecipe((IRecipe)Lucky.lucky_sword.getCrafting());
        /*SL:109*/GameRegistry.addRecipe((IRecipe)Lucky.lucky_bow.getCrafting());
        /*SL:110*/GameRegistry.addRecipe((IRecipe)Lucky.lucky_potion.getCrafting());
        /*SL:111*/GameRegistry.registerWorldGenerator((IWorldGenerator)Lucky.lucky_block.getWorldGenerator(), 1);
        /*SL:113*/this.tickHandler = new LuckyTickHandler();
        /*SL:114*/FMLCommonHandler.instance().bus().register((Object)this.tickHandler);
        MinecraftForge.EVENT_BUS.register(/*EL:115*/(Object)this.tickHandler);
        /*SL:117*/Rotations.registerRotationHandlers();
        Lucky.proxy.register();
    }
    
    public static Lucky getInstance() {
        /*SL:123*/return Lucky.instance;
    }
    
    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent a1) {
    }
    
    public ResourceLoader getResourceLoader() {
        /*SL:134*/return this.resourceLoader;
    }
    
    public LuckyTickHandler getTickHandler() {
        /*SL:139*/return this.tickHandler;
    }
    
    public static ArrayList<Structure> getStructures() {
        /*SL:144*/return Lucky.structures;
    }
    
    public static Structure getStructure(final String v1) {
        /*SL:149*/for (final Structure a1 : Lucky.structures) {
            /*SL:150*/if (v1.equals(a1.getId())) {
                return a1;
            }
        }
        /*SL:151*/return null;
    }
    
    public static void addStructure(final Structure a1) {
        Lucky.structures.add(/*EL:156*/a1);
    }
    
    static {
        Lucky.MODID = "lucky";
        Lucky.VERSION = "7.0.1";
        Lucky.MC_VERSION = "1.8.0";
    }
}
