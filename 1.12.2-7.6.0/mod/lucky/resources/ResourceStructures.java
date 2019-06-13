package mod.lucky.resources;

import mod.lucky.Lucky;
import mod.lucky.structure.Structure;
import mod.lucky.resources.loader.BaseLoader;
import mod.lucky.util.LuckyReader;

public class ResourceStructures extends BaseResource
{
    @Override
    public void process(final LuckyReader v-1, final BaseLoader v0) {
        try {
            String v;
            /*SL:13*/while ((v = v-1.readLine()) != null) {
                final Structure a1 = /*EL:14*/new Structure();
                /*SL:15*/a1.readProperties(v, v0);
                final Structure a2 = /*EL:17*/a1.newTypeInstance();
                /*SL:18*/a2.readFromFile();
                /*SL:20*/Lucky.addStructure(a2);
            }
        }
        catch (Exception v2) {
            System.err.println(/*EL:23*/"Lucky Block: Error reading 'structures.txt'");
            /*SL:24*/v2.printStackTrace();
        }
    }
    
    @Override
    public String getDirectory() {
        /*SL:30*/return "structures.txt";
    }
}
