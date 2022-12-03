package aww.bugs.cmd.items.tools.basetools;

import java.util.List;

import aww.bugs.cmd.entities.SoulProjectileEntity;
import aww.bugs.cmd.init.ItemInit;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ModSword extends SwordItem {

	public ModSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
		super(toolMaterial, attackDamage, attackSpeed, settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

		if (!world.isClient()) {
			if (user.getHealth() == user.getMaxHealth()) {

				float damage = (float) ((user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE)
						+ EnchantmentHelper.getAttackDamage(user.getMainHandStack(), EntityGroup.DEFAULT)) * 2.5f
						* 0.8f);

				for (int i = 0; i < 1; i++) {
					SoulProjectileEntity projectile = new SoulProjectileEntity(world, user);
					projectile.refreshPositionAndAngles(user.getX(), user.getEyeY(), user.getZ(), 0, 0);
					projectile.setVelocity(user, user.getPitch(), user.getYaw() - 10 + 5 * i, 0f, 1.5f, 1);
					projectile.setDamage(damage);

					world.spawnEntity(projectile);
				}

				user.getItemCooldownManager().set(ItemInit.ULTRA_MASTER_SWORD,
						10);
				user.getStackInHand(hand).damage(
						20, user,
						player -> player.sendToolBreakStatus(hand));
			}
		}

		return TypedActionResult.success(user.getStackInHand(hand));
	}

	@Override
	public int getMaxUseTime(ItemStack stack) {
		return 72000;
	}

	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
		tooltip.add(new TranslatableText("item.cmd.ultra_master_sword_tip").formatted(Formatting.DARK_PURPLE));
	}

}
