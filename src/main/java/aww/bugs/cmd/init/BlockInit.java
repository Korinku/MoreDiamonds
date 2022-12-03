package aww.bugs.cmd.init;

import aww.bugs.cmd.CustomDiamonds;
import aww.bugs.cmd.blocks.BaseOreBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockInit {

	// OVERWORLD ORE
	public static final Block WOOD_ORE = new BaseOreBlock(
			FabricBlockSettings.of(Material.STONE).requiresTool().strength(2.0F, 2.0F));

	public static final Block TNT_ORE = new BaseOreBlock(
			FabricBlockSettings.of(Material.STONE).requiresTool().strength(2.0F, 2.0F));

	public static final Block PIG_ORE = new BaseOreBlock(
			FabricBlockSettings.of(Material.STONE).requiresTool().strength(2.0F, 2.0F));

	public static final Block GOLD_DIAMOND_ORE = new BaseOreBlock(
			FabricBlockSettings.of(Material.STONE).requiresTool().strength(2.0F, 2.0F));

	public static final Block LIGHTNING_ORE = new BaseOreBlock(
			FabricBlockSettings.of(Material.STONE).requiresTool().strength(2.0F, 2.0F));

	public static final Block ULTRA_MASTER_DIAMOND_ORE = new BaseOreBlock(
			FabricBlockSettings.of(Material.STONE).requiresTool().strength(2.0F, 2.0F));

	// NETHER ORE
	public static final Block WITHER_ORE = new BaseOreBlock(
			FabricBlockSettings.of(Material.STONE).requiresTool().strength(2.0F, 2.0F));

	public static void registerBlocks() {

		// OVERWORLD ORE
		Registry.register(Registry.BLOCK, new Identifier(CustomDiamonds.MOD_ID, "wood_ore"), WOOD_ORE);
		Registry.register(Registry.BLOCK, new Identifier(CustomDiamonds.MOD_ID, "tnt_ore"), TNT_ORE);
		Registry.register(Registry.BLOCK, new Identifier(CustomDiamonds.MOD_ID, "pig_ore"), PIG_ORE);
		Registry.register(Registry.BLOCK, new Identifier(CustomDiamonds.MOD_ID, "lightning_ore"), LIGHTNING_ORE);
		Registry.register(Registry.BLOCK, new Identifier(CustomDiamonds.MOD_ID, "ultra_master_diamond_ore"),
				ULTRA_MASTER_DIAMOND_ORE);
		Registry.register(Registry.BLOCK, new Identifier(CustomDiamonds.MOD_ID, "gold_diamond_ore"), GOLD_DIAMOND_ORE);

		// NETHER ORE
		Registry.register(Registry.BLOCK, new Identifier(CustomDiamonds.MOD_ID, "wither_ore"), WITHER_ORE);
	}

	public static void registerBlockItems() {

		// OVERWORLD ORE
		Registry.register(Registry.ITEM, new Identifier(CustomDiamonds.MOD_ID, "wood_ore"),
				new BlockItem(WOOD_ORE, new Item.Settings().group(CustomDiamonds.CUSTOM_DIAMONDS)));

		Registry.register(Registry.ITEM, new Identifier(CustomDiamonds.MOD_ID, "tnt_ore"),
				new BlockItem(TNT_ORE, new Item.Settings().group(CustomDiamonds.CUSTOM_DIAMONDS)));

		Registry.register(Registry.ITEM, new Identifier(CustomDiamonds.MOD_ID, "pig_ore"),
				new BlockItem(PIG_ORE, new Item.Settings().group(CustomDiamonds.CUSTOM_DIAMONDS)));

		Registry.register(Registry.ITEM, new Identifier(CustomDiamonds.MOD_ID, "lightning_ore"),
				new BlockItem(LIGHTNING_ORE, new Item.Settings().group(CustomDiamonds.CUSTOM_DIAMONDS)));

		Registry.register(Registry.ITEM, new Identifier(CustomDiamonds.MOD_ID, "gold_diamond_ore"),
				new BlockItem(GOLD_DIAMOND_ORE, new Item.Settings().group(CustomDiamonds.CUSTOM_DIAMONDS)));

		Registry.register(Registry.ITEM, new Identifier(CustomDiamonds.MOD_ID, "ultra_master_diamond_ore"),
				new BlockItem(ULTRA_MASTER_DIAMOND_ORE, new Item.Settings().group(CustomDiamonds.CUSTOM_DIAMONDS)));

		// NETHER ORE
		Registry.register(Registry.ITEM, new Identifier(CustomDiamonds.MOD_ID, "wither_ore"),
				new BlockItem(WITHER_ORE, new Item.Settings().group(CustomDiamonds.CUSTOM_DIAMONDS)));

	}

	public static void registerBlockEntities() {
		// Register block entities here
	}
}
