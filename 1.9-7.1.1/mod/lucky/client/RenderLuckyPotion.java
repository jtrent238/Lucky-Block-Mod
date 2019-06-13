package mod.lucky.client;

import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import mod.lucky.Lucky;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import mod.lucky.entity.EntityLuckyPotion;
import net.minecraft.client.renderer.entity.RenderSnowball;

@SideOnly(Side.CLIENT)
public class RenderLuckyPotion extends RenderSnowball<EntityLuckyPotion>
{
    private RenderItem renderItem;
    
    public RenderLuckyPotion(final RenderManager a1, final RenderItem a2) {
        super(a1, (Item)Lucky.lucky_potion, a2);
        this.renderItem = a2;
    }
    
    public ItemStack getItemStack(final EntityLuckyPotion a1) {
        ItemStack v1 = /*EL:26*/a1.getItemLuckyPotion();
        /*SL:27*/if (v1 == null) {
            v1 = new ItemStack(Items.field_151055_y, 1);
        }
        /*SL:28*/return v1;
    }
    
    public ItemStack getPotion(final EntityLuckyPotion a1) {
        /*SL:34*/return this.getItemStack(a1);
    }
}
