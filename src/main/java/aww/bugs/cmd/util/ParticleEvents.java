package aww.bugs.cmd.util;

import aww.bugs.cmd.CustomDiamonds;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import io.wispforest.owo.particles.ClientParticles;
import io.wispforest.owo.particles.ServerParticles;
import io.wispforest.owo.util.VectorRandomUtils;
import io.wispforest.owo.util.VectorSerializer;
import net.minecraft.world.World;

public class ParticleEvents {

	public static final Identifier LINE = new Identifier(CustomDiamonds.MOD_ID, "line");
	public static final Identifier BREAK_BLOCK = new Identifier(CustomDiamonds.MOD_ID, "break_block");
	public static final Identifier UNLINK_WEAVER = new Identifier(CustomDiamonds.MOD_ID, "unlink_weaver");
	public static final Identifier CONJURER_SUMMON = new Identifier(CustomDiamonds.MOD_ID, "conjurer_summon");
	public static final Identifier EXTRACTION_RITUAL_FINISHED = new Identifier(CustomDiamonds.MOD_ID,
			"extraction_ritual_finished");
	public static final Identifier PEDESTAL_REMOVED = new Identifier(CustomDiamonds.MOD_ID, "pedestal_removed");
	public static final Identifier SOULFIRE_FORGE_SOULS = new Identifier(CustomDiamonds.MOD_ID, "soulfire_forge_souls");
	public static final Identifier LINK_SOUL_FUNNEL = new Identifier(CustomDiamonds.MOD_ID, "link_soul_funnel");

	@Environment(EnvType.CLIENT)
	public static class Client {
		public static void registerClientListeners() {
			ServerParticles.registerClientSideHandler(UNLINK_WEAVER, (client, pos, buffer) -> {
				BlockPos pedestal = buffer.readBlockPos();
				client.execute(() -> {
					ClientParticles.setParticleCount(20);
					ClientParticles.spawnLine(ParticleTypes.SMOKE, client.world, pos.add(0.5, 0.4, 0.5),
							Vec3d.of(pedestal).add(0.5, 0.5, 0.5), 0);

					ClientParticles.setParticleCount(30);
					ClientParticles.spawnWithinBlock(ParticleTypes.SMOKE, client.world, pedestal);
				});
			});

			ServerParticles.registerClientSideHandler(BREAK_BLOCK, (client, pos, data) -> {
				client.execute(() -> {
					ClientParticles.setParticleCount(3);
					ClientParticles.spawnCubeOutline(ParticleTypes.SOUL_FIRE_FLAME, client.world,
							pos.add(0.175, 0.175, 0.175), 0.65f, 0f);
				});
			});

			ServerParticles.registerClientSideHandler(LINE, (client, pos, data) -> {
				Vec3d start = VectorSerializer.read(data);
				Vec3d end = VectorSerializer.read(data);
				client.execute(() -> {
					ClientParticles.setParticleCount(15);
					ClientParticles.spawnLine(ParticleTypes.ENCHANTED_HIT, client.world, start, end, 0.1f);
				});
			});

			ServerParticles.registerClientSideHandler(CONJURER_SUMMON, (client, pos, data) -> {
				client.execute(() -> {
					Vec3d loc = pos.add(.5, .5, .5);

					ClientParticles.setParticleCount(20);
					ClientParticles.spawn(ParticleTypes.ENCHANTED_HIT, client.world, loc, 2);

					ClientParticles.setParticleCount(20);
					ClientParticles.spawn(ParticleTypes.SOUL_FIRE_FLAME, client.world, loc, 2);
				});
			});

			ServerParticles.registerClientSideHandler(EXTRACTION_RITUAL_FINISHED, (client, pos, data) -> {
				boolean successful = data.readBoolean();
				client.execute(() -> {
					Vec3d loc = pos.add(.5, .5, .5);
					ParticleEffect particle = successful ? ParticleTypes.SOUL_FIRE_FLAME : ParticleTypes.LAVA;

					for (int i = 0; i < 60; ++i) {
						ClientParticles.setVelocity(VectorRandomUtils.getRandomOffset(client.world, Vec3d.ZERO, .25));
						ClientParticles.spawn(particle, client.world, loc, 1.5);

						ClientParticles.spawn(ParticleTypes.LARGE_SMOKE, client.world, loc, 1.5);
					}
				});
			});

			ServerParticles.registerClientSideHandler(PEDESTAL_REMOVED, (client, pos, data) -> {
				Direction direction = data.readEnumConstant(Direction.class);
				client.execute(() -> {
					final Vec3d loc = pos.add(.5, 0, .5);

					ClientParticles.setParticleCount(20);
					ClientParticles.spawnPrecise(ParticleTypes.SMOKE, client.world,
							loc.add(direction.getOffsetX() * .15, .35, direction.getOffsetZ() * .15),
							direction.getOffsetZ() / 4d, 0.1, direction.getOffsetX() / 4d);
				});
			});

			ServerParticles.registerClientSideHandler(SOULFIRE_FORGE_SOULS, (client, pos, data) -> {
				client.execute(() -> {
					ClientParticles.setParticleCount(3);
					ClientParticles.spawnPrecise(ParticleTypes.SOUL, client.world, pos.add(.5, .75, .5), .45, 0, .45);
				});
			});

			ServerParticles.registerClientSideHandler(LINK_SOUL_FUNNEL, (client, pos, data) -> {
				var offset = data.readBlockPos();
				client.execute(() -> {
					float offsetX = 0.5f + offset.getX() / 8f;
					float offsetY = 0.35f;
					float offsetZ = 0.5f + offset.getZ() / 8f;

					ClientParticles.setParticleCount(20);
					ClientParticles.spawnPrecise(ParticleTypes.WITCH, client.world,
							new Vec3d(offsetX, offsetY, offsetZ).add(pos), offset.getZ() / 12d, 0.1f,
							offset.getX() / 12d);
				});
			});
		}
	}

	public static class Server {
		public static void sendRitualFinished(World world, BlockPos pos, boolean success) {
			ServerParticles.issueEvent((ServerWorld) world, Vec3d.of(pos), EXTRACTION_RITUAL_FINISHED,
					byteBuf -> byteBuf.writeBoolean(success));
		}

		public static void sendPedestalRemoved(World world, BlockPos pos, Direction offset) {
			ServerParticles.issueEvent((ServerWorld) world, Vec3d.of(pos), PEDESTAL_REMOVED,
					byteBuf -> byteBuf.writeEnumConstant(offset));
		}

		public static void sendFunnelLinked(World world, BlockPos pos, BlockPos offset) {
			ServerParticles.issueEvent((ServerWorld) world, Vec3d.of(pos), LINK_SOUL_FUNNEL,
					byteBuf -> byteBuf.writeBlockPos(offset));
		}
	}

}
