package bastion;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

import bastion.blocks.*;
import bastion.blocks.logic.*;
import bastion.items.block.*;
import bastion.util.*;

public class CContent
{
    public static void createBlocks()
    {
        //Defensive
        woodWall = new WallBlock(PHBastion.woodWall, Material.wood, 0.4f).setStepSound(Block.soundWoodFootstep).setCreativeTab(tab).setUnlocalizedName("bastion.woodwall");
        GameRegistry.registerBlock(woodWall, "bastion.woodwall");
        tab.init(new ItemStack(woodWall));
        
        banner = new BannerBlock(PHBastion.banner, Material.wood).setStepSound(Block.soundWoodFootstep).setCreativeTab(tab).setUnlocalizedName("bastion.banner");
        GameRegistry.registerBlock(banner, BannerItem.class, "bastion.banner");
        GameRegistry.registerTileEntity(BannerLogic.class, "bastion.banner.light");
        
        //Crystalline
        aggregator = new Aggregator(PHBastion.aggregator).setUnlocalizedName("bastion.aggregator");
        aggregator.stepSound = Block.soundMetalFootstep;
        GameRegistry.registerBlock(aggregator, "bastion.aggregator");
        GameRegistry.registerTileEntity(LightAggregator.class, "bastion.aggregator.light");

        crystalBlock = new CrystalBlock(PHBastion.crystalBlock).setUnlocalizedName("bastion.crystal.block");
        crystalBlock.stepSound = Block.soundGlassFootstep;
        GameRegistry.registerBlock(crystalBlock, LightCrystalItem.class, "bastion.crystal.block");
        GameRegistry.registerTileEntity(CrystalLogic.class, "bastion.crystal.logic");

        antilight = new AntiLight(PHBastion.antilight).setUnlocalizedName("bastion.antilight");
        GameRegistry.registerBlock(antilight, "bastion.antilight");
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
    //Defensive
    public static Block woodWall;
    public static Block banner;
    
    //Crystalline
    public static Block crystalBlock;
    public static Block aggregator;
    public static Block antilight;
}
