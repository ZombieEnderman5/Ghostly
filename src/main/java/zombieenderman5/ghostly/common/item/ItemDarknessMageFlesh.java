package zombieenderman5.ghostly.common.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import zombieenderman5.ghostly.client.core.GhostlyCreativeTabManager;
import zombieenderman5.ghostly.common.core.GhostlyItemManager;

public class ItemDarknessMageFlesh extends ItemFood {

	public ItemDarknessMageFlesh() {
		
		super(6, 0.3F, true);
		
		setUnlocalizedName("darkness_mage_flesh");
		setRegistryName("darkness_mage_flesh");
		setCreativeTab(GhostlyCreativeTabManager.foodstuffs);
		
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        super.onItemUseFinish(stack, worldIn, entityLiving);
        entityLiving.addPotionEffect(new PotionEffect(MobEffects.POISON, 600, 1));
        entityLiving.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 600, 1));
        entityLiving.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 600, 0));
        return stack;
    }
	
	@Override
	public EnumRarity getRarity(ItemStack stack)
    {
        return GhostlyItemManager.SHADOW_RARITY;
    }
	
}
