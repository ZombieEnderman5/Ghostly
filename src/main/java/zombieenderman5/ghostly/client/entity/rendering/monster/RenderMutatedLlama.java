package zombieenderman5.ghostly.client.entity.rendering.monster;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.GhostlyReference;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelMutatedLlama;
import zombieenderman5.ghostly.common.entity.monster.EntityMutatedLlama;

@SideOnly(Side.CLIENT)
public class RenderMutatedLlama extends RenderLiving<EntityMutatedLlama> {

	private static final ResourceLocation[] MUTATED_LLAMA_TEXTURES = new ResourceLocation[] {new ResourceLocation(GhostlyReference.MOD_ID, "textures/entity/mutated_llama/mutated_llama_creamy.png"), new ResourceLocation(GhostlyReference.MOD_ID, "textures/entity/mutated_llama/mutated_llama_white.png"), new ResourceLocation(GhostlyReference.MOD_ID, "textures/entity/mutated_llama/mutated_llama_brown.png"), new ResourceLocation(GhostlyReference.MOD_ID, "textures/entity/mutated_llama/mutated_llama_gray.png")};

	public static final Factory FACTORY = new Factory();

	public RenderMutatedLlama(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelMutatedLlama(), 0.5F);
		
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	@Override
	protected ResourceLocation getEntityTexture(EntityMutatedLlama entity)
	{
		return MUTATED_LLAMA_TEXTURES[entity.getVariant()];
	}

	public static class Factory implements IRenderFactory<EntityMutatedLlama> {

		@Override
		public Render<? super EntityMutatedLlama> createRenderFor(RenderManager manager) {
			return new RenderMutatedLlama(manager);
		}

	}


}
