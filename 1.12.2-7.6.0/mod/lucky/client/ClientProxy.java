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
            /*SL:27*/RenderingRegistry.registerEntityRenderingHandler((Class)EntityLuckyProjectile.class, (IRenderFactory)new RenderFactoryLuckyProjectile());
            /*SL:29*/RenderingRegistry.registerEntityRenderingHandler((Class)EntityLuckyPotion.class, (IRenderFactory)new RenderFactoryLuckyPotion());
            final ResourceLoader resourceLoader = /*EL:32*/new ResourceLoader(Minecraft.func_71410_x().field_71412_D);
            final List list = /*EL:34*/(List)ObfuscationReflectionHelper.getPrivateValue((Class)FMLClientHandler.class, /*EL:35*/(Object)FMLClientHandler.instance(), "resourcePackList");
            /*SL:37*/for (final PluginLoader v1 : resourceLoader.getPluginLoaders()) {
                System.out.println(/*EL:38*/v1.getPluginFile().getAbsolutePath());
                /*SL:39*/list.add(v1.getResourcePack());
            }
        }
        catch (Exception ex) {
            System.err.println(/*EL:43*/"Lucky Block: Error loading client plugins");
            /*SL:44*/ex.printStackTrace();
        }
    }
    
    @Override
    public void register() {
        /*SL:50*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a(/*EL:54*/Item.func_150898_a((Block)Lucky.lucky_block), 0, new ModelResourceLocation("lucky:lucky_block", "inventory"));
        /*SL:57*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)Lucky.lucky_sword, /*EL:59*/0, new ModelResourceLocation("lucky:lucky_sword", "inventory"));
        /*SL:62*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)Lucky.lucky_potion, /*EL:64*/0, new ModelResourceLocation("lucky:lucky_potion", "inventory"));
        /*SL:67*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a((Item)Lucky.lucky_bow, /*EL:69*/0, new ModelResourceLocation("lucky:lucky_bow", "inventory"));
        MinecraftForge.EVENT_BUS.register(/*EL:72*/(Object)new LuckyClientEventHandler());
        /*SL:74*/for (final PluginLoader v1 : Lucky.lucky_block_plugins) {
            /*SL:75*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a(/*EL:79*/Item.func_150898_a((Block)v1.getBlock()), 0, /*EL:82*/new ModelResourceLocation(((ResourceLocation)Block.field_149771_c.func_177774_c((Object)v1.getBlock())).toString(), "inventory"));
            /*SL:84*/if (v1.getSword() != null) {
                /*SL:85*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a(/*EL:87*/(Item)v1.getSword(), /*EL:89*/0, /*EL:92*/new ModelResourceLocation(((ResourceLocation)Item.field_150901_e.func_177774_c((Object)v1.getSword())).toString(), "inventory"));
            }
            /*SL:94*/if (v1.getPotion() != null) {
                /*SL:95*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a(/*EL:97*/(Item)v1.getPotion(), /*EL:99*/0, /*EL:102*/new ModelResourceLocation(((ResourceLocation)Item.field_150901_e.func_177774_c((Object)v1.getPotion())).toString(), "inventory"));
            }
            /*SL:104*/if (v1.getBow() != null) {
                /*SL:105*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a(/*EL:107*/(Item)v1.getBow(), /*EL:109*/0, /*EL:112*/new ModelResourceLocation(((ResourceLocation)Item.field_150901_e.func_177774_c((Object)v1.getBow())).toString(), "inventory"));
            }
        }
    }
}
