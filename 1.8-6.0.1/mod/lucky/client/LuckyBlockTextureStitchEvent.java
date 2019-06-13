package mod.lucky.client;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.lang.reflect.Field;
import java.util.Map;
import net.minecraftforge.client.event.TextureStitchEvent;

public class LuckyBlockTextureStitchEvent
{
    @SubscribeEvent
    public void onTextureStitchedPre(final TextureStitchEvent.Pre v0) {
        try {
            final Field a1 = /*EL:16*/v0.map.getClass().getDeclaredField("mapRegisteredSprites");
            /*SL:17*/a1.setAccessible(true);
            /*SL:19*/((Map)a1.get(v0.map)).put("lucky:blocks/lucky_block", new LuckyBlockTexture());
        }
        catch (Exception v) {
            /*SL:27*/v.printStackTrace();
        }
    }
}
