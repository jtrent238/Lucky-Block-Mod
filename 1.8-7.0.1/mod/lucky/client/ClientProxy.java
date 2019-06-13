package mod.lucky.client;

import java.util.Iterator;
import net.minecraftforge.common.MinecraftForge;
import mod.lucky.entity.EntityLuckyPotion;
import net.minecraft.client.renderer.entity.Render;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import mod.lucky.entity.EntityLuckyProjectile;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import mod.lucky.Lucky;
import mod.lucky.resources.loader.PluginLoader;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.client.FMLClientHandler;
import java.util.List;
import mod.lucky.resources.loader.ResourceLoader;
import net.minecraft.client.Minecraft;
import mod.lucky.CommonProxy;

public class ClientProxy extends CommonProxy
{
    @Override
    public void register() {
        try {
            final ResourceLoader resourceLoader = /*EL:28*/new ResourceLoader(Minecraft.func_71410_x().field_71412_D);
            final List list = /*EL:29*/(List)ObfuscationReflectionHelper.getPrivateValue((Class)FMLClientHandler.class, (Object)FMLClientHandler.instance(), new String[] { "resourcePackList" });
            /*SL:31*/for (final PluginLoader v1 : resourceLoader.getPluginLoaders()) {
                System.out.println(/*EL:33*/v1.getPluginFile().getAbsolutePath());
                /*SL:34*/list.add(v1.getResourcePack());
            }
        }
        catch (Exception ex) {
            System.err.println(/*EL:40*/"Lucky Block: Error loading client plugins");
            /*SL:41*/ex.printStackTrace();
        }
        /*SL:44*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a(Item.func_150898_a((Block)Lucky.lucky_block), 0, new ModelResourceLocation("lucky:lucky_block", "inventory"));
        /*SL:45*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)Lucky.lucky_sword, 0, new ModelResourceLocation("lucky:lucky_sword", "inventory"));
        /*SL:46*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)Lucky.lucky_potion, 0, new ModelResourceLocation("lucky:lucky_potion", "inventory"));
        /*SL:48*/ModelBakery.addVariantName((Item)Lucky.lucky_bow, new String[] { "lucky:lucky_bow", "lucky:lucky_bow_pulling_0", "lucky:lucky_bow_pulling_1", "lucky:lucky_bow_pulling_2" });
        /*SL:49*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)Lucky.lucky_bow, 0, new ModelResourceLocation("lucky:lucky_bow", "inventory"));
        /*SL:50*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)Lucky.lucky_bow, 1, new ModelResourceLocation("lucky:lucky_bow_pulling_0", "inventory"));
        /*SL:51*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)Lucky.lucky_bow, 2, new ModelResourceLocation("lucky:lucky_bow_pulling_1", "inventory"));
        /*SL:52*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)Lucky.lucky_bow, 3, new ModelResourceLocation("lucky:lucky_bow_pulling_2", "inventory"));
        /*SL:54*/RenderingRegistry.registerEntityRenderingHandler((Class)EntityLuckyProjectile.class, (Render)new RenderLuckyProjectile(Minecraft.func_71410_x().func_175598_ae()));
        /*SL:55*/RenderingRegistry.registerEntityRenderingHandler((Class)EntityLuckyPotion.class, (Render)new RenderLuckyPotion(Minecraft.func_71410_x().func_175598_ae(), Minecraft.func_71410_x().func_175599_af()));
        MinecraftForge.EVENT_BUS.register(/*EL:57*/(Object)new LuckyClientEventHandler());
        /*SL:59*/for (final PluginLoader pluginLoader : Lucky.lucky_block_plugins) {
            /*SL:61*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a(Item.func_150898_a((Block)pluginLoader.getBlock()), 0, new ModelResourceLocation(Block.field_149771_c.func_177774_c((Object)pluginLoader.getBlock()).toString(), "inventory"));
            /*SL:63*/if (pluginLoader.getSword() != null) {
                Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)pluginLoader.getSword(), 0, new ModelResourceLocation(Item.field_150901_e.func_177774_c((Object)pluginLoader.getSword()).toString(), "inventory"));
            }
            /*SL:64*/if (pluginLoader.getPotion() != null) {
                Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)pluginLoader.getPotion(), 0, new ModelResourceLocation(Item.field_150901_e.func_177774_c((Object)pluginLoader.getPotion()).toString(), "inventory"));
            }
            /*SL:65*/if (pluginLoader.getBow() != null) {
                final String v2 = Item.field_150901_e.func_177774_c(/*EL:67*/(Object)pluginLoader.getBow()).toString();
                /*SL:68*/ModelBakery.addVariantName((Item)pluginLoader.getBow(), new String[] { v2, v2 + "_pulling_0", v2 + "_pulling_1", v2 + "_pulling_2" });
                /*SL:69*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)pluginLoader.getBow(), 0, new ModelResourceLocation(v2, "inventory"));
                /*SL:70*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)pluginLoader.getBow(), 1, new ModelResourceLocation(v2 + "_pulling_0", "inventory"));
                /*SL:71*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)pluginLoader.getBow(), 2, new ModelResourceLocation(v2 + "_pulling_1", "inventory"));
                /*SL:72*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)pluginLoader.getBow(), 3, new ModelResourceLocation(v2 + "_pulling_2", "inventory"));
            }
        }
    }
}
