package bastion.blocks;

import java.util.List;

import bastion.CContent;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ColorBlock extends Block
{
    public static final String[] colorNames = new String[] { "white", "orange", "magenta", "lightblue", "yellow", "lime", "pink", "gray", "silver", "aqua", "purple", "blue", "brown", "green", "red",
            "black" };
    public final String textureName;
    public final String localName;
    public Icon[] icons;

    public ColorBlock(int ID, Material material, float hardness, String texture, String name)
    {
        super(ID, material);
        this.setHardness(hardness);
        this.textureName = texture;
        this.localName = name;
        this.setCreativeTab(CContent.decorTab);
    }
    
    @Override
    public String getUnlocalizedName()
    {
        return "tile." + localName;
    }

    @Override
    public int damageDropped(int meta)
    {
        return meta;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        this.icons = new Icon[colorNames.length];

        for (int i = 0; i < this.icons.length; ++i)
        {
            this.icons[i] = iconRegister.registerIcon("chaoticbastion:decoration/" + textureName + "_" + colorNames[i]);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int meta)
    {
        return meta < icons.length ? icons[meta] : icons[0];
    }

    @Override
    public void getSubBlocks(int id, CreativeTabs tab, List list)
    {
        for (int iter = 0; iter < icons.length; iter++)
        {
            list.add(new ItemStack(id, 1, iter));
        }
    }
}
