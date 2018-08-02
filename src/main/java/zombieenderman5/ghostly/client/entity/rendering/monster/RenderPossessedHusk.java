package zombieenderman5.ghostly.client.entity.rendering.monster;

import org.apache.logging.log4j.core.config.status.StatusConfiguration;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import zombieenderman5.ghostly.client.entity.rendering.layer.LayerPossessedHuskEyes;
import zombieenderman5.ghostly.client.entity.rendering.layer.LayerPossessedZombieEyes;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelPossessedZombie;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedHusk;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedZombie;

public class RenderPossessedHusk extends RenderBiped<EntityZombie> {
	
	private static final ResourceLocation POSSESSED_HUSK_TEXTURES = new ResourceLocation("textures/entity/zombie/husk.png");

	public static final Factory FACTORY = new Factory();
	
    public RenderPossessedHusk(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelPossessedZombie(), 0.5F);
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
        	@Override
            protected void initArmor()
            {
                this.modelLeggings = new ModelZombie(0.5F, true);
                this.modelArmor = new ModelZombie(1.0F, true);
            }
        };
        this.addLayer(layerbipedarmor);
        this.addLayer(new LayerPossessedHuskEyes(this));
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityPossessedHusk entitylivingbaseIn, float partialTickTime)
    {
        float f = 1.0625F;
        GlStateManager.scale(1.0625F, 1.0625F, 1.0625F);
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(EntityZombie entity)
    {
        return POSSESSED_HUSK_TEXTURES;
    }
	
    public static class Factory implements IRenderFactory<EntityPossessedHusk> {

		@Override
		public Render<? super EntityPossessedHusk> createRenderFor(RenderManager manager) {
			return new RenderPossessedHusk(manager);
		}

	}
    
}
