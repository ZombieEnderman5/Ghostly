package zombieenderman5.ghostly.common.core;

import java.util.ArrayList;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import zombieenderman5.ghostly.Ghostly;
import zombieenderman5.ghostly.GhostlyConfig;
import zombieenderman5.ghostly.GhostlyReference;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderDarknessMage;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderInfestedEnderman;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderMutatedCow;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderMutatedDonkey;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderMutatedHorse;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderMutatedLlama;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderMutatedOcelot;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderMutatedPig;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderMutatedSheep;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderMutatedWolf;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderPossessedBoxerHusk;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderPossessedBoxerSkeleton;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderPossessedBoxerStray;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderPossessedBoxerWitherSkeleton;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderPossessedBoxerZombie;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderPossessedHunchbone;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderPossessedHusk;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderPossessedSkeleton;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderPossessedStray;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderPossessedSword;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderPossessedWitherHunchbone;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderPossessedWitherSkeleton;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderPossessedWitheredZombie;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderPossessedZombie;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderShade;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderShadowRemnant;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderSickenedSpider;
import zombieenderman5.ghostly.client.entity.rendering.monster.RenderSpiritualTurret;
import zombieenderman5.ghostly.client.entity.rendering.projectile.RenderCorporealityArrow;
import zombieenderman5.ghostly.client.entity.rendering.projectile.RenderDustedCorporealityArrow;
import zombieenderman5.ghostly.client.entity.rendering.projectile.RenderDustedVenomCorporealityArrow;
import zombieenderman5.ghostly.client.entity.rendering.projectile.RenderShadowOrb;
import zombieenderman5.ghostly.client.entity.rendering.projectile.RenderSpectralCorporealityArrow;
import zombieenderman5.ghostly.client.entity.rendering.projectile.RenderSpectralVenomArrow;
import zombieenderman5.ghostly.client.entity.rendering.projectile.RenderSpectralVenomCorporealityArrow;
import zombieenderman5.ghostly.client.entity.rendering.projectile.RenderTippedCorporealityArrow;
import zombieenderman5.ghostly.client.entity.rendering.projectile.RenderVenomArrow;
import zombieenderman5.ghostly.client.entity.rendering.projectile.RenderVenomCorporealityArrow;
import zombieenderman5.ghostly.common.entity.monster.EntityDarknessMage;
import zombieenderman5.ghostly.common.entity.monster.EntityInfestedEnderman;
import zombieenderman5.ghostly.common.entity.monster.EntityMutatedCow;
import zombieenderman5.ghostly.common.entity.monster.EntityMutatedDonkey;
import zombieenderman5.ghostly.common.entity.monster.EntityMutatedHorse;
import zombieenderman5.ghostly.common.entity.monster.EntityMutatedLlama;
import zombieenderman5.ghostly.common.entity.monster.EntityMutatedOcelot;
import zombieenderman5.ghostly.common.entity.monster.EntityMutatedPig;
import zombieenderman5.ghostly.common.entity.monster.EntityMutatedSheep;
import zombieenderman5.ghostly.common.entity.monster.EntityMutatedWolf;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedBoxerHusk;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedBoxerSkeleton;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedBoxerStray;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedBoxerWitherSkeleton;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedBoxerZombie;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedHunchbone;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedHusk;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedSkeleton;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedStray;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedSword;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedWitherHunchbone;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedWitherSkeleton;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedWitheredZombie;
import zombieenderman5.ghostly.common.entity.monster.EntityPossessedZombie;
import zombieenderman5.ghostly.common.entity.monster.EntityShade;
import zombieenderman5.ghostly.common.entity.monster.EntityShadowRemnant;
import zombieenderman5.ghostly.common.entity.monster.EntitySickenedSpider;
import zombieenderman5.ghostly.common.entity.monster.EntitySpiritualTurret;
import zombieenderman5.ghostly.common.entity.projectile.EntityCorporealityArrow;
import zombieenderman5.ghostly.common.entity.projectile.EntityDustedCorporealityArrow;
import zombieenderman5.ghostly.common.entity.projectile.EntityDustedVenomCorporealityArrow;
import zombieenderman5.ghostly.common.entity.projectile.EntityGiantShadowOrb;
import zombieenderman5.ghostly.common.entity.projectile.EntityShadowOrb;
import zombieenderman5.ghostly.common.entity.projectile.EntitySpectralCorporealityArrow;
import zombieenderman5.ghostly.common.entity.projectile.EntitySpectralVenomArrow;
import zombieenderman5.ghostly.common.entity.projectile.EntitySpectralVenomCorporealityArrow;
import zombieenderman5.ghostly.common.entity.projectile.EntityTinyShadowOrb;
import zombieenderman5.ghostly.common.entity.projectile.EntityTippedCorporealityArrow;
import zombieenderman5.ghostly.common.entity.projectile.EntityVenomArrow;
import zombieenderman5.ghostly.common.entity.projectile.EntityVenomCorporealityArrow;

public class GhostlyEntityManager {

	public static Ghostly mod = Ghostly.instance;

	public static final ArrayList<Biome> OVERWORLD_BIOMES = new ArrayList<>();
	public static final ArrayList<Biome> NETHER_BIOMES = new ArrayList<>();
	public static final ArrayList<Biome> DESERT_BIOMES = new ArrayList<>();
	public static final ArrayList<Biome> ICE_BIOMES = new ArrayList<>();
	public static final ArrayList<Biome> END_BIOMES = new ArrayList<>();
	public static final ArrayList<Biome> COLD_FOREST_BIOMES = new ArrayList<>();
	public static final ArrayList<Biome> FOREST_BIOMES = new ArrayList<>();
	public static final ArrayList<Biome> PLAINS_BIOMES = new ArrayList<>();
	public static final ArrayList<Biome> SAVANNA_BIOMES = new ArrayList<>();
	public static final ArrayList<Biome> JUNGLE_BIOMES = new ArrayList<>();
	public static final ArrayList<Biome> MOUNTAIN_BIOMES = new ArrayList<>();
	public static final ArrayList<Biome> HIGH_SAVANNA_BIOMES = new ArrayList<>();
	
