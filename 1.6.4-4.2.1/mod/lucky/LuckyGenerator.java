package mod.lucky;

import none.ado;
import none.abw;
import java.util.Random;
import cpw.mods.fml.common.IWorldGenerator;

public class LuckyGenerator implements IWorldGenerator
{
    public int spawnrate;
    
    public LuckyGenerator(final int a1) {
        this.spawnrate = a1;
    }
    
    public void generate(final Random a1, final int a2, final int a3, final abw a4, final ado a5, final ado a6) {
        /*SL:25*/switch (a4.t.i) {
            case -1: {
                /*SL:28*/this.generateNether(a4, a1, a2 * 16, a3 * 16);
                /*SL:29*/break;
            }
            case 0: {
                /*SL:31*/if (this.spawnrate != 0 && a1.nextInt(this.spawnrate) == 0) {
                    this.generateSurface(a4, a1, a2 * 16, a3 * 16);
                    break;
                }
                break;
            }
            case 1: {
                /*SL:34*/this.generateEnd(a4, a1, a2 * 16, a3 * 16);
                break;
            }
        }
    }
    
    private void generateEnd(final abw a1, final Random a2, final int a3, final int a4) {
    }
    
    private void generateSurface(final abw a1, final Random a2, final int a3, final int a4) {
        final int v1 = /*EL:46*/a3 + a2.nextInt(16) + 8;
        final int v2 = /*EL:47*/128;
        final int v3 = /*EL:48*/a4 + a2.nextInt(16) + 8;
        /*SL:50*/new WorldGenLucky(Lucky.blockLucky.cF).a(a1, a2, v1, v2, v3);
    }
    
    private void generateNether(final abw a1, final Random a2, final int a3, final int a4) {
    }
}
