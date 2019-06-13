package mod.lucky.structure;

import mod.lucky.drop.value.DropStringUtils;
import mod.lucky.drop.value.ValueParser;
import mod.lucky.util.LuckyReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import net.minecraft.util.math.Vec3d;
import mod.lucky.drop.func.DropFunction;
import mod.lucky.structure.rotation.Rotations;
import net.minecraft.util.math.BlockPos;
import net.minecraft.init.Blocks;
import mod.lucky.drop.func.DropProcessData;
import mod.lucky.drop.DropProperties;
import java.util.ArrayList;

public class LuckyStructure extends Structure
{
    private ArrayList<DropProperties> blocks;
    private ArrayList<DropProperties> entities;
    
    @Override
    public void process(final DropProcessData v-5) {
        final DropProperties dropProperties = /*EL:22*/v-5.getDropProperties();
        final Vec3d vecPos = /*EL:23*/dropProperties.getVecPos();
        final int intValue = /*EL:24*/dropProperties.getPropertyInt("rotation");
        final BlockPlacer a2 = /*EL:25*/new BlockPlacer(v-5.getWorld());
        /*SL:27*/if (!this.blockMode.equals("overlay") && !this.blockMode.equals("air")) {
            /*SL:28*/for (int v0 = 0; v0 < this.length; ++v0) {
                /*SL:29*/for (int v = 0; v < this.height; ++v) {
                    /*SL:30*/for (int a1 = 0; a1 < this.width; ++a1) {
                        /*SL:31*/StructureUtils.setBlock(a2, Blocks.field_150350_a.func_176223_P(), /*EL:33*/new BlockPos(v0, v, a1), this.getCenterPos(), /*EL:35*/vecPos, intValue);
                    }
                }
            }
        }
        final DropProcessData v2 = /*EL:43*/v-5.copy();
        /*SL:44*/v2.setProcessType(DropProcessData.EnumProcessType.LUCKY_STRUCT);
        /*SL:45*/for (DropProperties v3 : this.blocks) {
            /*SL:46*/v3 = v3.initialize(v2);
            /*SL:47*/v2.setDropProperties(v3);
            /*SL:48*/if (this.blockMode.equals("air")) {
                /*SL:49*/if (v3.getBlockState().func_177230_c() == Blocks.field_150350_a) {
                    continue;
                }
                /*SL:50*/StructureUtils.setBlock(a2, Blocks.field_150350_a.func_176223_P(), /*EL:52*/v3.getBlockPos(), /*EL:53*/this.getCenterPos(), /*EL:54*/vecPos, intValue);
            }
            else {
                /*SL:58*/StructureUtils.setBlock(a2, v3.getBlockState(), /*EL:60*/v3.getBlockPos(), /*EL:61*/this.getCenterPos(), /*EL:62*/vecPos, intValue);
                /*SL:65*/if (v3.getPropertyNBT("tileEntity") == null) {
                    continue;
                }
                /*SL:66*/StructureUtils.setTileEntity(v2.getWorld(), /*EL:67*/v3.getPropertyNBT("tileEntity"), /*EL:68*/v3.getBlockPos(), /*EL:69*/this.getCenterPos(), /*EL:70*/vecPos, intValue);
            }
        }
        final DropProcessData v4 = /*EL:76*/v-5.copy();
        /*SL:77*/v4.setProcessType(DropProcessData.EnumProcessType.LUCKY_STRUCT);
        /*SL:78*/for (DropProperties v5 : this.entities) {
            /*SL:79*/v5 = v5.initialize(v4);
            /*SL:80*/v4.setDropProperties(v5);
            final Vec3d v6 = /*EL:81*/v5.getVecPos();
            /*SL:82*/if (v5.getPropertyNBT("NBTTag") != null) {
                /*SL:83*/Rotations.rotateEntity(v5.getPropertyNBT("NBTTag"), /*EL:84*/vecPos.func_178787_e(this.getCenterPos()), /*EL:85*/intValue);
            }
            /*SL:87*/v5.setVecPos(/*EL:88*/StructureUtils.getWorldPos(v6, this.getCenterPos(), vecPos, intValue));
            /*SL:89*/v4.setDropProperties(v5);
            /*SL:90*/DropFunction.getDropFunction("entity").process(v4);
            /*SL:91*/v5.setVecPos(v6);
        }
        /*SL:94*/if (this.blockUpdate) {
            a2.update();
        }
        /*SL:95*/this.processOverlay(v-5);
    }
    
