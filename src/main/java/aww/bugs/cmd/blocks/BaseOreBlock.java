package aww.bugs.cmd.blocks;

import net.minecraft.block.OreBlock;

public class BaseOreBlock extends OreBlock {

	public BaseOreBlock(Settings settings) {
		super(settings);
		this.settings.requiresTool();
	}

}
