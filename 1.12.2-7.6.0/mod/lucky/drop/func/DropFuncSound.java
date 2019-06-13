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
        final DropProperties v1 = /*EL:13*/a1.getDropProperties();
        /*SL:14*/if (a1.getPlayer() instanceof EntityPlayerMP) {
            /*SL:15*/((EntityPlayerMP)a1.getPlayer()).field_71135_a.func_147359_a((Packet)new SPacketSoundEffect((SoundEvent)SoundEvent.field_187505_a.func_82594_a(/*EL:18*/(Object)new ResourceLocation(v1.getPropertyString("ID"))), SoundCategory.BLOCKS, a1.getHarvestPos().field_72450_a, /*EL:20*/a1.getHarvestPos().field_72448_b, /*EL:21*/a1.getHarvestPos().field_72449_c, /*EL:22*/(float)v1.getPropertyFloat("volume"), /*EL:23*/(float)v1.getPropertyFloat("pitch")));
        }
    }
    
    @Override
    public void registerProperties() {
        /*SL:29*/DropProperties.setDefaultProperty(this.getType(), "volume", Float.class, 1.0f);
        /*SL:30*/DropProperties.setDefaultProperty(this.getType(), "pitch", Float.class, 1.0f);
    }
    
    @Override
    public String getType() {
        /*SL:35*/return "sound";
    }
}
