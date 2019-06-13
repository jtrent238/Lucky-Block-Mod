package mod.lucky.resources;

import mod.lucky.drop.value.ValueParser;
import mod.lucky.resources.loader.BaseLoader;
import mod.lucky.util.LuckyReader;

public class ResourceProperties implements IBaseResource
{
    @Override
    public void process(final LuckyReader v-1, final BaseLoader v0) {
        try {
            String v;
            /*SL:15*/while ((v = v-1.readLine()) != null) {
                final String a1 = /*EL:17*/v.substring(0, v.indexOf(61));
                final String a2 = /*EL:18*/v.substring(v.indexOf(61) + 1, v.length());
                /*SL:20*/if (a1.equals("doDropsOnCreativeMode")) {
                    v0.getBlock().setCrativeModeDrops(ValueParser.getBoolean(a2));
                }
            }
        }
        catch (Exception v2) {
            System.err.println(/*EL:25*/"Lucky Block: Error reading 'properties.txt'");
            /*SL:26*/v2.printStackTrace();
        }
    }
    
    @Override
    public String getDirectory() {
        /*SL:33*/return "properties.txt";
    }
}
