package bastion.util;

import java.io.ByteArrayOutputStream;

import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.PacketDispatcher;

public class CNetworkHandler
{


    public static void updateServer (ByteArrayOutputStream bos)
    {
        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = "TConstruct";
        packet.data = bos.toByteArray();
        packet.length = bos.size();

        PacketDispatcher.sendPacketToServer(packet);
    }
}
