package mod.lucky;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.item.crafting.IRecipe;
import mod.lucky.tileentity.TileEntityLuckyBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import mod.lucky.item.ItemLuckyBlock;
import net.minecraft.creativetab.CreativeTabs;
import mod.lucky.block.BlockLuckyBlock;
import net.minecraft.block.material.Material;
import mod.lucky.util.LuckyConfiguration;
import cpw.mods.fml.relauncher.Side;
import mod.lucky.network.ParticlePacket;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.SidedProxy;
import mod.lucky.crafting.LuckyCrafting;
import mod.lucky.drop.LuckyDropBase;
import mod.lucky.gen.LuckyGenerator;
import net.minecraft.block.Block;
import cpw.mods.fml.common.Mod;

@Mod(modid = "lucky", name = "Lucky Block", version = "5.1.0")
public class Lucky
{
    @Mod.Instance("lucky")
    public static Lucky instance;
    public static Block lucky_block;
    public LuckyGenerator luckyGenerator;
    public boolean doDropsOnCreativeMode;
    public LuckyDropBase[] allDrops;
    public LuckyCrafting luckyCrafting;
    @SidedProxy(clientSide = "mod.lucky.client.ClientProxy", serverSide = "mod.lucky.CommonProxy")
    public static CommonProxy proxy;
    public static SimpleNetworkWrapper networkHandler;
    
    public Lucky() {
        this.luckyGenerator = new LuckyGenerator();
        this.doDropsOnCreativeMode = false;
        this.allDrops = new LuckyDropBase[1];
        this.luckyCrafting = new LuckyCrafting();
    }
    
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent a1) {
        (Lucky.networkHandler = NetworkRegistry.INSTANCE.newSimpleChannel(/*EL:50*/"LuckyBlock")).registerMessage(/*EL:51*/(Class)ParticlePacket.Handler.class, (Class)ParticlePacket.class, 0, Side.CLIENT);
        final String v1 = /*EL:53*/LuckyConfiguration.getConfigVersion();
        final String v2 = /*EL:54*/Lucky.class.<Mod>getAnnotation(Mod.class).version();
        /*SL:56*/if (!v1.equals(v2)) {
            /*SL:58*/LuckyConfiguration.createNewConfigFile();
        }
        /*SL:60*/LuckyConfiguration.readConfig();
        (Lucky.lucky_block = /*EL:63*/new BlockLuckyBlock(Material.field_151575_d).func_149663_c("luckyBlock").func_149711_c(0.2f).func_149752_b(6000000.0f).func_149672_a(Block.field_149769_e).func_149647_a(CreativeTabs.field_78030_b).func_149658_d("lucky:lucky_block")).setHarvestLevel(/*EL:64*/"pickaxe", 0);
        /*SL:65*/GameRegistry.registerBlock(Lucky.lucky_block, (Class)ItemLuckyBlock.class, "lucky_block");
        /*SL:68*/this.luckyCrafting.makeLuckyBlockRecipe();
        /*SL:69*/GameRegistry.registerTileEntity((Class)TileEntityLuckyBlock.class, "luckyBlockData");
        /*SL:72*/GameRegistry.addRecipe((IRecipe)this.luckyCrafting);
        /*SL:75*/GameRegistry.registerWorldGenerator((IWorldGenerator)this.luckyGenerator, 1);
        Lucky.proxy.registerRenderers();
    }
    
    @Mod.EventHandler
    public void load(final FMLInitializationEvent a1) {
    }
    
    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent a1) {
    }
}
