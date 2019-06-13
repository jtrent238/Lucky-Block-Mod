package mod.lucky.command;

import net.minecraft.command.CommandResultStats;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import java.util.Date;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.command.ICommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import java.text.SimpleDateFormat;
import net.minecraft.command.ICommandSender;

public class LuckyCommandLogic implements ICommandSender
{
    private static final SimpleDateFormat dateFormat;
    private int successCount;
    private ITextComponent outputMessage;
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
        /*SL:27*/return this.successCount;
    }
    
    public ITextComponent getOutputMessage() {
        /*SL:31*/return this.outputMessage;
    }
    
    public boolean func_70003_b(final int a1, final String a2) {
        /*SL:36*/return a1 <= 2;
    }
    
    public void setCommand(final String a1) {
        /*SL:40*/this.command = a1;
    }
    
    public String getCommand() {
        /*SL:44*/return this.command;
    }
    
    public void setDoOutput(final boolean a1) {
        /*SL:48*/this.doOutput = a1;
    }
    
    public boolean getDoOutput() {
        /*SL:52*/return this.doOutput;
    }
    
    public World getWorld() {
        /*SL:56*/return this.world;
    }
    
    public void setWorld(final World a1) {
        /*SL:60*/this.world = a1;
    }
    
    public void executeCommand() {
        /*SL:64*/if (this.world.field_72995_K) {
            /*SL:65*/this.successCount = 0;
        }
        final MinecraftServer v0 = /*EL:68*/this.func_184102_h();
        /*SL:69*/if (v0 != null) {
            final ICommandManager v = /*EL:70*/v0.func_71187_D();
            /*SL:71*/this.successCount = v.func_71556_a((ICommandSender)this, this.command);
        }
        else {
            /*SL:73*/this.successCount = 0;
        }
    }
    
    public String func_70005_c_() {
        /*SL:79*/return this.commandSender;
    }
    
    public ITextComponent func_145748_c_() {
        /*SL:84*/return (ITextComponent)new TextComponentString(this.func_70005_c_());
    }
    
    public void setName(final String a1) {
        /*SL:88*/this.commandSender = a1;
    }
    
    public void func_145747_a(final ITextComponent a1) {
        /*SL:93*/if (this.doOutput && this.func_130014_f_() != null && !this.func_130014_f_().field_72995_K) {
            /*SL:96*/this.outputMessage = new TextComponentString("[" + LuckyCommandLogic.dateFormat.format(new Date()) + "] ").func_150257_a(a1);
        }
    }
    
    public void setOutputMessage(final ITextComponent a1) {
        /*SL:101*/this.outputMessage = a1;
    }
    
    public World func_130014_f_() {
        /*SL:106*/return this.world;
    }
    
    public BlockPos func_180425_c() {
        /*SL:111*/return this.position;
    }
    
    public BlockPos setPosition(final BlockPos a1) {
        /*SL:115*/return this.position = a1;
    }
    
    public Vec3d func_174791_d() {
        /*SL:120*/return new Vec3d((double)this.position.func_177958_n(), (double)this.position.func_177956_o(), (double)this.position.func_177952_p());
    }
    
    public Entity func_174793_f() {
        /*SL:125*/return null;
    }
    
    public boolean func_174792_t_() {
        /*SL:130*/return this.doOutput;
    }
    
    public void func_174794_a(final CommandResultStats.Type a1, final int a2) {
    }
    
    public MinecraftServer func_184102_h() {
        /*SL:138*/return this.world.func_73046_m();
    }
    
    static {
        dateFormat = new SimpleDateFormat("HH:mm:ss");
    }
}
