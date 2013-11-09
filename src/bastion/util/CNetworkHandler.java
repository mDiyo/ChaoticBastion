package bastion.util;

import java.io.ByteArrayOutputStream;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import bastion.entity.friendly.Gardeslime;
import bastion.inventory.MiniGardyContainer;
import bastion.inventory.MiniGardyGui;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.PacketDispatcher;

public class CNetworkHandler implements IGuiHandler
{
    public static void updateServer (ByteArrayOutputStream bos)
    {
        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = "TConstruct";
        packet.data = bos.toByteArray();
        packet.length = bos.size();

        PacketDispatcher.sendPacketToServer(packet);
    }
    
    public static int miniGardyGui = 1;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == miniGardyGui)
        {
            for (Object o : world.loadedEntityList)
            {
                Entity entity = (Entity) o;
                if (entity.entityId == x)
                {
                    return new MiniGardyContainer(player.inventory, (Gardeslime) entity);
                }
            }
            return null;
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == miniGardyGui)
        {
            for (Object o : world.loadedEntityList)
            {
                Entity entity = (Entity) o;
                if (entity.entityId == x)
                {
                    return new MiniGardyGui(player.inventory, (Gardeslime) entity);
                }
            }
        }
        return null;
    }
}
