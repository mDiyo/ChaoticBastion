package bastion.ai.task;

import net.minecraft.entity.EntityLiving;

public abstract class TaskBase
{
    public final EntityLiving host;
    
    public TaskBase(EntityLiving entity)
    {
        this.host = entity;
    }
    
    /** Determines how high of a priority this task has. Higher is better
     * 
     * @return priority level
     */
    public abstract float getPriority();
    /** Initial setup for this task. Only fires once 
     */
    public void startTask() {}
    /** Runs every tick
     * 
     * @return true to continue, false to end task
     */
    public boolean updateTask() { return true; }
    public void taskInterrupted() {}
    /** Cleanup for this task. Only fires once
     */
    public void endTask() {}
}
