package bastion.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabBastion extends CreativeTabs
{
    ItemStack display;

    public TabBastion(String name)
    {
        super(name);
    }

    public TabBastion init (ItemStack stack)
    {
        display = stack;
        return this;
    }

    public ItemStack getIconItemStack ()
    {
        return display;
    }
}