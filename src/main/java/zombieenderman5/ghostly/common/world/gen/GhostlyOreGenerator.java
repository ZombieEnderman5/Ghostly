package zombieenderman5.ghostly.common.world.gen;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.Dimension;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;

import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import zombieenderman5.ghostly.common.core.RegistryHandler;

import static zombieenderman5.ghostly.common.world.gen.GhostlyOreGenerator.OreType.CORPOREALITE_ORE;

public class GhostlyOreGenerator {

		public static void handleWorldGen(final BiomeLoadingEvent event) {
			spawnOreInAllBiomes(CORPOREALITE_ORE, event, Dimension.THE_NETHER.toString());
				}

		private static OreFeatureConfig getOverworldFeatureConfig(OreType ore) {
			return new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
					ore.getBlock().get().getDefaultState(), ore.getMaxVeinSize());
		}

		private static OreFeatureConfig getNetherFeatureConfig(OreType ore) {
			return new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK,
					ore.getBlock().get().getDefaultState(), ore.getMaxVeinSize());
		}

		private static OreFeatureConfig getEndFeatureConfig(OreType ore) {
			return new OreFeatureConfig(new BlockMatchRuleTest(Blocks.END_STONE),
					ore.getBlock().get().getDefaultState(), ore.getMaxVeinSize());
		}

		// Currently only supports vanilla Dimensions
		private static ConfiguredFeature<?, ?> makeOreFeature(OreType ore, String dimensionToSpawnIn) {
			OreFeatureConfig oreFeatureConfig = null;

			if (dimensionToSpawnIn.equals(Dimension.OVERWORLD.toString())) {
				oreFeatureConfig = getOverworldFeatureConfig(ore);
			} else if (dimensionToSpawnIn.equals(Dimension.THE_NETHER.toString())) {
				oreFeatureConfig = getNetherFeatureConfig(ore);
			} else if (dimensionToSpawnIn.equals(Dimension.THE_END.toString())) {
				oreFeatureConfig = getEndFeatureConfig(ore);
			}

			ConfiguredPlacement<TopSolidRangeConfig> configuredPlacement = Placement.RANGE.configure(
					new TopSolidRangeConfig(ore.getMinHeight(), ore.getMinHeight(), ore.getMaxHeight()));

			return registerOreFeature(ore, oreFeatureConfig, configuredPlacement);
		}

		private static void spawnOreInOverworldInGivenBiomes(OreType ore, final BiomeLoadingEvent event, Biome... biomesToSpawnIn) {
			OreFeatureConfig oreFeatureConfig = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
					ore.getBlock().get().getDefaultState(), ore.getMaxVeinSize());

			ConfiguredPlacement<TopSolidRangeConfig> configuredPlacement = Placement.RANGE.configure(
					new TopSolidRangeConfig(ore.getMinHeight(), ore.getMinHeight(), ore.getMaxHeight()));

			ConfiguredFeature<?, ?> oreFeature = registerOreFeature(ore, oreFeatureConfig, configuredPlacement);

			if (Arrays.stream(biomesToSpawnIn).anyMatch(b -> b.getRegistryName().equals(event.getName()))) {
				event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, oreFeature);
			}
		}


		private static void spawnOreInSpecificModBiome(Biome biomeToSpawnIn, OreType currentOreType,
													   final BiomeLoadingEvent event, String dimension) {
			if (event.getName().toString().contains(biomeToSpawnIn.getRegistryName().toString())) {
				event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, makeOreFeature(currentOreType, dimension));
			}
		}

		private static void spawnOreInSpecificBiome(RegistryKey<Biome> biomeToSpawnIn, OreType currentOreType, final BiomeLoadingEvent event, String dimension) {
			if (event.getName().toString().contains(biomeToSpawnIn.getLocation().toString())) {
				event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, makeOreFeature(currentOreType, dimension));
			}
		}

		private static void spawnOreInAllBiomes(OreType currentOreType, final BiomeLoadingEvent event, String dimension) {
			event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
					makeOreFeature(currentOreType, dimension));
		}

		private static ConfiguredFeature<?, ?> registerOreFeature(OreType ore, OreFeatureConfig oreFeatureConfig, ConfiguredPlacement configuredPlacement) {
			return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, ore.getBlock().get().getRegistryName(),
					Feature.ORE.withConfiguration(oreFeatureConfig).withPlacement(configuredPlacement)
							.square()
							.count(ore.getVeinsPerChunk()));
		}
		public enum OreType {
			CORPOREALITE_ORE(Lazy.of(RegistryHandler.CORPOREALITE_ORE), 6, 0, 256, 20);
				private final Lazy<Block> block;
			private final int maxVeinSize;
			private final int minHeight;
			private final int maxHeight;
			private final int veinsPerChunk;

			OreType(Lazy<Block> block, int maxVeinSize, int minHeight, int maxHeight, int veinsPerChunk) {
				this.block = block;
				this.maxVeinSize = maxVeinSize;
				this.minHeight = minHeight;
				this.maxHeight = maxHeight;
				this.veinsPerChunk = veinsPerChunk;
			}

			public int getVeinsPerChunk() {
				return veinsPerChunk;
			}

			public Lazy<Block> getBlock() {
				return block;
			}

			public int getMaxVeinSize() {
				return maxVeinSize;
			}

			public int getMinHeight() {
				return minHeight;
			}

			public int getMaxHeight() {
				return maxHeight;
			}
		}
	}