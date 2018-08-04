package zombieenderman5.ghostly.client.entity.rendering.monster;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.GhostlyReference;
import zombieenderman5.ghostly.common.entity.monster.EntityShade;

@SideOnly(Side.CLIENT)
public class RenderShade extends RenderBiped<EntityShade> {

	private static final ResourceLocation SHADE_TEXTURES = new ResourceLocation(GhostlyReference.MOD_ID, "textures/entity/shade.png");

	public static final Factory FACTORY = new Factory();

	public RenderShade(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelBiped(), 0.5F);

	}

	@Override
	public void doRender(EntityShade entity, double x, double y, double z, float entityYaw, float partialTicks) {

		GlStateManager.pushMatrix();
		GlStateManager.disableBlend();
		GlStateManager.colorMask(false, false, false, false);
		GlStateManager.color(1, 1, 1, 1);

		//super.doRender(entity, x, y, z, entityYaw, partialTicks);

		GlStateManager.colorMask(true, true, true, true);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1F, 1F, 1F, 1F - 0.7F);

		super.doRender(entity, x, y, z, entityYaw, partialTicks);

		GlStateManager.popMatrix();

	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	@Override
	protected ResourceLocation getEntityTexture(EntityShade entity)
	{
		return SHADE_TEXTURES;
	}

	public static class Factory implements IRenderFactory<EntityShade> {

		@Override
		public Render<? super EntityShade> createRenderFor(RenderManager manager) {
			return new RenderShade(manager);
		}

	}


}
