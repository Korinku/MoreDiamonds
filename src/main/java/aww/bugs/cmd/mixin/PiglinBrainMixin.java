package aww.bugs.cmd.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import aww.bugs.cmd.init.ItemInit;
import aww.bugs.cmd.util.PlayerEquipUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mixin(PiglinBrain.class)
public abstract class PiglinBrainMixin {

	@Inject(method = "wearsGoldArmor", at = @At("HEAD"), cancellable = true)
	private static void wearsGoldArmor(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {

		PlayerEntity player = (PlayerEntity) entity;

		if (PlayerEquipUtil.hasItemInMainHand(player, ItemInit.GOLD_DIAMOND)
				|| PlayerEquipUtil.hasItemInOffHand(player, ItemInit.GOLD_DIAMOND)) {
			cir.setReturnValue(true);
		}
	}

	@Inject(method = "acceptsForBarter", at = @At("RETURN"), cancellable = true)
	private static void acceptsForBarterInject(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
		if (stack.getItem().equals(ItemInit.GOLD_DIAMOND))
			cir.setReturnValue(true);
	}

	@Redirect(method = "loot", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
	private static boolean nuggetLootRedirect(ItemStack stack, Item item) {
		if (stack.getItem().equals(ItemInit.GOLD_DIAMOND)) {
			return true;
		}
		return false;
	}

	@Inject(method = "canGather", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/entity/mob/PiglinEntity;canInsertIntoInventory(Lnet/minecraft/item/ItemStack;)Z"), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
	private static void canGatherInject(PiglinEntity piglin, ItemStack stack, CallbackInfoReturnable<Boolean> cir,
			boolean bl) {
		if (stack.getItem().equals(ItemInit.GOLD_DIAMOND))
			cir.setReturnValue(bl);
	}

}
