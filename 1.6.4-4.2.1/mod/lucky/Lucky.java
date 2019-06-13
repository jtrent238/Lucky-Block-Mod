package mod.lucky;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import java.util.List;
import cpw.mods.fml.common.IWorldGenerator;
import none.yc;
import none.ye;
import java.util.ArrayList;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import none.ww;
import none.akc;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import mod.lucky.drops.LuckyConfiguration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.SidedProxy;
import none.aqz;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.Mod;

@Mod(modid = "lucky", name = "Lucky Block", version = "4.2.1")
@NetworkMod(clientSideRequired = true)
public class Lucky
{
    @Mod.Instance("lucky")
    public static Lucky instance;
    public static aqz blockLucky;
    public static int blockLuckyID;
    public static int spawnrate;
    public static String recipe;
    public static String[] allDrops;
    @SidedProxy(clientSide = "mod.lucky.client.ClientProxy", serverSide = "mod.lucky.CommonProxy")
    public static CommonProxy proxy;
    
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent a1) {
        final String v1 = /*EL:62*/LuckyConfiguration.getConfigVersion();
        /*SL:63*/if (!v1.equals("4.2.1")) {
            /*SL:65*/LuckyConfiguration.createNewConfigFile();
        }
        /*SL:67*/LuckyConfiguration.readConfig();
    }
    
    @Mod.EventHandler
    public void load(final FMLInitializationEvent v0) {
        /*SL:77*/LanguageRegistry.addName((Object)(Lucky.blockLucky = new BlockLucky(Lucky.blockLuckyID, akc.d).c(0.2f).b(6000000.0f).a(aqz.k).c("blockLucky").a(ww.b)), "Lucky Block");
        /*SL:80*/GameRegistry.registerBlock(Lucky.blockLucky, "luckyBlock");
        /*SL:81*/MinecraftForge.setBlockHarvestLevel(Lucky.blockLucky, "pickaxe", 0);
        try {
            final String[] v = Lucky.recipe.split(/*EL:86*/",");
            final List<Object> v2 = /*EL:87*/new ArrayList<Object>();
            /*SL:89*/for (int a1 = 0; a1 < v.length; ++a1) {
                /*SL:92*/if (v[a1].startsWith("block")) {
                    /*SL:94*/v2.add(new ye(aqz.s[Integer.valueOf(v[a1].substring(v[a1].indexOf(":") + 1))]));
                }
                else/*SL:96*/ if (v[a1].startsWith("item")) {
                    /*SL:98*/v2.add(new ye(yc.g[Integer.valueOf(v[a1].substring(v[a1].indexOf(":") + 1))]));
                }
                else/*SL:100*/ if (a1 + 1 != v.length && (v[a1 + 1].startsWith("block") || v[a1 + 1].startsWith("item"))) {
                    /*SL:102*/v2.add(v[a1].charAt(0));
                }
                else {
                    /*SL:106*/v2.add(v[a1]);
                }
            }
            /*SL:109*/GameRegistry.addRecipe(new ye(Lucky.blockLucky), v2.toArray());
        }
        catch (Exception ex) {}
        /*SL:117*/GameRegistry.registerWorldGenerator((IWorldGenerator)new LuckyGenerator(Lucky.spawnrate));
    }
    
    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent a1) {
    }
    
    static {
        Lucky.blockLuckyID = 850;
        Lucky.spawnrate = 0;
        Lucky.recipe = "";
    }
}
