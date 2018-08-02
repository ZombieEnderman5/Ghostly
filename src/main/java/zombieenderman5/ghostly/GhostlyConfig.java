package zombieenderman5.ghostly;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = GhostlyReference.MOD_ID)
@Config.LangKey("ghostly.config.title")
public class GhostlyConfig {

	public static boolean logging = false;
	
	public static final Mobs MOBS = new Mobs();
	public static final Aesthetics AESTHETICS = new Aesthetics();
	public static final Audio AUDIO = new Audio();
	
	public static class Mobs {
		
		public boolean possessedSwords = false;
		public boolean possessedWitherSkeletonFortressRestriction = true;
		public boolean possessedBoxerWitherSkeletonFortressRestriction = true;
		public boolean possessedWitherHunchboneFortressRestriction = true;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedZombieSpawnRate = 50;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedSkeletonSpawnRate = 50;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedSkeletonFortressSpawnRate = 1;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedHuskSpawnRate = 70;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedStraySpawnRate = 70;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedWitherSkeletonSpawnRate = 70;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedWitherSkeletonFortressSpawnRate = 4;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int shadeSpawnRateNether = 70;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int shadeSpawnRateOverworld = 2;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int shadeSpawnRateEnd = 2;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedBoxerZombieSpawnRate = 50;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedBoxerSkeletonSpawnRate = 50;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedBoxerSkeletonFortressSpawnRate = 1;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedBoxerHuskSpawnRate = 70;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedBoxerStraySpawnRate = 70;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedBoxerWitherSkeletonSpawnRate = 50;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedBoxerWitherSkeletonFortressSpawnRate = 4;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedHunchboneSpawnRate = 50;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedHunchboneFortressSpawnRate = 1;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedWitherHunchboneSpawnRate = 50;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedWitherHunchboneFortressSpawnRate = 4;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedWitheredZombieSpawnRate = 70;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int darknessMageSpawnRate = 0;
		@Config.RangeDouble(min = -0.1D, max = 1.0D)
		public double shadeDissipationLightLevel = 0.15D;
		@Config.RangeDouble(min = -0.1D, max = 1.0D)
		public double darknessMageDissipationLightLevel = 0.30D;
		public boolean possessedZombies = true;
		public boolean possessedHusks = true;
		public boolean possessedSkeletons = true;
		public boolean possessedStrays = true;
		public boolean possessedWitherSkeletons = true;
		public boolean possessedBoxerZombies = true;
		public boolean possessedBoxerHusks = true;
		public boolean possessedBoxerSkeletons = true;
		public boolean possessedBoxerStrays = true;
		public boolean possessedBoxerWitherSkeletons = true;
		public boolean shades = true;
		public boolean infestedEndermen = true;
		public boolean possessedHunchbones = true;
		public boolean possessedWitherHunchbones = true;
		public boolean possessedWitheredZombies = true;
		public boolean darknessMages = true;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedSwordSpawnRate = 100;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int infestedEndermenSpawnRate = 0;
		public boolean infestedEndermenInfestOtherEndermen = false;
		public boolean shadesInfestEndermen = false;
		public boolean shadesPossessUndead = true;
		@Config.RangeInt(min = 0, max = 13)
		public int shadePossessionEyeType = 3;
		
	}

	public static class Aesthetics {
		
		public boolean multicolorPossessedZombieEyes = true;
		public boolean multicolorPossessedHuskEyes = true;
		public boolean multicolorPossessedSkeletonEyes = true;
		public boolean multicolorPossessedStrayEyes = true;
		public boolean multicolorPossessedWitherSkeletonEyes = true;
		public boolean multicolorPossessedBoxerZombieEyes = true;
		public boolean multicolorPossessedBoxerHuskEyes = true;
		public boolean multicolorPossessedBoxerSkeletonEyes = true;
		public boolean multicolorPossessedBoxerStrayEyes = true;
		public boolean multicolorPossessedBoxerWitherSkeletonEyes = true;
		public boolean multicolorPossessedHunchboneEyes = true;
		public boolean multicolorPossessedWitherHunchboneEyes = true;
		public boolean multicolorPossessedWitheredZombieEyes = true;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int multicolorPossessedZombieEyesChance = 5;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int multicolorPossessedHuskEyesChance = 5;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int multicolorPossessedSkeletonEyesChance = 5;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int multicolorPossessedStrayEyesChance = 5;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int multicolorPossessedWitherSkeletonEyesChance = 5;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int multicolorPossessedBoxerZombieEyesChance = 5;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int multicolorPossessedBoxerHuskEyesChance = 5;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int multicolorPossessedBoxerSkeletonEyesChance = 5;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int multicolorPossessedBoxerStrayEyesChance = 5;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int multicolorPossessedBoxerWitherSkeletonEyesChance = 5;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int multicolorPossessedHunchboneEyesChance = 5;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int multicolorPossessedWitherHunchboneEyesChance = 5;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int multicolorPossessedWitheredZombieEyesChance = 5;
		public boolean shadePossessUndeadEffects = true;
		
	}
	
	public static class Audio {
		
		public boolean alternateShadeAudio = true;
		public boolean alternateDarknessMageAudio = true;
		
	}
	
	@Mod.EventBusSubscriber(modid = GhostlyReference.MOD_ID)
	private static class EventHandler {

		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {

			if (event.getModID().equals(GhostlyReference.MOD_ID)) {

				ConfigManager.sync(GhostlyReference.MOD_ID, Config.Type.INSTANCE);

			}

		}

	}

}
