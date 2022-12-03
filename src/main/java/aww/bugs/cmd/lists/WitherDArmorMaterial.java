package aww.bugs.cmd.lists;

import aww.bugs.cmd.init.ItemInit;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class WitherDArmorMaterial implements ArmorMaterial {
	private static int durabilityMultiplier = 71;
	private static int enchantability = 25;
	private static float toughness = 3.0F;
	private static float knochback = 0.0F;
	private static int witherHead = 6;
	private static int witherChest = 11;
	private static int witherLeggings = 9;
	private static int witherBoots = 6;

	private static final int[] BASE_DURABILITY = new int[] { 13, 15, 16, 11 };
	private static final int[] PROTECTION_AMOUNT = new int[] { witherHead, witherLeggings, witherChest, witherBoots };

	@Override
	public int getDurability(EquipmentSlot slot) {
		return BASE_DURABILITY[slot.getEntitySlotId()] * durabilityMultiplier;
	}

	@Override
	public int getProtectionAmount(EquipmentSlot slot) {
		return PROTECTION_AMOUNT[slot.getEntitySlotId()];
	}

	@Override
	public int getEnchantability() {
		return enchantability;
	}

	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(ItemInit.WITHER_DIAMOND);
	}

	@Override
	public String getName() {
		return "wither";
	}

	@Override
	public float getToughness() {
		return toughness;
	}

	@Override
	public float getKnockbackResistance() {
		return knochback;
	}
}
