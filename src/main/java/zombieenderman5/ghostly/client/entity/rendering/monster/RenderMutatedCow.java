package zombieenderman5.ghostly.client.entity.rendering.monster;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.GhostlyReference;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelMutatedCow;
import zombieenderman5.ghostly.common.entity.monster.EntityMutatedCow;

@SideOnly(Side.CLIENT)
public class RenderMutatedCow extends RenderLiving<EntityMutatedCow> {

	private static final ResourceLocation MUTATED_COW_TEXTURES = new ResourceLocation(GhostlyReference.MOD_ID, "textures/entity/mutated_cow.png");

	public static final Factory FACTORY = new Factory();

	public RenderMutatedCow(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelMutatedCow(), 0.5F);
		
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	@Override
	protected ResourceLocation getEntityTexture(EntityMutatedCow entity)
	{
		return MUTATED_COW_TEXTURES;
	}

	public static class Factory implements IRenderFactory<EntityMutatedCow> {

		@Override
		public Render<? super EntityMutatedCow> createRenderFor(RenderManager manager) {
			return new RenderMutatedCow(manager);
		}

	}


}
