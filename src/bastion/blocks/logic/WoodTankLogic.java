package bastion.blocks.logic;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import tconstruct.common.TContent;
import tconstruct.library.blocks.AdaptiveInventoryLogic;
import tconstruct.library.component.IComponentHolder;
import tconstruct.library.component.LogicComponent;
import tconstruct.library.component.TankLayerScan;
import tconstruct.library.util.IMasterLogic;
import tconstruct.library.util.IServantLogic;

public class WoodTankLogic extends AdaptiveInventoryLogic implements IMasterLogic, IComponentHolder
{
    byte direction;
    boolean needsUpdate;

    TankLayerScan structure = new TankLayerScan(this, TContent.smeltery, TContent.lavaTank);

    @Override
    public byte getRenderDirection ()
    {
        return direction;
    }

    @Override
    public ForgeDirection getForgeDirection ()
    {
        return null;
    }

    @Override
    public void setDirection (int side)
    {
        direction = (byte) side;
    }

    @Override
    public void setDirection (float yaw, float pitch, EntityLivingBase player)
    {
        if (direction < 2)
        {
            int facing = MathHelper.floor_double((double) (yaw / 360) + 0.5D) & 3;
            switch (facing)
            {
            case 0:
                direction = 2;
                break;

            case 1:
                direction = 5;
                break;

            case 2:
                direction = 3;
                break;

            case 3:
                direction = 4;
                break;
            }
        }
    }

    @Override
    public Container getGuiContainer (InventoryPlayer inventoryplayer, World world, int x, int y, int z)
    {
        return null;
    }

    @Override
    protected String getDefaultName ()
    {
        return ""; //Not a gui block
    }

    public void readFromNBT (NBTTagCompound tags)
    {
        super.readFromNBT(tags);
        readCustomNBT(tags);
    }

    public void readCustomNBT (NBTTagCompound tags)
    {
        direction = tags.getByte("direction");
    }

    public void writeToNBT (NBTTagCompound tags)
    {
        super.writeToNBT(tags);
        writeCustomNBT(tags);
    }

    public void writeCustomNBT (NBTTagCompound tags)
    {
        tags.setByte("direction", direction);
    }

    @Override
    public Packet getDescriptionPacket ()
    {
        NBTTagCompound tag = new NBTTagCompound();
        writeCustomNBT(tag);
        return new Packet132TileEntityData(xCoord, yCoord, zCoord, 1, tag);
    }

    @Override
    public void onDataPacket (INetworkManager net, Packet132TileEntityData packet)
    {
        readCustomNBT(packet.data);
        worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
    }

    //@Override
    protected boolean isValidBlock (int x, int y, int z)
    {
        int blockID = worldObj.getBlockId(x, y, z);
        if (blockID == TContent.smeltery.blockID)
            return true;

        return false;
    }

    public void checkValidStructure ()
    {
        structure.checkValidStructure();
    }

    @Override
    public void notifyChange (IServantLogic servant, int x, int y, int z)
    {

    }

    @Override
    public List<LogicComponent> getComponents ()
    {
        ArrayList<LogicComponent> ret = new ArrayList<LogicComponent>(1);
        ret.add(structure);
        return ret;
    }

    @Override
    public void placeBlock (EntityLivingBase entity, ItemStack stack)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeBlock ()
    {
        // TODO Auto-generated method stub
        
    }
}
