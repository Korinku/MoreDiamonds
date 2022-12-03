package aww.bugs.cmd.items.diamonds;

import java.util.List;

import aww.bugs.cmd.util.PlayerSpecialAbilities;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WitherDiamond extends BaseDiamond {

	public WitherDiamond(Settings settings) {
		super(settings);
	}

	static int rangeSuffering = 50;
	static int damageSuffering = 2;

	private int tick = 0;

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if (world.isClient)
			return;

		tick++;

		PlayerEntity player = (PlayerEntity) entity;
		BlockPos pos = player.getBlockPos();

		ItemStack equippedOff = player.getOffHandStack();
		ItemStack equippedMain = player.getMainHandStack();

		if (tick > 100) {
			if (stack == equippedOff || stack == equippedMain) {
				PlayerSpecialAbilities.inflictSuffering(world, pos, rangeSuffering, damageSuffering);
				tick = 0;
			}
		}
	}

	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
		tooltip.add(new TranslatableText("item.cmd.wither_diamond_tip").formatted(Formatting.DARK_PURPLE));
	}

}
