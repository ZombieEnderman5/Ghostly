package zombieenderman5.ghostly.client.entity.rendering.monster;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.GhostlyReference;
import zombieenderman5.ghostly.common.entity.monster.EntityShadowRemnant;
import zombieenderman5.ghostly.common.entity.projectile.AbstractShadowOrb;

@SideOnly(Side.CLIENT)
public class RenderShadowRemnant extends Render<EntityShadowRemnant>
{
	private static final ResourceLocation SHADOW_REMNANT_TEXTURE = new ResourceLocation(GhostlyReference.MOD_ID, "textures/entity/shadow_remnant.png");
    private static final ResourceLocation SHADOW_ORB_TEXTURE = new ResourceLocation(GhostlyReference.MOD_ID, "textures/entity/shadow_orb.png");
    
    public static final Factory SHADOW_REMNANT_FACTORY = new Factory();

    public RenderShadowRemnant(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    @Override
    public void doRender(EntityShadowRemnant entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
    	GlStateManager.pushMatrix();
    	GlStateManager.colorMask(true, true, true, true);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1F, 1F, 1F, 1F - 0.8F);
    	float s = entity.getSize() * 0.5F;
        GlStateManager.pushMatrix();
        this.bindEntityTexture(entity);
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(s, s, s);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        float f = 1.0F;
        float f1 = 0.5F;
        float f2 = 0.25F;
        GlStateManager.rotate(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate((float)(this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * -this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);

        if (this.renderOutlines)
        {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }

        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
        bufferbuilder.pos(-0.5D, -0.25D, 0.0D).tex(0.0D, 1.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
        bufferbuilder.pos(0.5D, -0.25D, 0.0D).tex(1.0D, 1.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
        bufferbuilder.pos(0.5D, 0.75D, 0.0D).tex(1.0D, 0.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
        bufferbuilder.pos(-0.5D, 0.75D, 0.0D).tex(0.0D, 0.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
        tessellator.draw();

        if (this.renderOutlines)
        {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(EntityShadowRemnant entity)
    {
        return SHADOW_ORB_TEXTURE;
    }
    
    public static class Factory implements IRenderFactory<EntityShadowRemnant> {

		@Override
		public Render<? super EntityShadowRemnant> createRenderFor(RenderManager manager) {
			
			return new RenderShadowRemnant(manager);
			
		}

	}
}