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
public class ModelMutatedLlama extends ModelBase {
    public ModelRenderer head;
    public ModelRenderer rightLeg;
    public ModelRenderer leftArm;
    public ModelRenderer leftLeg;
    public ModelRenderer rightArm;
    public ModelRenderer body;

    public ModelMutatedLlama() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.body = new ModelRenderer(this, 29, 0);
        this.body.setRotationPoint(0.0F, 1.0F, 2.0F);
        this.body.addBox(-6.0F, -9.0F, -5.0F, 12, 18, 10, 0.0F);
        this.rightArm = new ModelRenderer(this, 29, 29);
        this.rightArm.setRotationPoint(-7.8F, -6.0F, 2.0F);
        this.rightArm.addBox(-2.0F, 0.0F, -2.0F, 4, 14, 4, 0.0F);
        this.setRotateAngle(rightArm, -0.0F, 0.0F, 0.10000736613927509F);
        this.leftLeg = new ModelRenderer(this, 29, 29);
        this.leftLeg.mirror = true;
        this.leftLeg.setRotationPoint(3.5F, 9.0F, 2.0F);
        this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 14, 4, 0.0F);
        this.leftArm = new ModelRenderer(this, 29, 29);
        this.leftArm.mirror = true;
        this.leftArm.setRotationPoint(7.8F, -6.0F, 2.0F);
        this.leftArm.addBox(-2.0F, 0.0F, -2.0F, 4, 14, 4, 0.0F);
        this.setRotateAngle(leftArm, 0.0F, 0.0F, -0.10000736613927509F);
        this.rightLeg = new ModelRenderer(this, 29, 29);
        this.rightLeg.setRotationPoint(-3.5F, 9.0F, 2.0F);
        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 14, 4, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, -10.0F, 4.0F);
        this.head.addBox(-2.0F, -2.0F, -10.0F, 4, 4, 9, 0.0F);
        this.head.setTextureOffset(0, 14).addBox(-4.0F, -4.0F, -6.0F, 8, 6, 6, 0.0F);
        this.head.setTextureOffset(17, 0).addBox(-4.0F, -7.0F, -4.0F, 3, 3, 2, 0.0F);
        this.head.setTextureOffset(17, 0).addBox(1.0F, -7.0F, -4.0F, 3, 3, 2, 0.0F);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    	super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    	this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
    	
        this.rightArm.render(scale);
        this.body.render(scale);
        this.leftLeg.render(scale);
        this.leftArm.render(scale);
        this.rightLeg.render(scale);
        this.head.renderWithRotation(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
    	super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    	
    	this.head.rotateAngleX = headPitch * 0.017453292F;
        this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.body.rotateAngleY = 0.0F;
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
        this.rightLeg.rotationPointY = 9.0F;
        this.leftLeg.rotationPointY = 9.0F;
        this.head.rotationPointY = -10.0F;
        this.rightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.leftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.rightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.leftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        if (this.head.rotateAngleX > 0.0F) {
        	this.head.rotateAngleX = 0.0F;
        }
        if (this.head.rotateAngleX < -0.13962634015954636F) {
        	this.head.rotateAngleX = -0.13962634015954636F;
        }
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
