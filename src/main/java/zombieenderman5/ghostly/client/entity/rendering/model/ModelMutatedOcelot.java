package zombieenderman5.ghostly.client.entity.rendering.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

/**
 * Created using Tabula 7.0.0
 * @author ZombieEnderman5
 */
public class ModelMutatedOcelot extends ModelBase {
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer leftLeg;
    public ModelRenderer rightLeg;
    public ModelRenderer tail;
    public ModelRenderer rightArm;
    public ModelRenderer leftArm;
    public ModelRenderer nose;
    public ModelRenderer ear1;
    public ModelRenderer ear2;
    public ModelRenderer tail2;

    public ModelMutatedOcelot() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.tail = new ModelRenderer(this, 0, 15);
        this.tail.setRotationPoint(0.0F, 13.0F, 3.3F);
        this.tail.addBox(-0.5F, -0.7F, -0.5F, 1, 8, 1, 0.0F);
        this.setRotateAngle(tail, 0.9000662952534757F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.0F, -1.5F);
        this.head.addBox(-2.5F, -2.0F, -3.0F, 5, 4, 5, 0.0F);
        this.leftLeg = new ModelRenderer(this, 8, 13);
        this.leftLeg.mirror = true;
        this.leftLeg.setRotationPoint(1.1F, 18.0F, 0.0F);
        this.leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.tail2 = new ModelRenderer(this, 4, 15);
        this.tail2.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.tail2.addBox(-0.5F, -0.7F, 0.15F, 1, 8, 1, 0.0F);
        this.setRotateAngle(tail2, 0.8278096642209105F, 0.0F, 0.0F);
        this.rightArm = new ModelRenderer(this, 40, 0);
        this.rightArm.setRotationPoint(-2.8F, 5.0F, 0.0F);
        this.rightArm.addBox(-1.0F, 0.0F, -1.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(rightArm, 0.0F, 0.0F, 0.10000736613927509F);
        this.ear1 = new ModelRenderer(this, 0, 10);
        this.ear1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ear1.addBox(-2.0F, -3.0F, 0.0F, 1, 1, 2, 0.0F);
        this.body = new ModelRenderer(this, 20, 0);
        this.body.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.body.addBox(-2.0F, -8.0F, -3.0F, 4, 16, 6, 0.0F);
        this.ear2 = new ModelRenderer(this, 6, 10);
        this.ear2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.ear2.addBox(1.0F, -3.0F, 0.0F, 1, 1, 2, 0.0F);
        this.leftArm = new ModelRenderer(this, 40, 0);
        this.leftArm.mirror = true;
        this.leftArm.setRotationPoint(2.8F, 5.0F, 0.0F);
        this.leftArm.addBox(-1.0F, 0.0F, -1.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(leftArm, -0.0F, 0.0F, -0.10000736613927509F);
        this.rightLeg = new ModelRenderer(this, 8, 13);
        this.rightLeg.setRotationPoint(-1.1F, 18.0F, 0.0F);
        this.rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, 0.0F);
        this.nose = new ModelRenderer(this, 0, 24);
        this.nose.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.nose.addBox(-1.5F, 0.0F, -4.0F, 3, 2, 2, 0.0F);
        this.tail.addChild(this.tail2);
        this.head.addChild(this.ear1);
        this.head.addChild(this.ear2);
        this.head.addChild(this.nose);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    	super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    	this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
    	
        this.tail.render(scale);
        this.head.render(scale);
        this.leftLeg.render(scale);
        this.rightArm.render(scale);
        this.body.render(scale);
        this.leftArm.render(scale);
        this.rightLeg.render(scale);
    }

    @Override
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
    	
    	this.tail.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    	
    }
    
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
    	super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    	
    	this.head.rotateAngleX = headPitch * 0.017453292F;
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.body.rotateAngleY = 0.0F;
        this.tail.rotateAngleX = 0.9000662952534757F;
        this.tail2.rotateAngleX = 0.8278098047465243F + ((float)Math.PI / 4F) * MathHelper.cos(limbSwing) * limbSwingAmount;
        this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / 1.0F;
        this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / 1.0F;
        this.rightArm.rotateAngleZ = 0.0F;
        this.leftArm.rotateAngleZ = 0.0F;
        setRotateAngle(rightLeg, MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / 1.0F, 0.0F, 0.0F);
        setRotateAngle(leftLeg, MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / 1.0F, 0.0F, 0.0F);
        if (this.isRiding) {
            this.rightArm.rotateAngleX += -((float)Math.PI / 5F);
            this.leftArm.rotateAngleX += -((float)Math.PI / 5F);
            setRotateAngle(rightLeg, -1.4137167F, ((float)Math.PI / 10F), 0.07853982F);
            setRotateAngle(leftLeg, -1.4137167F, -((float)Math.PI / 10F), -0.07853982F);
        }
        this.rightArm.rotateAngleY = 0.0F;
        this.rightArm.rotateAngleZ = 0.0F;
        this.leftArm.rotateAngleY = 0.0F;
        this.rightArm.rotateAngleY = 0.0F;
        if (this.swingProgress > 0.0F) {
            EnumHandSide enumhandside = this.getMainHand(entityIn);
            ModelRenderer modelrenderer = this.getArmForSide(enumhandside);
            float f1 = this.swingProgress;
            this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float)Math.PI * 2F)) * 0.2F;

            if (enumhandside == EnumHandSide.LEFT) {
                this.body.rotateAngleY *= -1.0F;
            }

            this.rightArm.rotateAngleY += this.body.rotateAngleY;
            this.leftArm.rotateAngleY += this.body.rotateAngleY;
            this.leftArm.rotateAngleX += this.body.rotateAngleY;
            f1 = 1.0F - this.swingProgress;
            f1 = f1 * f1;
            f1 = f1 * f1;
            f1 = 1.0F - f1;
            float f2 = MathHelper.sin(f1 * (float)Math.PI);
            float f3 = MathHelper.sin(this.swingProgress * (float)Math.PI) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
            setRotateAngle(modelrenderer, (float)((double)modelrenderer.rotateAngleX - ((double)f2 * 1.2D + (double)f3)), modelrenderer.rotateAngleY + (this.body.rotateAngleY * 2.0F), modelrenderer.rotateAngleZ + (MathHelper.sin(this.swingProgress * (float)Math.PI) * -0.4F));
        }
        this.body.rotateAngleX = 0.0F;
        this.rightLeg.rotationPointY = 18.0F;
        this.leftLeg.rotationPointY = 18.0F;
        this.head.rotationPointY = 0.0F;
        this.rightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.leftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.rightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.leftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
    	
    }
    
    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    protected EnumHandSide getMainHand(Entity entityIn) {
        if (entityIn instanceof EntityLivingBase) {
            EntityLivingBase entitylivingbase = (EntityLivingBase)entityIn;
            EnumHandSide enumhandside = entitylivingbase.getPrimaryHand();
            return entitylivingbase.swingingHand == EnumHand.MAIN_HAND ? enumhandside : enumhandside.opposite();
        } else {
            return EnumHandSide.RIGHT;
        }
    }
    
    protected ModelRenderer getArmForSide(EnumHandSide side) {
        return side == EnumHandSide.LEFT ? this.leftArm : this.rightArm;
    }
}
