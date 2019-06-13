package mod.lucky.util;

import mod.lucky.Lucky;
import net.minecraft.util.MathHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import java.util.Random;

public class CustomStructures
{
    public static Random random;
    
    public static void setRandom(final Random a1) {
        CustomStructures.random = /*EL:19*/a1;
    }
    
    public static void makeCage(final World a1, final EntityPlayer a2, final int a3, final int a4, final int a5, final int a6) {
        /*SL:24*/StructureUtil.fill(a1, a3 - 1, a4 - 1, a5 - 1, 3, 1, 3, Blocks.field_150417_aV);
        /*SL:25*/StructureUtil.fillPerimiter(a1, a3 - 1, a4, a5 - 1, 3, a6, 3, Blocks.field_150411_aY);
    }
    
    public static void makeWaterCage(final World a1, final EntityPlayer a2, final int a3, final int a4, final int a5) {
        /*SL:30*/StructureUtil.fill(a1, a3 - 1, a4 - 1, a5 - 1, 3, 1, 3, Blocks.field_150343_Z);
        /*SL:31*/StructureUtil.fillPerimiter(a1, a3 - 1, a4, a5 - 1, 3, 1, 3, Blocks.field_150343_Z);
        /*SL:32*/StructureUtil.fillPerimiter(a1, a3 - 1, a4 + 1, a5 - 1, 3, 1, 3, Blocks.field_150359_w);
        /*SL:33*/StructureUtil.fill(a1, a3 - 1, a4 + 2, a5 - 1, 3, 1, 3, Blocks.field_150343_Z);
        /*SL:34*/StructureUtil.fill(a1, a3, a4, a5, 1, 2, 1, (Block)Blocks.field_150358_i);
    }
    
    public static void makeFort(final World a1, final EntityPlayer a2, final int a3, final int a4, final int a5) {
        /*SL:39*/StructureUtil.fill(a1, a3 - 1, a4, a5 - 1, 3, 2, 3, Blocks.field_150350_a);
        /*SL:40*/StructureUtil.fill(a1, a3 - 2, a4 - 1, a5 - 2, 5, 1, 5, Blocks.field_150322_A);
        /*SL:41*/StructureUtil.fillPerimiter(a1, a3 - 2, a4, a5 - 2, 5, 2, 5, Blocks.field_150322_A);
        /*SL:42*/StructureUtil.fillCorners(a1, a3 - 2, a4 + 2, a5 - 2, 5, 1, 5, Blocks.field_150340_R);
        /*SL:43*/StructureUtil.setRandomLuckyBlock(a1, CustomStructures.random, a3 + 1, a4, a5 + 1);
        /*SL:44*/StructureUtil.setRandomLuckyBlock(a1, CustomStructures.random, a3 - 1, a4, a5 - 1);
        /*SL:45*/StructureUtil.setRandomLuckyBlock(a1, CustomStructures.random, a3 + 1, a4, a5 - 1);
        /*SL:46*/StructureUtil.setRandomLuckyBlock(a1, CustomStructures.random, a3 - 1, a4, a5 + 1);
    }
    
    public static void makePitTrap(final World a1, final EntityPlayer a2, final int a3, final int a4, final int a5) {
        /*SL:51*/StructureUtil.fill(a1, a3 - 1, a4 - 60, a5 - 1, 3, 60, 3, Blocks.field_150350_a);
        /*SL:52*/StructureUtil.fill(a1, a3 - 1, a4 - 59, a5 - 1, 3, 1, 3, (Block)Blocks.field_150356_k);
        /*SL:53*/StructureUtil.fill(a1, a3 - 1, a4 - 58, a5 - 1, 3, 1, 3, Blocks.field_150321_G);
    }
    
