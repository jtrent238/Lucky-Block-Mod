package mod.lucky.resources;

import mod.lucky.resources.loader.BaseLoader;
import mod.lucky.util.LuckyReader;

public class ResourceTexture implements IBaseResource
{
    @Override
    public void process(final LuckyReader a1, final BaseLoader a2) {
    }
    
    @Override
    public String getDirectory() {
        /*SL:16*/return "assets/lucky/textures/blocks/lucky_block.png";
    }
}
