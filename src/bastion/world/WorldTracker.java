package bastion.world;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import bastion.util.ValueCoordTuple;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldTracker implements IWorldGenerator
{
    public enum CrystalType
    {
        Light, Life, Time, Stone, Fire, Water
    }

    public static HashMap<ValueCoordTuple, CrystalValues> theftValue = new HashMap<ValueCoordTuple, CrystalValues>(); //Value of materials
    public static HashMap<ValueCoordTuple, CrystalValues> charge = new HashMap<ValueCoordTuple, CrystalValues>(); //Balance of crystal

    public static void updateTheft (int dim, int posX, int posZ, int value, CrystalType type)
    {
        ValueCoordTuple coord = new ValueCoordTuple(dim, (int) Math.floor(posX / 16D), (int) Math.floor(posZ / 16D));
        CrystalValues crystal = theftValue.get(coord);
        if (crystal == null)
            crystal = new CrystalValues("Theft");
        crystal.addValue(value, type);
        theftValue.put(coord, crystal);
    }

    public static void updateCharge (int dim, int posX, int posZ, int value, CrystalType type)
    {
        ValueCoordTuple coord = new ValueCoordTuple(dim, (int) Math.floor(posX / 16D), (int) Math.floor(posZ / 16D));
        CrystalValues crystal = charge.get(coord);
        if (crystal == null)
            crystal = new CrystalValues("Charge");
        crystal.addValue(value, type);
        charge.put(coord, crystal);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        initializeChunkData(chunkX, chunkZ, world.provider.dimensionId);
    }
    
    void initializeChunkData (int chunkX, int chunkZ, int worldID)
    {
        ValueCoordTuple coord = new ValueCoordTuple(worldID, chunkX, chunkZ);
        WorldTracker.theftValue.put(coord, new CrystalValues("Theft"));
        WorldTracker.charge.put(coord, new CrystalValues("Charge"));
    }
}
