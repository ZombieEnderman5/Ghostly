package zombieenderman5.ghostly.client.entity.rendering.monster;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.GhostlyReference;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelMutatedOcelot;
import zombieenderman5.ghostly.common.entity.monster.EntityMutatedOcelot;

@SideOnly(Side.CLIENT)
public class RenderMutatedOcelot extends RenderLiving<EntityMutatedOcelot> {

	private static final ResourceLocation MUTATED_OCELOT_TEXTURES = new ResourceLocation(GhostlyReference.MOD_ID, "textures/entity/mutated_ocelot.png");

	public static final Factory FACTORY = new Factory();

	public RenderMutatedOcelot(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelMutatedOcelot(), 0.5F);
		
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	@Override
	protected ResourceLocation getEntityTexture(EntityMutatedOcelot entity)
	{
		return MUTATED_OCELOT_TEXTURES;
	}

	public static class Factory implements IRenderFactory<EntityMutatedOcelot> {

		@Override
		public Render<? super EntityMutatedOcelot> createRenderFor(RenderManager manager) {
			return new RenderMutatedOcelot(manager);
		}

	}


}
