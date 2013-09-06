package bastion;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

import bastion.blocks.*;
import bastion.blocks.logic.*;
import bastion.entity.*;
import bastion.items.block.*;
import bastion.util.*;

public class CContent
{
    public static void createBlocks()
    {
        //Defensive
        woodWall = new WallBlock(PHBastion.woodWall, Material.wood, 0.4f).setStepSound(Block.soundWoodFootstep).setCreativeTab(tab).setUnlocalizedName("bastion.woodwall");
        GameRegistry.registerBlock(woodWall, WoodBarrierItem.class, "bastion.woodwall");
        tab.init(new ItemStack(woodWall));
        
        banner = new BannerBlock(PHBastion.banner, Material.wood).setStepSound(Block.soundWoodFootstep).setCreativeTab(tab).setUnlocalizedName("bastion.banner");
        GameRegistry.registerBlock(banner, BannerItem.class, "bastion.banner");
        GameRegistry.registerTileEntity(BannerLogic.class, "bastion.banner.light");
        
        //Base building
        woodRail = new WoodRail(PHBastion.woodRail).setStepSound(Block.soundWoodFootstep).setCreativeTab(tab).setUnlocalizedName("bastion.woodrail");
        GameRegistry.registerBlock(woodRail, "bastion.woodrail");
        
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
        woodMinecart = new Item(PHBastion.woodCart).setUnlocalizedName("bastion.woodcart");
    }
    
    public static void createEntities()
    {
        EntityRegistry.registerModEntity(WoodMinecartEntity.class, "WoodMinecart.Empty", 0, ChaoticBastion.instance, 32, 3, true);
        
    }

    public static void createRecipes()
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(woodWall, 16, 0), "x x", " b ", "x x", 'b', "plankWood", 'x', "stickWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(woodRail, 4, 0), "b b", "bxb", "b b", 'b', "plankWood", 'x', "stickWood"));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(woodMinecart, 1, 0), "b b", "bbb", 'b', "plankWood"));
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
    
    //Base building
    public static Block woodRail;
    
    //Crystalline
    public static Block crystalBlock;
    public static Block aggregator;
    public static Block antilight;
    
    public static Item woodMinecart;
}
