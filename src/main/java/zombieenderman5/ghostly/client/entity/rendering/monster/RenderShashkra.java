package zombieenderman5.ghostly.client.entity.rendering.monster;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.GhostlyReference;
import zombieenderman5.ghostly.client.entity.rendering.layer.LayerPossessedZombieEyes;
import zombieenderman5.ghostly.client.entity.rendering.layer.LayerShashkraGlowingParts;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelDarknessMage;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelShashkra;
import zombieenderman5.ghostly.common.entity.monster.EntityDarknessMage;
import zombieenderman5.ghostly.common.entity.monster.EntityShade;
import zombieenderman5.ghostly.common.entity.monster.EntityShashkra;

@SideOnly(Side.CLIENT)
public class RenderShashkra extends RenderBiped<EntityShashkra> {

	private static final ResourceLocation SHASHKRA_TEXTURES = new ResourceLocation(GhostlyReference.MOD_ID, "textures/entity/shashkra/shashkra.png");
	private static final ResourceLocation SHASHKRA_SPELLCASTER_TEXTURES = new ResourceLocation(GhostlyReference.MOD_ID, "textures/entity/shashkra/shashkra_spellcaster.png");
	private static final ResourceLocation SHASHKRA_FIREBALLING_TEXTURES = new ResourceLocation(GhostlyReference.MOD_ID, "textures/entity/shashkra/shashkra_fireballing.png");

	public static final Factory FACTORY = new Factory();

	public RenderShashkra(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelShashkra(), 0.5F);
		this.addLayer(new LayerShashkraGlowingParts(this));

	}

	@Override
	public void doRender(EntityShashkra entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	@Override
	protected ResourceLocation getEntityTexture(EntityShashkra entity)
	{
		if (entity.getFireballing()) {
			return SHASHKRA_FIREBALLING_TEXTURES;
		} else if (entity.getSpellcaster()) {
			return SHASHKRA_SPELLCASTER_TEXTURES;
		} else {
			return SHASHKRA_TEXTURES;
		}
	}

	public static class Factory implements IRenderFactory<EntityShashkra> {

		@Override
		public Render<? super EntityShashkra> createRenderFor(RenderManager manager) {
			return new RenderShashkra(manager);
		}

	}


}
