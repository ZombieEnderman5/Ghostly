package zombieenderman5.ghostly.client.entity.rendering.monster;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerEndermanEyes;
import net.minecraft.client.renderer.entity.layers.LayerHeldBlock;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.GhostlyReference;
import zombieenderman5.ghostly.client.entity.rendering.layer.LayerInfestedEndermanEyes;
import zombieenderman5.ghostly.client.entity.rendering.layer.LayerInfestedEndermanHeldBlock;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderPossessedHusk.Factory;
import zombieenderman5.ghostly.common.entity.monster.EntityInfestedEnderman;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedHusk;

@SideOnly(Side.CLIENT)
public class RenderInfestedEnderman extends RenderLiving<EntityInfestedEnderman>
{
    private static final ResourceLocation INFESTED_ENDERMAN_TEXTURES = new ResourceLocation(GhostlyReference.MOD_ID, "textures/entity/infested_enderman.png");
    
    private final Random rnd = new Random();
    
    public static final Factory FACTORY = new Factory();

    public RenderInfestedEnderman(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelEnderman(0.0F), 0.5F);
        this.addLayer(new LayerInfestedEndermanEyes(this));
        this.addLayer(new LayerInfestedEndermanHeldBlock(this));
    }

    @Override
    public ModelEnderman getMainModel()
    {
        return (ModelEnderman)super.getMainModel();
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    @Override
    public void doRender(EntityInfestedEnderman entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
    	GlStateManager.pushMatrix();
		GlStateManager.disableBlend();
		GlStateManager.colorMask(false, false, false, false);
		GlStateManager.color(1, 1, 1, 1);

		super.doRender(entity, x, y, z, entityYaw, partialTicks);

		GlStateManager.colorMask(true, true, true, true);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1F, 1F, 1F, 1F - 0.4F);

		super.doRender(entity, x, y, z, entityYaw, partialTicks);

		GlStateManager.popMatrix();
    	
        IBlockState iblockstate = entity.getHeldBlockState();
        ModelEnderman modelenderman = this.getMainModel();
        modelenderman.isCarrying = iblockstate != null;
        modelenderman.isAttacking = entity.isScreaming();

        if (entity.isScreaming())
        {
            x += this.rnd.nextGaussian() * 0.0175D;
            z += this.rnd.nextGaussian() * 0.0175D;
        }

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(EntityInfestedEnderman entity)
    {
        return INFESTED_ENDERMAN_TEXTURES;
    }
    
    public static class Factory implements IRenderFactory<EntityInfestedEnderman> {

		@Override
		public Render<? super EntityInfestedEnderman> createRenderFor(RenderManager manager) {
			return new RenderInfestedEnderman(manager);
		}

	}
    
}