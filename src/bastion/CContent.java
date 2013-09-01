package bastion;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import bastion.blocks.*;
import bastion.util.*;
import cpw.mods.fml.common.registry.GameRegistry;

public class CContent
{
    public static void createBlocks()
    {
        woodWall = new WallBlock(PHBastion.woodWall, Material.wood, 0.4f).setCreativeTab(tab).setUnlocalizedName("bastion.woodwall");
        GameRegistry.registerBlock(woodWall, "bastion.woodwall");
        tab.init(new ItemStack(woodWall));
    }

    public static void createItems()
    {

    }

    public static void createRecipes()
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(woodWall, 16, 0), "x x", " b ", "x x", 'b', "plankWood", 'x', "stickWood"));
    }

    public static void createOtherContent()
    {
    }

    public static void crossModCommunication()
    {

    }

    public static TabBastion tab = new TabBastion();
    public static Block woodWall;
}
