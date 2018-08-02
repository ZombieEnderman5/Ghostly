package zombieenderman5.ghostly.client.entity.rendering.monster;

import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerStrayClothing;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.client.entity.rendering.layer.LayerPossessedStrayClothing;
import zombieenderman5.ghostly.client.entity.rendering.layer.LayerPossessedStrayEyes;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelPossessedSkeleton;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderPossessedWitherSkeleton.Factory;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedStray;

@SideOnly(Side.CLIENT)
public class RenderPossessedStray extends RenderBiped<AbstractSkeleton>
{
    private static final ResourceLocation STRAY_SKELETON_TEXTURES = new ResourceLocation("textures/entity/skeleton/stray.png");

    public static final Factory FACTORY = new Factory();
    
    public RenderPossessedStray(RenderManager p_i47191_1_)
    {
        super(p_i47191_1_, new ModelPossessedSkeleton(), 0.5F);
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
        this.addLayer(new LayerPossessedStrayEyes(this));
        this.addLayer(new LayerPossessedStrayClothing(this));
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(AbstractSkeleton entity)
    {
        return STRAY_SKELETON_TEXTURES;
    }
    
    @Override
    public void transformHeldFull3DItemLayer()
    {
        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }
    
    public static class Factory implements IRenderFactory<EntityPossessedStray> {

		@Override
		public Render<? super EntityPossessedStray> createRenderFor(RenderManager manager) {
			return new RenderPossessedStray(manager);
		}

	}
}