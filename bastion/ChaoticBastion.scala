package bastion

import cpw.mods.fml.common.event.FMLInitializationEvent
import bastion.util.PHBastion
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.network.NetworkMod
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.common.MinecraftForge
import cpw.mods.fml.common.Mod.EventHandler
import bastion.util.CEventHandler


@Mod(modid = "ChaoticBastion", name = "Chaotic Bastion", version = "1.6.2_Test")
@NetworkMod(serverSideRequired = false, clientSideRequired = true)
object ChaoticBastion {
    /* Proxies for sides, used for graphics processing */
    //var proxy: CProxyCommon

    /* Define blocks, items, crucial info */
    @EventHandler
    def preInit(event: FMLPreInitializationEvent) {
        PHBastion.initProps(event.getModConfigurationDirectory())
        CContent.createBlocks()
        CContent.createItems()
        CContent.createRecipes()
        CContent.createOtherContent()
    }

    /* Register blocks in the ore dictionary, other setup */
    @EventHandler
    def init(event: FMLInitializationEvent) {
        //proxy.addNames()
        //proxy.registerRendering()
        MinecraftForge.EVENT_BUS.register(new CEventHandler())
    }

    /* Cross-mod support */
    @EventHandler
    def postInit(event: FMLPostInitializationEvent) {
        CContent.crossModCommunication()
    }
}
