package bastion.util;

import bastion.blocks.logic.*;
import bastion.client.block.*;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class CProxyClient extends CProxyCommon
{

    public void registerRendering ()
    {
        RenderingRegistry.registerBlockHandler(new CrystalBlockRender());
        ClientRegistry.bindTileEntitySpecialRenderer(BannerLogic.class, new BannerSpecialRender());
    }
}
