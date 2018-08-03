package zombieenderman5.ghostly.client.entity.rendering.projectile;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import zombieenderman5.ghostly.common.entity.projectile.EntityTippedCorporealityArrow;

public class RenderTippedCorporealityArrow extends RenderCorporealityArrowAbstract {

	public static final Factory FACTORY = new Factory();
	
	public RenderTippedCorporealityArrow(RenderManager renderManagerIn) {
		
		super(renderManagerIn);
		
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		
		return new ResourceLocation("textures/entity/projectiles/tipped_arrow.png");
		
	}

	public static class Factory implements IRenderFactory<EntityTippedCorporealityArrow> {

		@Override
		public Render<? super EntityTippedCorporealityArrow> createRenderFor(RenderManager manager) {
			return new RenderTippedCorporealityArrow(manager);
		}

	}
	
}
