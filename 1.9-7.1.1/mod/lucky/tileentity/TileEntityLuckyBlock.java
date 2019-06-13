package mod.lucky.tileentity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import mod.lucky.block.BlockLuckyBlock;
import mod.lucky.Lucky;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.network.Packet;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTBase;
import mod.lucky.util.LuckyFunction;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.tileentity.TileEntity;

public class TileEntityLuckyBlock extends TileEntity implements ITickable
{
    private String[] drops;
    private int luck;
    
    public TileEntityLuckyBlock() {
        this.drops = new String[0];
        this.luck = 0;
    }
    
    public void func_145841_b(final NBTTagCompound a1) {
        /*SL:23*/super.func_145841_b(a1);
        /*SL:24*/a1.func_74782_a("Drops", (NBTBase)LuckyFunction.getNBTTagListFromStringArray(this.drops));
        /*SL:25*/a1.func_74768_a("Luck", this.luck);
    }
    
    public void func_145839_a(final NBTTagCompound a1) {
        /*SL:31*/super.func_145839_a(a1);
        /*SL:32*/this.drops = LuckyFunction.getStringArrayFromNBTTagList((NBTTagList)a1.func_74781_a("Drops"));
        /*SL:33*/this.luck = a1.func_74762_e("Luck");
    }
    
    public Packet func_145844_m() {
        final NBTTagCompound v1 = /*EL:39*/new NBTTagCompound();
        /*SL:40*/this.func_145841_b(v1);
        /*SL:41*/return (Packet)new SPacketUpdateTileEntity(new BlockPos(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p()), 1, v1);
    }
    
    public void func_73660_a() {
        /*SL:47*/if (this.field_145850_b != null && !this.field_145850_b.field_72995_K && this.field_145850_b.func_82737_E() % 20L == 0L) {
            final Block v1 = /*EL:49*/this.field_145850_b.func_180495_p(new BlockPos(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p())).func_177230_c();
            /*SL:50*/if (v1 == Lucky.lucky_block && /*EL:52*/this.field_145850_b.func_175640_z(new BlockPos(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p()))) {
                /*SL:54*/((BlockLuckyBlock)v1).removeLuckyBlock(this.field_145850_b, null, new BlockPos(this.field_174879_c.func_177958_n(), this.field_174879_c.func_177956_o(), this.field_174879_c.func_177952_p()), true);
            }
        }
    }
    
    public void setLuck(final int a1) {
        /*SL:62*/this.luck = a1;
    }
    
    public int getLuck() {
        /*SL:67*/return this.luck;
    }
    
    public void setDrops(final String[] a1) {
        /*SL:72*/this.drops = a1;
    }
    
    public String[] getDrops() {
        /*SL:77*/return this.drops;
    }
}
