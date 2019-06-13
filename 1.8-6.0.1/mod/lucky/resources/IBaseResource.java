package mod.lucky.resources;

import mod.lucky.resources.loader.BaseLoader;
import mod.lucky.util.LuckyReader;

public interface IBaseResource
{
    void process(LuckyReader p0, BaseLoader p1);
    
    String getDirectory();
}
