package zombieenderman5.ghostly.client.entity.rendering.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;

public class ModelPossessedHunchbone extends ModelBiped {
	
	public ModelRenderer jaw;
	
	public ModelPossessedHunchbone() {
		
		this(0.0F, false);
	}
	
	public ModelPossessedHunchbone(float modelSize, boolean notThin) {
		
		super(modelSize, 0.0F, 64, notThin ? 32 : 64);
		bipedHead = new ModelRenderer(this, 0, 0);
		bipedHead.setRotationPoint(0.0F, 2.7F, -4.5F);
		bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		bipedHeadwear = new ModelRenderer(this, 32, 0);
		bipedHeadwear.setRotationPoint(0.0F, 2.7F, -4.5F);
		bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
		jaw = new ModelRenderer(this, 0, 32);
		jaw.setRotationPoint(-3.8F, 3.5F, -4.0F);
		jaw.addBox(0.0F, -2.1F, 0.0F, 8, 1, 6, 0.0F);
		bipedHead.addChild(jaw);
		// if (notThin)
		// {
		bipedRightArm = new ModelRenderer(this, 40, 16);
		bipedRightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, modelSize);
		bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		bipedLeftArm = new ModelRenderer(this, 40, 16);
		bipedLeftArm.mirror = true;
		bipedLeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, modelSize);
		bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
		bipedRightLeg = new ModelRenderer(this, 0, 16);
		bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, modelSize);
		bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
		bipedLeftLeg = new ModelRenderer(this, 0, 16);
		bipedLeftLeg.mirror = true;
		bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, modelSize);
		bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
		// }
	}
	
	/**
	 * Used for easily adding entity-dependent animations. The second and third
	 * float params here are the same second and third as in the setRotationAngles
	 * method.
	 */
	@Override
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
		
		rightArmPose = ModelBiped.ArmPose.EMPTY;
		leftArmPose = ModelBiped.ArmPose.EMPTY;
		ItemStack itemstack = entitylivingbaseIn.getHeldItem(EnumHand.MAIN_HAND);
		
		if (itemstack.getItem() == Items.BOW && ((AbstractSkeleton) entitylivingbaseIn).isSwingingArms()) {
			if (entitylivingbaseIn.getPrimaryHand() == EnumHandSide.RIGHT) {
				rightArmPose = ModelBiped.ArmPose.BOW_AND_ARROW;
			} else {
				leftArmPose = ModelBiped.ArmPose.BOW_AND_ARROW;
			}
		}
		
		super.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTickTime);
	}
	
	/**
	 * Sets the model's various rotation angles. For bipeds, par1 and par2 are used
	 * for animating the movement of arms and legs, where par1 represents the
	 * time(so that arms and legs swing back and forth) and par2 represents how
	 * "far" arms and legs can swing at most.
	 */
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
		ItemStack itemstack = ((EntityLivingBase) entityIn).getHeldItemMainhand();
		AbstractSkeleton abstractskeleton = (AbstractSkeleton) entityIn;
		
		if (abstractskeleton.isSwingingArms() && (itemstack.isEmpty() || itemstack.getItem() != Items.BOW)) {
			float f = MathHelper.sin(swingProgress * (float) Math.PI);
			float f1 = MathHelper.sin((1.0F - (1.0F - swingProgress) * (1.0F - swingProgress)) * (float) Math.PI);
			bipedRightArm.rotateAngleZ = 0.0F;
			bipedLeftArm.rotateAngleZ = 0.0F;
			bipedRightArm.rotateAngleY = -(0.1F - f * 0.6F);
			bipedLeftArm.rotateAngleY = 0.1F - f * 0.6F;
			bipedRightArm.rotateAngleX = -((float) Math.PI / 2F);
			bipedLeftArm.rotateAngleX = -((float) Math.PI / 2F);
			bipedRightArm.rotateAngleX -= f * 1.2F - f1 * 0.4F;
			bipedLeftArm.rotateAngleX -= f * 1.2F - f1 * 0.4F;
			bipedRightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
			bipedLeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
			bipedRightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
			bipedLeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		}
		
		setRotateAngle(jaw, 0.25F, 0.0F, -0.05F);
	}
	
	@Override
	public void postRenderArm(float scale, EnumHandSide side) {
		
		float f = side == EnumHandSide.RIGHT ? 1.0F : -1.0F;
		ModelRenderer modelrenderer = getArmForSide(side);
		modelrenderer.rotationPointX += f;
		modelrenderer.postRender(scale);
		modelrenderer.rotationPointX -= f;
	}
	
	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	/**
	 * Sets the models various rotation angles then renders the model.
	 */
	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		
		setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		GlStateManager.pushMatrix();
		
		if (isChild) {
			GlStateManager.scale(0.75F, 0.75F, 0.75F);
			GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
			bipedHead.render(scale);
			// this.jaw.render(scale);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
			bipedBody.render(scale);
			bipedRightArm.render(scale);
			bipedLeftArm.render(scale);
			bipedRightLeg.render(scale);
			bipedLeftLeg.render(scale);
			bipedHeadwear.render(scale);
		} else {
			if (entityIn.isSneaking()) {
				GlStateManager.translate(0.0F, 0.2F, 0.0F);
			}
			
			bipedHead.render(scale);
			// this.jaw.render(scale);
			bipedBody.render(scale);
			bipedRightArm.render(scale);
			bipedLeftArm.render(scale);
			bipedRightLeg.render(scale);
			bipedLeftLeg.render(scale);
			bipedHeadwear.render(scale);
		}
		
		GlStateManager.popMatrix();
	}
	
	public void changeBipedHeadwearVisibility(boolean visible) {
        
        bipedHeadwear.showModel = visible;
        
    }
	
}
