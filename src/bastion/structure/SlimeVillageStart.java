package bastion.structure;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureStart;

public class SlimeVillageStart extends StructureStart
{
    public SlimeVillageStart() {}
    
    public SlimeVillageStart(World world, Random random, int x, int y, int z)
    {
        
    }
    
    public void generateStructure(World world, Random random, StructureBoundingBox sbb)
    {
        //No generation happening here
    }
}
