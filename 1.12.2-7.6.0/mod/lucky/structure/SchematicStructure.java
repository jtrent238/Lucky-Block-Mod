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
        final DropProperties dropProperties = /*EL:27*/v-5.getDropProperties();
        final Vec3d vecPos = /*EL:28*/dropProperties.getVecPos();
        final int intValue = /*EL:29*/dropProperties.getPropertyInt("rotation");
        final BlockPlacer a2 = /*EL:30*/new BlockPlacer(v-5.getWorld());
        /*SL:32*/for (int v0 = 0; v0 < this.height; ++v0) {
            /*SL:33*/for (int v = 0; v < this.width; ++v) {
                /*SL:34*/for (int a1 = 0; a1 < this.length; ++a1) {
                    /*SL:35*/if (this.blockMode.equals("overlay")) {
                        /*SL:36*/if (this.blocks[v0][v][a1] != Blocks.field_150350_a) {
                            /*SL:37*/StructureUtils.setBlock(a2, this.blocks[v0][v][a1].func_176203_a(this.blockData[v0][v][a1]), /*EL:39*/new BlockPos(a1, v0, v), this.getCenterPos(), /*EL:41*/vecPos, intValue);
                        }
                    }
                    else/*SL:44*/ if (this.blockMode.equals("air")) {
                        /*SL:45*/if (this.blocks[v0][v][a1] != Blocks.field_150350_a) {
                            /*SL:46*/StructureUtils.setBlock(a2, Blocks.field_150350_a.func_176223_P(), /*EL:48*/new BlockPos(a1, v0, v), this.getCenterPos(), /*EL:50*/vecPos, intValue);
                        }
                    }
                    else {
                        /*SL:54*/StructureUtils.setBlock(a2, this.blocks[v0][v][a1].func_176203_a(this.blockData[v0][v][a1]), /*EL:56*/new BlockPos(a1, v0, v), this.getCenterPos(), /*EL:58*/vecPos, intValue);
                    }
                }
            }
        }
        /*SL:65*/for (final NBTTagCompound v2 : this.tileEntities) {
            /*SL:66*/StructureUtils.setTileEntity(v-5.getWorld(), /*EL:68*/TileEntity.func_190200_a(v-5.getWorld(), v2), this.getCenterPos(), /*EL:69*/vecPos, intValue);
        }
        /*SL:73*/for (final NBTTagCompound v2 : this.entities) {
            /*SL:74*/StructureUtils.setEntity(v-5.getWorld(), /*EL:76*/EntityList.func_75615_a(v2, v-5.getWorld()), this.getCenterPos(), /*EL:77*/vecPos, intValue);
        }
        /*SL:81*/if (this.blockUpdate) {
            a2.update();
        }
        /*SL:82*/this.processOverlay(v-5);
    }
    
    @Override
    public void readFromFile() {
        NBTTagCompound v0 = /*EL:87*/null;
        try {
            final DataInputStream v = /*EL:90*/new DataInputStream(new GZIPInputStream(this.fileStream));
            /*SL:91*/v0 = CompressedStreamTools.func_74794_a(v);
            /*SL:92*/v.close();
        }
        catch (Exception v2) {
            System.err.println(/*EL:94*/"Lucky Block: Error loading structure '" + this.getId() + "'");
            /*SL:95*/v2.printStackTrace();
        }
        /*SL:99*/this.length = v0.func_74765_d("Width");
        /*SL:100*/this.width = v0.func_74765_d("Length");
        /*SL:101*/this.height = v0.func_74765_d("Height");
        final int v3 = /*EL:103*/this.length * this.width * this.height;
        /*SL:104*/if (v3 > 100000) {
            System.err.println(/*EL:105*/"Lucky Block: Error loading structure. The structure '" + this.getId() + /*EL:107*/"' (" + v3 + " blocks) exceeds the " + 100000 + " block limit");
            /*SL:113*/return;
        }
        /*SL:116*/this.blocks = new Block[this.height][this.width][this.length];
        /*SL:117*/this.blockData = new int[this.height][this.width][this.length];
        final byte[] v4 = /*EL:119*/v0.func_74770_j("Blocks");
        final byte[] v5 = /*EL:120*/v0.func_74770_j("Data");
        int v6 = /*EL:121*/1;
        int v7 = 1;
        int v8 = 1;
        /*SL:122*/for (int v9 = 0; v9 < v4.length; ++v9) {
            final int v10 = /*EL:123*/(short)(v4[v9] & 0xFF);
            /*SL:124*/this.blocks[v7 - 1][v8 - 1][v6 - 1] = Block.func_149729_e(v10);
            /*SL:125*/this.blockData[v7 - 1][v8 - 1][v6 - 1] = v5[v9];
            /*SL:127*/if (++v6 > this.length) {
                /*SL:128*/v6 = 1;
                /*SL:129*/++v8;
            }
            /*SL:131*/if (v8 > this.width) {
                /*SL:132*/v8 = 1;
                /*SL:133*/++v7;
            }
        }
        final NBTTagList v11 = /*EL:137*/v0.func_150295_c("Entities", 10);
        /*SL:138*/this.entities = new NBTTagCompound[v11.func_74745_c()];
        /*SL:139*/for (int v10 = 0; v10 < v11.func_74745_c(); ++v10) {
            /*SL:140*/this.entities[v10] = v11.func_150305_b(v10);
        }
        final NBTTagList v12 = /*EL:142*/v0.func_150295_c("TileEntities", 10);
        /*SL:143*/this.tileEntities = new NBTTagCompound[v12.func_74745_c()];
        /*SL:144*/for (int v13 = 0; v13 < v12.func_74745_c(); ++v13) {
            /*SL:145*/this.tileEntities[v13] = v12.func_150305_b(v13);
        }
        /*SL:147*/this.initCenterPos();
    }
}
