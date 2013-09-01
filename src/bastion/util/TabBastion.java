package bastion.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabBastion extends CreativeTabs
{
    static ItemStack display;

    public TabBastion()
    {
        super("ChaoticBastion");
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