package bastion.ai.action;

import bastion.entity.friendly.GolemBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.World;

public class ActionBase extends EntityAIBase
{
    protected GolemBase golem;
    protected World world;

    public ActionBase(GolemBase entity)
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
