package mod.lucky;

import none.aqz;
import java.util.Random;
import none.abw;
import none.afe;

public class WorldGenLucky extends afe
{
    private int luckyBlockID;
    
    public WorldGenLucky(final int a1) {
        this.luckyBlockID = a1;
    }
    
    public boolean a(final abw a1, final Random a2, final int a3, int a4, final int a5) {
        aqz v1 = /*EL:20*/null;
        /*SL:32*/do {
            v1 = aqz.s[a1.a(a3, a4, a5)];
            if (v1 != null) {
                if ((v1.c() && v1 != aqz.P) || v1 == aqz.aY || v1 == aqz.G || v1 == aqz.F || v1 == aqz.I) {
                    break;
                }
                if (v1 == aqz.H) {
                    break;
                }
                continue;
            }
        } while (--a4 > 0);
        final int v2 = /*EL:35*/a4 + 1;
        /*SL:38*/if (Lucky.blockLucky.f(a1, a3, v2, a5)) {
            /*SL:40*/a1.f(a3, v2, a5, this.luckyBlockID, 0, 2);
        }
        /*SL:43*/return true;
    }
}
