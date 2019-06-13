package mod.lucky.gen;

import net.minecraft.util.MathHelper;
import mod.lucky.Lucky;
import mod.lucky.util.StructureUtil;
import mod.lucky.util.CustomStructures;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.World;
import java.util.Random;
import cpw.mods.fml.common.IWorldGenerator;

public class LuckyGenerator implements IWorldGenerator
{
    public int spawnrate;
    public int structureChance;
    
    public LuckyGenerator() {
        this.spawnrate = 300;
        this.structureChance = 2;
        this.spawnrate = 300;
    }
    
    public void setSpawnrate(final int a1) {
        /*SL:25*/this.spawnrate = a1;
    }
    
    public void setStructureChance(final int a1) {
        /*SL:30*/this.structureChance = a1;
    }
    
    public void generate(final Random a1, final int a2, final int a3, final World a4, final IChunkProvider a5, final IChunkProvider a6) {
        /*SL:36*/CustomStructures.setRandom(a1);
        /*SL:38*/switch (a4.field_73011_w.field_76574_g) {
            case -1: {
                /*SL:41*/if (this.spawnrate != 0 && a1.nextInt((this.spawnrate / 3 == 0) ? 1 : (this.spawnrate / 3)) == 0) {
                    this.generateNether(a4, a1, a2 * 16, a3 * 16);
                    break;
                }
                break;
            }
            case 0: {
                /*SL:44*/if (this.spawnrate != 0 && a1.nextInt(this.spawnrate) == 0) {
                    this.generateSurface(a4, a1, a2 * 16, a3 * 16);
                    break;
                }
                break;
            }
            case 1: {
                /*SL:47*/if (this.spawnrate != 0 && a1.nextInt((this.spawnrate / 8 == 0) ? 1 : (this.spawnrate / 8)) == 0) {
                    this.generateEnd(a4, a1, a2 * 16, a3 * 16);
                    break;
                }
                break;
            }
        }
    }
    
    private void generateNether(final World a1, final Random a2, int a3, int a4) {
        /*SL:54*/a3 += a2.nextInt(16) + 8;
        final int v1 = /*EL:55*/64;
        /*SL:56*/a4 += a2.nextInt(16) + 8;
        /*SL:58*/CustomStructures.makeSurfaceLuckyBlock(a1, a3, v1, a4, -20, 20);
    }
    
    private void generateSurface(final World a1, final Random a2, int a3, int a4) {
        /*SL:63*/a3 += a2.nextInt(16) + 8;
        final int v1 = /*EL:64*/128;
        /*SL:65*/a4 += a2.nextInt(16) + 8;
        /*SL:67*/this.makeSurfaceLuckyBlockOrStructure(a1, a2, a3, v1, a4);
    }
    
    private void generateEnd(final World a1, final Random a2, int a3, int a4) {
        /*SL:72*/a3 += a2.nextInt(16) + 8;
        final int v1 = /*EL:73*/100;
        /*SL:74*/a4 += a2.nextInt(16) + 8;
        /*SL:76*/CustomStructures.makeSurfaceLuckyBlock(a1, a3, v1, a4, -20, 20);
    }
    
    private boolean makeSurfaceLuckyBlockOrStructure(final World a1, final Random a2, final int a3, final int a4, final int a5) {
        final int v1 = /*EL:81*/StructureUtil.getSurfacePosY(a1, a3, a4, a5);
        /*SL:82*/if (v1 == -1) {
            return false;
        }
        /*SL:84*/if (!Lucky.lucky_block.func_149718_j(a1, a3, v1, a5)) {
            /*SL:105*/return false;
        }
        if (this.structureChance != 0 && a2.nextInt(this.structureChance) == 0) {
            if (a2.nextInt(2) == 0) {
                CustomStructures.makeLuckyGenStructure(a1, a2, a3, v1, a5);
            }
            else {
                CustomStructures.makeUnluckyGenStructure(a1, a2, a3, v1, a5);
            }
            return true;
        }
        return StructureUtil.setLuckyBlock(a1, a3, v1, a5, MathHelper.func_76136_a(a2, -20, 20));
    }
}
