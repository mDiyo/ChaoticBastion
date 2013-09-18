package bastion.entity.ai;

import bastion.entity.friendly.GolemBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.World;

public class AIBase extends EntityAIBase
{
    protected GolemBase golem;
    protected World world;

    public AIBase(GolemBase entity)
    {
        golem = entity;
        world = entity.worldObj;
    }

    @Override
    public boolean shouldExecute ()
    {
        return false;
    }
}
