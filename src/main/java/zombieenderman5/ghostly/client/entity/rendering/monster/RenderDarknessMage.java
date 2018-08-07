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
import zombieenderman5.ghostly.client.entity.rendering.model.ModelDarknessMage;
import zombieenderman5.ghostly.common.entity.monster.EntityDarknessMage;
import zombieenderman5.ghostly.common.entity.monster.EntityShade;

@SideOnly(Side.CLIENT)
public class RenderDarknessMage extends RenderBiped<EntityDarknessMage> {

	private static final ResourceLocation DARKNESS_MAGE_TEXTURES = new ResourceLocation(GhostlyReference.MOD_ID, "textures/entity/darkness_mage.png");

	public static final Factory FACTORY = new Factory();

	public RenderDarknessMage(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelDarknessMage(), 0.5F);

	}

	@Override
	public void doRender(EntityDarknessMage entity, double x, double y, double z, float entityYaw, float partialTicks) {

		GlStateManager.pushMatrix();
		GlStateManager.disableBlend();
		GlStateManager.colorMask(false, false, false, false);
		GlStateManager.color(1, 1, 1, 1);

		//super.doRender(entity, x, y, z, entityYaw, partialTicks);

		GlStateManager.colorMask(true, true, true, true);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1F, 1F, 1F, 1F - 0.5F);

		super.doRender(entity, x, y, z, entityYaw, partialTicks);

		GlStateManager.popMatrix();

	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	@Override
	protected ResourceLocation getEntityTexture(EntityDarknessMage entity)
	{
		return DARKNESS_MAGE_TEXTURES;
	}

	public static class Factory implements IRenderFactory<EntityDarknessMage> {

		@Override
		public Render<? super EntityDarknessMage> createRenderFor(RenderManager manager) {
			return new RenderDarknessMage(manager);
		}

	}


}
