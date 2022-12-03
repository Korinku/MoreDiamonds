package aww.bugs.cmd.items.armor;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WitherArmor extends ArmorItem {

	public WitherArmor(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
		super(material, slot, settings);
	}

	private int ticks;

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		PlayerEntity player = (PlayerEntity) entity;
		ItemStack equippedFeet = player.getEquippedStack(EquipmentSlot.FEET);

		List<BlockBox> oldPos = new ArrayList<BlockBox>();

		BlockBox box = new BlockBox(
				player.getBlockPos().add(0, -1, 0).south(1).west(1).getX(),
				player.getBlockPos().add(0, -1, 0).getY(),
				player.getBlockPos().add(0, -1, 0).north(1).east(1).getZ(),

				player.getBlockPos().add(0, -1, 0).north(1).east(1).getX(),
				player.getBlockPos().add(0, -1, 0).getY(),
				player.getBlockPos().add(0, -1, 0).south(1).west(1).getZ());

		if (stack.equals(equippedFeet)) {

			player.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 10, 1, true, false, false));

			if (!player.isOnGround()) {
				ticks++;
				if (ticks == 25) {
					if (world.getBlockState(player.getBlockPos().add(0, -1, 0)).isAir()) {

						for (BlockPos blockPos : BlockPos.iterate(box.getMinX(), box.getMinY(), box.getMinZ(),
								box.getMaxX(), box.getMaxY(), box.getMaxZ())) {
							if (world.getBlockState(blockPos).isAir()) {

								world.setBlockState(blockPos, Blocks.BARRIER.getDefaultState());

								AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(world,
										blockPos.getX(), blockPos.getY(), blockPos.getZ());

								areaEffectCloudEntity.setRadius(1.0f);
								areaEffectCloudEntity.setWaitTime(0);
								areaEffectCloudEntity.setRadiusGrowth(0);
								areaEffectCloudEntity.setDuration(100);

								world.spawnEntity(areaEffectCloudEntity);

							}

						}

						oldPos.add(box);

					}
				}

				new java.util.Timer().schedule(
						new java.util.TimerTask() {
							@Override
							public void run() {
								for (int i = 0; i < oldPos.size(); i++) {
									for (BlockPos blockPos : BlockPos.iterate(oldPos.get(i).getMinX(),
											oldPos.get(i).getMinY(),
											oldPos.get(i).getMinZ(),
											oldPos.get(i).getMaxX(), oldPos.get(i).getMaxY(),
											oldPos.get(i).getMaxZ())) {
										if (world.getBlockState(blockPos).equals(Blocks.BARRIER.getDefaultState())) {
											world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
										}
									}
									oldPos.remove(i);
								}
							}
						},
						5000);
			} else {
				ticks = 0;
			}
		}

	}

	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
		tooltip.add(new TranslatableText("item.cmd.wither_boots_tip").formatted(Formatting.DARK_PURPLE));
	}

}
