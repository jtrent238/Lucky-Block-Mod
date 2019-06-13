package mod.lucky;

import java.util.Iterator;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import mod.lucky.structure.rotation.Rotations;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;
import mod.lucky.tileentity.TileEntityLuckyBlock;
import mod.lucky.entity.EntityLuckyPotion;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import mod.lucky.entity.EntityLuckyProjectile;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import java.io.File;
import mod.lucky.drop.func.DropFunction;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.relauncher.Side;
import mod.lucky.network.ParticlePacket;
import net.minecraftforge.fml.common.network.NetworkRegistry;
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

@Mod(modid = "lucky", name = "Lucky Block", version = "7.5.0", acceptedMinecraftVersions = "[1.12]")
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
    public static ResourceLoader resourceLoader;
    private LuckyTickHandler tickHandler;
    public static int ENTITY_ID;
    @SidedProxy(clientSide = "mod.lucky.client.ClientProxy", serverSide = "mod.lucky.CommonProxy")
    public static CommonProxy proxy;
    public static SimpleNetworkWrapper networkHandler;
    
    public Lucky() {
        (Lucky.networkHandler = NetworkRegistry.INSTANCE.newSimpleChannel("LuckyBlock")).registerMessage((Class)ParticlePacket.Handler.class, (Class)ParticlePacket.class, 0, Side.CLIENT);
        (Lucky.lucky_block = (BlockLuckyBlock)new BlockLuckyBlock(Material.field_151575_d).func_149663_c("luckyBlock").func_149711_c(0.2f).func_149752_b(6000000.0f).func_149647_a(CreativeTabs.field_78030_b).setRegistryName("lucky_block")).setHarvestLevel("pickaxe", 0);
        Lucky.lucky_sword = (ItemLuckySword)new ItemLuckySword().func_77655_b("luckySword").func_77637_a(CreativeTabs.field_78037_j).setRegistryName("lucky_sword");
        Lucky.lucky_bow = (ItemLuckyBow)new ItemLuckyBow().func_77655_b("luckyBow").func_77637_a(CreativeTabs.field_78037_j).setRegistryName("lucky_bow");
        Lucky.lucky_potion = (ItemLuckyPotion)new ItemLuckyPotion().func_77655_b("luckyPotion").func_77637_a(CreativeTabs.field_78037_j).setRegistryName("lucky_potion");
        Lucky.lucky_block_plugins = new ArrayList<PluginLoader>();
        Lucky.structures = new ArrayList<Structure>();
        DropFunction.registerFunctions();
        (Lucky.resourceLoader = new ResourceLoader(new File("."))).registerPlugins();
        Lucky.resourceLoader.extractDefaultResources();
        Lucky.resourceLoader.loadAllResources(false);
    }
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent a1) {
        Lucky.proxy.register();
        /*SL:97*/EntityRegistry.registerModEntity(new ResourceLocation("lucky", "LuckyProjectile"), (Class)EntityLuckyProjectile.class, "LuckyProjectile", Lucky.ENTITY_ID++, (Object)this, 128, 20, true);
        /*SL:99*/EntityRegistry.registerModEntity(new ResourceLocation("lucky", "LuckyPotion"), (Class)EntityLuckyPotion.class, "LuckyPotion", Lucky.ENTITY_ID++, (Object)this, 128, 20, true);
        /*SL:101*/GameRegistry.registerTileEntity((Class)TileEntityLuckyBlock.class, "luckyBlockData");
        /*SL:102*/GameRegistry.registerWorldGenerator((IWorldGenerator)Lucky.lucky_block.getWorldGenerator(), 1);
        /*SL:104*/this.tickHandler = new LuckyTickHandler();
        MinecraftForge.EVENT_BUS.register(/*EL:105*/(Object)this.tickHandler);
        /*SL:107*/Rotations.registerRotationHandlers();
    }
    
    public static Lucky getInstance() {
        /*SL:112*/return Lucky.instance;
    }
    
    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent a1) {
    }
    
    public ResourceLoader getResourceLoader() {
        /*SL:123*/return Lucky.resourceLoader;
    }
    
    public LuckyTickHandler getTickHandler() {
        /*SL:128*/return this.tickHandler;
    }
    
    public static ArrayList<Structure> getStructures() {
        /*SL:133*/return Lucky.structures;
    }
    
    public static Structure getStructure(final String v1) {
        /*SL:138*/for (final Structure a1 : Lucky.structures) {
            /*SL:139*/if (v1.equals(a1.getId())) {
                return a1;
            }
        }
        /*SL:140*/return null;
    }
    
    public static void addStructure(final Structure a1) {
        Lucky.structures.add(/*EL:145*/a1);
    }
    
    static {
        Lucky.MODID = "lucky";
        Lucky.VERSION = "7.5.0";
        Lucky.MC_VERSION = "1.12";
        Lucky.ENTITY_ID = 24653;
    }
}
