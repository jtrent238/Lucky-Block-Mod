package mod.lucky.util;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.entity.Entity;
import mod.lucky.entity.EntityLuckyFallingBlock;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import mod.lucky.network.ParticlePacket;
import mod.lucky.Lucky;
import net.minecraft.block.Block;
import mod.lucky.drop.LuckyDrop;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class SpawnOther
{
    public void spawnOther(final World v-6, final EntityPlayer v-5, final Random v-4, final LuckyDrop v-3) {
        /*SL:32*/CustomStructures.setRandom(v-4);
        final int posX = /*EL:34*/v-3.getPosX();
        final int posY = /*EL:35*/v-3.getPosY();
        final int v0 = /*EL:36*/v-3.getPosZ();
        /*SL:38*/if (v-3.getType().equals("block")) {
            /*SL:40*/StructureUtil.setBlock(v-6, posX, posY, v0, Block.func_149684_b(v-3.getId()), v-3.getDamage());
            try {
                /*SL:43*/if (v-3.getNBTTag() != null) {
                    final TileEntity a1 = /*EL:45*/v-6.func_147438_o(v-3.getPosX(), v-3.getPosY(), v-3.getPosZ());
                    /*SL:46*/v-3.getNBTTag().func_74768_a("x", v-3.getPosX());
                    /*SL:47*/v-3.getNBTTag().func_74768_a("y", v-3.getPosY());
                    /*SL:48*/v-3.getNBTTag().func_74768_a("z", v-3.getPosZ());
                    /*SL:49*/a1.func_145839_a(v-3.getNBTTag());
                }
            }
            catch (Exception ex) {}
        }
        /*SL:56*/if (v-3.getType().equals("particle")) {
            try {
                final int a2 = /*EL:60*/Integer.valueOf(v-3.getId());
                /*SL:61*/v-6.func_72926_e(a2, posX, posY, v0, v-3.getDamage());
            }
            catch (NumberFormatException a3) {
                Lucky.networkHandler.sendToAll(/*EL:65*/(IMessage)new ParticlePacket("heart", posX, posY, v0));
            }
        }
        /*SL:70*/if (v-3.getType().equals("falling_block")) {
            /*SL:72*/StructureUtil.fill(v-6, posX, posY, v0, 1, 6, 1, Blocks.field_150350_a);
            final EntityLuckyFallingBlock v = /*EL:73*/new EntityLuckyFallingBlock(v-6, posX + 0.5, posY + 0.5 + 6.0, v0 + 0.5, Block.func_149684_b(v-3.getId()), v-3.getDamage());
            /*SL:74*/if (v-3.getNBTTag() != null) {
                v.setNBTTagCompound(v-3.getNBTTag());
            }
            /*SL:75*/this.spawnEntity(v-6, (Entity)v);
        }
        /*SL:78*/if (v-3.getType().equals("structure")) {
            /*SL:80*/if (v-3.getId().equals("anvil_trap")) {
                /*SL:82*/CustomStructures.makeCage(v-6, v-5, posX, posY, v0, 3);
                final String[] v2 = /*EL:83*/{ "", "Look Up", "", "", "" };
                /*SL:84*/CustomStructures.makeCageSign(v-6, v-5, posX, posY, v0, v2);
                /*SL:85*/StructureUtil.fill(v-6, posX, posY, v0, 1, 64, 1, Blocks.field_150350_a);
                /*SL:86*/StructureUtil.setBlock(v-6, posX, posY + 64, v0, Blocks.field_150467_bQ);
                /*SL:87*/StructureUtil.setBlock(v-6, posX, posY + 63, v0, Blocks.field_150467_bQ);
            }
            /*SL:89*/if (v-3.getId().equals("lava_trap")) {
                /*SL:91*/CustomStructures.makeCage(v-6, v-5, posX, posY, v0, 4);
                final String[] v2 = /*EL:92*/{ "", "Look Up", "", "", "" };
                /*SL:93*/CustomStructures.makeCageSign(v-6, v-5, posX, posY, v0, v2);
                /*SL:94*/StructureUtil.setBlock(v-6, posX, posY + 3, v0, (Block)Blocks.field_150356_k);
            }
            /*SL:96*/if (v-3.getId().equals("water_trap")) {
                /*SL:98*/CustomStructures.makeWaterCage(v-6, v-5, posX, posY, v0);
            }
            /*SL:100*/if (v-3.getId().equals("pit_trap")) {
                /*SL:102*/CustomStructures.makePitTrap(v-6, v-5, posX, posY, v0);
            }
            /*SL:104*/if (v-3.getId().equals("lucky_choice")) {
                final String[] v2 = /*EL:106*/{ "", "One is lucky.", "One is not.", "", "" };
                /*SL:107*/CustomStructures.makeLuckyBlockChoice(v-6, v-5, posX, posY, v0, v2);
            }
            /*SL:109*/if (v-3.getId().equals("fort")) {
                /*SL:111*/CustomStructures.makeFort(v-6, v-5, posX, posY, v0);
            }
            /*SL:113*/if (v-3.getId().equals("temple")) {
                /*SL:115*/CustomStructures.makeTemple(v-6, v-5, posX, posY, v0);
            }
            /*SL:117*/if (v-3.getId().equals("bedrock_problem")) {
                /*SL:119*/StructureUtil.setBlock(v-6, v-3.getPosX(), v-3.getPosY(), v-3.getPosZ(), Blocks.field_150357_h);
                final String[] v2 = /*EL:120*/{ "", "Well, there's", "your problem.", "", "" };
                /*SL:121*/StructureUtil.makeSign(v-6, posX, posY + 1, v0, Blocks.field_150472_an, LuckyFunction.getPlayerDirection(v-5, 16) + 8, v2);
            }
            /*SL:123*/if (v-3.getId().equals("explosion")) {
                /*SL:125*/v-6.func_72876_a((Entity)null, (double)posX, (double)posY, (double)v0, (float)(v-3.getDamage() * 2), true);
            }
            /*SL:127*/if (v-3.getId().equals("wishing_well")) {
                /*SL:129*/CustomStructures.makeWishingWell(v-6, v-5, posX, posY, v0, v-3.getNBTTag());
            }
            /*SL:131*/if (v-3.getId().equals("chest")) {
                final Random v3 = /*EL:133*/new Random();
                final int v4 = /*EL:134*/LuckyFunction.getPlayerDirection(v-5, 4);
                /*SL:135*/StructureUtil.setBlock(v-6, posX, posY, v0, (Block)Blocks.field_150486_ae);
                /*SL:136*/switch (v4) {
                    case 0: {
                        /*SL:139*/StructureUtil.setBlockMeta(v-6, posX, posY, v0, 2);
                        /*SL:140*/break;
                    }
                    case 1: {
                        /*SL:142*/StructureUtil.setBlockMeta(v-6, posX, posY, v0, 5);
                        /*SL:143*/break;
                    }
                    case 2: {
                        /*SL:145*/StructureUtil.setBlockMeta(v-6, posX, posY, v0, 3);
                        /*SL:146*/break;
                    }
                    case 3: {
                        /*SL:148*/StructureUtil.setBlockMeta(v-6, posX, posY, v0, 4);
                        break;
                    }
                }
                final TileEntityChest v5 = /*EL:151*/(TileEntityChest)v-6.func_147438_o(posX, posY, v0);
                /*SL:153*/if (v5 != null && v5 != null) {
                    try {
                        /*SL:157*/if (v-3.getNBTTag() != null) {
                            /*SL:159*/if (v-3.getNBTTag().func_74764_b("type")) {
                                final String v6 = /*EL:161*/v-3.getNBTTag().func_74779_i("type");
                                /*SL:162*/WeightedRandomChestContent.func_76293_a(v3, ChestGenHooks.getItems(v6, v3), (IInventory)v5, ChestGenHooks.getCount(v6, v3));
                            }
                            else {
                                final NBTTagList v7 = /*EL:167*/v-3.getNBTTag().func_150295_c("items", 10);
                                final int v8 = /*EL:169*/v-3.getNBTTag().func_74762_e("min");
                                final int v9 = /*EL:170*/v-3.getNBTTag().func_74762_e("max");
                                final int v10 = /*EL:171*/(v8 < v9) ? (v8 + v3.nextInt(v9 - v8)) : v9;
                                final WeightedRandomChestContent[] v11 = /*EL:173*/new WeightedRandomChestContent[v7.func_74745_c()];
                                /*SL:174*/for (int v12 = 0; v12 < v7.func_74745_c(); ++v12) {
                                    final NBTTagCompound v13 = /*EL:176*/v7.func_150305_b(v12);
                                    final int v14 = /*EL:180*/v13.func_74762_e("id");
                                    final int v15 = /*EL:181*/v13.func_74762_e("damage");
                                    int v16 = /*EL:182*/v13.func_74762_e("minAmount");
                                    int v17 = /*EL:183*/v13.func_74762_e("maxAmount");
                                    final int v18 = /*EL:184*/v13.func_74762_e("weight");
                                    /*SL:186*/if (v16 == 0) {
                                        v16 = 1;
                                    }
                                    /*SL:187*/if (v17 == 0) {
                                        v17 = 1;
                                    }
                                    ItemStack v20;
                                    /*SL:189*/if (v13.func_74764_b("item")) {
                                        final NBTTagCompound v19 = /*EL:191*/v13.func_74775_l("item");
                                        /*SL:192*/v20 = ItemStack.func_77949_a(v19);
                                    }
                                    else {
                                        /*SL:197*/v20 = new ItemStack(this.getItem(v14), 1, v15);
                                    }
                                    /*SL:200*/v11[v12] = new WeightedRandomChestContent(v20, v16, v17, v18);
                                }
                                /*SL:203*/WeightedRandomChestContent.func_76293_a(v3, v11, (IInventory)v5, v10);
                            }
                        }
                    }
                    catch (Exception v21) {
                        /*SL:209*/v21.printStackTrace();
                    }
                }
            }
        }
    }
    
    public boolean spawnEntity(final World v1, final Entity v2) {
        try {
            /*SL:221*/v1.func_72838_d(v2);
            /*SL:222*/return true;
        }
        catch (Exception a1) {
            /*SL:226*/a1.printStackTrace();
            /*SL:227*/return false;
        }
    }
    
    public Item getItem(final String a1) {
        /*SL:233*/return (Item)Item.field_150901_e.func_82594_a(a1);
    }
    
    public Item getItem(final int a1) {
        /*SL:238*/return (Item)Item.field_150901_e.func_148754_a(a1);
    }
}
