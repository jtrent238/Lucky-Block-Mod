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
        /*SL:21*/if (a2.func_177956_o() <= 0) {
            return;
        }
        /*SL:22*/DropFuncBlock.setBlock(this.world, a1, a2, false);
        /*SL:23*/this.updatePos.add(a2);
        /*SL:24*/this.updateState.add(a1);
    }
    
    public void update() {
        /*SL:28*/for (int v1 = 0; v1 < this.updatePos.size(); ++v1) {
            /*SL:29*/this.world.markAndNotifyBlock((BlockPos)this.updatePos.get(v1), /*EL:30*/this.world.func_175726_f((BlockPos)this.updatePos.get(v1)), /*EL:31*/this.world.func_180495_p((BlockPos)this.updatePos.get(v1)), /*EL:32*/(IBlockState)this.updateState.get(v1), /*EL:33*/3);
        }
    }
}
