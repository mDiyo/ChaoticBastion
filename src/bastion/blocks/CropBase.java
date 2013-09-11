package bastion.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class CropBase extends BlockFlower
{
    @SideOnly(Side.CLIENT)
    protected Icon[] iconArray;
    protected boolean simpleGrowth;

    protected CropBase(int ID, boolean growth)
    {
        super(ID);
        simpleGrowth = growth;
        this.setTickRandomly(true);
        float f = 0.5F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        this.setCreativeTab((CreativeTabs) null);
        this.setHardness(0.0F);
        this.setStepSound(soundGrassFootstep);
        this.disableStats();
    }

    /**
     * Assumes vanilla wheat behavior - change for others
     */
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        super.updateTick(world, x, y, z, rand);

        int meta = world.getBlockMetadata(x, y, z);
        int light = world.getBlockLightValue(x, y + 1, z);
        if (!requiresSun(meta) || light >= getMinimumLight(world, x, y, z))
        {
            if (meta < 7)
            {
                float f = simpleGrowth ? this.getSimpleGrowthRate(world, x, y, z, meta, light) : this.getGrowthRate(world, x, y, z, meta, light);

                if (rand.nextInt((int) (25.0F / f) + 1) == 0)
                {
                    ++meta;
                    world.setBlockMetadataWithNotify(x, y, z, meta, 3);
                }
            }
        }
    }

    public boolean requiresSun(int meta)
    {
        return true;
    }

    public boolean requriesSun(World world, int x, int y, int z)
    {
        return requiresSun(world.getBlockMetadata(x, y, z));
    }

    public int getMinimumLight(World world, int x, int y, int z)
    {
        return 9;
    }

    /**
     * Apply bonemeal to the crops. Entity may be null, passes back to the bonemeal event
     */
    public abstract boolean fertilize(World world, int x, int y, int z, Random rand, EntityLivingBase entity);

    /**
     * Gets the growth rate for the crop. Vanilla style, prefers rows
     */
    protected float getGrowthRate(World par1World, int x, int y, int z, int meta, int light)
    {
        float f = 1.0F;
        int l = par1World.getBlockId(x, y, z - 1);
        int i1 = par1World.getBlockId(x, y, z + 1);
        int j1 = par1World.getBlockId(x - 1, y, z);
        int k1 = par1World.getBlockId(x + 1, y, z);
        int l1 = par1World.getBlockId(x - 1, y, z - 1);
        int i2 = par1World.getBlockId(x + 1, y, z - 1);
        int j2 = par1World.getBlockId(x + 1, y, z + 1);
        int k2 = par1World.getBlockId(x - 1, y, z + 1);
        boolean flag = j1 == this.blockID || k1 == this.blockID;
        boolean flag1 = l == this.blockID || i1 == this.blockID;
        boolean flag2 = l1 == this.blockID || i2 == this.blockID || j2 == this.blockID || k2 == this.blockID;

        for (int l2 = x - 1; l2 <= x + 1; ++l2)
        {
            for (int i3 = z - 1; i3 <= z + 1; ++i3)
            {
                int j3 = par1World.getBlockId(l2, y - 1, i3);
                float f1 = 0.0F;

                if (blocksList[j3] != null && blocksList[j3].canSustainPlant(par1World, l2, y - 1, i3, ForgeDirection.UP, this))
                {
                    f1 = 1.0F;

                    if (blocksList[j3].isFertile(par1World, l2, y - 1, i3))
                    {
                        f1 = 3.0F;
                    }
                }

                if (l2 != x || i3 != z)
                {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        if (flag2 || flag && flag1)
        {
            f /= 2.0F;
        }

        return f;
    }

    /**
     * Gets the growth rate for the crop. Simpler, grows faster when in adequate light
     */
    protected float getSimpleGrowthRate(World world, int x, int y, int z, int meta, int light)
    {
        float growth = 0.25f * (light - 7);
        Block soil = blocksList[world.getBlockId(x, y - 1, z)];

        if (world.canBlockSeeTheSky(x, y, z) || !requiresSun(meta))
            growth += 2f;

        if (soil != null && soil.isFertile(world, x, y - 1, z))
            growth *= 2f;

        return 1f + growth;
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 6;
    }

    protected abstract ItemStack getSeedStack(int metadata);
    
    public int quantityDropped(int meta, int fortune, Random random)
    {
        return meta / 2 + random.nextInt(fortune);
    }

    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = super.getBlockDropped(world, x, y, z, metadata, fortune);

        if (metadata >= 7)
        {
            for (int n = 0; n < 3 + fortune; n++)
            {
                if (world.rand.nextInt(15) <= metadata)
                {
                    ret.add(getSeedStack(metadata));
                }
            }
        }

        return ret;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        ItemStack stack = getSeedStack(world.getBlockMetadata(x, y, z));
        stack.stackSize = 1;
        return stack;
    }
}
