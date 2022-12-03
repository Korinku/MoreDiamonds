package aww.bugs.cmd.items.tools.basetools;

import java.util.List;

import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class ModPickaxe extends PickaxeItem {

	public ModPickaxe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
		super(material, attackDamage, attackSpeed, settings);
	}

	@Override
	public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
		Material material = state.getMaterial();

		return material != Material.METAL && material != Material.REPAIR_STATION && material != Material.STONE
				? super.getMiningSpeedMultiplier(stack, state)
				: this.miningSpeed;
	}

	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
		tooltip.add(new TranslatableText("item.cmd.gold_diamond_pickaxe_tip").formatted(Formatting.DARK_PURPLE));
	}
}
