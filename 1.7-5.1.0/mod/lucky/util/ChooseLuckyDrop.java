package mod.lucky.util;

import java.util.Random;
import java.util.ArrayList;
import mod.lucky.drop.LuckyDropBase;

public class ChooseLuckyDrop
{
    public static LuckyDropBase chooseDrop(final LuckyDropBase[] v-12, final int v-11) {
        final LuckyDropBase[] a3 = /*EL:12*/new LuckyDropBase[v-12.length];
        int luck = /*EL:13*/0;
        int luck2 = /*EL:14*/0;
        /*SL:15*/for (int a1 = 0; a1 < a3.length; ++a1) {
            /*SL:17*/a3[a1] = v-12[a1].copy();
            /*SL:18*/if (a3[a1].getLuck() < luck) {
                luck = a3[a1].getLuck();
            }
            /*SL:19*/if (a3[a1].getLuck() > luck2) {
                luck2 = a3[a1].getLuck();
            }
        }
        /*SL:21*/luck2 += luck * -1 + 1;
        final float n = /*EL:23*/1.0f / (1.0f - ((v-11 < 0) ? (v-11 * -1) : v-11) * 0.77f / 100.0f);
        float n2 = /*EL:25*/0.0f;
        final ArrayList<Float> a4 = /*EL:26*/new ArrayList<Float>();
        /*SL:27*/a4.add(0.0f);
        /*SL:28*/for (final LuckyDropBase luckyDropBase : a3) {
            final int a2 = /*EL:30*/luckyDropBase.getLuck() + luck * -1 + 1;
            float v1 = /*EL:31*/0.0f;
            /*SL:32*/if (v-11 >= 0) {
                v1 = (float)Math.pow(n, a2);
            }
            else {
                /*SL:33*/v1 = (float)Math.pow(n, luck2 + 1 - a2);
            }
            final float v2 = /*EL:34*/luckyDropBase.getChance() * v1 * 100.0f;
            /*SL:35*/luckyDropBase.setChance(v2);
            /*SL:36*/n2 += v2;
            /*SL:37*/a4.add(n2);
        }
        final Random random = /*EL:40*/new Random();
        final float v3 = /*EL:41*/random.nextFloat() * n2;
        final LuckyDropBase dropByweight = getDropByweight(/*EL:42*/a3, a4, v3);
        /*SL:44*/return dropByweight;
    }
    
    private static LuckyDropBase getDropByweight(final LuckyDropBase[] a2, final ArrayList<Float> a3, final float v1) {
        /*SL:49*/for (int a4 = 0; a4 < a2.length; ++a4) {
            /*SL:51*/if (v1 >= a3.get(a4) && v1 < a3.get(a4 + 1)) {
                /*SL:53*/return a2[a4];
            }
        }
        /*SL:56*/return null;
    }
}
