package aww.bugs.cmd;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import aww.bugs.cmd.entities.PirateBossEntity;
import aww.bugs.cmd.entities.SoulProjectileEntity;
import aww.bugs.cmd.init.BlockInit;
import aww.bugs.cmd.init.ItemInit;
import aww.bugs.cmd.world.OreGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CustomDiamonds implements ModInitializer {

	public static final String MOD_ID = "cmd";
	public static final ItemGroup CUSTOM_DIAMONDS = FabricItemGroupBuilder
			.build(new Identifier(MOD_ID, "custom_diamonds_group"), () -> new ItemStack(ItemInit.WOOD_DIAMOND));

	public static final Logger LOGGER = LogManager.getLogger("cmd");

	public static final EntityType<SoulProjectileEntity> SOUL_PROJECTILE;

	public static final EntityType<PirateBossEntity> PIRATE = FabricEntityTypeBuilder
			.createMob().spawnGroup(SpawnGroup.MONSTER)
			.entityFactory(PirateBossEntity::new)
			.dimensions(EntityDimensions.fixed(0.6f, 1.99f))
			.build();

	static {
		SOUL_PROJECTILE = FabricEntityTypeBuilder
				.<SoulProjectileEntity>create(SpawnGroup.MISC, SoulProjectileEntity::new)
				.dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build();

	}

	@Override
	public void onInitialize() {

		BlockInit.registerBlocks();
		BlockInit.registerBlockItems();
		BlockInit.registerBlockEntities();
		Registry.register(Registry.ENTITY_TYPE, new Identifier(MOD_ID, "soul_projectile"), SOUL_PROJECTILE);

		Registry.register(Registry.ENTITY_TYPE, new Identifier(CustomDiamonds.MOD_ID, "pirate_boss"),
				PIRATE);

		ItemInit.registerItems();
		OreGeneration.init();
	}

}
