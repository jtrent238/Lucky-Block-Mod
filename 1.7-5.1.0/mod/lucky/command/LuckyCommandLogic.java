package mod.lucky.command;

import io.netty.buffer.ByteBuf;
import java.util.Date;
import net.minecraft.util.ChatComponentText;
import net.minecraft.command.ICommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import java.text.SimpleDateFormat;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.server.CommandBlockLogic;

public class LuckyCommandLogic extends CommandBlockLogic implements ICommandSender
{
    private static final SimpleDateFormat dateFormat;
    private int successCount;
    private IChatComponent outputMessage;
    private boolean doOutput;
    private String command;
    private String commandSender;
    public ChunkCoordinates commandCoordinates;
    public World world;
    
    public LuckyCommandLogic() {
        this.outputMessage = null;
        this.doOutput = false;
        this.command = "";
        this.commandSender = "@";
        this.commandCoordinates = new ChunkCoordinates();
    }
    
    public int getSuccessCount() {
        /*SL:30*/return this.successCount;
    }
    
    public IChatComponent getOutputMessage() {
        /*SL:35*/return this.outputMessage;
    }
    
    public boolean func_70003_b(final int a1, final String a2) {
        /*SL:44*/return a1 <= 2;
    }
    
    public void setCommand(final String a1) {
        /*SL:49*/this.command = a1;
    }
    
    public String getCommand() {
        /*SL:54*/return this.command;
    }
    
    public void setDoOutput(final boolean a1) {
        /*SL:59*/this.doOutput = a1;
    }
    
    public boolean getDoOutput() {
        /*SL:64*/return this.doOutput;
    }
    
    public World getWorld() {
        /*SL:69*/return this.world;
    }
    
    public void setWorld(final World a1) {
        /*SL:74*/this.world = a1;
    }
    
    public void executeCommand() {
        /*SL:79*/if (this.world.field_72995_K) {
            /*SL:81*/this.successCount = 0;
        }
        final MinecraftServer v0 = /*EL:84*/MinecraftServer.func_71276_C();
        /*SL:86*/if (v0 != null) {
            final boolean v = /*EL:88*/v0.field_71305_c[0].func_82736_K().func_82766_b("commandBlockOutput");
            /*SL:89*/if (!this.doOutput) {
                v0.field_71305_c[0].func_82736_K().func_82764_b("commandBlockOutput", "false");
            }
            final ICommandManager v2 = /*EL:91*/v0.func_71187_D();
            /*SL:92*/this.successCount = v2.func_71556_a((ICommandSender)this, this.command);
            /*SL:94*/if (!this.doOutput) {
                v0.field_71305_c[0].func_82736_K().func_82764_b("commandBlockOutput", String.valueOf(v));
            }
        }
        else {
            /*SL:98*/this.successCount = 0;
        }
    }
    
    public String func_70005_c_() {
        /*SL:109*/return this.commandSender;
    }
    
    public IChatComponent func_145748_c_() {
        /*SL:115*/return (IChatComponent)new ChatComponentText(this.func_70005_c_());
    }
    
    public void setCommandSender(final String a1) {
        /*SL:120*/this.commandSender = a1;
    }
    
    public void func_145747_a(final IChatComponent a1) {
        /*SL:126*/if (this.doOutput && this.func_130014_f_() != null && !this.func_130014_f_().field_72995_K) {
            /*SL:128*/this.outputMessage = new ChatComponentText("[" + LuckyCommandLogic.dateFormat.format(new Date()) + "] ").func_150257_a(a1);
        }
    }
    
    public void setOutputMessage(final IChatComponent a1) {
        /*SL:134*/this.outputMessage = a1;
    }
    
    public void setCommandCoordinates(final ChunkCoordinates a1) {
        /*SL:139*/this.commandCoordinates = a1;
    }
    
    public ChunkCoordinates getCommandCoordinates() {
        /*SL:144*/return this.commandCoordinates;
    }
    
    public ChunkCoordinates func_82114_b() {
        /*SL:150*/return this.commandCoordinates;
    }
    
    public World func_130014_f_() {
        /*SL:156*/return this.world;
    }
    
    public void func_145756_e() {
    }
    
    public int func_145751_f() {
        /*SL:167*/return 0;
    }
    
    public void func_145757_a(final ByteBuf a1) {
        /*SL:173*/a1.writeInt(this.commandCoordinates.field_71574_a);
        /*SL:174*/a1.writeInt(this.commandCoordinates.field_71572_b);
        /*SL:175*/a1.writeInt(this.commandCoordinates.field_71573_c);
    }
    
    static {
        dateFormat = new SimpleDateFormat("HH:mm:ss");
    }
}
