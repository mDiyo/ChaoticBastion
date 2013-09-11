package bastion.items.block;

import bastion.CContent;
import bastion.blocks.logic.BannerLogic;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class HerbItem extends ItemBlock
{
    public HerbItem(int par1)
    {
        super(par1);
        this.maxStackSize = 1;
    }
    
    public Icon getIconFromDamage(int par1)
    {
        return this.field_94588_b != null ? this.field_94588_b : Block.blocksList[this.blockID].getIcon(1, 3);
    }
}
