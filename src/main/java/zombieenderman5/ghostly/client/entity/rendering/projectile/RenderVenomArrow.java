package zombieenderman5.ghostly.client.entity.rendering.projectile;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import zombieenderman5.ghostly.common.entity.projectile.EntityVenomArrow;

public class RenderVenomArrow extends RenderVenomArrowAbstract {

	public static final Factory FACTORY = new Factory();
	
	public RenderVenomArrow(RenderManager renderManagerIn) {
		
		super(renderManagerIn);
		
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		
		return new ResourceLocation("ghostly:textures/entity/projectiles/venom_arrow.png");
		
	}

	public static class Factory implements IRenderFactory<EntityVenomArrow> {

		@Override
		public Render<? super EntityVenomArrow> createRenderFor(RenderManager manager) {
			return new RenderVenomArrow(manager);
		}

	}
	
}
