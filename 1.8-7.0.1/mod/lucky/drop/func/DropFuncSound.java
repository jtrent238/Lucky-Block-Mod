package mod.lucky.drop.func;

import mod.lucky.drop.DropProperties;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.entity.player.EntityPlayerMP;

public class DropFuncSound extends DropFunction
{
    @Override
    public void process(final DropProcessData a1) {
        final DropProperties v1 = /*EL:12*/a1.getDropProperties();
        /*SL:13*/if (a1.getPlayer() instanceof EntityPlayerMP) {
            ((EntityPlayerMP)a1.getPlayer()).field_71135_a.func_147359_a((Packet)new S29PacketSoundEffect(v1.getPropertyString("ID"), a1.getHarvestPos().field_72450_a, a1.getHarvestPos().field_72448_b, a1.getHarvestPos().field_72449_c, (float)v1.getPropertyFloat("volume"), (float)v1.getPropertyFloat("pitch")));
        }
    }
    
    @Override
    public void registerProperties() {
        /*SL:20*/DropProperties.setDefaultProperty(this.getType(), "volume", Float.class, 1.0f);
        /*SL:21*/DropProperties.setDefaultProperty(this.getType(), "pitch", Float.class, 1.0f);
    }
    
    @Override
    public String getType() {
        /*SL:27*/return "sound";
    }
}
