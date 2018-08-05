package zombieenderman5.ghostly.client.creative_tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import zombieenderman5.ghostly.common.core.GhostlyItemManager;

public class TabGhostlyFoodstuffs extends CreativeTabs {

	public TabGhostlyFoodstuffs(String label) {
		
		super(label);
		
	}

	@Override
	public ItemStack getTabIconItem() {
		
		return new ItemStack(GhostlyItemManager.sickenedSpiderEye);
		
	}

}
