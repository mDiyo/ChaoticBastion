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
        aggregator = config.getBlock("Aggregator", 2850).getInt(2850);
        crystalBlock = config.getBlock("Crystal Spire", 2851).getInt(2851);
        antilight = config.getBlock("Antilight", 2852).getInt(2852);
        
        woodWall = config.getBlock("Wooden Wall", 2870).getInt(2870);
        banner = config.getBlock("Banner", 2871).getInt(2871);
    }
    
    public static int woodWall;
    public static int banner;
    
    public static int aggregator;
    public static int crystalBlock;
    public static int antilight;

}
