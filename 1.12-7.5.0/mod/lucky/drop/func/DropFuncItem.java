package mod.lucky.drop.func;

import mod.lucky.drop.DropProperties;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;

public class DropFuncItem extends DropFunction
{
    @Override
    public void process(final DropProcessData a1) {
        final DropProperties v1 = /*EL:13*/a1.getDropProperties();
        final Item v2 = /*EL:14*/Item.func_111206_d(v1.getPropertyString("ID"));
        final ItemStack v3 = /*EL:17*/new ItemStack(v2, 1, (int)v1.getPropertyInt("damage"));
        /*SL:18*/v3.func_77982_d(v1.getPropertyNBT("NBTTag"));
        /*SL:20*/Block.func_180635_a(a1.getWorld(), v1.getBlockPos(), v3);
    }
    
    @Override
    public String getType() {
        /*SL:26*/return "item";
    }
}
