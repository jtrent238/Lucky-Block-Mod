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
    
    public DropProcessor() {
        this.drops = new ArrayList<DropContainer>();
    }
    
    public void processDrop(final DropBase v-10, final DropProcessData v-9) {
        final DropBase initialize = /*EL:23*/v-10.initialize(v-9);
        /*SL:25*/if (initialize instanceof DropContainer) {
            this.processDrop(((DropContainer)initialize).getDrop(), v-9);
        }
        /*SL:26*/if (initialize instanceof DropGroup) {
            DropGroup a2;
            int a2;
            /*SL:29*/for (a2 = (DropGroup)initialize, a2 = 0; a2 < a2.getAmount(); ++a2) {
                /*SL:31*/this.processDrop(a2.getDrops().get(a2), v-9);
            }
        }
        /*SL:34*/if (initialize instanceof DropProperties) {
            final DropProperties dropProperties = /*EL:36*/(DropProperties)v-10;
            final DropProperties dropProperties2 = /*EL:37*/(DropProperties)initialize;
            final DropFunction dropFunction = /*EL:38*/DropFunction.getDropFunction(dropProperties2);
            /*SL:40*/if (dropFunction == null) {
                System.err.println("Lucky Block: Error processing drop type '" + dropProperties2.getPropertyString("type") + "'. Drop type does not exist.");
            }
            else {
                final int intValue = /*EL:43*/dropProperties2.getPropertyInt("amount");
                final boolean booleanValue = /*EL:44*/dropProperties2.getPropertyBoolean("reinitialize");
                final boolean booleanValue2 = /*EL:45*/dropProperties2.getPropertyBoolean("postDelayInit");
                final DropProcessData copy = /*EL:47*/v-9.copy();
                /*SL:48*/copy.setDropProperties(dropProperties2);
                /*SL:50*/if (dropProperties2.hasProperty("delay")) {
                    /*SL:52*/if (booleanValue) {
                        /*SL:54*/for (int v0 = 0; v0 < intValue; ++v0) {
                            final float v = /*EL:56*/copy.getDropProperties().getPropertyFloat("delay");
                            /*SL:57*/if (booleanValue2) {
                                /*SL:59*/copy.setDropProperties(dropProperties);
                                /*SL:60*/Lucky.getInstance().getTickHandler().addDelayDrop(this, copy.copy(), v);
                            }
                            else {
                                /*SL:62*/Lucky.getInstance().getTickHandler().addDelayDrop(this, copy, v);
                            }
                            /*SL:63*/if (v0 < intValue - 1) {
                                copy.setDropProperties(dropProperties.initialize(copy));
                            }
                        }
                    }
                    else {
                        /*SL:68*/if (booleanValue2) {
                            copy.setDropProperties(dropProperties);
                        }
                        /*SL:69*/Lucky.getInstance().getTickHandler().addDelayDrop(this, copy, copy.getDropProperties().getPropertyFloat("delay"));
                    }
                    /*SL:71*/return;
                }
                /*SL:74*/for (int v0 = 0; v0 < intValue; ++v0) {
                    /*SL:76*/dropFunction.process(copy);
                    /*SL:77*/if (booleanValue && v0 < intValue - 1) {
                        copy.setDropProperties(dropProperties.initialize(copy));
                    }
                }
            }
        }
    }
    
    public void processDelayDrop(final DropProcessData v-3) {
        final DropProperties dropProperties = /*EL:85*/v-3.getDropProperties();
        DropProperties dropProperties2 = /*EL:86*/v-3.getDropProperties();
        final DropFunction v0 = /*EL:87*/DropFunction.getDropFunction(dropProperties2);
        /*SL:88*/if (v0 == null) {
            System.err.println("Lucky Block: Error processing drop type '" + dropProperties2.getPropertyString("type") + "'. Drop type does not exist.");
        }
        else {
            final boolean v = /*EL:91*/dropProperties2.getPropertyBoolean("postDelayInit");
            /*SL:92*/if (v) {
                dropProperties2 = dropProperties.initialize(v-3);
            }
            final int v2 = /*EL:93*/dropProperties2.getPropertyInt("amount");
            final boolean v3 = /*EL:94*/dropProperties2.getPropertyBoolean("reinitialize");
            final DropProcessData v4 = /*EL:96*/v-3.copy();
            /*SL:97*/v4.setDropProperties(dropProperties2);
            /*SL:99*/if (v3) {
                v0.process(v4);
            }
            else {
                /*SL:102*/for (int a1 = 0; a1 < v2; ++a1) {
                    /*SL:103*/v0.process(v4);
                }
            }
        }
    }
    
    public void processRandomDrop(final DropProcessData a1, final int a2) {
        /*SL:110*/this.processRandomDrop(a1, a2, true);
    }
    
    public void processRandomDrop(final DropProcessData a1, final int a2, final boolean a3) {
        final DropContainer v1 = /*EL:115*/this.selectRandomDrop(this.drops, a2);
        /*SL:116*/if (v1 == null) {
            return;
        }
        /*SL:117*/if (a3) {
            System.out.println("Chosen Lucky Block Drop: " + v1);
        }
        /*SL:118*/this.processDrop(v1, a1);
    }
    
    public void processRandomDrop(final ArrayList<DropContainer> a1, final DropProcessData a2, final int a3) {
        /*SL:123*/this.processRandomDrop(a1, a2, a3, true);
    }
    
    public void processRandomDrop(final ArrayList<DropContainer> a1, final DropProcessData a2, final int a3, final boolean a4) {
        final DropContainer v1 = /*EL:128*/this.selectRandomDrop(a1, a3);
        /*SL:129*/if (v1 == null) {
            return;
        }
        /*SL:130*/if (a4) {
            System.out.println("Chosen Lucky Block Drop: " + v1);
        }
        /*SL:131*/this.processDrop(v1, a2);
    }
    
    public DropContainer selectRandomDrop(final ArrayList<DropContainer> v-9, final int v-8) {
        /*SL:136*/if (v-9.size() == 0) {
            return null;
        }
        int luck = /*EL:138*/0;
        int luck2 = /*EL:139*/0;
        /*SL:140*/for (int a1 = 0; a1 < v-9.size(); ++a1) {
            /*SL:142*/if (v-9.get(a1).getLuck() < luck) {
                luck = v-9.get(a1).getLuck();
            }
            /*SL:143*/if (v-9.get(a1).getLuck() > luck2) {
                luck2 = v-9.get(a1).getLuck();
            }
        }
        /*SL:145*/luck2 += luck * -1 + 1;
        final float n = /*EL:147*/1.0f / (1.0f - ((v-8 < 0) ? (v-8 * -1) : v-8) * 0.77f / 100.0f);
        float n2 = /*EL:149*/0.0f;
        final ArrayList<Float> v4 = /*EL:150*/new ArrayList<Float>();
        /*SL:151*/v4.add(0.0f);
        /*SL:152*/for (final DropContainer dropContainer : v-9) {
            final int a2 = /*EL:154*/dropContainer.getLuck() + luck * -1 + 1;
            float v1 = /*EL:155*/0.0f;
            /*SL:156*/if (v-8 >= 0) {
                v1 = (float)Math.pow(n, a2);
            }
            else {
                /*SL:157*/v1 = (float)Math.pow(n, luck2 + 1 - a2);
            }
            final float v2 = /*EL:158*/dropContainer.getChance() * v1 * 100.0f;
            /*SL:159*/n2 += v2;
            /*SL:160*/v4.add(n2);
        }
        final Random random = /*EL:163*/new Random();
        final float v5 = /*EL:164*/random.nextFloat() * n2;
        final DropContainer v3 = /*EL:165*/v-9.get(this.getDropIndexByWeight(v4, v5));
        /*SL:167*/return v3;
    }
    
    private int getDropIndexByWeight(final ArrayList<Float> v1, final float v2) {
        /*SL:172*/for (int a1 = 0; a1 < v1.size(); ++a1) {
            /*SL:174*/if (v2 >= v1.get(a1) && v2 < v1.get(a1 + 1)) {
                return a1;
            }
        }
        /*SL:176*/return 0;
    }
    
    public void registerDrop(final DropContainer a1) {
        /*SL:181*/this.drops.add(a1);
    }
    
    public ArrayList<DropContainer> getDrops() {
        /*SL:186*/return this.drops;
    }
}
