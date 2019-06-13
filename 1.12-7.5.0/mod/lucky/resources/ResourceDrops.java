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
            /*SL:15*/while ((a2 = v-1.readLine()) != null) {
                /*SL:17*/a2 = new DropContainer();
                /*SL:18*/a2.readFromString(a2);
                /*SL:19*/v0.getBlock().getDropProcessor().registerDrop(a2);
            }
        }
        catch (Exception v) {
            System.err.println(/*EL:24*/"Lucky Block: Error reading 'drops.txt'");
            /*SL:25*/v.printStackTrace();
        }
    }
    
    @Override
    public String getDirectory() {
        /*SL:32*/return "drops.txt";
    }
}
