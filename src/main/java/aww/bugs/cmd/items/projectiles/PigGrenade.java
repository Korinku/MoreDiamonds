package aww.bugs.cmd.items.projectiles;

import java.util.List;

import aww.bugs.cmd.entities.PigGrenadeEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class PigGrenade extends Item {

	public PigGrenade(Settings settings) {
		super(settings);
	}

	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack itemStack = user.getStackInHand(hand); // creates a new ItemStack instance of the user's itemStack
															// in-hand
		world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW,
				SoundCategory.NEUTRAL, 0.5F, 1F); // plays a globalSoundEvent

		user.getItemCooldownManager().set(this, 5);

		if (!world.isClient) {
			PigGrenadeEntity pigGrenadeEntity = new PigGrenadeEntity(world, user);
			pigGrenadeEntity.setItem(itemStack);
			pigGrenadeEntity.setVelocity(user, user.getPitch() + 0.1F, user.getYaw(), 0.0F, 1.5F, 0F);
			world.spawnEntity(pigGrenadeEntity); // spawns entity
		}

		user.incrementStat(Stats.USED.getOrCreateStat(this));
		if (!user.getAbilities().creativeMode) {
			itemStack.decrement(1); // decrements itemStack if user is not in creative mode
		}

		return TypedActionResult.success(itemStack, world.isClient());
	}

	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
		tooltip.add(new TranslatableText("item.cmd.pig_grenade_tip").formatted(Formatting.DARK_PURPLE));
	}

}
