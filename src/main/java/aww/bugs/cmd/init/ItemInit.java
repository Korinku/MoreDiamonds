package aww.bugs.cmd.init;

import aww.bugs.cmd.CustomDiamonds;
import aww.bugs.cmd.items.tools.basetools.ModPickaxe;
import aww.bugs.cmd.items.tools.basetools.ModShovel;
import aww.bugs.cmd.items.tools.basetools.ModSword;
import aww.bugs.cmd.items.armor.WitherArmor;
import aww.bugs.cmd.items.diamonds.GoldDiamond;
import aww.bugs.cmd.items.diamonds.LightningDiamond;
import aww.bugs.cmd.items.diamonds.PigDiamond;
import aww.bugs.cmd.items.diamonds.TntDiamond;
import aww.bugs.cmd.items.diamonds.UltraMasterDiamond;
import aww.bugs.cmd.items.diamonds.WitherDiamond;
import aww.bugs.cmd.items.diamonds.WoodDiamond;
import aww.bugs.cmd.items.projectiles.PigGrenade;
import aww.bugs.cmd.items.tools.basetools.ModAxe;
import aww.bugs.cmd.items.tools.basetools.ModBow;
import aww.bugs.cmd.lists.GoldDToolMaterial;
import aww.bugs.cmd.lists.TntDToolMaterial;
import aww.bugs.cmd.lists.UltraMasterDToolMaterial;
import aww.bugs.cmd.lists.WitherDArmorMaterial;
import aww.bugs.cmd.lists.WoodDToolMaterial;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemInit {

	public static final Item WOOD_DIAMOND = new WoodDiamond(
			(new Item.Settings()).group(CustomDiamonds.CUSTOM_DIAMONDS));

	public static final Item PIG_DIAMOND = new PigDiamond(
			(new Item.Settings()).group(CustomDiamonds.CUSTOM_DIAMONDS));

	public static final Item TNT_DIAMOND = new TntDiamond(
			(new Item.Settings()).group(CustomDiamonds.CUSTOM_DIAMONDS));

	public static final Item GOLD_DIAMOND = new GoldDiamond(
			(new Item.Settings()).group(CustomDiamonds.CUSTOM_DIAMONDS));

	public static final Item LIGHTNING_DIAMOND = new LightningDiamond(
			(new Item.Settings()).group(CustomDiamonds.CUSTOM_DIAMONDS));

	public static final Item WITHER_DIAMOND = new WitherDiamond(
			(new Item.Settings()).group(CustomDiamonds.CUSTOM_DIAMONDS));

	public static final Item ULTRA_MASTER_DIAMOND = new UltraMasterDiamond(
			(new Item.Settings()).group(CustomDiamonds.CUSTOM_DIAMONDS));

	// Declare Material Values
	public static final ToolMaterial GOLD_DIAMOND_TOOL_MATERIAL = new GoldDToolMaterial();
	public static final ToolMaterial WOOD_DIAMOND_TOOL_MATERIAL = new WoodDToolMaterial();
	public static final ToolMaterial TNT_DIAMOND_TOLL_MATERIAL = new TntDToolMaterial();
	public static final ToolMaterial ULTRA_MASTER_DIAMOND_TOOL_MATERIAL = new UltraMasterDToolMaterial();

	public static final ArmorMaterial WITHER_DIAMOND_ARMOR_MATERIAL = new WitherDArmorMaterial();

	// Tools

	public static final Item BRUTAL_AXE = new ModAxe(
			WOOD_DIAMOND_TOOL_MATERIAL, 7.0F, -3.2F,
			(new Item.Settings()).group(CustomDiamonds.CUSTOM_DIAMONDS));

	public static final Item GOLD_DIAMOND_PICKAXE = new ModPickaxe(
			GOLD_DIAMOND_TOOL_MATERIAL, -3, -2.3F,
			(new Item.Settings()).group(CustomDiamonds.CUSTOM_DIAMONDS));

	public static final Item LIGHTNING_BOW = new ModBow(
			(new Item.Settings()).maxDamage(3800).group(CustomDiamonds.CUSTOM_DIAMONDS));

	public static final Item TNT_SHOVEL = new ModShovel(TNT_DIAMOND_TOLL_MATERIAL, -3.0F, -3.0F,
			(new Item.Settings()).group(CustomDiamonds.CUSTOM_DIAMONDS));

	public static final Item ULTRA_MASTER_SWORD = new ModSword(ULTRA_MASTER_DIAMOND_TOOL_MATERIAL, 1,
			2.0F - 4.0F,
			(new Item.Settings()).group(CustomDiamonds.CUSTOM_DIAMONDS));

	public static final Item PIG_GRENADE = new PigGrenade(
			new Item.Settings().group(CustomDiamonds.CUSTOM_DIAMONDS).maxCount(16));

	public static final Item WITHER_BOOTS = new WitherArmor(
			WITHER_DIAMOND_ARMOR_MATERIAL, EquipmentSlot.FEET,
			(new Item.Settings()).group(CustomDiamonds.CUSTOM_DIAMONDS));

	public static void registerItems() {

		Registry.register(Registry.ITEM, new Identifier(CustomDiamonds.MOD_ID, "wood_diamond"), WOOD_DIAMOND);
		Registry.register(Registry.ITEM, new Identifier(CustomDiamonds.MOD_ID, "pig_diamond"), PIG_DIAMOND);
		Registry.register(Registry.ITEM, new Identifier(CustomDiamonds.MOD_ID, "tnt_diamond"), TNT_DIAMOND);
		Registry.register(Registry.ITEM, new Identifier(CustomDiamonds.MOD_ID, "gold_diamond"), GOLD_DIAMOND);
		Registry.register(Registry.ITEM, new Identifier(CustomDiamonds.MOD_ID, "lightning_diamond"), LIGHTNING_DIAMOND);
		Registry.register(Registry.ITEM, new Identifier(CustomDiamonds.MOD_ID, "wither_diamond"), WITHER_DIAMOND);
		Registry.register(Registry.ITEM, new Identifier(CustomDiamonds.MOD_ID, "ultra_master_diamond"),
				ULTRA_MASTER_DIAMOND);

		// TOOLS

		Registry.register(Registry.ITEM, new Identifier(CustomDiamonds.MOD_ID, "brutal_axe"), BRUTAL_AXE);

		Registry.register(Registry.ITEM, new Identifier(CustomDiamonds.MOD_ID, "gold_diamond_pickaxe"),
				GOLD_DIAMOND_PICKAXE);

		Registry.register(Registry.ITEM, new Identifier(CustomDiamonds.MOD_ID, "lightning_bow"), LIGHTNING_BOW);

		Registry.register(Registry.ITEM, new Identifier(CustomDiamonds.MOD_ID, "tnt_shovel"), TNT_SHOVEL);

		Registry.register(Registry.ITEM, new Identifier(CustomDiamonds.MOD_ID, "ultra_master_sword"),
				ULTRA_MASTER_SWORD);

		Registry.register(Registry.ITEM, new Identifier(CustomDiamonds.MOD_ID, "pig_grenade"), PIG_GRENADE);

		Registry.register(Registry.ITEM, new Identifier(CustomDiamonds.MOD_ID, "wither_boots"), WITHER_BOOTS);

		// SPAWN EGGS

		Registry.register(Registry.ITEM, new Identifier(CustomDiamonds.MOD_ID, "pirate_boss_spawn_egg"),
				new SpawnEggItem(CustomDiamonds.PIRATE, 7960171, 15263976,
						new FabricItemSettings().group(CustomDiamonds.CUSTOM_DIAMONDS)));
	}

}
