package aww.bugs.cmd.client;

import aww.bugs.cmd.CustomDiamonds;
import aww.bugs.cmd.entities.EntityCreatePacket;
import aww.bugs.cmd.entities.SoulEntityRenderer;
import aww.bugs.cmd.entities.PirateBossEntityRenderer;
import aww.bugs.cmd.util.ParticleEvents;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class CustomDiamondsClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {

		EntityRendererRegistry.register(CustomDiamonds.SOUL_PROJECTILE, SoulEntityRenderer::new);

		ClientPlayNetworking.registerGlobalReceiver(EntityCreatePacket.ID, EntityCreatePacket::onPacket);

		ParticleEvents.Client.registerClientListeners();

		EntityRendererRegistry.register(CustomDiamonds.PIRATE, PirateBossEntityRenderer::new);

	}

}
