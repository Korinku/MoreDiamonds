package aww.bugs.cmd.items.tools.basetools;

import aww.bugs.cmd.init.ItemInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ModBowClientRender implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		FabricModelPredicateProviderRegistry.register(ItemInit.LIGHTNING_BOW, new Identifier("pull"),
				(stack, world, entity, i) -> {
					if (entity == null) {
						return 0.0F;
					} else {
						return entity.getActiveItem() != stack ? 0.0F
								: (float) (stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0F;
					}
				});

		FabricModelPredicateProviderRegistry.register(ItemInit.LIGHTNING_BOW, new Identifier("pulling"),
				(stack, world, entity, i) -> {
					return entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
				});
	}

}
