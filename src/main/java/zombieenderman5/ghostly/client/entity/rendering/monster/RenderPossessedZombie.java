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
import zombieenderman5.ghostly.client.entity.rendering.layer.LayerPossessedZombieEyes;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelPossessedZombie;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedZombie;
import zombieenderman5.ghostly.common.entity.monster.EntityShade;

@SideOnly(Side.CLIENT)
public class RenderPossessedZombie extends RenderBiped<EntityZombie>
{
    private static final ResourceLocation POSSESSED_ZOMBIE_TEXTURES = new ResourceLocation("textures/entity/zombie/zombie.png");
    
    public static final Factory FACTORY = new Factory();

    public RenderPossessedZombie(RenderManager renderManagerIn)
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
        this.addLayer(new LayerPossessedZombieEyes(this));
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(EntityZombie entity)
    {
        return POSSESSED_ZOMBIE_TEXTURES;
    }
    
    public static class Factory implements IRenderFactory<EntityPossessedZombie> {

		@Override
		public Render<? super EntityPossessedZombie> createRenderFor(RenderManager manager) {
			return new RenderPossessedZombie(manager);
		}

	}
    
}