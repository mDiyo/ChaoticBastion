package bastion.util;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

public interface IItemSucker
{
    public void addWorldItems(EntityItem target, ItemStack inventory);
}