	public static void preInitialization(FMLPreInitializationEvent event) {

		int id = 0;

		if (GhostlyConfig.MOBS.shades) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "shade"), EntityShade.class, GhostlyReference.MOD_ID + ":shade", id++, mod, 80, 1, false, 0x000000, 0xE91111);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "shade"), EntityShade.class, GhostlyReference.MOD_ID + ":shade", id++, mod, 80, 1, false);
		}
		if (GhostlyConfig.MOBS.possessedZombies) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_zombie"), EntityPossessedZombie.class, GhostlyReference.MOD_ID + ":possessed_zombie", id++, mod, 64, 1, false, 44975, 7969893);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_zombie"), EntityPossessedZombie.class, GhostlyReference.MOD_ID + ":possessed_zombie", id++, mod, 64, 1, false);
		}
		if (GhostlyConfig.MOBS.possessedSkeletons) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_skeleton"), EntityPossessedSkeleton.class, GhostlyReference.MOD_ID + ":possessed_skeleton", id++, mod, 64, 1, false, 12698049, 4802889);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_skeleton"), EntityPossessedSkeleton.class, GhostlyReference.MOD_ID + ":possessed_skeleton", id++, mod, 64, 1, false);
		}
		if (GhostlyConfig.MOBS.possessedHusks) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_husk"), EntityPossessedHusk.class, GhostlyReference.MOD_ID + ":possessed_husk", id++, mod, 64, 1, false, 7958625, 15125652);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_husk"), EntityPossessedHusk.class, GhostlyReference.MOD_ID + ":possessed_husk", id++, mod, 64, 1, false);
		}
		if (GhostlyConfig.MOBS.possessedWitherSkeletons) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_wither_skeleton"), EntityPossessedWitherSkeleton.class, GhostlyReference.MOD_ID + ":possessed_wither_skeleton", id++, mod, 64, 1, false, 1315860, 4672845);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_wither_skeleton"), EntityPossessedWitherSkeleton.class, GhostlyReference.MOD_ID + ":possessed_wither_skeleton", id++, mod, 64, 1, false);
		}
		if (GhostlyConfig.MOBS.possessedStrays) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_stray"), EntityPossessedStray.class, GhostlyReference.MOD_ID + ":possessed_stray", id++, mod, 64, 1, false, 6387319, 14543594);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_stray"), EntityPossessedStray.class, GhostlyReference.MOD_ID + ":possessed_stray", id++, mod, 64, 1, false);
		}
		if (GhostlyConfig.MOBS.infestedEndermen) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "infested_enderman"), EntityInfestedEnderman.class, GhostlyReference.MOD_ID + ":infested_enderman", id++, mod, 64, 1, false, 0x0b0404, 0x000000);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "infested_enderman"), EntityInfestedEnderman.class, GhostlyReference.MOD_ID + ":infested_enderman", id++, mod, 64, 1, false);
		}
		if (GhostlyConfig.MOBS.possessedSwords) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_sword"), EntityPossessedSword.class, GhostlyReference.MOD_ID + ":possessed_sword", id++, mod, 64, 1, false, 0x1E8A77, 0x33EBCB);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_sword"), EntityPossessedSword.class, GhostlyReference.MOD_ID + ":possessed_sword", id++, mod, 64, 1, false);
		}
		if (GhostlyConfig.MOBS.possessedBoxerZombies && Loader.isModLoaded("theboxingdead")) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_boxer_zombie"), EntityPossessedBoxerZombie.class, GhostlyReference.MOD_ID + ":possessed_boxer_zombie", id++, mod, 64, 1, false, 44975, 7969893);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_boxer_zombie"), EntityPossessedBoxerZombie.class, GhostlyReference.MOD_ID + ":possessed_boxer_zombie", id++, mod, 64, 1, false);
		}
		if (GhostlyConfig.MOBS.possessedBoxerSkeletons && Loader.isModLoaded("theboxingdead")) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_boxer_skeleton"), EntityPossessedBoxerSkeleton.class, GhostlyReference.MOD_ID + ":possessed_boxer_skeleton", id++, mod, 64, 1, false, 12698049, 4802889);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_boxer_skeleton"), EntityPossessedBoxerSkeleton.class, GhostlyReference.MOD_ID + ":possessed_boxer_skeleton", id++, mod, 64, 1, false);
		}
		if (GhostlyConfig.MOBS.possessedBoxerHusks && Loader.isModLoaded("theboxingdead")) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_boxer_husk"), EntityPossessedBoxerHusk.class, GhostlyReference.MOD_ID + ":possessed_boxer_husk", id++, mod, 64, 1, false, 7958625, 15125652);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_boxer_husk"), EntityPossessedBoxerHusk.class, GhostlyReference.MOD_ID + ":possessed_boxer_husk", id++, mod, 64, 1, false);
		}
		if (GhostlyConfig.MOBS.possessedBoxerWitherSkeletons && Loader.isModLoaded("theboxingdead")) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_boxer_wither_skeleton"), EntityPossessedBoxerWitherSkeleton.class, GhostlyReference.MOD_ID + ":possessed_boxer_wither_skeleton", id++, mod, 64, 1, false, 1315860, 4672845);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_boxer_wither_skeleton"), EntityPossessedBoxerWitherSkeleton.class, GhostlyReference.MOD_ID + ":possessed_boxer_wither_skeleton", id++, mod, 64, 1, false);
		}
		if (GhostlyConfig.MOBS.possessedBoxerStrays && Loader.isModLoaded("theboxingdead")) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_boxer_stray"), EntityPossessedBoxerStray.class, GhostlyReference.MOD_ID + ":possessed_boxer_stray", id++, mod, 64, 1, false, 6387319, 14543594);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_boxer_stray"), EntityPossessedBoxerStray.class, GhostlyReference.MOD_ID + ":possessed_boxer_stray", id++, mod, 64, 1, false);
		}
		if (GhostlyConfig.MOBS.possessedHunchbones && Loader.isModLoaded("hardcoredimensionexpansion")) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_hunchbone"), EntityPossessedHunchbone.class, GhostlyReference.MOD_ID + ":possessed_hunchbone", id++, mod, 64, 1, false, 12698049, 4802889);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_hunchbone"), EntityPossessedHunchbone.class, GhostlyReference.MOD_ID + ":possessed_hunchbone", id++, mod, 64, 1, false);
		}
		if (GhostlyConfig.MOBS.possessedWitherHunchbones && Loader.isModLoaded("hardcoredimensionexpansion")) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_wither_hunchbone"), EntityPossessedWitherHunchbone.class, GhostlyReference.MOD_ID + ":possessed_wither_hunchbone", id++, mod, 64, 1, false, 1315860, 4672845);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_wither_hunchbone"), EntityPossessedWitherHunchbone.class, GhostlyReference.MOD_ID + ":possessed_wither_hunchbone", id++, mod, 64, 1, false);
		}
		if (GhostlyConfig.MOBS.possessedWitheredZombies && Loader.isModLoaded("hardcoredimensionexpansion")) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_withered_zombie"), EntityPossessedWitheredZombie.class, GhostlyReference.MOD_ID + ":possessed_withered_zombie", id++, mod, 64, 1, false, 0x202323, 0x466664);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "possessed_withered_zombie"), EntityPossessedWitheredZombie.class, GhostlyReference.MOD_ID + ":possessed_withered_zombie", id++, mod, 64, 1, false);
		}
		if (GhostlyConfig.MOBS.shadowRemnants) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "shadow_remnant"), EntityShadowRemnant.class, GhostlyReference.MOD_ID + ":shadow_remnant", id++, mod, 32, 1, false, 0x000000, 0xE91111);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "shadow_remnant"), EntityShadowRemnant.class, GhostlyReference.MOD_ID + ":shadow_remnant", id++, mod, 32, 1, false);
		}
		if (GhostlyConfig.MOBS.spiritualTurrets) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "spiritual_turret"), EntitySpiritualTurret.class, GhostlyReference.MOD_ID + ":spiritual_turret", id++, mod, 64, 1, false, 0x232020, 0x1A1919);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "spiritual_turret"), EntitySpiritualTurret.class, GhostlyReference.MOD_ID + ":spiritual_turret", id++, mod, 64, 1, false);
		}
		if (GhostlyConfig.MOBS.sickenedSpiders) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "sickened_spider"), EntitySickenedSpider.class, GhostlyReference.MOD_ID + ":sickened_spider", id++, mod, 64, 1, false, 0x4B622D, 0x201F5B);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "sickened_spider"), EntitySickenedSpider.class, GhostlyReference.MOD_ID + ":sickened_spider", id++, mod, 64, 1, false);
		}
		if (GhostlyConfig.MOBS.darknessMages) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "darkness_mage"), EntityDarknessMage.class, GhostlyReference.MOD_ID + ":darkness_mage", id++, mod, 80, 1, false, 0x221F1F, 0x131111);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "darkness_mage"), EntityDarknessMage.class, GhostlyReference.MOD_ID + ":darkness_mage", id++, mod, 80, 1, false);
		}
		if (GhostlyConfig.MOBS.mutatedWolves) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "mutated_wolf"), EntityMutatedWolf.class, GhostlyReference.MOD_ID + ":mutated_wolf", id++, mod, 64, 1, false, 14144467, 13545366);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "mutated_wolf"), EntityMutatedWolf.class, GhostlyReference.MOD_ID + ":mutated_wolf", id++, mod, 64, 1, false);
		}
		if (GhostlyConfig.MOBS.mutatedHorses) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "mutated_horse"), EntityMutatedHorse.class, GhostlyReference.MOD_ID + ":mutated_horse", id++, mod, 64, 1, false, 12623485, 15656192);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "mutated_horse"), EntityMutatedHorse.class, GhostlyReference.MOD_ID + ":mutated_horse", id++, mod, 64, 1, false);
		}
		if (GhostlyConfig.MOBS.mutatedCows) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "mutated_cow"), EntityMutatedCow.class, GhostlyReference.MOD_ID + ":mutated_cow", id++, mod, 64, 1, false, 4470310, 10592673);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "mutated_cow"), EntityMutatedCow.class, GhostlyReference.MOD_ID + ":mutated_cow", id++, mod, 64, 1, false);
		}
		if (GhostlyConfig.MOBS.mutatedOcelots) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "mutated_ocelot"), EntityMutatedOcelot.class, GhostlyReference.MOD_ID + ":mutated_ocelot", id++, mod, 64, 1, false, 15720061, 5653556);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "mutated_ocelot"), EntityMutatedOcelot.class, GhostlyReference.MOD_ID + ":mutated_ocelot", id++, mod, 64, 1, false);
		}
		if (GhostlyConfig.MOBS.mutatedPigs) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "mutated_pig"), EntityMutatedPig.class, GhostlyReference.MOD_ID + ":mutated_pig", id++, mod, 64, 1, false, 15771042, 14377823);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "mutated_pig"), EntityMutatedPig.class, GhostlyReference.MOD_ID + ":mutated_pig", id++, mod, 64, 1, false);
		}
		if (GhostlyConfig.MOBS.mutatedDonkeys) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "mutated_donkey"), EntityMutatedDonkey.class, GhostlyReference.MOD_ID + ":mutated_donkey", id++, mod, 64, 1, false, 5457209, 8811878);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "mutated_donkey"), EntityMutatedDonkey.class, GhostlyReference.MOD_ID + ":mutated_donkey", id++, mod, 64, 1, false);
		}
		if (GhostlyConfig.MOBS.mutatedSheep) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "mutated_sheep"), EntityMutatedSheep.class, GhostlyReference.MOD_ID + ":mutated_sheep", id++, mod, 64, 1, false, 15198183, 16758197);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "mutated_sheep"), EntityMutatedSheep.class, GhostlyReference.MOD_ID + ":mutated_sheep", id++, mod, 64, 1, false);
		}
		if (GhostlyConfig.MOBS.mutatedLlamas) {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "mutated_llama"), EntityMutatedLlama.class, GhostlyReference.MOD_ID + ":mutated_llama", id++, mod, 64, 1, false, 12623485, 10051392);
		} else {
			EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "mutated_llama"), EntityMutatedLlama.class, GhostlyReference.MOD_ID + ":mutated_llama", id++, mod, 64, 1, false);
		}
		
		EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "shadow_orb"), EntityShadowOrb.class, GhostlyReference.MOD_ID + ":shadow_orb", id++, mod, 64, 1, true);
		EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "giant_shadow_orb"), EntityGiantShadowOrb.class, GhostlyReference.MOD_ID + ":giant_shadow_orb", id++, mod, 64, 1, true);
		EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "tiny_shadow_orb"), EntityTinyShadowOrb.class, GhostlyReference.MOD_ID + ":tiny_shadow_orb", id++, mod, 32, 1, true);
		EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "arrow_of_corporeality"), EntityCorporealityArrow.class, GhostlyReference.MOD_ID + ":arrow_of_corporeality", id++, mod, 64, 1, true);
		EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "tipped_arrow_of_corporeality"), EntityTippedCorporealityArrow.class, GhostlyReference.MOD_ID + ":tipped_arrow_of_corporeality", id++, mod, 64, 1, true);
		EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "spectral_arrow_of_corporeality"), EntitySpectralCorporealityArrow.class, GhostlyReference.MOD_ID + ":spectral_arrow_of_corporeality", id++, mod, 64, 1, true);
		EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "dusted_arrow_of_corporeality"), EntityDustedCorporealityArrow.class, GhostlyReference.MOD_ID + ":dusted_arrow_of_corporeality", id++, mod, 64, 1, true);
		EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "venom_arrow"), EntityVenomArrow.class, GhostlyReference.MOD_ID + ":venom_arrow", id++, mod, 64, 1, true);
		EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "spectral_venom_arrow"), EntitySpectralVenomArrow.class, GhostlyReference.MOD_ID + ":spectral_venom_arrow", id++, mod, 64, 1, true);
		EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "dusted_venom_arrow_of_corporeality"), EntityDustedVenomCorporealityArrow.class, GhostlyReference.MOD_ID + ":dusted_venom_arrow_of_corporeality", id++, mod, 64, 1, true);
		EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "venom_arrow_of_corporeality"), EntityVenomCorporealityArrow.class, GhostlyReference.MOD_ID + ":venom_arrow_of_corporeality", id++, mod, 64, 1, true);
		EntityRegistry.registerModEntity(new ResourceLocation(GhostlyReference.MOD_ID, "spectral_venom_arrow_of_corporeality"), EntitySpectralVenomCorporealityArrow.class, GhostlyReference.MOD_ID + ":spectral_venom_arrow_of_corporeality", id++, mod, 64, 1, true);
		
		
	}

	public static void registerEntityRenderingHandlers(FMLPreInitializationEvent event) {

		if (GhostlyConfig.MOBS.shades) RenderingRegistry.registerEntityRenderingHandler(EntityShade.class, RenderShade.FACTORY);
		if (GhostlyConfig.MOBS.possessedZombies) RenderingRegistry.registerEntityRenderingHandler(EntityPossessedZombie.class, RenderPossessedZombie.FACTORY);
		if (GhostlyConfig.MOBS.possessedSkeletons) RenderingRegistry.registerEntityRenderingHandler(EntityPossessedSkeleton.class, RenderPossessedSkeleton.FACTORY);
		if (GhostlyConfig.MOBS.possessedHusks) RenderingRegistry.registerEntityRenderingHandler(EntityPossessedHusk.class, RenderPossessedHusk.FACTORY);
		if (GhostlyConfig.MOBS.possessedWitherSkeletons) RenderingRegistry.registerEntityRenderingHandler(EntityPossessedWitherSkeleton.class, RenderPossessedWitherSkeleton.FACTORY);
		if (GhostlyConfig.MOBS.possessedStrays) RenderingRegistry.registerEntityRenderingHandler(EntityPossessedStray.class, RenderPossessedStray.FACTORY);
		if (GhostlyConfig.MOBS.infestedEndermen) RenderingRegistry.registerEntityRenderingHandler(EntityInfestedEnderman.class, RenderInfestedEnderman.FACTORY);
		if (GhostlyConfig.MOBS.possessedSwords) RenderingRegistry.registerEntityRenderingHandler(EntityPossessedSword.class, RenderPossessedSword.FACTORY);
		if (GhostlyConfig.MOBS.possessedBoxerZombies) RenderingRegistry.registerEntityRenderingHandler(EntityPossessedBoxerZombie.class, RenderPossessedBoxerZombie.FACTORY);
		if (GhostlyConfig.MOBS.possessedBoxerSkeletons) RenderingRegistry.registerEntityRenderingHandler(EntityPossessedBoxerSkeleton.class, RenderPossessedBoxerSkeleton.FACTORY);
		if (GhostlyConfig.MOBS.possessedBoxerHusks) RenderingRegistry.registerEntityRenderingHandler(EntityPossessedBoxerHusk.class, RenderPossessedBoxerHusk.FACTORY);
		if (GhostlyConfig.MOBS.possessedBoxerWitherSkeletons) RenderingRegistry.registerEntityRenderingHandler(EntityPossessedBoxerWitherSkeleton.class, RenderPossessedBoxerWitherSkeleton.FACTORY);
		if (GhostlyConfig.MOBS.possessedBoxerStrays) RenderingRegistry.registerEntityRenderingHandler(EntityPossessedBoxerStray.class, RenderPossessedBoxerStray.FACTORY);
		if (GhostlyConfig.MOBS.possessedHunchbones) RenderingRegistry.registerEntityRenderingHandler(EntityPossessedHunchbone.class, RenderPossessedHunchbone.FACTORY);
		if (GhostlyConfig.MOBS.possessedWitherHunchbones) RenderingRegistry.registerEntityRenderingHandler(EntityPossessedWitherHunchbone.class, RenderPossessedWitherHunchbone.FACTORY);
		if (GhostlyConfig.MOBS.possessedWitheredZombies) RenderingRegistry.registerEntityRenderingHandler(EntityPossessedWitheredZombie.class, RenderPossessedWitheredZombie.FACTORY);
		if (GhostlyConfig.MOBS.spiritualTurrets) RenderingRegistry.registerEntityRenderingHandler(EntitySpiritualTurret.class, RenderSpiritualTurret.FACTORY);
		if (GhostlyConfig.MOBS.sickenedSpiders) RenderingRegistry.registerEntityRenderingHandler(EntitySickenedSpider.class, RenderSickenedSpider.FACTORY);
		if (GhostlyConfig.MOBS.mutatedWolves) RenderingRegistry.registerEntityRenderingHandler(EntityMutatedWolf.class, RenderMutatedWolf.FACTORY);
		if (GhostlyConfig.MOBS.mutatedHorses) RenderingRegistry.registerEntityRenderingHandler(EntityMutatedHorse.class, RenderMutatedHorse.FACTORY);
		if (GhostlyConfig.MOBS.mutatedCows) RenderingRegistry.registerEntityRenderingHandler(EntityMutatedCow.class, RenderMutatedCow.FACTORY);
		if (GhostlyConfig.MOBS.mutatedOcelots) RenderingRegistry.registerEntityRenderingHandler(EntityMutatedOcelot.class, RenderMutatedOcelot.FACTORY);
		if (GhostlyConfig.MOBS.mutatedPigs) RenderingRegistry.registerEntityRenderingHandler(EntityMutatedPig.class, RenderMutatedPig.FACTORY);
		if (GhostlyConfig.MOBS.mutatedDonkeys) RenderingRegistry.registerEntityRenderingHandler(EntityMutatedDonkey.class, RenderMutatedDonkey.FACTORY);
		if (GhostlyConfig.MOBS.mutatedSheep) RenderingRegistry.registerEntityRenderingHandler(EntityMutatedSheep.class, RenderMutatedSheep.FACTORY);
		if (GhostlyConfig.MOBS.mutatedLlamas) RenderingRegistry.registerEntityRenderingHandler(EntityMutatedLlama.class, RenderMutatedLlama.FACTORY);
		
		if (GhostlyConfig.MOBS.darknessMages) {
			
			RenderingRegistry.registerEntityRenderingHandler(EntityDarknessMage.class, RenderDarknessMage.FACTORY);
			RenderingRegistry.registerEntityRenderingHandler(EntityGiantShadowOrb.class, RenderShadowOrb.GIANT_SHADOW_ORB_FACTORY);
			
		}
		
		if (GhostlyConfig.MOBS.darknessMages || GhostlyConfig.MOBS.spiritualTurrets) {
			
			RenderingRegistry.registerEntityRenderingHandler(EntityShadowOrb.class, RenderShadowOrb.SHADOW_ORB_FACTORY);
			
		}
		
		if (GhostlyConfig.MOBS.shadowRemnants) {
			
			RenderingRegistry.registerEntityRenderingHandler(EntityShadowRemnant.class, RenderShadowRemnant.SHADOW_REMNANT_FACTORY);
			RenderingRegistry.registerEntityRenderingHandler(EntityTinyShadowOrb.class, RenderShadowOrb.TINY_SHADOW_ORB_FACTORY);
			
		}
		
		RenderingRegistry.registerEntityRenderingHandler(EntityCorporealityArrow.class, RenderCorporealityArrow.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityTippedCorporealityArrow.class, RenderTippedCorporealityArrow.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntitySpectralCorporealityArrow.class, RenderSpectralCorporealityArrow.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityDustedCorporealityArrow.class, RenderDustedCorporealityArrow.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityVenomArrow.class, RenderVenomArrow.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntitySpectralVenomArrow.class, RenderSpectralVenomArrow.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityVenomCorporealityArrow.class, RenderVenomCorporealityArrow.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntitySpectralVenomCorporealityArrow.class, RenderSpectralVenomCorporealityArrow.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityDustedVenomCorporealityArrow.class, RenderDustedVenomCorporealityArrow.FACTORY);
		
	}

	public static void initialization(FMLInitializationEvent event) {

		Biome[] biomeArray = new Biome[0];
		Biome[] netherBiomeArray = new Biome[0];
		Biome[] desertBiomeArray = new Biome[0];
		Biome[] iceBiomeArray = new Biome[0];
		Biome[] endBiomeArray = new Biome[0];
		Biome[] coldForestBiomeArray = new Biome[0];
		Biome[] forestBiomeArray = new Biome[0];
		Biome[] plainsBiomeArray = new Biome[0];
		Biome[] savannaBiomeArray = new Biome[0];
		Biome[] jungleBiomeArray = new Biome[0];
		Biome[] mountainBiomeArray = new Biome[0];
		Biome[] highSavannaBiomeArray = new Biome[0];

		for (Biome biome : ForgeRegistries.BIOMES) {

			if (!BiomeDictionary.hasType(biome, Type.END) && !BiomeDictionary.hasType(biome, Type.NETHER) && !BiomeDictionary.hasType(biome, Type.MUSHROOM) && !BiomeDictionary.hasType(biome, Type.WATER) && !BiomeDictionary.hasType(biome, Type.VOID)) {

				OVERWORLD_BIOMES.add(biome);
				
				if (BiomeDictionary.hasType(biome, Type.HOT) && BiomeDictionary.hasType(biome, Type.DRY) && BiomeDictionary.hasType(biome, Type.SANDY) && !BiomeDictionary.hasType(biome, Type.MESA)) {
                    DESERT_BIOMES.add(biome);
                }
				
				if (BiomeDictionary.hasType(biome, Type.COLD) && BiomeDictionary.hasType(biome, Type.SNOWY) && (BiomeDictionary.hasType(biome, Type.WASTELAND) || BiomeDictionary.hasType(biome, Type.MOUNTAIN))) {
					ICE_BIOMES.add(biome);
				}

				if (BiomeDictionary.hasType(biome, Type.COLD) && BiomeDictionary.hasType(biome, Type.FOREST) ) {
					COLD_FOREST_BIOMES.add(biome);
				}
				
				if (!BiomeDictionary.hasType(biome, Type.COLD) && BiomeDictionary.hasType(biome, Type.FOREST) ) {
					FOREST_BIOMES.add(biome);
				}
				
				if (!BiomeDictionary.hasType(biome, Type.SAVANNA) && BiomeDictionary.hasType(biome, Type.PLAINS)) {
					PLAINS_BIOMES.add(biome);
				}
				
				if (BiomeDictionary.hasType(biome, Type.SAVANNA)) {
					SAVANNA_BIOMES.add(biome);
				}
				
				if (BiomeDictionary.hasType(biome, Type.JUNGLE) && biome != Biomes.JUNGLE_EDGE) {
					JUNGLE_BIOMES.add(biome);
				}
				
				if (BiomeDictionary.hasType(biome, Type.MOUNTAIN) && !BiomeDictionary.hasType(biome, Type.COLD) && !BiomeDictionary.hasType(biome, Type.SNOWY)) {
					MOUNTAIN_BIOMES.add(biome);
				}
				
				if (BiomeDictionary.hasType(biome, Type.SAVANNA) && biome.getBaseHeight() > 1.1F) {
					HIGH_SAVANNA_BIOMES.add(biome);
				}
				
			} else if (BiomeDictionary.hasType(biome, Type.NETHER)) {
				NETHER_BIOMES.add(biome);
			} else if (BiomeDictionary.hasType(biome, Type.END)) {
				END_BIOMES.add(biome);
			}

		}

		biomeArray = OVERWORLD_BIOMES.toArray(biomeArray);
		netherBiomeArray = NETHER_BIOMES.toArray(netherBiomeArray);
		desertBiomeArray = DESERT_BIOMES.toArray(desertBiomeArray);
		iceBiomeArray = ICE_BIOMES.toArray(iceBiomeArray);
		endBiomeArray = END_BIOMES.toArray(endBiomeArray);
		coldForestBiomeArray = COLD_FOREST_BIOMES.toArray(coldForestBiomeArray);
		forestBiomeArray = FOREST_BIOMES.toArray(forestBiomeArray);
		plainsBiomeArray = PLAINS_BIOMES.toArray(plainsBiomeArray);
		savannaBiomeArray = SAVANNA_BIOMES.toArray(savannaBiomeArray);
		jungleBiomeArray = JUNGLE_BIOMES.toArray(jungleBiomeArray);
		mountainBiomeArray = MOUNTAIN_BIOMES.toArray(mountainBiomeArray);
		highSavannaBiomeArray = HIGH_SAVANNA_BIOMES.toArray(highSavannaBiomeArray);

		if (GhostlyConfig.MOBS.shades && GhostlyConfig.MOBS.shadeSpawnRateNether != 0) EntityRegistry.addSpawn(EntityShade.class, GhostlyConfig.MOBS.shadeSpawnRateNether, 1, 5, EnumCreatureType.MONSTER, netherBiomeArray);
		if (GhostlyConfig.MOBS.shades && GhostlyConfig.MOBS.shadeSpawnRateOverworld != 0) EntityRegistry.addSpawn(EntityShade.class, GhostlyConfig.MOBS.shadeSpawnRateOverworld, 1, 3, EnumCreatureType.MONSTER, biomeArray);
		if (GhostlyConfig.MOBS.shades && GhostlyConfig.MOBS.shadeSpawnRateEnd != 0) EntityRegistry.addSpawn(EntityShade.class, GhostlyConfig.MOBS.shadeSpawnRateEnd, 1, 3, EnumCreatureType.MONSTER, endBiomeArray);
		if (GhostlyConfig.MOBS.possessedZombies && GhostlyConfig.MOBS.possessedZombieSpawnRate != 0) EntityRegistry.addSpawn(EntityPossessedZombie.class, GhostlyConfig.MOBS.possessedZombieSpawnRate, 2, 4, EnumCreatureType.MONSTER, biomeArray);
		if (GhostlyConfig.MOBS.possessedSkeletons && GhostlyConfig.MOBS.possessedSkeletonSpawnRate != 0) EntityRegistry.addSpawn(EntityPossessedSkeleton.class, GhostlyConfig.MOBS.possessedSkeletonSpawnRate, 2, 4, EnumCreatureType.MONSTER, biomeArray);
		if (GhostlyConfig.MOBS.possessedHusks && GhostlyConfig.MOBS.possessedHuskSpawnRate != 0) EntityRegistry.addSpawn(EntityPossessedHusk.class, GhostlyConfig.MOBS.possessedHuskSpawnRate, 2, 4, EnumCreatureType.MONSTER, desertBiomeArray);
		if (GhostlyConfig.MOBS.possessedStrays && GhostlyConfig.MOBS.possessedStraySpawnRate != 0) EntityRegistry.addSpawn(EntityPossessedStray.class, GhostlyConfig.MOBS.possessedStraySpawnRate, 2, 4, EnumCreatureType.MONSTER, iceBiomeArray);
		if (GhostlyConfig.MOBS.infestedEndermen && GhostlyConfig.MOBS.infestedEndermenSpawnRate != 0) EntityRegistry.addSpawn(EntityInfestedEnderman.class, GhostlyConfig.MOBS.infestedEndermenSpawnRate, 2, 4, EnumCreatureType.MONSTER, endBiomeArray);
		if (GhostlyConfig.MOBS.possessedWitherSkeletons && GhostlyConfig.MOBS.possessedWitherSkeletonSpawnRate != 0 && !GhostlyConfig.MOBS.possessedWitherSkeletonFortressRestriction) EntityRegistry.addSpawn(EntityPossessedWitherSkeleton.class, GhostlyConfig.MOBS.possessedWitherSkeletonSpawnRate, 2, 4, EnumCreatureType.MONSTER, netherBiomeArray);
		if (GhostlyConfig.MOBS.possessedSwords && GhostlyConfig.MOBS.possessedSwordSpawnRate != 0) EntityRegistry.addSpawn(EntityPossessedSword.class, GhostlyConfig.MOBS.possessedSwordSpawnRate, 1, 1, EnumCreatureType.MONSTER, netherBiomeArray);
		if (GhostlyConfig.MOBS.possessedBoxerZombies && GhostlyConfig.MOBS.possessedBoxerZombieSpawnRate != 0 && Loader.isModLoaded("theboxingdead")) EntityRegistry.addSpawn(EntityPossessedBoxerZombie.class, GhostlyConfig.MOBS.possessedBoxerZombieSpawnRate, 2, 4, EnumCreatureType.MONSTER, biomeArray);
		if (GhostlyConfig.MOBS.possessedBoxerSkeletons && GhostlyConfig.MOBS.possessedBoxerSkeletonSpawnRate != 0 && Loader.isModLoaded("theboxingdead")) EntityRegistry.addSpawn(EntityPossessedBoxerSkeleton.class, GhostlyConfig.MOBS.possessedBoxerSkeletonSpawnRate, 2, 4, EnumCreatureType.MONSTER, biomeArray);
		if (GhostlyConfig.MOBS.possessedBoxerHusks && GhostlyConfig.MOBS.possessedBoxerHuskSpawnRate != 0 && Loader.isModLoaded("theboxingdead")) EntityRegistry.addSpawn(EntityPossessedBoxerHusk.class, GhostlyConfig.MOBS.possessedBoxerHuskSpawnRate, 2, 4, EnumCreatureType.MONSTER, desertBiomeArray);
		if (GhostlyConfig.MOBS.possessedBoxerStrays && GhostlyConfig.MOBS.possessedBoxerStraySpawnRate != 0 && Loader.isModLoaded("theboxingdead")) EntityRegistry.addSpawn(EntityPossessedBoxerStray.class, GhostlyConfig.MOBS.possessedBoxerStraySpawnRate, 2, 4, EnumCreatureType.MONSTER, iceBiomeArray);
		if (GhostlyConfig.MOBS.possessedBoxerWitherSkeletons && GhostlyConfig.MOBS.possessedBoxerWitherSkeletonSpawnRate != 0 && !GhostlyConfig.MOBS.possessedBoxerWitherSkeletonFortressRestriction && Loader.isModLoaded("theboxingdead")) EntityRegistry.addSpawn(EntityPossessedBoxerWitherSkeleton.class, GhostlyConfig.MOBS.possessedBoxerWitherSkeletonSpawnRate, 2, 4, EnumCreatureType.MONSTER, netherBiomeArray);
		if (GhostlyConfig.MOBS.possessedHunchbones && GhostlyConfig.MOBS.possessedHunchboneSpawnRate != 0 && Loader.isModLoaded("hardcoredimensionexpansion")) EntityRegistry.addSpawn(EntityPossessedHunchbone.class, GhostlyConfig.MOBS.possessedHunchboneSpawnRate, 4, 4, EnumCreatureType.MONSTER, biomeArray);
		if (GhostlyConfig.MOBS.possessedWitherHunchbones && GhostlyConfig.MOBS.possessedWitherHunchboneSpawnRate != 0 && !GhostlyConfig.MOBS.possessedWitherHunchboneFortressRestriction && Loader.isModLoaded("hardcoredimensionexpansion")) EntityRegistry.addSpawn(EntityPossessedWitherHunchbone.class, GhostlyConfig.MOBS.possessedWitherHunchboneSpawnRate, 4, 4, EnumCreatureType.MONSTER, netherBiomeArray);
		if (GhostlyConfig.MOBS.possessedWitheredZombies && GhostlyConfig.MOBS.possessedWitheredZombieSpawnRate != 0 && Loader.isModLoaded("hardcoredimensionexpansion")) EntityRegistry.addSpawn(EntityPossessedWitheredZombie.class, GhostlyConfig.MOBS.possessedWitheredZombieSpawnRate, 4, 4, EnumCreatureType.MONSTER, netherBiomeArray);
		if (GhostlyConfig.MOBS.spiritualTurrets && GhostlyConfig.MOBS.spiritualTurretSpawnRate != 0) EntityRegistry.addSpawn(EntitySpiritualTurret.class, GhostlyConfig.MOBS.spiritualTurretSpawnRate, 1, 5, EnumCreatureType.MONSTER, netherBiomeArray);
		if (GhostlyConfig.MOBS.sickenedSpiders && GhostlyConfig.MOBS.sickenedSpiderSpawnRate != 0) EntityRegistry.addSpawn(EntitySickenedSpider.class, GhostlyConfig.MOBS.sickenedSpiderSpawnRate, 4, 2, EnumCreatureType.MONSTER, biomeArray);
		if (GhostlyConfig.MOBS.darknessMages && GhostlyConfig.MOBS.darknessMageSpawnRate != 0) EntityRegistry.addSpawn(EntityDarknessMage.class, GhostlyConfig.MOBS.darknessMageSpawnRate, 1, 1, EnumCreatureType.MONSTER, biomeArray);
		if (GhostlyConfig.MOBS.mutatedWolves && GhostlyConfig.MOBS.mutatedWolfColdForestSpawnRate != 0) EntityRegistry.addSpawn(EntityMutatedWolf.class, GhostlyConfig.MOBS.mutatedWolfColdForestSpawnRate, 4, 4, EnumCreatureType.MONSTER, coldForestBiomeArray);
		if (GhostlyConfig.MOBS.mutatedWolves && GhostlyConfig.MOBS.mutatedWolfForestSpawnRate != 0) EntityRegistry.addSpawn(EntityMutatedWolf.class, GhostlyConfig.MOBS.mutatedWolfForestSpawnRate, 4, 4, EnumCreatureType.MONSTER, forestBiomeArray);
		if (GhostlyConfig.MOBS.mutatedHorses && GhostlyConfig.MOBS.mutatedHorsePlainsSpawnRate != 0) EntityRegistry.addSpawn(EntityMutatedHorse.class, GhostlyConfig.MOBS.mutatedHorsePlainsSpawnRate, 2, 6, EnumCreatureType.MONSTER, plainsBiomeArray);
		if (GhostlyConfig.MOBS.mutatedHorses && GhostlyConfig.MOBS.mutatedHorseSavannaSpawnRate != 0) EntityRegistry.addSpawn(EntityMutatedHorse.class, GhostlyConfig.MOBS.mutatedHorseSavannaSpawnRate, 2, 6, EnumCreatureType.MONSTER, savannaBiomeArray);
		if (GhostlyConfig.MOBS.mutatedCows && GhostlyConfig.MOBS.mutatedCowSpawnRate != 0) EntityRegistry.addSpawn(EntityMutatedCow.class, GhostlyConfig.MOBS.mutatedCowSpawnRate, 4, 4, EnumCreatureType.MONSTER, biomeArray);
		if (GhostlyConfig.MOBS.mutatedOcelots && GhostlyConfig.MOBS.mutatedOcelotSpawnRate != 0) EntityRegistry.addSpawn(EntityMutatedOcelot.class, GhostlyConfig.MOBS.mutatedOcelotSpawnRate, 1, 1, EnumCreatureType.MONSTER, jungleBiomeArray);
		if (GhostlyConfig.MOBS.mutatedPigs && GhostlyConfig.MOBS.mutatedPigSpawnRate != 0) EntityRegistry.addSpawn(EntityMutatedPig.class, GhostlyConfig.MOBS.mutatedPigSpawnRate, 4, 4, EnumCreatureType.MONSTER, biomeArray);
		if (GhostlyConfig.MOBS.mutatedDonkeys && GhostlyConfig.MOBS.mutatedDonkeyPlainsSpawnRate != 0) EntityRegistry.addSpawn(EntityMutatedDonkey.class, GhostlyConfig.MOBS.mutatedDonkeyPlainsSpawnRate, 1, 3, EnumCreatureType.MONSTER, plainsBiomeArray);
		if (GhostlyConfig.MOBS.mutatedDonkeys && GhostlyConfig.MOBS.mutatedDonkeySavannaSpawnRate != 0) EntityRegistry.addSpawn(EntityMutatedDonkey.class, GhostlyConfig.MOBS.mutatedDonkeySavannaSpawnRate, 1, 1, EnumCreatureType.MONSTER, savannaBiomeArray);
		if (GhostlyConfig.MOBS.mutatedSheep && GhostlyConfig.MOBS.mutatedSheepSpawnRate != 0) EntityRegistry.addSpawn(EntityMutatedSheep.class, GhostlyConfig.MOBS.mutatedSheepSpawnRate, 4, 4, EnumCreatureType.MONSTER, biomeArray);
		if (GhostlyConfig.MOBS.mutatedLlamas && GhostlyConfig.MOBS.mutatedLlamaMountainSpawnRate != 0) EntityRegistry.addSpawn(EntityMutatedLlama.class, GhostlyConfig.MOBS.mutatedLlamaMountainSpawnRate, 4, 6, EnumCreatureType.MONSTER, mountainBiomeArray);
		if (GhostlyConfig.MOBS.mutatedLlamas && GhostlyConfig.MOBS.mutatedLlamaHighSavannaSpawnRate != 0) EntityRegistry.addSpawn(EntityMutatedLlama.class, GhostlyConfig.MOBS.mutatedLlamaHighSavannaSpawnRate, 4, 4, EnumCreatureType.MONSTER, highSavannaBiomeArray);
		

	}

}
