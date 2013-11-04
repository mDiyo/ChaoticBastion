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
import bastion.entity.friendly.*;
import bastion.items.*;
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

        //Decorative 
        coloredStone = new ColorBlock(PHBastion.coloredStone, Material.rock, 1.5f, "stone_raw", "stone.raw", PHBastion.coloredCobble).setUnlocalizedName("bastion.stone.raw");
        GameRegistry.registerBlock(coloredStone, ColorItemBlock.class, "bastion.stone.raw");
        coloredCobble = new ColorBlock(PHBastion.coloredCobble, Material.rock, 2.0f, "stone_cobble", "stone.cobble", 0).setUnlocalizedName("bastion.stone.cobble");
        GameRegistry.registerBlock(coloredCobble, ColorItemBlock.class, "bastion.stone.cobble");
        coloredMossCobble = new ColorBlock(PHBastion.coloredMossCobble, Material.rock, 2.0f, "stone_mosscobble", "stone.mosscobble", 0).setUnlocalizedName("bastion.stone.mosscobble");
        GameRegistry.registerBlock(coloredMossCobble, ColorItemBlock.class, "bastion.stone.mosscobble");
        coloredStoneBrick = new ColorBlock(PHBastion.coloredStoneBrick, Material.rock, 1.5f, "stone_brick", "stone.brick", 0).setUnlocalizedName("bastion.stone.brick");
        GameRegistry.registerBlock(coloredStoneBrick, ColorItemBlock.class, "bastion.stone.brick");
        coloredMossStoneBrick = new ColorBlock(PHBastion.coloredMossStoneBrick, Material.rock, 1.5f, "stone_mossbrick", "stone.mossbrick", 0).setUnlocalizedName("bastion.stone.mossbrick");
        GameRegistry.registerBlock(coloredMossStoneBrick, ColorItemBlock.class, "bastion.stone.mossbrick");
        coloredCrackedStoneBrick = new ColorBlock(PHBastion.coloredCrackedBrick, Material.rock, 1.5f, "stone_crackedbrick", "stone.crackedbrick", 0).setUnlocalizedName("bastion.stone.crackedbrick");
        GameRegistry.registerBlock(coloredCrackedStoneBrick, ColorItemBlock.class, "bastion.stone.crackedbrick");
        coloredStoneRoad = new ColorBlock(PHBastion.coloredStoneRoad, Material.rock, 1.5f, "stone_road", "stone.road", 0).setUnlocalizedName("bastion.stone.road");
        GameRegistry.registerBlock(coloredStoneRoad, ColorItemBlock.class, "bastion.stone.road");
        coloredStoneFancyBrick = new ColorBlock(PHBastion.coloredStoneFancyBrick, Material.rock, 1.5f, "stone_fancy", "stone.fancy", 0).setUnlocalizedName("bastion.stone.fancy");
        GameRegistry.registerBlock(coloredStoneFancyBrick, ColorItemBlock.class, "bastion.stone.fancy");
        coloredStoneSquareBrick = new ColorBlock(PHBastion.coloredStoneSquareBrick, Material.rock, 1.5f, "stone_square", "stone.chiseled", 0).setUnlocalizedName("bastion.stone.chiseled");
        GameRegistry.registerBlock(coloredStoneSquareBrick, ColorItemBlock.class, "bastion.stone.chiseled");
        decorTab.init(new ItemStack(coloredStoneSquareBrick, 1, 3));

        //Worldgen
        baseHerb = new HerbStandard(PHBastion.baseHerb, "base").setUnlocalizedName("bastion.herb.base");
        GameRegistry.registerBlock(baseHerb, HerbItem.class, "bastion.herb.base");
        bloodyHerb = new HerbStandard(PHBastion.bloodyHerb, "bloody").setUnlocalizedName("bastion.herb.bloody");
        GameRegistry.registerBlock(bloodyHerb, HerbItem.class, "bastion.herb.bloody");
        manaHerb = new HerbStandard(PHBastion.manaHerb, "mana").setUnlocalizedName("bastion.herb.mana");
        GameRegistry.registerBlock(manaHerb, HerbItem.class, "bastion.herb.mana");
        whiteHerb = new HerbStandard(PHBastion.whiteHerb, "white").setUnlocalizedName("bastion.herb.white");
        GameRegistry.registerBlock(whiteHerb, HerbItem.class, "bastion.herb.white");
        mandrakeHerb = new HerbStandard(PHBastion.mandrakeHerb, "mandrake").setUnlocalizedName("bastion.herb.mandrake");
        GameRegistry.registerBlock(mandrakeHerb, HerbItem.class, "bastion.herb.mandrake");
        springHerb = new HerbStandard(PHBastion.springHerb, "spring").setUnlocalizedName("bastion.herb.spring");
        GameRegistry.registerBlock(springHerb, HerbItem.class, "bastion.herb.spring");
        poisonHerb = new HerbStandard(PHBastion.poisonHerb, "poison").setUnlocalizedName("bastion.herb.poison");
        GameRegistry.registerBlock(poisonHerb, HerbItem.class, "bastion.herb.poison");

    }

    public static void createItems()
    {
        woodMinecart = new Item(PHBastion.woodCart).setUnlocalizedName("bastion.woodcart");
        spawnEgg = new CSpawnEgg(PHBastion.spawnEgg).setUnlocalizedName("bastion.spawnEgg");
        basket = new Basket(PHBastion.basket).setUnlocalizedName("bastion.basket");
    }

    public static void createEntities()
    {
        EntityRegistry.registerModEntity(WoodMinecartEntity.class, "WoodMinecart.Empty", 0, ChaoticBastion.instance, 32, 3, true);
        EntityRegistry.registerModEntity(GardeSlime.class, "GardeSlime", 1, ChaoticBastion.instance, 64, 3, true);

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

    public static TabBastion tab = new TabBastion("ChaoticBastion");
    public static TabBastion decorTab = new TabBastion("ChaoticBastionDecor");

    //Defensive
    public static Block woodWall;
    public static Block banner;

    //Base building
    public static Block woodRail;

    //Crystalline
    public static Block crystalBlock;
    public static Block aggregator;
    public static Block antilight;

    //Decorative
    public static Block coloredStone;
    public static Block coloredCobble;
    public static Block coloredMossCobble;
    public static Block coloredStoneBrick;
    public static Block coloredMossStoneBrick;
    public static Block coloredCrackedStoneBrick;
    public static Block coloredStoneRoad;
    public static Block coloredStoneFancyBrick;
    public static Block coloredStoneSquareBrick;

    //Worldgen
    public static Block baseHerb;
    public static Block bloodyHerb;
    public static Block manaHerb;
    public static Block whiteHerb;
    public static Block mandrakeHerb;
    public static Block springHerb;
    public static Block poisonHerb;

    public static Item woodMinecart;
    public static Item spawnEgg;
    
    //Villager related
    public static Item basket;
}
