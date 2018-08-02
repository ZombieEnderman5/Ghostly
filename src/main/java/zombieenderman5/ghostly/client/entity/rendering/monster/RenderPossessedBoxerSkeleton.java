package zombieenderman5.ghostly.client.entity.rendering.monster;

import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.GhostlyReference;
import zombieenderman5.ghostly.client.entity.rendering.layer.LayerPossessedBoxerSkeletonEyes;
import zombieenderman5.ghostly.client.entity.rendering.layer.LayerPossessedSkeletonEyes;
import zombieenderman5.ghostly.client.entity.rendering.layer.LayerPossessedZombieEyes;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelPossessedSkeleton;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelPossessedZombie;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderPossessedZombie.Factory;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedBoxerSkeleton;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedSkeleton;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedZombie;

@SideOnly(Side.CLIENT)
public class RenderPossessedBoxerSkeleton extends RenderBiped<AbstractSkeleton>
{
	private static final ResourceLocation RES_POSSESSED_BOXER_SKELETON_GLOVELESS = new ResourceLocation("textures/entity/skeleton/skeleton.png");
	private static final ResourceLocation RES_POSSESSED_BOXER_SKELETON_RED_GLOVES = new ResourceLocation("ghostly:textures/entity/possessed_boxer_skeleton/possessed_boxer_skeleton_red_gloves.png");
	private static final ResourceLocation RES_POSSESSED_BOXER_SKELETON_BLUE_GLOVES = new ResourceLocation("ghostly:textures/entity/possessed_boxer_skeleton/possessed_boxer_skeleton_blue_gloves.png");

    public static final Factory FACTORY = new Factory();
    
    public RenderPossessedBoxerSkeleton(RenderManager renderManagerIn)
    {
    	super(renderManagerIn, new ModelPossessedSkeleton(), 0.5F);
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
        	@Override
            protected void initArmor()
            {
                this.modelLeggings = new ModelSkeleton(0.5F, true);
                this.modelArmor = new ModelSkeleton(1.0F, true);
            }
        };
        this.addLayer(layerbipedarmor);
        this.addLayer(new LayerPossessedBoxerSkeletonEyes(this));
    }

    @Override
    public void transformHeldFull3DItemLayer()
    {
        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(AbstractSkeleton skeleton)
    {
    	EntityPossessedBoxerSkeleton entity = (EntityPossessedBoxerSkeleton) skeleton;
    	
    	if (entity.getGlovesType() == 0) {
    	
    		return RES_POSSESSED_BOXER_SKELETON_RED_GLOVES;
    		
    	} else if (entity.getGlovesType() == 1) {
    		
    		return RES_POSSESSED_BOXER_SKELETON_BLUE_GLOVES;
    		
    	}
    	
    	return RES_POSSESSED_BOXER_SKELETON_GLOVELESS;
    }
    
    public static class Factory implements IRenderFactory<EntityPossessedBoxerSkeleton> {

		@Override
		public Render<? super EntityPossessedBoxerSkeleton> createRenderFor(RenderManager manager) {
			return new RenderPossessedBoxerSkeleton(manager);
		}

	}
    
}