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
            /*SL:16*/while ((v = v-1.readLine()) != null) {
                final Structure a1 = /*EL:18*/new Structure();
                /*SL:19*/a1.readProperties(v, v0);
                final Structure a2 = /*EL:21*/a1.newTypeInstance();
                /*SL:22*/a2.readFromFile();
                /*SL:24*/Lucky.addStructure(a2);
            }
        }
        catch (Exception v2) {
            System.err.println(/*EL:29*/"Lucky Block: Error reading 'structures.txt'");
            /*SL:30*/v2.printStackTrace();
        }
    }
    
    @Override
    public String getDirectory() {
        /*SL:37*/return "structures.txt";
    }
}
