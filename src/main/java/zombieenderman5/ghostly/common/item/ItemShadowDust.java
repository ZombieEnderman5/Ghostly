package zombieenderman5.ghostly.common.item;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import zombieenderman5.ghostly.Ghostly;


public class ItemShadowDust extends Item {
	
	public ItemShadowDust() {
		super(new Properties()
				.group(Ghostly.ITEMS));
	}

	@Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("ghostly.shadow_dust.damage_information").mergeStyle(TextFormatting.DARK_GRAY));
		tooltip.add(new TranslationTextComponent("ghostly.shadow_dust.wither_information").mergeStyle(TextFormatting.DARK_GRAY));
	}
}