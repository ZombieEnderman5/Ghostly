package zombieenderman5.ghostly.common.item;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import zombieenderman5.ghostly.Ghostly;

public class ItemDarknessMageFlesh extends Item {

    public ItemDarknessMageFlesh() {
        super(new Properties()
                .group(Ghostly.FOOD)
                .food(new Food.Builder()
                        .hunger(6)
                        .saturation(0.3F)
                        .effect(() -> new EffectInstance(Effects.POISON, 600, 1), 1.0F) // 100% chance
                        .effect(() -> new EffectInstance(Effects.HUNGER, 600, 1), 1.0F)
                        .effect(() -> new EffectInstance(Effects.NAUSEA, 600, 0), 1.0F)
                        .build()
                ));
    }
}
