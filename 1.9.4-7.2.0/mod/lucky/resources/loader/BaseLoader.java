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
            final InputStream a1 = /*EL:26*/this.getResourceStream(v-1);
            /*SL:27*/if (a1 == null) {
                return;
            }
            final LuckyReader v1 = /*EL:28*/new LuckyReader(new InputStreamReader(a1));
            /*SL:29*/v-1.process(v1, this);
        }
        catch (Exception v2) {
            System.err.println(/*EL:33*/"Lucky Block: Error loading resource: " + v-1.getDirectory());
            /*SL:34*/v2.printStackTrace();
        }
    }
    
    public BlockLuckyBlock getBlock() {
        /*SL:40*/return this.lucky_block;
    }
    
    public ItemLuckySword getSword() {
        /*SL:45*/return this.lucky_sword;
    }
    
    public ItemLuckyBow getBow() {
        /*SL:50*/return this.lucky_bow;
    }
    
    public ItemLuckyPotion getPotion() {
        /*SL:55*/return this.lucky_potion;
    }
    
    public void setLuckyBlockItems(final BlockLuckyBlock a1, final ItemLuckySword a2, final ItemLuckyBow a3, final ItemLuckyPotion a4) {
        /*SL:60*/this.lucky_block = a1;
        /*SL:61*/this.lucky_sword = a2;
        /*SL:62*/this.lucky_bow = a3;
        /*SL:63*/this.lucky_potion = a4;
    }
}
