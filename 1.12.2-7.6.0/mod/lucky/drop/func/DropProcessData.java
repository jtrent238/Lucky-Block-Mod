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
        /*SL:71*/return this.world;
    }
    
    public Entity getPlayer() {
        /*SL:75*/if (this.player == null) {
            try {
                /*SL:77*/this.player = this.world.func_73046_m().func_175576_a(this.playerUUID);
            }
            catch (Exception ex) {}
        }
        /*SL:82*/if (this.player == null) {
            try {
                final LuckyCommandLogic v1 = /*EL:84*/new LuckyCommandLogic();
                /*SL:85*/v1.setWorld(this.world);
                /*SL:86*/v1.setPosition(new BlockPos(this.harvestPos));
                /*SL:87*/this.player = /*EL:88*/(Entity)CommandBase.func_184888_a(this.world.func_73046_m(), (ICommandSender)v1, "@p");
            }
            catch (Exception ex2) {}
        }
        /*SL:93*/return this.player;
    }
    
    public Entity getHitEntity() {
        /*SL:97*/if (this.hitEntity == null) {
            /*SL:98*/this.hitEntity = this.world.func_73046_m().func_175576_a(this.hitEntityUUID);
        }
        /*SL:100*/return this.hitEntity;
    }
    
    public float getBowPower() {
        /*SL:104*/return this.bowPower;
    }
    
    public DropProcessData setHitEntity(final Entity a1) {
        /*SL:108*/this.hitEntity = a1;
        /*SL:109*/return this;
    }
    
    public DropProcessData setBowPower(final float a1) {
        /*SL:113*/this.bowPower = a1;
        /*SL:114*/return this;
    }
    
    public Vec3d getHarvestPos() {
        /*SL:118*/return this.harvestPos;
    }
    
    public BlockPos getHarvestBlockPos() {
        /*SL:122*/return new BlockPos(this.harvestPos.field_72450_a, this.harvestPos.field_72448_b, this.harvestPos.field_72449_c);
    }
    
    public DropProperties getDropProperties() {
        /*SL:126*/return this.dropProperties;
    }
    
    public EnumProcessType getProcessType() {
        /*SL:130*/return this.processType;
    }
    
    public void setProcessType(final EnumProcessType a1) {
        /*SL:134*/this.processType = a1;
    }
    
    public void setDropProperties(final DropProperties a1) {
        /*SL:138*/this.dropProperties = a1;
    }
    
    public void setPlayer(final Entity a1) {
        /*SL:142*/this.player = a1;
    }
    
    public void setHarvestPos(final Vec3d a1) {
        /*SL:146*/this.harvestPos = a1;
    }
    
    public void readFromNBT(final NBTTagCompound a1) {
        /*SL:150*/(this.dropProperties = new DropProperties()).readFromNBT(/*EL:151*/a1.func_74775_l("drop"));
        /*SL:156*/this.harvestPos = new Vec3d(a1.func_74769_h("harvestPosX"), a1.func_74769_h("harvestPosY"), a1.func_74769_h("harvestPosZ"));
        /*SL:157*/this.bowPower = a1.func_74760_g("bowPower");
        /*SL:158*/if (a1.func_74764_b("playerUUID")) {
            /*SL:159*/this.playerUUID = UUID.fromString(a1.func_74779_i("playerUUID"));
        }
        /*SL:160*/if (a1.func_74764_b("hitEntityUUID")) {
            /*SL:161*/this.hitEntityUUID = UUID.fromString(a1.func_74779_i("hitEntityUUID"));
        }
        /*SL:162*/this.world = (World)DimensionManager.getWorld(0);
    }
    
    public NBTTagCompound writeToNBT() {
        final NBTTagCompound v1 = /*EL:166*/new NBTTagCompound();
        /*SL:167*/v1.func_74782_a("drop", (NBTBase)this.dropProperties.writeToNBT());
        /*SL:168*/v1.func_74780_a("harvestPosX", this.harvestPos.field_72450_a);
        /*SL:169*/v1.func_74780_a("harvestPosY", this.harvestPos.field_72448_b);
        /*SL:170*/v1.func_74780_a("harvestPosZ", this.harvestPos.field_72449_c);
        /*SL:171*/v1.func_74776_a("bowPower", this.bowPower);
        /*SL:172*/if (this.player != null || this.playerUUID != null) {
            /*SL:173*/v1.func_74778_a("playerUUID", (this.player == null) ? this.playerUUID.toString() : /*EL:175*/this.player.func_110124_au().toString());
        }
        /*SL:176*/if (this.hitEntity != null || this.hitEntityUUID != null) {
            /*SL:177*/v1.func_74778_a("hitEntityUUID", (this.hitEntity == null) ? this.hitEntityUUID.toString() : /*EL:180*/this.hitEntity.func_110124_au().toString());
        }
        /*SL:182*/return v1;
    }
    
    public DropProcessData copy() {
        /*SL:186*/return new DropProcessData(this.world, this.player, this.harvestPos, this.dropProperties, this.processType, this.hitEntity).setBowPower(this.bowPower);
    }
    
    public enum EnumProcessType
    {
        NORMAL, 
        LUCKY_STRUCT;
    }
}
