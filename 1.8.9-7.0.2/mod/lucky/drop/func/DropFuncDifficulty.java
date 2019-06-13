package mod.lucky.drop.func;

import mod.lucky.drop.DropProperties;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.EnumDifficulty;
import mod.lucky.drop.value.ValueParser;

public class DropFuncDifficulty extends DropFunction
{
    @Override
    public void process(final DropProcessData a1) {
        final DropProperties v1 = /*EL:13*/a1.getDropProperties();
        final String v2 = /*EL:14*/v1.getPropertyString("ID");
        final EnumDifficulty v3 = /*EL:15*/(!v2.equalsIgnoreCase("peaceful") && !v2.equalsIgnoreCase("p")) ? ((!v2.equalsIgnoreCase("easy") && !v2.equalsIgnoreCase("e")) ? ((!v2.equalsIgnoreCase("normal") && !v2.equalsIgnoreCase("n")) ? ((!v2.equalsIgnoreCase("hard") && !v2.equalsIgnoreCase("h")) ? EnumDifficulty.func_151523_a((int)ValueParser.getInteger(v2)) : EnumDifficulty.HARD) : EnumDifficulty.NORMAL) : EnumDifficulty.EASY) : EnumDifficulty.PEACEFUL;
        /*SL:16*/MinecraftServer.func_71276_C().func_147139_a(v3);
    }
    
    @Override
    public String getType() {
        /*SL:22*/return "difficulty";
    }
}
