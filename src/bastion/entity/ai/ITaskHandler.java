package bastion.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.ItemStack;

public interface ITaskHandler
{
    public TaskSet getTasks(Entity entity, ItemStack stack);
    
    public class TaskSet
    {
        public final EntityAIBase[] tasks;
        public final Integer[] priority;
        
        public TaskSet(Integer[] priority, EntityAIBase[] tasks)
        {
            assert tasks.length == priority.length : "You need to pass two arrays of identical length";
            this.tasks = tasks;
            this.priority = priority;
        }
    }
    
    public static final TaskSet emptySet = new TaskSet(new Integer[0], new EntityAIBase[0]);
}
