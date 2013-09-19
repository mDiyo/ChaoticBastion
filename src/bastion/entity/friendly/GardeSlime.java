package bastion.entity.friendly;

import java.util.HashMap;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import bastion.ChaoticBastion;
import bastion.entity.ai.AIFollowLeader;
import bastion.entity.ai.AISwim;
import bastion.entity.ai.GardeslimeTaskHandler;
import bastion.entity.ai.ITaskHandler;
import bastion.entity.ai.ITaskHandler.TaskSet;
import bastion.util.CNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GardeSlime extends GolemBase
{
    protected EntityAIBase[] currentTasks = new EntityAIBase[0];
    public GardeSlime(World world)
    {
        super(world);
        this.setSize(0.375F, 0.875F);
        initDefaultTasks();
    }

    private void initDefaultTasks()
    {
        this.tasks.addTask(1, new AISwim(this));
        this.tasks.addTask(10, new AIFollowLeader(this));
        
        ITaskHandler handler = new GardeslimeTaskHandler();
        registerTaskHandler(Item.axeWood, handler);
        registerTaskHandler(Item.axeStone, handler);
        registerTaskHandler(Item.axeIron, handler);
        registerTaskHandler(Item.axeDiamond, handler);
        registerTaskHandler(Item.axeDiamond, handler);
    }

    @Override
    public void setupInventory ()
    {
        inventory = new ItemStack[16]; //inventory + quiver
    }

    @SideOnly(Side.CLIENT)
    public float getShadowSize () //Opacity, not size
    {
        return 1.0F;
    }

    public boolean interact (EntityPlayer player)
    {
        if (getLeader() == null)
            setLeader(player);
        if (player.isSneaking())
        {
            if (!worldObj.isRemote)
                player.openGui(ChaoticBastion.instance, CNetworkHandler.miniGardyGui, this.worldObj, this.entityId, 0, 0);
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
    
    static HashMap<Item, ITaskHandler> taskHandlers = new HashMap<Item, ITaskHandler>();
    
    public static void registerTaskHandler(Item item, ITaskHandler handler)
    {
        if (item != null)
        {
            taskHandlers.put(item, handler);
        }
        else
        {
            System.err.println("Item cannot be null.");
        }
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
                for (int i = 0; i < newTasks.tasks.length; i++)
                {
                    this.tasks.addTask(newTasks.priority[i], newTasks.tasks[i]);
                }
                currentTasks = newTasks.tasks;
            }
        }
        else
        {
            currentTasks = new EntityAIBase[0];
        }
    }
}
