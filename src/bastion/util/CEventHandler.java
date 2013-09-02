package bastion.util;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EnumStatus;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.oredict.OreDictionary.OreRegisterEvent;

public class CEventHandler
{
    @ForgeSubscribe
    public void registerOre(OreRegisterEvent evt)
    {

    }

    @ForgeSubscribe
    public void playerSleep(PlayerSleepInBedEvent event)
    {
        if (event.entityPlayer.worldObj.provider.dimensionId == 0)
        {
            //Side side = FMLCommonHandler.instance().getEffectiveSide();
            //if (side != Side.CLIENT)
            event.result = EnumStatus.OTHER_PROBLEM;

            event.entityPlayer.setSpawnChunk(new ChunkCoordinates(event.x, event.y, event.z), false, 0);
            {
                event.entityPlayer.addChatMessage("Spawn point set!");
            }
        }
    }
}
