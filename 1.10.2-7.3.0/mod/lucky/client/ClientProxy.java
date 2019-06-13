package mod.lucky.client;

import java.util.Iterator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import mod.lucky.Lucky;
import mod.lucky.resources.loader.PluginLoader;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.client.FMLClientHandler;
import java.util.List;
import mod.lucky.resources.loader.ResourceLoader;
import net.minecraft.client.Minecraft;
import mod.lucky.entity.EntityLuckyPotion;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import mod.lucky.entity.EntityLuckyProjectile;
import mod.lucky.CommonProxy;

public class ClientProxy extends CommonProxy
{
    @Override
    public void preRegister() {
        /*SL:25*/RenderingRegistry.registerEntityRenderingHandler((Class)EntityLuckyProjectile.class, (IRenderFactory)new RenderFactoryLuckyProjectile());
        /*SL:26*/RenderingRegistry.registerEntityRenderingHandler((Class)EntityLuckyPotion.class, (IRenderFactory)new RenderFactoryLuckyPotion());
    }
    
    @Override
    public void register() {
        try {
            final ResourceLoader resourceLoader = /*EL:34*/new ResourceLoader(Minecraft.func_71410_x().field_71412_D);
            final List list = /*EL:35*/(List)ObfuscationReflectionHelper.getPrivateValue((Class)FMLClientHandler.class, (Object)FMLClientHandler.instance(), new String[] { "resourcePackList" });
            /*SL:37*/for (final PluginLoader v1 : resourceLoader.getPluginLoaders()) {
                System.out.println(/*EL:39*/v1.getPluginFile().getAbsolutePath());
                /*SL:40*/list.add(v1.getResourcePack());
            }
        }
        catch (Exception ex) {
            System.err.println(/*EL:46*/"Lucky Block: Error loading client plugins");
            /*SL:47*/ex.printStackTrace();
        }
        /*SL:50*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a(Item.func_150898_a((Block)Lucky.lucky_block), 0, new ModelResourceLocation("lucky:lucky_block", "inventory"));
        /*SL:51*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)Lucky.lucky_sword, 0, new ModelResourceLocation("lucky:lucky_sword", "inventory"));
        /*SL:52*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)Lucky.lucky_potion, 0, new ModelResourceLocation("lucky:lucky_potion", "inventory"));
        /*SL:53*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)Lucky.lucky_bow, 0, new ModelResourceLocation("lucky:lucky_bow", "inventory"));
        MinecraftForge.EVENT_BUS.register(/*EL:55*/(Object)new LuckyClientEventHandler());
        /*SL:57*/for (final PluginLoader pluginLoader : Lucky.lucky_block_plugins) {
            /*SL:59*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a(Item.func_150898_a((Block)pluginLoader.getBlock()), 0, new ModelResourceLocation(((ResourceLocation)Block.field_149771_c.func_177774_c((Object)pluginLoader.getBlock())).toString(), "inventory"));
            /*SL:61*/if (pluginLoader.getSword() != null) {
                Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)pluginLoader.getSword(), 0, new ModelResourceLocation(((ResourceLocation)Item.field_150901_e.func_177774_c((Object)pluginLoader.getSword())).toString(), "inventory"));
            }
            /*SL:62*/if (pluginLoader.getPotion() != null) {
                Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)pluginLoader.getPotion(), 0, new ModelResourceLocation(((ResourceLocation)Item.field_150901_e.func_177774_c((Object)pluginLoader.getPotion())).toString(), "inventory"));
            }
            /*SL:63*/if (pluginLoader.getBow() != null) {
                Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)pluginLoader.getBow(), 0, new ModelResourceLocation(((ResourceLocation)Item.field_150901_e.func_177774_c((Object)pluginLoader.getBow())).toString(), "inventory"));
            }
        }
    }
}
