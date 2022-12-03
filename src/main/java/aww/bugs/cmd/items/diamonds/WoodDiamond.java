package aww.bugs.cmd.items.diamonds;

import java.util.List;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class WoodDiamond extends BaseDiamond {

	public WoodDiamond(Settings settings) {
		super(settings);
	}

	private int tick = 0;

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if (world.isClient)
			return;

		tick++;

		PlayerEntity player = (PlayerEntity) entity;

		ItemStack equippedOff = player.getOffHandStack();
		ItemStack equippedMain = player.getMainHandStack();

		if (tick > 100) {
			if (stack == equippedOff || stack == equippedMain) {
				player.giveItemStack(new ItemStack(Items.APPLE));
				tick = 0;
			}
		}

	}

	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
		tooltip.add(new TranslatableText("item.cmd.wood_diamond_tip").formatted(Formatting.DARK_PURPLE));
	}

}
