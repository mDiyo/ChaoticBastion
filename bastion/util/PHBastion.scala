package bastion.util

import java.io.IOException
import java.io.File
import net.minecraftforge.common.Configuration

object PHBastion {

    def initProps(location: File) {
        /* Here we will set up the config file for the mod 
         * First: Create a folder inside the config folder
         * Second: Create the actual config file
         * Note: Configs are a pain, but absolutely necessary for every mod.
         */
        var newFile = new File(location + "/ChaoticBastion.txt")

        /* Some basic debugging will go a long way */
        try {
            newFile.createNewFile()
        } catch {
            case e: IOException => {
                System.out.println("Could not create configuration file for Chaotic Bastion. Reason:")
                System.out.println(e)
            }
        }

        /* [Forge] Configuration class, used as config method */
        val config = new Configuration(newFile)

        /* Load the configuration file */
        config.load()

        /* Define the mod's IDs. 
         * Avoid values below 4096 for items and in the 250-450 range for blocks
         */
    }
}