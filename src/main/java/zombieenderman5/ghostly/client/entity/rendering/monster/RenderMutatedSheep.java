package zombieenderman5.ghostly.client.entity.rendering.monster;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.GhostlyReference;
import zombieenderman5.ghostly.client.entity.rendering.layer.LayerMutatedSheepWool;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelMutatedSheep;
import zombieenderman5.ghostly.common.entity.monster.EntityMutatedSheep;

@SideOnly(Side.CLIENT)
public class RenderMutatedSheep extends RenderLiving<EntityMutatedSheep> {

	private static final ResourceLocation MUTATED_SHEEP_TEXTURES = new ResourceLocation(GhostlyReference.MOD_ID, "textures/entity/mutated_sheep.png");

	public static final Factory FACTORY = new Factory();

	public RenderMutatedSheep(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelMutatedSheep(), 0.5F);
		
		this.addLayer(new LayerMutatedSheepWool(this));
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	@Override
	protected ResourceLocation getEntityTexture(EntityMutatedSheep entity)
	{
		return MUTATED_SHEEP_TEXTURES;
	}

	public static class Factory implements IRenderFactory<EntityMutatedSheep> {

		@Override
		public Render<? super EntityMutatedSheep> createRenderFor(RenderManager manager) {
			return new RenderMutatedSheep(manager);
		}

	}


}
