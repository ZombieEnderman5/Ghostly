package zombieenderman5.ghostly.client.entity.rendering.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSkeleton;

public class ModelPossessedSkeleton extends ModelSkeleton {
	
	public ModelPossessedSkeleton() {
		
		super();
		
	}
	
	public ModelPossessedSkeleton(float modelSize, boolean p_i46303_2_)
    {
        super(modelSize, p_i46303_2_);

        if (!p_i46303_2_)
        {
            this.bipedRightArm = new ModelRenderer(this, 40, 16);
            this.bipedRightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, modelSize);
            this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
            this.bipedLeftArm = new ModelRenderer(this, 40, 16);
            this.bipedLeftArm.mirror = true;
            this.bipedLeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, modelSize);
            this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
            this.bipedRightLeg = new ModelRenderer(this, 0, 16);
            this.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, modelSize);
            this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
            this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
            this.bipedLeftLeg.mirror = true;
            this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, modelSize);
            this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        }
    }
	
	public void changeBipedHeadwearVisibility(boolean visible) {
        
        bipedHeadwear.showModel = visible;
        
    }
	
	public void changeBipedBodyVisibility(boolean visible) {
        
        bipedBody.showModel = visible;
        
    }
	
}
