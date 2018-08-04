package zombieenderman5.ghostly.common.core;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GhostlyRecipeManager {
	
	public static void preInitialization(FMLPreInitializationEvent event) {
		
		GameRegistry.addSmelting(GhostlyBlockManager.corporealiteOre, new ItemStack(GhostlyItemManager.corporealiteIngot, 1), 0.8F);
		
	}
	
}
