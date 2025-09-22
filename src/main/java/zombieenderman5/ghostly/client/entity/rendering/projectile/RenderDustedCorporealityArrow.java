package zombieenderman5.ghostly.client.entity.rendering.projectile;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import zombieenderman5.ghostly.common.entity.projectile.EntityDustedCorporealityArrow;

public class RenderDustedCorporealityArrow extends ArrowRenderer<EntityDustedCorporealityArrow> {
	public RenderDustedCorporealityArrow(EntityRendererManager manager) {
		super(manager);
	}

	@Override
	public ResourceLocation getEntityTexture(EntityDustedCorporealityArrow entity) {
		return new ResourceLocation("textures/entity/projectiles/dusted_arrow_of_corporeality.png");

	}
}
