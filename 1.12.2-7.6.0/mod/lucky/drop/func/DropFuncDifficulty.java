package mod.lucky.drop.func;

import mod.lucky.drop.DropProperties;
import net.minecraft.world.EnumDifficulty;
import mod.lucky.drop.value.ValueParser;

public class DropFuncDifficulty extends DropFunction
{
    @Override
    public void process(final DropProcessData a1) {
        final DropProperties v1 = /*EL:10*/a1.getDropProperties();
        final String v2 = /*EL:11*/v1.getPropertyString("ID");
        final EnumDifficulty v3 = /*EL:12*/(!v2.equalsIgnoreCase("peaceful") && /*EL:13*/!v2.equalsIgnoreCase("p")) ? ((!v2.equalsIgnoreCase("easy") && /*EL:14*/!v2.equalsIgnoreCase("e")) ? ((!v2.equalsIgnoreCase("normal") && /*EL:15*/!v2.equalsIgnoreCase("n")) ? ((!v2.equalsIgnoreCase("hard") && /*EL:16*/!v2.equalsIgnoreCase("h")) ? /*EL:17*/EnumDifficulty.func_151523_a((int)ValueParser.getInteger(v2)) : EnumDifficulty.HARD) : EnumDifficulty.NORMAL) : EnumDifficulty.EASY) : EnumDifficulty.PEACEFUL;
        /*SL:22*/a1.getWorld().func_73046_m().func_147139_a(v3);
    }
    
    @Override
    public String getType() {
        /*SL:27*/return "difficulty";
    }
}
