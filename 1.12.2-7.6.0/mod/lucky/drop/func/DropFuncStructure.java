package mod.lucky.drop.func;

import mod.lucky.structure.Structure;
import mod.lucky.drop.DropProperties;
import mod.lucky.Lucky;

public class DropFuncStructure extends DropFunction
{
    @Override
    public void process(final DropProcessData a1) {
        final DropProperties v1 = /*EL:10*/a1.getDropProperties();
        final Structure v2 = /*EL:11*/Lucky.getStructure(v1.getPropertyString("ID"));
        /*SL:12*/if (v2 != null) {
            v2.process(a1);
        }
        else {
            System.err.println(/*EL:14*/"Lucky Block: Structure with ID '" + v1.getPropertyString("ID") + /*EL:15*/"' does not exist");
        }
    }
    
    @Override
    public void registerProperties() {
        /*SL:20*/DropProperties.setDefaultProperty(this.getType(), "rotation", Integer.class, 0);
        /*SL:21*/DropProperties.setDefaultProperty(this.getType(), "blockUpdate", Boolean.class, true);
    }
    
    @Override
    public String getType() {
        /*SL:26*/return "structure";
    }
}
