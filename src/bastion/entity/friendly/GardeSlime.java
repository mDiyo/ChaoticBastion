package bastion.entity.friendly;

import java.util.HashMap;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tconstruct.TConstruct;
import tconstruct.common.TProxyCommon;
import bastion.entity.ai.*;
import bastion.entity.ai.ITaskHandler.TaskSet;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GardeSlime extends GolemBase
{
    protected EntityAIBase[] currentTasks;
    public GardeSlime(World world)
    {
        super(world);
        this.setSize(0.375F, 0.875F);
        this.tasks.addTask(1, new AISwim(this));
        this.tasks.addTask(10, new AIFollowLeader(this));
        initDefaultTasks();
    }

    private void initDefaultTasks()
    {
        ITaskHandler handler = new GardeslimeTaskHandler();
        registerTaskHandler(Item.axeWood.itemID, handler);
        registerTaskHandler(Item.axeStone.itemID, handler);
        registerTaskHandler(Item.axeIron.itemID, handler);
        registerTaskHandler(Item.axeDiamond.itemID, handler);
        registerTaskHandler(Item.axeDiamond.itemID, handler);
    }

    @Override
    public void setupInventory ()
    {
        inventory = new ItemStack[14];
    }

    @SideOnly(Side.CLIENT)
    public float getShadowSize () //Opacity, not size
    {
        return 1.0F;
    }

    public boolean interact (EntityPlayer player)
    {
        if (player.isSneaking())
        {
            if (!worldObj.isRemote)
                player.openGui(TConstruct.instance, TProxyCommon.miniGardyGui, this.worldObj, this.entityId, 0, 0);
            return true;
            //return false;
        }
        else
        {
            ItemStack stack = this.getHeldItem();
            if (stack == null)
            {
                ItemStack playerStack = player.getCurrentEquippedItem();
                if (playerStack != null)
                {
                    this.setCurrentItemOrArmor(0, playerStack.copy());
                    updateItemAI(playerStack);
                    player.destroyCurrentEquippedItem();
                }
            }
            else
            {
                if (player.inventory.addItemStackToInventory(stack.copy()))
                {
                    //this.worldObj.playSoundAtEntity(this, par1Str, par2, par3);
                    this.worldObj.playSoundAtEntity(player, "random.pop", 0.3F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                    this.setCurrentItemOrArmor(0, null);
                    updateItemAI(null);
                }
            }
            return true;
        }
    }
    
    static HashMap<Integer, ITaskHandler> taskHandlers = new HashMap<Integer, ITaskHandler>();
    
    public static void registerTaskHandler(ItemStack item, ITaskHandler handler)
    {
        if (item != null)
        {
            taskHandlers.put(item.itemID, handler);
        }
        else
        {
            System.err.println("Itemstack cannot be null.");
        }
    }
    
    private static void registerTaskHandler(int itemID, ITaskHandler handler)
    {
        taskHandlers.put(itemID, handler);        
    }

    void updateItemAI(ItemStack stack)
    {
        for (EntityAIBase task : currentTasks)
        {
            this.tasks.removeTask(task);
        }
        
        if (stack != null)
        {
            ITaskHandler handler = taskHandlers.get(stack.itemID);
            if (handler != null)
            {
                TaskSet newTasks = handler.getTasks(this, stack);
            }
        }
        else
        {
            currentTasks = new EntityAIBase[0];
        }
    }
}
