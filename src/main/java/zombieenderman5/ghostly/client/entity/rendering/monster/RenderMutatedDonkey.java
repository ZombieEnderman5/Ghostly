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
import zombieenderman5.ghostly.client.entity.rendering.model.ModelMutatedHorse;
import zombieenderman5.ghostly.common.entity.monster.EntityMutatedDonkey;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedBoxerHusk;

@SideOnly(Side.CLIENT)
public class RenderMutatedDonkey extends RenderLiving<EntityMutatedDonkey> {

	private static final ResourceLocation MUTATED_DONKEY_TEXTURES = new ResourceLocation(GhostlyReference.MOD_ID, "textures/entity/mutated_donkey.png");

	public static final Factory FACTORY = new Factory();

	public RenderMutatedDonkey(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelMutatedHorse(true), 0.5F);
		
	}

	protected void preRenderCallback(EntityMutatedDonkey entitylivingbaseIn, float partialTickTime)
    {
        float f = 0.75F;
        GlStateManager.scale(0.75F, 0.75F, 0.75F);
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }
	
	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	@Override
	protected ResourceLocation getEntityTexture(EntityMutatedDonkey entity)
	{
		return MUTATED_DONKEY_TEXTURES;
	}

	public static class Factory implements IRenderFactory<EntityMutatedDonkey> {

		@Override
		public Render<? super EntityMutatedDonkey> createRenderFor(RenderManager manager) {
			return new RenderMutatedDonkey(manager);
		}

	}


}
