package mod.lucky.structure;

import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.CompressedStreamTools;
import java.io.InputStream;
import java.io.DataInputStream;
import java.util.zip.GZIPInputStream;
import net.minecraft.util.math.Vec3d;
import mod.lucky.drop.DropProperties;
import net.minecraft.entity.EntityList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.init.Blocks;
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
        final Vec3d vecPos = /*EL:31*/dropProperties.getVecPos();
        final int intValue = /*EL:32*/dropProperties.getPropertyInt("rotation");
        final BlockPlacer a2 = /*EL:33*/new BlockPlacer(v-5.getWorld());
        /*SL:35*/for (int v0 = 0; v0 < this.height; ++v0) {
            /*SL:37*/for (int v = 0; v < this.width; ++v) {
                /*SL:39*/for (int a1 = 0; a1 < this.length; ++a1) {
                    /*SL:41*/if (this.blockMode.equals("overlay")) {
                        /*SL:43*/if (this.blocks[v0][v][a1] != Blocks.field_150350_a) {
                            StructureUtils.setBlock(a2, this.blocks[v0][v][a1].func_176203_a(this.blockData[v0][v][a1]), new BlockPos(a1, v0, v), this.getCenterPos(), vecPos, intValue);
                        }
                    }
                    else/*SL:45*/ if (this.blockMode.equals("air")) {
                        /*SL:47*/if (this.blocks[v0][v][a1] != Blocks.field_150350_a) {
                            StructureUtils.setBlock(a2, Blocks.field_150350_a.func_176223_P(), new BlockPos(a1, v0, v), this.getCenterPos(), vecPos, intValue);
                        }
                    }
                    else {
                        /*SL:49*/StructureUtils.setBlock(a2, this.blocks[v0][v][a1].func_176203_a(this.blockData[v0][v][a1]), new BlockPos(a1, v0, v), this.getCenterPos(), vecPos, intValue);
                    }
                }
            }
        }
        /*SL:54*/for (final NBTTagCompound v2 : this.tileEntities) {
            /*SL:55*/StructureUtils.setTileEntity(v-5.getWorld(), TileEntity.func_189514_c(v2), this.getCenterPos(), vecPos, intValue);
        }
        /*SL:57*/for (final NBTTagCompound v2 : this.entities) {
            /*SL:58*/StructureUtils.setEntity(v-5.getWorld(), EntityList.func_75615_a(v2, v-5.getWorld()), this.getCenterPos(), vecPos, intValue);
        }
        /*SL:60*/if (this.blockUpdate) {
            a2.update();
        }
        /*SL:61*/this.processOverlay(v-5);
    }
    
    @Override
    public void readFromFile() {
        NBTTagCompound v0 = /*EL:67*/null;
        try {
            final DataInputStream v = /*EL:71*/new DataInputStream(new GZIPInputStream(this.fileStream));
            /*SL:72*/v0 = CompressedStreamTools.func_74794_a(v);
            /*SL:73*/v.close();
        }
        catch (Exception v2) {
            System.err.println(/*EL:77*/"Lucky Block: Error loading structure '" + this.getId() + "'");
            /*SL:78*/v2.printStackTrace();
        }
        /*SL:82*/this.length = v0.func_74765_d("Width");
        /*SL:83*/this.width = v0.func_74765_d("Length");
        /*SL:84*/this.height = v0.func_74765_d("Height");
        final int v3 = /*EL:86*/this.length * this.width * this.height;
        /*SL:87*/if (v3 > 100000) {
            System.err.println(/*EL:89*/"Lucky Block: Error loading structure. The structure '" + this.getId() + "' (" + v3 + " blocks) exceeds the " + 100000 + " block limit");
            /*SL:90*/return;
        }
        /*SL:93*/this.blocks = new Block[this.height][this.width][this.length];
        /*SL:94*/this.blockData = new int[this.height][this.width][this.length];
        final byte[] v4 = /*EL:96*/v0.func_74770_j("Blocks");
        final byte[] v5 = /*EL:97*/v0.func_74770_j("Data");
        int v6 = /*EL:98*/1;
        int v7 = 1;
        int v8 = 1;
        /*SL:99*/for (int v9 = 0; v9 < v4.length; ++v9) {
            final int v10 = /*EL:101*/(short)(v4[v9] & 0xFF);
            /*SL:102*/this.blocks[v7 - 1][v8 - 1][v6 - 1] = Block.func_149729_e(v10);
            /*SL:103*/this.blockData[v7 - 1][v8 - 1][v6 - 1] = v5[v9];
            /*SL:105*/if (++v6 > this.length) {
                /*SL:107*/v6 = 1;
                /*SL:108*/++v8;
            }
            /*SL:110*/if (v8 > this.width) {
                /*SL:112*/v8 = 1;
                /*SL:113*/++v7;
            }
        }
        final NBTTagList v11 = /*EL:117*/v0.func_150295_c("Entities", 10);
        /*SL:118*/this.entities = new NBTTagCompound[v11.func_74745_c()];
        /*SL:119*/for (int v10 = 0; v10 < v11.func_74745_c(); ++v10) {
            /*SL:120*/this.entities[v10] = v11.func_150305_b(v10);
        }
        final NBTTagList v12 = /*EL:122*/v0.func_150295_c("TileEntities", 10);
        /*SL:123*/this.tileEntities = new NBTTagCompound[v12.func_74745_c()];
        /*SL:124*/for (int v13 = 0; v13 < v12.func_74745_c(); ++v13) {
            /*SL:125*/this.tileEntities[v13] = v12.func_150305_b(v13);
        }
        /*SL:127*/this.initCenterPos();
    }
}
