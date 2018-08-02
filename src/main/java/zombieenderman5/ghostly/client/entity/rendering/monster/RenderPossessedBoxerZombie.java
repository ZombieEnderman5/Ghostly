package zombieenderman5.ghostly.client.entity.rendering.monster;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.client.entity.rendering.layer.LayerPossessedBoxerZombieEyes;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelPossessedZombie;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedBoxerZombie;

@SideOnly(Side.CLIENT)
public class RenderPossessedBoxerZombie extends RenderBiped<EntityZombie>
{
	private static final ResourceLocation RES_POSSESSED_BOXER_ZOMBIE_GLOVELESS = new ResourceLocation("textures/entity/zombie/zombie.png");
	private static final ResourceLocation RES_POSSESSED_BOXER_ZOMBIE_RED_GLOVES = new ResourceLocation("ghostly:textures/entity/possessed_boxer_zombie/possessed_boxer_zombie_red_gloves.png");
	private static final ResourceLocation RES_POSSESSED_BOXER_ZOMBIE_BLUE_GLOVES = new ResourceLocation("ghostly:textures/entity/possessed_boxer_zombie/possessed_boxer_zombie_blue_gloves.png");
    
    public static final Factory FACTORY = new Factory();

    public RenderPossessedBoxerZombie(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelPossessedZombie(), 0.5F);
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
        	@Override
            protected void initArmor()
            {
                this.modelLeggings = new ModelZombie(0.5F, true);
                this.modelArmor = new ModelZombie(1.0F, true);
            }
        };
        this.addLayer(layerbipedarmor);
        this.addLayer(new LayerPossessedBoxerZombieEyes(this));
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(EntityZombie zombie)
    {
    	EntityPossessedBoxerZombie entity = (EntityPossessedBoxerZombie) zombie;
    	
    	if (entity.getGlovesType() == 0) {
    	
    		return RES_POSSESSED_BOXER_ZOMBIE_RED_GLOVES;
    		
    	} else if (entity.getGlovesType() == 1) {
    		
    		return RES_POSSESSED_BOXER_ZOMBIE_BLUE_GLOVES;
    		
    	}
    	
    	return RES_POSSESSED_BOXER_ZOMBIE_GLOVELESS;
    }
    
    public static class Factory implements IRenderFactory<EntityPossessedBoxerZombie> {

		@Override
		public Render<? super EntityPossessedBoxerZombie> createRenderFor(RenderManager manager) {
			return new RenderPossessedBoxerZombie(manager);
		}

	}
    
}