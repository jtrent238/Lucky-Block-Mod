package mod.lucky.client;

import java.util.Iterator;
import mod.lucky.block.BlockLuckyBlock;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import mod.lucky.Lucky;
import mod.lucky.resources.loader.PluginLoader;
import mod.lucky.resources.loader.ResourceLoader;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.client.FMLClientHandler;
import java.util.List;
import mod.lucky.CommonProxy;

public class ClientProxy extends CommonProxy
{
    @Override
    public void register() {
        try {
            final List list = /*EL:24*/(List)ObfuscationReflectionHelper.getPrivateValue((Class)FMLClientHandler.class, (Object)FMLClientHandler.instance(), new String[] { "resourcePackList" });
            final ResourceLoader resourceLoader = /*EL:25*/new ResourceLoader(Minecraft.func_71410_x().field_71412_D);
            /*SL:27*/for (final PluginLoader v1 : resourceLoader.getPluginLoaders()) {
                /*SL:28*/list.add(v1.getResourcePack());
            }
        }
        catch (Exception ex) {
            System.err.println(/*EL:32*/"Lucky Block: Error loading client plugins");
            /*SL:33*/ex.printStackTrace();
        }
        /*SL:36*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a(Item.func_150898_a((Block)Lucky.lucky_block), 0, new ModelResourceLocation("lucky:lucky_block", "inventory"));
        /*SL:38*/Lucky.getInstance();
        for (final BlockLuckyBlock blockLuckyBlock : Lucky.lucky_block_plugins) {
            final String v2 = Block.field_149771_c.func_177774_c(/*EL:40*/(Object)blockLuckyBlock).toString();
            /*SL:41*/Minecraft.func_71410_x().func_175599_af().func_175037_a().func_178086_a(Item.func_150898_a((Block)blockLuckyBlock), 0, new ModelResourceLocation(v2, "inventory"));
        }
    }
}
