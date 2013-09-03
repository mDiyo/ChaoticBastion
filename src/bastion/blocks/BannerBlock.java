package bastion.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import bastion.blocks.logic.BannerLogic;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BannerBlock extends LogicBlock
{

    public BannerBlock(int id, Material material)
    {
        super(id, material);
        this.setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.75F, 0.625F);
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return new BannerLogic();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon (int side, int meta)
    {
        return Block.planks.getIcon(side, 0);
    }
    
    @Override
    public void registerIcons (IconRegister iconRegister)
    {
        
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public int damageDropped (int meta)
    {
        return meta;
    }

    @Override
    public String[] getTextureNames()
    {
        return null;
    }
}
