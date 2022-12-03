package aww.bugs.cmd.world;

import aww.bugs.cmd.CustomDiamonds;
import aww.bugs.cmd.init.BlockInit;
import net.minecraft.world.gen.feature.Feature;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.CountPlacementModifier;
import net.minecraft.world.gen.decorator.HeightRangePlacementModifier;
import net.minecraft.world.gen.decorator.SquarePlacementModifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;

import net.minecraft.world.gen.GenerationStep;

public class OreGeneration {

	// OVERWORLD

	private static ConfiguredFeature<?, ?> WOOD_ORE_CONFIGURED_FEATURE = Feature.ORE
			.configure(new OreFeatureConfig(
					OreConfiguredFeatures.STONE_ORE_REPLACEABLES, // we use OreConfiguredFeatures.NETHERRACK here
					BlockInit.WOOD_ORE.getDefaultState(),
					9));

	public static PlacedFeature WOOD_ORE_PLACED_FEATURE = WOOD_ORE_CONFIGURED_FEATURE.withPlacement(
			CountPlacementModifier.of(20),
			SquarePlacementModifier.of(),
			HeightRangePlacementModifier.uniform(YOffset.fixed(35), YOffset.fixed(96)));

	private static ConfiguredFeature<?, ?> TNT_ORE_CONFIGURED_FEATURE = Feature.ORE
			.configure(new OreFeatureConfig(
					OreConfiguredFeatures.STONE_ORE_REPLACEABLES, // we use OreConfiguredFeatures.NETHERRACK here
					BlockInit.TNT_ORE.getDefaultState(),
					9));

	public static PlacedFeature TNT_ORE_PLACED_FEATURE = TNT_ORE_CONFIGURED_FEATURE.withPlacement(
			CountPlacementModifier.of(20),
			SquarePlacementModifier.of(),
			HeightRangePlacementModifier.uniform(YOffset.fixed(23), YOffset.fixed(45)));

	private static ConfiguredFeature<?, ?> PIG_ORE_CONFIGURED_FEATURE = Feature.ORE
			.configure(new OreFeatureConfig(
					OreConfiguredFeatures.STONE_ORE_REPLACEABLES, // we use OreConfiguredFeatures.NETHERRACK here
					BlockInit.PIG_ORE.getDefaultState(),
					9));

	public static PlacedFeature PIG_ORE_PLACED_FEATURE = PIG_ORE_CONFIGURED_FEATURE.withPlacement(
			CountPlacementModifier.of(20),
			SquarePlacementModifier.of(),
			HeightRangePlacementModifier.uniform(YOffset.fixed(34), YOffset.fixed(60)));

	private static ConfiguredFeature<?, ?> LIGHTNING_ORE_CONFIGURED_FEATURE = Feature.ORE
			.configure(new OreFeatureConfig(
					OreConfiguredFeatures.STONE_ORE_REPLACEABLES, // we use OreConfiguredFeatures.NETHERRACK here
					BlockInit.LIGHTNING_ORE.getDefaultState(),
					9));

	public static PlacedFeature LIGHTNING_ORE_PLACED_FEATURE = LIGHTNING_ORE_CONFIGURED_FEATURE.withPlacement(
			CountPlacementModifier.of(20),
			SquarePlacementModifier.of(),
			HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(20)));

	private static ConfiguredFeature<?, ?> GOLD_DIAMOND_ORE_CONFIGURED_FEATURE = Feature.ORE
			.configure(new OreFeatureConfig(
					OreConfiguredFeatures.STONE_ORE_REPLACEABLES, // we use OreConfiguredFeatures.NETHERRACK here
					BlockInit.GOLD_DIAMOND_ORE.getDefaultState(),
					9));

	public static PlacedFeature GOLD_DIAMOND_ORE_PLACED_FEATURE = GOLD_DIAMOND_ORE_CONFIGURED_FEATURE.withPlacement(
			CountPlacementModifier.of(20),
			SquarePlacementModifier.of(),
			HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(36)));

	// NETHER ORE

	private static ConfiguredFeature<?, ?> NETHER_WITHER_ORE_CONFIGURED_FEATURE = Feature.ORE
			.configure(new OreFeatureConfig(
					OreConfiguredFeatures.NETHERRACK, // we use OreConfiguredFeatures.NETHERRACK here
					BlockInit.WITHER_ORE.getDefaultState(),
					9));

	public static PlacedFeature NETHER_WITHER_ORE_PLACED_FEATURE = NETHER_WITHER_ORE_CONFIGURED_FEATURE.withPlacement(
			CountPlacementModifier.of(20),
			SquarePlacementModifier.of(),
			HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(64)));

	public static void init() {

		// OVERWORLD

		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
				new Identifier(CustomDiamonds.MOD_ID, "wood_ore"), WOOD_ORE_CONFIGURED_FEATURE);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(
				CustomDiamonds.MOD_ID, "wood_ore"),
				WOOD_ORE_PLACED_FEATURE);
		BiomeModifications.addFeature(
				BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
				RegistryKey.of(Registry.PLACED_FEATURE_KEY,
						new Identifier(CustomDiamonds.MOD_ID, "wood_ore")));

		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
				new Identifier(CustomDiamonds.MOD_ID, "tnt_ore"), TNT_ORE_CONFIGURED_FEATURE);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(
				CustomDiamonds.MOD_ID, "tnt_ore"),
				TNT_ORE_PLACED_FEATURE);
		BiomeModifications.addFeature(
				BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
				RegistryKey.of(Registry.PLACED_FEATURE_KEY,
						new Identifier(CustomDiamonds.MOD_ID, "tnt_ore")));

		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
				new Identifier(CustomDiamonds.MOD_ID, "pig_ore"), PIG_ORE_CONFIGURED_FEATURE);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(
				CustomDiamonds.MOD_ID, "pig_ore"),
				PIG_ORE_PLACED_FEATURE);
		BiomeModifications.addFeature(
				BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
				RegistryKey.of(Registry.PLACED_FEATURE_KEY,
						new Identifier(CustomDiamonds.MOD_ID, "pig_ore")));

		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
				new Identifier(CustomDiamonds.MOD_ID, "lightning_ore"), LIGHTNING_ORE_CONFIGURED_FEATURE);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(
				CustomDiamonds.MOD_ID, "lightning_ore"),
				LIGHTNING_ORE_PLACED_FEATURE);
		BiomeModifications.addFeature(
				BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
				RegistryKey.of(Registry.PLACED_FEATURE_KEY,
						new Identifier(CustomDiamonds.MOD_ID, "lightning_ore")));

		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
				new Identifier(CustomDiamonds.MOD_ID, "gold_diamond_ore"), GOLD_DIAMOND_ORE_CONFIGURED_FEATURE);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(
				CustomDiamonds.MOD_ID, "gold_diamond_ore"),
				GOLD_DIAMOND_ORE_PLACED_FEATURE);
		BiomeModifications.addFeature(
				BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
				RegistryKey.of(Registry.PLACED_FEATURE_KEY,
						new Identifier(CustomDiamonds.MOD_ID, "gold_diamond_ore")));

		// NETHER ORE
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
				new Identifier(CustomDiamonds.MOD_ID, "wither_ore"), NETHER_WITHER_ORE_CONFIGURED_FEATURE);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(
				CustomDiamonds.MOD_ID, "wither_ore"),
				NETHER_WITHER_ORE_PLACED_FEATURE);
		BiomeModifications.addFeature(
				BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES,
				RegistryKey.of(Registry.PLACED_FEATURE_KEY,
						new Identifier(CustomDiamonds.MOD_ID, "wither_ore")));
	}
}
