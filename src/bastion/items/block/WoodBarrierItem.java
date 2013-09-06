package bastion.items.block;

import java.util.List;

import bastion.blocks.CrystalBlock;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class WoodBarrierItem extends ItemBlock
{
    private int bID;

    public WoodBarrierItem(int id)
    {
        super(id);
        setMaxDamage(0);
        this.bID = id + 256;
        //setHasSubtypes(true);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (player.isSneaking())
            return placeBlock(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
        else
        {

            boolean placed = placeBlock(stack, player, world, x, y, z, side, hitX, hitY, hitZ);

            if (side == 0) //Top
            {
                for (int xOffset = -1; xOffset <= 1; xOffset++)
                {
                    for (int zOffset = -1; zOffset <= 1; zOffset++)
                    {
                        if (stack.stackSize > 0)
                            if (placeBlock(stack, player, world, x + xOffset, y, z + zOffset, side, hitX, hitY, hitZ))
                                placed = true;

                        if (stack.stackSize > 0)
                            if (placeBlock(stack, player, world, x - xOffset, y, z - zOffset, side, hitX, hitY, hitZ))
                                placed = true;
                    }
                }
            }

            else
            {
                if (stack.stackSize > 0)
                    if (placeBlock(stack, player, world, x, y + 1, z, side, hitX, hitY, hitZ))
                        placed = true;
                if (stack.stackSize > 0)
                    if (placeBlock(stack, player, world, x, y - 1, z, side, hitX, hitY, hitZ))
                        placed = true;

                int xOffset = 0;
                int zOffset = 0;
                int facing = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3; //Side 0

                /*if (side == 2)
                    facing = 0;
                if (side == 3)
                    facing = 2;
                if (side == 4)
                    facing = 3;
                if (side == 5)
                    facing = 1;*/

                if (facing == 0)
                    xOffset = 1;
                if (facing == 1)
                    zOffset = 1;
                if (facing == 2)
                    xOffset = -1;
                if (facing == 3)
                    zOffset = -1;

                for (int yOffset = -1; yOffset <= 1; yOffset++)
                {
                    if (stack.stackSize > 0)
                        if (placeBlock(stack, player, world, x + xOffset, y + yOffset, z + zOffset, side, hitX, hitY, hitZ))
                            placed = true;

                    if (stack.stackSize > 0)
                        if (placeBlock(stack, player, world, x - xOffset, y + yOffset, z - zOffset, side, hitX, hitY, hitZ))
                            placed = true;
                }
            }
            return placed;
        }
    }

    public boolean placeBlock(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        int i1 = world.getBlockId(x, y, z);

        if (i1 == Block.snow.blockID && (world.getBlockMetadata(x, y, z) & 7) < 1)
        {
            side = 1;
        }
        else if (i1 != Block.vine.blockID && i1 != Block.tallGrass.blockID && i1 != Block.deadBush.blockID
                && (Block.blocksList[i1] == null || !Block.blocksList[i1].isBlockReplaceable(world, x, y, z)))
        {
            if (side == 0)
            {
                --y;
            }

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
        }

        if (stack.stackSize == 0)
        {
            return false;
        }
        else if (!player.canPlayerEdit(x, y, z, side, stack))
        {
            return false;
        }
        else if (y == 255 && Block.blocksList[this.bID].blockMaterial.isSolid())
        {
            return false;
        }
        else if (world.canPlaceEntityOnSide(this.bID, x, y, z, false, side, player, stack))
        {
            Block block = Block.blocksList[this.bID];
            int j1 = this.getMetadata(stack.getItemDamage());
            int k1 = Block.blocksList[this.bID].onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, j1);

            if (placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, k1))
            {
                world.playSoundEffect((double) ((float) x + 0.5F), (double) ((float) y + 0.5F), (double) ((float) z + 0.5F), block.stepSound.getPlaceSound(),
                        (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
                --stack.stackSize;
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        if (stack.hasTagCompound())
        {
            int value = stack.getTagCompound().getInteger("Value");
            list.add("Crystal Value: " + value);
        }
    }
}
