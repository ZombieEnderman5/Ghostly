package zombieenderman5.ghostly.client.entity.rendering.monster;

import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.client.entity.rendering.layer.LayerSickenedSpiderEyes;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedBoxerHusk;
import zombieenderman5.ghostly.common.entity.monster.EntityShade;
import zombieenderman5.ghostly.common.entity.monster.EntitySickenedSpider;

@SideOnly(Side.CLIENT)
public class RenderSickenedSpider<T extends EntitySickenedSpider> extends RenderLiving<T>
{
    private static final ResourceLocation SICKENED_SPIDER_TEXTURES = new ResourceLocation("ghostly:textures/entity/sickened_spider/sickened_spider.png");
    
    public static final Factory FACTORY = new Factory();

    public RenderSickenedSpider(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelSpider(), 1.0F);
        this.addLayer(new LayerSickenedSpiderEyes(this));
    }

    @Override
    protected float getDeathMaxRotation(T entityLivingBaseIn)
    {
        return 180.0F;
    }

    @Override
	public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {

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
    
    @Override
    protected void preRenderCallback(T entitylivingbaseIn, float partialTickTime)
    {
        float f = 0.9F;
        GlStateManager.scale(0.9F, 0.9F, 0.9F);
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }
    
    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(T entity)
    {
        return SICKENED_SPIDER_TEXTURES;
    }
    
    public static class Factory implements IRenderFactory<EntitySickenedSpider> {

		@Override
		public Render<? super EntitySickenedSpider> createRenderFor(RenderManager manager) {
			return new RenderSickenedSpider(manager);
		}

	}
    
}