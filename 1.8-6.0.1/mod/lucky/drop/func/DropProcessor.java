package mod.lucky.drop.func;

import java.util.Iterator;
import java.util.Random;
import mod.lucky.Lucky;
import mod.lucky.drop.DropProperties;
import mod.lucky.drop.DropGroup;
import mod.lucky.drop.DropBase;
import mod.lucky.structure.Structure;
import mod.lucky.drop.DropContainer;
import java.util.ArrayList;

public class DropProcessor
{
    private ArrayList<DropContainer> drops;
    private ArrayList<Structure> structures;
    
    public DropProcessor() {
        this.drops = new ArrayList<DropContainer>();
        this.structures = new ArrayList<Structure>();
    }
    
    public void processDrop(final DropBase v-9, final DropProcessData v-8) {
        final DropBase initialize = /*EL:26*/v-9.initialize(v-8);
        /*SL:28*/if (initialize instanceof DropContainer) {
            this.processDrop(((DropContainer)initialize).getDrop(), v-8);
        }
        /*SL:29*/if (initialize instanceof DropGroup) {
            DropGroup a2;
            int a2;
            /*SL:32*/for (a2 = (DropGroup)initialize, a2 = 0; a2 < a2.getAmount(); ++a2) {
                /*SL:34*/this.processDrop(a2.getDrops().get(a2), v-8);
            }
        }
        /*SL:37*/if (initialize instanceof DropProperties) {
            final DropProperties dropProperties = /*EL:39*/(DropProperties)v-9;
            final DropProperties dropProperties2 = /*EL:40*/(DropProperties)initialize;
            final DropFunction dropFunction = /*EL:41*/DropFunction.getDropFunction(dropProperties2);
            /*SL:43*/if (dropFunction == null) {
                System.err.println("Lucky Block: Error processing drop type '" + dropProperties2.getPropertyString("type") + "'. Drop type does not exist.");
            }
            else {
                final int intValue = /*EL:46*/dropProperties2.getPropertyInt("amount");
                final boolean booleanValue = /*EL:47*/dropProperties2.getPropertyBoolean("reinitialize");
                final boolean booleanValue2 = /*EL:48*/dropProperties2.getPropertyBoolean("postDelayInit");
                final DropProcessData v0 = /*EL:50*/new DropProcessData(v-8.getBlock(), v-8.getWorld(), v-8.getPlayer(), v-8.getHarvestPos());
                /*SL:51*/v0.setDropProperties(dropProperties2);
                /*SL:53*/if (dropProperties2.hasProperty("delay")) {
                    /*SL:55*/if (booleanValue2) {
                        /*SL:57*/v0.setDropProperties(dropProperties);
                        /*SL:58*/Lucky.getInstance().getTickHandler().addDelayDrop(v0);
                    }
                    else/*SL:60*/ if (booleanValue) {
                        /*SL:62*/for (int v = 0; v < intValue; ++v) {
                            /*SL:64*/Lucky.getInstance().getTickHandler().addDelayDrop(v0);
                            /*SL:65*/if (v < intValue - 1) {
                                v0.setDropProperties(dropProperties.initialize(v0));
                            }
                        }
                    }
                    else {
                        /*SL:68*/Lucky.getInstance().getTickHandler().addDelayDrop(v0);
                    }
                    /*SL:69*/return;
                }
                /*SL:72*/for (int v = 0; v < intValue; ++v) {
                    /*SL:74*/dropFunction.process(v0);
                    /*SL:75*/if (booleanValue && v < intValue - 1) {
                        v0.setDropProperties(dropProperties.initialize(v0));
                    }
                }
            }
        }
    }
    
