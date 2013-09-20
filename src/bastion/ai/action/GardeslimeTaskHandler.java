package bastion.ai.action;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import bastion.ai.task.TaskBase;
import bastion.entity.friendly.Gardeslime;
import bastion.entity.friendly.GolemBase;
import bastion.util.IItemSucker;

public class GardeslimeTaskHandler implements ITaskHandler
{
    @Override
    public ActionSet getActions(Entity entity, ItemStack stack)
    {
        if (entity instanceof Gardeslime)
        {
            Item item = stack.getItem();
            if (item instanceof ItemAxe)
            {
                return new ActionSet(new Integer[] { 1 }, new EntityAIBase[] { new ActionChopTree((GolemBase) entity) });
            }
            if (item instanceof IItemSucker)
            {
                return new ActionSet(new Integer[] { 1 }, new EntityAIBase[] { new ActionGatherItems((GolemBase) entity) });                
            }
        }
        return ITaskHandler.emptySet;
    }

    @Override
    public TaskBase getToolTask (Entity entity, ItemStack stack)
    {
        if (entity instanceof Gardeslime)
        {
            Item item = stack.getItem();
            if (item instanceof ItemAxe)
            {
                
            }
            if (item instanceof IItemSucker)
            {
                       
            }
        }
        return null;
    }

}
