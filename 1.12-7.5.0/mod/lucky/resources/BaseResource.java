package mod.lucky.resources;

import mod.lucky.resources.loader.BaseLoader;
import mod.lucky.util.LuckyReader;

public abstract class BaseResource
{
    public abstract void process(final LuckyReader p0, final BaseLoader p1);
    
    public abstract String getDirectory();
    
    public boolean isOptional() {
        /*SL:14*/return false;
    }
    
    public boolean postInit() {
        /*SL:17*/return false;
    }
}
