package mod.lucky.util;

import net.minecraft.util.MathHelper;
import java.util.Random;
import mod.lucky.tileentity.TileEntityLuckyBlock;
import mod.lucky.Lucky;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.init.Blocks;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class StructureUtil
{
    public static int getEdgeDistance(final int a1, final int a2) {
        int v1 = /*EL:18*/a2 - a1;
        /*SL:19*/if (v1 < 0) {
            v1 *= -1;
        }
        /*SL:21*/return v1;
    }
    
    public static boolean isEdgeSingleSide(final int a1, final int a2, final int a3) {
        int v1 = /*EL:26*/a2 - a1;
        /*SL:27*/if (v1 < 0) {
            v1 *= -1;
        }
        /*SL:29*/return v1 == a3;
    }
    
    public static boolean isEdge(final int a1, final int a2, final int a3, final int a4, final int a5) {
        /*SL:36*/return (getEdgeDistance(a1, a3) == a5 && getEdgeDistance(a2, a4) <= a5) || (getEdgeDistance(/*EL:37*/a2, a4) == a5 && getEdgeDistance(a1, a3) <= a5);
    }
    
    public static boolean isCenterEdge(final int a1, final int a2, final int a3, final int a4, final int a5) {
        /*SL:44*/return (getEdgeDistance(a1, a3) == a5 && getEdgeDistance(a2, a4) == 0) || (getEdgeDistance(/*EL:45*/a2, a4) == a5 && getEdgeDistance(a1, a3) == 0);
    }
    
    public static boolean isCorner(final int a1, final int a2, final int a3, final int a4, final int a5) {
        /*SL:52*/return getEdgeDistance(a1, a3) == a5 && getEdgeDistance(a2, a4) == a5;
    }
    
    public static void fill(final World a4, final int a5, final int a6, final int a7, final int a8, final int v1, final int v2, final Block v3) {
        /*SL:58*/for (int a9 = a5; a9 < a5 + a8; ++a9) {
            /*SL:60*/for (int a10 = a6; a10 < a6 + v1; ++a10) {
                /*SL:62*/for (int a11 = a7; a11 < a7 + v2; ++a11) {
                    setBlock(/*EL:64*/a4, a9, a10, a11, v3);
                }
            }
        }
    }
    
    public static void fill(final World a4, final int a5, final int a6, final int a7, final int a8, final int a9, final int v1, final Block v2, final int v3) {
        /*SL:72*/for (int a10 = a5; a10 < a5 + a8; ++a10) {
            /*SL:74*/for (int a11 = a6; a11 < a6 + a9; ++a11) {
                /*SL:76*/for (int a12 = a7; a12 < a7 + v1; ++a12) {
                    setBlock(/*EL:78*/a4, a10, a11, a12, v2, v3);
                }
            }
        }
    }
    
    public static void fillHollow(final World a4, final int a5, final int a6, final int a7, final int a8, final int v1, final int v2, final Block v3) {
        /*SL:86*/for (int a9 = a5; a9 < a5 + a8; ++a9) {
            /*SL:88*/for (int a10 = a6; a10 < a6 + v1; ++a10) {
                /*SL:90*/for (int a11 = a7; a11 < a7 + v2; ++a11) {
                    /*SL:92*/if (a9 == a5 || a9 == a5 + a8 - 1 || a10 == a6 || a10 == a6 + v1 - 1 || a11 == a7 || a11 == a7 + v2 - 1) {
                        setBlock(a4, a9, a10, a11, v3);
                    }
                }
            }
        }
    }
    
    public static void fillHollow(final World a4, final int a5, final int a6, final int a7, final int a8, final int a9, final int v1, final Block v2, final int v3) {
        /*SL:100*/for (int a10 = a5; a10 < a5 + a8; ++a10) {
            /*SL:102*/for (int a11 = a6; a11 < a6 + a9; ++a11) {
                /*SL:104*/for (int a12 = a7; a12 < a7 + v1; ++a12) {
                    /*SL:106*/if (a10 == a5 || a10 == a5 + a8 - 1 || a11 == a6 || a11 == a6 + a9 - 1 || a12 == a7 || a12 == a7 + v1 - 1) {
                        setBlock(a4, a10, a11, a12, v2, v3);
                    }
                }
            }
        }
    }
    
    public static void fillPerimiter(final World a4, final int a5, final int a6, final int a7, final int a8, final int v1, final int v2, final Block v3) {
        /*SL:114*/for (int a9 = a5; a9 < a5 + a8; ++a9) {
            /*SL:116*/for (int a10 = a6; a10 < a6 + v1; ++a10) {
                /*SL:118*/for (int a11 = a7; a11 < a7 + v2; ++a11) {
                    /*SL:120*/if (a9 == a5 || a9 == a5 + a8 - 1 || a11 == a7 || a11 == a7 + v2 - 1) {
                        setBlock(a4, a9, a10, a11, v3);
                    }
                }
            }
        }
    }
    
    public static void fillPerimiter(final World a4, final int a5, final int a6, final int a7, final int a8, final int a9, final int v1, final Block v2, final int v3) {
        /*SL:128*/for (int a10 = a5; a10 < a5 + a8; ++a10) {
            /*SL:130*/for (int a11 = a6; a11 < a6 + a9; ++a11) {
                /*SL:132*/for (int a12 = a7; a12 < a7 + v1; ++a12) {
                    /*SL:134*/if (a10 == a5 || a10 == a5 + a8 - 1 || a12 == a7 || a12 == a7 + v1 - 1) {
                        setBlock(a4, a10, a11, a12, v2, v3);
                    }
                }
            }
        }
    }
    
    public static void fillPerimiterStairs(final World a2, final int a3, final int a4, final int a5, final int a6, final int a7, final int a8, final BlockStairs v1) {
        /*SL:142*/for (int a9 = a4; a9 <= a4 + a7 - 1; ++a9) {
            fill(/*EL:144*/a2, a3 + 1, a9, a5, a6 - 1, 1, 1, (Block)v1, 2);
            fill(/*EL:145*/a2, a3 + 1, a9, a5 + a8 - 1, a6 - 1, 1, 1, (Block)v1, 3);
            fill(/*EL:146*/a2, a3, a9, a5, 1, 1, a8, (Block)v1, 0);
            fill(/*EL:147*/a2, a3 + a6 - 1, a9, a5, 1, 1, a8, (Block)v1, 1);
        }
    }
    
    public static void fillCorners(final World a4, final int a5, final int a6, final int a7, final int a8, final int v1, final int v2, final Block v3) {
        /*SL:153*/for (int a9 = a5; a9 < a5 + a8; ++a9) {
            /*SL:155*/for (int a10 = a6; a10 < a6 + v1; ++a10) {
                /*SL:157*/for (int a11 = a7; a11 < a7 + v2; ++a11) {
                    /*SL:159*/if ((a9 == a5 || a9 == a5 + a8 - 1) && (a11 == a7 || a11 == a7 + v2 - 1)) {
                        setBlock(a4, a9, a10, a11, v3);
                    }
                }
            }
        }
    }
    
    public static void fillCorners(final World a4, final int a5, final int a6, final int a7, final int a8, final int a9, final int v1, final Block v2, final int v3) {
        /*SL:167*/for (int a10 = a5; a10 < a5 + a8; ++a10) {
            /*SL:169*/for (int a11 = a6; a11 < a6 + a9; ++a11) {
                /*SL:171*/for (int a12 = a7; a12 < a7 + v1; ++a12) {
                    /*SL:173*/if ((a10 == a5 || a10 == a5 + a8 - 1) && (a12 == a7 || a12 == a7 + v1 - 1)) {
                        setBlock(a4, a10, a11, a12, v2, v3);
                    }
                }
            }
        }
    }
    
    public static void makeSign(final World a2, final int a3, final int a4, final int a5, final Block a6, final int a7, final String[] v1) {
        setBlock(/*EL:181*/a2, a3, a4, a5, a6, a7);
        setBlock(/*EL:182*/a2, a3, a4 + 1, a5, Blocks.field_150350_a);
        final TileEntitySign v2 = /*EL:184*/new TileEntitySign();
        /*SL:185*/for (int a8 = 0; a8 < 4; ++a8) {
            /*SL:187*/v2.field_145915_a[a8] = v1[a8];
        }
        /*SL:190*/a2.func_147455_a(a3, a4, a5, (TileEntity)v2);
    }
    
    public static int getSurfacePosY(final World a1, final int a2, final int a3, final int a4) {
        int v1 = /*EL:195*/a3;
        boolean v2 = /*EL:196*/false;
        Block v3 = /*EL:198*/null;
        Block v4 = /*EL:199*/null;
        /*SL:216*/do {
            v3 = a1.func_147439_a(a2, v1, a4);
            if (v3 != null && v4 != null && ((v3.func_149662_c() && v3 != Blocks.field_150362_t) || v3 == Blocks.field_150432_aD || v3 == Blocks.field_150355_j || v3 == Blocks.field_150358_i || v3 == Blocks.field_150353_l || v3 == Blocks.field_150356_k) && !v4.func_149662_c()) {
                v2 = true;
                break;
            }
            --v1;
            v4 = v3;
        } while (v1 > 0);
        /*SL:218*/++v1;
        /*SL:220*/return v2 ? v1 : -1;
    }
    
    public static boolean setBlock(final World a2, final int a3, final int a4, final int a5, final Block v1) {
        try {
            /*SL:227*/return a2.func_147465_d(a3, a4, a5, v1, 0, 3);
        }
        catch (Exception a6) {
            /*SL:232*/a6.printStackTrace();
            /*SL:233*/return false;
        }
    }
    
    public static boolean setBlock(final World a2, final int a3, final int a4, final int a5, final Block a6, final int v1) {
        try {
            /*SL:241*/return a2.func_147465_d(a3, a4, a5, a6, v1, 3);
        }
        catch (Exception a7) {
            /*SL:246*/a7.printStackTrace();
            /*SL:247*/return false;
        }
    }
    
    public static boolean setLuckyBlock(final World a3, final int a4, final int a5, final int v1, final int v2) {
        try {
            setBlock(/*EL:255*/a3, a4, a5, v1, Lucky.lucky_block);
            /*SL:257*/if (v2 != 0) {
                final TileEntityLuckyBlock a6 = /*EL:259*/new TileEntityLuckyBlock();
                /*SL:260*/a6.setLuck(v2);
                /*SL:261*/a6.func_70296_d();
                /*SL:262*/a3.func_147455_a(a4, a5, v1, (TileEntity)a6);
                /*SL:263*/a3.func_147471_g(a4, a5, v1);
            }
            /*SL:266*/return true;
        }
        catch (Exception a7) {
            /*SL:270*/a7.printStackTrace();
            /*SL:271*/return false;
        }
    }
    
    public static boolean setRandomLuckyBlock(final World a4, final Random a5, final int v1, final int v2, final int v3) {
        try {
            setBlock(/*EL:279*/a4, v1, v2, v3, Lucky.lucky_block);
            final int a6 = /*EL:280*/MathHelper.func_76136_a(a5, -100, 100);
            /*SL:282*/if (a6 != 0) {
                final TileEntityLuckyBlock a7 = /*EL:284*/new TileEntityLuckyBlock();
                /*SL:286*/a7.setLuck(a6);
                /*SL:287*/a7.func_70296_d();
                /*SL:289*/a4.func_147455_a(v1, v2, v3, (TileEntity)a7);
                /*SL:290*/a4.func_147471_g(v1, v2, v3);
            }
            /*SL:293*/return true;
        }
        catch (Exception a8) {
            /*SL:297*/a8.printStackTrace();
            /*SL:298*/return false;
        }
    }
    
    public static boolean setBlockMeta(final World a2, final int a3, final int a4, final int a5, final int v1) {
        try {
            /*SL:306*/a2.func_72921_c(a3, a4, a5, v1, 3);
            /*SL:307*/return true;
        }
        catch (Exception a6) {
            /*SL:311*/a6.printStackTrace();
            /*SL:312*/return false;
        }
    }
}
