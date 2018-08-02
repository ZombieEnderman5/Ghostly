package zombieenderman5.ghostly.client.entity.rendering.monster;

import net.minecraft.client.model.ModelSkeleton;
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
import zombieenderman5.ghostly.client.entity.rendering.layer.LayerPossessedBoxerStrayClothing;
import zombieenderman5.ghostly.client.entity.rendering.layer.LayerPossessedBoxerStrayEyes;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelPossessedSkeleton;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedBoxerStray;

@SideOnly(Side.CLIENT)
public class RenderPossessedBoxerStray extends RenderBiped<AbstractSkeleton>
{
	private static final ResourceLocation RES_POSSESSED_BOXER_STRAY_GLOVELESS = new ResourceLocation("textures/entity/skeleton/stray.png");
	private static final ResourceLocation RES_POSSESSED_BOXER_STRAY_RED_GLOVES = new ResourceLocation("ghostly:textures/entity/possessed_boxer_stray/possessed_boxer_stray_red_gloves.png");
	private static final ResourceLocation RES_POSSESSED_BOXER_STRAY_BLUE_GLOVES = new ResourceLocation("ghostly:textures/entity/possessed_boxer_stray/possessed_boxer_stray_blue_gloves.png");

    public static final Factory FACTORY = new Factory();
    
    public RenderPossessedBoxerStray(RenderManager p_i47191_1_)
    {
        super(p_i47191_1_, new ModelPossessedSkeleton(), 0.5F);
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
        	@Override
            protected void initArmor()
            {
                this.modelLeggings = new ModelSkeleton(0.5F, true);
                this.modelArmor = new ModelSkeleton(1.0F, true);
            }
        };
        this.addLayer(layerbipedarmor);
        this.addLayer(new LayerPossessedBoxerStrayEyes(this));
        this.addLayer(new LayerPossessedBoxerStrayClothing(this));
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(AbstractSkeleton skeleton)
    {
    	EntityPossessedBoxerStray entity = (EntityPossessedBoxerStray) skeleton;
    	
    	if (entity.getGlovesType() == 0) {
    	
    		return RES_POSSESSED_BOXER_STRAY_RED_GLOVES;
    		
    	} else if (entity.getGlovesType() == 1) {
    		
    		return RES_POSSESSED_BOXER_STRAY_BLUE_GLOVES;
    		
    	}
    	
    	return RES_POSSESSED_BOXER_STRAY_GLOVELESS;
    }
    
    @Override
    public void transformHeldFull3DItemLayer()
    {
        GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
    }
    
    public static class Factory implements IRenderFactory<EntityPossessedBoxerStray> {

		@Override
		public Render<? super EntityPossessedBoxerStray> createRenderFor(RenderManager manager) {
			return new RenderPossessedBoxerStray(manager);
		}

	}
}