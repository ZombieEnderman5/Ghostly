package zombieenderman5.ghostly.common.item;

import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import zombieenderman5.ghostly.client.core.GhostlyCreativeTabManager;
import zombieenderman5.ghostly.common.core.GhostlyItemManager;

public class ItemSickenedSpiderEye extends ItemFood {
	
	public ItemSickenedSpiderEye() {
		
		super(2, 0.8F, false);
		
		setUnlocalizedName("sickened_spider_eye");
		setRegistryName("sickened_spider_eye");
		setCreativeTab(GhostlyCreativeTabManager.foodstuffs);
		setPotionEffect(new PotionEffect(MobEffects.POISON, 100, 1), 1.0F);
		
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack)
    {
        return GhostlyItemManager.VENOM_RARITY;
    }
	
}
