package zombieenderman5.ghostly.common.core;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zombieenderman5.ghostly.GhostlyReference;

public class GhostlyLootTableManager {
	
	public static final ResourceLocation ENTITIES_SHADE = new ResourceLocation(GhostlyReference.MOD_ID, "entities/shade");
	
	public static void preInitialization(FMLPreInitializationEvent event) {
		
		LootTableList.register(ENTITIES_SHADE);
		
	}
	
}
