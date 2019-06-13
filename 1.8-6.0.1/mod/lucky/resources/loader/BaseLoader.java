package mod.lucky.resources.loader;

import mod.lucky.util.LuckyReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import mod.lucky.resources.IBaseResource;
import mod.lucky.block.BlockLuckyBlock;

public abstract class BaseLoader
{
    protected BlockLuckyBlock lucky_block;
    
    public abstract InputStream getResourceStream(final IBaseResource p0);
    
    public void loadResource(final IBaseResource v-1) {
        try {
            final InputStream a1 = /*EL:20*/this.getResourceStream(v-1);
            /*SL:21*/if (a1 == null) {
                return;
            }
            final LuckyReader v1 = /*EL:22*/new LuckyReader(new InputStreamReader(a1));
            /*SL:23*/v-1.process(v1, this);
        }
        catch (Exception v2) {
            System.err.println(/*EL:27*/"Lucky Block: Error loading resource: " + v-1.getDirectory());
            /*SL:28*/v2.printStackTrace();
        }
    }
    
    public BlockLuckyBlock getBlock() {
        /*SL:34*/return this.lucky_block;
    }
    
    public void setLuckyBlock(final BlockLuckyBlock a1) {
        /*SL:39*/this.lucky_block = a1;
    }
}