    public void processDelayDrop(final DropProcessData v-3) {
        final DropProperties dropProperties = /*EL:83*/v-3.getDropProperties();
        DropProperties dropProperties2 = /*EL:84*/v-3.getDropProperties();
        final DropFunction v0 = /*EL:85*/DropFunction.getDropFunction(dropProperties2);
        /*SL:86*/if (v0 == null) {
            System.err.println("Lucky Block: Error processing drop type '" + dropProperties2.getPropertyString("type") + "'. Drop type does not exist.");
        }
        else {
            final boolean v = /*EL:89*/dropProperties2.getPropertyBoolean("postDelayInit");
            /*SL:90*/if (v) {
                dropProperties2 = dropProperties.initialize(v-3);
            }
            final int v2 = /*EL:91*/dropProperties2.getPropertyInt("amount");
            final boolean v3 = /*EL:92*/dropProperties2.getPropertyBoolean("reinitialize");
            final DropProcessData v4 = /*EL:94*/new DropProcessData(v-3.getBlock(), v-3.getWorld(), v-3.getPlayer(), v-3.getHarvestPos());
            /*SL:95*/v4.setDropProperties(dropProperties2);
            /*SL:97*/for (int a1 = 0; a1 < v2; ++a1) {
                /*SL:99*/v0.process(v4);
                /*SL:100*/if (v && v3 && a1 < v2 - 1) {
                    v4.setDropProperties(dropProperties.initialize(v4));
                }
            }
        }
    }
    
    public void processRandomDrop(final DropProcessData a1, final int a2) {
        final DropContainer v1 = /*EL:107*/this.selectRandomDrop(this.drops, a2);
        System.out.println(/*EL:108*/"Chosen Lucky Block Drop: " + v1);
        /*SL:109*/this.processDrop(v1, a1);
    }
    
    public void processRandomDrop(final ArrayList<DropContainer> a1, final DropProcessData a2, final int a3) {
        final DropContainer v1 = /*EL:114*/this.selectRandomDrop(a1, a3);
        System.out.println(/*EL:115*/"Chosen Lucky Block Drop: " + v1);
        /*SL:116*/this.processDrop(v1, a2);
    }
    
    public DropContainer selectRandomDrop(final ArrayList<DropContainer> v-9, final int v-8) {
        int luck = /*EL:121*/0;
        int luck2 = /*EL:122*/0;
        /*SL:123*/for (int a1 = 0; a1 < v-9.size(); ++a1) {
            /*SL:125*/if (v-9.get(a1).getLuck() < luck) {
                luck = v-9.get(a1).getLuck();
            }
            /*SL:126*/if (v-9.get(a1).getLuck() > luck2) {
                luck2 = v-9.get(a1).getLuck();
            }
        }
        /*SL:128*/luck2 += luck * -1 + 1;
        final float n = /*EL:130*/1.0f / (1.0f - ((v-8 < 0) ? (v-8 * -1) : v-8) * 0.77f / 100.0f);
        float n2 = /*EL:132*/0.0f;
        final ArrayList<Float> v4 = /*EL:133*/new ArrayList<Float>();
        /*SL:134*/v4.add(0.0f);
        /*SL:135*/for (final DropContainer dropContainer : v-9) {
            final int a2 = /*EL:137*/dropContainer.getLuck() + luck * -1 + 1;
            float v1 = /*EL:138*/0.0f;
            /*SL:139*/if (v-8 >= 0) {
                v1 = (float)Math.pow(n, a2);
            }
            else {
                /*SL:140*/v1 = (float)Math.pow(n, luck2 + 1 - a2);
            }
            final float v2 = /*EL:141*/dropContainer.getChance() * v1 * 100.0f;
            /*SL:142*/n2 += v2;
            /*SL:143*/v4.add(n2);
        }
        final Random random = /*EL:146*/new Random();
        final float v5 = /*EL:147*/random.nextFloat() * n2;
        final DropContainer v3 = /*EL:148*/v-9.get(this.getDropIndexByWeight(v4, v5));
        /*SL:150*/return v3;
    }
    
    private int getDropIndexByWeight(final ArrayList<Float> v1, final float v2) {
        /*SL:155*/for (int a1 = 0; a1 < v1.size(); ++a1) {
            /*SL:157*/if (v2 >= v1.get(a1) && v2 < v1.get(a1 + 1)) {
                return a1;
            }
        }
        /*SL:159*/return 0;
    }
    
    public void registerDrop(final DropContainer a1) {
        /*SL:164*/this.drops.add(a1);
    }
    
    public ArrayList<DropContainer> getDrops() {
        /*SL:169*/return this.drops;
    }
    
    public ArrayList<Structure> getStructures() {
        /*SL:174*/return this.structures;
    }
    
    public Structure getStructure(final String v2) {
        /*SL:179*/for (final Structure a1 : this.structures) {
            /*SL:180*/if (v2.equals(a1.getId())) {
                return a1;
            }
        }
        /*SL:181*/return null;
    }
    
    public void addStructure(final Structure a1) {
        /*SL:186*/this.structures.add(a1);
    }
}
