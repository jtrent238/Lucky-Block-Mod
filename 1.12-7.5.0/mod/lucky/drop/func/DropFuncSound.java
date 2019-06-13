package mod.lucky.drop.func;

import mod.lucky.drop.DropProperties;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.entity.player.EntityPlayerMP;

public class DropFuncSound extends DropFunction
{
    @Override
    public void process(final DropProcessData a1) {
        final DropProperties v1 = /*EL:15*/a1.getDropProperties();
        /*SL:16*/if (a1.getPlayer() instanceof EntityPlayerMP) {
            ((EntityPlayerMP)a1.getPlayer()).field_71135_a.func_147359_a((Packet)new SPacketSoundEffect((SoundEvent)SoundEvent.field_187505_a.func_82594_a((Object)new ResourceLocation(v1.getPropertyString("ID"))), SoundCategory.BLOCKS, a1.getHarvestPos().field_72450_a, a1.getHarvestPos().field_72448_b, a1.getHarvestPos().field_72449_c, (float)v1.getPropertyFloat("volume"), (float)v1.getPropertyFloat("pitch")));
        }
    }
    
    @Override
    public void registerProperties() {
        /*SL:23*/DropProperties.setDefaultProperty(this.getType(), "volume", Float.class, 1.0f);
        /*SL:24*/DropProperties.setDefaultProperty(this.getType(), "pitch", Float.class, 1.0f);
    }
    
    @Override
    public String getType() {
        /*SL:30*/return "sound";
    }
}
