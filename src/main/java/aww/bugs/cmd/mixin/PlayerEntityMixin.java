package aww.bugs.cmd.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import aww.bugs.cmd.init.ItemInit;
import aww.bugs.cmd.util.PlayerEquipUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

	protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "isInvulnerableTo", at = @At("HEAD"), cancellable = true)
	public void isInvulnerableTo(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {

		PlayerEntity player = (PlayerEntity) (Object) this;

		if (PlayerEquipUtil.hasItemInMainHand(player, ItemInit.TNT_DIAMOND)
				|| PlayerEquipUtil.hasItemInOffHand(player, ItemInit.TNT_DIAMOND)
				|| PlayerEquipUtil.hasItemInMainHand(player, ItemInit.TNT_SHOVEL)) {
			if (damageSource.isExplosive()) {
				cir.setReturnValue(true);
			}
		}
	}

	@Inject(at = @At("HEAD"), method = "tick")
	private void tick(CallbackInfo info) {

		PlayerEntity player = (PlayerEntity) (Object) this;

		if (PlayerEquipUtil.hasItemInMainHand(player, ItemInit.TNT_DIAMOND)
				|| PlayerEquipUtil.hasItemInOffHand(player, ItemInit.TNT_DIAMOND)) {

			if (player.isSneaking()) {
				TntEntity tntEntity = EntityType.TNT.create(world);
				tntEntity.setFuse(0);
				tntEntity.setPosition(this.getX(), this.getY() - 1, this.getZ());

				world.spawnEntity(tntEntity);
			}
		}

		if (PlayerEquipUtil.hasItemInMainHand(player, ItemInit.PIG_DIAMOND)
				|| PlayerEquipUtil.hasItemInOffHand(player, ItemInit.PIG_DIAMOND)) {
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 10, 1, true, false, false));
		}

		if (PlayerEquipUtil.hasItemInMainHand(player, ItemInit.ULTRA_MASTER_DIAMOND)
				|| PlayerEquipUtil.hasItemInOffHand(player, ItemInit.ULTRA_MASTER_DIAMOND)) {
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 10, 1, true, false, false));
			this.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 10, 1, true, false, false));
		}
	}

}
