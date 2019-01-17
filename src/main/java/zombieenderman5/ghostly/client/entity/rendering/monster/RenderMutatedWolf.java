package zombieenderman5.ghostly.client.entity.rendering.monster;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.GhostlyReference;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelMutatedWolf;
import zombieenderman5.ghostly.common.entity.monster.EntityMutatedWolf;

@SideOnly(Side.CLIENT)
public class RenderMutatedWolf extends RenderLiving<EntityMutatedWolf> {

	private static final ResourceLocation MUTATED_WOLF_TEXTURES = new ResourceLocation(GhostlyReference.MOD_ID, "textures/entity/mutated_wolf.png");

	public static final Factory FACTORY = new Factory();

	public RenderMutatedWolf(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelMutatedWolf(), 0.5F);
		
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	@Override
	protected ResourceLocation getEntityTexture(EntityMutatedWolf entity)
	{
		return MUTATED_WOLF_TEXTURES;
	}

	public static class Factory implements IRenderFactory<EntityMutatedWolf> {

		@Override
		public Render<? super EntityMutatedWolf> createRenderFor(RenderManager manager) {
			return new RenderMutatedWolf(manager);
		}

	}


}
