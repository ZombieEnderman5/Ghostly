package zombieenderman5.ghostly.client.entity.rendering.layer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.GhostlyReference;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelShashkra;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderShashkra;
import zombieenderman5.ghostly.common.entity.monster.EntityShashkra;

@SideOnly(Side.CLIENT)
public class LayerShashkraGlowingParts implements LayerRenderer<EntityShashkra> {
	
	private static final ResourceLocation RES_SHASHKRA_GLOW_SPELLCASTER = new ResourceLocation(GhostlyReference.MOD_ID, "textures/entity/shashkra/shashkra_glow_spellcaster.png");
	private static final ResourceLocation RES_SHASHKRA_GLOW = new ResourceLocation(GhostlyReference.MOD_ID, "textures/entity/shashkra/shashkra_glow.png");
	private final RenderShashkra shashkraRenderer;
	
	public LayerShashkraGlowingParts(RenderShashkra shashkraRendererIn) {
		
		shashkraRenderer = shashkraRendererIn;
	}
	
	@Override
	public void doRenderLayer(EntityShashkra entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		
		if (entitylivingbaseIn.getSpellcaster()) {
			shashkraRenderer.bindTexture(RES_SHASHKRA_GLOW_SPELLCASTER);
		} else if (entitylivingbaseIn.getFireballing()) {
			shashkraRenderer.bindTexture(RES_SHASHKRA_GLOW);
		}
		
		GlStateManager.enableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
		GlStateManager.disableLighting();
		GlStateManager.depthMask(!entitylivingbaseIn.isInvisible());
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 61680.0F, 0.0F);
		GlStateManager.enableLighting();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		if (entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty()) {
			((ModelShashkra) shashkraRenderer.getMainModel()).changeBipedHeadwearVisibility(false);
		} else {
			((ModelShashkra) shashkraRenderer.getMainModel()).changeBipedHeadwearVisibility(true);
		}
		Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
		shashkraRenderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
		shashkraRenderer.setLightmap(entitylivingbaseIn);
		GlStateManager.depthMask(true);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
		
	}
	
	@Override
	public boolean shouldCombineTextures() {
		
		return false;
		
	}
}