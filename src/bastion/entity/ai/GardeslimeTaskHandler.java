package bastion.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import bastion.entity.friendly.GardeSlime;
import bastion.entity.friendly.GolemBase;

public class GardeslimeTaskHandler implements ITaskHandler
{
    @Override
    public TaskSet getTasks(Entity entity, ItemStack stack)
    {
        if (entity instanceof GardeSlime)
        {
            Item item = stack.getItem();
            if (item instanceof ItemAxe)
            {
                return new TaskSet(new Integer[] { 1 }, new EntityAIBase[] { new AIChopTree((GolemBase) entity) });
            }
        }
        return ITaskHandler.emptySet;
    }

}
