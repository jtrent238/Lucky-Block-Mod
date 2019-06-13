package mod.lucky.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderFactoryLuckyPotion implements IRenderFactory
{
    public Render createRenderFor(final RenderManager a1) {
        /*SL:13*/return (Render)new RenderLuckyPotion(a1, Minecraft.func_71410_x().func_175599_af());
    }
}
