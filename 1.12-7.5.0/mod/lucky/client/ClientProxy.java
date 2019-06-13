package mod.lucky.client;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import mod.lucky.Lucky;
import java.util.Iterator;
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
    public ClientProxy() {
        this.preRegister();
    }
    
    @Override
    public void preRegister() {
        try {
            /*SL:31*/RenderingRegistry.registerEntityRenderingHandler((Class)EntityLuckyProjectile.class, (IRenderFactory)new RenderFactoryLuckyProjectile());
            /*SL:32*/RenderingRegistry.registerEntityRenderingHandler((Class)EntityLuckyPotion.class, (IRenderFactory)new RenderFactoryLuckyPotion());
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
    }
    
    @Override
    public void register() {
        /*SL:54*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a(Item.func_150898_a((Block)Lucky.lucky_block), 0, new ModelResourceLocation("lucky:lucky_block", "inventory"));
        /*SL:55*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)Lucky.lucky_sword, 0, new ModelResourceLocation("lucky:lucky_sword", "inventory"));
        /*SL:56*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)Lucky.lucky_potion, 0, new ModelResourceLocation("lucky:lucky_potion", "inventory"));
        /*SL:57*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)Lucky.lucky_bow, 0, new ModelResourceLocation("lucky:lucky_bow", "inventory"));
        MinecraftForge.EVENT_BUS.register(/*EL:59*/(Object)new LuckyClientEventHandler());
        /*SL:61*/for (final PluginLoader v1 : Lucky.lucky_block_plugins) {
            /*SL:63*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a(Item.func_150898_a((Block)v1.getBlock()), 0, new ModelResourceLocation(((ResourceLocation)Block.field_149771_c.func_177774_c((Object)v1.getBlock())).toString(), "inventory"));
            /*SL:64*/if (v1.getSword() != null) {
                Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)v1.getSword(), 0, new ModelResourceLocation(((ResourceLocation)Item.field_150901_e.func_177774_c((Object)v1.getSword())).toString(), "inventory"));
            }
            /*SL:65*/if (v1.getPotion() != null) {
                Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)v1.getPotion(), 0, new ModelResourceLocation(((ResourceLocation)Item.field_150901_e.func_177774_c((Object)v1.getPotion())).toString(), "inventory"));
            }
            /*SL:66*/if (v1.getBow() != null) {
                Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)v1.getBow(), 0, new ModelResourceLocation(((ResourceLocation)Item.field_150901_e.func_177774_c((Object)v1.getBow())).toString(), "inventory"));
            }
        }
    }
}
