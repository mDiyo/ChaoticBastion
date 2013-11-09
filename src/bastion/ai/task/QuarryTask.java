package bastion.ai.task;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.world.World;
import tconstruct.library.util.CoordTuple;

public class QuarryTask extends TaskBase
{
    CoordTuple home;
    CoordTuple block;
    byte tick;
    boolean reverseX;
    boolean reverseZ;
    public static double cutSpeed = 2.5;
    PathNavigate navigator;

    public QuarryTask(EntityLiving entity)
    {
        super(entity);
        navigator = entity.getNavigator();
    }

    @Override
    public float getPriority ()
    {
        return 100;
    }

    @Override
    public void startTask ()
    {
        home = new CoordTuple(host.posX, host.posY, host.posZ);
        World world = host.worldObj;
        world.setBlock(home.x - 2, home.y, home.z - 1, Block.workbench.blockID);
        world.setBlock(home.x - 2, home.y, home.z, Block.chest.blockID);
        world.setBlock(home.x - 2, home.y, home.z + 1, Block.chest.blockID);
        block = new CoordTuple(home.x + 1, home.y - 1, home.z);
    }

    @Override
    public boolean updateTask ()
    {
        tick++;
        if (tick == 20)
        {
            tick = 0;
            if (host.getDistance(block.x, block.y, block.z) < 2)
            {
                //System.out.println("Block within range");
                World world = host.worldObj;
                int blockID = world.getBlockId(block.x, block.y, block.z);
                Block b = Block.blocksList[blockID];
                if (b != null)
                {
                    world.playAuxSFXAtEntity(null, 2001, block.x, block.y, block.z, blockID + (world.getBlockMetadata(block.x, block.y, block.z) << 12));
                    world.setBlock(block.x, block.y, block.z, 0);
                }
                chooseNextBlock();
            }
            else
            {
                //System.out.println("Moving towards block");
                if (!navigator.tryMoveToXYZ(block.x, block.y, block.z, 0.25f))
                    chooseNextBlock();
            }
        }
        return true;
    }

    boolean chooseNextBlock ()
    {
        int end = reverseX ? 0 : 10;
        int offset = reverseX ? -1 : 1;
        int x = block.x - home.x;
        int y = block.y;
        int z = block.z - home.z;
        if (x + offset == end)
        {
            reverseX = !reverseX;

            end = reverseZ ? 0 : 10;
            offset = reverseZ ? -1 : 1;
            if (z + offset == end)
            {
                reverseZ = !reverseZ;
                y -= 1;
            }
            else
            {
                z += offset;
            }
        }
        else
        {
            x += offset;
        }

        //System.out.println("Current block:" + block);
        block = new CoordTuple(x + home.x, y, z + home.z);
        //System.out.println("New block:" + block);
        return true;
    }

    boolean withinRange (CoordTuple coord, double range)
    {
        return withinRange(coord.x, coord.y, coord.z, range);
    }

    boolean withinRange (int x, int y, int z, double range)
    {
        if (x - range > host.posX && x + 1 + range < host.posX)
            if (y - range > host.posY && y + 1 + range < host.posY)
                if (z - range > host.posZ && z + 1 + range < host.posZ)
                    return true;

        return false;
    }

}
