package zombieenderman5.ghostly.client.entity.rendering.monster;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.GhostlyReference;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelSpiritualTurret;
import zombieenderman5.ghostly.common.entity.monster.EntitySpiritualTurret;

@SideOnly(Side.CLIENT)
public class RenderSpiritualTurret extends RenderLiving<EntitySpiritualTurret> {

	private static final ResourceLocation SPIRITUAL_TURRET_TEXTURES = new ResourceLocation(GhostlyReference.MOD_ID, "textures/entity/spiritual_turret.png");

	public static final Factory FACTORY = new Factory();

	public RenderSpiritualTurret(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelSpiritualTurret(), 1.0F);

	}

	@Override
	public void doRender(EntitySpiritualTurret entity, double x, double y, double z, float entityYaw, float partialTicks) {

		GlStateManager.pushMatrix();
		GlStateManager.disableBlend();
		GlStateManager.colorMask(false, false, false, false);
		GlStateManager.color(1, 1, 1, 1);

		//super.doRender(entity, x, y, z, entityYaw, partialTicks);

		GlStateManager.colorMask(true, true, true, true);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1F, 1F, 1F, 1F - 0.1F);

		super.doRender(entity, x, y, z, entityYaw, partialTicks);

		GlStateManager.popMatrix();

	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	@Override
	protected ResourceLocation getEntityTexture(EntitySpiritualTurret entity)
	{
		return SPIRITUAL_TURRET_TEXTURES;
	}

	public static class Factory implements IRenderFactory<EntitySpiritualTurret> {

		@Override
		public Render<? super EntitySpiritualTurret> createRenderFor(RenderManager manager) {
			return new RenderSpiritualTurret(manager);
		}

	}


}
