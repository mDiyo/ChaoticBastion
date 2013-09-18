package bastion.util;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import tconstruct.TConstruct;
import tconstruct.blocks.logic.DrawbridgeLogic;
import tconstruct.blocks.logic.ToolForgeLogic;
import tconstruct.blocks.logic.ToolStationLogic;
import tconstruct.library.blocks.InventoryLogic;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class CPacketHandler implements IPacketHandler
{

    @Override
    public void onPacketData (INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        Side side = FMLCommonHandler.instance().getEffectiveSide();

        if (packet.channel.equals("ChaoticBastion"))
        {
            if (side == Side.SERVER)
                handleServerPacket(packet, (EntityPlayerMP) player);
            else
                handleClientPacket(packet, (EntityPlayer) player);
        }
    }

    void handleClientPacket (Packet250CustomPayload packet, EntityPlayer player)
    {
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

        byte packetID;

        try
        {
            packetID = inputStream.readByte();
        }
        catch (Exception e)
        {
            System.out.println("Failed at reading client packet for Chaotic Bastion.");
            e.printStackTrace();
            return;
        }
    }

    void handleServerPacket (Packet250CustomPayload packet, EntityPlayerMP player)
    {
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

        byte packetID;

        try
        {
            packetID = inputStream.readByte();

            
        }
        catch (IOException e)
        {
            System.out.println("Failed at reading server packet for Chaotic Bastion.");
            e.printStackTrace();
            return;
        }
    }

    Entity getEntity (World world, int id)
    {
        for (Object o : world.loadedEntityList)
        {
            if (((Entity) o).entityId == id)
                return (Entity) o;
        }
        return null;
    }
}
