package zombieenderman5.ghostly.client.entity.rendering.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import zombieenderman5.ghostly.common.entity.monster.EntityMutatedDonkey;
import zombieenderman5.ghostly.common.entity.monster.EntityMutatedHorse;

/**
 * Created using Tabula 7.0.0
 * @author ZombieEnderman5
 */
public class ModelMutatedHorse extends ModelBase {
    public ModelRenderer tailBase;
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer rightArm;
    public ModelRenderer leftLeg;
    public ModelRenderer rightLeg;
    public ModelRenderer leftArm;
    public ModelRenderer tailMiddle;
    public ModelRenderer tailTip;
    public ModelRenderer upperMouth;
    public ModelRenderer lowerMouth;
    public ModelRenderer rightEar;
    public ModelRenderer leftEar;
    public ModelRenderer altRightEar;
    public ModelRenderer altLeftEar;
    public ModelRenderer rightForearm;
    public ModelRenderer rightArmHoof;
    public ModelRenderer leftShin;
    public ModelRenderer leftLegHoof;
    public ModelRenderer rightShin;
    public ModelRenderer rightLegHoof;
    public ModelRenderer leftForearm;
    public ModelRenderer leftArmHoof;
    private boolean donkey;

    public ModelMutatedHorse(boolean donkey) {
    	this.donkey = donkey;
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.rightArm = new ModelRenderer(this, 60, 29);
        this.rightArm.setRotationPoint(-6.7F, -8.0F, 1.5F);
        this.rightArm.addBox(-1.1F, -1.0F, -2.1F, 3, 8, 4, 0.0F);
        this.setRotateAngle(rightArm, 0.0F, 0.0F, 0.10000736613927509F);
        this.rightLeg = new ModelRenderer(this, 96, 29);
        this.rightLeg.setRotationPoint(-3.5F, 9.0F, 1.5F);
        this.rightLeg.addBox(-1.5F, -2.0F, -2.5F, 4, 9, 5, 0.0F);
        this.rightArmHoof = new ModelRenderer(this, 60, 51);
        this.rightArmHoof.setRotationPoint(0.35F, 7.0F, -0.1F);
        this.rightArmHoof.addBox(-2.0F, 5.1F, -2.0F, 4, 3, 4, 0.0F);
        this.leftLeg = new ModelRenderer(this, 78, 29);
        this.leftLeg.setRotationPoint(3.5F, 9.0F, 1.5F);
        this.leftLeg.addBox(-2.5F, -2.0F, -2.5F, 4, 9, 5, 0.0F);
        this.leftShin = new ModelRenderer(this, 78, 43);
        this.leftShin.setRotationPoint(-0.5F, 7.0F, 0.1F);
        this.leftShin.addBox(-1.5F, 0.0F, -1.5F, 3, 5, 3, 0.0F);
        this.leftForearm = new ModelRenderer(this, 44, 41);
        this.leftForearm.setRotationPoint(-0.35F, 7.0F, -0.1F);
        this.leftForearm.addBox(-1.5F, 0.0F, -1.5F, 3, 5, 3, 0.0F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, -10.2F, -4.2F);
        this.head.addBox(-2.5F, -10.0F, -1.5F, 5, 5, 7, 0.0F);
        this.setRotateAngle(head, 0.3490658503988659F, 0.0F, 0.0F);
        this.lowerMouth = new ModelRenderer(this, 24, 27);
        this.lowerMouth.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.lowerMouth.addBox(-2.0F, -7.0F, -6.5F, 4, 2, 5, 0.0F);
        this.rightLegHoof = new ModelRenderer(this, 96, 51);
        this.rightLegHoof.setRotationPoint(0.5F, 7.0F, 0.1F);
        this.rightLegHoof.addBox(-2.0F, 5.1F, -2.0F, 4, 3, 4, 0.0F);
        this.upperMouth = new ModelRenderer(this, 24, 18);
        this.upperMouth.setRotationPoint(0.0F, 0.019999999552965164F, 0.019999999552965164F);
        this.upperMouth.addBox(-2.0F, -10.0F, -7.0F, 4, 3, 6, 0.0F);
        this.rightForearm = new ModelRenderer(this, 60, 41);
        this.rightForearm.setRotationPoint(0.35F, 7.0F, -0.1F);
        this.rightForearm.addBox(-1.5F, 0.0F, -1.5F, 3, 5, 3, 0.0F);
        this.rightShin = new ModelRenderer(this, 96, 43);
        this.rightShin.setRotationPoint(0.5F, 7.0F, 0.1F);
        this.rightShin.addBox(-1.5F, 0.0F, -1.5F, 3, 5, 3, 0.0F);
        this.rightEar = new ModelRenderer(this, 0, 0);
        this.rightEar.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightEar.addBox(-2.45F, -12.0F, 4.0F, 2, 3, 1, 0.0F);
        this.altRightEar = new ModelRenderer(this, 0, 12);
        this.altRightEar.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.altRightEar.addBox(-0.25F, -16.0F, 4.0F, 2, 7, 1, 0.0F);
        this.setRotateAngle(altRightEar, 0.0F, 0.0F, -0.2617993877991494F);
        this.leftArm = new ModelRenderer(this, 44, 29);
        this.leftArm.setRotationPoint(6.7F, -8.0F, 1.5F);
        this.leftArm.addBox(-1.9F, -1.0F, -2.1F, 3, 8, 4, 0.0F);
        this.setRotateAngle(leftArm, -0.0F, 0.0F, -0.10000736613927509F);
        this.tailTip = new ModelRenderer(this, 24, 3);
        this.tailTip.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tailTip.addBox(-1.5F, -4.5F, 9.0F, 3, 4, 7, 0.0F);
        this.setRotateAngle(tailTip, -0.2617993877991494F, 0.0F, 0.0F);
        this.leftEar = new ModelRenderer(this, 0, 0);
        this.leftEar.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftEar.addBox(0.45F, -12.0F, 4.0F, 2, 3, 1, 0.0F);
        this.altLeftEar = new ModelRenderer(this, 0, 12);
        this.altLeftEar.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.altLeftEar.addBox(-1.75F, -16.0F, 4.0F, 2, 7, 1, 0.0F);
        this.tailBase = new ModelRenderer(this, 44, 0);
        this.tailBase.setRotationPoint(0.0F, 0.0F, 5.3F);
        this.tailBase.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 3, 0.0F);
        this.setRotateAngle(tailBase, -0.6981317007977318F, 0.0F, 0.0F);
        this.tailMiddle = new ModelRenderer(this, 38, 7);
        this.tailMiddle.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tailMiddle.addBox(-1.5F, -2.0F, 3.0F, 3, 4, 7, 0.0F);
        this.setRotateAngle(altLeftEar, 0.0F, 0.0F, 0.2617993877991494F);
        this.leftArmHoof = new ModelRenderer(this, 44, 51);
        this.leftArmHoof.setRotationPoint(-0.35F, 7.0F, -0.1F);
        this.leftArmHoof.addBox(-2.0F, 5.1F, -2.0F, 4, 3, 4, 0.0F);
        this.leftLegHoof = new ModelRenderer(this, 78, 51);
        this.leftLegHoof.setRotationPoint(-0.5F, 7.0F, 0.1F);
        this.leftLegHoof.addBox(-2.0F, 5.1F, -2.0F, 4, 3, 4, 0.0F);
        this.body = new ModelRenderer(this, 0, 34);
        this.body.setRotationPoint(0.0F, -12.0F, -2.0F);
        this.body.addBox(-5.0F, -8.0F, -5.0F, 10, 10, 24, 0.0F);
        this.setRotateAngle(body, -1.5707963267948966F, 0.0F, 0.0F);
        this.rightArm.addChild(this.rightArmHoof);
        this.leftLeg.addChild(this.leftShin);
        this.leftArm.addChild(this.leftForearm);
        this.head.addChild(this.lowerMouth);
        this.rightLeg.addChild(this.rightLegHoof);
        this.head.addChild(this.upperMouth);
        this.rightArm.addChild(this.rightForearm);
        this.rightLeg.addChild(this.rightShin);
        this.head.addChild(this.rightEar);
        this.head.addChild(this.altRightEar);
        this.tailBase.addChild(this.tailTip);
        this.head.addChild(this.leftEar);
        this.head.addChild(this.altLeftEar);
        this.tailBase.addChild(this.tailMiddle);
        this.leftArm.addChild(this.leftArmHoof);
        this.leftLeg.addChild(this.leftLegHoof);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    	super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    	this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
    	
