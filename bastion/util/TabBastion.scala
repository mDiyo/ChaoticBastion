package bastion.util

import net.minecraft.item.ItemStack
import net.minecraft.creativetab.CreativeTabs

class TabBastion extends CreativeTabs("ChaoticBastion") {
    var display: ItemStack

    def init(stack: ItemStack) {
        display = stack
    }

    @Override
    def getIconItemStack() = display
}