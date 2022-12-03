package aww.bugs.cmd.items.projectiles;

import java.util.UUID;

import aww.bugs.cmd.CustomDiamonds;
import aww.bugs.cmd.entities.EntitySpawnPacket;
import aww.bugs.cmd.entities.PigGrenadeEntity;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;

@SuppressWarnings({ "resource", "unchecked", "rawtypes" })
public class PigGrenadeClientRender implements ClientModInitializer {

	public static final Identifier PacketID = new Identifier(CustomDiamonds.MOD_ID, "pig_grenade");

	public void onInitializeClient() {
		EntityRendererRegistry.register(PigGrenadeEntity.PigGrenadeEntityType,
				(context) -> new FlyingItemEntityRenderer(context));
		this.receiveEntityPacket();

	}

	public void receiveEntityPacket() {
		System.out.println("OLA");
		ClientPlayNetworking.registerGlobalReceiver(PacketID, (client, handler, byteBuf, responseSender) -> {
			EntityType<?> et = Registry.ENTITY_TYPE.get(byteBuf.readVarInt());
			UUID uuid = byteBuf.readUuid();
			int entityId = byteBuf.readVarInt();
			Vec3d pos = EntitySpawnPacket.PacketBufUtil.readVec3d(byteBuf);
			float pitch = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
			float yaw = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
			client.execute(() -> {
				if (MinecraftClient.getInstance().world == null)
					throw new IllegalStateException("Tried to spawn entity in a null world!");
				Entity e = et.create(MinecraftClient.getInstance().world);
				if (e == null)
					throw new IllegalStateException(
							"Failed to create instance of entity \"" + Registry.ENTITY_TYPE.getId(et) + "\"!");
				e.updateTrackedPosition(pos);
				e.setPos(pos.x, pos.y, pos.z);
				e.setPitch(pitch);
				e.setYaw(yaw);
				e.setId(entityId);
				e.setUuid(uuid);
				MinecraftClient.getInstance().world.addEntity(entityId, e);
			});
		});
	}

}
