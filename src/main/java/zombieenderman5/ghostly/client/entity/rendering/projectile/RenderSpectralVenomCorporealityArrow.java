package zombieenderman5.ghostly.client.entity.rendering.projectile;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import zombieenderman5.ghostly.common.entity.projectile.EntitySpectralVenomCorporealityArrow;

public class RenderSpectralVenomCorporealityArrow extends RenderVenomCorporealityArrowAbstract {

	public static final Factory FACTORY = new Factory();
	
	public RenderSpectralVenomCorporealityArrow(RenderManager renderManagerIn) {
		
		super(renderManagerIn);
		
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		
		return new ResourceLocation("ghostly:textures/entity/projectiles/spectral_venom_arrow.png");
		
	}

	public static class Factory implements IRenderFactory<EntitySpectralVenomCorporealityArrow> {

		@Override
		public Render<? super EntitySpectralVenomCorporealityArrow> createRenderFor(RenderManager manager) {
			return new RenderSpectralVenomCorporealityArrow(manager);
		}

	}
	
}
