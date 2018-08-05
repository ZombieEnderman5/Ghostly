package zombieenderman5.ghostly.client.entity.rendering.projectile;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import zombieenderman5.ghostly.common.entity.projectile.EntityDustedVenomCorporealityArrow;

public class RenderDustedVenomCorporealityArrow extends RenderVenomCorporealityArrowAbstract {

	public static final Factory FACTORY = new Factory();
	
	public RenderDustedVenomCorporealityArrow(RenderManager renderManagerIn) {
		
		super(renderManagerIn);
		
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		
		return new ResourceLocation("ghostly:textures/entity/projectiles/dusted_venom_arrow_of_corporeality.png");
		
	}

	public static class Factory implements IRenderFactory<EntityDustedVenomCorporealityArrow> {

		@Override
		public Render<? super EntityDustedVenomCorporealityArrow> createRenderFor(RenderManager manager) {
			return new RenderDustedVenomCorporealityArrow(manager);
		}

	}
	
}
