package mod.lucky.resources;

import mod.lucky.resources.loader.BaseLoader;
import mod.lucky.util.LuckyReader;

public class ResourceStructureFile extends BaseResource
{
    private String name;
    
    public ResourceStructureFile(final String a1) {
        this.name = a1;
    }
    
    @Override
    public void process(final LuckyReader a1, final BaseLoader a2) {
    }
    
    @Override
    public String getDirectory() {
        /*SL:24*/return "structures/" + this.name;
    }
}
