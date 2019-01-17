package zombieenderman5.ghostly.client.entity.rendering.layer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.passive.HorseArmorType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zombieenderman5.ghostly.GhostlyReference;
import zombieenderman5.ghostly.client.entity.rendering.model.ModelMutatedHorse;
import zombieenderman5.ghostly.common.entity.monster.EntityMutatedHorse;

@SideOnly(Side.CLIENT)
public class LayerMutatedHorseArmor implements LayerRenderer<EntityMutatedHorse>
{
    private static final ResourceLocation RES_IRON_ARMOR = new ResourceLocation(GhostlyReference.MOD_ID, "textures/entity/mutated_horse/mutated_horse_armor_iron.png");
    private static final ResourceLocation RES_GOLD_ARMOR = new ResourceLocation(GhostlyReference.MOD_ID, "textures/entity/mutated_horse/mutated_horse_armor_gold.png");
    private static final ResourceLocation RES_DIAMOND_ARMOR = new ResourceLocation(GhostlyReference.MOD_ID, "textures/entity/mutated_horse/mutated_horse_armor_diamond.png");
    private final RenderLivingBase<?> renderer;
    private final ModelMutatedHorse layerModel = new ModelMutatedHorse(false);

    public LayerMutatedHorseArmor(RenderLivingBase<?> p_i47183_1_)
    {
        this.renderer = p_i47183_1_;
    }

    @Override
    public void doRenderLayer(EntityMutatedHorse entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        this.layerModel.setModelAttributes(this.renderer.getMainModel());
        this.layerModel.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        if (entitylivingbaseIn.getMutatedHorseArmorType() == HorseArmorType.IRON) {
        	this.renderer.bindTexture(RES_IRON_ARMOR);
        } else if (entitylivingbaseIn.getMutatedHorseArmorType() == HorseArmorType.GOLD) {
        	this.renderer.bindTexture(RES_GOLD_ARMOR);
        } else if (entitylivingbaseIn.getMutatedHorseArmorType() == HorseArmorType.DIAMOND) {
        	this.renderer.bindTexture(RES_DIAMOND_ARMOR);
        }
        this.layerModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    @Override
    public boolean shouldCombineTextures()
    {
        return true;
    }
}
