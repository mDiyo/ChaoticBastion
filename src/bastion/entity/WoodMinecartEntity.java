package bastion.entity;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.minecart.MinecartInteractEvent;

public class WoodMinecartEntity extends EntityMinecart
{

    public WoodMinecartEntity(World world)
    {
        super(world);
    }
    
    public WoodMinecartEntity(World world, double x, double y, double z)
    {
        super(world, x, y, z);
    }

    @Override
    public boolean func_130002_c(EntityPlayer par1EntityPlayer)
    {
        if(MinecraftForge.EVENT_BUS.post(new MinecartInteractEvent(this, par1EntityPlayer))) 
        {
            return true;
        }
        if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != par1EntityPlayer)
        {
            return true;
        }
        else if (this.riddenByEntity != null && this.riddenByEntity != par1EntityPlayer)
        {
            return false;
        }
        else
        {
            if (!this.worldObj.isRemote)
            {
                par1EntityPlayer.mountEntity(this);
            }

            return true;
        }
    }

    @Override
    public int getMinecartType()
    {
        return 28940;
    }

    protected void applyDrag()
    {
        this.motionX *= 0.996999979019165D;
        this.motionY *= 0.0D;
        this.motionZ *= 0.996999979019165D;
    }

    public ItemStack getCartItem()
    {
        return null;
    }
    
    public boolean canBeRidden()
    {
        return true;
    }
}
