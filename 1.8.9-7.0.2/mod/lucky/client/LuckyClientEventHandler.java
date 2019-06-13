package mod.lucky.client;

import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import mod.lucky.item.ItemLuckyBow;
import net.minecraftforge.client.event.FOVUpdateEvent;

public class LuckyClientEventHandler
{
    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onEvent(final FOVUpdateEvent v2) {
        /*SL:13*/if (v2.entity.func_71039_bw() && v2.entity.func_71011_bu().func_77973_b() instanceof ItemLuckyBow) {
            float a1 = /*EL:15*/v2.entity.func_71057_bx() / 20.0f;
            /*SL:16*/if (a1 > 1.0f) {
                a1 = 1.0f;
            }
            else {
                /*SL:17*/a1 *= a1;
            }
            /*SL:19*/v2.newfov = v2.fov * 1.0f - a1 * 0.15f;
        }
    }
}
