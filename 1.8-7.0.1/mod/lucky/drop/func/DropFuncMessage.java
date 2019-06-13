package mod.lucky.drop.func;

import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.server.MinecraftServer;

public class DropFuncMessage extends DropFunction
{
    @Override
    public void process(final DropProcessData a1) {
        /*SL:11*/MinecraftServer.func_71276_C().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText(a1.getDropProperties().getPropertyString("ID")));
    }
    
    @Override
    public String getType() {
        /*SL:17*/return "message";
    }
}
