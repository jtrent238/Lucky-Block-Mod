package mod.lucky.drop.func;

import java.util.Iterator;
import java.util.Random;
import mod.lucky.Lucky;
import mod.lucky.drop.DropProperties;
import mod.lucky.drop.DropGroup;
import mod.lucky.drop.DropBase;
import mod.lucky.drop.DropContainer;
import java.util.ArrayList;

public class DropProcessor
{
    private ArrayList<DropContainer> drops;
    private int debugDropIndex;
    private int debugIndexMin;
    private int debugIndexMax;
    
    public DropProcessor() {
        this.debugDropIndex = 0;
        this.debugIndexMin = 0;
        this.debugIndexMax = 1000;
        this.drops = new ArrayList<DropContainer>();
    }
    
    public void processDrop(final DropBase v-10, final DropProcessData v-9) {
        final DropBase initialize = /*EL:26*/v-10.initialize(v-9);
        /*SL:28*/if (initialize instanceof DropContainer) {
            this.processDrop(((DropContainer)initialize).getDrop(), v-9);
        }
        /*SL:29*/if (initialize instanceof DropGroup) {
            DropGroup a2;
            int a2;
            /*SL:32*/for (a2 = (DropGroup)initialize, a2 = 0; a2 < a2.getAmount(); ++a2) {
                /*SL:34*/this.processDrop(a2.getDrops().get(a2), v-9);
            }
        }
        /*SL:37*/if (initialize instanceof DropProperties) {
            final DropProperties dropProperties = /*EL:39*/(DropProperties)v-10;
            final DropProperties dropProperties2 = /*EL:40*/(DropProperties)initialize;
            final DropFunction dropFunction = /*EL:41*/DropFunction.getDropFunction(dropProperties2);
            /*SL:43*/if (dropFunction == null) {
                System.err.println("Lucky Block: Error processing drop type '" + dropProperties2.getPropertyString("type") + "'. Drop type does not exist.");
            }
            else {
                final int intValue = /*EL:46*/dropProperties2.getPropertyInt("amount");
                final boolean booleanValue = /*EL:47*/dropProperties2.getPropertyBoolean("reinitialize");
                final boolean booleanValue2 = /*EL:48*/dropProperties2.getPropertyBoolean("postDelayInit");
                final DropProcessData copy = /*EL:50*/v-9.copy();
                /*SL:51*/copy.setDropProperties(dropProperties2);
                /*SL:53*/if (dropProperties2.hasProperty("delay")) {
                    /*SL:55*/if (booleanValue) {
                        /*SL:57*/for (int v0 = 0; v0 < intValue; ++v0) {
                            final float v = /*EL:59*/copy.getDropProperties().getPropertyFloat("delay");
                            /*SL:60*/if (booleanValue2) {
                                /*SL:62*/copy.setDropProperties(dropProperties);
                                /*SL:63*/Lucky.getInstance().getTickHandler().addDelayDrop(this, copy.copy(), v);
                            }
                            else {
                                /*SL:65*/Lucky.getInstance().getTickHandler().addDelayDrop(this, copy, v);
                            }
                            /*SL:66*/if (v0 < intValue - 1) {
                                copy.setDropProperties(dropProperties.initialize(copy));
                            }
                        }
                    }
                    else {
                        /*SL:71*/if (booleanValue2) {
                            copy.setDropProperties(dropProperties);
                        }
                        /*SL:72*/Lucky.getInstance().getTickHandler().addDelayDrop(this, copy, copy.getDropProperties().getPropertyFloat("delay"));
                    }
                    /*SL:74*/return;
                }
                /*SL:77*/for (int v0 = 0; v0 < intValue; ++v0) {
                    /*SL:79*/dropFunction.process(copy);
                    /*SL:80*/if (booleanValue && v0 < intValue - 1) {
                        copy.setDropProperties(dropProperties.initialize(copy));
                    }
                }
            }
        }
    }
    
    public void processDelayDrop(final DropProcessData v-3) {
        final DropProperties dropProperties = /*EL:88*/v-3.getDropProperties();
        DropProperties dropProperties2 = /*EL:89*/v-3.getDropProperties();
        final DropFunction v0 = /*EL:90*/DropFunction.getDropFunction(dropProperties2);
        /*SL:91*/if (v0 == null) {
            System.err.println("Lucky Block: Error processing drop type '" + dropProperties2.getPropertyString("type") + "'. Drop type does not exist.");
        }
        else {
            final boolean v = /*EL:94*/dropProperties2.getPropertyBoolean("postDelayInit");
            /*SL:95*/if (v) {
                dropProperties2 = dropProperties.initialize(v-3);
            }
            final int v2 = /*EL:96*/dropProperties2.getPropertyInt("amount");
            final boolean v3 = /*EL:97*/dropProperties2.getPropertyBoolean("reinitialize");
            final DropProcessData v4 = /*EL:99*/v-3.copy();
            /*SL:100*/v4.setDropProperties(dropProperties2);
            /*SL:102*/if (v3) {
                v0.process(v4);
            }
            else {
                /*SL:105*/for (int a1 = 0; a1 < v2; ++a1) {
                    /*SL:106*/v0.process(v4);
                }
            }
        }
    }
    