        this.rightArm.render(scale);
        this.rightLeg.render(scale);
        this.leftLeg.render(scale);
        this.head.render(scale);
        this.leftArm.render(scale);
        this.tailBase.render(scale);
        this.body.render(scale);
        if (this.donkey) {
        	this.altRightEar.isHidden = false;
        	this.altLeftEar.isHidden = false;
        } else {
        	this.altRightEar.isHidden = true;
        	this.altLeftEar.isHidden = true;
        }
    }

    @Override
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
    	
    	float f1;
    	boolean flag;
    	if (donkey) {
    		EntityMutatedDonkey mutateddonkey = (EntityMutatedDonkey) entitylivingbaseIn;
        	f1 = mutateddonkey.getMouthOpennessAngle(partialTickTime);
            flag = mutateddonkey.tailCounter != 0;
    	} else {
    		EntityMutatedHorse mutatedhorse = (EntityMutatedHorse) entitylivingbaseIn;
        	f1 = mutatedhorse.getMouthOpennessAngle(partialTickTime);
            flag = mutatedhorse.tailCounter != 0;
    	}
        float f2 = (float)entitylivingbaseIn.ticksExisted + partialTickTime;
        float f3 = -1.3089969F + limbSwingAmount * 1.5F;
        if (f3 > 0.0F) {
            f3 = 0.0F;
        }
        if (flag) {
            this.tailBase.rotateAngleY = MathHelper.cos(f2 * 0.7F);
            f3 = 0.0F;
        } else {
            this.tailBase.rotateAngleY = 0.0F;
        }
        this.tailBase.rotateAngleX = f3;
        this.upperMouth.rotationPointY = 0.02F;
        this.lowerMouth.rotationPointY = 0.0F;
        this.upperMouth.rotationPointZ = 0.02F - f1;
        this.lowerMouth.rotationPointZ = f1;
        this.upperMouth.rotateAngleX = -0.09424778F * f1;
        this.lowerMouth.rotateAngleX = 0.15707964F * f1;
        this.upperMouth.rotateAngleY = 0.0F;
        this.lowerMouth.rotateAngleY = 0.0F;
    	
    }
    
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
    	super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    	
    	this.head.rotateAngleX = 0.3490658503988659F + headPitch * 0.017453292F;
        this.tailBase.rotateAngleX = -0.6981317007977318F;
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
        this.body.rotateAngleX = -1.5707963267948966F;
        this.body.rotationPointY = -12.0F;
        this.rightLeg.rotationPointY = 9.0F;
        this.leftLeg.rotationPointY = 9.0F;
        this.head.rotationPointY = -10.2F;
        this.rightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.leftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.rightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.leftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        if (this.head.rotateAngleX > 0.6248278722139698F) {
        	this.head.rotateAngleX = 0.6248278722139698F;
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
