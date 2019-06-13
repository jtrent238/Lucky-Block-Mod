package mod.lucky.drop.func;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class DropFuncMessage extends DropFunction
{
    @Override
    public void process(final DropProcessData a1) {
        /*SL:8*/a1.getPlayer().func_145747_a(/*EL:11*/(ITextComponent)new TextComponentString(a1.getDropProperties().getPropertyString("ID")));
    }
    
    @Override
    public String getType() {
        /*SL:16*/return "message";
    }
}
