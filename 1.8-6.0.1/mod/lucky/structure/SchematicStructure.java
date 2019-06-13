package mod.lucky.structure;

import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.CompressedStreamTools;
import java.io.InputStream;
import java.io.DataInputStream;
import java.util.zip.GZIPInputStream;
import mod.lucky.drop.DropProperties;
import net.minecraft.entity.EntityList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.init.Blocks;
import net.minecraft.util.Vec3;
import mod.lucky.drop.func.DropProcessData;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.block.Block;

public class SchematicStructure extends Structure
{
    private Block[][][] blocks;
    private int[][][] blockData;
    private NBTTagCompound[] entities;
    private NBTTagCompound[] tileEntities;
    
    @Override
    public void process(final DropProcessData v-5) {
        final DropProperties dropProperties = /*EL:30*/v-5.getDropProperties();
        final Vec3 a2 = /*EL:31*/new Vec3(dropProperties.getPropertyInt("posX") + 0.5, (double)dropProperties.getPropertyInt("posY"), dropProperties.getPropertyInt("posZ") + 0.5);
        final int intValue = /*EL:32*/dropProperties.getPropertyInt("rotation");
        final BlockPlacer a3 = /*EL:33*/new BlockPlacer(v-5.getWorld());
        /*SL:35*/for (int v0 = 0; v0 < this.height; ++v0) {
            /*SL:37*/for (int v = 0; v < this.width; ++v) {
                /*SL:39*/for (int a1 = 0; a1 < this.length; ++a1) {
                    /*SL:41*/if (!this.blockMode.equals("overlay") || this.blocks[v0][v][a1] != Blocks.field_150350_a) {
                        /*SL:42*/StructureUtils.setBlock(a3, this.blocks[v0][v][a1].func_176203_a(this.blockData[v0][v][a1]), new BlockPos(a1, v0, v), this.getCenterPos(), a2, intValue);
                    }
                }
            }
        }
        /*SL:47*/for (final NBTTagCompound v2 : this.tileEntities) {
            /*SL:48*/StructureUtils.setTileEntity(v-5.getWorld(), TileEntity.func_145827_c(v2), this.getCenterPos(), a2, intValue);
        }
        /*SL:50*/for (final NBTTagCompound v2 : this.entities) {
            /*SL:51*/StructureUtils.setEntity(v-5.getWorld(), EntityList.func_75615_a(v2, v-5.getWorld()), this.getCenterPos(), a2, intValue);
        }
        /*SL:53*/if (this.blockUpdate) {
            a3.update();
        }
        /*SL:54*/this.processOverlay(v-5);
    }
    
    @Override
    public void readFromFile() {
        NBTTagCompound func_74794_a = /*EL:60*/null;
        try {
            final DataInputStream v0 = /*EL:64*/new DataInputStream(new GZIPInputStream(this.fileStream));
            /*SL:65*/func_74794_a = CompressedStreamTools.func_74794_a(v0);
            /*SL:66*/v0.close();
        }
        catch (Exception v) {
            System.err.println(/*EL:70*/"Lucky Block: Error loading structure '" + this.getId() + "'");
            /*SL:71*/v.printStackTrace();
        }
        /*SL:75*/this.length = func_74794_a.func_74765_d("Width");
        /*SL:76*/this.width = func_74794_a.func_74765_d("Length");
        /*SL:77*/this.height = func_74794_a.func_74765_d("Height");
        final int v2 = /*EL:79*/this.length * this.width * this.height;
        /*SL:80*/if (v2 > 100000) {
            System.err.println(/*EL:82*/"Lucky Block: Error loading structure. The structure '" + this.getId() + "' (" + v2 + " blocks) exceeds the " + 100000 + " block limit");
            /*SL:83*/return;
        }
        /*SL:86*/this.blocks = new Block[this.height][this.width][this.length];
        /*SL:87*/this.blockData = new int[this.height][this.width][this.length];
        final byte[] v3 = /*EL:89*/func_74794_a.func_74770_j("Blocks");
        final byte[] v4 = /*EL:90*/func_74794_a.func_74770_j("Data");
        int v5 = /*EL:91*/1;
        int v6 = 1;
        int v7 = 1;
        /*SL:92*/for (int v8 = 0; v8 < v3.length; ++v8) {
            final int v9 = /*EL:94*/(short)(v3[v8] & 0xFF);
            /*SL:95*/this.blocks[v6 - 1][v7 - 1][v5 - 1] = Block.func_149729_e(v9);
            /*SL:96*/this.blockData[v6 - 1][v7 - 1][v5 - 1] = v4[v8];
            /*SL:98*/if (++v5 > this.length) {
                /*SL:100*/v5 = 1;
                /*SL:101*/++v7;
            }
            /*SL:103*/if (v7 > this.width) {
                /*SL:105*/v7 = 1;
                /*SL:106*/++v6;
            }
        }
        final NBTTagList v10 = /*EL:110*/func_74794_a.func_150295_c("Entities", 10);
        /*SL:111*/this.entities = new NBTTagCompound[v10.func_74745_c()];
        /*SL:112*/for (int v9 = 0; v9 < v10.func_74745_c(); ++v9) {
            /*SL:113*/this.entities[v9] = v10.func_150305_b(v9);
        }
        final NBTTagList v11 = /*EL:115*/func_74794_a.func_150295_c("TileEntities", 10);
        /*SL:116*/this.tileEntities = new NBTTagCompound[v11.func_74745_c()];
        /*SL:117*/for (int v12 = 0; v12 < v11.func_74745_c(); ++v12) {
            /*SL:118*/this.tileEntities[v12] = v11.func_150305_b(v12);
        }
        /*SL:120*/this.initCenterPos();
    }
}
