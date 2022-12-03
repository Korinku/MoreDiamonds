package aww.bugs.cmd.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PlayerEquipUtil {

	// Generalized check for itemstack in inventory
	public static boolean hasItemInInventory(PlayerEntity player, Item item) {
		PlayerInventory inv = player.getInventory();
		int size = inv.size();

		// Is the item in the player inventory?
		for (int slot = 0; slot < size; slot++) {
			ItemStack stack = inv.getStack(slot);
			if (stack.getItem() == item) {
				return true;
			}
		}

		return false;
	}

	// Generalized check for itemstack in off hand
	public static boolean hasItemInOffHand(PlayerEntity player, Item item) {
		ItemStack offHand = player.getOffHandStack();

		if (offHand.getItem() == item) {
			return true;
		}

		return false;
	}

	// Generalized check for itemstack in main hand
	public static boolean hasItemInMainHand(PlayerEntity player, Item item) {
		ItemStack offHand = player.getMainHandStack();

		if (offHand.getItem() == item) {
			return true;
		}

		return false;
	}

}
