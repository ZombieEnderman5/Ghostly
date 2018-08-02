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
import zombieenderman5.ghostly.client.entity.rendering.layer.LayerPossessedSkeletonEyes;
import zombieenderman5.ghostly.client.entity.rendering.layer.LayerPossessedZombieEyes;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelPossessedSkeleton;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelPossessedZombie;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderPossessedZombie.Factory;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedSkeleton;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedZombie;

@SideOnly(Side.CLIENT)
public class RenderPossessedSkeleton extends RenderBiped<AbstractSkeleton>
{
    private static final ResourceLocation SKELETON_TEXTURES = new ResourceLocation("textures/entity/skeleton/skeleton.png");

    public static final Factory FACTORY = new Factory();
    
    public RenderPossessedSkeleton(RenderManager renderManagerIn)
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
        this.addLayer(new LayerPossessedSkeletonEyes(this));
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
    protected ResourceLocation getEntityTexture(AbstractSkeleton entity)
    {
        return SKELETON_TEXTURES;
    }
    
    public static class Factory implements IRenderFactory<EntityPossessedSkeleton> {

		@Override
		public Render<? super EntityPossessedSkeleton> createRenderFor(RenderManager manager) {
			return new RenderPossessedSkeleton(manager);
		}

	}
    
}