package mod.lucky.client;

import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import mod.lucky.item.ItemLuckyBow;
import net.minecraftforge.client.event.FOVUpdateEvent;

public class LuckyClientEventHandler
{
    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onEvent(final FOVUpdateEvent v-1) {
        /*SL:13*/if (v-1.getEntity().func_184587_cr() && v-1.getEntity().func_184607_cu() != null && v-1.getEntity().func_184607_cu().func_77973_b() instanceof ItemLuckyBow) {
            final int a1 = /*EL:15*/v-1.getEntity().func_184612_cw();
            float v1 = /*EL:16*/a1 / 20.0f;
            /*SL:18*/if (v1 > 1.0f) {
                /*SL:20*/v1 = 1.0f;
            }
            else {
                /*SL:24*/v1 *= v1;
            }
            /*SL:26*/v-1.setNewfov(v-1.getFov() * 1.0f - v1 * 0.15f);
        }
    }
}
