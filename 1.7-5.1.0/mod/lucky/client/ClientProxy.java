package mod.lucky.client;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import mod.lucky.client.render.RenderLuckyBlock;
import net.minecraft.item.Item;
import mod.lucky.Lucky;
import mod.lucky.CommonProxy;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerRenderers() {
        /*SL:14*/MinecraftForgeClient.registerItemRenderer(Item.func_150898_a(Lucky.lucky_block), (IItemRenderer)new RenderLuckyBlock());
    }
}
