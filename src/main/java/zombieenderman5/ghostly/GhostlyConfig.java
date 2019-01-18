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
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int shadeSpawnRateNether = 70;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int shadeSpawnRateOverworld = 2;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int shadeSpawnRateEnd = 2;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int darknessMageSpawnRate = 0;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int spiritualTurretSpawnRate = 90;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int sickenedSpiderSpawnRate = 50;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int mutatedWolfColdForestSpawnRate = 28;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int mutatedWolfForestSpawnRate = 25;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int mutatedHorsePlainsSpawnRate = 25;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int mutatedHorseSavannaSpawnRate = 21;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int mutatedCowSpawnRate = 28;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int mutatedOcelotSpawnRate = 22;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int mutatedPigSpawnRate = 30;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int mutatedDonkeyPlainsSpawnRate = 21;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int mutatedDonkeySavannaSpawnRate = 21;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int mutatedSheepSpawnRate = 32;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int mutatedLlamaHighSavannaSpawnRate = 28;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int mutatedLlamaMountainSpawnRate = 25;
		@Config.RangeDouble(min = -0.1D, max = 1.0D)
		public double shadeDissipationLightLevel = 0.15D;
		@Config.RangeDouble(min = -0.1D, max = 1.0D)
		public double darknessMageDissipationLightLevel = 0.30D;
		public boolean shades = true;
		public boolean infestedEndermen = true;
		public boolean darknessMages = true;
		public boolean shadowRemnants = true;
		public boolean spiritualTurrets = false;
		public boolean sickenedSpiders = true;
		public boolean mutatedWolves = true;
		public boolean mutatedHorses = true;
		public boolean mutatedCows = true;
		public boolean mutatedOcelots = true;
		public boolean mutatedPigs = true;
		public boolean mutatedDonkeys = true;
		public boolean mutatedSheep = true;
		public boolean mutatedLlamas = true;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedSwordSpawnRate = 100;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int infestedEndermenSpawnRate = 0;
		public boolean infestedEndermenInfestOtherEndermen = false;
		public boolean shadesInfestEndermen = false;
		public boolean shadesPossessUndead = true;
		@Config.RangeInt(min = 0, max = 13)
		public int shadePossessionEyeType = 3;
		@Config.RangeDouble(min = 0.0D, max = 1.0D)
		public double shadowRemnantChance = 0.1D;
		@Config.RangeDouble(min = 0.0D, max = 0.99D)
		public double mutatedWolfFleeHealthPercentage = 0.25D;
		@Config.RangeDouble(min = 0.0D, max = 0.99D)
		public double mutatedHorseFleeHealthPercentage = 0.25D;
		@Config.RangeDouble(min = 0.0D, max = 0.99D)
		public double mutatedCowFleeHealthPercentage = 0.25D;
		@Config.RangeDouble(min = 0.0D, max = 0.99D)
		public double mutatedOcelotFleeHealthPercentage = 0.35D;
		@Config.RangeDouble(min = 0.0D, max = 0.99D)
		public double mutatedPigFleeHealthPercentage = 0.25D;
		@Config.RangeDouble(min = 0.0D, max = 0.99D)
		public double mutatedDonkeyFleeHealthPercentage = 0.25D;
		@Config.RangeDouble(min = 0.0D, max = 0.99D)
		public double mutatedSheepFleeHealthPercentage = 0.25D;
		@Config.RangeDouble(min = 0.0D, max = 0.99D)
		public double mutatedLlamaFleeHealthPercentage = 0.25D;
		@Config.RangeDouble(min = -0.1D, max = 0.99D)
		public double mutatedHorseArmorSpawnIronChance = -0.1D;
		@Config.RangeDouble(min = -0.1D, max = 0.99D)
		public double mutatedHorseArmorSpawnGoldChance = -0.1D;
		@Config.RangeDouble(min = -0.1D, max = 0.99D)
		public double mutatedHorseArmorSpawnDiamondChance = -0.1D;
			
		public boolean possessedWitherSkeletonFortressRestriction = true;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedZombieSpawnRate = 30;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedSkeletonSpawnRate = 30;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedSkeletonFortressSpawnRate = 1;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedHuskSpawnRate = 50;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedStraySpawnRate = 50;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedWitherSkeletonSpawnRate = 50;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedWitherSkeletonFortressSpawnRate = 4;
		public boolean possessedZombies = true;
		public boolean possessedHusks = true;
		public boolean possessedSkeletons = true;
		public boolean possessedStrays = true;
		public boolean possessedWitherSkeletons = true;
			
		public boolean possessedWitherHunchboneFortressRestriction = true;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedHunchboneSpawnRate = 30;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedHunchboneFortressSpawnRate = 1;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedWitherHunchboneSpawnRate = 30;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedWitherHunchboneFortressSpawnRate = 4;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedWitheredZombieSpawnRate = 50;
		public boolean possessedHunchbones = true;
		public boolean possessedWitherHunchbones = true;
		public boolean possessedWitheredZombies = true;
			
		public boolean possessedBoxerWitherSkeletonFortressRestriction = true;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedBoxerZombieSpawnRate = 30;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedBoxerSkeletonSpawnRate = 30;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedBoxerSkeletonFortressSpawnRate = 1;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedBoxerHuskSpawnRate = 50;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedBoxerStraySpawnRate = 50;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedBoxerWitherSkeletonSpawnRate = 30;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int possessedBoxerWitherSkeletonFortressSpawnRate = 4;
		public boolean possessedBoxerZombies = true;
		public boolean possessedBoxerHusks = true;
		public boolean possessedBoxerSkeletons = true;
		public boolean possessedBoxerStrays = true;
		public boolean possessedBoxerWitherSkeletons = true;
		
	}

	public static class Aesthetics {
		
		public boolean shadePossessUndeadEffects = true;
			
		public boolean multicolorPossessedZombieEyes = true;
		public boolean multicolorPossessedHuskEyes = true;
		public boolean multicolorPossessedSkeletonEyes = true;
		public boolean multicolorPossessedStrayEyes = true;
		public boolean multicolorPossessedWitherSkeletonEyes = true;
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
			
		public boolean multicolorPossessedHunchboneEyes = true;
		public boolean multicolorPossessedWitherHunchboneEyes = true;
		public boolean multicolorPossessedWitheredZombieEyes = true;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int multicolorPossessedHunchboneEyesChance = 5;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int multicolorPossessedWitherHunchboneEyesChance = 5;
		@Config.RangeInt(min = 0, max = Integer.MAX_VALUE)
		public int multicolorPossessedWitheredZombieEyesChance = 5;
			
		public boolean multicolorPossessedBoxerZombieEyes = true;
		public boolean multicolorPossessedBoxerHuskEyes = true;
		public boolean multicolorPossessedBoxerSkeletonEyes = true;
		public boolean multicolorPossessedBoxerStrayEyes = true;
		public boolean multicolorPossessedBoxerWitherSkeletonEyes = true;
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