    public void processRandomDrop(final DropProcessData a1, final int a2) {
        /*SL:113*/this.processRandomDrop(a1, a2, true);
    }
    
    public void processRandomDrop(final DropProcessData a1, final int a2, final boolean a3) {
        /*SL:118*/this.processRandomDrop(a1, a2, true, false);
    }
    
    public void processRandomDrop(final DropProcessData a1, final int a2, final boolean a3, final boolean a4) {
        DropContainer v1 = /*EL:123*/this.selectRandomDrop(this.drops, a2);
        /*SL:124*/if (a4) {
            /*SL:126*/if (this.debugDropIndex >= this.drops.size() || this.debugDropIndex > this.debugIndexMax) {
                this.debugDropIndex = this.debugIndexMin;
            }
            /*SL:127*/v1 = this.drops.get(this.debugDropIndex);
            /*SL:128*/++this.debugDropIndex;
        }
        /*SL:130*/if (v1 == null) {
            return;
        }
        /*SL:131*/if (a3) {
            System.out.println("Chosen Lucky Block Drop: " + v1);
        }
        /*SL:132*/this.processDrop(v1, a1);
    }
    
    public void processRandomDrop(final ArrayList<DropContainer> a1, final DropProcessData a2, final int a3) {
        /*SL:137*/this.processRandomDrop(a1, a2, a3, true);
    }
    
    public void processRandomDrop(final ArrayList<DropContainer> a1, final DropProcessData a2, final int a3, final boolean a4) {
        final DropContainer v1 = /*EL:142*/this.selectRandomDrop(a1, a3);
        /*SL:143*/if (v1 == null) {
            return;
        }
        /*SL:144*/if (a4) {
            System.out.println("Chosen Lucky Block Drop: " + v1);
        }
        /*SL:145*/this.processDrop(v1, a2);
    }
    
    public DropContainer selectRandomDrop(final ArrayList<DropContainer> v-9, final int v-8) {
        /*SL:150*/if (v-9.size() == 0) {
            return null;
        }
        int luck = /*EL:152*/0;
        int luck2 = /*EL:153*/0;
        /*SL:154*/for (int a1 = 0; a1 < v-9.size(); ++a1) {
            /*SL:156*/if (v-9.get(a1).getLuck() < luck) {
                luck = v-9.get(a1).getLuck();
            }
            /*SL:157*/if (v-9.get(a1).getLuck() > luck2) {
                luck2 = v-9.get(a1).getLuck();
            }
        }
        /*SL:159*/luck2 += luck * -1 + 1;
        final float n = /*EL:161*/1.0f / (1.0f - ((v-8 < 0) ? (v-8 * -1) : v-8) * 0.77f / 100.0f);
        float n2 = /*EL:163*/0.0f;
        final ArrayList<Float> v4 = /*EL:164*/new ArrayList<Float>();
        /*SL:165*/v4.add(0.0f);
        /*SL:166*/for (final DropContainer dropContainer : v-9) {
            final int a2 = /*EL:168*/dropContainer.getLuck() + luck * -1 + 1;
            float v1 = /*EL:169*/0.0f;
            /*SL:170*/if (v-8 >= 0) {
                v1 = (float)Math.pow(n, a2);
            }
            else {
                /*SL:171*/v1 = (float)Math.pow(n, luck2 + 1 - a2);
            }
            final float v2 = /*EL:172*/dropContainer.getChance() * v1 * 100.0f;
            /*SL:173*/n2 += v2;
            /*SL:174*/v4.add(n2);
        }
        final Random random = /*EL:177*/new Random();
        final float v5 = /*EL:178*/random.nextFloat() * n2;
        final DropContainer v3 = /*EL:179*/v-9.get(this.getDropIndexByWeight(v4, v5));
        /*SL:181*/return v3;
    }
    
    private int getDropIndexByWeight(final ArrayList<Float> v1, final float v2) {
        /*SL:186*/for (int a1 = 0; a1 < v1.size(); ++a1) {
            /*SL:188*/if (v2 >= v1.get(a1) && v2 < v1.get(a1 + 1)) {
                return a1;
            }
        }
        /*SL:190*/return 0;
    }
    
    public void registerDrop(final DropContainer a1) {
        /*SL:195*/this.drops.add(a1);
    }
    
    public ArrayList<DropContainer> getDrops() {
        /*SL:200*/return this.drops;
    }
}
