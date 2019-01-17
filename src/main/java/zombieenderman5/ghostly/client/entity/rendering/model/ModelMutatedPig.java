package zombieenderman5.ghostly.client.entity.rendering.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

/**
 * Created using Tabula 7.0.0
 * @author ZombieEnderman5
 */
public class ModelMutatedPig extends ModelBase {
    public ModelRenderer head;
    public ModelRenderer rightLeg;
    public ModelRenderer leftArm;
    public ModelRenderer leftLeg;
    public ModelRenderer rightArm;
    public ModelRenderer body;

    public ModelMutatedPig() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.leftLeg = new ModelRenderer(this, 0, 16);
        this.leftLeg.setRotationPoint(3.0F, 18.0F, 2.0F);
        this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, -1.0F, -1.0F);
        this.head.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
        this.rightLeg = new ModelRenderer(this, 0, 16);
        this.rightLeg.setRotationPoint(-3.0F, 18.0F, 2.0F);
        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
        this.rightArm = new ModelRenderer(this, 0, 16);
        this.rightArm.setRotationPoint(-7.0F, 5.0F, 2.0F);
        this.rightArm.addBox(-2.0F, -1.0F, -2.0F, 4, 6, 4, 0.0F);
        this.setRotateAngle(rightArm, 0.0F, 0.0F, 0.10000736613927509F);
        this.body = new ModelRenderer(this, 28, 8);
        this.body.setRotationPoint(0.0F, 10.0F, 2.0F);
        this.body.addBox(-5.0F, -8.0F, -4.0F, 10, 16, 8, 0.0F);
        this.leftArm = new ModelRenderer(this, 0, 16);
        this.leftArm.mirror = true;
        this.leftArm.setRotationPoint(7.0F, 5.0F, 2.0F);
        this.leftArm.addBox(-2.0F, -1.0F, -2.0F, 4, 6, 4, 0.0F);
        this.setRotateAngle(leftArm, -0.0F, 0.0F, -0.10000736613927509F);
        this.head.setTextureOffset(16, 16).addBox(-2.0F, 0.0F, -5.0F, 4, 3, 1, 0.0F);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    	super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    	this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
    	
        this.leftLeg.render(scale);
        this.head.render(scale);
        this.rightLeg.render(scale);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.rightArm.offsetX, this.rightArm.offsetY, this.rightArm.offsetZ);
        GlStateManager.translate(this.rightArm.rotationPointX * scale, this.rightArm.rotationPointY * scale, this.rightArm.rotationPointZ * scale);
        GlStateManager.scale(1.0D, 1.5D, 1.0D);
        GlStateManager.translate(-this.rightArm.offsetX, -this.rightArm.offsetY, -this.rightArm.offsetZ);
        GlStateManager.translate(-this.rightArm.rotationPointX * scale, -this.rightArm.rotationPointY * scale, -this.rightArm.rotationPointZ * scale);
        this.rightArm.render(scale);
        GlStateManager.popMatrix();
        this.body.render(scale);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.leftArm.offsetX, this.leftArm.offsetY, this.leftArm.offsetZ);
        GlStateManager.translate(this.leftArm.rotationPointX * scale, this.leftArm.rotationPointY * scale, this.leftArm.rotationPointZ * scale);
        GlStateManager.scale(1.0D, 1.5D, 1.0D);
        GlStateManager.translate(-this.leftArm.offsetX, -this.leftArm.offsetY, -this.leftArm.offsetZ);
        GlStateManager.translate(-this.leftArm.rotationPointX * scale, -this.leftArm.rotationPointY * scale, -this.leftArm.rotationPointZ * scale);
        this.leftArm.render(scale);
        GlStateManager.popMatrix();
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
        this.rightLeg.rotationPointY = 18.0F;
        this.leftLeg.rotationPointY = 18.0F;
        this.head.rotationPointY = -1.0F;
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