    public static void makeTemple(final World a3, final EntityPlayer a4, final int a5, final int v1, final int v2) {
        /*SL:58*/StructureUtil.fill(a3, a5 - 3, v1 - 1, v2 - 3, 7, 1, 7, Blocks.field_150322_A);
        /*SL:59*/StructureUtil.fillPerimiterStairs(a3, a5 - 3, v1, v2 - 3, 7, 1, 7, (BlockStairs)Blocks.field_150372_bz);
        /*SL:60*/StructureUtil.fill(a3, a5 - 2, v1, v2 - 2, 5, 1, 5, Blocks.field_150350_a);
        /*SL:61*/StructureUtil.fillPerimiterStairs(a3, a5 - 2, v1 + 1, v2 - 2, 5, 1, 5, (BlockStairs)Blocks.field_150372_bz);
        /*SL:62*/StructureUtil.fill(a3, a5 - 1, v1 + 1, v2 - 1, 3, 1, 3, Blocks.field_150350_a);
        /*SL:63*/StructureUtil.fillPerimiterStairs(a3, a5 - 1, v1 + 2, v2 - 1, 3, 1, 3, (BlockStairs)Blocks.field_150372_bz);
        /*SL:64*/StructureUtil.setBlock(a3, a5, v1 + 3, v2, Blocks.field_150340_R);
        /*SL:65*/StructureUtil.setBlock(a3, a5, v1 + 2, v2, Blocks.field_150350_a);
        /*SL:66*/StructureUtil.fillCorners(a3, a5 - 3, v1, v2 - 3, 7, 3, 7, Blocks.field_150322_A);
        /*SL:67*/StructureUtil.fillCorners(a3, a5 - 3, v1 + 3, v2 - 3, 7, 1, 7, Blocks.field_150340_R);
        /*SL:70*/StructureUtil.setBlock(a3, a5, v1 + 1, v2 - 1, Blocks.field_150478_aa, 3);
        /*SL:71*/StructureUtil.setBlock(a3, a5 + 1, v1 + 1, v2, Blocks.field_150478_aa, 2);
        /*SL:72*/StructureUtil.setBlock(a3, a5, v1 + 1, v2 + 1, Blocks.field_150478_aa, 4);
        /*SL:73*/StructureUtil.setBlock(a3, a5 - 1, v1 + 1, v2, Blocks.field_150478_aa, 1);
        /*SL:75*/for (int a6 = a5 - 2; a6 <= a5 + 2; ++a6) {
            /*SL:77*/for (int a7 = v2 - 2; a7 <= v2 + 2; ++a7) {
                /*SL:79*/if (StructureUtil.isCorner(a5, v2, a6, a7, 2) || StructureUtil.isCenterEdge(a5, v2, a6, a7, 2)) {
                    /*SL:81*/StructureUtil.setRandomLuckyBlock(a3, CustomStructures.random, a6, v1, a7);
                }
            }
        }
    }
    
    public static void makeLuckyBlockChoice(final World a1, final EntityPlayer a2, final int a3, final int a4, final int a5, final String[] a6) {
        final int v1 = /*EL:89*/LuckyFunction.getPlayerDirection(a2, 4);
        final int v2 = (CustomStructures.random.nextInt(/*EL:90*/2) == 0) ? 100 : -100;
        final int v3 = /*EL:91*/(v2 == 100) ? -100 : 100;
        /*SL:93*/switch (v1) {
            case 0: {
                /*SL:96*/StructureUtil.setLuckyBlock(a1, a3 - 1, a4, a5 + 2, v2);
                /*SL:97*/StructureUtil.setLuckyBlock(a1, a3 + 1, a4, a5 + 2, v3);
                /*SL:98*/StructureUtil.makeSign(a1, a3, a4, a5 + 2, Blocks.field_150472_an, 8, a6);
                /*SL:99*/break;
            }
            case 1: {
                /*SL:101*/StructureUtil.setLuckyBlock(a1, a3 - 2, a4, a5 - 1, v2);
                /*SL:102*/StructureUtil.setLuckyBlock(a1, a3 - 2, a4, a5 + 1, v3);
                /*SL:103*/StructureUtil.makeSign(a1, a3 - 2, a4, a5, Blocks.field_150472_an, 12, a6);
                /*SL:104*/break;
            }
            case 2: {
                /*SL:106*/StructureUtil.setLuckyBlock(a1, a3 - 1, a4, a5 - 2, v2);
                /*SL:107*/StructureUtil.setLuckyBlock(a1, a3 + 1, a4, a5 - 2, v3);
                /*SL:108*/StructureUtil.makeSign(a1, a3, a4, a5 - 2, Blocks.field_150472_an, 0, a6);
                /*SL:109*/break;
            }
            case 3: {
                /*SL:111*/StructureUtil.setLuckyBlock(a1, a3 + 2, a4, a5 - 1, v2);
                /*SL:112*/StructureUtil.setLuckyBlock(a1, a3 + 2, a4, a5 + 1, v3);
                /*SL:113*/StructureUtil.makeSign(a1, a3 + 2, a4, a5, Blocks.field_150472_an, 4, a6);
                break;
            }
        }
    }
    
