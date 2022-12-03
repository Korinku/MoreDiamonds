package aww.bugs.cmd.lists;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class UltraMasterDToolMaterial implements ToolMaterial {
	private static int durability = 10200;
	private static float miningSpeed = 12.0F;
	private static float attackDamage = 12.0F;
	private static int miningLevel = 5;
	private static int enchantability = 25;

	@Override
	public int getDurability() {
		return durability;
	}

	@Override
	public float getMiningSpeedMultiplier() {
		return miningSpeed;
	}

	@Override
	public float getAttackDamage() {
		return attackDamage;
	}

	@Override
	public int getMiningLevel() {
		return miningLevel;
	}

	@Override
	public int getEnchantability() {
		return enchantability;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return null;
	}
}