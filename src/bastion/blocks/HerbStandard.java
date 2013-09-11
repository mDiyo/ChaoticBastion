package bastion.blocks;

import java.util.ArrayList;
import java.util.Random;

import bastion.CContent;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HerbStandard extends CropBase
{
    String herbType;

    public HerbStandard(int ID, String type)
    {
        super(ID, true);
        this.setCreativeTab(CContent.tab);
        herbType = type;
    }

    @Override
    public boolean fertilize(World world, int x, int y, int z, Random rand, EntityLivingBase entity)
    {
        return false;
    }

    @Override
    protected ItemStack getSeedStack(int metadata)
    {
        return new ItemStack(this.blockID, 1, 3);
    }

    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        if (metadata >= 3)
        {
            for (int n = 0; n < (metadata - 3) + fortune; n++)
            {
                ret.add(getSeedStack(metadata));
            }
        }

        return ret;
    }
    
    public int getRenderType()
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        if (meta < 0 || meta > 3)
        {
            meta = 3;
        }

        return this.iconArray[meta];
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconArray = new Icon[4];

        for (int i = 1; i <= this.iconArray.length; ++i)
        {
            this.iconArray[i - 1] = par1IconRegister.registerIcon("chaoticbastion:crops/herb_" + herbType + "_" + i);
        }
    }
}
