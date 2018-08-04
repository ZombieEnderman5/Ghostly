package zombieenderman5.ghostly.client.entity.rendering.projectile;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import zombieenderman5.ghostly.common.entity.projectile.EntityDustedCorporealityArrow;

public class RenderDustedCorporealityArrow extends RenderCorporealityArrowAbstract {

	public static final Factory FACTORY = new Factory();
	
	public RenderDustedCorporealityArrow(RenderManager renderManagerIn) {
		
		super(renderManagerIn);
		
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		
		return new ResourceLocation("ghostly:textures/entity/projectiles/dusted_arrow_of_corporeality.png");
		
	}

	public static class Factory implements IRenderFactory<EntityDustedCorporealityArrow> {

		@Override
		public Render<? super EntityDustedCorporealityArrow> createRenderFor(RenderManager manager) {
			return new RenderDustedCorporealityArrow(manager);
		}

	}
	
}
