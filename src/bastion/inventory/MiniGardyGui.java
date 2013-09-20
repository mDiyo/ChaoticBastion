package bastion.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import bastion.entity.friendly.GardeSlime;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MiniGardyGui extends GuiContainer
{
    public GardeSlime gardy;

    public MiniGardyGui(InventoryPlayer inventoryplayer, GardeSlime gardy)
    {
        super(new MiniGardyContainer(inventoryplayer, gardy));
        this.gardy = gardy;
    }

    protected void drawGuiContainerForegroundLayer (int par1, int par2)
    {
        fontRenderer.drawString(gardy.getEntityName(), 8, 6, 0x404040);
        fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, (ySize - 96) + 4, 0x404040);
    }

    private static final ResourceLocation background = new ResourceLocation("chaoticbastion", "textures/gui/googirl.png");

    protected void drawGuiContainerBackgroundLayer (float f, int i, int j)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(background);
        int cornerX = (width - xSize) / 2;
        int cornerY = (height - ySize) / 2;
        drawTexturedModalRect(cornerX, cornerY, 0, 0, xSize, ySize);

        ItemStack equip = gardy.getCurrentItemOrArmor(0);
        if (equip == null) //Equip
            drawTexturedModalRect(cornerX + 7, cornerY + 35, xSize + 48, 4, 22, 22);

        if (gardy.getCurrentItemOrArmor(1) == null) //Helmet
            drawTexturedModalRect(cornerX + 25, cornerY + 17, xSize + 4, 4, 22, 22);

        if (gardy.getCurrentItemOrArmor(2) == null) //Chestplate
            drawTexturedModalRect(cornerX + 25, cornerY + 35, xSize + 4, 26, 22, 22);

        if (gardy.getCurrentItemOrArmor(3) == null) //Leggings
            drawTexturedModalRect(cornerX + 25, cornerY + 53, xSize + 4, 48, 22, 22);

        /*if (gardy.getCurrentItemOrArmor(4) == null) //Other
        {
            if (equip == null)
            {

            }
            else if (equip.getItem() instanceof ItemBow)
                drawTexturedModalRect(cornerX + 43, cornerY + 35, xSize + 26, 26, 22, 22);
        }*/
    }
}
