package zombieenderman5.ghostly.client.entity.rendering.monster;

import gatocreador887.hardcoredimensionexpansion.util.HDEReference;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.client.entity.rendering.layer.LayerPossessedWitheredZombieEyes;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelPossessedZombie;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedHusk;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedWitheredZombie;

@SideOnly(Side.CLIENT)
public class RenderPossessedWitheredZombie extends RenderBiped<EntityZombie> {
	
	private static final ResourceLocation POSSESSED_WITHERED_ZOMBIE_TEXTURES = new ResourceLocation(HDEReference.ID, "textures/entity/withered_zombie.png");

	public static final Factory FACTORY = new Factory();
	
    public RenderPossessedWitheredZombie(RenderManager renderManagerIn)
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
        this.addLayer(new LayerPossessedWitheredZombieEyes(this));
    }

    /**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityPossessedHusk entitylivingbaseIn, float partialTickTime)
    {
    	GlStateManager.scale(0.75F, 0.9F, 0.75F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(EntityZombie entity)
    {
        return POSSESSED_WITHERED_ZOMBIE_TEXTURES;
    }
	
    public static class Factory implements IRenderFactory<EntityPossessedWitheredZombie> {

		@Override
		public Render<? super EntityPossessedWitheredZombie> createRenderFor(RenderManager manager) {
			return new RenderPossessedWitheredZombie(manager);
		}

	}
    
}
