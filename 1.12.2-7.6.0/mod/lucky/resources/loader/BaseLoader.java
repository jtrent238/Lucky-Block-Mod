package mod.lucky.resources.loader;

import mod.lucky.util.LuckyReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import mod.lucky.resources.BaseResource;
import mod.lucky.item.ItemLuckyPotion;
import mod.lucky.item.ItemLuckyBow;
import mod.lucky.item.ItemLuckySword;
import mod.lucky.block.BlockLuckyBlock;

public abstract class BaseLoader
{
    private BlockLuckyBlock lucky_block;
    private ItemLuckySword lucky_sword;
    private ItemLuckyBow lucky_bow;
    private ItemLuckyPotion lucky_potion;
    
    public abstract InputStream getResourceStream(final BaseResource p0);
    
    public void loadResource(final BaseResource v-1) {
        try {
            final InputStream a1 = /*EL:22*/this.getResourceStream(v-1);
            /*SL:23*/if (a1 == null) {
                return;
            }
            final LuckyReader v1 = /*EL:24*/new LuckyReader(new InputStreamReader(a1));
            /*SL:25*/v-1.process(v1, this);
        }
        catch (Exception v2) {
            System.err.println(/*EL:27*/"Lucky Block: Error loading resource: " + v-1.getDirectory());
            /*SL:28*/v2.printStackTrace();
        }
    }
    
    public BlockLuckyBlock getBlock() {
        /*SL:33*/return this.lucky_block;
    }
    
    public ItemLuckySword getSword() {
        /*SL:37*/return this.lucky_sword;
    }
    
    public ItemLuckyBow getBow() {
        /*SL:41*/return this.lucky_bow;
    }
    
    public ItemLuckyPotion getPotion() {
        /*SL:45*/return this.lucky_potion;
    }
    
    public void setLuckyBlockItems(final BlockLuckyBlock a1, final ItemLuckySword a2, final ItemLuckyBow a3, final ItemLuckyPotion a4) {
        /*SL:53*/this.lucky_block = a1;
        /*SL:54*/this.lucky_sword = a2;
        /*SL:55*/this.lucky_bow = a3;
        /*SL:56*/this.lucky_potion = a4;
    }
}
