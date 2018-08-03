package zombieenderman5.ghostly.common.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import zombieenderman5.ghostly.GhostlyReference;
import zombieenderman5.ghostly.common.item.ItemAxeOfCorporeality;
import zombieenderman5.ghostly.common.item.ItemHoeOfCorporeality;
import zombieenderman5.ghostly.common.item.ItemPickaxeOfCorporeality;
import zombieenderman5.ghostly.common.item.ItemShovelOfCorporeality;
import zombieenderman5.ghostly.common.item.ItemSwordOfCorporeality;

public class GhostlyItemManager {
	
	public static Item swordOfCorporeality;
	public static Item axeOfCorporeality;
	public static Item pickaxeOfCorporeality;
	public static Item shovelOfCorporeality;
	public static Item hoeOfCorporeality;
	
	public static final Item.ToolMaterial CORPOREALITY_TOOL_MATERIAL = EnumHelper.addToolMaterial("corporeality_tool_material", 2, 299, 6.5F, 2.0F, 0);
	
	public static void preInitialization(FMLPreInitializationEvent event) {
		
		swordOfCorporeality = new ItemSwordOfCorporeality();
		axeOfCorporeality = new ItemAxeOfCorporeality();
		pickaxeOfCorporeality = new ItemPickaxeOfCorporeality();
		shovelOfCorporeality = new ItemShovelOfCorporeality();
		hoeOfCorporeality = new ItemHoeOfCorporeality();
		
		ForgeRegistries.ITEMS.register(swordOfCorporeality);
		ForgeRegistries.ITEMS.register(axeOfCorporeality);
		ForgeRegistries.ITEMS.register(pickaxeOfCorporeality);
		ForgeRegistries.ITEMS.register(shovelOfCorporeality);
		ForgeRegistries.ITEMS.register(hoeOfCorporeality);
		
	}
	
	public static void registerRenders() {
		
		registerRender(swordOfCorporeality);
		registerRender(axeOfCorporeality);
		registerRender(pickaxeOfCorporeality);
		registerRender(shovelOfCorporeality);
		registerRender(hoeOfCorporeality);
		
	}
	
	public static void registerRender(Item item) {
		
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(GhostlyReference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
		
	}
	
}
