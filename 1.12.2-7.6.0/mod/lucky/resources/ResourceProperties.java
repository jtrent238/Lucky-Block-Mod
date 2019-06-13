package mod.lucky.resources;

import mod.lucky.drop.value.ValueParser;
import mod.lucky.resources.loader.BaseLoader;
import mod.lucky.util.LuckyReader;

public class ResourceProperties extends BaseResource
{
    @Override
    public void process(final LuckyReader v-1, final BaseLoader v0) {
        try {
            String v;
            /*SL:12*/while ((v = v-1.readLine()) != null) {
                final String a1 = /*EL:13*/v.substring(0, v.indexOf(61));
                final String a2 = /*EL:14*/v.substring(v.indexOf(61) + 1, v.length());
                /*SL:16*/if (a1.equals("doDropsOnCreativeMode")) {
                    /*SL:17*/v0.getBlock().setCreativeModeDrops(ValueParser.getBoolean(a2));
                }
            }
        }
        catch (Exception v2) {
            System.err.println(/*EL:20*/"Lucky Block: Error reading 'properties.txt'");
            /*SL:21*/v2.printStackTrace();
        }
    }
    
    @Override
    public String getDirectory() {
        /*SL:27*/return "properties.txt";
    }
}
