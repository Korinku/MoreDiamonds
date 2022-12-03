package aww.bugs.cmd.entities;

import aww.bugs.cmd.CustomDiamonds;
import aww.bugs.cmd.init.ItemInit;
import aww.bugs.cmd.items.projectiles.PigGrenadeClientRender;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class PigGrenadeEntity extends ThrownItemEntity {

	public static final EntityType<PigGrenadeEntity> PigGrenadeEntityType = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier(CustomDiamonds.MOD_ID, "pig_grenade"),
			FabricEntityTypeBuilder.<PigGrenadeEntity>create(SpawnGroup.MISC,
					PigGrenadeEntity::new)
					.dimensions(EntityDimensions.fixed(0.25F, 0.25F)) // dimensions in Minecraft units of the projectile
					.trackRangeBlocks(4).trackedUpdateRate(10) // necessary for all thrown projectiles (as it prevents
																// it from breaking, lol)
					.build() // VERY IMPORTANT DONT DELETE FOR THE LOVE OF GOD PSLSSSSSS
	);

	public PigGrenadeEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
		super(entityType, world);
	}

	public PigGrenadeEntity(World world, LivingEntity owner) {
		super(PigGrenadeEntityType, owner, world);
	}

	public PigGrenadeEntity(World world, double x, double y, double z) {
		super(PigGrenadeEntityType, x, y, z, world);
	}

	@Override
	protected Item getDefaultItem() {
		return ItemInit.PIG_GRENADE;
	}

	protected void onEntityHit(EntityHitResult entityHitResult) { // called on entity hit.
		super.onEntityHit(entityHitResult);
		Entity entity = entityHitResult.getEntity();

		if (entity instanceof LivingEntity livingEntity) { // checks if entity is an instance of LivingEntity
			// traps the mob inside a cage~
			world.setBlockState(entity.getBlockPos().add(1, 0, 0), Blocks.DIRT.getDefaultState());
			world.setBlockState(entity.getBlockPos().add(0, 0, 1), Blocks.DIRT.getDefaultState());
			world.setBlockState(entity.getBlockPos().add(0, 0, -1), Blocks.DIRT.getDefaultState());
			world.setBlockState(entity.getBlockPos().add(-1, 0, 0), Blocks.DIRT.getDefaultState());
			world.setBlockState(entity.getBlockPos().add(1, 1, 0), Blocks.DIRT.getDefaultState());
			world.setBlockState(entity.getBlockPos().add(0, 1, 1), Blocks.DIRT.getDefaultState());
			world.setBlockState(entity.getBlockPos().add(0, 1, -1), Blocks.DIRT.getDefaultState());
			world.setBlockState(entity.getBlockPos().add(-1, 1, 0), Blocks.DIRT.getDefaultState());
			world.setBlockState(entity.getBlockPos().add(1, 2, 0), Blocks.DIRT.getDefaultState());
			world.setBlockState(entity.getBlockPos().add(0, 2, 1), Blocks.DIRT.getDefaultState());
			world.setBlockState(entity.getBlockPos().add(0, 2, -1), Blocks.DIRT.getDefaultState());
			world.setBlockState(entity.getBlockPos().add(-1, 2, 0), Blocks.DIRT.getDefaultState());
			world.setBlockState(entity.getBlockPos().add(0, 2, 0), Blocks.DIRT.getDefaultState());
		}
	}

	protected void onCollision(HitResult hitResult) { // called on collision with a block
		super.onCollision(hitResult);
		if (!this.world.isClient) { // checks if the world is client
			this.world.sendEntityStatus(this, (byte) 3); // particle?
			this.kill(); // kills the projectile
		}

	}

	@Environment(EnvType.CLIENT)
	private ParticleEffect getParticleParameters() { // Not entirely sure, but probably has do to with the snowball's
														// particles. (OPTIONAL)
		ItemStack itemStack = this.getItem();
		return (ParticleEffect) (itemStack.isEmpty() ? ParticleTypes.EXPLOSION
				: new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack));
	}

	@Environment(EnvType.CLIENT)
	public void handleStatus(byte status) { // Also not entirely sure, but probably also has to do with the particles.
											// This method (as well as the previous one) are optional, so if you don't
											// understand, don't include this one.
		if (status == 3) {
			ParticleEffect particleEffect = this.getParticleParameters();

			for (int i = 0; i < 8; ++i) {
				this.world.addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
			}
		}

	}

	@Override
	public Packet<?> createSpawnPacket() {
		return EntitySpawnPacket.create(this, PigGrenadeClientRender.PacketID);
	}

}
