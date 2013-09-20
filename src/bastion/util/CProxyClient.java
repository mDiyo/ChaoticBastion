package bastion.util;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;

import org.lwjgl.opengl.GL11;

import bastion.ChaoticBastion;
import bastion.ai.skill.Skill;
import bastion.ai.skill.SkillSet;
import bastion.blocks.logic.BannerLogic;
import bastion.client.block.BannerSpecialRender;
import bastion.client.block.CrystalBlockRender;
import bastion.client.entity.CrystalGuardianRender;
import bastion.client.entity.MiniGardyRender;
import bastion.client.entity.SlimeCloneRender;
import bastion.client.entity.WoodMinecartRender;
import bastion.client.model.CloneHeadModel;
import bastion.entity.WoodMinecartEntity;
import bastion.entity.friendly.Automaton;
import bastion.entity.friendly.GardeSlime;
import bastion.entity.friendly.SlimeClone;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class CProxyClient extends CProxyCommon
{

    public void registerRendering()
    {
        registerBlocks();
        registerEntities();
        MinecraftForge.EVENT_BUS.register(this);
    }

    void registerBlocks()
    {
        RenderingRegistry.registerBlockHandler(new CrystalBlockRender());
        ClientRegistry.bindTileEntitySpecialRenderer(BannerLogic.class, new BannerSpecialRender());
    }

    void registerEntities()
    {
        WoodMinecartRender minecart = new WoodMinecartRender();
        RenderingRegistry.registerEntityRenderingHandler(WoodMinecartEntity.class, minecart);
        RenderingRegistry.registerEntityRenderingHandler(GardeSlime.class, new MiniGardyRender());
        RenderingRegistry.registerEntityRenderingHandler(Automaton.class, new CrystalGuardianRender());
        RenderingRegistry.registerEntityRenderingHandler(SlimeClone.class, new SlimeCloneRender(new CloneHeadModel(0), new CloneHeadModel(1f), 0.25F));
    }

    public static void renderStandardInvBlock(RenderBlocks renderblocks, Block block, int meta)
    {
        Tessellator tessellator = Tessellator.instance;
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1F, 0.0F);
        renderblocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, meta));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderblocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(1, meta));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1F);
        renderblocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(2, meta));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderblocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(3, meta));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1F, 0.0F, 0.0F);
        renderblocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(4, meta));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderblocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(5, meta));
        tessellator.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }

    Minecraft mc = Minecraft.getMinecraft();

    /* HUD */
    @ForgeSubscribe
    public void renderHealthbar(RenderGameOverlayEvent.Post event)
    {
        ScaledResolution scaledresolution = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        int scaledWidth = scaledresolution.getScaledWidth();
        int scaledHeight = scaledresolution.getScaledHeight();
        int xBasePos = scaledWidth / 2 - 91;
        int yBasePos = scaledHeight - 39;
        SkillSet stats = ChaoticBastion.skillTracker.getSkillSet(mc.thePlayer.username);
        if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR)
        {
            if (stats == null)
                return;

            int amount = 0;
            GL11.glScalef(1 / 16f, 1 / 16f, 1 / 16f);
            for (Skill skill : stats.hotbar)
            {
                if (skill != null)
                {
                    if (!skill.getActive())
                        GL11.glColor4f(0.5f, 0.5f, 0.5f, 1.0F);
                    this.mc.renderEngine.bindTexture(skill.getTextureFile(scaledresolution.getScaleFactor()));
                    this.drawTexturedModalRect((2 + amount * 18) * 16, 32, 0, 0, 256, 256);
                }
                amount++;
            }
            GL11.glScalef(16f, 16f, 16f);
        }
    }

    double zLevel = 0;

    public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double) (par1 + 0), (double) (par2 + par6), (double) this.zLevel, (double) ((float) (par3 + 0) * f), (double) ((float) (par4 + par6) * f1));
        tessellator.addVertexWithUV((double) (par1 + par5), (double) (par2 + par6), (double) this.zLevel, (double) ((float) (par3 + par5) * f), (double) ((float) (par4 + par6) * f1));
        tessellator.addVertexWithUV((double) (par1 + par5), (double) (par2 + 0), (double) this.zLevel, (double) ((float) (par3 + par5) * f), (double) ((float) (par4 + 0) * f1));
        tessellator.addVertexWithUV((double) (par1 + 0), (double) (par2 + 0), (double) this.zLevel, (double) ((float) (par3 + 0) * f), (double) ((float) (par4 + 0) * f1));
        tessellator.draw();
    }
}
