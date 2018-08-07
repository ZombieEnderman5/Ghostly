package zombieenderman5.ghostly.common.core;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import zombieenderman5.ghostly.GhostlyReference;

public class GhostlyLootTableManager {
	
	public static final ResourceLocation ENTITIES_SHADE = new ResourceLocation(GhostlyReference.MOD_ID, "entities/shade");
	public static final ResourceLocation ENTITIES_SPIRITUAL_TURRET = new ResourceLocation(GhostlyReference.MOD_ID, "entities/spiritual_turret");
	public static final ResourceLocation ENTITIES_SICKENED_SPIDER = new ResourceLocation(GhostlyReference.MOD_ID, "entities/sickened_spider");
	public static final ResourceLocation ENTITIES_DARKNESS_MAGE = new ResourceLocation(GhostlyReference.MOD_ID, "entities/darkness_mage");
	
	public static void preInitialization(FMLPreInitializationEvent event) {
		
		LootTableList.register(ENTITIES_SHADE);
		LootTableList.register(ENTITIES_SPIRITUAL_TURRET);
		LootTableList.register(ENTITIES_SICKENED_SPIDER);
		LootTableList.register(ENTITIES_DARKNESS_MAGE);
		
	}
	
}
