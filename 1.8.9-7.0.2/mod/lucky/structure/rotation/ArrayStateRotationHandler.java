package mod.lucky.structure.rotation;

import net.minecraft.block.state.IBlockState;
import java.util.ArrayList;
import net.minecraft.block.properties.IProperty;

public class ArrayStateRotationHandler extends StateRotationHandler
{
    public IProperty rotationProperty;
    public ArrayList<Comparable> rotationValues;
    
    public ArrayStateRotationHandler(final IProperty v1, final Comparable... v2) {
        this.rotationProperty = v1;
        this.rotationValues = new ArrayList<Comparable>();
        for (final Comparable a1 : v2) {
            this.rotationValues.add(a1);
        }
    }
    
    @Override
    public IBlockState rotate(final IBlockState a1, final int a2) {
        int v1 = /*EL:24*/this.rotationValues.indexOf(a1.func_177228_b().get(this.rotationProperty));
        /*SL:25*/if (v1 == -1) {
            return a1;
        }
        /*SL:26*/v1 += ((this.rotationValues.size() > 4) ? (a2 * (this.rotationValues.size() / 4)) : a2);
        /*SL:27*/v1 %= this.rotationValues.size();
        /*SL:28*/return a1.func_177226_a(this.rotationProperty, (Comparable)this.rotationValues.get(v1));
    }
}
