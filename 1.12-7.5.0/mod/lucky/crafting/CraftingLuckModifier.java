package mod.lucky.crafting;

import net.minecraft.item.Item;

public class CraftingLuckModifier
{
    private Item item;
    private int damage;
    private int luckValue;
    
    public CraftingLuckModifier(final Item a1, final int a2, final int a3) {
        this.damage = -1;
        this.item = a1;
        this.damage = a2;
        this.luckValue = a3;
    }
    
    public Item getItem() {
        /*SL:20*/return this.item;
    }
    
    public int getDamage() {
        /*SL:25*/return this.damage;
    }
    
    public int getLuckValue() {
        /*SL:30*/return this.luckValue;
    }
}
