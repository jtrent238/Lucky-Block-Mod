package mod.lucky.network;

import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class ParticlePacket implements IMessage
{
    private NBTTagCompound tag;
    
    public ParticlePacket() {
    }
    
    public ParticlePacket(final String a1, final double a2, final double a3, final double a4) {
        (this.tag = new NBTTagCompound()).func_74778_a("name", a1);
        this.tag.func_74780_a("x", a2);
        this.tag.func_74780_a("y", a3);
        this.tag.func_74780_a("z", a4);
    }
    
    public void fromBytes(final ByteBuf a1) {
        /*SL:31*/this.tag = ByteBufUtils.readTag(a1);
    }
    
    public void toBytes(final ByteBuf a1) {
        /*SL:37*/ByteBufUtils.writeTag(a1, this.tag);
    }
    
    public static class Handler implements IMessageHandler<ParticlePacket, IMessage>
    {
        public IMessage onMessage(final ParticlePacket a1, final MessageContext a2) {
            /*SL:45*/Minecraft.func_71410_x().field_71441_e.func_72869_a(a1.tag.func_74779_i("name"), a1.tag.func_74769_h("x"), a1.tag.func_74769_h("y"), a1.tag.func_74769_h("z"), 0.0, 0.0, 0.0);
            /*SL:46*/return null;
        }
    }
}
