package aww.bugs.cmd.lists;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class WoodDToolMaterial implements ToolMaterial {
	private static int durability = 4;
	private static float miningSpeed = 2.0F;
	private static float attackDamage = 10.0F;
	private static int miningLevel = 1;
	private static int enchantability = 0;

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