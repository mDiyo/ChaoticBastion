package bastion.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import tconstruct.library.blocks.InventoryBlock;
import bastion.ChaoticBastion;
import bastion.blocks.logic.WoodTankLogic;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WoodTank extends InventoryBlock
{
    public WoodTank(int id, Material material)
    {
        super(id, material);
    }
    
    @Override
    public void onBlockPlacedBy (World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack stack)
    {
        super.onBlockPlacedBy(world, x, y, z, entityliving, stack);
        WoodTankLogic logic = (WoodTankLogic) world.getBlockTileEntity(x,y,z);
        logic.checkValidStructure();
    }
    
    /*@Override
    public boolean removeBlockByPlayer (World world, EntityPlayer player, int x, int y, int z)
    {
        WoodTankLogic logic = (WoodTankLogic) world.getBlockTileEntity(x,y,z);
        return super.removeBlockByPlayer(world, player, x, y, z);
    }*/

    @Override
    public TileEntity createTileEntity (World world, int metadata)
    {
        return new WoodTankLogic();
    }

    @Override
    public Integer getGui (World world, int x, int y, int z, EntityPlayer entityplayer)
    {
        return null;
    }

    @Override
    public Object getModInstance ()
    {
        return ChaoticBastion.instance;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon (int side, int meta)
    {
        return Block.beacon.getIcon(1, 0);
    }

    @Override
    public String[] getTextureNames ()
    {
        return new String[0];
    }
    
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
