package mod.lucky.network;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

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
        /*SL:32*/this.tag = ByteBufUtils.readTag(a1);
    }
    
    public void toBytes(final ByteBuf a1) {
        /*SL:38*/ByteBufUtils.writeTag(a1, this.tag);
    }
    
    public static class Handler implements IMessageHandler<ParticlePacket, IMessage>
    {
        public IMessage onMessage(final ParticlePacket v1, final MessageContext v2) {
            final String v3 = /*EL:46*/v1.tag.func_74779_i("name");
            EnumParticleTypes v4 = EnumParticleTypes.EXPLOSION_NORMAL;
            /*SL:48*/for (final EnumParticleTypes a1 : EnumParticleTypes.values()) {
                /*SL:50*/if (a1.func_179346_b().equals(v3)) {
                    /*SL:52*/v4 = a1;
                    /*SL:53*/break;
                }
            }
            /*SL:56*/Minecraft.func_71410_x().field_71441_e.func_175688_a(v4, v1.tag.func_74769_h("x"), v1.tag.func_74769_h("y"), v1.tag.func_74769_h("z"), 0.0, 0.0, 0.0, new int[0]);
            /*SL:57*/return null;
        }
    }
}