    public static void makeWishingWell(final World a1, final EntityPlayer a2, final int a3, final int a4, final int a5, final NBTTagCompound a6) {
        /*SL:120*/StructureUtil.fill(a1, a3, a4, a5, 1, 256, 1, Blocks.field_150350_a);
        /*SL:121*/StructureUtil.fill(a1, a3 - 2, a4, a5 - 2, 5, 20, 5, Blocks.field_150350_a);
        /*SL:122*/StructureUtil.fill(a1, a3 - 1, a4 - 2, a5 - 1, 3, 1, 3, Blocks.field_150339_S);
        /*SL:123*/StructureUtil.fillPerimiter(a1, a3 - 1, a4 - 1, a5 - 1, 3, 2, 3, Blocks.field_150347_e);
        /*SL:124*/StructureUtil.fillCorners(a1, a3 - 1, a4 + 1, a5 - 1, 3, 2, 3, Blocks.field_150422_aJ);
        /*SL:125*/StructureUtil.fillPerimiterStairs(a1, a3 - 1, a4 + 3, a5 - 1, 3, 1, 3, (BlockStairs)Blocks.field_150446_ar);
        /*SL:127*/StructureUtil.setBlock(a1, a3, a4 + 3, a5, Blocks.field_150359_w);
        /*SL:128*/StructureUtil.setBlock(a1, a3, a4 - 1, a5, Blocks.field_150452_aw);
        /*SL:129*/StructureUtil.setBlock(a1, a3, a4, a5, Blocks.field_150355_j);
    }
    
    public static void makeCageSign(final World a1, final EntityPlayer a2, final int a3, final int a4, final int a5, final String[] a6) {
        final int v1 = /*EL:134*/LuckyFunction.getPlayerDirection(a2, 4);
        /*SL:135*/switch (v1) {
            case 0: {
                /*SL:138*/StructureUtil.setBlock(a1, a3, a4 - 1, a5 + 2, Blocks.field_150417_aV);
                /*SL:139*/StructureUtil.makeSign(a1, a3, a4, a5 + 2, Blocks.field_150472_an, 8, a6);
                /*SL:140*/break;
            }
            case 1: {
                /*SL:142*/StructureUtil.setBlock(a1, a3 - 2, a4 - 1, a5, Blocks.field_150417_aV);
                /*SL:143*/StructureUtil.makeSign(a1, a3 - 2, a4, a5, Blocks.field_150472_an, 12, a6);
                /*SL:144*/break;
            }
            case 2: {
                /*SL:146*/StructureUtil.setBlock(a1, a3, a4 - 1, a5 - 2, Blocks.field_150417_aV);
                /*SL:147*/StructureUtil.makeSign(a1, a3, a4, a5 - 2, Blocks.field_150472_an, 0, a6);
                /*SL:148*/break;
            }
            case 3: {
                /*SL:150*/StructureUtil.setBlock(a1, a3 + 2, a4 - 1, a5, Blocks.field_150417_aV);
                /*SL:151*/StructureUtil.makeSign(a1, a3 + 2, a4, a5, Blocks.field_150472_an, 4, a6);
                break;
            }
        }
    }
    
