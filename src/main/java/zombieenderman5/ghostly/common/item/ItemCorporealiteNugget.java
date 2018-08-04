package zombieenderman5.ghostly.common.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import zombieenderman5.ghostly.client.core.GhostlyCreativeTabManager;
import zombieenderman5.ghostly.common.core.GhostlyItemManager;

public class ItemCorporealiteNugget extends Item {
	
	public ItemCorporealiteNugget() {
		
		setUnlocalizedName("corporealite_nugget");
		setRegistryName("corporealite_nugget");
		setCreativeTab(GhostlyCreativeTabManager.miscellaneous);
		
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack)
    {
        return GhostlyItemManager.CORPOREAL_RARITY;
    }
	
}
