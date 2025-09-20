package zombieenderman5.ghostly.common.item;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import zombieenderman5.ghostly.Ghostly;

public class ItemSickenedSpiderEye extends Item {
	
	public ItemSickenedSpiderEye() {

		super(new Properties()
				.group(Ghostly.FOOD)
				.food(new Food.Builder()
						.hunger(2)
						.saturation(0.8F)
						.effect(new EffectInstance(Effects.POISON, 100, 1), 1.0F)
						.build()
				));
	}
}