    public static void makeLuckyGenStructure(final World a3, final Random a4, final int a5, final int v1, final int v2) {
        /*SL:158*/StructureUtil.fill(a3, a5 - 2, v1, v2 - 3, 5, 4, 7, Blocks.field_150350_a);
        /*SL:160*/StructureUtil.fill(a3, a5 - 1, v1, v2 - 2, 3, 1, 5, Blocks.field_150371_ca);
        /*SL:161*/StructureUtil.fillPerimiterStairs(a3, a5 - 2, v1, v2 - 3, 5, 1, 7, (BlockStairs)Blocks.field_150370_cb);
        /*SL:163*/for (int a6 = a5 - 1; a6 <= a5 + 1; ++a6) {
            /*SL:165*/for (int a7 = v2 - 2; a7 <= v2 + 2; ++a7) {
                /*SL:167*/if (StructureUtil.isCorner(a5, v2 - 1, a6, a7, 1) || StructureUtil.isCorner(a5, v2 + 1, a6, a7, 1)) {
                    /*SL:169*/StructureUtil.fill(a3, a6, v1 + 1, a7, 1, 3, 1, Blocks.field_150371_ca, 2);
                }
            }
        }
        /*SL:174*/StructureUtil.fillPerimiter(a3, a5 - 2, v1 + 3, v2 - 3, 5, 1, 7, (Block)Blocks.field_150333_U, 15);
        /*SL:175*/StructureUtil.fill(a3, a5 - 1, v1 + 4, v2 - 3, 3, 1, 7, (Block)Blocks.field_150333_U, 7);
        /*SL:177*/StructureUtil.setLuckyBlock(a3, a5, v1 + 1, v2, MathHelper.func_76136_a(a4, 50, 100));
    }
    
    public static void makeUnluckyGenStructure(final World a5, final Random v1, final int v2, final int v3, final int v4) {
        /*SL:182*/for (int a6 = v2 - 3; a6 <= v2 + 3; ++a6) {
            /*SL:184*/for (int a7 = v4 - 3; a7 <= v4 + 3; ++a7) {
                /*SL:186*/if (a6 == v2 - 3 || a6 == v2 + 3 || a7 == v4 - 3 || a7 == v4 + 3) {
                    /*SL:188*/if (v1.nextInt(3) == 0) {
                        setStoneBrickOrNetherrack(a5, v1, a6, v3 - 1, a7);
                    }
                }
                else {
                    setStoneBrickOrNetherrack(/*EL:190*/a5, v1, a6, v3 - 1, a7);
                }
            }
        }
        /*SL:194*/for (int a8 = v2 - 2; a8 <= v2 + 2; ++a8) {
            /*SL:196*/for (int a9 = v4 - 2; a9 <= v4 + 2; ++a9) {
                /*SL:198*/if (v1.nextInt(3) == 0) {
                    setStoneBrickOrNetherrack(a5, v1, a8, v3, a9);
                }
            }
        }
        /*SL:202*/StructureUtil.setLuckyBlock(a5, v2, v3, v4, MathHelper.func_76136_a(v1, -100, -50));
    }
    
    public static void setStoneBrickOrNetherrack(final World a1, final Random a2, final int a3, final int a4, final int a5) {
        /*SL:207*/if (a2.nextInt(3) == 0) {
            StructureUtil.setBlock(a1, a3, a4, a5, Blocks.field_150424_aL);
        }
        else {
            /*SL:208*/StructureUtil.setBlock(a1, a3, a4, a5, Blocks.field_150417_aV, a2.nextInt(3));
        }
    }
    
    public static boolean makeSurfaceLuckyBlock(final World a1, final int a2, final int a3, final int a4, final int a5, final int a6) {
        final int v1 = /*EL:213*/StructureUtil.getSurfacePosY(a1, a2, a3, a4);
        /*SL:216*/return v1 != -1 && Lucky.lucky_block.func_149718_j(a1, a2, v1, a4) && /*EL:218*/StructureUtil.setLuckyBlock(a1, a2, v1, a4, MathHelper.func_76136_a(CustomStructures.random, a5, a6));
    }
    
    static {
        CustomStructures.random = new Random();
    }
}
