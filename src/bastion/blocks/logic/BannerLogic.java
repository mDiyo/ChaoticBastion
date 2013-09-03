package bastion.blocks.logic;

import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class BannerLogic extends TileEntity
{
    public static final Potion[][] effectsList = new Potion[][] { { Potion.moveSpeed, Potion.digSpeed }, { Potion.resistance, Potion.jump }, { Potion.damageBoost }, { Potion.regeneration } };
    
    public int statusEffect;

    @Override
    public void updateEntity()
    {
        //if (worldObj.isRemote)
            //return;

        if (this.worldObj.getTotalWorldTime() % 80L == 0L)
        {
            this.addEffectsToPlayers();
        }
    }

    private void addEffectsToPlayers()
    {
        if (this.statusEffect > 0)
        {
            double range = (double) (8);
            byte level = 0;

            AxisAlignedBB axisalignedbb = AxisAlignedBB.getAABBPool()
                    .getAABB((double) this.xCoord, (double) this.yCoord, (double) this.zCoord, (double) (this.xCoord + 1), (double) (this.yCoord + 1), (double) (this.zCoord + 1))
                    .expand(range, range, range);
            axisalignedbb.maxY = (double) this.worldObj.getHeight();
            List list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, axisalignedbb); //Will allow for other mobs in the future
            Iterator iterator = list.iterator();
            EntityPlayer entityplayer;

            while (iterator.hasNext())
            {
                entityplayer = (EntityPlayer) iterator.next();
                entityplayer.addPotionEffect(new PotionEffect(this.statusEffect, 180, level, true));
            }
        }
    }
    
    public void setEffect(int e)
    {
        System.out.println("Setting status to "+e);
        this.statusEffect = e;
    }
    
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        this.statusEffect = par1NBTTagCompound.getInteger("Effect");
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("Effect", this.statusEffect);
    }
    
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bb = AxisAlignedBB.getAABBPool().getAABB(xCoord-1, yCoord-1, zCoord-1, xCoord + 1, yCoord + 1, zCoord + 1);
        return bb;
    }
}
