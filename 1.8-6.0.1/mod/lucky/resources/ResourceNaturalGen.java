package mod.lucky.resources;

import mod.lucky.drop.DropContainer;
import mod.lucky.resources.loader.BaseLoader;
import mod.lucky.util.LuckyReader;

public class ResourceNaturalGen implements IBaseResource
{
    @Override
    public void process(final LuckyReader v-2, final BaseLoader v-1) {
        try {
            String a2 = /*EL:14*/"";
            String v1;
            /*SL:16*/while ((v1 = v-2.readLine()) != null) {
                /*SL:18*/if (v1.startsWith(">")) {
                    /*SL:20*/a2 = v1;
                }
                else {
                    /*SL:24*/a2 = new DropContainer();
                    /*SL:25*/a2.readFromString(v1);
                    /*SL:26*/if (a2.equals(">surface")) {
                        v-1.getBlock().getWorldGenerator().addSurfacedDrop(a2);
                    }
                    /*SL:27*/if (a2.equals(">nether")) {
                        v-1.getBlock().getWorldGenerator().addNetherDrop(a2);
                    }
                    /*SL:28*/if (!a2.equals(">end")) {
                        continue;
                    }
                    v-1.getBlock().getWorldGenerator().addEndDrop(a2);
                }
            }
        }
        catch (Exception v2) {
            System.err.println(/*EL:33*/"Lucky Block: Error reading 'drops.txt'");
            /*SL:34*/v2.printStackTrace();
        }
    }
    
    @Override
    public String getDirectory() {
        /*SL:41*/return "natural_gen.txt";
    }
}
