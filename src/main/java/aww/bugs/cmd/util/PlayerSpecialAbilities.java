package aww.bugs.cmd.util;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class PlayerSpecialAbilities {

	public static void giveHealing(PlayerEntity player, int amount) {
		if (player.age % 180 == 0) {
			if (player.getHealth() < player.getMaxHealth()) {
				player.heal(amount);
			}
		}
	}

	// Set player saturation/food level to max on tick update
	public static void giveSaturationEffect(PlayerEntity player) {
		if (player.age % 180 == 0) {
			if (player.getHungerManager().getFoodLevel() < 20)
				player.getHungerManager().setFoodLevel(player.getHungerManager().getFoodLevel() + 1);
		}
	}

	public static void inflictSuffering(World world, BlockPos pos, int range, int damage) {
		// Scan for hostile mobs
		Box mobBox = (new Box(pos)).expand(range, 2, range);
		List<Entity> list2 = world.getNonSpectatingEntities(Entity.class, mobBox);
		Iterator<Entity> iterator2 = list2.iterator();

		Entity targetEntity;

		// Cycle through and effect entities
		while (iterator2.hasNext()) {
			targetEntity = (Entity) iterator2.next();

			// Exclude some of the harder mobs
			if (targetEntity instanceof WitherEntity ||
					targetEntity instanceof GuardianEntity ||
					targetEntity instanceof BlazeEntity ||
					targetEntity instanceof VexEntity)
				continue;

			if (targetEntity instanceof HostileEntity || targetEntity instanceof PassiveEntity) {
				targetEntity.damage(DamageSource.GENERIC, damage);
			}
		}
	}

	public static void strikerLightning(World world, BlockPos pos, int range) {
		// Scan for hostile mobs
		Box mobBox = (new Box(pos)).expand(range, 2, range);
		List<Entity> list3 = world.getNonSpectatingEntities(Entity.class, mobBox);
		Iterator<Entity> iterator3 = list3.iterator();

		Entity targetEntity;

		// Cycle through and effect entities
		while (iterator3.hasNext()) {
			targetEntity = (Entity) iterator3.next();

			// Exclude some of the harder mobs
			if (targetEntity instanceof WitherEntity ||
					targetEntity instanceof GuardianEntity ||
					targetEntity instanceof BlazeEntity ||
					targetEntity instanceof VexEntity)
				continue;

			if (targetEntity instanceof HostileEntity || targetEntity instanceof PassiveEntity) {
				LightningEntity lEntity = EntityType.LIGHTNING_BOLT.create(world);
				lEntity.setPos(targetEntity.getX(), targetEntity.getY(), targetEntity.getZ());
				world.spawnEntity(lEntity);
			}
		}
	}

}
