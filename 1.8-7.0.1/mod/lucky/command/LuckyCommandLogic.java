package mod.lucky.command;

import net.minecraft.command.CommandResultStats;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import java.util.Date;
import net.minecraft.util.ChatComponentText;
import net.minecraft.command.ICommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import java.text.SimpleDateFormat;
import net.minecraft.command.ICommandSender;

public class LuckyCommandLogic implements ICommandSender
{
    private static final SimpleDateFormat dateFormat;
    private int successCount;
    private IChatComponent outputMessage;
    private boolean doOutput;
    private String command;
    private String commandSender;
    public BlockPos position;
    public World world;
    
    public LuckyCommandLogic() {
        this.outputMessage = null;
        this.doOutput = false;
        this.command = "";
        this.commandSender = "@";
        this.position = new BlockPos(0, 0, 0);
    }
    
    public int getSuccessCount() {
        /*SL:30*/return this.successCount;
    }
    
    public IChatComponent getOutputMessage() {
        /*SL:35*/return this.outputMessage;
    }
    
    public boolean func_70003_b(final int a1, final String a2) {
        /*SL:41*/return a1 <= 2;
    }
    
    public void setCommand(final String a1) {
        /*SL:46*/this.command = a1;
    }
    
    public String getCommand() {
        /*SL:51*/return this.command;
    }
    
    public void setDoOutput(final boolean a1) {
        /*SL:56*/this.doOutput = a1;
    }
    
    public boolean getDoOutput() {
        /*SL:61*/return this.doOutput;
    }
    
    public World getWorld() {
        /*SL:66*/return this.world;
    }
    
    public void setWorld(final World a1) {
        /*SL:71*/this.world = a1;
    }
    
    public void executeCommand() {
        /*SL:76*/if (this.world.field_72995_K) {
            /*SL:78*/this.successCount = 0;
        }
        final MinecraftServer v0 = /*EL:81*/MinecraftServer.func_71276_C();
        /*SL:82*/if (v0 != null) {
            final ICommandManager v = /*EL:90*/v0.func_71187_D();
            /*SL:91*/this.successCount = v.func_71556_a((ICommandSender)this, this.command);
        }
        else {
            /*SL:99*/this.successCount = 0;
        }
    }
    
    public String func_70005_c_() {
        /*SL:106*/return this.commandSender;
    }
    
    public IChatComponent func_145748_c_() {
        /*SL:112*/return (IChatComponent)new ChatComponentText(this.func_70005_c_());
    }
    
    public void setName(final String a1) {
        /*SL:117*/this.commandSender = a1;
    }
    
    public void func_145747_a(final IChatComponent a1) {
        /*SL:123*/if (this.doOutput && this.func_130014_f_() != null && !this.func_130014_f_().field_72995_K) {
            /*SL:125*/this.outputMessage = new ChatComponentText("[" + LuckyCommandLogic.dateFormat.format(new Date()) + "] ").func_150257_a(a1);
        }
    }
    
    public void setOutputMessage(final IChatComponent a1) {
        /*SL:131*/this.outputMessage = a1;
    }
    
    public World func_130014_f_() {
        /*SL:137*/return this.world;
    }
    
    public BlockPos func_180425_c() {
        /*SL:143*/return this.position;
    }
    
    public BlockPos setPosition(final BlockPos a1) {
        /*SL:148*/return this.position = a1;
    }
    
    public Vec3 func_174791_d() {
        /*SL:154*/return new Vec3((double)this.position.func_177958_n(), (double)this.position.func_177956_o(), (double)this.position.func_177952_p());
    }
    
    public Entity func_174793_f() {
        /*SL:160*/return null;
    }
    
    public boolean func_174792_t_() {
        /*SL:166*/return this.doOutput;
    }
    
    public void func_174794_a(final CommandResultStats.Type a1, final int a2) {
    }
    
    static {
        dateFormat = new SimpleDateFormat("HH:mm:ss");
    }
}
