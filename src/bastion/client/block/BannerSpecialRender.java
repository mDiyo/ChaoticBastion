package bastion.client.block;

import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import bastion.blocks.logic.BannerLogic;
import bastion.client.model.BannerModel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BannerSpecialRender extends TileEntitySpecialRenderer
{
    private static final ResourceLocation field_110638_a = new ResourceLocation("chaoticbastion", "textures/entity/banner.png");

    /** The ModelSign instance used by the TileEntitySignRenderer */
    private final BannerModel modelSign = new BannerModel();

    public void renderTileEntitySignAt(BannerLogic par1TileEntitySign, double par2, double par4, double par6, float par8)
    {
        Block block = par1TileEntitySign.getBlockType();
        GL11.glPushMatrix();
        float f1 = 1F;
        float f2;

        //if (block == Block.signPost)
        //{
            GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 0.5F * f1, (float)par6 + 0.5F);
            float f3 = (float)(par1TileEntitySign.getBlockMetadata() * 360) / 16.0F;
            GL11.glRotatef(-f3, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(90, 0.0F, 1.0F, 0.0F);
            //this.modelSign.signStick.showModel = true;
        /*}
        else
        {
            int i = par1TileEntitySign.getBlockMetadata();
            f2 = 0.0F;

            if (i == 2)
            {
                f2 = 180.0F;
            }

            if (i == 4)
            {
                f2 = 90.0F;
            }

            if (i == 5)
            {
                f2 = -90.0F;
            }

            GL11.glTranslatef((float)par2 + 0.5F, (float)par4 + 0.75F * f1, (float)par6 + 0.5F);
            GL11.glRotatef(-f2, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(0.0F, -0.3125F, -0.4375F);
            this.modelSign.signStick.showModel = false;
        }*/

        this.func_110628_a(field_110638_a);
        GL11.glPushMatrix();
        GL11.glScalef(f1, -f1, -f1);
        this.modelSign.renderBanner(0.0625f);
        GL11.glPopMatrix();
        FontRenderer fontrenderer = this.getFontRenderer();
        f2 = 0.016666668F * f1;
        GL11.glTranslatef(0.0F, 0.5F * f1, 0.07F * f1);
        GL11.glScalef(f2, -f2, f2);
        GL11.glNormal3f(0.0F, 0.0F, -1.0F * f2);
        GL11.glDepthMask(false);
        byte b0 = 0;

        /*for (int j = 0; j < par1TileEntitySign.signText.length; ++j)
        {
            String s = par1TileEntitySign.signText[j];

            if (j == par1TileEntitySign.lineBeingEdited)
            {
                s = "> " + s + " <";
                fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, j * 10 - par1TileEntitySign.signText.length * 5, b0);
            }
            else
            {
                fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, j * 10 - par1TileEntitySign.signText.length * 5, b0);
            }
        }*/

        GL11.glDepthMask(true);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderTileEntitySignAt((BannerLogic)par1TileEntity, par2, par4, par6, par8);
    }
}
