package bastion.items.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import bastion.CContent;
import bastion.blocks.logic.BannerLogic;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
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
    private int bID;
    @SideOnly(Side.CLIENT)
    private Icon icon;
    
    public HerbItem(int par1)
    {
        super(par1);
        this.maxStackSize = 1;
        this.bID = par1 + 256;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        String s = Block.blocksList[this.bID].getItemIconName();

        if (s != null)
        {
            this.icon = par1IconRegister.registerIcon(s);
        }
    }
    
    public Icon getIconFromDamage(int par1)
    {
        return this.icon != null ? this.icon : Block.blocksList[this.bID].getIcon(1, 3);
    }
}
