package mod.lucky.world;

import mod.lucky.drop.DropBase;
import net.minecraft.entity.Entity;
import mod.lucky.drop.func.DropProcessData;
import net.minecraft.util.math.BlockPos;
import java.util.Iterator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.World;
import java.util.Random;
import mod.lucky.drop.DropContainer;
import java.util.ArrayList;
import mod.lucky.block.BlockLuckyBlock;
import net.minecraftforge.fml.common.IWorldGenerator;

public class LuckyGenerator implements IWorldGenerator
{
    private BlockLuckyBlock block;
    private ArrayList<DropContainer> surfaceDrops;
    private ArrayList<DropContainer> netherDrops;
    private ArrayList<DropContainer> endDrops;
    
    public LuckyGenerator(final BlockLuckyBlock a1) {
        this.block = a1;
        this.surfaceDrops = new ArrayList<DropContainer>();
        this.netherDrops = new ArrayList<DropContainer>();
        this.endDrops = new ArrayList<DropContainer>();
    }
    
    public void generate(final Random a6, final int v1, final int v2, final World v3, final IChunkGenerator v4, final IChunkProvider v5) {
        try {
            /*SL:35*/switch (v3.field_73011_w.getDimension()) {
                case -1: {
                    /*SL:38*/for (final DropContainer a7 : this.netherDrops) {
                        /*SL:39*/if (a6.nextInt((int)a7.getChance()) == 0) {
                            this.generateNether(v3, a6, v1 * 16, v2 * 16, a7);
                        }
                    }
                    /*SL:40*/break;
                }
                case 0: {
                    /*SL:42*/for (final DropContainer a8 : this.surfaceDrops) {
                        /*SL:43*/if (a6.nextInt((int)a8.getChance()) == 0) {
                            this.generateSurface(v3, a6, v1 * 16, v2 * 16, a8);
                        }
                    }
                    /*SL:44*/break;
                }
                case 1: {
                    /*SL:46*/for (final DropContainer a9 : this.endDrops) {
                        /*SL:47*/if (a6.nextInt((int)a9.getChance()) == 0) {
                            this.generateEnd(v3, a6, v1 * 16, v2 * 16, a9);
                        }
                    }
                    break;
                }
            }
        }
        catch (Exception a10) {
            System.err.println(/*EL:53*/"Lucky Block: Error during natural generation");
            /*SL:54*/a10.printStackTrace();
        }
    }
    
    private void generateNether(final World a1, final Random a2, int a3, int a4, final DropContainer a5) {
        /*SL:60*/a3 += a2.nextInt(16) + 8;
        /*SL:61*/a4 += a2.nextInt(16) + 8;
        /*SL:62*/this.generate(a1, new BlockPos(a3, 64, a4), a5);
    }
    
    private void generateSurface(final World a1, final Random a2, int a3, int a4, final DropContainer a5) {
        /*SL:67*/a3 += a2.nextInt(16) + 8;
        /*SL:68*/a4 += a2.nextInt(16) + 8;
        /*SL:69*/this.generate(a1, new BlockPos(a3, 128, a4), a5);
    }
    
    private void generateEnd(final World a1, final Random a2, int a3, int a4, final DropContainer a5) {
        /*SL:74*/a3 += a2.nextInt(16) + 8;
        /*SL:75*/a4 += a2.nextInt(16) + 8;
        /*SL:76*/this.generate(a1, new BlockPos(a3, 100, a4), a5);
    }
    
    private void generate(final World a1, BlockPos a2, final DropContainer a3) {
        /*SL:81*/a2 = this.getSurfacePos(a1, a2);
        /*SL:82*/if (a2 != null) {
            this.block.getDropProcessor().processDrop(a3, new DropProcessData(a1, null, a2));
        }
    }
    
    public void addSurfacedDrop(final DropContainer a1) {
        /*SL:87*/this.addDrop(this.surfaceDrops, a1);
    }
    
    public void addNetherDrop(final DropContainer a1) {
        /*SL:92*/this.addDrop(this.netherDrops, a1);
    }
    
    public void addEndDrop(final DropContainer a1) {
        /*SL:97*/this.addDrop(this.endDrops, a1);
    }
    
    private void addDrop(final ArrayList<DropContainer> a1, final DropContainer a2) {
        /*SL:102*/if (!a2.wasChanceSet()) {
            a2.setChance(300.0f);
        }
        /*SL:103*/a1.add(a2);
    }
    
    private BlockPos getSurfacePos(final World v1, final BlockPos v2) {
        int v3 = /*EL:108*/v2.func_177956_o();
        boolean v4 = /*EL:109*/false;
        /*SL:119*/do {
            final BlockPos a1 = new BlockPos(v2.func_177958_n(), v3, v2.func_177952_p());
            if (this.block.canBlockStay(v1, a1)) {
                v4 = true;
                break;
            }
        } while (--v3 > 0);
        /*SL:121*/return v4 ? new BlockPos(v2.func_177958_n(), v3, v2.func_177952_p()) : null;
    }
}
