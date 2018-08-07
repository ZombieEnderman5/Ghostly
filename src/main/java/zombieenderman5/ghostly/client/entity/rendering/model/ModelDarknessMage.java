package zombieenderman5.ghostly.client.entity.rendering.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.AbstractIllager;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import zombieenderman5.ghostly.common.entity.monster.EntityDarknessMage;
import zombieenderman5.ghostly.common.entity.monster.EntityDarknessMage.DarknessMageArmPose;

/**
 * Created using Tabula 7.0.0
 * @author ZombieEnderman5
 */
public class ModelDarknessMage extends ModelBiped {
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer bipedHeadwear;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedBody;
    public ModelRenderer bipedHead;
    public ModelRenderer claw3;
    public ModelRenderer claw4;
    public ModelRenderer claw1;
    public ModelRenderer claw2;

    public ModelDarknessMage() {
        this.textureWidth = 70;
        this.textureHeight = 64;
        this.bipedRightArm = new ModelRenderer(this, 50, 20);
        this.bipedRightArm.setRotationPoint(-7.0F, -13.5F, 0.0F);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 6, 20, 4, 0.0F);
        this.setRotateAngle(bipedRightArm, 0.0F, 0.0F, 0.10000736613927509F);
        this.bipedBody = new ModelRenderer(this, 20, 20);
        this.bipedBody.setRotationPoint(0.0F, -16.0F, 0.0F);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 20, 4, 0.0F);
        this.claw3 = new ModelRenderer(this, 5, 50);
        this.claw3.setRotationPoint(-1.9F, 17.5F, -1.0F);
        this.claw3.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(claw3, 0.0F, 0.0F, 0.02617993877991494F);
        this.bipedLeftArm = new ModelRenderer(this, 50, 20);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.setRotationPoint(5.0F, -13.5F, 0.0F);
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 6, 20, 4, 0.0F);
        this.setRotateAngle(bipedLeftArm, 0.0F, 0.0F, -0.10000736613927509F);
        this.claw1 = new ModelRenderer(this, 5, 50);
        this.claw1.setRotationPoint(0.1F, 17.8F, -1.0F);
        this.claw1.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(claw1, 0.0F, 0.0F, -0.02617993877991494F);
        this.bipedLeftLeg = new ModelRenderer(this, 0, 20);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.setRotationPoint(1.9F, 4.0F, 0.1F);
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 20, 4, 0.0F);
        this.claw4 = new ModelRenderer(this, 5, 50);
        this.claw4.mirror = true;
        this.claw4.setRotationPoint(1.9F, 17.5F, -1.0F);
        this.claw4.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(claw4, 0.0F, 0.0F, 0.02617993877991494F);
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.setRotationPoint(0.0F, -16.0F, 0.0F);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.bipedRightLeg = new ModelRenderer(this, 0, 20);
        this.bipedRightLeg.setRotationPoint(-1.9F, 4.0F, 0.1F);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 20, 4, 0.0F);
        this.bipedHeadwear = new ModelRenderer(this, 40, 0);
        this.bipedHeadwear.setRotationPoint(0.0F, -16.0F, 0.0F);
        this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
        this.claw2 = new ModelRenderer(this, 5, 50);
        this.claw2.mirror = true;
        this.claw2.setRotationPoint(3.9F, 17.8F, -1.0F);
        this.claw2.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(claw2, 0.0F, 0.0F, -0.02617993877991494F);
        this.bipedRightArm.addChild(this.claw3);
        this.bipedLeftArm.addChild(this.claw1);
        this.bipedRightArm.addChild(this.claw4);
        this.bipedLeftArm.addChild(this.claw2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.bipedRightArm.render(f5);
        this.bipedBody.render(f5);
        this.bipedLeftArm.render(f5);
        this.bipedLeftLeg.render(f5);
        this.bipedHead.render(f5);
        this.bipedRightLeg.render(f5);
        this.bipedHeadwear.render(f5);
    }
    
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
    	float f = 1.0F;
    	this.bipedBody.rotateAngleY = 0.0F;
    	setRotateAngle(bipedRightLeg, MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f, 0.0F, 0.0F);
    	setRotateAngle(bipedLeftLeg, MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f, 0.0F, 0.0F);
    	EntityDarknessMage.DarknessMageArmPose darknessmage$darknessmagearmpose = ((EntityDarknessMage)entityIn).getArmPose();
    	if (darknessmage$darknessmagearmpose == EntityDarknessMage.DarknessMageArmPose.SPELLCASTING) {
    		
    		bipedRightArm.rotationPointZ = 0.0F;
            bipedRightArm.rotationPointX = -5.0F;
            bipedLeftArm.rotationPointZ = 0.0F;
            bipedLeftArm.rotationPointX = 5.0F;
            setRotateAngle(bipedRightArm, MathHelper.cos(ageInTicks * 0.6662F) * 0.25F, 0.0F, 2.3561945F);
            setRotateAngle(bipedLeftArm, MathHelper.cos(ageInTicks * 0.6662F) * 0.25F, 0.0F, -2.3561945F);
    		
    	} else {
    		
    		bipedRightArm.setRotationPoint(-7.0F, -13.5F, 0.0F);
    		setRotateAngle(bipedRightArm, 0.0F, 0.0F, 0.10000736613927509F);
    		bipedLeftArm.setRotationPoint(5.0F, -13.5F, 0.0F);
    		setRotateAngle(bipedLeftArm, 0.0F, 0.0F, -0.10000736613927509F);
            this.bipedRightArm.rotationPointZ = 0.0F;
            this.bipedRightArm.rotationPointX = -5.0F;
            this.bipedLeftArm.rotationPointZ = 0.0F;
            this.bipedLeftArm.rotationPointX = 5.0F;
            this.bipedRightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
            this.bipedLeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
            this.bipedRightArm.rotateAngleZ = 0.0F;
            this.bipedLeftArm.rotateAngleZ = 0.0F;
            this.bipedRightArm.rotateAngleY = 0.0F;
            this.bipedRightArm.rotateAngleZ = 0.0F;
            this.bipedLeftArm.rotateAngleY = 0.0F;
            this.bipedRightArm.rotateAngleY = 0.0F;
            
            if (this.swingProgress > 0.0F) {
            	
            	EnumHandSide enumhandside = this.getMainHand(entityIn);
                ModelRenderer modelrenderer = this.getArmForSide(enumhandside);
                float f1 = this.swingProgress;
                this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float)Math.PI * 2F)) * 0.2F;

                if (enumhandside == EnumHandSide.LEFT)
                {
                    this.bipedBody.rotateAngleY *= -1.0F;
                }

                this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
                this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
                this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
                this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
                this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
                this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
                this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
                f1 = 1.0F - this.swingProgress;
                f1 = f1 * f1;
                f1 = f1 * f1;
                f1 = 1.0F - f1;
                float f2 = MathHelper.sin(f1 * (float)Math.PI);
                float f3 = MathHelper.sin(this.swingProgress * (float)Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
                modelrenderer.rotateAngleX = (float)((double)modelrenderer.rotateAngleX - ((double)f2 * 1.2D + (double)f3));
                modelrenderer.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
                modelrenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float)Math.PI) * -0.4F;
            	
            }
    		
            this.bipedRightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
            this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
            this.bipedRightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
            this.bipedLeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
            
    	}
    	
    	 this.bipedBody.rotateAngleX = 0.0F;
         this.bipedRightLeg.rotationPointZ = 0.1F;
         this.bipedLeftLeg.rotationPointZ = 0.1F;
         
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
