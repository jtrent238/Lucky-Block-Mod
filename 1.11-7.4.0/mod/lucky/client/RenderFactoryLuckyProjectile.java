package mod.lucky.client;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderFactoryLuckyProjectile implements IRenderFactory
{
    public Render createRenderFor(final RenderManager a1) {
        /*SL:12*/return new RenderLuckyProjectile(a1);
    }
}
