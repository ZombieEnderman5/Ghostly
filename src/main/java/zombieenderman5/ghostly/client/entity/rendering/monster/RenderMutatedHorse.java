package zombieenderman5.ghostly.client.entity.rendering.monster;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.LayeredTexture;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.GhostlyReference;
import zombieenderman5.ghostly.client.entity.rendering.layer.LayerMutatedHorseArmor;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelMutatedHorse;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelMutatedWolf;
import zombieenderman5.ghostly.common.entity.monster.EntityMutatedHorse;
import zombieenderman5.ghostly.common.entity.monster.EntityMutatedWolf;

@SideOnly(Side.CLIENT)
public class RenderMutatedHorse extends RenderLiving<EntityMutatedHorse> {

	private static final Map<String, ResourceLocation> LAYERED_LOCATION_CACHE = Maps.<String, ResourceLocation>newHashMap();
	public static final Factory FACTORY = new Factory();

	public RenderMutatedHorse(RenderManager renderManagerIn)
	{
		super(renderManagerIn, new ModelMutatedHorse(false), 0.5F);
		this.addLayer(new LayerMutatedHorseArmor(this));
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(EntityMutatedHorse entity) {
        String s = entity.getMutatedHorseTexture();
        ResourceLocation resourcelocation = LAYERED_LOCATION_CACHE.get(s);

        if (resourcelocation == null) {
            resourcelocation = new ResourceLocation(s);
            Minecraft.getMinecraft().getTextureManager().loadTexture(resourcelocation, new LayeredTexture(entity.getVariantTexturePaths()));
            LAYERED_LOCATION_CACHE.put(s, resourcelocation);
        }

        return resourcelocation;
    }

	public static class Factory implements IRenderFactory<EntityMutatedHorse> {

		@Override
		public Render<? super EntityMutatedHorse> createRenderFor(RenderManager manager) {
			return new RenderMutatedHorse(manager);
		}

	}


}
