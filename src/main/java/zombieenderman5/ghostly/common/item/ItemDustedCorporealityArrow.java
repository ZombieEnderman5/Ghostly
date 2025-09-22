package zombieenderman5.ghostly.common.item;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import zombieenderman5.ghostly.Ghostly;
import zombieenderman5.ghostly.common.entity.projectile.EntityDustedCorporealityArrow;

public class ItemDustedCorporealityArrow extends ArrowItem {

	public ItemDustedCorporealityArrow() {
		super(new Properties()
				.group(Ghostly.ITEMS));
	}

	@Override
	public boolean isInfinite(ItemStack stack, ItemStack bow, PlayerEntity player) {
		int enchant = EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, bow);
		return enchant <= 0 ? false : this.getClass() == ItemDustedCorporealityArrow.class;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("ghostly.dusted_arrow_of_corporeality.damage_information").mergeStyle(TextFormatting.GOLD));
		tooltip.add(new TranslationTextComponent("ghostly.dusted_arrow_of_corporeality.arrow_information").mergeStyle(TextFormatting.GOLD));
		tooltip.add(new TranslationTextComponent("ghostly.dusted_arrow_of_corporeality.bow_information").mergeStyle(TextFormatting.GOLD));
		tooltip.add(new TranslationTextComponent("ghostly.dusted_arrow_of_corporeality.wither_information").mergeStyle(TextFormatting.GOLD));
	}

	@Override
	public AbstractArrowEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
		ItemStack activeBow = shooter.getActiveItemStack();

		// If the shooter is using YOUR custom bow → spawn dusted arrow entity
		/*if (activeBow.getItem() == RegistryHandler.CORPOREALITY_BOW.get()) {
			return new EntityDustedCorporealityArrow(world, shooter);
		}*/

		// Otherwise → spawn vanilla arrow entity
		return new EntityDustedCorporealityArrow(world, shooter);	}
}