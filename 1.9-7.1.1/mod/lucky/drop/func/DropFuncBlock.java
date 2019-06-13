package mod.lucky.drop.func;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.block.state.IBlockState;
import mod.lucky.drop.DropProperties;

public class DropFuncBlock extends DropFunction
{
    @Override
    public void process(final DropProcessData a1) {
        final DropProperties v1 = /*EL:17*/a1.getDropProperties();
        final IBlockState v2 = /*EL:18*/v1.getBlockState();
        /*SL:19*/if (v1.getPropertyBoolean("blockUpdate")) {
            a1.getWorld().func_180501_a(v1.getBlockPos(), v2, 3);
        }
        else {
            setBlock(/*EL:20*/a1.getWorld(), v2, v1.getBlockPos(), v1.getPropertyNBT("NBTTag"), v1.getPropertyBoolean("blockUpdate"));
        }
        setTileEntity(/*EL:21*/a1.getWorld(), v2, v1.getBlockPos(), v1.getPropertyNBT("NBTTag"));
    }
    
    @Override
    public void registerProperties() {
        /*SL:27*/DropProperties.setDefaultProperty(this.getType(), "tileEntity", NBTTagCompound.class, null);
        /*SL:28*/DropProperties.setDefaultProperty(this.getType(), "blockUpdate", Boolean.class, true);
        /*SL:29*/DropProperties.setReplaceProperty("meta", "damage");
        /*SL:30*/DropProperties.setReplaceProperty("state", "damage");
        /*SL:31*/DropProperties.setReplaceProperty("tileEntity", "NBTTag");
    }
    
    @Override
    public String getType() {
        /*SL:37*/return "block";
    }
    
    public static void setBlock(final World a1, final IBlockState a2, final BlockPos a3, final boolean a4) {
        setBlock(/*EL:42*/a1, a2, a3, null, a4);
    }
    
    public static void setBlock(final World a4, final IBlockState a5, final BlockPos v1, final NBTTagCompound v2, final boolean v3) {
        final Chunk v4 = /*EL:47*/a4.func_175726_f(v1);
        ExtendedBlockStorage v5 = /*EL:48*/v4.func_76587_i()[v1.func_177956_o() >> 4];
        /*SL:49*/if (v5 == null) {
            final ExtendedBlockStorage[] func_76587_i = v4.func_76587_i();
            final int n = v1.func_177956_o() >> 4;
            final ExtendedBlockStorage extendedBlockStorage = new ExtendedBlockStorage(v1.func_177956_o() >> 4 << 4, !a4.field_73011_w.func_177495_o());
            func_76587_i[n] = extendedBlockStorage;
            v5 = extendedBlockStorage;
        }
        /*SL:51*/if (v5.func_177485_a(v1.func_177958_n() & 0xF, v1.func_177956_o() & 0xF, v1.func_177952_p() & 0xF) != a5.func_177230_c()) {
            final IBlockState a6 = /*EL:53*/a4.func_180495_p(v1);
            /*SL:54*/v5.func_177484_a(v1.func_177958_n() & 0xF, v1.func_177956_o() & 0xF, v1.func_177952_p() & 0xF, a5);
            /*SL:55*/v4.func_177427_f(true);
            /*SL:56*/a4.markAndNotifyBlock(v1, v4, a6, a5, 3);
            /*SL:57*/a4.func_175664_x(v1);
            /*SL:58*/if (v3) {
                a4.markAndNotifyBlock(v1, v4, a5, a6, 3);
            }
        }
        /*SL:61*/if (v2 != null && a5.func_177230_c().hasTileEntity(a5)) {
            /*SL:63*/a4.func_175713_t(v1);
            final BlockPos a7 = /*EL:64*/new BlockPos(v1.func_177958_n() & 0xF, v1.func_177956_o(), v1.func_177952_p() & 0xF);
            TileEntity a8 = /*EL:65*/v4.func_177424_a(a7, Chunk.EnumCreateEntityType.CHECK);
            /*SL:67*/a8 = a5.func_177230_c().createTileEntity(a4, a5);
            /*SL:68*/a8.func_145839_a(v2);
            /*SL:69*/a4.func_175690_a(v1, a8);
            /*SL:70*/a8.func_145836_u();
        }
    }
    
    public static void setTileEntity(final World v1, final IBlockState v2, final BlockPos v3, final NBTTagCompound v4) {
        /*SL:76*/if (v4 != null && v2.func_177230_c().hasTileEntity(v2)) {
            final Chunk a1 = /*EL:78*/v1.func_175726_f(v3);
            ExtendedBlockStorage a2 = /*EL:79*/a1.func_76587_i()[v3.func_177956_o() >> 4];
            /*SL:80*/if (a2 == null) {
                final ExtendedBlockStorage[] func_76587_i = a1.func_76587_i();
                final int n = v3.func_177956_o() >> 4;
                final ExtendedBlockStorage extendedBlockStorage = new ExtendedBlockStorage(v3.func_177956_o() >> 4 << 4, !v1.field_73011_w.func_177495_o());
                func_76587_i[n] = extendedBlockStorage;
                a2 = extendedBlockStorage;
            }
            /*SL:82*/v1.func_175713_t(v3);
            final BlockPos a3 = /*EL:83*/new BlockPos(v3.func_177958_n() & 0xF, v3.func_177956_o(), v3.func_177952_p() & 0xF);
            TileEntity a4 = /*EL:84*/a1.func_177424_a(a3, Chunk.EnumCreateEntityType.CHECK);
            /*SL:86*/a4 = v2.func_177230_c().createTileEntity(v1, v2);
            /*SL:87*/a4.func_145839_a(v4);
            /*SL:88*/v1.func_175690_a(v3, a4);
            /*SL:89*/a4.func_145836_u();
        }
    }
}
