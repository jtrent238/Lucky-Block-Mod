package mod.lucky.drop.func;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.block.state.IBlockState;
import mod.lucky.drop.DropProperties;
import net.minecraft.util.math.BlockPos;

public class DropFuncFill extends DropFunction
{
    @Override
    public void process(final DropProcessData v-7) {
        final DropProperties dropProperties = /*EL:13*/v-7.getDropProperties();
        final IBlockState blockState = /*EL:14*/dropProperties.getBlockState();
        BlockPos blockPos = /*EL:15*/dropProperties.getBlockPos();
        int intValue = /*EL:18*/dropProperties.getPropertyInt("length");
        int intValue2 = /*EL:19*/dropProperties.getPropertyInt("height");
        int intValue3 = /*EL:20*/dropProperties.getPropertyInt("width");
        /*SL:21*/if (dropProperties.hasProperty("pos2X")) {
            /*SL:23*/intValue = Math.abs(dropProperties.getPropertyInt("pos2X") - blockPos.func_177958_n()) + 1;
            /*SL:24*/if (blockPos.func_177958_n() > dropProperties.getPropertyInt("pos2X")) {
                blockPos = blockPos.func_177982_a(-intValue + 1, 0, 0);
            }
        }
        /*SL:26*/if (dropProperties.hasProperty("pos2Y")) {
            /*SL:28*/intValue2 = Math.abs(dropProperties.getPropertyInt("pos2Y") - blockPos.func_177956_o()) + 1;
            /*SL:29*/if (blockPos.func_177956_o() > dropProperties.getPropertyInt("pos2Y")) {
                blockPos = blockPos.func_177982_a(0, -intValue2 + 1, 0);
            }
        }
        /*SL:31*/if (dropProperties.hasProperty("pos2Z")) {
            /*SL:33*/intValue3 = Math.abs(dropProperties.getPropertyInt("pos2Z") - blockPos.func_177952_p()) + 1;
            /*SL:34*/if (blockPos.func_177952_p() > dropProperties.getPropertyInt("pos2Z")) {
                blockPos = blockPos.func_177982_a(0, 0, -intValue3 + 1);
            }
        }
        /*SL:37*/for (int v0 = blockPos.func_177958_n(); v0 < blockPos.func_177958_n() + intValue; ++v0) {
            /*SL:39*/for (int v = blockPos.func_177956_o(); v < blockPos.func_177956_o() + intValue2; ++v) {
                /*SL:41*/for (int a1 = blockPos.func_177952_p(); a1 < blockPos.func_177952_p() + intValue3; ++a1) {
                    /*SL:43*/DropFuncBlock.setBlock(v-7.getWorld(), blockState, new BlockPos(v0, v, a1), dropProperties.getPropertyNBT("NBTTag"), dropProperties.getPropertyBoolean("blockUpdate"));
                }
            }
        }
    }
    
    @Override
    public void registerProperties() {
        /*SL:52*/DropProperties.setDefaultProperty(this.getType(), "length", Integer.class, 1);
        /*SL:53*/DropProperties.setDefaultProperty(this.getType(), "height", Integer.class, 1);
        /*SL:54*/DropProperties.setDefaultProperty(this.getType(), "width", Integer.class, 1);
        /*SL:55*/DropProperties.setDefaultProperty(this.getType(), "size", String.class, "(1,1,1)");
        /*SL:56*/DropProperties.setDefaultProperty(this.getType(), "pos2X", Integer.class, 0);
        /*SL:57*/DropProperties.setDefaultProperty(this.getType(), "pos2Y", Integer.class, 0);
        /*SL:58*/DropProperties.setDefaultProperty(this.getType(), "pos2Z", Integer.class, 0);
        /*SL:59*/DropProperties.setDefaultProperty(this.getType(), "pos2", String.class, "(0,0,0)");
        /*SL:60*/DropProperties.setDefaultProperty(this.getType(), "tileEntity", NBTTagCompound.class, null);
        /*SL:61*/DropProperties.setDefaultProperty(this.getType(), "blockUpdate", Boolean.class, true);
        /*SL:62*/DropProperties.setDefaultProperty(this.getType(), "blockMode", String.class, "replace");
        /*SL:63*/DropProperties.setReplaceProperty("tileEntity", "NBTTag");
    }
    
    @Override
    public String getType() {
        /*SL:69*/return "fill";
    }
}
