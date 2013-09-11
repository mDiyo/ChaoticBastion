package bastion.client.entity;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

import org.lwjgl.opengl.GL11;

import bastion.client.model.WoodMinecartModel;
import bastion.entity.WoodMinecartEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class WoodMinecartRender extends Render
{
    private static final ResourceLocation field_110804_g = new ResourceLocation("chaoticbastion", "textures/entity/woodminecart.png");

    /** instance of ModelMinecart for rendering */
    protected ModelBase modelMinecart = new WoodMinecartModel();
    protected final RenderBlocks field_94145_f;

    public WoodMinecartRender()
    {
        this.shadowSize = 0.5F;
        this.field_94145_f = new RenderBlocks();
    }

    /**
     * Renders the Minecart.
     */
    public void renderTheMinecart(WoodMinecartEntity par1WoodMinecartEntity, double par2, double par4, double par6, float par8, float par9)
    {
        GL11.glPushMatrix();
        this.bindEntityTexture(par1WoodMinecartEntity);
        long i = (long)par1WoodMinecartEntity.entityId * 493286711L;
        i = i * i * 4392167121L + i * 98761L;
        float f2 = (((float)(i >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float f3 = (((float)(i >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float f4 = (((float)(i >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        GL11.glTranslatef(f2, f3, f4);
        double d3 = par1WoodMinecartEntity.lastTickPosX + (par1WoodMinecartEntity.posX - par1WoodMinecartEntity.lastTickPosX) * (double)par9;
        double d4 = par1WoodMinecartEntity.lastTickPosY + (par1WoodMinecartEntity.posY - par1WoodMinecartEntity.lastTickPosY) * (double)par9;
        double d5 = par1WoodMinecartEntity.lastTickPosZ + (par1WoodMinecartEntity.posZ - par1WoodMinecartEntity.lastTickPosZ) * (double)par9;
        double d6 = 0.30000001192092896D;
        Vec3 vec3 = par1WoodMinecartEntity.func_70489_a(d3, d4, d5);
        float f5 = par1WoodMinecartEntity.prevRotationPitch + (par1WoodMinecartEntity.rotationPitch - par1WoodMinecartEntity.prevRotationPitch) * par9;

        if (vec3 != null)
        {
            Vec3 vec31 = par1WoodMinecartEntity.func_70495_a(d3, d4, d5, d6);
            Vec3 vec32 = par1WoodMinecartEntity.func_70495_a(d3, d4, d5, -d6);

            if (vec31 == null)
            {
                vec31 = vec3;
            }

            if (vec32 == null)
            {
                vec32 = vec3;
            }

            par2 += vec3.xCoord - d3;
            par4 += (vec31.yCoord + vec32.yCoord) / 2.0D - d4;
            par6 += vec3.zCoord - d5;
            Vec3 vec33 = vec32.addVector(-vec31.xCoord, -vec31.yCoord, -vec31.zCoord);

            if (vec33.lengthVector() != 0.0D)
            {
                vec33 = vec33.normalize();
                par8 = (float)(Math.atan2(vec33.zCoord, vec33.xCoord) * 180.0D / Math.PI);
                f5 = (float)(Math.atan(vec33.yCoord) * 73.0D);
            }
        }

        GL11.glTranslatef((float)par2, (float)par4, (float)par6);
        GL11.glRotatef(180.0F - par8, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-f5, 0.0F, 0.0F, 1.0F);
        float f6 = (float)par1WoodMinecartEntity.getRollingAmplitude() - par9;
        float f7 = par1WoodMinecartEntity.getDamage() - par9;

        if (f7 < 0.0F)
        {
            f7 = 0.0F;
        }

        if (f6 > 0.0F)
        {
            GL11.glRotatef(MathHelper.sin(f6) * f6 * f7 / 10.0F * (float)par1WoodMinecartEntity.getRollingDirection(), 1.0F, 0.0F, 0.0F);
        }

        int j = par1WoodMinecartEntity.getDisplayTileOffset();
        Block block = par1WoodMinecartEntity.getDisplayTile();
        int k = par1WoodMinecartEntity.getDisplayTileData();

        if (block != null)
        {
            GL11.glPushMatrix();
            this.bindTexture(TextureMap.locationBlocksTexture);
            float f8 = 0.75F;
            GL11.glScalef(f8, f8, f8);
            GL11.glTranslatef(0.0F, (float)j / 16.0F, 0.0F);
            this.renderBlockInMinecart(par1WoodMinecartEntity, par9, block, k);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.bindEntityTexture(par1WoodMinecartEntity);
        }

        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        this.modelMinecart.render(par1WoodMinecartEntity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    protected ResourceLocation func_110803_a(WoodMinecartEntity par1WoodMinecartEntity)
    {
        return field_110804_g;
    }

    /**
     * Renders the block that is inside the minecart.
     */
    protected void renderBlockInMinecart(WoodMinecartEntity par1WoodMinecartEntity, float par2, Block par3Block, int par4)
    {
        float f1 = par1WoodMinecartEntity.getBrightness(par2);
        GL11.glPushMatrix();
        this.field_94145_f.renderBlockAsItem(par3Block, par4, f1);
        GL11.glPopMatrix();
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.func_110803_a((WoodMinecartEntity)par1Entity);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderTheMinecart((WoodMinecartEntity)par1Entity, par2, par4, par6, par8, par9);
    }
}
