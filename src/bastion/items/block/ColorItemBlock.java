package bastion.items.block;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import bastion.blocks.ColorBlock;

public class ColorItemBlock extends ItemBlock
{
    public ColorItemBlock(int id)
    {
        super(id);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata (int meta)
    {
        return meta;
    }
    
    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        return super.getUnlocalizedName() + "." + ColorBlock.colorNames[par1ItemStack.getItemDamage()];
    }
}
