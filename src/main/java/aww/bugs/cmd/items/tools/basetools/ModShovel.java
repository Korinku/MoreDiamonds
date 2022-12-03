package aww.bugs.cmd.items.tools.basetools;

import java.util.List;

import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ModShovel extends ShovelItem {

	public ModShovel(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
		super(material, attackDamage, attackSpeed, settings);
	}

	@Override
	public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos,
			LivingEntity miner) {

		for (int i = 0; i < 20; i++) {
			TntEntity tntEntity = EntityType.TNT.create(world);
			tntEntity.setFuse(0);
			tntEntity.setPosition(miner.raycast(4.5d, 0.0f, false).getPos().add(0, -i, 0));

			world.spawnEntity(tntEntity);
		}

		return super.postMine(stack, world, state, pos, miner);
	}

	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
		tooltip.add(new TranslatableText("item.cmd.tnt_shovel_tip").formatted(Formatting.DARK_PURPLE));
	}

}
