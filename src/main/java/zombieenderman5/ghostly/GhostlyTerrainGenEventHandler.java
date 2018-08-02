package zombieenderman5.ghostly;

import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.gen.structure.MapGenNetherBridge;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedBoxerSkeleton;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedBoxerWitherSkeleton;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedHunchbone;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedSkeleton;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedWitherHunchbone;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedWitherSkeleton;

public class GhostlyTerrainGenEventHandler {
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onMapGenBaseGenerate(InitMapGenEvent event) {
		
		if (event.getNewGen() != null && event.getNewGen() instanceof MapGenNetherBridge) {
			
			MapGenNetherBridge fortress = (MapGenNetherBridge) event.getNewGen();
			
			if (GhostlyConfig.MOBS.possessedWitherSkeletons && GhostlyConfig.MOBS.possessedWitherSkeletonFortressSpawnRate != 0) fortress.getSpawnList().add(new SpawnListEntry(EntityPossessedWitherSkeleton.class, GhostlyConfig.MOBS.possessedWitherSkeletonFortressSpawnRate, 5, 5));
			if (GhostlyConfig.MOBS.possessedSkeletons && GhostlyConfig.MOBS.possessedSkeletonFortressSpawnRate != 0) fortress.getSpawnList().add(new SpawnListEntry(EntityPossessedSkeleton.class, GhostlyConfig.MOBS.possessedSkeletonFortressSpawnRate, 5, 5));
			if (GhostlyConfig.MOBS.possessedBoxerWitherSkeletons && GhostlyConfig.MOBS.possessedBoxerWitherSkeletonFortressSpawnRate != 0 && Loader.isModLoaded("theboxingdead")) fortress.getSpawnList().add(new SpawnListEntry(EntityPossessedBoxerWitherSkeleton.class, GhostlyConfig.MOBS.possessedBoxerWitherSkeletonFortressSpawnRate, 5, 5));
			if (GhostlyConfig.MOBS.possessedBoxerSkeletons && GhostlyConfig.MOBS.possessedBoxerSkeletonFortressSpawnRate != 0 && Loader.isModLoaded("theboxingdead")) fortress.getSpawnList().add(new SpawnListEntry(EntityPossessedBoxerSkeleton.class, GhostlyConfig.MOBS.possessedBoxerSkeletonFortressSpawnRate, 5, 5));
			if (GhostlyConfig.MOBS.possessedWitherHunchbones && GhostlyConfig.MOBS.possessedWitherHunchboneFortressSpawnRate != 0 && Loader.isModLoaded("hardcoredimensionexpansion")) fortress.getSpawnList().add(new SpawnListEntry(EntityPossessedWitherHunchbone.class, GhostlyConfig.MOBS.possessedWitherHunchboneFortressSpawnRate, 5, 5));
			if (GhostlyConfig.MOBS.possessedHunchbones && GhostlyConfig.MOBS.possessedHunchboneFortressSpawnRate != 0 && Loader.isModLoaded("hardcoredimensionexpansion")) fortress.getSpawnList().add(new SpawnListEntry(EntityPossessedHunchbone.class, GhostlyConfig.MOBS.possessedHunchboneFortressSpawnRate, 5, 5));
			
		}
		
	}
	
}
