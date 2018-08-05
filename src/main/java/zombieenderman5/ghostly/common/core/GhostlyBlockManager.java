package zombieenderman5.ghostly.common.core;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import zombieenderman5.ghostly.GhostlyReference;
import zombieenderman5.ghostly.common.block.BlockCorporealite;
import zombieenderman5.ghostly.common.block.BlockCorporealiteOre;

public class GhostlyBlockManager {
	
	public static Block corporealiteOre;
	public static Block corporealiteBlock;
	
	public static void preInitialization(FMLPreInitializationEvent event) {
		
		corporealiteOre = new BlockCorporealiteOre();
		corporealiteBlock = new BlockCorporealite();
		
	}
	
	@SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
		
		ForgeRegistries.BLOCKS.register(corporealiteOre);
		ForgeRegistries.BLOCKS.register(corporealiteBlock);
		
	}
	
	public static void registerRenders() {
		
		registerRender(corporealiteOre);
		registerRender(corporealiteBlock);
		
	}
	public static void registerRender(Block block) {
		
		Item item = Item.getItemFromBlock(block);
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(GhostlyReference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
		
	}
	
}
