package mod.lucky.resources;

import mod.lucky.drop.DropContainer;
import mod.lucky.resources.loader.BaseLoader;
import mod.lucky.util.LuckyReader;

public class ResourceDrops extends BaseResource
{
    @Override
    public void process(final LuckyReader v-1, final BaseLoader v0) {
        try {
            String a2;
            DropContainer a2;
            /*SL:12*/while ((a2 = v-1.readLine()) != null) {
                /*SL:13*/a2 = new DropContainer();
                /*SL:14*/a2.readFromString(a2);
                /*SL:15*/v0.getBlock().getDropProcessor().registerDrop(a2);
            }
        }
        catch (Exception v) {
            System.err.println(/*EL:18*/"Lucky Block: Error reading 'drops.txt'");
            /*SL:19*/v.printStackTrace();
        }
    }
    
    @Override
    public String getDirectory() {
        /*SL:25*/return "drops.txt";
    }
}
