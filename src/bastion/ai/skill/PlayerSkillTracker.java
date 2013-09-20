package bastion.ai.skill;

import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.IPlayerTracker;

public class PlayerSkillTracker implements IPlayerTracker
{

    @Override
    public void onPlayerLogin(EntityPlayer player)
    {
        SkillSet set = new SkillSet();
        set.readFromNBT(player);
        skillSets.put(player.username, set);
    }

    @Override
    public void onPlayerLogout(EntityPlayer player)
    {
        SkillSet set = skillSets.get(player.username);
        set.saveToNBT(player);
        skillSets.remove(player.username);
    }

    @Override
    public void onPlayerChangedDimension(EntityPlayer player)
    {
        
    }

    @Override
    public void onPlayerRespawn(EntityPlayer player)
    {
        
    }
    
    public ConcurrentHashMap<String, SkillSet> skillSets = new ConcurrentHashMap<String, SkillSet>();

    public SkillSet getSkillSet(String username)
    {
        return skillSets.get(username);
    }
}
