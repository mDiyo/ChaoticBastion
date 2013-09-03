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
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BannerItem extends ItemBlock
{
    public BannerItem(int par1)
    {
        super(par1);
        this.maxStackSize = 1;
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (side == 0)
        {
            return false;
        }
        else if (!world.getBlockMaterial(x, y, z).isSolid())
        {
            return false;
        }
        else
        {
            if (side == 1)
            {
                ++y;
            }

            if (side == 2)
            {
                --z;
            }

            if (side == 3)
            {
                ++z;
            }

            if (side == 4)
            {
                --x;
            }

            if (side == 5)
            {
                ++x;
            }

            if (!player.canPlayerEdit(x, y, z, side, stack))
            {
                return false;
            }
            else if (!Block.signPost.canPlaceBlockAt(world, x, y, z))
            {
                return false;
            }
            else if (world.isRemote)
            {
                return true;
            }
            else
            {
                //if (par7 == 1)
                //{
                    int i1 = MathHelper.floor_double((double)((player.rotationYaw + 180.0F) * 16.0F / 360.0F) + 0.5D) & 15;
                    world.setBlock(x, y, z, CContent.banner.blockID, i1, 3);
                /*}
                else
                {
                    par3World.setBlock(par4, par5, par6, CContent.banner.blockID, par7, 3);
                }*/

                --stack.stackSize;
                BannerLogic logic = (BannerLogic) world.getBlockTileEntity(x, y, z);
                logic.setEffect(Potion.digSpeed.id);
                
                /*@Override
                public void onBlockPlacedBy (World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack stack)
                {
                    System.out.println("Placed");
                    BannerLogic logic = (BannerLogic) world.getBlockTileEntity(x, y, z);
                    logic.setEffect(Potion.digSpeed.id);
                }*/
                /*TileEntitySign tileentitysign = (TileEntitySign)par3World.getBlockTileEntity(par4, par5, par6);

                if (tileentitysign != null)
                {
                    par2EntityPlayer.displayGUIEditSign(tileentitysign);
                }*/

                return true;
            }
        }
    }
}
