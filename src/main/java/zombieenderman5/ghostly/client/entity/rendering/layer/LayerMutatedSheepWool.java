package zombieenderman5.ghostly.client.entity.rendering.layer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.GhostlyReference;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelMutatedSheep;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelMutatedSheepWool;
import zombieenderman5.ghostly.common.entity.monster.EntityMutatedSheep;

@SideOnly(Side.CLIENT)
public class LayerMutatedSheepWool implements LayerRenderer<EntityMutatedSheep> {
	
    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/sheep/sheep_fur.png");
    private final RenderLivingBase<?> renderer;
    private final ModelMutatedSheepWool layerModel = new ModelMutatedSheepWool();

    public LayerMutatedSheepWool(RenderLivingBase<?> rendererIn) {
        this.renderer = rendererIn;
    }

    @Override
    public void doRenderLayer(EntityMutatedSheep entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.layerModel.setModelAttributes(this.renderer.getMainModel());
        this.layerModel.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
        if (entitylivingbaseIn.hasCustomName() && "jeb_".equals(entitylivingbaseIn.getCustomNameTag())) {
            int i1 = 25;
            int i = entitylivingbaseIn.ticksExisted / 25 + entitylivingbaseIn.getEntityId();
            int j = EnumDyeColor.values().length;
            int k = i % j;
            int l = (i + 1) % j;
            float f = ((float)(entitylivingbaseIn.ticksExisted % 25) + partialTicks) / 25.0F;
            float[] afloat1 = EntityMutatedSheep.getDyeRgb(EnumDyeColor.byMetadata(k));
            float[] afloat2 = EntityMutatedSheep.getDyeRgb(EnumDyeColor.byMetadata(l));
            GlStateManager.color(afloat1[0] * (1.0F - f) + afloat2[0] * f, afloat1[1] * (1.0F - f) + afloat2[1] * f, afloat1[2] * (1.0F - f) + afloat2[2] * f);
        } else {
            float[] afloat = EntityMutatedSheep.getDyeRgb(entitylivingbaseIn.getFleeceColor());
            GlStateManager.color(afloat[0], afloat[1], afloat[2]);
        }
        this.renderer.bindTexture(TEXTURE);
        this.layerModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    @Override
    public boolean shouldCombineTextures() {
        return true;
    }
}
