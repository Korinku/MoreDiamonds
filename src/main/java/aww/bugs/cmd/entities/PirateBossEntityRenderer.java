package aww.bugs.cmd.entities;

import aww.bugs.cmd.CustomDiamonds;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.IllagerEntityRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.IllagerEntityModel;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class PirateBossEntityRenderer
		extends IllagerEntityRenderer<PirateBossEntity> {

	public PirateBossEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new IllagerEntityModel<>(context.getPart(EntityModelLayers.PILLAGER)), 0.5f);
		this.addFeature(new HeldItemFeatureRenderer<PirateBossEntity, IllagerEntityModel<PirateBossEntity>>(this));
	}

	@Override
	public Identifier getTexture(PirateBossEntity entity) {
		return new Identifier(CustomDiamonds.MOD_ID, "textures/entity/pirate_boss.png");
	}

}
