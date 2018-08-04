package zombieenderman5.ghostly.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import zombieenderman5.ghostly.client.core.GhostlyCreativeTabManager;
import zombieenderman5.ghostly.common.core.GhostlyItemManager;

public class BlockCorporealiteOre extends Block {
	
	public BlockCorporealiteOre() {
		
		super(Material.ROCK, MapColor.NETHERRACK);
		setCreativeTab(GhostlyCreativeTabManager.blocks);
		setHardness(3.0F);
		setResistance(5.0F);
		setSoundType(SoundType.STONE);
		setHarvestLevel("pickaxe", 2);
		setUnlocalizedName("corporealite_ore");
		setRegistryName("corporealite_ore");
		ForgeRegistries.ITEMS.register(new ItemBlock(this) {
			
			@Override
			public EnumRarity getRarity(ItemStack stack)
		    {
		        return GhostlyItemManager.CORPOREAL_RARITY;
		    }
			
		}.setRegistryName(this.getRegistryName()));
		
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		
		return Item.getItemFromBlock(this);
		
	}
	
	@Override
	public int quantityDropped(Random random) {
		
		return 1;
		
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		
		return new ItemStack(this);
		
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		
		return 0;
		
	}
	
}
