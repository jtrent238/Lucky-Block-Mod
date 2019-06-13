package mod.lucky.drop.func;

import net.minecraft.nbt.NBTBase;
import net.minecraftforge.common.DimensionManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.CommandBase;
import mod.lucky.command.LuckyCommandLogic;
import net.minecraft.util.math.BlockPos;
import mod.lucky.drop.DropProperties;
import net.minecraft.util.math.Vec3d;
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
    private Vec3d harvestPos;
    private DropProperties dropProperties;
    private EnumProcessType processType;
    
    public DropProcessData(final World a1) {
        this.bowPower = 1.0f;
        this.world = a1;
    }
    
    public DropProcessData(final World a1, final Entity a2, final Vec3d a3) {
        this(a1, a2, a3, null);
    }
    
    public DropProcessData(final World a1, final Entity a2, final BlockPos a3) {
        this(a1, a2, new Vec3d(a3.func_177958_n() + 0.5, (double)a3.func_177956_o(), a3.func_177952_p() + 0.5), null);
    }
    
    public DropProcessData(final World a1, final Entity a2, final Vec3d a3, final DropProperties a4) {
        this(a1, a2, a3, a4, EnumProcessType.NORMAL);
    }
    
    public DropProcessData(final World a1, final Entity a2, final Vec3d a3, final DropProperties a4, final EnumProcessType a5) {
        this(a1, a2, a3, a4, a5, null);
    }
    
    public DropProcessData(final World a1, final Entity a2, final Vec3d a3, final DropProperties a4, final EnumProcessType a5, final Entity a6) {
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
                /*SL:73*/this.player = this.world.func_73046_m().func_175576_a(this.playerUUID);
            }
            catch (Exception ex) {}
        }
        /*SL:80*/if (this.player == null) {
            try {
                final LuckyCommandLogic v1 = /*EL:84*/new LuckyCommandLogic();
                /*SL:85*/v1.setWorld(this.world);
                /*SL:86*/v1.setPosition(new BlockPos(this.harvestPos));
                /*SL:87*/this.player = (Entity)CommandBase.func_184888_a(this.world.func_73046_m(), (ICommandSender)v1, "@p");
            }
            catch (Exception ex2) {}
        }
        /*SL:94*/return this.player;
    }
    
    public Entity getHitEntity() {
        /*SL:99*/if (this.hitEntity == null) {
            /*SL:101*/this.hitEntity = this.world.func_73046_m().func_175576_a(this.hitEntityUUID);
        }
        /*SL:103*/return this.hitEntity;
    }
    
    public float getBowPower() {
        /*SL:108*/return this.bowPower;
    }
    
    public DropProcessData setHitEntity(final Entity a1) {
        /*SL:113*/this.hitEntity = a1;
        /*SL:114*/return this;
    }
    
    public DropProcessData setBowPower(final float a1) {
        /*SL:119*/this.bowPower = a1;
        /*SL:120*/return this;
    }
    
    public Vec3d getHarvestPos() {
        /*SL:125*/return this.harvestPos;
    }
    
    public BlockPos getHarvestBlockPos() {
        /*SL:130*/return new BlockPos(this.harvestPos.field_72450_a, this.harvestPos.field_72448_b, this.harvestPos.field_72449_c);
    }
    
    public DropProperties getDropProperties() {
        /*SL:135*/return this.dropProperties;
    }
    
    public EnumProcessType getProcessType() {
        /*SL:140*/return this.processType;
    }
    
    public void setProcessType(final EnumProcessType a1) {
        /*SL:145*/this.processType = a1;
    }
    
    public void setDropProperties(final DropProperties a1) {
        /*SL:150*/this.dropProperties = a1;
    }
    
    public void setPlayer(final Entity a1) {
        /*SL:155*/this.player = a1;
    }
    
    public void setHarvestPos(final Vec3d a1) {
        /*SL:160*/this.harvestPos = a1;
    }
    
    public void readFromNBT(final NBTTagCompound a1) {
        /*SL:165*/(this.dropProperties = new DropProperties()).readFromNBT(/*EL:166*/a1.func_74775_l("drop"));
        /*SL:167*/this.harvestPos = new Vec3d(a1.func_74769_h("harvestPosX"), a1.func_74769_h("harvestPosY"), a1.func_74769_h("harvestPosZ"));
        /*SL:168*/this.bowPower = a1.func_74760_g("bowPower");
        /*SL:169*/if (a1.func_74764_b("playerUUID")) {
            this.playerUUID = UUID.fromString(a1.func_74779_i("playerUUID"));
        }
        /*SL:170*/if (a1.func_74764_b("hitEntityUUID")) {
            this.hitEntityUUID = UUID.fromString(a1.func_74779_i("hitEntityUUID"));
        }
        /*SL:171*/this.world = (World)DimensionManager.getWorld(0);
    }
    
    public NBTTagCompound writeToNBT() {
        final NBTTagCompound v1 = /*EL:176*/new NBTTagCompound();
        /*SL:177*/v1.func_74782_a("drop", (NBTBase)this.dropProperties.writeToNBT());
        /*SL:178*/v1.func_74780_a("harvestPosX", this.harvestPos.field_72450_a);
        /*SL:179*/v1.func_74780_a("harvestPosY", this.harvestPos.field_72448_b);
        /*SL:180*/v1.func_74780_a("harvestPosZ", this.harvestPos.field_72449_c);
        /*SL:181*/v1.func_74776_a("bowPower", this.bowPower);
        /*SL:182*/if (this.player != null || this.playerUUID != null) {
            v1.func_74778_a("playerUUID", (this.player == null) ? this.playerUUID.toString() : this.player.func_110124_au().toString());
        }
        /*SL:183*/if (this.hitEntity != null || this.hitEntityUUID != null) {
            v1.func_74778_a("hitEntityUUID", (this.hitEntity == null) ? this.hitEntityUUID.toString() : this.hitEntity.func_110124_au().toString());
        }
        /*SL:184*/return v1;
    }
    
    public DropProcessData copy() {
        /*SL:189*/return new DropProcessData(this.world, this.player, this.harvestPos, this.dropProperties, this.processType, this.hitEntity).setBowPower(this.bowPower);
    }
    
    public enum EnumProcessType
    {
        NORMAL, 
        LUCKY_STRUCT;
    }
}
