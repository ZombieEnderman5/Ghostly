package zombieenderman5.ghostly.common.core;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import zombieenderman5.ghostly.GhostlyReference;
import zombieenderman5.ghostly.common.item.ItemAxeOfCorporeality;
import zombieenderman5.ghostly.common.item.ItemBowOfCorporeality;
import zombieenderman5.ghostly.common.item.ItemCorporealiteIngot;
import zombieenderman5.ghostly.common.item.ItemCorporealiteNugget;
import zombieenderman5.ghostly.common.item.ItemDustedCorporealityArrow;
import zombieenderman5.ghostly.common.item.ItemHoeOfCorporeality;
import zombieenderman5.ghostly.common.item.ItemPickaxeOfCorporeality;
import zombieenderman5.ghostly.common.item.ItemShadowDust;
import zombieenderman5.ghostly.common.item.ItemShovelOfCorporeality;
import zombieenderman5.ghostly.common.item.ItemSwordOfCorporeality;

public class GhostlyItemManager {
	
	public static Item swordOfCorporeality;
	public static Item axeOfCorporeality;
	public static Item pickaxeOfCorporeality;
	public static Item shovelOfCorporeality;
	public static Item hoeOfCorporeality;
	public static Item bowOfCorporeality;
	public static Item corporealiteIngot;
	public static Item corporealiteNugget;
	public static Item dustedArrowOfCorporeality;
	public static Item shadowDust;
	
	public static final Item.ToolMaterial CORPOREALITY_TOOL_MATERIAL = EnumHelper.addToolMaterial("corporeality_tool_material", 2, 299, 6.5F, 2.0F, 0);
	public static final EnumRarity CORPOREAL_RARITY = EnumHelper.addRarity("corporeal", TextFormatting.GOLD, "Corporeal");
	public static final EnumRarity SHADOW_RARITY = EnumHelper.addRarity("shadow", TextFormatting.DARK_GRAY, "Shadow");
	
	static {
		
		CORPOREALITY_TOOL_MATERIAL.setRepairItem(new ItemStack(corporealiteIngot, 1));
		
	}
	
	public static void preInitialization(FMLPreInitializationEvent event) {
		
		swordOfCorporeality = new ItemSwordOfCorporeality();
		axeOfCorporeality = new ItemAxeOfCorporeality();
		pickaxeOfCorporeality = new ItemPickaxeOfCorporeality();
		shovelOfCorporeality = new ItemShovelOfCorporeality();
		hoeOfCorporeality = new ItemHoeOfCorporeality();
		bowOfCorporeality = new ItemBowOfCorporeality();
		corporealiteIngot = new ItemCorporealiteIngot();
		corporealiteNugget = new ItemCorporealiteNugget();
		dustedArrowOfCorporeality = new ItemDustedCorporealityArrow();
		shadowDust = new ItemShadowDust();
		
		ForgeRegistries.ITEMS.register(swordOfCorporeality);
		ForgeRegistries.ITEMS.register(axeOfCorporeality);
		ForgeRegistries.ITEMS.register(pickaxeOfCorporeality);
		ForgeRegistries.ITEMS.register(shovelOfCorporeality);
		ForgeRegistries.ITEMS.register(hoeOfCorporeality);
		ForgeRegistries.ITEMS.register(bowOfCorporeality);
		ForgeRegistries.ITEMS.register(corporealiteIngot);
		ForgeRegistries.ITEMS.register(corporealiteNugget);
		ForgeRegistries.ITEMS.register(dustedArrowOfCorporeality);
		ForgeRegistries.ITEMS.register(shadowDust);
		
	}
	
	public static void registerRenders() {
		
		registerRender(swordOfCorporeality);
		registerRender(axeOfCorporeality);
		registerRender(pickaxeOfCorporeality);
		registerRender(shovelOfCorporeality);
		registerRender(hoeOfCorporeality);
		registerRender(bowOfCorporeality);
		registerRender(corporealiteIngot);
		registerRender(corporealiteNugget);
		registerRender(dustedArrowOfCorporeality);
		registerRender(shadowDust);
		
	}
	
	public static void registerRender(Item item) {
		
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(GhostlyReference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
		
	}
	
}
