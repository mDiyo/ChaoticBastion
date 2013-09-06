package bastion.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class WoodMinecartModel extends ModelBase
{
    ModelRenderer Base;
    ModelRenderer Wheel1;
    ModelRenderer Wheel2;
    ModelRenderer Wheel3;
    ModelRenderer Wheel4;

    public WoodMinecartModel()
    {
        Base = new ModelRenderer(this, 0, 0);
        Base.addBox(-8F, 0F, -6F, 16, 2, 12);
        Base.setRotationPoint(0F, 4F, 0F);
        Base.mirror = true;
        setRotation(Base, 0F, 0F, 0F);
        Wheel1 = new ModelRenderer(this, 0, 14);
        Wheel1.addBox(-2F, -2F, 0F, 4, 4, 1);
        Wheel1.setRotationPoint(-6F, 5F, -7F);
        Wheel1.mirror = true;
        setRotation(Wheel1, 0F, 0F, 0F);
        Wheel2 = new ModelRenderer(this, 10, 14);
        Wheel2.addBox(-2F, -2F, 0F, 4, 4, 1);
        Wheel2.setRotationPoint(-5F, 5F, 6F);
        Wheel2.mirror = true;
        setRotation(Wheel2, 0F, 0F, 0F);
        Wheel3 = new ModelRenderer(this, 20, 14);
        Wheel3.addBox(-2F, -2F, 0F, 4, 4, 1);
        Wheel3.setRotationPoint(5F, 5F, -7F);
        Wheel3.mirror = true;
        setRotation(Wheel3, 0F, 0F, 0F);
        Wheel4 = new ModelRenderer(this, 30, 14);
        Wheel4.addBox(-2F, -2F, 0F, 4, 4, 1);
        Wheel4.setRotationPoint(5F, 5F, 6F);
        Wheel4.mirror = true;
        setRotation(Wheel4, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Base.render(f5);
        Wheel1.render(f5);
        Wheel2.render(f5);
        Wheel3.render(f5);
        Wheel4.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}
