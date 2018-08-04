package zombieenderman5.ghostly.client.creative_tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import zombieenderman5.ghostly.common.core.GhostlyBlockManager;
import zombieenderman5.ghostly.common.core.GhostlyItemManager;

public class TabGhostlyBlocks extends CreativeTabs {

	public TabGhostlyBlocks(String label) {
		
		super(label);
		
	}

	@Override
	public ItemStack getTabIconItem() {
		
		return new ItemStack(GhostlyBlockManager.corporealiteBlock);
		
	}

}
