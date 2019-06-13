package mod.lucky.drop.func;

import mod.lucky.drop.DropProperties;
import mod.lucky.command.LuckyCommandLogic;

public class DropFuncCommand extends DropFunction
{
    @Override
    public void process(final DropProcessData a1) {
        final DropProperties v1 = /*EL:9*/a1.getDropProperties();
        final LuckyCommandLogic v2 = /*EL:10*/new LuckyCommandLogic();
        /*SL:11*/v2.setWorld(a1.getWorld());
        /*SL:12*/v2.setPosition(v1.getBlockPos());
        /*SL:13*/v2.setCommand(v1.getPropertyString("ID"));
        /*SL:14*/v2.setName(v1.getPropertyString("commandSender"));
        /*SL:15*/v2.setDoOutput(v1.getPropertyBoolean("displayOutput"));
        /*SL:16*/v2.executeCommand();
    }
    
    @Override
    public void registerProperties() {
        /*SL:21*/DropProperties.setDefaultProperty(this.getType(), "commandSender", String.class, "@");
        /*SL:22*/DropProperties.setDefaultProperty(this.getType(), "displayOutput", Boolean.class, false);
    }
    
    @Override
    public String getType() {
        /*SL:27*/return "command";
    }
}
