package zombieenderman5.ghostly.client.entity.rendering.projectile;

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
import zombieenderman5.ghostly.common.entity.projectile.AbstractShadowOrb;

@SideOnly(Side.CLIENT)
public class RenderShadowOrb extends Render<AbstractShadowOrb>
{
    private static final ResourceLocation SHADOW_ORB_TEXTURE = new ResourceLocation(GhostlyReference.MOD_ID, "textures/entity/shadow_orb.png");
    private final float scale;
    
    public static final Factory SHADOW_ORB_FACTORY = new Factory(0.6F);
    public static final Factory GIANT_SHADOW_ORB_FACTORY = new Factory(2.0F);
    public static final Factory TINY_SHADOW_ORB_FACTORY = new Factory(0.2F);

    public RenderShadowOrb(RenderManager renderManagerIn, float scaleIn)
    {
        super(renderManagerIn);
        this.scale = scaleIn;
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    @Override
    public void doRender(AbstractShadowOrb entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        this.bindEntityTexture(entity);
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(this.scale, this.scale, this.scale);
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
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(AbstractShadowOrb entity)
    {
        return SHADOW_ORB_TEXTURE;
    }
    
    public static class Factory implements IRenderFactory<AbstractShadowOrb> {
    	
    	private final float scale;
    	
    	public Factory(float scaleIn) {
    		
    		this.scale = scaleIn;
    		
    	}

		@Override
		public Render<? super AbstractShadowOrb> createRenderFor(RenderManager manager) {
			
			return new RenderShadowOrb(manager, this.scale);
			
		}

	}
}