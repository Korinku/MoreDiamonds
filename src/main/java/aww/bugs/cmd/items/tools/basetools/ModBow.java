package aww.bugs.cmd.items.tools.basetools;

import java.util.List;

import aww.bugs.cmd.entities.BowProjectileEntity;
import aww.bugs.cmd.init.ItemInit;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ModBow extends BowItem {

	public ModBow(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

		if (!world.isClient()) {

			float damage = (float) ((user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE)
					+ EnchantmentHelper.getAttackDamage(user.getMainHandStack(), EntityGroup.DEFAULT)) * 1.5f
					* 0.2f * 0);

			for (int i = 0; i < 1; i++) {
				BowProjectileEntity projectile = new BowProjectileEntity(world, user);
				projectile.refreshPositionAndAngles(user.getX(), user.getEyeY(), user.getZ(), 0, 0);
				projectile.setVelocity(user, user.getPitch(), user.getYaw() - 10 + 5 * i, 0f, 1.5f, 1);
				projectile.setDamage(damage);

				world.spawnEntity(projectile);
			}

			user.getItemCooldownManager().set(ItemInit.LIGHTNING_BOW,
					10);
			user.getStackInHand(hand).damage(
					20, user,
					player -> player.sendToolBreakStatus(hand));
		}

		return TypedActionResult.success(user.getStackInHand(hand));
	}

	@Override
	public int getMaxUseTime(ItemStack stack) {
		return 72000;
	}

	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
		tooltip.add(new TranslatableText("item.cmd.lightning_bow_tip").formatted(Formatting.DARK_PURPLE));
	}
}
