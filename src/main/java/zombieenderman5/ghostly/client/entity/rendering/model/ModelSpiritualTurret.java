package zombieenderman5.ghostly.client.entity.rendering.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

/**
 * Created using Tabula 7.0.0
 * @author ZombieEnderman5
 */
public class ModelSpiritualTurret extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer leftArm1;
    public ModelRenderer leftArm2;
    public ModelRenderer rightArm1;
    public ModelRenderer rightArm2;
    public ModelRenderer antenna1;
    public ModelRenderer antenna2;
    public ModelRenderer antenna3;

    public ModelSpiritualTurret() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.leftArm2 = new ModelRenderer(this, 40, 16);
        this.leftArm2.mirror = true;
        this.leftArm2.setRotationPoint(5.0F, 7.0F, 0.0F);
        this.leftArm2.addBox(-1.0F, -2.0F, -2.0F, 4, 14, 4, 0.0F);
        this.setRotateAngle(leftArm2, -1.5707963267948966F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 20, 43);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-5.0F, -10.0F, -5.0F, 10, 10, 10, 0.0F);
        this.rightArm1 = new ModelRenderer(this, 40, 16);
        this.rightArm1.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.rightArm1.addBox(-3.0F, -2.0F, -2.0F, 4, 14, 4, 0.0F);
        this.setRotateAngle(rightArm1, -1.5707963267948966F, 0.0F, 0.0F);
        this.leg2 = new ModelRenderer(this, 22, 12);
        this.leg2.setRotationPoint(3.0F, 14.0F, 0.0F);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 16, 4, 0.0F);
        this.setRotateAngle(leg2, 0.0F, 0.0F, -0.9599310885968813F);
        this.rightArm2 = new ModelRenderer(this, 40, 16);
        this.rightArm2.setRotationPoint(-5.0F, 7.0F, 0.0F);
        this.rightArm2.addBox(-3.0F, -2.0F, -2.0F, 4, 14, 4, 0.0F);
        this.setRotateAngle(rightArm2, -1.5707963267948966F, 0.0F, 0.0F);
        this.leg4 = new ModelRenderer(this, 22, 12);
        this.leg4.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 16, 4, 0.0F);
        this.setRotateAngle(leg4, 0.9599310885968813F, 0.0F, 0.0F);
        this.antenna1 = new ModelRenderer(this, 3, 3);
        this.antenna1.setRotationPoint(0.0F, -9.0F, -3.0F);
        this.antenna1.addBox(-1.0F, -8.0F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(antenna1, 0.6981317007977318F, 0.0F, 0.0F);
        this.leftArm1 = new ModelRenderer(this, 40, 16);
        this.leftArm1.mirror = true;
        this.leftArm1.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.leftArm1.addBox(-1.0F, -2.0F, -2.0F, 4, 14, 4, 0.0F);
        this.setRotateAngle(leftArm1, -1.5707963267948966F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 30);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 16, 4, 0.0F);
        this.antenna2 = new ModelRenderer(this, 3, 3);
        this.antenna2.setRotationPoint(-3.0F, -9.0F, -3.0F);
        this.antenna2.addBox(-1.0F, -7.5F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(antenna2, 0.6981317007977318F, 0.0F, -0.6981317007977318F);
        this.leg3 = new ModelRenderer(this, 22, 12);
        this.leg3.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 16, 4, 0.0F);
        this.setRotateAngle(leg3, -0.9599310885968813F, 0.0F, 0.0F);
        this.antenna3 = new ModelRenderer(this, 3, 3);
        this.antenna3.setRotationPoint(3.0F, -9.0F, -3.0F);
        this.antenna3.addBox(-1.0F, -7.5F, -1.0F, 2, 8, 2, 0.0F);
        this.setRotateAngle(antenna3, 0.6981317007977318F, 0.0F, 0.6981317007977318F);
        this.leg1 = new ModelRenderer(this, 22, 12);
        this.leg1.setRotationPoint(-3.0F, 14.0F, 0.0F);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 16, 4, 0.0F);
        this.setRotateAngle(leg1, 0.0F, 0.0F, 0.9599310885968813F);
        this.head.addChild(this.antenna1);
        this.head.addChild(this.antenna2);
        this.head.addChild(this.antenna3);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) { 
        this.leftArm2.render(scale);
        this.head.render(scale);
        this.rightArm1.render(scale);
        this.leg2.render(scale);
        this.rightArm2.render(scale);
        this.leg4.render(scale);
        this.leftArm1.render(scale);
        this.body.render(scale);
        this.leg3.render(scale);
        this.leg1.render(scale);
    }

    @Override
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
    	
    	
    	
    }
    
    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
