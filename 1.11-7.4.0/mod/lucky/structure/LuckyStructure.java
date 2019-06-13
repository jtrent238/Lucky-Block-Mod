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
        final DropProperties dropProperties = /*EL:25*/v-5.getDropProperties();
        final Vec3d vecPos = /*EL:26*/dropProperties.getVecPos();
        final int intValue = /*EL:27*/dropProperties.getPropertyInt("rotation");
        final BlockPlacer a2 = /*EL:28*/new BlockPlacer(v-5.getWorld());
        /*SL:30*/if (!this.blockMode.equals("overlay") && !this.blockMode.equals("air")) {
            /*SL:32*/for (int v0 = 0; v0 < this.length; ++v0) {
                /*SL:34*/for (int v = 0; v < this.height; ++v) {
                    /*SL:36*/for (int a1 = 0; a1 < this.width; ++a1) {
                        /*SL:38*/StructureUtils.setBlock(a2, Blocks.field_150350_a.func_176223_P(), new BlockPos(v0, v, a1), this.getCenterPos(), vecPos, intValue);
                    }
                }
            }
        }
        final DropProcessData v2 = /*EL:44*/v-5.copy();
        /*SL:45*/v2.setProcessType(DropProcessData.EnumProcessType.LUCKY_STRUCT);
        /*SL:46*/for (DropProperties v3 : this.blocks) {
            /*SL:48*/v3 = v3.initialize(v2);
            /*SL:49*/v2.setDropProperties(v3);
            /*SL:50*/if (this.blockMode.equals("air")) {
                /*SL:52*/if (v3.getBlockState().func_177230_c() == Blocks.field_150350_a) {
                    continue;
                }
                StructureUtils.setBlock(a2, Blocks.field_150350_a.func_176223_P(), v3.getBlockPos(), this.getCenterPos(), vecPos, intValue);
            }
            else {
                /*SL:56*/StructureUtils.setBlock(a2, v3.getBlockState(), v3.getBlockPos(), this.getCenterPos(), vecPos, intValue);
                /*SL:57*/if (v3.getPropertyNBT("tileEntity") == null) {
                    continue;
                }
                StructureUtils.setTileEntity(v2.getWorld(), v3.getPropertyNBT("tileEntity"), v3.getBlockPos(), this.getCenterPos(), vecPos, intValue);
            }
        }
        final DropProcessData v4 = /*EL:61*/v-5.copy();
        /*SL:62*/v4.setProcessType(DropProcessData.EnumProcessType.LUCKY_STRUCT);
        /*SL:63*/for (DropProperties v5 : this.entities) {
            /*SL:65*/v5 = v5.initialize(v4);
            /*SL:66*/v4.setDropProperties(v5);
            final Vec3d v6 = /*EL:67*/v5.getVecPos();
            /*SL:68*/if (v5.getPropertyNBT("NBTTag") != null) {
                Rotations.rotateEntity(v5.getPropertyNBT("NBTTag"), vecPos.func_178787_e(this.getCenterPos()), intValue);
            }
            /*SL:69*/v5.setVecPos(StructureUtils.getWorldPos(v6, this.getCenterPos(), vecPos, intValue));
            /*SL:70*/v4.setDropProperties(v5);
            /*SL:71*/DropFunction.getDropFunction("entity").process(v4);
            /*SL:72*/v5.setVecPos(v6);
        }
        /*SL:75*/if (this.blockUpdate) {
            a2.update();
        }
        /*SL:76*/this.processOverlay(v-5);
    }
    
    @Override
    public void readFromFile() {
        try {
            final LuckyReader luckyReader = /*EL:84*/new LuckyReader(new InputStreamReader(this.fileStream));
            String s = /*EL:86*/"";
            /*SL:89*/this.blocks = new ArrayList<DropProperties>();
            /*SL:90*/this.entities = new ArrayList<DropProperties>();
            String v0;
            /*SL:92*/while ((v0 = luckyReader.readLine()) != null) {
                /*SL:94*/if (v0.startsWith(">")) {
                    /*SL:96*/s = v0;
                }
                else {
                    /*SL:100*/if (s.equals(">properties")) {
                        final String v = /*EL:102*/v0.substring(0, v0.indexOf(61));
                        final String v2 = /*EL:103*/v0.substring(v0.indexOf(61) + 1, v0.length());
                        /*SL:105*/if (v.equals("length")) {
                            this.length = ValueParser.getInteger(v2);
                        }
                        /*SL:106*/if (v.equals("width")) {
                            this.width = ValueParser.getInteger(v2);
                        }
                        /*SL:107*/if (v.equals("height")) {
                            this.height = ValueParser.getInteger(v2);
                        }
                        final int v3 = /*EL:109*/this.length * this.width * this.height;
                        /*SL:110*/if (v3 > 100000) {
                            System.err.println(/*EL:112*/"Lucky Block: Error loading structure. The structure '" + this.getId() + "' (" + v3 + " blocks) exceeds the " + 100000 + " block limit");
                            /*SL:113*/luckyReader.close();
                            /*SL:114*/return;
                        }
                    }
                    /*SL:117*/if (s.equals(">blocks")) {
                        final DropProperties v4 = /*EL:119*/new DropProperties();
                        final String[] v5 = /*EL:120*/DropStringUtils.splitBracketString(v0, ',');
                        /*SL:122*/v4.setRawProperty("type", "block");
                        /*SL:123*/v4.setRawProperty("posX", v5[0]);
                        /*SL:124*/v4.setRawProperty("posY", v5[1]);
                        /*SL:125*/v4.setRawProperty("posZ", v5[2]);
                        /*SL:126*/v4.setRawProperty("ID", v5[3]);
                        /*SL:127*/if (v5.length > 4) {
                            v4.setRawProperty("meta", v5[4]);
                        }
                        /*SL:128*/if (v5.length > 5) {
                            v4.setRawProperty("tileEntity", v5[5]);
                        }
                        /*SL:129*/this.blocks.add(v4);
                    }
                    /*SL:131*/if (!s.equals(">entities")) {
                        continue;
                    }
                    final DropProperties v4 = /*EL:133*/new DropProperties();
                    final String[] v5 = /*EL:134*/DropStringUtils.splitBracketString(v0, ',');
                    /*SL:136*/v4.setRawProperty("type", "entity");
                    /*SL:137*/v4.setRawProperty("posX", v5[0]);
                    /*SL:138*/v4.setRawProperty("posY", v5[1]);
                    /*SL:139*/v4.setRawProperty("posZ", v5[2]);
                    /*SL:140*/v4.setRawProperty("ID", v5[3]);
                    /*SL:141*/if (v5.length > 4) {
                        v4.setRawProperty("NBTTag", v5[4]);
                    }
                    /*SL:142*/this.blocks.add(v4);
                }
            }
            /*SL:145*/luckyReader.close();
            /*SL:147*/this.initCenterPos();
        }
        catch (Exception ex) {
            System.err.println(/*EL:151*/"Lucky Block: Error loading structure '" + this.getId() + "'");
            /*SL:152*/ex.printStackTrace();
        }
    }
}
