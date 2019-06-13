package mod.lucky.drop.func;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerSelector;
import mod.lucky.command.LuckyCommandLogic;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import mod.lucky.drop.DropProperties;
import net.minecraft.util.Vec3;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class DropProcessData
{
    private World world;
    private Entity player;
    private Entity hitEntity;
    private float bowPower;
    private UUID playerUUID;
    private UUID hitEntityUUID;
    private Vec3 harvestPos;
    private DropProperties dropProperties;
    private EnumProcessType processType;
    
    public DropProcessData(final World a1) {
        this.bowPower = 1.0f;
        this.world = a1;
    }
    
    public DropProcessData(final World a1, final Entity a2, final Vec3 a3) {
        this(a1, a2, a3, null);
    }
    
    public DropProcessData(final World a1, final Entity a2, final BlockPos a3) {
        this(a1, a2, new Vec3(a3.func_177958_n() + 0.5, (double)a3.func_177956_o(), a3.func_177952_p() + 0.5), null);
    }
    
    public DropProcessData(final World a1, final Entity a2, final Vec3 a3, final DropProperties a4) {
        this(a1, a2, a3, a4, EnumProcessType.NORMAL);
    }
    
    public DropProcessData(final World a1, final Entity a2, final Vec3 a3, final DropProperties a4, final EnumProcessType a5) {
        this(a1, a2, a3, a4, a5, null);
    }
    
    public DropProcessData(final World a1, final Entity a2, final Vec3 a3, final DropProperties a4, final EnumProcessType a5, final Entity a6) {
        this.bowPower = 1.0f;
        this.world = a1;
        this.player = a2;
        this.harvestPos = a3;
        this.dropProperties = a4;
        this.processType = a5;
        this.hitEntity = a6;
    }
    
    public World getWorld() {
        /*SL:64*/return this.world;
    }
    
    public Entity getPlayer() {
        /*SL:69*/if (this.player == null) {
            try {
                /*SL:73*/this.player = (Entity)MinecraftServer.func_71276_C().func_71203_ab().func_177451_a(this.playerUUID);
                /*SL:74*/if (this.player == null) {
                    this.player = MinecraftServer.func_71276_C().func_175576_a(this.playerUUID);
                }
            }
            catch (Exception ex) {}
        }
        /*SL:81*/if (this.player == null) {
            try {
                final LuckyCommandLogic v1 = /*EL:85*/new LuckyCommandLogic();
                /*SL:86*/v1.setWorld(this.world);
                /*SL:87*/v1.setPosition(new BlockPos(this.harvestPos));
                /*SL:88*/this.player = (Entity)PlayerSelector.func_82386_a((ICommandSender)v1, "@p");
            }
            catch (Exception ex2) {}
        }
        /*SL:95*/return this.player;
    }
    
    public Entity getHitEntity() {
        /*SL:100*/if (this.hitEntity == null) {
            /*SL:102*/this.hitEntity = (Entity)MinecraftServer.func_71276_C().func_71203_ab().func_177451_a(this.hitEntityUUID);
            /*SL:103*/if (this.hitEntity == null) {
                this.hitEntity = MinecraftServer.func_71276_C().func_175576_a(this.hitEntityUUID);
            }
        }
        /*SL:105*/return this.hitEntity;
    }
    
    public float getBowPower() {
        /*SL:110*/return this.bowPower;
    }
    
    public DropProcessData setHitEntity(final Entity a1) {
        /*SL:115*/this.hitEntity = a1;
        /*SL:116*/return this;
    }
    
    public DropProcessData setBowPower(final float a1) {
        /*SL:121*/this.bowPower = a1;
        /*SL:122*/return this;
    }
    
    public Vec3 getHarvestPos() {
        /*SL:127*/return this.harvestPos;
    }
    
    public BlockPos getHarvestBlockPos() {
        /*SL:132*/return new BlockPos(this.harvestPos.field_72450_a, this.harvestPos.field_72448_b, this.harvestPos.field_72449_c);
    }
    
    public DropProperties getDropProperties() {
        /*SL:137*/return this.dropProperties;
    }
    
    public EnumProcessType getProcessType() {
        /*SL:142*/return this.processType;
    }
    
    public void setProcessType(final EnumProcessType a1) {
        /*SL:147*/this.processType = a1;
    }
    
    public void setDropProperties(final DropProperties a1) {
        /*SL:152*/this.dropProperties = a1;
    }
    
    public void setPlayer(final Entity a1) {
        /*SL:157*/this.player = a1;
    }
    
    public void setHarvestPos(final Vec3 a1) {
        /*SL:162*/this.harvestPos = a1;
    }
    
    public void readFromNBT(final NBTTagCompound a1) {
        /*SL:167*/(this.dropProperties = new DropProperties()).readFromNBT(/*EL:168*/a1.func_74775_l("drop"));
        /*SL:169*/this.harvestPos = new Vec3(a1.func_74769_h("harvestPosX"), a1.func_74769_h("harvestPosY"), a1.func_74769_h("harvestPosZ"));
        /*SL:170*/this.bowPower = a1.func_74760_g("bowPower");
        /*SL:171*/if (a1.func_74764_b("playerUUID")) {
            this.playerUUID = UUID.fromString(a1.func_74779_i("playerUUID"));
        }
        /*SL:172*/if (a1.func_74764_b("hitEntityUUID")) {
            this.hitEntityUUID = UUID.fromString(a1.func_74779_i("hitEntityUUID"));
        }
        /*SL:173*/this.world = MinecraftServer.func_71276_C().func_130014_f_();
    }
    
    public NBTTagCompound writeToNBT() {
        final NBTTagCompound v1 = /*EL:178*/new NBTTagCompound();
        /*SL:179*/v1.func_74782_a("drop", (NBTBase)this.dropProperties.writeToNBT());
        /*SL:180*/v1.func_74780_a("harvestPosX", this.harvestPos.field_72450_a);
        /*SL:181*/v1.func_74780_a("harvestPosY", this.harvestPos.field_72448_b);
        /*SL:182*/v1.func_74780_a("harvestPosZ", this.harvestPos.field_72449_c);
        /*SL:183*/v1.func_74776_a("bowPower", this.bowPower);
        /*SL:184*/if (this.player != null || this.playerUUID != null) {
            v1.func_74778_a("playerUUID", (this.player == null) ? this.playerUUID.toString() : this.player.func_110124_au().toString());
        }
        /*SL:185*/if (this.hitEntity != null || this.hitEntityUUID != null) {
            v1.func_74778_a("hitEntityUUID", (this.hitEntity == null) ? this.hitEntityUUID.toString() : this.hitEntity.func_110124_au().toString());
        }
        /*SL:186*/return v1;
    }
    
    public DropProcessData copy() {
        /*SL:191*/return new DropProcessData(this.world, this.player, this.harvestPos, this.dropProperties, this.processType, this.hitEntity).setBowPower(this.bowPower);
    }
    
    public enum EnumProcessType
    {
        NORMAL, 
        LUCKY_STRUCT;
    }
}
