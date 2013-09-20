package bastion.ai.action;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.ItemStack;
import bastion.ai.task.TaskBase;

public interface ITaskHandler
{
    public ActionSet getActions(Entity entity, ItemStack stack);
    public TaskBase getToolTask(Entity entity, ItemStack stack);
    
    public class ActionSet
    {
        public final EntityAIBase[] tasks;
        public final Integer[] priority;
        
        public ActionSet(Integer[] priority, EntityAIBase[] tasks)
        {
            assert tasks.length == priority.length : "You need to pass two arrays of identical length";
            this.tasks = tasks;
            this.priority = priority;
        }
    }
    
    public static final ActionSet emptySet = new ActionSet(new Integer[0], new EntityAIBase[0]);
}
