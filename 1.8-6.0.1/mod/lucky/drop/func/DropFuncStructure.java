package mod.lucky.drop.func;

import mod.lucky.structure.Structure;
import mod.lucky.drop.DropProperties;

public class DropFuncStructure extends DropFunction
{
    @Override
    public void process(final DropProcessData a1) {
        final DropProperties v1 = /*EL:11*/a1.getDropProperties();
        final Structure v2 = /*EL:12*/a1.getBlock().getDropProcessor().getStructure(v1.getPropertyString("ID"));
        /*SL:13*/if (v2 != null) {
            v2.process(a1);
        }
        else {
            System.err.println(/*EL:14*/"Lucky Block: Structure with ID '" + v1.getPropertyString("ID") + "' does not exist");
        }
    }
    
    @Override
    public void registerProperties() {
        /*SL:20*/DropProperties.setDefaultProperty(this.getType(), "rotation", Integer.class, 0);
        /*SL:21*/DropProperties.setDefaultProperty(this.getType(), "blockUpdate", Boolean.class, true);
    }
    
    @Override
    public String getType() {
        /*SL:27*/return "structure";
    }
}
