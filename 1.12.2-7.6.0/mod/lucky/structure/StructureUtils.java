package mod.lucky.structure;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.Vec3d;
import mod.lucky.structure.rotation.Rotations;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.BlockPos;

public class StructureUtils
{
    public static BlockPos getWorldPos(final BlockPos a1, final BlockPos a2, final BlockPos a3, final int a4) {
        /*SL:16*/return Rotations.rotatePos(a3.func_177971_a((Vec3i)a1).func_177973_b(/*EL:17*/(Vec3i)a2), a3, a4);
    }
    
    public static BlockPos getWorldPos(final BlockPos a1, final Vec3d a2, final Vec3d a3, final int a4) {
        /*SL:22*/return new BlockPos(getWorldPos(/*EL:24*/new Vec3d(a1.func_177958_n() + 0.5, (double)a1.func_177956_o(), a1.func_177952_p() + 0.5), a2, a3, a4));
    }
    
    public static Vec3d getWorldPos(final Vec3d a1, final Vec3d a2, final Vec3d a3, final int a4) {
        /*SL:32*/return Rotations.rotatePos(a3.func_178787_e(a1).func_178788_d(/*EL:33*/a2), a3, a4);
    }
    
    public static void setBlock(final BlockPlacer a1, final IBlockState a2, final BlockPos a3, final Vec3d a4, final Vec3d a5, final int a6) {
        /*SL:43*/a1.add(/*EL:44*/Rotations.rotateState(a2, a6), getWorldPos(a3, a4, a5, a6));
    }
    
    public static void setTileEntity(final World a1, final NBTTagCompound a2, final BlockPos a3, final Vec3d a4, final Vec3d a5, final int a6) {
        final BlockPos v1 = getWorldPos(/*EL:55*/a3, a4, a5, a6);
        final IBlockState v2 = /*EL:56*/a1.func_180495_p(v1);
        /*SL:58*/a1.func_175713_t(v1);
        final BlockPos v3 = /*EL:59*/new BlockPos(v1.func_177958_n() & 0xF, v1.func_177956_o(), v1.func_177952_p() & 0xF);
        TileEntity v4 = /*EL:60*/a1.func_175726_f(v1).func_177424_a(/*EL:62*/v3, Chunk.EnumCreateEntityType.CHECK);
        /*SL:65*/v4 = v2.func_177230_c().createTileEntity(a1, v2);
        /*SL:66*/v4.func_145839_a(a2);
        /*SL:67*/v4.func_174878_a(v1);
        /*SL:68*/v4.func_145834_a(a1);
        /*SL:69*/Rotations.rotateTileEntity(v4, a6);
        /*SL:71*/a1.func_175690_a(v1, v4);
        /*SL:72*/v4.func_145836_u();
    }
    
    public static void setTileEntity(final World a1, final TileEntity a2, final Vec3d a3, final Vec3d a4, final int a5) {
        final BlockPos v1 = getWorldPos(/*EL:77*/a2.func_174877_v(), a3, a4, a5);
        /*SL:78*/a1.func_175713_t(v1);
        /*SL:79*/a2.func_174878_a(v1);
        /*SL:80*/a2.func_145834_a(a1);
        /*SL:81*/Rotations.rotateTileEntity(a2, a5);
        /*SL:82*/a1.func_175690_a(v1, a2);
    }
    
    public static void setEntity(final World a1, final Entity a2, final Vec3d a3, final Vec3d a4, final int a5) {
        final Vec3d v1 = getWorldPos(/*EL:87*/a2.func_174791_d(), a3, a4, a5);
        /*SL:88*/a2.func_70107_b(v1.field_72450_a, v1.field_72448_b, v1.field_72449_c);
        /*SL:89*/Rotations.rotateEntity(a2, a5);
        /*SL:90*/a1.func_72838_d(a2);
    }
}
