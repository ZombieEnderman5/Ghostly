package zombieenderman5.ghostly.client.entity.rendering.monster;

import gatocreador887.hardcoredimensionexpansion.util.HDEReference;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.client.entity.rendering.layer.LayerPossessedHunchboneEyes;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelPossessedHunchbone;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelPossessedSkeleton;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedHunchbone;

@SideOnly(Side.CLIENT)
public class RenderPossessedHunchbone extends RenderBiped<EntityPossessedHunchbone>
{
	private static final ResourceLocation POSSESSED_HUNCHBONE_TEXTURES = new ResourceLocation(HDEReference.ID, "textures/entity/hunchbone/hunchbone.png");

    public static final Factory FACTORY = new Factory();
    
    public RenderPossessedHunchbone(RenderManager renderManagerIn)
    {
    	super(renderManagerIn, new ModelPossessedHunchbone(), 0.5F);
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
        	@Override
            protected void initArmor()
            {
                this.modelLeggings = new ModelPossessedHunchbone(0.5F, true);
                this.modelArmor = new ModelPossessedHunchbone(1.0F, true);
            }
        };
        this.addLayer(layerbipedarmor);
        this.addLayer(new LayerPossessedHunchboneEyes(this));
    }

    @Override
    public void transformHeldFull3DItemLayer()
    {
        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(EntityPossessedHunchbone entity)
    {
        return POSSESSED_HUNCHBONE_TEXTURES;
    }
    
    public static class Factory implements IRenderFactory<EntityPossessedHunchbone> {

		@Override
		public Render<? super EntityPossessedHunchbone> createRenderFor(RenderManager manager) {
			return new RenderPossessedHunchbone(manager);
		}

	}
    
}