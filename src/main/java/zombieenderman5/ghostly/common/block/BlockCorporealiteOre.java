package zombieenderman5.ghostly.common.block;

import java.util.Collections;
import java.util.List;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;

import net.minecraftforge.common.ToolType;

public class BlockCorporealiteOre extends Block {
	
	public BlockCorporealiteOre() {
		super(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.NETHERRACK)
				.hardnessAndResistance(3,5)
						.sound(SoundType.STONE)
						.harvestLevel(2)
						.harvestTool(ToolType.PICKAXE));
		}

	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
		List<ItemStack> dropsOriginal = super.getDrops(state, builder);
		if (!dropsOriginal.isEmpty())
			return dropsOriginal;
		return Collections.singletonList(new ItemStack(this, 1));
	}
}