package mod.lucky.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import mod.lucky.block.BlockLuckyBlock;
import mod.lucky.Lucky;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.network.Packet;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTBase;
import mod.lucky.util.LuckyFunction;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityLuckyBlock extends TileEntity
{
    private String[] drops;
    private int luck;
    private int ticksBeforeUpdate;
    
    public TileEntityLuckyBlock() {
        this.drops = new String[0];
        this.luck = 0;
        this.ticksBeforeUpdate = 1;
    }
    
    public void func_145841_b(final NBTTagCompound a1) {
        /*SL:22*/super.func_145841_b(a1);
        /*SL:23*/a1.func_74782_a("Drops", (NBTBase)LuckyFunction.getNBTTagListFromArray(this.drops));
        /*SL:24*/a1.func_74768_a("Luck", this.luck);
    }
    
    public void func_145839_a(final NBTTagCompound a1) {
        /*SL:30*/super.func_145839_a(a1);
        /*SL:31*/this.drops = LuckyFunction.getArrayFromNBTTagList((NBTTagList)a1.func_74781_a("Drops"));
        /*SL:32*/this.luck = a1.func_74762_e("Luck");
    }
    
    public Packet func_145844_m() {
        final NBTTagCompound v1 = /*EL:38*/new NBTTagCompound();
        /*SL:39*/this.func_145841_b(v1);
        /*SL:40*/return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, v1);
    }
    
    public void func_145845_h() {
        /*SL:46*/if (this.ticksBeforeUpdate == 0) {
            final Block v1 = /*EL:48*/this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            /*SL:49*/if (v1 == Lucky.lucky_block && /*EL:51*/this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e)) {
                /*SL:53*/((BlockLuckyBlock)v1).removeLuckyBlock(this.field_145850_b, null, this.field_145851_c, this.field_145848_d, this.field_145849_e, true);
            }
        }
        /*SL:58*/if (!this.field_145850_b.field_72995_K) {
            /*SL:60*/--this.ticksBeforeUpdate;
            /*SL:61*/if (this.ticksBeforeUpdate < 0) {
                this.ticksBeforeUpdate = 1;
            }
        }
    }
    
    public void setLuck(final int a1) {
        /*SL:67*/this.luck = a1;
    }
    
    public int getLuck() {
        /*SL:72*/return this.luck;
    }
    
    public void setDrops(final String[] a1) {
        /*SL:77*/this.drops = a1;
    }
    
    public String[] getDrops() {
        /*SL:82*/return this.drops;
    }
}
