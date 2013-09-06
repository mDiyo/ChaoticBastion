package bastion.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class WallBlock extends Block
{
    public WallBlock(int id, Material material, float hardness)
    {
        super(id, material);
        this.setHardness(hardness);
        this.setResistance(hardness * 10);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon("chaoticbastion:woodwall");
    }

    public float getBlockHardness(World world, int x, int y, int z)
    {
        int amount = world.getBlockMetadata(x, y, z) + 1;
        return this.blockHardness * amount;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
    {
        if ((side == 0 || side == 1) && world.getBlockId(x, y, z) == this.blockID)
            return false;
        return super.shouldSideBeRendered(world, x, y, z, side);
    }
}
