package bastion.ai.skill;

import java.util.ArrayList;
import java.util.HashSet;

import net.minecraft.entity.player.EntityPlayer;

public class SkillSet
{
    public ArrayList<Skill> hotbar = new ArrayList<Skill>(20);
    public HashSet<Skill> skills = new HashSet<Skill>();
    
    public void readFromNBT(EntityPlayer player)
    {
        // TODO Auto-generated method stub
        
    }

    public void saveToNBT(EntityPlayer player)
    {
        // TODO Auto-generated method stub
        
    }
}
