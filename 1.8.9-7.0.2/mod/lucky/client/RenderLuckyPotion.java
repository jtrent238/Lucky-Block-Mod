package mod.lucky.client;

import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import mod.lucky.entity.EntityLuckyPotion;
import net.minecraft.item.Item;
import mod.lucky.Lucky;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderSnowball;

@SideOnly(Side.CLIENT)
public class RenderLuckyPotion extends RenderSnowball
{
    private RenderItem renderItem;
    
    public RenderLuckyPotion(final RenderManager a1, final RenderItem a2) {
        super(a1, (Item)Lucky.lucky_potion, a2);
        this.renderItem = a2;
    }
    
    public ItemStack getItemStack(final EntityLuckyPotion a1) {
        ItemStack v1 = /*EL:27*/a1.getItemLuckyPotion();
        /*SL:28*/if (v1 == null) {
            v1 = new ItemStack(Items.field_151055_y, 1);
        }
        /*SL:29*/return v1;
    }
    
    public ItemStack func_177082_d(final Entity a1) {
        /*SL:35*/return this.getItemStack((EntityLuckyPotion)a1);
    }
}
