package bastion;

import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import bastion.util.CEventHandler;
import bastion.util.CProxyCommon;
import bastion.util.OverworldAlterProvider;
import bastion.util.PHBastion;
import bastion.world.WorldTracker;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "ChaoticBastion", name = "Chaotic Bastion", version = "@VERSION@")
@NetworkMod(serverSideRequired = false, clientSideRequired = true)
public class ChaoticBastion
{
    /* Instance of this mod, used for grabbing prototype fields */
    @Instance("ChaoticBastion")
    public static ChaoticBastion instance;
    /* Proxies for sides, used for graphics processing */
    @SidedProxy(clientSide = "bastion.util.CProxyClient", serverSide = "bastion.util.CProxyCommon")
    public static CProxyCommon proxy;

    /* Define blocks, items, crucial info */
    @EventHandler
    public void preInit (FMLPreInitializationEvent event)
    {
        PHBastion.initProps(event.getModConfigurationDirectory());
        CContent.createBlocks();
        CContent.createItems();
        CContent.createEntities();
        CContent.createRecipes();
        CContent.createOtherContent();
    }

    /* Register blocks in the ore dictionary, other setup */
    @EventHandler
    public void init (FMLInitializationEvent event)
    {
        proxy.registerRendering();
        MinecraftForge.EVENT_BUS.register(new CEventHandler());
        GameRegistry.registerWorldGenerator(new WorldTracker());
        DimensionManager.unregisterProviderType(0);
        DimensionManager.registerProviderType(0, OverworldAlterProvider.class, true);
    }

    /* Cross-mod support */
    @EventHandler
    public void postInit (FMLPostInitializationEvent evt)
    {
        CContent.crossModCommunication();
    }
}
