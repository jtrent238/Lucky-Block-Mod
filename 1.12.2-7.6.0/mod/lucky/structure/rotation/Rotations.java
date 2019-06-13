package mod.lucky.structure.rotation;

import net.minecraft.block.BlockAnvil;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.BlockTripWireHook;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockVine;
import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.BlockRedstoneComparator;
import net.minecraft.block.BlockRedstoneRepeater;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.BlockDropper;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockWallSign;
import net.minecraft.block.BlockStandingSign;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockRail;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockBanner;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockPistonMoving;
import net.minecraft.block.BlockPistonExtension;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockRedstoneTorch;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.BlockLog;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public final class Rotations
{
    private static StateRotationHandler[] stateRotationHandlers;
    private static TileEntityRotationHandler[] tileEntityRotationHandlers;
    
    public static Vec3d rotatePos(final Vec3d v1, final Vec3d v2, int v3) {
        /*SL:24*/v3 %= 4;
        /*SL:25*/if (v3 < 0) {
            v3 += 4;
        }
        double v4 = /*EL:27*/v1.field_72450_a - v2.field_72450_a;
        final double v5 = /*EL:28*/v1.field_72448_b - v2.field_72448_b;
        double v6 = /*EL:29*/v2.field_72449_c - v1.field_72449_c;
        /*SL:31*/for (double a3 = 0; a3 < v3; ++a3) {
            final double a2 = /*EL:32*/v4;
            /*SL:33*/a3 = /*EL:34*/(v4 = v6);
            /*SL:35*/v6 = -a2;
        }
        /*SL:37*/return new Vec3d(v4 + v2.field_72450_a, v5 + v2.field_72448_b, v2.field_72449_c - v6);
    }
    
    public static BlockPos rotatePos(final BlockPos a1, final BlockPos a2, final int a3) {
        final Vec3d v1 = rotatePos(/*EL:43*/new Vec3d((double)a1.func_177958_n(), (double)a1.func_177956_o(), (double)a1.func_177952_p()), /*EL:44*/new Vec3d((double)a2.func_177958_n(), (double)a2.func_177956_o(), (double)a2.func_177952_p()), a3);
        /*SL:46*/return new BlockPos(v1);
    }
    
    public static BlockPos rotatePos(final BlockPos a1, final Vec3d a2, final int a3) {
        final Vec3d v1 = rotatePos(/*EL:52*/new Vec3d((double)a1.func_177958_n(), (double)a1.func_177956_o(), (double)a1.func_177952_p()), a2, a3);
        /*SL:53*/return new BlockPos(v1);
    }
    
    public static IBlockState rotateState(final IBlockState a1, int a2) {
        /*SL:57*/a2 %= 4;
        /*SL:58*/if (a2 < 0) {
            a2 += 4;
        }
        final StateRotationHandler v1 = /*EL:60*/Rotations.stateRotationHandlers[Block.func_149682_b(a1.func_177230_c())];
        /*SL:61*/if (v1 != null) {
            return v1.rotate(a1, a2);
        }
        /*SL:62*/return a1;
    }
    
    public static NBTTagCompound rotateEntity(final NBTTagCompound v-3, final Vec3d v-2, int v-1) {
        /*SL:67*/v-1 %= 4;
        /*SL:68*/if (v-1 < 0) {
            v-1 += 4;
        }
        /*SL:70*/if (v-3.func_74764_b("Pos")) {
            NBTTagList a1 = /*EL:71*/v-3.func_150295_c("Pos", 6);
            Vec3d a2 = /*EL:73*/new Vec3d(a1.func_150309_d(0), a1.func_150309_d(1), a1.func_150309_d(2));
            /*SL:74*/a2 = rotatePos(a2, v-2, v-1);
            /*SL:75*/a1 = new NBTTagList();
            /*SL:76*/a1.func_74742_a((NBTBase)new NBTTagDouble(a2.field_72450_a));
            /*SL:77*/a1.func_74742_a((NBTBase)new NBTTagDouble(a2.field_72448_b));
            /*SL:78*/a1.func_74742_a((NBTBase)new NBTTagDouble(a2.field_72449_c));
            /*SL:79*/v-3.func_74782_a("Pos", (NBTBase)a1);
        }
        /*SL:82*/if (v-3.func_74764_b("Motion")) {
            NBTTagList a3 = /*EL:83*/v-3.func_150295_c("Motion", 6);
            Vec3d v1 = /*EL:86*/new Vec3d(a3.func_150309_d(0), a3.func_150309_d(1), a3.func_150309_d(2));
            /*SL:87*/v1 = rotatePos(v1, new Vec3d(0.0, 0.0, 0.0), v-1);
            /*SL:88*/a3 = new NBTTagList();
            /*SL:89*/a3.func_74742_a((NBTBase)new NBTTagDouble(v1.field_72450_a));
            /*SL:90*/a3.func_74742_a((NBTBase)new NBTTagDouble(v1.field_72448_b));
            /*SL:91*/a3.func_74742_a((NBTBase)new NBTTagDouble(v1.field_72449_c));
            /*SL:92*/v-3.func_74782_a("Motion", (NBTBase)a3);
        }
        /*SL:95*/if (v-3.func_74764_b("Rotation")) {
            NBTTagList v2 = /*EL:96*/v-3.func_150295_c("Rotation", 5);
            float v3 = /*EL:97*/v2.func_150308_e(0);
            final float v4 = /*EL:98*/v2.func_150308_e(1);
            /*SL:99*/v3 = (v3 + v-1 * 90.0f) % 360.0f;
            /*SL:100*/v2 = new NBTTagList();
            /*SL:101*/v2.func_74742_a((NBTBase)new NBTTagFloat(v3));
            /*SL:102*/v2.func_74742_a((NBTBase)new NBTTagFloat(v4));
            /*SL:103*/v-3.func_74782_a("Rotation", (NBTBase)v2);
        }
        /*SL:105*/return v-3;
    }
    
    public static Entity rotateEntity(final Entity a1, int a2) {
        /*SL:109*/a2 %= 4;
        /*SL:110*/if (a2 < 0) {
            a2 += 4;
        }
        final Vec3d v1 = rotatePos(/*EL:111*/new Vec3d(a1.field_70159_w, a1.field_70181_x, a1.field_70179_y), new Vec3d(0.0, 0.0, 0.0), a2);
        /*SL:116*/a1.field_70159_w = v1.field_72450_a;
        /*SL:117*/a1.field_70181_x = v1.field_72448_b;
        /*SL:118*/a1.field_70179_y = v1.field_72449_c;
        /*SL:119*/a1.field_70177_z = (a1.field_70177_z + a2 * 90.0f) % 360.0f;
        /*SL:120*/return a1;
    }
    
    public static TileEntity rotateTileEntity(final TileEntity a1, int a2) {
        /*SL:124*/a2 %= 4;
        /*SL:125*/if (a2 < 0) {
            a2 += 4;
        }
        final TileEntityRotationHandler v1 = /*EL:127*/Rotations.tileEntityRotationHandlers[Block.func_149682_b(a1.func_145838_q())];
        /*SL:128*/if (v1 != null) {
            v1.rotate(a1, a2);
        }
        /*SL:129*/return a1;
    }
    
    public static void registerRotationHandlers() {
        final EnumFacing[] v1 = /*EL:133*/{ EnumFacing.NORTH, EnumFacing.EAST, EnumFacing.SOUTH, EnumFacing.WEST };
        final Integer[] v2 = /*EL:136*/{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
        /*SL:137*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150364_r)] = new ArrayStateRotationHandler((IProperty)BlockLog.field_176299_a, new Comparable[] { BlockLog.EnumAxis.Z, BlockLog.EnumAxis.X });
        /*SL:139*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150478_aa)] = new ArrayStateRotationHandler((IProperty)BlockTorch.field_176596_a, (Comparable[])v1);
        /*SL:141*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150437_az)] = new ArrayStateRotationHandler((IProperty)BlockRedstoneTorch.field_176596_a, (Comparable[])v1);
        /*SL:143*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150429_aA)] = new ArrayStateRotationHandler((IProperty)BlockRedstoneTorch.field_176596_a, (Comparable[])v1);
        /*SL:145*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150324_C)] = new ArrayStateRotationHandler((IProperty)BlockBed.field_185512_D, (Comparable[])v1);
        /*SL:147*/Rotations.stateRotationHandlers[Block.func_149682_b((Block)Blocks.field_150331_J)] = new ArrayStateRotationHandler((IProperty)BlockPistonBase.field_176387_N, (Comparable[])v1);
        /*SL:149*/Rotations.stateRotationHandlers[Block.func_149682_b((Block)Blocks.field_150320_F)] = new ArrayStateRotationHandler((IProperty)BlockPistonBase.field_176387_N, (Comparable[])v1);
        /*SL:151*/Rotations.stateRotationHandlers[Block.func_149682_b((Block)Blocks.field_150332_K)] = new ArrayStateRotationHandler((IProperty)BlockPistonExtension.field_176387_N, (Comparable[])v1);
        /*SL:153*/Rotations.stateRotationHandlers[Block.func_149682_b((Block)Blocks.field_180384_M)] = new ArrayStateRotationHandler((IProperty)BlockPistonMoving.field_176426_a, (Comparable[])v1);
        /*SL:155*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150476_ad)] = new ArrayStateRotationHandler((IProperty)BlockStairs.field_176309_a, (Comparable[])v1);
        /*SL:157*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150446_ar)] = new ArrayStateRotationHandler((IProperty)BlockStairs.field_176309_a, (Comparable[])v1);
        /*SL:159*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150389_bf)] = new ArrayStateRotationHandler((IProperty)BlockStairs.field_176309_a, (Comparable[])v1);
        /*SL:161*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150390_bg)] = new ArrayStateRotationHandler((IProperty)BlockStairs.field_176309_a, (Comparable[])v1);
        /*SL:163*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150387_bl)] = new ArrayStateRotationHandler((IProperty)BlockStairs.field_176309_a, (Comparable[])v1);
        /*SL:165*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150372_bz)] = new ArrayStateRotationHandler((IProperty)BlockStairs.field_176309_a, (Comparable[])v1);
        /*SL:167*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150485_bF)] = new ArrayStateRotationHandler((IProperty)BlockStairs.field_176309_a, (Comparable[])v1);
        /*SL:169*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150487_bG)] = new ArrayStateRotationHandler((IProperty)BlockStairs.field_176309_a, (Comparable[])v1);
        /*SL:171*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150481_bH)] = new ArrayStateRotationHandler((IProperty)BlockStairs.field_176309_a, (Comparable[])v1);
        /*SL:173*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150370_cb)] = new ArrayStateRotationHandler((IProperty)BlockStairs.field_176309_a, (Comparable[])v1);
        /*SL:175*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150400_ck)] = new ArrayStateRotationHandler((IProperty)BlockStairs.field_176309_a, (Comparable[])v1);
        /*SL:177*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150401_cl)] = new ArrayStateRotationHandler((IProperty)BlockStairs.field_176309_a, (Comparable[])v1);
        /*SL:179*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_180396_cN)] = new ArrayStateRotationHandler((IProperty)BlockStairs.field_176309_a, (Comparable[])v1);
        /*SL:181*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_180393_cK)] = new ArrayStateRotationHandler((IProperty)BlockBanner.field_176448_b, (Comparable[])v2);
        /*SL:183*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_180394_cL)] = new ArrayStateRotationHandler((IProperty)BlockStairs.field_176309_a, (Comparable[])v1);
        /*SL:185*/Rotations.stateRotationHandlers[Block.func_149682_b((Block)Blocks.field_180413_ao)] = new ArrayStateRotationHandler((IProperty)BlockDoor.field_176520_a, (Comparable[])v1);
        /*SL:187*/Rotations.stateRotationHandlers[Block.func_149682_b((Block)Blocks.field_150454_av)] = new ArrayStateRotationHandler((IProperty)BlockDoor.field_176520_a, (Comparable[])v1);
        /*SL:189*/Rotations.stateRotationHandlers[Block.func_149682_b((Block)Blocks.field_180414_ap)] = new ArrayStateRotationHandler((IProperty)BlockDoor.field_176520_a, (Comparable[])v1);
        /*SL:191*/Rotations.stateRotationHandlers[Block.func_149682_b((Block)Blocks.field_180412_aq)] = new ArrayStateRotationHandler((IProperty)BlockDoor.field_176520_a, (Comparable[])v1);
        /*SL:193*/Rotations.stateRotationHandlers[Block.func_149682_b((Block)Blocks.field_180411_ar)] = new ArrayStateRotationHandler((IProperty)BlockDoor.field_176520_a, (Comparable[])v1);
        /*SL:195*/Rotations.stateRotationHandlers[Block.func_149682_b((Block)Blocks.field_180410_as)] = new ArrayStateRotationHandler((IProperty)BlockDoor.field_176520_a, (Comparable[])v1);
        /*SL:197*/Rotations.stateRotationHandlers[Block.func_149682_b((Block)Blocks.field_180409_at)] = new ArrayStateRotationHandler((IProperty)BlockDoor.field_176520_a, (Comparable[])v1);
        final ArrayStateRotationHandler v3 = /*EL:199*/new ArrayStateRotationHandler((IProperty)BlockRail.field_176565_b, new Comparable[] { BlockRailBase.EnumRailDirection.NORTH_SOUTH, BlockRailBase.EnumRailDirection.EAST_WEST });
        final ArrayStateRotationHandler v4 = /*EL:202*/new ArrayStateRotationHandler((IProperty)BlockRail.field_176565_b, new Comparable[] { BlockRailBase.EnumRailDirection.ASCENDING_NORTH, BlockRailBase.EnumRailDirection.ASCENDING_EAST, BlockRailBase.EnumRailDirection.ASCENDING_SOUTH, BlockRailBase.EnumRailDirection.ASCENDING_WEST });
        final ArrayStateRotationHandler v5 = /*EL:209*/new ArrayStateRotationHandler((IProperty)BlockRail.field_176565_b, new Comparable[] { BlockRailBase.EnumRailDirection.NORTH_EAST, BlockRailBase.EnumRailDirection.SOUTH_EAST, BlockRailBase.EnumRailDirection.SOUTH_WEST, BlockRailBase.EnumRailDirection.NORTH_WEST });
        /*SL:216*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150318_D)] = new MultipleStateRotationHandler(new StateRotationHandler[] { v3, v4, v5 });
        /*SL:218*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150319_E)] = new MultipleStateRotationHandler(new StateRotationHandler[] { v3, v4, v5 });
        /*SL:220*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150448_aq)] = new MultipleStateRotationHandler(new StateRotationHandler[] { v3, v4, v5 });
        /*SL:222*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150408_cc)] = new MultipleStateRotationHandler(new StateRotationHandler[] { v3, v4, v5 });
        /*SL:224*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150468_ap)] = new ArrayStateRotationHandler((IProperty)BlockLadder.field_176382_a, (Comparable[])v1);
        /*SL:226*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150460_al)] = new ArrayStateRotationHandler((IProperty)BlockFurnace.field_176447_a, (Comparable[])v1);
        /*SL:228*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150470_am)] = new ArrayStateRotationHandler((IProperty)BlockFurnace.field_176447_a, (Comparable[])v1);
        /*SL:230*/Rotations.stateRotationHandlers[Block.func_149682_b((Block)Blocks.field_150486_ae)] = new ArrayStateRotationHandler((IProperty)BlockChest.field_176459_a, (Comparable[])v1);
        /*SL:232*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150477_bB)] = new ArrayStateRotationHandler((IProperty)BlockEnderChest.field_176437_a, (Comparable[])v1);
        /*SL:234*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150447_bR)] = new ArrayStateRotationHandler((IProperty)BlockChest.field_176459_a, (Comparable[])v1);
        /*SL:236*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150472_an)] = new ArrayStateRotationHandler((IProperty)BlockStandingSign.field_176413_a, (Comparable[])v2);
        /*SL:238*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150444_as)] = new ArrayStateRotationHandler((IProperty)BlockWallSign.field_176412_a, (Comparable[])v1);
        /*SL:240*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150367_z)] = new ArrayStateRotationHandler((IProperty)BlockDispenser.field_176441_a, (Comparable[])v1);
        /*SL:242*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150409_cd)] = new ArrayStateRotationHandler((IProperty)BlockDropper.field_176441_a, (Comparable[])v1);
        /*SL:244*/Rotations.stateRotationHandlers[Block.func_149682_b((Block)Blocks.field_150438_bZ)] = new ArrayStateRotationHandler((IProperty)BlockHopper.field_176430_a, (Comparable[])v1);
        final ArrayStateRotationHandler v6 = /*EL:246*/new ArrayStateRotationHandler((IProperty)BlockLever.field_176360_a, new Comparable[] { BlockLever.EnumOrientation.DOWN_Z, BlockLever.EnumOrientation.DOWN_X });
        final ArrayStateRotationHandler v7 = /*EL:251*/new ArrayStateRotationHandler((IProperty)BlockLever.field_176360_a, new Comparable[] { BlockLever.EnumOrientation.NORTH, BlockLever.EnumOrientation.EAST, BlockLever.EnumOrientation.SOUTH, BlockLever.EnumOrientation.WEST });
        final ArrayStateRotationHandler v8 = /*EL:258*/new ArrayStateRotationHandler((IProperty)BlockLever.field_176360_a, new Comparable[] { BlockLever.EnumOrientation.UP_Z, BlockLever.EnumOrientation.UP_X });
        /*SL:261*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150442_at)] = new MultipleStateRotationHandler(new StateRotationHandler[] { v6, v7, v8 });
        /*SL:263*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150430_aB)] = new ArrayStateRotationHandler((IProperty)BlockButton.field_176387_N, (Comparable[])v1);
        /*SL:265*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150471_bO)] = new ArrayStateRotationHandler((IProperty)BlockButton.field_176387_N, (Comparable[])v1);
        /*SL:267*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150423_aK)] = new ArrayStateRotationHandler((IProperty)BlockPumpkin.field_185512_D, (Comparable[])v1);
        /*SL:269*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150428_aP)] = new ArrayStateRotationHandler((IProperty)BlockPumpkin.field_185512_D, (Comparable[])v1);
        /*SL:271*/Rotations.stateRotationHandlers[Block.func_149682_b((Block)Blocks.field_150413_aR)] = new ArrayStateRotationHandler((IProperty)BlockRedstoneRepeater.field_185512_D, (Comparable[])v1);
        /*SL:273*/Rotations.stateRotationHandlers[Block.func_149682_b((Block)Blocks.field_150416_aS)] = new ArrayStateRotationHandler((IProperty)BlockRedstoneRepeater.field_185512_D, (Comparable[])v1);
        /*SL:275*/Rotations.stateRotationHandlers[Block.func_149682_b((Block)Blocks.field_150441_bU)] = new ArrayStateRotationHandler((IProperty)BlockRedstoneComparator.field_185512_D, (Comparable[])v1);
        /*SL:277*/Rotations.stateRotationHandlers[Block.func_149682_b((Block)Blocks.field_150455_bV)] = new ArrayStateRotationHandler((IProperty)BlockRedstoneComparator.field_185512_D, (Comparable[])v1);
        /*SL:279*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150415_aT)] = new ArrayStateRotationHandler((IProperty)BlockTrapDoor.field_176284_a, (Comparable[])v1);
        /*SL:281*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_180400_cw)] = new ArrayStateRotationHandler((IProperty)BlockTrapDoor.field_176284_a, (Comparable[])v1);
        final ArrayStateRotationHandler v9 = /*EL:283*/new ArrayStateRotationHandler((IProperty)BlockHugeMushroom.field_176380_a, new Comparable[] { BlockHugeMushroom.EnumType.NORTH, BlockHugeMushroom.EnumType.EAST, BlockHugeMushroom.EnumType.SOUTH, BlockHugeMushroom.EnumType.WEST });
        final ArrayStateRotationHandler v10 = /*EL:290*/new ArrayStateRotationHandler((IProperty)BlockHugeMushroom.field_176380_a, new Comparable[] { BlockHugeMushroom.EnumType.NORTH_EAST, BlockHugeMushroom.EnumType.SOUTH_EAST, BlockHugeMushroom.EnumType.SOUTH_WEST, BlockHugeMushroom.EnumType.NORTH_WEST });
        /*SL:297*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150419_aX)] = new MultipleStateRotationHandler(new StateRotationHandler[] { v9, v10 });
        /*SL:299*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150420_aW)] = new MultipleStateRotationHandler(new StateRotationHandler[] { v9, v10 });
        /*SL:301*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150395_bd)] = new StateRotationHandler() {
            @Override
            public IBlockState rotate(final IBlockState v-7, final int v-6) {
                Boolean b = /*EL:305*/(Boolean)v-7.func_177229_b((IProperty)BlockVine.field_176273_b);
                Boolean b2 = /*EL:306*/(Boolean)v-7.func_177229_b((IProperty)BlockVine.field_176278_M);
                Boolean b3 = /*EL:307*/(Boolean)v-7.func_177229_b((IProperty)BlockVine.field_176279_N);
                Boolean b4 = /*EL:308*/(Boolean)v-7.func_177229_b((IProperty)BlockVine.field_176280_O);
                /*SL:311*/for (Boolean v3 = (Object)0; v3 < v-6; ++v3) {
                    final Boolean a1 = /*EL:312*/b4;
                    final Boolean a2 = /*EL:313*/b;
                    final Boolean v2 = /*EL:314*/b2;
                    /*SL:315*/v3 = b3;
                    /*SL:316*/b = a1;
                    /*SL:317*/b2 = a2;
                    /*SL:318*/b3 = v2;
                    /*SL:319*/b4 = v3;
                }
                /*SL:322*/return v-7.func_177226_a((IProperty)BlockVine.field_176273_b, (Comparable)b).func_177226_a((IProperty)BlockVine.field_176278_M, /*EL:323*/(Comparable)b2).func_177226_a((IProperty)BlockVine.field_176279_N, /*EL:324*/(Comparable)b3).func_177226_a((IProperty)BlockVine.field_176280_O, /*EL:325*/(Comparable)b4);
            }
        };
        /*SL:329*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_180390_bo)] = new ArrayStateRotationHandler((IProperty)BlockFenceGate.field_185512_D, (Comparable[])v1);
        /*SL:331*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_180391_bp)] = new ArrayStateRotationHandler((IProperty)BlockFenceGate.field_185512_D, (Comparable[])v1);
        /*SL:333*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_180392_bq)] = new ArrayStateRotationHandler((IProperty)BlockFenceGate.field_185512_D, (Comparable[])v1);
        /*SL:335*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_180386_br)] = new ArrayStateRotationHandler((IProperty)BlockFenceGate.field_185512_D, (Comparable[])v1);
        /*SL:337*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_180385_bs)] = new ArrayStateRotationHandler((IProperty)BlockFenceGate.field_185512_D, (Comparable[])v1);
        /*SL:339*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_180387_bt)] = new ArrayStateRotationHandler((IProperty)BlockFenceGate.field_185512_D, (Comparable[])v1);
        /*SL:341*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150378_br)] = new ArrayStateRotationHandler((IProperty)BlockEndPortalFrame.field_176508_a, (Comparable[])v1);
        /*SL:343*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150375_by)] = new ArrayStateRotationHandler((IProperty)BlockCocoa.field_185512_D, (Comparable[])v1);
        /*SL:345*/Rotations.stateRotationHandlers[Block.func_149682_b((Block)Blocks.field_150479_bC)] = new ArrayStateRotationHandler((IProperty)BlockTripWireHook.field_176264_a, (Comparable[])v1);
        /*SL:347*/Rotations.stateRotationHandlers[Block.func_149682_b((Block)Blocks.field_150465_bP)] = new ArrayStateRotationHandler((IProperty)BlockSkull.field_176418_a, (Comparable[])v1);
        /*SL:349*/Rotations.tileEntityRotationHandlers[Block.func_149682_b((Block)Blocks.field_150465_bP)] = new TileEntityRotationHandler() {
            @Override
            public void rotate(final TileEntity a1, final int a2) {
                final TileEntitySkull v1 = /*EL:353*/(TileEntitySkull)a1;
                /*SL:354*/v1.func_145903_a((v1.func_145906_b() + /*EL:355*/a2 * 4) % 16);
            }
        };
        /*SL:358*/Rotations.stateRotationHandlers[Block.func_149682_b(Blocks.field_150467_bQ)] = new ArrayStateRotationHandler((IProperty)BlockAnvil.field_176506_a, (Comparable[])v1);
    }
    
    static {
        Rotations.stateRotationHandlers = new StateRotationHandler[30000];
        Rotations.tileEntityRotationHandlers = new TileEntityRotationHandler[30000];
    }
}
