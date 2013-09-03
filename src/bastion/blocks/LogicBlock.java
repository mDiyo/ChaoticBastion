package bastion.blocks;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import bastion.blocks.logic.InventoryLogicBase;

/*
 * Base block for inventory. Does not necessarily have a Gui
 */
public abstract class LogicBlock extends BlockContainer
{
    protected Random rand = new Random();

    protected LogicBlock(int id, Material material)
    {
        super(id, material);
    }

    /* Logic backend */
    public TileEntity createNewTileEntity (World var1)
    {
        return null;
    }

    public abstract TileEntity createTileEntity (World world, int metadata);

    /* Inventory */

    @Override
    public void breakBlock (World par1World, int x, int y, int z, int par5, int meta)
    {
        TileEntity te = par1World.getBlockTileEntity(x, y, z);

        if (te != null && te instanceof InventoryLogicBase)
        {
            InventoryLogicBase logic = (InventoryLogicBase) te;
            for (int iter = 0; iter < logic.getSizeInventory(); ++iter)
            {
                ItemStack stack = logic.getStackInSlot(iter);

                if (stack != null && logic.canDropInventorySlot(iter))
                {
                    float jumpX = rand.nextFloat() * 0.8F + 0.1F;
                    float jumpY = rand.nextFloat() * 0.8F + 0.1F;
                    float jumpZ = rand.nextFloat() * 0.8F + 0.1F;

                    while (stack.stackSize > 0)
                    {
                        int itemSize = rand.nextInt(21) + 10;

                        if (itemSize > stack.stackSize)
                        {
                            itemSize = stack.stackSize;
                        }

                        stack.stackSize -= itemSize;
                        EntityItem entityitem = new EntityItem(par1World, (double) ((float) x + jumpX), (double) ((float) y + jumpY), (double) ((float) z + jumpZ), new ItemStack(stack.itemID,
                                itemSize, stack.getItemDamage()));

                        if (stack.hasTagCompound())
                        {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound) stack.getTagCompound().copy());
                        }

                        float offset = 0.05F;
                        entityitem.motionX = (double) ((float) rand.nextGaussian() * offset);
                        entityitem.motionY = (double) ((float) rand.nextGaussian() * offset + 0.2F);
                        entityitem.motionZ = (double) ((float) rand.nextGaussian() * offset);
                        par1World.spawnEntityInWorld(entityitem);
                    }
                }
            }
        }

        super.breakBlock(par1World, x, y, z, par5, meta);
    }

    /* Placement */
    @Override
    public void onBlockPlacedBy (World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack stack)
    {
        if (stack.hasDisplayName())
        {
            ((InventoryLogicBase) world.getBlockTileEntity(x, y, z)).setInvName(stack.getDisplayName());
        }
    }

    public int damageDropped (int meta)
    {
        return meta;
    }

    /* Textures */
    public Icon[] icons;

    public abstract String[] getTextureNames ();

    @Override
    public void registerIcons (IconRegister iconRegister)
    {
        String[] textureNames = getTextureNames();
        this.icons = new Icon[textureNames.length];

        for (int i = 0; i < this.icons.length; ++i)
        {
            this.icons[i] = iconRegister.registerIcon("chaoticbastion:" + textureNames[i]);
        }
    }
}
