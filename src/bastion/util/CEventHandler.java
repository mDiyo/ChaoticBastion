package bastion.util;

import net.minecraft.entity.player.EnumStatus;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.oredict.OreDictionary.OreRegisterEvent;
import tconstruct.library.util.ValueCoordTuple;
import bastion.world.CrystalValues;
import bastion.world.WorldTracker;

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
    
    /* Chunks */
    @ForgeSubscribe
    public void chunkDataLoad (ChunkDataEvent.Load event)
    {
        Chunk chunk = event.getChunk();
        int worldID = chunk.worldObj.provider.dimensionId;
        ValueCoordTuple coord = new ValueCoordTuple(worldID, chunk.xPosition, chunk.zPosition);
        CrystalValues theft = new CrystalValues("Theft");
        WorldTracker.theftValue.put(coord, theft.loadFromNBT(event.getData()));
        CrystalValues charge = new CrystalValues("Charge");
        WorldTracker.theftValue.put(coord, charge.loadFromNBT(event.getData()));
    }

    @ForgeSubscribe
    public void chunkDataSave (ChunkDataEvent.Save event)
    {
        Chunk chunk = event.getChunk();
        int worldID = chunk.worldObj.provider.dimensionId;
        ValueCoordTuple coord = new ValueCoordTuple(worldID, chunk.xPosition, chunk.zPosition);
        if (WorldTracker.theftValue.containsKey(coord))
        {
            CrystalValues crystal = WorldTracker.theftValue.get(coord);
            crystal.saveToNBT(event.getData());
            if (!event.getChunk().isChunkLoaded)
            {
                WorldTracker.theftValue.remove(coord);
            }
        }

        if (WorldTracker.charge.containsKey(coord))
        {
            CrystalValues crystal = WorldTracker.charge.get(coord);
            crystal.saveToNBT(event.getData());
            if (!event.getChunk().isChunkLoaded)
            {
                WorldTracker.charge.remove(coord);
            }
        }
    }
}
