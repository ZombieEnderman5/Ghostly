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
public class ModelMutatedCow extends ModelBase {
    public ModelRenderer head1;
    public ModelRenderer rightLeg;
    public ModelRenderer leftArm;
    public ModelRenderer leftLeg;
    public ModelRenderer rightArm;
    public ModelRenderer body1;
    public ModelRenderer head2;
    public ModelRenderer head3;
    //public ModelRenderer body2;

    public ModelMutatedCow() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.rightLeg = new ModelRenderer(this, 0, 16);
        this.rightLeg.setRotationPoint(-4.0F, 12.0F, 0.0F);
        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.rightArm = new ModelRenderer(this, 0, 16);
        this.rightArm.setRotationPoint(-7.5F, -4.0F, 0.0F);
        this.rightArm.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(rightArm, 0.0F, 0.0F, 0.10000736613927509F);
        /*this.body2 = new ModelRenderer(this, 52, 0);
        this.body2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body2.addBox(-2.0F, 2.0F, -8.0F, 4, 6, 1, 0.0F);*/
        this.head1 = new ModelRenderer(this, 0, 0);
        this.head1.setRotationPoint(0.0F, -9.0F, -1.5F);
        this.head1.addBox(-4.0F, -4.0F, -6.0F, 8, 8, 6, 0.0F);
        this.head2 = new ModelRenderer(this, 22, 0);
        this.head2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head2.addBox(-5.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
        this.head3 = new ModelRenderer(this, 22, 0);
        this.head3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head3.addBox(4.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
        this.leftArm = new ModelRenderer(this, 0, 16);
        this.leftArm.mirror = true;
        this.leftArm.setRotationPoint(7.5F, -4.0F, 0.0F);
        this.leftArm.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(leftArm, -0.0F, 0.0F, -0.10000736613927509F);
        this.body1 = new ModelRenderer(this, 18, 4);
        this.body1.setRotationPoint(0.0F, 4.0F, 2.0F);
        this.body1.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 10, 0.0F);
        this.leftLeg = new ModelRenderer(this, 0, 16);
        this.leftLeg.mirror = true;
        this.leftLeg.setRotationPoint(4.0F, 12.0F, 0.0F);
        this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        //this.body1.addChild(this.body2);
        this.head1.addChild(this.head2);
        this.head1.addChild(this.head3);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    	super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    	this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
    	
        this.rightLeg.render(scale);
        this.rightArm.render(scale);
        this.head1.render(scale);
        this.leftArm.render(scale);
        this.body1.render(scale);
        this.leftLeg.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
    	super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    	
    	this.head1.rotateAngleX = headPitch * 0.017453292F;
        this.head1.rotateAngleY = netHeadYaw * 0.017453292F;
        this.body1.rotateAngleY = 0.0F;
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
            this.body1.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float)Math.PI * 2F)) * 0.2F;

            if (enumhandside == EnumHandSide.LEFT) {
                this.body1.rotateAngleY *= -1.0F;
            }

            this.rightArm.rotateAngleY += this.body1.rotateAngleY;
            this.leftArm.rotateAngleY += this.body1.rotateAngleY;
            this.leftArm.rotateAngleX += this.body1.rotateAngleY;
            f1 = 1.0F - this.swingProgress;
            f1 = f1 * f1;
            f1 = f1 * f1;
            f1 = 1.0F - f1;
            float f2 = MathHelper.sin(f1 * (float)Math.PI);
            float f3 = MathHelper.sin(this.swingProgress * (float)Math.PI) * -(this.head1.rotateAngleX - 0.7F) * 0.75F;
            setRotateAngle(modelrenderer, (float)((double)modelrenderer.rotateAngleX - ((double)f2 * 1.2D + (double)f3)), modelrenderer.rotateAngleY + (this.body1.rotateAngleY * 2.0F), modelrenderer.rotateAngleZ + (MathHelper.sin(this.swingProgress * (float)Math.PI) * -0.4F));
        }
        this.body1.rotateAngleX = 0.0F;
        this.rightLeg.rotationPointY = 12.0F;
        this.leftLeg.rotationPointY = 12.0F;
        this.head1.rotationPointY = -9.0F;
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
