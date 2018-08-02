package zombieenderman5.ghostly.client.entity.rendering.monster;

import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.client.entity.rendering.layer.LayerPossessedWitherSkeletonEyes;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelPossessedSkeleton;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedWitherSkeleton;

@SideOnly(Side.CLIENT)
public class RenderPossessedWitherSkeleton extends RenderBiped<AbstractSkeleton>
{
    private static final ResourceLocation POSSESSED_WITHER_SKELETON_TEXTURES = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");

    public static final Factory FACTORY = new Factory();
    
    public RenderPossessedWitherSkeleton(RenderManager renderManagerIn)
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
        this.addLayer(new LayerPossessedWitherSkeletonEyes(this));
    }

    @Override
    public void transformHeldFull3DItemLayer()
    {
        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    
    protected void preRenderCallback(AbstractSkeleton entitylivingbaseIn, float partialTickTime)
    {
        GlStateManager.scale(1.2F, 1.2F, 1.2F);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(AbstractSkeleton entity)
    {
        return POSSESSED_WITHER_SKELETON_TEXTURES;
    }
    
    public static class Factory implements IRenderFactory<EntityPossessedWitherSkeleton> {

		@Override
		public Render<? super EntityPossessedWitherSkeleton> createRenderFor(RenderManager manager) {
			return new RenderPossessedWitherSkeleton(manager);
		}

	}
    
}