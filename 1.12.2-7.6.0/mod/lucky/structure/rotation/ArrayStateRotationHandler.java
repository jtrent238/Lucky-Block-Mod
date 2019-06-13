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
        int v1 = /*EL:19*/this.rotationValues.indexOf(a1.func_177228_b().get(/*EL:20*/this.rotationProperty));
        /*SL:21*/if (v1 == -1) {
            return a1;
        }
        /*SL:23*/v1 += ((this.rotationValues.size() > 4) ? (a2 * (this.rotationValues.size() / 4)) : a2);
        /*SL:24*/v1 %= this.rotationValues.size();
        /*SL:25*/return a1.func_177226_a(this.rotationProperty, (Comparable)this.rotationValues.get(v1));
    }
}
