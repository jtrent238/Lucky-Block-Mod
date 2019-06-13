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
        final DropBase initialize = /*EL:22*/v-10.initialize(v-9);
        /*SL:24*/if (initialize instanceof DropContainer) {
            /*SL:25*/this.processDrop(((DropContainer)initialize).getDrop(), v-9);
        }
        /*SL:26*/if (initialize instanceof DropGroup) {
            DropGroup a2;
            int a2;
            /*SL:28*/for (a2 = (DropGroup)initialize, a2 = 0; a2 < a2.getAmount(); ++a2) {
                /*SL:29*/this.processDrop(a2.getDrops().get(a2), v-9);
            }
        }
        /*SL:32*/if (initialize instanceof DropProperties) {
            final DropProperties dropProperties = /*EL:33*/(DropProperties)v-10;
            final DropProperties dropProperties2 = /*EL:34*/(DropProperties)initialize;
            final DropFunction dropFunction = /*EL:35*/DropFunction.getDropFunction(dropProperties2);
            /*SL:37*/if (dropFunction == null) {
                System.err.println(/*EL:38*/"Lucky Block: Error processing drop type '" + dropProperties2.getPropertyString("type") + /*EL:40*/"'. Drop type does not exist.");
            }
            else {
                final int intValue = /*EL:43*/dropProperties2.getPropertyInt("amount");
                final boolean booleanValue = /*EL:44*/dropProperties2.getPropertyBoolean("reinitialize");
                final boolean booleanValue2 = /*EL:45*/dropProperties2.getPropertyBoolean("postDelayInit");
                final DropProcessData copy = /*EL:47*/v-9.copy();
                /*SL:48*/copy.setDropProperties(dropProperties2);
                /*SL:50*/if (dropProperties2.hasProperty("delay")) {
                    /*SL:51*/if (booleanValue) {
                        /*SL:52*/for (int v0 = 0; v0 < intValue; ++v0) {
                            final float v = /*EL:53*/copy.getDropProperties().getPropertyFloat("delay");
                            /*SL:54*/if (booleanValue2) {
                                /*SL:55*/copy.setDropProperties(dropProperties);
                                /*SL:56*/Lucky.getInstance().getTickHandler().addDelayDrop(this, copy.copy(), v);
                            }
                            else {
                                /*SL:57*/Lucky.getInstance().getTickHandler().addDelayDrop(this, copy, v);
                            }
                            /*SL:58*/if (v0 < intValue - 1) {
                                copy.setDropProperties(dropProperties.initialize(copy));
                            }
                        }
                    }
                    else {
                        /*SL:61*/if (booleanValue2) {
                            copy.setDropProperties(dropProperties);
                        }
                        /*SL:62*/Lucky.getInstance().getTickHandler().addDelayDrop(/*EL:63*/this, copy, copy.getDropProperties().getPropertyFloat(/*EL:65*/"delay"));
                    }
                    /*SL:67*/return;
                }
                /*SL:70*/for (int v0 = 0; v0 < intValue; ++v0) {
                    /*SL:71*/dropFunction.process(copy);
                    /*SL:72*/if (booleanValue && v0 < intValue - 1) {
                        /*SL:73*/copy.setDropProperties(dropProperties.initialize(copy));
                    }
                }
            }
        }
    }
    
    public void processDelayDrop(final DropProcessData v-3) {
        final DropProperties dropProperties = /*EL:80*/v-3.getDropProperties();
        DropProperties dropProperties2 = /*EL:81*/v-3.getDropProperties();
        final DropFunction v0 = /*EL:82*/DropFunction.getDropFunction(dropProperties2);
        /*SL:83*/if (v0 == null) {
            System.err.println(/*EL:84*/"Lucky Block: Error processing drop type '" + dropProperties2.getPropertyString("type") + /*EL:86*/"'. Drop type does not exist.");
        }
        else {
            final boolean v = /*EL:89*/dropProperties2.getPropertyBoolean("postDelayInit");
            /*SL:90*/if (v) {
                dropProperties2 = dropProperties.initialize(v-3);
            }
            final int v2 = /*EL:91*/dropProperties2.getPropertyInt("amount");
            final boolean v3 = /*EL:92*/dropProperties2.getPropertyBoolean("reinitialize");
            final DropProcessData v4 = /*EL:94*/v-3.copy();
            /*SL:95*/v4.setDropProperties(dropProperties2);
            /*SL:97*/if (v3) {
                v0.process(v4);
            }
            else {
                /*SL:99*/for (int a1 = 0; a1 < v2; ++a1) {
                    v0.process(v4);
                }
            }
        }
    }
    
    public void processRandomDrop(final DropProcessData a1, final int a2) {
        /*SL:105*/this.processRandomDrop(a1, a2, true);
    }
    
    public void processRandomDrop(final DropProcessData a1, final int a2, final boolean a3) {
        /*SL:109*/this.processRandomDrop(a1, a2, true, false);
    }
    
    public void processRandomDrop(final DropProcessData a1, final int a2, final boolean a3, final boolean a4) {
        DropContainer v1 = /*EL:114*/this.selectRandomDrop(this.drops, a2);
        /*SL:115*/if (a4) {
            /*SL:116*/if (this.debugDropIndex >= this.drops.size() || this.debugDropIndex > this.debugIndexMax) {
                /*SL:117*/this.debugDropIndex = this.debugIndexMin;
            }
            /*SL:118*/v1 = this.drops.get(this.debugDropIndex);
            /*SL:119*/++this.debugDropIndex;
        }
        /*SL:121*/if (v1 == null) {
            return;
        }
        /*SL:122*/if (a3) {
            System.out.println("Chosen Lucky Block Drop: " + v1);
        }
        /*SL:123*/this.processDrop(v1, a1);
    }
    
    public void processRandomDrop(final ArrayList<DropContainer> a1, final DropProcessData a2, final int a3) {
        /*SL:128*/this.processRandomDrop(a1, a2, a3, true);
    }
    
    public void processRandomDrop(final ArrayList<DropContainer> a1, final DropProcessData a2, final int a3, final boolean a4) {
        final DropContainer v1 = /*EL:133*/this.selectRandomDrop(a1, a3);
        /*SL:134*/if (v1 == null) {
            return;
        }
        /*SL:135*/if (a4) {
            System.out.println("Chosen Lucky Block Drop: " + v1);
        }
        /*SL:136*/this.processDrop(v1, a2);
    }
    
    public DropContainer selectRandomDrop(final ArrayList<DropContainer> v-9, final int v-8) {
        /*SL:140*/if (v-9.size() == 0) {
            return null;
        }
        int luck = /*EL:142*/0;
        int luck2 = /*EL:143*/0;
        /*SL:144*/for (int a1 = 0; a1 < v-9.size(); ++a1) {
            /*SL:145*/if (v-9.get(a1).getLuck() < luck) {
                luck = v-9.get(a1).getLuck();
            }
            /*SL:146*/if (v-9.get(a1).getLuck() > luck2) {
                luck2 = v-9.get(a1).getLuck();
            }
        }
        /*SL:148*/luck2 += luck * -1 + 1;
        final float n = /*EL:150*/1.0f / (1.0f - ((v-8 < 0) ? (v-8 * -1) : v-8) * 0.77f / 100.0f);
        float n2 = /*EL:152*/0.0f;
        final ArrayList<Float> v4 = /*EL:153*/new ArrayList<Float>();
        /*SL:154*/v4.add(0.0f);
        /*SL:155*/for (final DropContainer dropContainer : v-9) {
            final int a2 = /*EL:156*/dropContainer.getLuck() + luck * -1 + 1;
            float v1 = /*EL:157*/0.0f;
            /*SL:158*/if (v-8 >= 0) {
                v1 = (float)Math.pow(n, a2);
            }
            else {
                /*SL:159*/v1 = (float)Math.pow(n, luck2 + 1 - a2);
            }
            final float v2 = /*EL:160*/dropContainer.getChance() * v1 * 100.0f;
            /*SL:161*/n2 += v2;
            /*SL:162*/v4.add(n2);
        }
        final Random random = /*EL:165*/new Random();
        final float v5 = /*EL:166*/random.nextFloat() * n2;
        final DropContainer v3 = /*EL:167*/v-9.get(this.getDropIndexByWeight(v4, v5));
        /*SL:169*/return v3;
    }
    
    private int getDropIndexByWeight(final ArrayList<Float> v1, final float v2) {
        /*SL:173*/for (int a1 = 0; a1 < v1.size(); ++a1) {
            /*SL:174*/if (v2 >= v1.get(a1) && v2 < v1.get(a1 + 1)) {
                return a1;
            }
        }
        /*SL:176*/return 0;
    }
    
    public void registerDrop(final DropContainer a1) {
        /*SL:180*/this.drops.add(a1);
    }
    
    public ArrayList<DropContainer> getDrops() {
        /*SL:184*/return this.drops;
    }
}
