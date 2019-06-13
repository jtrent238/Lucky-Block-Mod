package mod.lucky.structure.rotation;

import net.minecraft.block.state.IBlockState;

public class MultipleStateRotationHandler extends StateRotationHandler
{
    private StateRotationHandler[] rotationHandlers;
    
    public MultipleStateRotationHandler(final StateRotationHandler... v2) {
        this.rotationHandlers = new StateRotationHandler[v2.length];
        for (int a1 = 0; a1 < v2.length; ++a1) {
            this.rotationHandlers[a1] = v2[a1];
        }
    }
    
    @Override
    public IBlockState rotate(final IBlockState v2, final int v3) {
        /*SL:19*/for (IBlockState a2 = (IBlockState)0; a2 < this.rotationHandlers.length; ++a2) {
            /*SL:21*/a2 = this.rotationHandlers[a2].rotate(v2, v3);
            /*SL:22*/if (a2 != v2) {
                return a2;
            }
        }
        /*SL:24*/return v2;
    }
}
