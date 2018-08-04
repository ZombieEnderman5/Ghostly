package zombieenderman5.ghostly.common.world.gen;

import java.util.Random;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import zombieenderman5.ghostly.common.core.GhostlyBlockManager;

public class GhostlyOreGenerator implements IWorldGenerator {
	
	private WorldGenerator genCorporealiteOre;
	
	public GhostlyOreGenerator() {
		
		genCorporealiteOre = new WorldGenMinable(GhostlyBlockManager.corporealiteOre.getDefaultState(), 6, BlockMatcher.forBlock(Blocks.NETHERRACK));
		
	}
	
	@Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		
		switch (world.provider.getDimension()) {
	    case 0: //Overworld
	    	
	        break;
	        
	    case -1: //Nether
	    	
	    	this.runGenerator(genCorporealiteOre, world, random, chunkX, chunkZ, 20, 0, 256);
	    	
	        break;
	        
	    case 1: //End

	        break;
	        
		}
    }
    
    private void runGenerator(WorldGenerator generator, World world, Random rand, int chunkX, int chunkZ, int chancesToSpawn, int minHeight, int maxHeight) {
        if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
            throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

        int heightDiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chancesToSpawn; i ++) {
            int x = chunkX * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunkZ * 16 + rand.nextInt(16);
            generator.generate(world, rand, new BlockPos(x, y, z));
        }
    }
	
}