package zombieenderman5.ghostly.client.entity.rendering.projectile;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import zombieenderman5.ghostly.common.entity.projectile.EntitySpectralCorporealityArrow;

public class RenderSpectralCorporealityArrow extends RenderCorporealityArrowAbstract {

	public static final Factory FACTORY = new Factory();
	
	public RenderSpectralCorporealityArrow(RenderManager renderManagerIn) {
		
		super(renderManagerIn);
		
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		
		return new ResourceLocation("textures/entity/projectiles/spectral_arrow.png");
		
	}

	public static class Factory implements IRenderFactory<EntitySpectralCorporealityArrow> {

		@Override
		public Render<? super EntitySpectralCorporealityArrow> createRenderFor(RenderManager manager) {
			return new RenderSpectralCorporealityArrow(manager);
		}

	}
	
}
