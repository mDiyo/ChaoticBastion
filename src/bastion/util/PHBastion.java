package bastion.util;

import java.io.File;
import java.io.IOException;

import net.minecraftforge.common.Configuration;

public class PHBastion
{
    public static void initProps (File location)
    {
        /* Here we will set up the config file for the mod 
         * First: Create a folder inside the config folder
         * Second: Create the actual config file
         * Note: Configs are a pain, but absolutely necessary for every mod.
         */
        File newFile = new File(location + "/ChaoticBastion.txt");

        /* Some basic debugging will go a long way */
        try
        {
            newFile.createNewFile();
        }
        catch (IOException e)
        {
            System.out.println("Could not create configuration file for Chaotic Bastion. Reason:");
            System.out.println(e);
        }

        /* [Forge] Configuration class, used as config method */
        Configuration config = new Configuration(newFile);

        /* Load the configuration file */
        config.load();

        /* Define the mod's IDs. 
         * Avoid values below 4096 for items and in the 250-450 range for blocks
         */
        
        //Blocks
        woodWall = config.getBlock("Wooden Wall", 2870).getInt(2870);
        banner = config.getBlock("Banner", 2871).getInt(2871);
        woodRail = config.getBlock("Wooden Rail", 2872).getInt(2872);
        
        baseHerb = config.getBlock("Base Herb", 2873).getInt(2873);
        bloodyHerb = config.getBlock("Bloody Herb", 2874).getInt(2874);
        manaHerb = config.getBlock("Mana Herb", 2875).getInt(2875);
        whiteHerb = config.getBlock("White Herb", 2876).getInt(2876);
        mandrakeHerb = config.getBlock("Mandrake Herb", 2877).getInt(2877);
        springHerb = config.getBlock("Spring Herb", 2878).getInt(2878);
        poisonHerb = config.getBlock("Poison Herb", 2879).getInt(2879);
        
        aggregator = config.getBlock("Aggregator", 2900).getInt(2900);
        crystalBlock = config.getBlock("Crystal Spire", 2901).getInt(2901);
        antilight = config.getBlock("Antilight", 2902).getInt(2902);
        
        coloredStone = config.getBlock("Colored Stone", 2903).getInt(2903);
        coloredCobble = config.getBlock("Colored Cobblestone", 2904).getInt(2904);
        coloredMossCobble = config.getBlock("Colored Mossy Cobble", 2905).getInt(2905);
        coloredStoneBrick = config.getBlock("Colored Stone Brick", 2906).getInt(2906);
        coloredMossStoneBrick = config.getBlock("Colored Mossy Stone Brick", 2907).getInt(2907);
        coloredCrackedBrick = config.getBlock("Colored Cracked Stone Brick", 2908).getInt(2908);
        coloredStoneRoad = config.getBlock("Colored Stone Road", 2909).getInt(2909);
        coloredStoneFancyBrick = config.getBlock("Colored Fancy Stone Brick", 2910).getInt(2910);
        coloredStoneSquareBrick = config.getBlock("Colored Chiseled Stone Brick", 2911).getInt(2911);
        
        //Items
        woodCart = config.getItem("Wooden Minecart", 23701).getInt(23701);
        spawnEgg = config.getItem("Spawn Egg", 23702).getInt(23702);
    }
    
    //Worldgen
    public static int baseHerb;
    public static int bloodyHerb;
    public static int manaHerb;
    public static int whiteHerb;
    public static int mandrakeHerb;
    public static int springHerb;
    public static int poisonHerb;
    
    //Defensive
    public static int woodWall;
    public static int banner;
    
    //Base building
    public static int woodRail;
    
    //Crystalline
    public static int aggregator;
    public static int crystalBlock;
    public static int antilight;
    
    //Decorative
    public static int coloredStone;
    public static int coloredCobble;
    public static int coloredMossCobble;
    public static int coloredStoneBrick;
    public static int coloredMossStoneBrick;
    public static int coloredCrackedBrick;
    public static int coloredStoneRoad;
    public static int coloredStoneFancyBrick;
    public static int coloredStoneSquareBrick;
    
    public static int woodCart;
    public static int spawnEgg;
    

}
