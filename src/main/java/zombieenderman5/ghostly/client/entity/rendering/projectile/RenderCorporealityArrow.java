package zombieenderman5.ghostly.client.entity.rendering.projectile;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import zombieenderman5.ghostly.common.entity.projectile.EntityCorporealityArrow;

public class RenderCorporealityArrow extends RenderCorporealityArrowAbstract {

	public static final Factory FACTORY = new Factory();
	
	public RenderCorporealityArrow(RenderManager renderManagerIn) {
		
		super(renderManagerIn);
		
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		
		return new ResourceLocation("textures/entity/projectiles/arrow.png");
		
	}

	public static class Factory implements IRenderFactory<EntityCorporealityArrow> {

		@Override
		public Render<? super EntityCorporealityArrow> createRenderFor(RenderManager manager) {
			return new RenderCorporealityArrow(manager);
		}

	}
	
}
