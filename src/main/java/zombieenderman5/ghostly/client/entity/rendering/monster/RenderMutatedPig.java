package zombieenderman5.ghostly.client.entity.rendering.monster;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.GhostlyReference;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelMutatedPig;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelMutatedWolf;
import zombieenderman5.ghostly.common.entity.monster.EntityMutatedPig;

@SideOnly(Side.CLIENT)
public class RenderMutatedPig extends RenderLiving<EntityMutatedPig> {

	private static final ResourceLocation MUTATED_PIG_TEXTURES = new ResourceLocation(GhostlyReference.MOD_ID, "textures/entity/mutated_pig.png");

	public static final Factory FACTORY = new Factory();

	public RenderMutatedPig(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelMutatedPig(), 0.5F);
		
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	@Override
	protected ResourceLocation getEntityTexture(EntityMutatedPig entity)
	{
		return MUTATED_PIG_TEXTURES;
	}

	public static class Factory implements IRenderFactory<EntityMutatedPig> {

		@Override
		public Render<? super EntityMutatedPig> createRenderFor(RenderManager manager) {
			return new RenderMutatedPig(manager);
		}

	}


}
