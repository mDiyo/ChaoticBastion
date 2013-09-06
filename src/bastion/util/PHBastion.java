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
        
        aggregator = config.getBlock("Aggregator", 2900).getInt(2900);
        crystalBlock = config.getBlock("Crystal Spire", 2901).getInt(2901);
        antilight = config.getBlock("Antilight", 2902).getInt(2902);
        
        //Items
        woodCart = config.getItem("Wooden Minecart", 23701).getInt(23701);
    }
    
    //Defensive
    public static int woodWall;
    public static int banner;
    
    //Base building
    public static int woodRail;
    
    //Crystalline
    public static int aggregator;
    public static int crystalBlock;
    public static int antilight;
    
    public static int woodCart;

}
