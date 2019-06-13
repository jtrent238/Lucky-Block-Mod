package mod.lucky.drop.func;

import net.minecraft.nbt.NBTBase;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerSelector;
import mod.lucky.command.LuckyCommandLogic;
import net.minecraft.server.MinecraftServer;
import mod.lucky.drop.DropProperties;
import net.minecraft.util.BlockPos;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import mod.lucky.block.BlockLuckyBlock;

public class DropProcessData
{
    private BlockLuckyBlock block;
    private World world;
    private EntityPlayer player;
    private UUID playerUUID;
    private BlockPos harvestPos;
    private DropProperties dropProperties;
    EnumProcessType processType;
    
    public DropProcessData(final World a1) {
        this.world = a1;
    }
    
    public DropProcessData(final BlockLuckyBlock a1, final World a2, final EntityPlayer a3, final BlockPos a4) {
        this(a1, a2, a3, a4, null);
    }
    
    public DropProcessData(final BlockLuckyBlock a1, final World a2, final EntityPlayer a3, final BlockPos a4, final DropProperties a5) {
        this(a1, a2, a3, a4, a5, EnumProcessType.NORMAL);
    }
    
    public DropProcessData(final BlockLuckyBlock a1, final World a2, final EntityPlayer a3, final BlockPos a4, final DropProperties a5, final EnumProcessType a6) {
        this.world = a2;
        this.player = a3;
        this.harvestPos = a4;
        this.dropProperties = a5;
        this.processType = a6;
        this.block = a1;
    }
    
    public World getWorld() {
        /*SL:53*/return this.world;
    }
    
    public EntityPlayer getPlayer() {
        /*SL:58*/if (this.player == null) {
            this.player = (EntityPlayer)MinecraftServer.func_71276_C().func_71203_ab().func_177451_a(this.playerUUID);
        }
        /*SL:59*/if (this.player == null) {
            final LuckyCommandLogic v1 = /*EL:61*/new LuckyCommandLogic();
            /*SL:62*/v1.setWorld(this.world);
            /*SL:63*/v1.setPosition(this.harvestPos);
            /*SL:64*/this.player = (EntityPlayer)PlayerSelector.func_82386_a((ICommandSender)v1, "@p");
        }
        /*SL:66*/return this.player;
    }
    
    public BlockPos getHarvestPos() {
        /*SL:71*/return this.harvestPos;
    }
    
    public DropProperties getDropProperties() {
        /*SL:76*/return this.dropProperties;
    }
    
    public EnumProcessType getProcessType() {
        /*SL:81*/return this.processType;
    }
    
    public void setProcessType(final EnumProcessType a1) {
        /*SL:86*/this.processType = a1;
    }
    
    public void setDropProperties(final DropProperties a1) {
        /*SL:91*/this.dropProperties = a1;
    }
    
    public BlockLuckyBlock getBlock() {
        /*SL:96*/return this.block;
    }
    
    public void readFromNBT(final NBTTagCompound a1) {
        /*SL:101*/(this.dropProperties = new DropProperties()).readFromNBT(/*EL:102*/a1.func_74775_l("drop"));
        final Block v1 = /*EL:104*/Block.func_149684_b(a1.func_74779_i("block"));
        /*SL:105*/if (v1 != null && v1 instanceof BlockLuckyBlock) {
            this.block = (BlockLuckyBlock)v1;
        }
        /*SL:107*/this.harvestPos = BlockPos.func_177969_a(a1.func_74763_f("harvestPos"));
        /*SL:108*/this.playerUUID = UUID.fromString(a1.func_74779_i("playerUUID"));
    }
    
    public NBTTagCompound writeToNBT() {
        final NBTTagCompound v1 = /*EL:113*/new NBTTagCompound();
        /*SL:114*/v1.func_74782_a("drop", (NBTBase)this.dropProperties.writeToNBT());
        /*SL:115*/if (this.block != null) {
            v1.func_74778_a("block", Block.field_149771_c.func_177774_c((Object)this.block).toString());
        }
        /*SL:116*/v1.func_74772_a("harvestPos", this.harvestPos.func_177986_g());
        /*SL:117*/v1.func_74778_a("playerUUID", (this.player == null) ? this.playerUUID.toString() : this.player.func_110124_au().toString());
        /*SL:118*/return v1;
    }
    
    public DropProcessData copy() {
        /*SL:123*/return new DropProcessData(this.block, this.world, this.player, this.harvestPos, this.dropProperties, this.processType);
    }
    
    public enum EnumProcessType
    {
        NORMAL, 
        LUCKY_STRUCT;
    }
}
