package mod.lucky.structure;

import mod.lucky.drop.func.DropFuncBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import java.util.ArrayList;
import net.minecraft.world.World;

public class BlockPlacer
{
    private World world;
    private ArrayList<BlockPos> updatePos;
    private ArrayList<IBlockState> updateState;
    
    public BlockPlacer(final World a1) {
        this.world = a1;
        this.updatePos = new ArrayList<BlockPos>();
        this.updateState = new ArrayList<IBlockState>();
    }
    
    public void add(final IBlockState a1, final BlockPos a2) {
        /*SL:25*/if (a2.func_177956_o() <= 0) {
            return;
        }
        /*SL:26*/DropFuncBlock.setBlock(this.world, a1, a2, false);
        /*SL:27*/this.updatePos.add(a2);
        /*SL:28*/this.updateState.add(a1);
    }
    
    public void update() {
        /*SL:33*/for (int v1 = 0; v1 < this.updatePos.size(); ++v1) {
            /*SL:35*/this.world.markAndNotifyBlock((BlockPos)this.updatePos.get(v1), this.world.func_175726_f((BlockPos)this.updatePos.get(v1)), this.world.func_180495_p((BlockPos)this.updatePos.get(v1)), (IBlockState)this.updateState.get(v1), 3);
        }
    }
}
