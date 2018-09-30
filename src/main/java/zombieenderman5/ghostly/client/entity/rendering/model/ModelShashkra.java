package zombieenderman5.ghostly.client.entity.rendering.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import zombieenderman5.ghostly.common.entity.monster.EntityDarknessMage;
import zombieenderman5.ghostly.common.entity.monster.EntityShashkra;

/**
 * Created using Tabula 7.0.0
 * @author ZombieEnderman5
 */
public class ModelShashkra extends ModelBiped {
	
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer bipedHeadwear;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedBody;
    public ModelRenderer bipedHead;
    public ModelRenderer spike1;
    public ModelRenderer spike2;
    public ModelRenderer spike3;
    public ModelRenderer spike4;
    public ModelRenderer spike5;
    public ModelRenderer spike6;
    public ModelRenderer spike7;
    public ModelRenderer spike8;
    public ModelRenderer spike9;
    public ModelRenderer spike10;
    public ModelRenderer spike11;
    public ModelRenderer spike12;
    public ModelRenderer spike13;
    public ModelRenderer spike14;
    public ModelRenderer spike15;
    public ModelRenderer spike16;
    public ModelRenderer spike17;

    public ModelShashkra() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.spike11 = new ModelRenderer(this, 0, 32);
        this.spike11.setRotationPoint(-1.8F, -7.0F, -1.8F);
        this.spike11.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(spike11, 0.7853981633974483F, 0.7853981633974483F, 0.0F);
        this.spike1 = new ModelRenderer(this, 0, 32);
        this.spike1.setRotationPoint(-1.0F, 0.0F, -1.5F);
        this.spike1.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(spike1, 1.2217304763960306F, 0.0F, 0.0F);
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.spike10 = new ModelRenderer(this, 0, 32);
        this.spike10.setRotationPoint(0.0F, -7.0F, -2.0F);
        this.spike10.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(spike10, 0.7853981633974483F, 0.0F, 0.0F);
        this.spike13 = new ModelRenderer(this, 0, 32);
        this.spike13.setRotationPoint(-1.8F, -7.0F, 1.8F);
        this.spike13.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(spike13, -0.7853981633974483F, -0.7853981633974483F, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 40, 16);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.spike15 = new ModelRenderer(this, 0, 32);
        this.spike15.setRotationPoint(1.8F, -7.0F, 1.8F);
        this.spike15.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(spike15, -0.7853981633974483F, 0.7853981633974483F, 0.0F);
        this.bipedHeadwear = new ModelRenderer(this, 32, 0);
        this.bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
        this.spike2 = new ModelRenderer(this, 0, 32);
        this.spike2.setRotationPoint(-1.0F, 3.0F, -1.5F);
        this.spike2.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(spike2, 1.2217304763960306F, 0.0F, 0.0F);
        this.spike12 = new ModelRenderer(this, 0, 32);
        this.spike12.setRotationPoint(-2.0F, -7.0F, 0.0F);
        this.spike12.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(spike12, 0.0F, 0.0F, -0.7853981633974483F);
        this.spike17 = new ModelRenderer(this, 0, 32);
        this.spike17.setRotationPoint(1.8F, -7.0F, -1.8F);
        this.spike17.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(spike17, 0.7853981633974483F, -0.7853981633974483F, 0.0F);
        this.bipedBody = new ModelRenderer(this, 16, 16);
        this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.spike7 = new ModelRenderer(this, 0, 32);
        this.spike7.mirror = true;
        this.spike7.setRotationPoint(1.0F, 6.0F, -1.5F);
        this.spike7.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(spike7, 1.2217304763960306F, 0.0F, 0.0F);
        this.spike14 = new ModelRenderer(this, 0, 32);
        this.spike14.setRotationPoint(0.0F, -7.0F, 2.0F);
        this.spike14.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(spike14, -0.7853981633974483F, 0.0F, 0.0F);
        this.spike9 = new ModelRenderer(this, 0, 32);
        this.spike9.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.spike9.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 40, 16);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        this.spike5 = new ModelRenderer(this, 0, 32);
        this.spike5.mirror = true;
        this.spike5.setRotationPoint(1.0F, 0.0F, -1.5F);
        this.spike5.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(spike5, 1.2217304763960306F, 0.0F, 0.0F);
        this.spike6 = new ModelRenderer(this, 0, 32);
        this.spike6.mirror = true;
        this.spike6.setRotationPoint(1.0F, 3.0F, -1.5F);
        this.spike6.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(spike6, 1.2217304763960306F, 0.0F, 0.0F);
        this.spike16 = new ModelRenderer(this, 0, 32);
        this.spike16.setRotationPoint(2.0F, -7.0F, 0.0F);
        this.spike16.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(spike16, 0.0F, 0.0F, 0.7853981633974483F);
        this.spike4 = new ModelRenderer(this, 0, 32);
        this.spike4.setRotationPoint(-1.0F, 9.0F, -1.5F);
        this.spike4.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(spike4, 1.2217304763960306F, 0.0F, 0.0F);
        this.spike8 = new ModelRenderer(this, 0, 32);
        this.spike8.mirror = true;
        this.spike8.setRotationPoint(1.0F, 9.0F, -1.5F);
        this.spike8.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(spike8, 1.2217304763960306F, 0.0F, 0.0F);
        this.bipedRightLeg = new ModelRenderer(this, 0, 16);
        this.bipedRightLeg.setRotationPoint(-1.899999976158142F, 12.0F, 0.0F);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.spike3 = new ModelRenderer(this, 0, 32);
        this.spike3.setRotationPoint(-1.0F, 6.0F, -1.5F);
        this.spike3.addBox(-1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(spike3, 1.2217304763960306F, 0.0F, 0.0F);
        this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.setRotationPoint(1.899999976158142F, 12.0F, 0.0F);
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.bipedHead.addChild(this.spike11);
        this.bipedRightArm.addChild(this.spike1);
        this.bipedHead.addChild(this.spike10);
        this.bipedHead.addChild(this.spike13);
        this.bipedHead.addChild(this.spike15);
        this.bipedRightArm.addChild(this.spike2);
        this.bipedHead.addChild(this.spike12);
        this.bipedHead.addChild(this.spike17);
        this.bipedLeftArm.addChild(this.spike7);
        this.bipedHead.addChild(this.spike14);
        this.bipedHead.addChild(this.spike9);
        this.bipedLeftArm.addChild(this.spike5);
        this.bipedLeftArm.addChild(this.spike6);
        this.bipedHead.addChild(this.spike16);
        this.bipedRightArm.addChild(this.spike4);
        this.bipedLeftArm.addChild(this.spike8);
        this.bipedRightArm.addChild(this.spike3);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.bipedHead.render(f5);
        this.bipedRightArm.render(f5);
        this.bipedHeadwear.render(f5);
        this.bipedBody.render(f5);
        this.bipedLeftArm.render(f5);
        this.bipedRightLeg.render(f5);
        this.bipedLeftLeg.render(f5);
    }
    
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
    	float f = 1.0F;
    	this.bipedBody.rotateAngleY = 0.0F;
    	setRotateAngle(bipedRightLeg, MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f, 0.0F, 0.0F);
    	setRotateAngle(bipedLeftLeg, MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f, 0.0F, 0.0F);
    	EntityShashkra.ShashkraArmPose shashkra$shashkraarmpose = ((EntityShashkra)entityIn).getArmPose();
    	if (shashkra$shashkraarmpose == EntityShashkra.ShashkraArmPose.SPELLCASTING) {
    		
    		bipedRightArm.rotationPointZ = 0.0F;
            bipedRightArm.rotationPointX = -5.0F;
            bipedLeftArm.rotationPointZ = 0.0F;
            bipedLeftArm.rotationPointX = 5.0F;
            setRotateAngle(bipedRightArm, MathHelper.cos(ageInTicks * 0.6662F) * 0.25F, 0.0F, 2.3561945F);
            setRotateAngle(bipedLeftArm, MathHelper.cos(ageInTicks * 0.6662F) * 0.25F, 0.0F, -2.3561945F);
    		
    	} else {
    		
    		bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
    		setRotateAngle(bipedRightArm, 0.0F, 0.0F, 0.10000736613927509F);
    		bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
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
    
    public void changeBipedHeadwearVisibility(boolean visible) {
        
        bipedHeadwear.showModel = visible;
        
    }
}
