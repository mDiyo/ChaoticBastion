package bastion.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class BannerModel extends ModelBase
{
    ModelRenderer Strut;
    ModelRenderer Flag;
    ModelRenderer Overlay;

    public BannerModel()
    {
        textureWidth = 64;
        textureHeight = 64;

        Strut = new ModelRenderer(this, 0, 0);
        Strut.addBox(-2F, 0F, -8F, 4, 4, 16);
        Strut.setRotationPoint(0F, -8F, 0F);
        Strut.setTextureSize(64, 64);
        Strut.mirror = true;
        //setRotation(Strut, 0F, 0.7853982F, 0F);
        Flag = new ModelRenderer(this, 0, 20);
        Flag.addBox(-3F, 0F, -8F, 1, 20, 16);
        Flag.setRotationPoint(0F, -8F, 0F);
        Flag.setTextureSize(64, 64);
        Flag.mirror = true;
        Overlay = new ModelRenderer(this, 34, 20);
        Overlay.addBox(-3F, 0F, -8F, 1, 20, 16);
        Overlay.setRotationPoint(0F, -8F, 0F);
        Overlay.mirror = true;
    }

    public void renderBanner(float f5)
    {
        Strut.render(f5);
        Flag.render(f5);
        Overlay.render(f5);
    }

}