    @Override
    public void readFromFile() {
        try {
            final LuckyReader luckyReader = /*EL:101*/new LuckyReader(new InputStreamReader(this.fileStream));
            String s = /*EL:103*/"";
            /*SL:106*/this.blocks = new ArrayList<DropProperties>();
            /*SL:107*/this.entities = new ArrayList<DropProperties>();
            String v0;
            /*SL:109*/while ((v0 = luckyReader.readLine()) != null) {
                /*SL:110*/if (v0.startsWith(">")) {
                    /*SL:111*/s = v0;
                }
                else {
                    /*SL:115*/if (s.equals(">properties")) {
                        final String v = /*EL:116*/v0.substring(0, v0.indexOf(61));
                        final String v2 = /*EL:117*/v0.substring(v0.indexOf(61) + 1, v0.length());
                        /*SL:119*/if (v.equals("length")) {
                            this.length = ValueParser.getInteger(v2);
                        }
                        /*SL:120*/if (v.equals("width")) {
                            this.width = ValueParser.getInteger(v2);
                        }
                        /*SL:121*/if (v.equals("height")) {
                            this.height = ValueParser.getInteger(v2);
                        }
                        final int v3 = /*EL:123*/this.length * this.width * this.height;
                        /*SL:124*/if (v3 > 100000) {
                            System.err.println(/*EL:125*/"Lucky Block: Error loading structure. The structure '" + this.getId() + /*EL:127*/"' (" + v3 + " blocks) exceeds the " + 100000 + " block limit");
                            /*SL:133*/luckyReader.close();
                            /*SL:134*/return;
                        }
                    }
                    /*SL:137*/if (s.equals(">blocks")) {
                        final DropProperties v4 = /*EL:138*/new DropProperties();
                        final String[] v5 = /*EL:139*/DropStringUtils.splitBracketString(v0, ',');
                        /*SL:141*/v4.setRawProperty("type", "block");
                        /*SL:142*/v4.setRawProperty("posX", v5[0]);
                        /*SL:143*/v4.setRawProperty("posY", v5[1]);
                        /*SL:144*/v4.setRawProperty("posZ", v5[2]);
                        /*SL:145*/v4.setRawProperty("ID", v5[3]);
                        /*SL:146*/if (v5.length > 4) {
                            v4.setRawProperty("meta", v5[4]);
                        }
                        /*SL:147*/if (v5.length > 5) {
                            v4.setRawProperty("tileEntity", v5[5]);
                        }
                        /*SL:148*/this.blocks.add(v4);
                    }
                    /*SL:150*/if (!s.equals(">entities")) {
                        continue;
                    }
                    final DropProperties v4 = /*EL:151*/new DropProperties();
                    final String[] v5 = /*EL:152*/DropStringUtils.splitBracketString(v0, ',');
                    /*SL:154*/v4.setRawProperty("type", "entity");
                    /*SL:155*/v4.setRawProperty("posX", v5[0]);
                    /*SL:156*/v4.setRawProperty("posY", v5[1]);
                    /*SL:157*/v4.setRawProperty("posZ", v5[2]);
                    /*SL:158*/v4.setRawProperty("ID", v5[3]);
                    /*SL:159*/if (v5.length > 4) {
                        v4.setRawProperty("NBTTag", v5[4]);
                    }
                    /*SL:160*/this.blocks.add(v4);
                }
            }
            /*SL:163*/luckyReader.close();
            /*SL:165*/this.initCenterPos();
        }
        catch (Exception ex) {
            System.err.println(/*EL:167*/"Lucky Block: Error loading structure '" + this.getId() + "'");
            /*SL:168*/ex.printStackTrace();
        }
    }
}
