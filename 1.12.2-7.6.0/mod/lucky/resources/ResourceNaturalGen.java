package mod.lucky.resources;

import mod.lucky.drop.DropContainer;
import mod.lucky.resources.loader.BaseLoader;
import mod.lucky.util.LuckyReader;

public class ResourceNaturalGen extends BaseResource
{
    @Override
    public void process(final LuckyReader v-2, final BaseLoader v-1) {
        try {
            String a2 = /*EL:11*/"";
            String v1;
            /*SL:13*/while ((v1 = v-2.readLine()) != null) {
                /*SL:14*/if (v1.startsWith(">")) {
                    /*SL:15*/a2 = v1;
                }
                else {
                    /*SL:19*/a2 = new DropContainer();
                    /*SL:20*/a2.readFromString(v1);
                    /*SL:21*/if (a2.equals(">surface")) {
                        v-1.getBlock().getWorldGenerator().addSurfacedDrop(a2);
                    }
                    /*SL:22*/if (a2.equals(">nether")) {
                        v-1.getBlock().getWorldGenerator().addNetherDrop(a2);
                    }
                    /*SL:23*/if (!a2.equals(">end")) {
                        continue;
                    }
                    v-1.getBlock().getWorldGenerator().addEndDrop(a2);
                }
            }
        }
        catch (Exception v2) {
            System.err.println(/*EL:26*/"Lucky Block: Error reading 'drops.txt'");
            /*SL:27*/v2.printStackTrace();
        }
    }
    
    @Override
    public String getDirectory() {
        /*SL:33*/return "natural_gen.txt";
    }
}
