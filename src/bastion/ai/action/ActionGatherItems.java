package bastion.ai.action;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.pathfinding.PathNavigate;
import bastion.entity.friendly.GolemBase;

public class ActionGatherItems extends ActionBase
{
    private PathNavigate pathfinder;
    private EntityItem target;
    private List<EntityItem> itemList = new ArrayList<EntityItem>();
    private float speed;
    private boolean avoidsWater;
    private int resetCounter;

    public static double searchRange = 8.0D;
    public static double searchHeight = 2.0D;
    public static float canCollectRange = 10.0f;

    public ActionGatherItems(GolemBase golem)
    {
        super(golem);
        this.speed = golem.getSpeed() + 0.125f;
        this.pathfinder = golem.getNavigator();
        this.setMutexBits(3);
        resetCounter = 0;
    }

    //Path: Should execute (true) > startExecuting(once) > continueExecuting (true) > updateTask()
    //                                                     continueExecuting (false) > resetTask() > shouldExecute()
    //shouldExecute() and continueExecuting() happen every tick while true 

    @Override
    public boolean shouldExecute ()
    {
        resetCounter++;
        if (resetCounter >= 40)
        {
            resetCounter = 0;
            if (itemList.size() < 1)
            {
                itemList = (List<EntityItem>) (world.getEntitiesWithinAABB(EntityItem.class, golem.boundingBox.expand(searchRange, searchHeight, searchRange)));
            }
            if (target == null)
            {
                findNearestItem();
            }
        }

        if (target == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void startExecuting ()
    {
        /*findNearestItem();
        System.out.println("Starting task. Target: " + target);
        if (target != null)
            this.pathfinder.tryMoveToXYZ(target.posX, target.posY, target.posZ, this.speed);*/
    }

    void findNearestItem ()
    {
        for (EntityItem e : itemList)
        {
            if (target == null)
            {
                target = e;
            }
            else
            {
                if (golem.getDistanceSqToEntity(target) > golem.getDistanceSqToEntity(e))
                {
                    target = e;
                }
            }
        }

        System.out.println("Finding nearest item: "+target);
        if (target != null)
        {
            golem.setHasTask(true);
            this.pathfinder.tryMoveToXYZ(target.posX, target.posY, target.posZ, this.speed);
        }
        else
        {
            golem.setHasTask(false);
        }
    }

    @Override
    public boolean continueExecuting ()
    {
        return target != null;
    }

    @Override
    public void resetTask ()
    {
    }

    @Override
    public void updateTask ()
    {
        if (target == null)
            return;

        //System.out.println("U task. Target: " + target);
        if (!target.isEntityAlive())
        {
            itemList.remove(target);
            target = null;
            findNearestItem();
        }
    }
}
