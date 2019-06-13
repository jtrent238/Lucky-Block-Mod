package mod.lucky.resources;

import mod.lucky.structure.Structure;
import mod.lucky.resources.loader.BaseLoader;
import mod.lucky.util.LuckyReader;

public class ResourceStructures implements IBaseResource
{
    @Override
    public void process(final LuckyReader v-1, final BaseLoader v0) {
        try {
            String v;
            /*SL:15*/while ((v = v-1.readLine()) != null) {
                final Structure a1 = /*EL:17*/new Structure();
                /*SL:18*/a1.readProperties(v, v0);
                final Structure a2 = /*EL:20*/a1.newTypeInstance();
                /*SL:21*/a2.readFromFile();
                /*SL:23*/v0.getBlock().getDropProcessor().addStructure(a2);
            }
        }
        catch (Exception v2) {
            System.err.println(/*EL:28*/"Lucky Block: Error reading 'structures.txt'");
            /*SL:29*/v2.printStackTrace();
        }
    }
    
    @Override
    public String getDirectory() {
        /*SL:36*/return "structures.txt";
    }
}
