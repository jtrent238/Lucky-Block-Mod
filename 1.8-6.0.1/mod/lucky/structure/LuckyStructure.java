package mod.lucky.structure;

import mod.lucky.drop.value.DropStringUtils;
import mod.lucky.drop.value.ValueParser;
import mod.lucky.util.LuckyReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import mod.lucky.drop.func.DropFunction;
import mod.lucky.structure.rotation.Rotations;
import net.minecraft.util.BlockPos;
import net.minecraft.init.Blocks;
import net.minecraft.util.Vec3;
import mod.lucky.drop.func.DropProcessData;
import mod.lucky.drop.DropProperties;
import java.util.ArrayList;

public class LuckyStructure extends Structure
{
    private ArrayList<DropProperties> blocks;
    private ArrayList<DropProperties> entities;
    
    @Override
    public void process(final DropProcessData v-5) {
        final DropProperties dropProperties = /*EL:25*/v-5.getDropProperties();
        final Vec3 vec3 = /*EL:26*/new Vec3(dropProperties.getPropertyInt("posX") + 0.5, (double)dropProperties.getPropertyInt("posY"), dropProperties.getPropertyInt("posZ") + 0.5);
        final int intValue = /*EL:27*/dropProperties.getPropertyInt("rotation");
        final BlockPlacer blockPlacer = /*EL:28*/new BlockPlacer(v-5.getWorld());
        /*SL:30*/if (!this.blockMode.equals("overlay")) {
            /*SL:32*/for (int v0 = 0; v0 < this.length; ++v0) {
                /*SL:34*/for (int v = 0; v < this.height; ++v) {
                    /*SL:36*/for (int a1 = 0; a1 < this.width; ++a1) {
                        /*SL:38*/StructureUtils.setBlock(blockPlacer, Blocks.field_150350_a.func_176223_P(), new BlockPos(v0, v, a1), this.getCenterPos(), vec3, intValue);
                    }
                }
            }
        }
        final DropProcessData v2 = /*EL:44*/v-5.copy();
        /*SL:45*/v2.setProcessType(DropProcessData.EnumProcessType.LUCKY_STRUCT);
        /*SL:46*/for (DropProperties v3 : this.blocks) {
            /*SL:48*/v3 = v3.initialize(v2);
            /*SL:49*/v2.setDropProperties(v3);
            /*SL:50*/StructureUtils.setBlock(blockPlacer, v3.getBlockState(), v3.getBlockPos(), this.getCenterPos(), vec3, intValue);
            /*SL:51*/if (v3.getPropertyNBT("tileEntity") != null) {
                StructureUtils.setTileEntity(v2.getWorld(), v3.getPropertyNBT("tileEntity"), v3.getBlockPos(), this.getCenterPos(), vec3, intValue);
            }
        }
        final DropProcessData v4 = /*EL:54*/v-5.copy();
        /*SL:55*/v4.setProcessType(DropProcessData.EnumProcessType.LUCKY_STRUCT);
        /*SL:56*/for (DropProperties v5 : this.entities) {
            /*SL:58*/v5 = v5.initialize(v4);
            /*SL:59*/v4.setDropProperties(v5);
            final Vec3 v6 = /*EL:60*/v5.getVecPos();
            /*SL:61*/if (v5.getPropertyNBT("NBTTag") != null) {
                Rotations.rotateEntity(v5.getPropertyNBT("NBTTag"), vec3.func_178787_e(this.getCenterPos()), intValue);
            }
            /*SL:62*/v5.setVecPos(StructureUtils.getWorldPos(v6, this.getCenterPos(), vec3, intValue));
            /*SL:63*/v4.setDropProperties(v5);
            /*SL:64*/DropFunction.getDropFunction("entity").process(v4);
            /*SL:65*/v5.setVecPos(v6);
        }
        /*SL:68*/if (this.blockUpdate) {
            blockPlacer.update();
        }
        /*SL:69*/this.processOverlay(v-5);
    }
    
    @Override
    public void readFromFile() {
        try {
            final LuckyReader luckyReader = /*EL:77*/new LuckyReader(new InputStreamReader(this.fileStream));
            String s = /*EL:79*/"";
            /*SL:82*/this.blocks = new ArrayList<DropProperties>();
            /*SL:83*/this.entities = new ArrayList<DropProperties>();
            String v0;
            /*SL:85*/while ((v0 = luckyReader.readLine()) != null) {
                /*SL:87*/if (v0.startsWith(">")) {
                    /*SL:89*/s = v0;
                }
                else {
                    /*SL:93*/if (s.equals(">properties")) {
                        final String v = /*EL:95*/v0.substring(0, v0.indexOf(61));
                        final String v2 = /*EL:96*/v0.substring(v0.indexOf(61) + 1, v0.length());
                        /*SL:98*/if (v.equals("length")) {
                            this.length = ValueParser.getInteger(v2);
                        }
                        /*SL:99*/if (v.equals("width")) {
                            this.width = ValueParser.getInteger(v2);
                        }
                        /*SL:100*/if (v.equals("height")) {
                            this.height = ValueParser.getInteger(v2);
                        }
                        final int v3 = /*EL:102*/this.length * this.width * this.height;
                        /*SL:103*/if (v3 > 100000) {
                            System.err.println(/*EL:105*/"Lucky Block: Error loading structure. The structure '" + this.getId() + "' (" + v3 + " blocks) exceeds the " + 100000 + " block limit");
                            /*SL:106*/luckyReader.close();
                            /*SL:107*/return;
                        }
                    }
                    /*SL:110*/if (s.equals(">blocks")) {
                        final DropProperties v4 = /*EL:112*/new DropProperties();
                        final String[] v5 = /*EL:113*/DropStringUtils.splitBracketString(v0, ',');
                        /*SL:115*/v4.setRawProperty("type", "block");
                        /*SL:116*/v4.setRawProperty("posX", v5[0]);
                        /*SL:117*/v4.setRawProperty("posY", v5[1]);
                        /*SL:118*/v4.setRawProperty("posZ", v5[2]);
                        /*SL:119*/v4.setRawProperty("ID", v5[3]);
                        /*SL:120*/if (v5.length > 4) {
                            v4.setRawProperty("meta", v5[4]);
                        }
                        /*SL:121*/if (v5.length > 5) {
                            v4.setRawProperty("tileEntity", v5[5]);
                        }
                        /*SL:122*/this.blocks.add(v4);
                    }
                    /*SL:124*/if (!s.equals(">entities")) {
                        continue;
                    }
                    final DropProperties v4 = /*EL:126*/new DropProperties();
                    final String[] v5 = /*EL:127*/DropStringUtils.splitBracketString(v0, ',');
                    /*SL:129*/v4.setRawProperty("type", "entity");
                    /*SL:130*/v4.setRawProperty("posX", v5[0]);
                    /*SL:131*/v4.setRawProperty("posY", v5[1]);
                    /*SL:132*/v4.setRawProperty("posZ", v5[2]);
                    /*SL:133*/v4.setRawProperty("ID", v5[3]);
                    /*SL:134*/if (v5.length > 4) {
                        v4.setRawProperty("NBTTag", v5[4]);
                    }
                    /*SL:135*/this.blocks.add(v4);
                }
            }
            /*SL:138*/luckyReader.close();
            /*SL:140*/this.initCenterPos();
        }
        catch (Exception ex) {
            System.err.println(/*EL:144*/"Lucky Block: Error loading structure '" + this.getId() + "'");
            /*SL:145*/ex.printStackTrace();
        }
    }
}
