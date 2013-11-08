// Date: 11/8/2013 9:50:48 AM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package dark.mining.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelMiningLaserGun extends ModelBase
{
    //fields
    ModelRenderer body2;
    ModelRenderer guard;
    ModelRenderer body6;
    ModelRenderer chamber;
    ModelRenderer chamber2;
    ModelRenderer battery;
    ModelRenderer batteryClip;
    ModelRenderer batteryClip2;
    ModelRenderer body;
    ModelRenderer body3;
    ModelRenderer body4;
    ModelRenderer barrel;
    ModelRenderer grip2;
    ModelRenderer barrel2;
    ModelRenderer screen;

    public ModelMiningLaserGun()
    {
        textureWidth = 64;
        textureHeight = 32;

        body2 = new ModelRenderer(this, 0, 0);
        body2.addBox(0F, 0F, 0F, 2, 1, 5);
        body2.setRotationPoint(0F, 18F, -3F);
        body2.setTextureSize(64, 32);
        body2.mirror = true;
        setRotation(body2, 0F, 0F, 0F);
        guard = new ModelRenderer(this, 1, 26);
        guard.addBox(0F, 0F, 0F, 1, 2, 1);
        guard.setRotationPoint(0.5F, 19F, -2F);
        guard.setTextureSize(64, 32);
        guard.mirror = true;
        setRotation(guard, 0.4014257F, 0F, 0F);
        body6 = new ModelRenderer(this, 0, 0);
        body6.addBox(-1.5F, 0F, 0F, 3, 2, 1);
        body6.setRotationPoint(1F, 16.1F, 1F);
        body6.setTextureSize(64, 32);
        body6.mirror = true;
        setRotation(body6, 0F, 0F, 0F);
        chamber = new ModelRenderer(this, 17, 7);
        chamber.addBox(-1.5F, 0F, 0F, 3, 3, 13);
        chamber.setRotationPoint(1F, 15.8F, -11.01F);
        chamber.setTextureSize(64, 32);
        chamber.mirror = true;
        setRotation(chamber, 0F, 0F, 0F);
        chamber2 = new ModelRenderer(this, 17, 7);
        chamber2.addBox(0F, 0F, 0F, 2, 1, 13);
        chamber2.setRotationPoint(0F, 15.6F, -11.01F);
        chamber2.setTextureSize(64, 32);
        chamber2.mirror = true;
        setRotation(chamber2, 0F, 0F, 0F);
        battery = new ModelRenderer(this, 17, 22);
        battery.addBox(-1.5F, 0F, 0F, 2, 4, 5);
        battery.setRotationPoint(-1F, 16F, -6.5F);
        battery.setTextureSize(64, 32);
        battery.mirror = true;
        setRotation(battery, 0F, 0F, 0F);
        batteryClip = new ModelRenderer(this, 17, 0);
        batteryClip.addBox(-1.5F, 0F, 0F, 2, 1, 1);
        batteryClip.setRotationPoint(-0.4F, 16.5F, -2F);
        batteryClip.setTextureSize(64, 32);
        batteryClip.mirror = true;
        setRotation(batteryClip, 0F, 0F, 0F);
        batteryClip2 = new ModelRenderer(this, 17, 0);
        batteryClip2.addBox(-1.5F, 0F, 0F, 2, 1, 1);
        batteryClip2.setRotationPoint(-0.4F, 16.5F, -7F);
        batteryClip2.setTextureSize(64, 32);
        batteryClip2.mirror = true;
        setRotation(batteryClip2, 0F, 0F, 0F);
        body = new ModelRenderer(this, 17, 7);
        body.addBox(-1.5F, 0F, 0F, 2, 1, 8);
        body.setRotationPoint(1.5F, 18.8F, -15.01F);
        body.setTextureSize(64, 32);
        body.mirror = true;
        setRotation(body, 0F, 0F, 0F);
        body3 = new ModelRenderer(this, 17, 7);
        body3.addBox(-1.5F, 0F, 0F, 1, 2, 8);
        body3.setRotationPoint(0.5F, 17F, -15.01F);
        body3.setTextureSize(64, 32);
        body3.mirror = true;
        setRotation(body3, 0F, 0F, 0F);
        body4 = new ModelRenderer(this, 17, 7);
        body4.addBox(-1.5F, 0F, 0F, 1, 2, 8);
        body4.setRotationPoint(3.5F, 17F, -15.01F);
        body4.setTextureSize(64, 32);
        body4.mirror = true;
        setRotation(body4, 0F, 0F, 0F);
        barrel = new ModelRenderer(this, 17, 7);
        barrel.addBox(0F, 0F, 0F, 2, 2, 6);
        barrel.setRotationPoint(0F, 16.8F, -17.01F);
        barrel.setTextureSize(64, 32);
        barrel.mirror = true;
        setRotation(barrel, 0F, 0F, 0F);
        grip2 = new ModelRenderer(this, 17, 7);
        grip2.addBox(-1.5F, 0F, 0F, 3, 1, 1);
        grip2.setRotationPoint(4.5F, 17.58F, -12.01F);
        grip2.setTextureSize(64, 32);
        grip2.mirror = true;
        setRotation(grip2, 0F, 0F, 0F);
        barrel2 = new ModelRenderer(this, 17, 7);
        barrel2.addBox(0F, 0F, 0F, 1, 3, 8);
        barrel2.setRotationPoint(0.5F, 16F, -19.01F);
        barrel2.setTextureSize(64, 32);
        barrel2.mirror = true;
        setRotation(barrel2, 0F, 0F, 0F);
        screen = new ModelRenderer(this, 17, 7);
        screen.addBox(0F, 0F, 0F, 3, 3, 1);
        screen.setRotationPoint(2.5F, 16F, -8.01F);
        screen.setTextureSize(64, 32);
        screen.mirror = true;
        setRotation(screen, 0.5410521F, 0F, 0F);
    }

    public void render(float f5)
    {
        body2.render(f5);
        guard.render(f5);
        body6.render(f5);
        chamber.render(f5);
        chamber2.render(f5);
        battery.render(f5);
        batteryClip.render(f5);
        batteryClip2.render(f5);
        body.render(f5);
        body3.render(f5);
        body4.render(f5);
        barrel.render(f5);
        grip2.render(f5);
        barrel.render(f5);
        screen.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

}
